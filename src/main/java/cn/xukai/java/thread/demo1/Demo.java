package cn.xukai.java.thread.demo1;

/**
 * Created by kaixu on 2017/11/21.
 * 两个线程交叉执行
 * 原理：
 *      1.对两个线程进行同步处理，即当AB两线程是顺序执行的，并不是异步执行的。
 *      2.两线程锁定的对象，必须是相同的对象，此处创建了一个对象Num 作为锁定对象
 *      3.Num类中有一个flag属性（boolean类型）通过该属性true false 变换来决定线程是否执行。
 *      4.wait() notify() 的用法。
 *      5.num.wait() 、 num.notify()
 */
public class Demo {
    public static void main(String[] args) {
        Num num = new Num();
        Thread threadA = new Thread(new ThreadA(num));
        Thread threadB = new Thread(new ThreadB(num));
        threadA.start();
        threadB.start();
    }
}
