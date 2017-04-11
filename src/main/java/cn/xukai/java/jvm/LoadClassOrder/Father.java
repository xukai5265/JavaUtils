package cn.xukai.java.jvm.LoadClassOrder;

/**
 * Created by xukai on 2017/4/11.
 */
public class Father {
    static {
        System.out.println("father : static block...");
    }
    {
        System.out.println("father : block...");
    }

    public Father() {
        System.out.println("father : 构造器...");
    }

    public void fun(){
        System.out.println("father : fun ...");
    }
}
