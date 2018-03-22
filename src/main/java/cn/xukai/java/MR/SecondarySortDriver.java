package cn.xukai.java.MR;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by kaixu on 2018/1/19.
 */
public class SecondarySortDriver extends Configured implements Tool {
    @Override
    public int run(String[] strings) throws Exception {
        Configuration conf = getConf();
        Job job = new Job(conf);
        job.setJarByClass(SecondarySortDriver.class);
        job.setJobName("SecondarySort");
        Path inputPath = new Path(strings[0]);
        Path outputPath = new Path(strings[1]);
//        FileInputFormat.setInputPaths(job.getConfiguration(),inputPath);
        return 0;
    }

    public static void main(String[] args) throws Exception {
        // 确保有两个参数
        if(args.length!=2){
            throw new IllegalArgumentException("Usage: SecondarySortDriver"+"<input-path> <output-path>");
        }
        int returnStatus = ToolRunner.run(new SecondarySortDriver(),args);
        System.exit(returnStatus);
    }

    /**
     * 第一次排序
     * 将日期和温度对儿  定义为java 对象
     */
    class DateTemperaturePair implements Writable,WritableComparable<DateTemperaturePair>{
        private Text yearMonth = new Text();
        private Text day = new Text();
        private IntWritable temperature = new IntWritable();

        @Override
        public int compareTo(DateTemperaturePair o) {
            int compareValue = yearMonth.compareTo(o.yearMonth);
            if(compareValue==0){ // 如果日期相同，比较温度
                compareValue = temperature.compareTo(o.temperature);
            }
            return -1 * compareValue;
        }

        @Override
        public void write(DataOutput dataOutput) throws IOException {

        }

        @Override
        public void readFields(DataInput dataInput) throws IOException {

        }

        public Text getYearMonth() {
            return yearMonth;
        }

        public void setYearMonth(String yearMonth) {
            this.yearMonth = new Text(yearMonth);
        }

        public Text getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = new Text(day);
        }

        public IntWritable getTemperature() {
            return temperature;
        }

        public void setTemperature(IntWritable temperature) {
            this.temperature = temperature;
        }
    }

    /**
     * 将相同的year-month 放入同一个分区中
     */
    class DateTemperaturePartitioner extends Partitioner<DateTemperaturePair,Text>{

        @Override
        public int getPartition(DateTemperaturePair dateTemperaturePair,
                                Text text,
                                int numPartitions) {
            return dateTemperaturePair.yearMonth.hashCode()%numPartitions;
        }
    }

    /**
     * 将year-month 相同的key 放入同一个reduce 中处理
     * 第二次排序
     */
    class DateTemperatureGroupingComparator extends WritableComparator{

        public DateTemperatureGroupingComparator() {
            super(DateTemperaturePair.class,true);
        }

        @Override
        public int compare(WritableComparable a, WritableComparable b) {
            DateTemperaturePair pair1 = (DateTemperaturePair)a;
            DateTemperaturePair pair2 = (DateTemperaturePair)b;
            return pair1.yearMonth.compareTo(pair2.yearMonth);
        }
    }


    /**
     * Mapper 类
     */
    class SecondarySortMapper extends Mapper<LongWritable, Text, DateTemperaturePair, IntWritable>{
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String data = value.toString();
            String [] tokens = data.split(",");
            // yyyy = tokens[0];
            // MM   = tokens[1];
            // DD   = tokens[2];
            // temperature = tokens[3];
            String yearMonth = tokens[0]+tokens[1];
            String day = tokens[2];
            IntWritable temperature = new IntWritable(Integer.parseInt(tokens[3]));
            DateTemperaturePair reduceKey = new DateTemperaturePair();
            reduceKey.setYearMonth(yearMonth);
            reduceKey.setDay(day);
            reduceKey.setTemperature(temperature);
            context.write(reduceKey,temperature);
        }
    }

    class SecondarySortReducer extends Reducer<DateTemperaturePair, IntWritable,Text,Text>{
        @Override
        protected void reduce(DateTemperaturePair key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            StringBuilder sb = new StringBuilder();
            for(IntWritable temperature:values){
                sb.append(temperature);
                sb.append(",");
            }
            context.write(key.yearMonth,new Text(sb.toString()));
        }
    }
}
