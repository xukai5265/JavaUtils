package cn.xukai.java.thread.arrayBlockingQueue;

import cn.xukai.java.demo.Demo;

/**
 * Created by kaixu on 2017/11/3.
 */
public class ArrayBlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        // 5：队列大小  true：公平队列
//        BlockingQueue queue = new ArrayBlockingQueue(5,true);
//        Producer producer = new Producer(queue);
//        Consumer consumer1 = new Consumer(queue,8000);
//        new Thread(producer).start();
//        new Thread(consumer1).start();

        BlockingQueueThreadPoll<String> pool = new BlockingQueueThreadPoll<>(5,1,10);
        pool.init();
        pool.run();
    }
}
