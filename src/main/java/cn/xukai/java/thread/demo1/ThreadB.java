package cn.xukai.java.thread.demo1;

/**
 * Created by kaixu on 2017/11/21.
 */
public class ThreadB implements Runnable {
    private Num num;
    public ThreadB(Num num){
        this.num=num;
    }
    @Override
    public void run() {
        while (true){
            synchronized (num){
                try {
                    if(!num.flag){
                        num.wait();
                    }
                    System.out.println("b");
                    num.flag = false;
                    Thread.sleep(2000);
                    num.notify();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
