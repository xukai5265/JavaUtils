package cn.xukai.java.zk.service.group;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by kaixu on 2017/12/18.
 * 创建组
 */
public class CreateGroup implements Watcher{
    private static final int SESSION_TIMEOUT=5000;
    private static final String HOST1="localhost:2181";
    private static final String HOST2="192.168.56.90:2182,192.168.56.90:2183,192.168.56.90:2184";

    private ZooKeeper zk;
    private CountDownLatch connectedSignal=new CountDownLatch(1);

    @Override
    public void process(WatchedEvent watchedEvent) {
        if(watchedEvent.getState()== Event.KeeperState.SyncConnected){
            connectedSignal.countDown();
        }
    }
    private void close() throws InterruptedException {
        zk.close();
    }

    /**
     * 创建znode
     * 路径：用字符串表示。
     * znode的内容：字节数组，本例中使用空值。
     * 访问控制列表：简称ACL，本例中使用了完全开放的ACL，允许任何客户端对znode进行读写。
     * 创建znode的类型：有两种类型的znode：短暂的和持久的。
     * @param groupName
     * @throws KeeperException
     * @throws InterruptedException
     */
    private void create(String groupName) throws KeeperException, InterruptedException {
        String path="/"+groupName;
        if(zk.exists(path, false)== null){
            zk.create(path, null/*data*/, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        System.out.println("Created:"+path);
    }

    /**
     * watcher 对象接收来自于zk的回调，会启动一个线程连接到zk服务，
     * @param hosts
     * @throws IOException
     * @throws InterruptedException
     */
    private void connect(String hosts) throws IOException, InterruptedException {
        // 实例化zk 对象，这个对象时客户端API中的主要类，并负责维护客户端和zk服务之间的连接
        zk = new ZooKeeper(hosts, SESSION_TIMEOUT, this);
        connectedSignal.await();
    }
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        CreateGroup createGroup = new CreateGroup();
        createGroup.connect(HOST1);
        createGroup.create("c");
        createGroup.close();
    }

}
