package cn.xukai.java.hbase;

import cn.xukai.java.hbase.utils.HBaseUtil;
import org.apache.hadoop.hbase.client.Result;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kaixu on 2018/3/22.
 */
public class HBaseTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void put() throws Exception {
        System.out.println("put...");
    }

    @Test
    public void put1() throws Exception {
    }

    @Test
    public void getRows() throws Exception {
        HBaseUtil.init();
        Result result = HBaseUtil.getRow("t1", "1".getBytes());
        HBaseUtil.formatRow(result.raw());
    }

    @Test
    public void getRow() throws Exception {
    }

    @Test
    public void generateRowkey() throws Exception {
    }

}