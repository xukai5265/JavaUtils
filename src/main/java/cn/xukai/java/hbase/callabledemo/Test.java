package cn.xukai.java.hbase.callabledemo;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by kaixu on 2018/6/1.
 */
public class Test extends Configured implements Tool {
    private final ExecutorService internalPool;
    private final int threads;
    public Test() throws IOException {
        this.threads = Runtime.getRuntime().availableProcessors() * 4;
        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setDaemon(true).setNameFormat("internal-pol-%d").build();
        this.internalPool = Executors.newFixedThreadPool(threads, threadFactory);
    }



    @Override
    public int run(String[] strings) throws Exception {
        List<Future> futureList = Lists.newArrayList();
        for(int i=0;i<500;i++){
            Future future = internalPool.submit(new WriteData2Hbase("t2",new HBaseUtil()));
            futureList.add(future);
        }
        for (Future future:futureList){
            future.get(1,TimeUnit.HOURS);
        }
        internalPool.shutdownNow();
        return 0;
    }
    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        ToolRunner.run(new Test(), args);
        System.out.println(System.currentTimeMillis() - startTime);
    }
}
