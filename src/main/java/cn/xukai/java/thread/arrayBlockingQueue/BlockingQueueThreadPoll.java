package cn.xukai.java.thread.arrayBlockingQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by kaixu on 2017/11/3.
 */
public class BlockingQueueThreadPoll<V> {
    private int consumerCount = 1;
    private int producerCount = 1;
    private int queueSize = 100;
    private BlockingQueue<V> queue;
    private ExecutorService pool;
    private List<Consumer> consumers;
    private List<Producer> producers;

    public BlockingQueueThreadPoll(int consumerCount, int producerCount, int queueSize) {
        this.consumerCount=consumerCount;
        this.producerCount=producerCount;
        this.queueSize=queueSize;
    }


    public void run(){
        for(Producer p : producers){
            pool.execute(p);
        }
        for(Consumer c : consumers){
            pool.execute(c);
        }
    }


    public void init(){
        consumers = new ArrayList<>();
        producers = new ArrayList<>();
        queue = new ArrayBlockingQueue<>(queueSize);
        for(int i=0; i<consumerCount; i++){
            Consumer c = new Consumer(queue,8000);
            consumers.add(c);
        }
        for(int i=0; i<producerCount; i++){
            Producer p = new Producer(queue);
            producers.add(p);
        }
        pool = Executors.newFixedThreadPool(consumerCount + producerCount);
    }

    public boolean enqueue(V task){
        boolean result = true;
        try {
            queue.put(task);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            result = false;
        }
        return result;
    }
}
