package cn.xukai.java.waitNotify;

/**
 * Created by xukai on 2017/4/1.
 */
public class ThreadDemo {
    private static Object o = new Object(); // 共享资源
    private static boolean flag = true; // 互斥信号量

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (o) {
                    while(true){
                        System.out.print("a");
                        o.notify();
                        if (flag) {
                            flag = false;
                            try {
                                o.wait(); // 当前线程等待
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (o){
                    while (true){
                        System.out.print("b");
                        o.notify(); // 唤醒另外一个进程
                        if(!flag){
                            flag = true;
                            try {
                               o.wait(); // 当前线程等待
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }

        });
        thread1.start();
        thread2.start();

    }

}
