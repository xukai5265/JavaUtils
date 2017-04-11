package cn.xukai.java.jvm.LoadClassOrder;

/**
 * Created by xukai on 2017/4/11.
 */
public class Son extends Father{
    static {
        System.out.println("son : static block...");
    }
    {
        System.out.println("son : block...");
    }

    public Son() {
        System.out.println("son : 构造器...");
    }

    @Override
    public void fun() {
        super.fun();
        System.out.println("son : fun ...");
    }
}
