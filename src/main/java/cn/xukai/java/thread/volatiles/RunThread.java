package cn.xukai.java.thread.volatiles;

/**
 * Created by xukai on 2017/4/14.
 */
public class RunThread extends Thread {
    /*
        volatile 的使用
           强制线程从主内存中取 volatile修饰的变量
        原因分析：
            每个线程都有一个自己的本地内存空间，线程执行时，先把变量从主内存读取到线程的本地内存空间，然后再对变量进行操作。
            现在有两个线程，main线程和RunThread 线程。他们都试图修改isRunning的变量。
            按照jvm的内存模型，main线程将isRunning读取到本地线程内存空间，修改后，再刷回到主内存。
            但是RunThread 运行时，线程一直会在自己本地的内存空间中读取isRunning的变量。
            因此RunThread线程无法读取到main线程改变的isRunning变量。
            从而出现了死循环，导致RunThread无法终止。
         解决办法：
            使用volatile 修饰变量，似的线程强制从主内存中读取volatile 修饰的变量。
     */
//    private boolean isRunning = true;
    volatile private boolean isRunning = true;

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    @Override
    public void run() {
        System.out.println("进入到run 方法中了");
        while(isRunning==true){

        }
        System.out.println("线程执行完毕了！");
    }
}
