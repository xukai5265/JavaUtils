package cn.xukai.java.demo;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by kaixu on 2018/3/23.
 */
public class Demo2 {
    public static void main(String[] args) {
        double r = ThreadLocalRandom.current().nextDouble();
        System.out.println(r);


    }
}
