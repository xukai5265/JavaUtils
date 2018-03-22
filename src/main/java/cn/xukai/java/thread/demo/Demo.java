package cn.xukai.java.thread.demo;

/**
 * Created by kaixu on 2017/11/16.
 */
public class Demo implements Runnable{

    @Override
    public void run() {
        try {
            this.exec();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 对象锁
     * 解释：将synchronized关键字加在方法上，当该类的相同对象调用该方法时，会以同步的方式执行。
     * @throws InterruptedException
     */
    public synchronized void exec() throws InterruptedException {
        for(int i =0;i<10;i++){
            System.out.println(i);
            Thread.sleep(1000*1);
        }
        this.notify();
    }

    public static void main(String[] args) throws InterruptedException {
       Demo d1 = new Demo();
       Demo d2 = d1;
       Thread t1 = new Thread(d1);
       Thread t2 = new Thread(d2);

        t1.start();
        t2.start();

    }
}
