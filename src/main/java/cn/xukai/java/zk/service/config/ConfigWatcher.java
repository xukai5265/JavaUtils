package cn.xukai.java.zk.service.config;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.io.IOException;

import static org.apache.zookeeper.Watcher.Event.*;

/**
 * Created by kaixu on 2017/12/18.
 * zk 作为配置中心使用
 */
public class ConfigWatcher implements Watcher{
    private static final String HOST1="localhost:2181";
    private static final String HOST2="hadoop-7:2181,,hadoop-6:2181,,hadoop-5:2181";
    private ActiveKeyValueStore store;

    public ConfigWatcher(String hosts) throws IOException, InterruptedException {
        store = new ActiveKeyValueStore();
        store.connect(hosts);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        if(watchedEvent.getType()== EventType.NodeDataChanged){
            try {
                displayConfig();
            } catch (KeeperException e) {
                System.out.printf("KeeperExceptions. Exiting.\n", e);
            } catch (InterruptedException e) {
                System.err.println("Interrupted. exiting. ");
                Thread.currentThread().interrupt();
            }
        }
    }

    private void displayConfig() throws KeeperException, InterruptedException {
        String value = store.read(ConfigUpdater.PATH,this);
        System.out.printf("Read %s as %s\n",ConfigUpdater.PATH,value);
    }

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ConfigWatcher configWatcher = new ConfigWatcher(HOST2);
        configWatcher.displayConfig();
        Thread.sleep(Long.MAX_VALUE);
    }
}
