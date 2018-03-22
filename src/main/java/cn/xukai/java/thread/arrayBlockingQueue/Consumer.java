package cn.xukai.java.thread.arrayBlockingQueue;

import java.util.concurrent.BlockingQueue;

/**
 * Created by kaixu on 2017/11/3.
 */
public class Consumer implements Runnable{
    protected BlockingQueue queue = null;

    private long sleepTimes = 0;

    public Consumer(BlockingQueue queue) {
        this.queue = queue;
    }

    public Consumer(BlockingQueue queue, long sleepTimes) {
        this.queue = queue;
        this.sleepTimes = sleepTimes;
    }

    @Override
    public synchronized void run() {
        try {
            while(true){
                System.out.println("isEmpty:"+queue.isEmpty()+"\n"+
                        "size:"+queue.size()+"\n"+
                        "remainingCapacity:"+queue.remainingCapacity()+"\n" +
                        "take:"+queue.take()+"\n"+
                        "sleepTimes:"+sleepTimes);  //take 如果队列为空，则阻塞
                System.out.println("-----------------------");

                Thread.sleep(sleepTimes);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
