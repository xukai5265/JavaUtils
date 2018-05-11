package cn.xukai.java.hbase.secondindex;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.coprocessor.BaseRegionObserver;
import org.apache.hadoop.hbase.coprocessor.ObserverContext;
import org.apache.hadoop.hbase.coprocessor.RegionCoprocessorEnvironment;
import org.apache.hadoop.hbase.regionserver.wal.WALEdit;

import java.io.IOException;
import java.util.List;


/**
 * https://www.cnblogs.com/qingyunzong/p/8697364.html
 * 协处理器：coprocessor 分两种： 1. Observer 2. endpoint
 * 1. Observer : 相当于数据库中的触发器
 *          提供了三种观察接口：
 *                  1. RegionObserver ：提供客户端的数据操作事件钩子：get put delete scan等
 *                  2. WalObserver ：提供Wal 相关操作钩子
 *                  3. MasterObserver ： 提供DDl 类型操作钩子，如：创建删除修改数据表等。
 * 2. endpoint: 类似于数据库中的存储过程，客户端可以调用这些endpoint协处理器执行一段server端代码，
 * 并将server端代码的结果返回给客户端进一步处理，最常见的就是进行聚类操作。如果没有协处理器，当用户要
 * 找出一张表中最大数据，即max 操作，就必须进行全表扫描，在客户端代码内遍历扫描结果，并执行求最大值操作。
 * 这样的方式无法利用底层集群并发的能力，而将计算都集中到了client端统一执行，势必效率低下。利用coprocessor
 * 可以将求最大值的代码部署到Hbase server端，Hbase将利用底层cluster的多节点，并发求出最大值操作，即在每个region
 * 范围内求出最大值，将每个region最大值结果返回给client端，客户端将多个region最大值进行计算求出最大值。这样效率就提升了。
 *
 *
 * Created by kaixu on 2018/4/26.
 */
public class HbaseEsIndexCoprocesser extends BaseRegionObserver {
    static Configuration conf = HBaseConfiguration.create();
    static Connection conn = null;
    static Table table = null;

    static {
        conf.set("hbase.zookeeper.quorum", "hadoop1:2181,hadoop2:2181,hadoop3:2181");
        try {
            conn = ConnectionFactory.createConnection(conf);
            table = conn.getTable(TableName.valueOf("fans"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 此方法是在真正的put方法调用之前进行调用
     * 例如：要向关注表里面插入一条数据    姓名：二狗子    关注的明星：王宝强
     *       自动向fans表中写入一条二级索引数据
     * @param e
     * @param put
     * @param edit
     * @param durability
     * @throws IOException
     */
    @Override
    public void prePut(ObserverContext<RegionCoprocessorEnvironment> e, Put put, WALEdit edit, Durability durability) throws IOException {
        //获取put对象里面的rowkey'ergouzi'
        byte[] row = put.getRow();
        //获取put对象里面的cell
        List<Cell> list = put.get("cf".getBytes(), "star".getBytes());
        Cell cell = list.get(0);

        //创建一个新的put对象
        Put new_put = new Put(cell.getValueArray());
        new_put.addColumn("cf".getBytes(), "fensi".getBytes(), row);
        table.put(new_put);
        conn.close();
    }

    @Override
    public void postPut(ObserverContext<RegionCoprocessorEnvironment> e, Put put, WALEdit edit, Durability durability) throws IOException {
        super.postPut(e, put, edit, durability);
    }
}
