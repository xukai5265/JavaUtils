package cn.xukai.java.threadPool;

/**
 * Created by xukai on 2017/1/6.
 */
public class ProduceConsume {
    public static void main(String[] args) {
        SyncStack ss = new SyncStack();//建造一个装馒头的框
        BlockingQueueProducer p = new BlockingQueueProducer(ss);//新建一个生产者，使之持有框
        BlockingQueueConsumer c = new BlockingQueueConsumer(ss);//新建一个消费者，使之持有同一个框
        Thread tp = new Thread(p);//新建一个生产者线程
        Thread tc = new Thread(c);//新建一个消费者线程
        tp.start();//启动生产者线程
        tc.start();//启动消费者线程
    }
}
