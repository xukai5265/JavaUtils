package cn.xukai.java.utils;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * Created by xukai on 2017/2/9.
 */
public class StringUtilsTest {
    /**
     * 返回字符串world 在字符串 helloworld 中第一次出现的位置。
     * 如果 searchChar 没有在 str 中出现则返回-1，
     * 如果 str 为 null 或 "" ，则也返回-1
     */
    @Test
    public void indexOfTest(){
        System.out.println(StringUtils.indexOf("helloworld", "world"));
    }
    /**
     * StringUtils.isEmpty() & StringUtils.isBlank() 方法的却别
     * blank 会对字符串中的空格进行判断，而empty认为空格、制表符等也是字符串的一部分
     */
    @Test
    public void isBlackTest(){
        String str = " ";
        System.out.println("Empty:"+StringUtils.isEmpty(str));
        System.out.println("Blank:"+StringUtils.isBlank(str));
    }
}
