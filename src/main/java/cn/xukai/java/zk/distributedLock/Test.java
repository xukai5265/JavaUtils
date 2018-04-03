package cn.xukai.java.zk.distributedLock;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.I0Itec.zkclient.ZkClient;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;

/**
 * Created by kaixu on 2018/4/3.
 */
public class Test {
    private static Logger logger = Logger.getLogger(Test.class);
    private final ExecutorService internalPool;
    private final int threads;
    private static final int DEFAULT_NUM_OPERATIONS = 500000;

    public Test() {
        this.threads = Runtime.getRuntime().availableProcessors()*4;
        // Daemon threads are great for things that get shut down.
        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setDaemon(true).setNameFormat("internal-pol-%d").build();
        this.internalPool = Executors.newFixedThreadPool(threads, threadFactory);
    }

    public void run(){
        int numOperations = DEFAULT_NUM_OPERATIONS;
        List<Future<Boolean>> futures = new ArrayList<>(numOperations);
        for (int i = 0; i < numOperations; i++) {
            String HOST2="hadoop-7:2181,hadoop-6:2181,hadoop-5:2181";
            ZkClient zkClient = new ZkClient(HOST2);
            Future<Boolean> f;
            f = internalPool.submit(new SimpleDistributedLockMutex(zkClient,"/locker"));
            futures.add(f);
        }
        internalPool.shutdownNow();
    }

    public static void main(String[] args) {
        Logger.getLogger("org.apache.zookeeper").setLevel(Level.OFF);
        Test t = new Test();
        t.run();
    }
}
