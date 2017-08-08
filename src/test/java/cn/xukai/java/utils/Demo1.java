package cn.xukai.java.utils;

/**
 * Created by xukai on 2017/5/2.
 */
public class Demo1 {
    public static void main(String[] args) {
        System.out.println("abc" == "abc");
        for (boolean isLink = true; isLink;) {
            System.out.println(isLink);
            isLink = false;
            System.out.println(isLink);
        }
    }
}
