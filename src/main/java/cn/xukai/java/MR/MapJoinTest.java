package cn.xukai.java.MR;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kaixu on 2017/7/27.
 * 两份数据，一份比较小，全部加载到内存，按关键字建立索引。
 * 大数据文件作为map的输入文件，对map()函数每一对输入，都能够方便地和已加载到内存的小数据进行连接。
 * 把连接结果按key输出，经过shuffle阶段，reduce端得到的就是已经按key分组的，
 * 并且连接好了的数据。
 * <p>
 * 这种方法，要使用hadoop中的DistributedCache把小数据分布到各个计算节点，
 * 每个map节点都要把小数据库加载到内存，按关键字建立索引。
 * <p>
 * 这种方法有明显的局限性：有一份数据比较小，在map端，能够把它加载到内存，并进行join操作。
 * <p>
 * <p>
 * 实现：
 * http://www.cnblogs.com/ivanny/p/mapreduce_join.html
 * 有客户数据customer和订单数据orders。
 * customer
 * 客户编号	姓名	    地址	    电话
 * 1	    hanmeimei	ShangHai	110
 * 2	    leilei	    BeiJing	    112
 * 3	    lucy	    GuangZhou	119
 * * order**
 * <p>
 * 订单编号	客户编号	其它字段被忽略
 * 1	        1	        50
 * 2	        1	        200
 * 3	        3	        15
 * 4	        3	        350
 * 5	        3	        58
 * 6	        1	        42
 * 7	        1	        352
 * 8	        2	        1135
 * 9	        2	        400
 * 10	        2	        2000
 * 11	        2	        300
 * <p>
 * 要求对customer和orders按照客户编号进行连接，结果要求对客户编号分组，对订单编号排序，对其它字段不作要求
 * 客户编号	订单编号	订单金额	姓名	地址	电话
 * 1	2	200	hanmeimei	ShangHai	110
 * 1	6	42	hanmeimei	ShangHai	110
 * 1	7	352	hanmeimei	ShangHai	110
 * 2	8	1135	leilei	BeiJing	112
 * 2	9	400	leilei	BeiJing	112
 * 2	10	2000	leilei	BeiJing	112
 * 2	11	300	leilei	BeiJing	112
 * 3	3	15	lucy	GuangZhou	119
 * 3	4	350	lucy	GuangZhou	119
 * 3	5	58	lucy	GuangZhou	119
 */
public class MapJoinTest extends Configured implements Tool {
    private static final String CUSTOMER_CACHE_URL = "hdfs://hadoop1:9000/user/hadoop/mapreduce/cache/customer.txt";
    @Override
    public int run(String[] args) throws Exception {
        Configuration conf = getConf();
        Job job = Job.getInstance(conf, MapJoinTest.class.getSimpleName());
        job.setJarByClass(MapJoinTest.class);

        // 添加customer cache文件
        job.addCacheFile(URI.create(CUSTOMER_CACHE_URL));

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        // map settings
        job.setMapperClass(JoinMapper.class);
        job.setMapOutputKeyClass(CustOrderMapOutKey.class);
        job.setMapOutputValueClass(Text.class);

        // reduce settings
        job.setReducerClass(JoinReducer.class);
        job.setOutputKeyClass(CustOrderMapOutKey.class);
        job.setOutputKeyClass(Text.class);

        boolean res = job.waitForCompletion(true);

        return res ? 0 : 1;
    }

    private static class CustomerBean {
        private int custId;
        private String name;
        private String address;
        private String phone;

        public CustomerBean() {
        }

        public CustomerBean(int custId, String name, String address,
                            String phone) {
            super();
            this.custId = custId;
            this.name = name;
            this.address = address;
            this.phone = phone;
        }


        public int getCustId() {
            return custId;
        }

        public String getName() {
            return name;
        }

        public String getAddress() {
            return address;
        }

        public String getPhone() {
            return phone;
        }
    }

    private static class CustOrderMapOutKey implements WritableComparable<CustOrderMapOutKey> {

        private int custId;
        private int orderId;

        public void set(int custId, int orderId) {
            this.custId = custId;
            this.orderId = orderId;
        }

        public int getCustId() {
            return custId;
        }

        public int getOrderId() {
            return orderId;
        }

        @Override
        public void write(DataOutput out) throws IOException {
            out.writeInt(custId);
            out.writeInt(orderId);
        }

        @Override
        public void readFields(DataInput in) throws IOException {
            custId = in.readInt();
            orderId = in.readInt();
        }

        @Override
        public int compareTo(CustOrderMapOutKey o) {
            int res = Integer.compare(custId, o.custId);
            return res == 0 ? Integer.compare(orderId, o.orderId) : res;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof CustOrderMapOutKey) {
                CustOrderMapOutKey o = (CustOrderMapOutKey) obj;
                return custId == o.custId && orderId == o.orderId;
            } else {
                return false;
            }
        }

        @Override
        public String toString() {
            return custId + "\t" + orderId;
        }
    }

    private static class JoinMapper extends Mapper<LongWritable, Text, CustOrderMapOutKey, Text> {
        private final CustOrderMapOutKey outputKey = new CustOrderMapOutKey();
        private final Text outputValue = new Text();

        /**
         * 在内存中customer数据
         */
        private static final Map<Integer, CustomerBean> CUSTOMER_MAP = new HashMap<>();

        @Override
        protected void map(LongWritable key, Text value, Context context)
                throws IOException, InterruptedException {

            // 格式: 订单编号 客户编号    订单金额
            String[] cols = value.toString().split("\t");
            if (cols.length < 3) {
                return;
            }

            int custId = Integer.parseInt(cols[1]);     // 取出客户编号
            CustomerBean customerBean = CUSTOMER_MAP.get(custId);

            if (customerBean == null) {         // 没有对应的customer信息可以连接
                return;
            }

            StringBuffer sb = new StringBuffer();
            sb.append(cols[2])
                    .append("\t")
                    .append(customerBean.getName())
                    .append("\t")
                    .append(customerBean.getAddress())
                    .append("\t")
                    .append(customerBean.getPhone());

            outputValue.set(sb.toString());
            outputKey.set(custId, Integer.parseInt(cols[0]));

            context.write(outputKey, outputValue);
        }
    }

    private static class JoinReducer extends Reducer<CustOrderMapOutKey, Text, CustOrderMapOutKey, Text> {
        @Override
        protected void reduce(CustOrderMapOutKey key, Iterable<Text> values, Context context)
                throws IOException, InterruptedException {
            // 什么事都不用做，直接输出
            for (Text value : values) {
                context.write(key, value);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            new IllegalArgumentException("Usage: <inpath> <outpath>");
            return;
        }

        ToolRunner.run(new Configuration(), new MapJoinTest(), args);
    }
}
