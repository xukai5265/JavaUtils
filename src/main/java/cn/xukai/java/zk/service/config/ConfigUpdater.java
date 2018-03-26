package cn.xukai.java.zk.service.config;

import org.apache.zookeeper.KeeperException;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by kaixu on 2017/12/18.
 */
public class ConfigUpdater {
    private static final String HOST1="localhost:2181";
    private static final String HOST2="hadoop-7:2181,,hadoop-6:2181,,hadoop-5:2181";

    public static final String  PATH="/config-tmp";

    private ActiveKeyValueStore store;
    private Random random=new Random();

    public ConfigUpdater(String hosts) throws IOException, InterruptedException {
        store = new ActiveKeyValueStore();
        store.connect(hosts);
    }
    public void run() throws InterruptedException, KeeperException {
        while(true){
            String value=random.nextInt(100)+"";
            store.write(PATH, value);
            System.out.println("Set "+PATH+" to "+value+"\n");
            TimeUnit.SECONDS.sleep(1);
        }
    }
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        System.out.println("更新配置...");
        ConfigUpdater configUpdater = new ConfigUpdater(HOST2);
        configUpdater.run();
    }
}
