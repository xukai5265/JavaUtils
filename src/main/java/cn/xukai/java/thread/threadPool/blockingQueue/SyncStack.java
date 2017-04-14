package cn.xukai.java.thread.threadPool.blockingQueue;

/**
 * Created by xukai on 2017/1/6.
 */
public class SyncStack {
    int index = 0;
    SteamBread [] stb = new SteamBread[6];

    public synchronized void push(SteamBread sb){
        while(index==stb.length){//筐满了，即栈满，
            try {
                this.wait();//让当前线程等待
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        this.notify();//唤醒在此对象监视器上等待的单个线程，即消费者线程
        stb[index] = sb;
        this.index++;
    }
    //从框中拿出，相当于出栈
    public synchronized SteamBread pop(){
        while(index==0){//筐空了，即栈空
            try {
                this.wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        this.notify();
        this.index--;//push第n个之后，this.index++，使栈顶为n+1，故return之前要减一
        return stb[index];
    }
}
