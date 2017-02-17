package cn.xukai.java.learn;

/**
 * Created by xukai on 2017/2/17.
 *
 */
public class BitOperation {
    /*
    43210      位数
    --------
    1010      十进制：10     原始数         number
    10100     十进制：20     左移一位       number = number << 1;
    1010      十进制：10     右移一位       number = number >> 1;

    1 << 30

     */

    public static void main(String[] args) {
        int number = 10;
        //原始数二进制
        printInfo(number);

        //左移一位
        number = number << 1;
        printInfo(number);
        //右移一位
        number = number >> 1;
        printInfo(number);

        //hashmap 最大容量
        System.out.println((1 << 30));

    }
    public static void printInfo(int num){
        System.out.println(Integer.toBinaryString(num));
    }
}
