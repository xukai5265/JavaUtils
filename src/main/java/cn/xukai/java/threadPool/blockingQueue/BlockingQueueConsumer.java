package cn.xukai.java.threadPool.blockingQueue;

/**
 * Created by xukai on 2017/1/6.
 */
public class BlockingQueueConsumer implements Runnable {
    SyncStack ss = null;

    public BlockingQueueConsumer(SyncStack ss) {
        this.ss = ss;
    }

    private boolean isRunning = true;

    public void stop(){
        this.isRunning=false;
    }

    @Override
    public void run() {
        for(int i=0;i<20;i++){//开始消费馒头
            SteamBread stb = ss.pop();
            System.out.println("消费了"+stb);
            try {
                Thread.sleep(100);//每消费一个馒头，睡觉100毫秒。即生产多个，消费一个
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
