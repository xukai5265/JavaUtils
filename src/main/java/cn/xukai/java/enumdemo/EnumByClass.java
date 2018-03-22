package cn.xukai.java.enumdemo;

/**
 * Created by kaixu on 2017/10/17.
 * 这种方式实现的枚举也叫int枚举模式，尽管很常用，但是由int实现的枚举很难保证安全性，
 * 即当调用不在枚举范围内的数值时，需要额外的维护。另外 ，也不利于查看log和测试。
 */
public class EnumByClass {
    public static final int RED=0;
    public static final int GREEN=1;
    public static final int BLUE=2;
}
