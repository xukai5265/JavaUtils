package cn.xukai.java.thread.volatiles;

/**
 * Created by xukai on 2017/4/14.
 */
public class Run {
    public static void main(String[] args) throws InterruptedException {
        RunThread thread = new RunThread();
        thread.start();
        Thread.sleep(1000);
        thread.setRunning(false);
    }
}
