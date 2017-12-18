package cn.xukai.java.zk.connect;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by kaixu on 2017/12/18.
 *
 * 基类 - 用于 连接zk
 */
public class ConnectionWatcher implements Watcher {
    private static final int SESSION_TIMEOUT=5000;
    protected ZooKeeper zk;
    CountDownLatch connectedSignal=new CountDownLatch(1);

    @Override
    public void process(WatchedEvent watchedEvent) {
        if(watchedEvent.getState()== Event.KeeperState.SyncConnected){
            connectedSignal.countDown();
        }
    }

    public void connect(String host) throws IOException, InterruptedException {
        zk = new ZooKeeper(host,SESSION_TIMEOUT,this);
        connectedSignal.await();
    }

    public void close() throws InterruptedException {
        zk.close();
    }
}
