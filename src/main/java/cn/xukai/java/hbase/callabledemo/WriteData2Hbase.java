package cn.xukai.java.hbase.callabledemo;

import com.google.common.collect.Lists;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Callable;

/**
 * Created by kaixu on 2018/6/1.
 */
public class WriteData2Hbase implements Callable<Boolean> {
    private String tableName;
    private HBaseUtil hBaseUtil;

    public WriteData2Hbase(String tableName, HBaseUtil hBaseUtil) {
        this.tableName = tableName;
        this.hBaseUtil = hBaseUtil;
    }

    @Override
    public Boolean call() throws Exception {
        System.out.println("is run...");
        List<Put> list = Lists.newArrayList();
        for (int i=0;i<1000;i++){
            String id = UUID.randomUUID().toString().replace("-", "");
            String name = UUID.randomUUID().toString();
            String content = "进入hbase shell console\n" +
                    "$HBASE_HOME/bin/hbase shell\n" +
                    "如果有kerberos认证，需要事先使用相应的keytab进行一下认证（使用kinit命令），认证成功之后再使用hbase shell进入可以使用whoami命令可查看当前用户\n" +
                    "hbase(main)> whoami\n" +
                    "表的管理\n" +
                    "1）查看有哪些表\n" +
                    "hbase(main)> list\n" +
                    "2）创建表\n" +
                    "\n" +
                    "# 语法：create <table>, {NAME => <family>, VERSIONS => <VERSIONS>}\n" +
                    "# 例如：创建表t1，有两个family name：f1，f2，且版本数均为2\n" +
                    "hbase(main)> create 't1',{NAME => 'f1', VERSIONS => 2},{NAME => 'f2', VERSIONS => 2}\n" +
                    "3）删除表\n" +
                    "分两步：首先disable，然后drop\n" +
                    "例如：删除表t1\n" +
                    "\n" +
                    "hbase(main)> disable 't1'\n" +
                    "hbase(main)> drop 't1'\n" +
                    "4）查看表的结构\n" +
                    "\n" +
                    "# 语法：describe <table>\n" +
                    "# 例如：查看表t1的结构\n" +
                    "hbase(main)> describe 't1'\n" +
                    "5）修改表结构\n" +
                    "修改表结构必须先disable\n" +
                    "\n" +
                    "# 语法：alter 't1', {NAME => 'f1'}, {NAME => 'f2', METHOD => 'delete'}\n" +
                    "# 例如：修改表test1的cf的TTL为180天\n" +
                    "hbase(main)> disable 'test1'\n" +
                    "hbase(main)> alter 'test1',{NAME=>'body',TTL=>'15552000'},{NAME=>'meta', TTL=>'15552000'}\n" +
                    "hbase(main)> enable 'test1'\n" +
                    "权限管理\n" +
                    "1）分配权限\n" +
                    "# 语法 : grant <user> <permissions> <table> <column family> <column qualifier> 参数后面用逗号分隔\n" +
                    "# 权限用五个字母表示： \"RWXCA\".\n" +
                    "# READ('R'), WRITE('W'), EXEC('X'), CREATE('C'), ADMIN('A')\n" +
                    "# 例如，给用户‘test'分配对表t1有读写的权限，\n" +
                    "hbase(main)> grant 'test','RW','t1'\n" +
                    "2）查看权限\n" +
                    "\n" +
                    "# 语法：user_permission <table>\n" +
                    "# 例如，查看表t1的权限列表\n" +
                    "hbase(main)> user_permission 't1'\n" +
                    "3）收回权限\n" +
                    "\n" +
                    "# 与分配权限类似，语法：revoke <user> <table> <column family> <column qualifier>\n" +
                    "# 例如，收回test用户在表t1上的权限\n" +
                    "hbase(main)> revoke 'test','t1'\n" +
                    "表数据的增删改查\n" +
                    "1）添加数据\n" +
                    "# 语法：put <table>,<rowkey>,<family:column>,<value>,<timestamp>\n" +
                    "# 例如：给表t1的添加一行记录：rowkey是rowkey001，family name：f1，column name：col1，value：value01，timestamp：系统默认\n" +
                    "hbase(main)> put 't1','rowkey001','f1:col1','value01'\n" +
                    "用法比较单一。\n" +
                    "2）查询数据\n" +
                    "a）查询某行记录\n" +
                    "\n" +
                    "# 语法：get <table>,<rowkey>,[<family:column>,....]\n" +
                    "# 例如：查询表t1，rowkey001中的f1下的col1的值\n" +
                    "hbase(main)> get 't1','rowkey001', 'f1:col1'\n" +
                    "# 或者：\n" +
                    "hbase(main)> get 't1','rowkey001', {COLUMN=>'f1:col1'}\n" +
                    "# 查询表t1，rowke002中的f1下的所有列值\n" +
                    "hbase(main)> get 't1','rowkey001'\n" +
                    "b）扫描表\n" +
                    "\n" +
                    "# 语法：scan <table>, {COLUMNS => [ <family:column>,.... ], LIMIT => num}\n" +
                    "# 另外，还可以添加STARTROW、TIMERANGE和FITLER等高级功能\n" +
                    "# 例如：扫描表t1的前5条数据\n" +
                    "hbase(main)> scan 't1',{LIMIT=>5}\n" +
                    "c）查询表中的数据行数\n" +
                    "\n" +
                    "# 语法：count <table>, {INTERVAL => intervalNum, CACHE => cacheNum}\n" +
                    "# INTERVAL设置多少行显示一次及对应的rowkey，默认1000；CACHE每次去取的缓存区大小，默认是10，调整该参数可提高查询速度\n" +
                    "# 例如，查询表t1中的行数，每100条显示一次，缓存区为500\n" +
                    "hbase(main)> count 't1', {INTERVAL => 100, CACHE => 500}\n" +
                    "3）删除数据\n" +
                    "a )删除行中的某个列值\n" +
                    "\n" +
                    "# 语法：delete <table>, <rowkey>,  <family:column> , <timestamp>,必须指定列名\n" +
                    "# 例如：删除表t1，rowkey001中的f1:col1的数据\n" +
                    "hbase(main)> delete 't1','rowkey001','f1:col1'\n" +
                    "注：将删除改行f1:col1列所有版本的数据\n" +
                    "b )删除行\n" +
                    "\n" +
                    "# 语法：deleteall <table>, <rowkey>,  <family:column> , <timestamp>，可以不指定列名，删除整行数据\n" +
                    "# 例如：删除表t1，rowk001的数据\n" +
                    "hbase(main)> deleteall 't1','rowkey001'\n" +
                    "c）删除表中的所有数据\n" +
                    "\n" +
                    "# 语法： truncate <table>\n" +
                    "# 其具体过程是：disable table -> drop table -> create table\n" +
                    "# 例如：删除表t1的所有数据\n" +
                    "hbase(main)> truncate 't1'\n" +
                    "Region管理\n" +
                    "1）移动region\n" +
                    "# 语法：move 'encodeRegionName', 'ServerName'\n" +
                    "# encodeRegionName指的regioName后面的编码，ServerName指的是master-status的Region Servers列表\n" +
                    "# 示例\n" +
                    "hbase(main)>move '4343995a58be8e5bbc739af1e91cd72d', 'db-41.xxx.xxx.org,60020,1390274516739'\n" +
                    "2）开启/关闭region\n" +
                    "\n" +
                    "# 语法：balance_switch true|false\n" +
                    "hbase(main)> balance_switch\n" +
                    "3）手动split\n" +
                    "\n" +
                    "# 语法：split 'regionName', 'splitKey'\n" +
                    "4）手动触发major compaction\n" +
                    "\n" +
                    "#语法：\n" +
                    "#Compact all regions in a table:\n" +
                    "#hbase> major_compact 't1'\n" +
                    "#Compact an entire region:\n" +
                    "#hbase> major_compact 'r1'\n" +
                    "#Compact a single column family within a region:\n" +
                    "#hbase> major_compact 'r1', 'c1'\n" +
                    "#Compact a single column family within a table:\n" +
                    "#hbase> major_compact 't1', 'c1'\n" +
                    "配置管理及节点重启\n" +
                    "1）修改hdfs配置\n" +
                    "hdfs配置位置：/etc/hadoop/conf\n" +
                    "# 同步hdfs配置\n" +
                    "cat /home/hadoop/slaves|xargs -i -t scp /etc/hadoop/conf/hdfs-site.xml hadoop@{}:/etc/hadoop/conf/hdfs-site.xml\n" +
                    "#关闭：\n" +
                    "cat /home/hadoop/slaves|xargs -i -t ssh hadoop@{} \"sudo /home/hadoop/cdh4/hadoop-2.0.0-cdh4.2.1/sbin/hadoop-daemon.sh --config /etc/hadoop/conf stop datanode\"\n" +
                    "#启动：\n" +
                    "cat /home/hadoop/slaves|xargs -i -t ssh hadoop@{} \"sudo /home/hadoop/cdh4/hadoop-2.0.0-cdh4.2.1/sbin/hadoop-daemon.sh --config /etc/hadoop/conf start datanode\"\n" +
                    "2）修改hbase配置\n" +
                    "hbase配置位置：\n" +
                    "\n" +
                    "# 同步hbase配置\n" +
                    "cat /home/hadoop/hbase/conf/regionservers|xargs -i -t scp /home/hadoop/hbase/conf/hbase-site.xml hadoop@{}:/home/hadoop/hbase/conf/hbase-site.xml\n" +
                    " \n" +
                    "# graceful重启\n" +
                    "cd ~/hbase\n" +
                    "bin/graceful_stop.sh --restart --reload --debug inspurXXX.xxx.xxx.org";
            Put put = new Put(Bytes.toBytes(id));
            put.addColumn(Bytes.toBytes("cf"), Bytes.toBytes("name"), Bytes.toBytes(name));
            put.addColumn(Bytes.toBytes("cf"), Bytes.toBytes("age"), Bytes.toBytes(new Random().nextInt(80)+""));
            put.addColumn(Bytes.toBytes("cf"), Bytes.toBytes("content"), Bytes.toBytes(content));
            list.add(put);
        }
        hBaseUtil.put(tableName,list);
        return true;
    }
}
