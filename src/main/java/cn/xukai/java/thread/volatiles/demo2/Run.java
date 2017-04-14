package cn.xukai.java.thread.volatiles.demo2;

/**
 * Created by xukai on 2017/4/14.
 */
public class Run {
    public static void main(String[] args) {
        MyThread[] mythreadArray = new MyThread[100];
        for(int i =0;i<100;i++){
            mythreadArray[i] = new MyThread();
        }
        for(int i=0;i<100;i++){
            mythreadArray[i].start();
        }
    }
}
