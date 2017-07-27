package cn.xukai.java.MR;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.mapreduce.lib.partition.HashPartitioner;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by kaixu on 2017/7/27.
 * tb_a
 *   id  name
     1   北京
     2   天津
     3   河北
     4   山西
     5   内蒙古
     6   辽宁
     7   吉林
     8   黑龙江
 * tb_b
 *   id  statyear    num
     1   2010    1962
     1   2011    2019
     2   2010    1299
     2   2011    1355
     4   2011    3574
     4   2011    3593
     9   2010    2303
     9   2011    2347
 执行命令运行mr
 * hadoop jar javaUtils-1.0-SNAPSHOT.jar cn.xukai.java.MR.ReduceJoinTest
 *
 */
public class ReduceJoinTest {
    // 定义输入路径
    private static final String INPUT_PATH = "hdfs://tianxi-ha/test/data/tb_*";
    // 定义输出路径
    private static final String OUT_PATH = "hdfs://tianxi-ha/test/out";

    public static void main(String[] args) {
        Configuration conf = new Configuration();
        try {
            //创建文件系统
            FileSystem system= FileSystem.get(new URI(OUT_PATH),conf);
            Path out_path = new Path(OUT_PATH);
            //如果输出目录存在就删除
            if(system.exists(out_path)){
                system.delete(out_path,true);
            }
            // 创建任务
            Job job = new Job(conf, ReduceJoinTest.class.getName());
            //1.1   设置输入目录和设置输入数据格式化的类
            FileInputFormat.setInputPaths(job, INPUT_PATH);
            job.setInputFormatClass(TextInputFormat.class);
            //1.2   设置自定义Mapper类和设置map函数输出数据的key和value的类型
            job.setMapperClass(ReduceJoinMapper.class);
            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(Text.class);

            //1.3   设置分区和reduce数量(reduce的数量，和分区的数量对应，因为分区为一个，所以reduce的数量也是一个)
            job.setPartitionerClass(HashPartitioner.class);
            job.setNumReduceTasks(1);

            //1.4   排序
            //1.5   归约
            //2.1   Shuffle把数据从Map端拷贝到Reduce端。
            //2.2   指定Reducer类和输出key和value的类型
            job.setReducerClass(ReduceJoinReduce.class);
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(Text.class);

            //2.3   指定输出的路径和设置输出的格式化类
            FileOutputFormat.setOutputPath(job, new Path(OUT_PATH));
            job.setOutputFormatClass(TextOutputFormat.class);

            job.setJarByClass(ReduceJoinTest.class);
            // 提交作业 退出
            System.exit(job.waitForCompletion(true) ? 0 : 1);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static class ReduceJoinMapper extends Mapper<LongWritable,Text,Text,Text> {
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            // TODO: 2017/7/27  FileSplit是什么？
            //获取输入文件的全路径和名称
            FileSplit fileSplit = (FileSplit) context.getInputSplit();
            String path = fileSplit.getPath().toString();
            //获取输入记录的字符串
            String line = value.toString();
            //抛弃空记录
            if (line == null || line.equals("")){
                return;
            }
            //处理来自tb_a 表的记录
            if(path.contains("tb_a")){
                String[] values = line.split("\t");
                //当数组长度小于2时，视为无效记录
                if (values.length < 2){
                    return;
                }
                //获取id和name
                String id = values[0];
                String name = values[1];
                //把结果写出去
                context.write(new Text(id), new Text("a#" + name));
            }else if(path.contains("tb_b")){
                String[] values = line.split("\t");
                if(values.length<3)
                    return;
                //获取属性
                String id = values[0];
                String statyear = values[1];
                String num = values[2];
                //写出去
                context.write(new Text(id), new Text("b#" + statyear + "  " + num));
            }
        }
    }
    public static class ReduceJoinReduce extends Reducer<Text,Text,Text,Text>{
        @Override
        protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            //用来存放来自tb_a表的数据
            List<String> vectorA = new ArrayList<>();
            //用来存放来自tb_b表的
            List<String> vectorB = new ArrayList<>();
            for(Text val : values){
                String value = val.toString();
                if(value.contains("a#")){
                    vectorA.add(value.substring(2));
                }else if(value.contains("b#")){
                    vectorB.add(value.substring(2));
                }
            }
            //获取两个Vector集合的长度
            int sizeA = vectorA.size();
            int sizeB = vectorB.size();
            //遍历两个集合将结果写出去
            for(int i =0 ;i<sizeA;i++){
                for(int j=0;j<sizeB;j++){
                    context.write(key,new Text("    "+vectorA.get(i)+"  "+vectorB.get(j)));
                }
            }
        }
    }
}
