package cn.xukai.java.tryDemo;

import java.io.*;

/**
 * Created by xukai on 2017/5/2.
 * 从 Java 7 build 105 版本开始，Java 7 的编译器和运行环境支持新的 try-with-resources 语句，
 * 称为 ARM 块(Automatic Resource Management) ，自动资源管理。
 * 新的语句支持包括流以及任何可关闭的资源，例如，一般我们会编写如下代码来释放资源：
 *
 * 采用try-with-resources 语句简化代码见Demo2
 */
public class Demo1 {
    private static void customBufferStreamCopy(File source, File target) {
        InputStream fis = null;
        OutputStream fos = null;
        try {
            fis = new FileInputStream(source);
            fos = new FileOutputStream(target);

            byte[] buf = new byte[8192];

            int i;
            while ((i = fis.read(buf)) != -1) {
                fos.write(buf, 0, i);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(fis);
            close(fos);
        }
    }
    private static void close(Closeable closable) {
        if (closable != null) {
            try {
                closable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        customBufferStreamCopy(new File("D:\\data.json"),new File("E:\\data.json"));
    }
}
