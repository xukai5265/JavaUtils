package cn.xukai.java.thread.demo;

import java.util.concurrent.BlockingQueue;

/**
 * Created by kaixu on 2017/11/3.
 */
public class Producer implements Runnable {
    protected BlockingQueue queue = null;

    public Producer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public synchronized void run() {
        while(true){
            try {
                String data = "producer-put:"+System.currentTimeMillis();
                System.out.println(data);
                queue.put(data);
                System.out.println("producer put before size :" + queue.size());
                System.out.println("++++++++++++++++++++++++++++++++");
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

//    @Override
//    public void run() {
//        try {
//            for(int i=0;i<10;i++){
//                System.out.println("put:"+i);
//                queue.put("AA");
//                Thread.sleep(4000);
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
}
