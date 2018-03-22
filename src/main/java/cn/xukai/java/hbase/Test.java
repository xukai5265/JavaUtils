package cn.xukai.java.hbase;

import cn.xukai.java.hbase.utils.HBaseUtil;
import org.apache.hadoop.hbase.client.Result;

/**
 * Created by kaixu on 2018/3/22.
 */
public class Test {
    public Test() {
        HBaseUtil.init();
    }

    public static void get(){
        Result result = HBaseUtil.getRow("t1", "1".getBytes());
        HBaseUtil.formatRow(result.raw());
    }
    public static void main(String[] args) {
        Test test = new Test();
    }
}
