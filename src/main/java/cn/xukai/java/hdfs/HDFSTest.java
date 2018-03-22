package cn.xukai.java.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

/**
 * Created by kaixu on 2017/11/2.
 */
public class HDFSTest {

    public static void main(String[] args) throws Exception {
//        HDFSTest.jjj();
        HDFSTest.existsHdfs();
    }

    public static void jjj ()throws Exception {
        Configuration conf = new Configuration();
        conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
        FileSystem fs = FileSystem.get(new URI("hdfs://tianxi-ha"), conf);
        //上传文件
        InputStream in =new FileInputStream("D:\\task.ser");
        OutputStream out = fs.create(new Path("/tmp/"));
        IOUtils.copyBytes(in, out, 4096, true);
    }
    public static void existsHdfs() throws Exception {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://tianxi-ha");
        conf.set("dfs.nameservices", "tianxi-ha");
        conf.set("dfs.ha.namenodes.ns1", "nn1,nn2");
        conf.set("dfs.namenode.rpc-address.ns1.nn1", "hadoop-1:8020");
        conf.set("dfs.namenode.rpc-address.ns1.nn2", "hadoop-2:8020");
        //conf.setBoolean(name, value);
        conf.set("dfs.client.failover.proxy.provider.ns1", "org.apache.hadoop.hdfs.server.namenode.ha.ConfiguredFailoverProxyProvider");
        FileSystem fs = FileSystem.get(new URI("hdfs://tianxi-ha"), conf,"hdfs");

        //上传文件
        InputStream in =new FileInputStream("D:\\1.txt");
        OutputStream out = fs.create(new Path("/tmp/task"));
        IOUtils.copyBytes(in, out, 4096, true);
        fs.close();
    }
}
