package cn.xukai.java.thread.volatiles.demo2;

/**
 * Created by xukai on 2017/4/14.
 * 验证volatile 修饰的变量是非原子性的
 */
public class MyThread extends Thread {
    volatile public static int count;

    private static void addCount(){
        for(int i =0;i<100;i++){
            count++;
        }
        System.out.println("count="+count);
    }

    @Override
    public void run() {
        addCount();
    }
}
