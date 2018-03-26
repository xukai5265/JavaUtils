package cn.xukai.java.hbase;

import cn.xukai.java.hbase.utils.HBaseUtil;
import cn.xukai.java.hbase.utils.ThreadPoolUtil;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by kaixu on 2018/3/22.
 */
public class Test {
    private static Logger logger = LoggerFactory.getLogger(Test.class);
    public static void get(){
        HBaseUtil.init();
        Result rs = HBase.getRow("t1","1".getBytes());
        HBaseUtil.formatRow(rs.raw());
    }
    public static void multiThread() throws InterruptedException {
        logger.info("start...");
        ThreadPoolUtil threadPool= ThreadPoolUtil.init();
        HBaseUtil.init();
        System.out.println(HBaseUtil.getConnection().hashCode());
        while (true){
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    logger.info(""+HBaseUtil.getConnection().hashCode());
                    try {
                        Thread.sleep(21000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            Thread.sleep(11000);
        }
    }
    public static void main(String[] args) throws Exception {
//        HBaseUtil.init();
//        String [] columnFamilies = {"cf"};
//        HBaseUtil.createTable("t2",columnFamilies,true);

        String[] s = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F" };
        int partition = 16;
        byte[][] splitKeys = new byte[partition - 1][];
        for (int i = 1; i < partition; i++) {
            splitKeys[i - 1] = Bytes.toBytes(s[i - 1]);
        }
        System.out.println(splitKeys.toString());
    }
}
