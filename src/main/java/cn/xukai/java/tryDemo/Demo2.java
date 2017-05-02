package cn.xukai.java.tryDemo;

import java.io.*;

/**
 * Created by xukai on 2017/5/2.
 *  try-with-resources Demo
 * 代码清晰很多吧？在这个例子中，数据流会在 try 执行完毕后自动被关闭，前提是，这些可关闭的资源必须实现 java.lang.AutoCloseable 接口。
 */
public class Demo2 {
    private static void customBufferStreamCopy(File source, File target){
        try (InputStream fis = new FileInputStream(source);
             OutputStream fos = new FileOutputStream(target)) {
            byte[] buf = new byte[8192];
            int i ;
            while ((i=fis.read(buf))!=-1){
                fos.write(buf,0,i);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
