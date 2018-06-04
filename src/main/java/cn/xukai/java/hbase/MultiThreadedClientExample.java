/**
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.xukai.java.hbase;

import cn.xukai.java.hbase.utils.HBaseUtil;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.KeyOnlyFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.*;


/**
 * Example on how to use HBase's {@link Connection} and {@link Table} in a
 * multi-threaded environment. Each table is a light weight object
 * that is created and thrown away. Connections are heavy weight objects
 * that hold on to zookeeper connections, async processes, and other state.
 *
 * <pre>
 * Usage:
 * bin/hbase org.apache.hadoop.hbase.client.example.MultiThreadedClientExample testTableName 500000
 * </pre>
 *
 * <p>
 * The table should already be created before running the command.
 * This example expects one column family named d.
 * </p>
 * <p>
 * This is meant to show different operations that are likely to be
 * done in a real world application. These operations are:
 * </p>
 *
 * <ul>
 *   <li>
 *     30% of all operations performed are batch writes.
 *     30 puts are created and sent out at a time.
 *     The response for all puts is waited on.
 *   </li>
 *   <li>
 *     20% of all operations are single writes.
 *     A single put is sent out and the response is waited for.
 *   </li>
 *   <li>
 *     50% of all operations are scans.
 *     These scans start at a random place and scan up to 100 rows.
 *   </li>
 * </ul>
 *
 */
public class MultiThreadedClientExample extends Configured implements Tool {
  private static final Log LOG = LogFactory.getLog(MultiThreadedClientExample.class);
  private static final int DEFAULT_NUM_OPERATIONS = 500000;

  /**
   * The name of the column family.
   *
   * d for default.
   */
  private static final byte[] FAMILY = Bytes.toBytes("cf");

  /**
   * For the example we're just using one qualifier.
   */
  private static final byte[] QUAL = Bytes.toBytes("test");

  private final ExecutorService internalPool;

  private final int threads;

  public MultiThreadedClientExample() throws IOException {
    // Base number of threads.
    // This represents the number of threads you application has
    // that can be interacting with an hbase client.
    this.threads = Runtime.getRuntime().availableProcessors() * 4;

    // Daemon threads are great for things that get shut down.
    ThreadFactory threadFactory = new ThreadFactoryBuilder()
        .setDaemon(true).setNameFormat("internal-pol-%d").build();


    this.internalPool = Executors.newFixedThreadPool(threads, threadFactory);
  }

  @Override
  public int run(String[] args) throws Exception {

    if (args.length < 1 || args.length > 2) {
      System.out.println("Usage: " + this.getClass().getName() + " tableName [num_operations]");
      return -1;
    }

    final TableName tableName = TableName.valueOf(args[0]);
    int numOperations = DEFAULT_NUM_OPERATIONS;

    // the second arg is the number of operations to send.
    if (args.length == 2) {
      numOperations = Integer.parseInt(args[1]);
    }

    // Threads for the client only.
    //
    // We don't want to mix hbase and business logic.
    //
    ExecutorService service = new ForkJoinPool(threads * 2);

    // Create two different connections showing how it's possible to
    // separate different types of requests onto different connections
    final Connection writeConnection = ConnectionFactory.createConnection(HBaseConfiguration.create(), service);
    final Connection readConnection = ConnectionFactory.createConnection(HBaseConfiguration.create(), service);

    warmUpConnectionCache(readConnection, tableName);
    warmUpConnectionCache(writeConnection, tableName);
    for (int i=0;i< 500;i++){
      internalPool.submit(new WriteDemoCallable(writeConnection, tableName));
    }
//    execute1(numOperations,writeConnection,readConnection,tableName);

    // Clean up after our selves for cleanliness
    internalPool.shutdownNow();
    service.shutdownNow();
    return 0;
  }

  private void execute1(int numOperations,Connection writeConnection,Connection readConnection,TableName tableName) throws InterruptedException, ExecutionException, TimeoutException {
    List<Future<Boolean>> futures = new ArrayList<>(numOperations);
    for (int i = 0; i < numOperations; i++) {
      double r = ThreadLocalRandom.current().nextDouble();
      Future<Boolean> f;

      // For the sake of generating some synthetic load this queues
      // some different callables.
      // These callables are meant to represent real work done by your application.
      if (r < .30) {
        f = internalPool.submit(new WriteExampleCallable(writeConnection, tableName));
      } else if (r < .50) {
        f = internalPool.submit(new SingleWriteExampleCallable(writeConnection, tableName));
      } else {
        f = internalPool.submit(new ReadExampleCallable(readConnection, tableName));
      }
      futures.add(f);
    }

    // Wait a long time for all the reads/writes to complete
    for (Future<Boolean> f : futures) {
      f.get(10, TimeUnit.MINUTES);
    }
  }

  private void warmUpConnectionCache(Connection connection, TableName tn) throws IOException {
    try (RegionLocator locator = connection.getRegionLocator(tn)) {
      LOG.info(
          "Warmed up region location cache for " + tn
              + " got " + locator.getAllRegionLocations().size());
    }
  }

  public static class WriteDemoCallable implements Callable<Boolean>{
    private final Connection connection;
    private final TableName tableName;

    public WriteDemoCallable(Connection connection, TableName tableName) {
      this.connection = connection;
      this.tableName = tableName;
    }

    @Override
    public Boolean call() throws Exception {
      List<Put> list = Lists.newArrayList();
      for (int i=0;i<1000;i++) {
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
        put.addColumn(Bytes.toBytes("cf"), Bytes.toBytes("age"), Bytes.toBytes(new Random().nextInt(80) + ""));
        put.addColumn(Bytes.toBytes("cf"), Bytes.toBytes("content"), Bytes.toBytes(content));
        list.add(put);
      }
      return true;
    }
  }

  /**
   * Class that will show how to send batches of puts at the same time.
   */
  public static class WriteExampleCallable implements Callable<Boolean> {
    private final Connection connection;
    private final TableName tableName;

    public WriteExampleCallable(Connection connection, TableName tableName) {
      this.connection = connection;
      this.tableName = tableName;
    }

    @Override
    public Boolean call() throws Exception {

      // Table implements Closable so we use the try with resource structure here.
      // https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html
      try (Table t = connection.getTable(tableName)) {
        byte[] value = Bytes.toBytes(Double.toString(ThreadLocalRandom.current().nextDouble()));
        int rows = 30;

        // Array to put the batch
        ArrayList<Put> puts = new ArrayList<>(rows);
        for (int i = 0; i < 30; i++) {
          byte[] rk = Bytes.toBytes(ThreadLocalRandom.current().nextLong());
          Put p = new Put(rk);
          p.addImmutable(FAMILY, QUAL, value);
          puts.add(p);
        }

        // now that we've assembled the batch it's time to push it to hbase.
        t.put(puts);
      }
      return true;
    }
  }

  /**
   * Class to show how to send a single put.
   */
  public static class SingleWriteExampleCallable implements Callable<Boolean> {
    private final Connection connection;
    private final TableName tableName;

    public SingleWriteExampleCallable(Connection connection, TableName tableName) {
      this.connection = connection;
      this.tableName = tableName;
    }

    @Override
    public Boolean call() throws Exception {
      try (Table t = connection.getTable(tableName)) {

        byte[] value = Bytes.toBytes(Double.toString(ThreadLocalRandom.current().nextDouble()));
        byte[] rk = Bytes.toBytes(ThreadLocalRandom.current().nextLong());
        Put p = new Put(rk);
        p.addImmutable(FAMILY, QUAL, value);
        t.put(p);
      }
      return true;
    }
  }


  /**
   * Class to show how to scan some rows starting at a random location.
   */
  public static class ReadExampleCallable implements Callable<Boolean> {
    private final Connection connection;
    private final TableName tableName;

    public ReadExampleCallable(Connection connection, TableName tableName) {
      this.connection = connection;
      this.tableName = tableName;
    }

    @Override
    public Boolean call() throws Exception {

      // total length in bytes of all read rows.
      int result = 0;

      // Number of rows the scan will read before being considered done.
      int toRead = 100;
      try (Table t = connection.getTable(tableName)) {
        byte[] rk = Bytes.toBytes(ThreadLocalRandom.current().nextLong());
        Scan s = new Scan(rk);

        // This filter will keep the values from being sent accross the wire.
        // This is good for counting or other scans that are checking for
        // existence and don't rely on the value.
        s.setFilter(new KeyOnlyFilter());

        // Don't go back to the server for every single row.
        // We know these rows are small. So ask for 20 at a time.
        // This would be application specific.
        //
        // The goal is to reduce round trips but asking for too
        // many rows can lead to GC problems on client and server sides.
        s.setCaching(20);

        // Don't use the cache. While this is a silly test program it's still good to be
        // explicit that scans normally don't use the block cache.
        s.setCacheBlocks(false);

        // Open up the scanner and close it automatically when done.
        try (ResultScanner rs = t.getScanner(s)) {

          // Now go through rows.
          for (Result r : rs) {
            // Keep track of things size to simulate doing some real work.
            result += r.getRow().length;
            toRead -= 1;

            // Most online applications won't be
            // reading the entire table so this break
            // simulates small to medium size scans,
            // without needing to know an end row.
            if (toRead <= 0)  {
              break;
            }
          }
        }
      }
      return result > 0;
    }
  }

  public static void main(String[] args) throws Exception {
    ToolRunner.run(new MultiThreadedClientExample(), args);
  }
}
