package cn.xukai.java.utils.io;

import org.apache.commons.io.FilenameUtils;

import java.io.IOException;

/**
 * commons-io 示例
 * Created by xukai on 2016/12/29.
 */
public class UtilityExample {
    private static final String EXAMPLE_TXT_PATH =
            "D:\\abc\\2016-12-15\\bb2d7aa3-bdcf-4c07-82c0-66331761adac.wav";
    public static void runExample() throws IOException {
        //FilenameUtils
        System.out.println("Utility Classes example...");
        System.out.println("文件完整路径: " + FilenameUtils.getFullPath(EXAMPLE_TXT_PATH));
        System.out.println("文件名："+FilenameUtils.getName(EXAMPLE_TXT_PATH));
        System.out.println("文件扩展名: " + FilenameUtils.getExtension(EXAMPLE_TXT_PATH));
        System.out.println("Base name of exampleTxt: " + FilenameUtils.getBaseName(EXAMPLE_TXT_PATH));
    }

    public static void main(String[] args) throws IOException {
        UtilityExample.runExample();
    }
}
