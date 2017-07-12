package cn.xukai.java.demo;

/**
 * Created by kaixu on 2017/7/12.
 */
public class Demo {
    public static void main(String[] args) {
        /*
        使用 assert（断言） 需要开启断言
        在 vm option 添加 -ea
         */
        String str = "abcd";
        assert (str.equals("abc"));
        System.out.println("?");
    }
}
