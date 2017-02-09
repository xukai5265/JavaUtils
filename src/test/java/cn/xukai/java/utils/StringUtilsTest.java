package cn.xukai.java.utils;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * Created by xukai on 2017/2/9.
 */
public class StringUtilsTest {
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
