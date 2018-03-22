package cn.xukai.java.tryDemo;

/**
 * Created by kaixu on 2017/11/6.
 */
public class Demo {
    public static void main(String[] args) {
        try{
            throw new RuntimeException("this args is not null");
        }catch (RuntimeException e){
;
        }
    }
}
