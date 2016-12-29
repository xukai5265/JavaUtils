package cn.xukai.java.utils.io;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.IOException;

/**
 * commons-io 示例
 * Created by xukai on 2016/12/29.
 */
public class UtilityExample {
    private static final String EXAMPLE_TXT_PATH =
            "D:\\abc\\2016-12-15\\test.txt";

    private static final String PARENT_DIR =
            "D:\\abc\\2016-12-16";
    public static void runExample() throws IOException {
        //FilenameUtils
        System.out.println("Utility Classes example...");
        System.out.println("文件完整路径: " + FilenameUtils.getFullPath(EXAMPLE_TXT_PATH));
        System.out.println("文件名："+FilenameUtils.getName(EXAMPLE_TXT_PATH));
        System.out.println("文件扩展名: " + FilenameUtils.getExtension(EXAMPLE_TXT_PATH));
        System.out.println("Base name of exampleTxt: " + FilenameUtils.getBaseName(EXAMPLE_TXT_PATH));

        //FileUtils
        //我们可以用 FileUtils.getFile(String) 创建一个新文件对象
        File exampleFile = FileUtils.getFile(EXAMPLE_TXT_PATH);
        LineIterator iter = FileUtils.lineIterator(exampleFile,"gbk");
        System.out.println("文件内容...");
        while (iter.hasNext()) {
            System.out.println("\t" + iter.next());
        }
        iter.close();


        // 判断一个文件是否在某个目录中.
        File parent = FileUtils.getFile(PARENT_DIR);
        System.out.println("Parent directory contains exampleTxt file: " + FileUtils.directoryContains(parent, exampleFile));
    }

    public static void main(String[] args) throws IOException {
        UtilityExample.runExample();
    }
}
