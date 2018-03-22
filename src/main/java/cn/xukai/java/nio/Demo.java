package cn.xukai.java.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * Created by kaixu on 2018/2/9.
 */
public class Demo {
    public static void main(String[] args) {
        nio("D:\\资料\\语料\\sougou-train\\C000007.txt");
    }

    public static void nio(String path){
        try (RandomAccessFile aFile= new RandomAccessFile(path,"rw")){
            FileChannel fileChannel = aFile.getChannel();
            ByteBuffer buff = ByteBuffer.allocate(1024);
            Charset charset = Charset.defaultCharset();
            int bytesRead = fileChannel.read(buff);
            System.out.println(bytesRead);
            while (bytesRead!=-1){
                buff.flip();// 将position 置位0、将limit 置位数据最后的位置
                byte[] b = new byte[1024];
                int i =0;
                while(buff.hasRemaining()){
                    b[i]=buff.get();
                    i++;
                }
                System.out.println(new String(b,"utf-8"));
                i=0;
                b = new byte[1024];
                // 清空buffer
                buff.compact();
                // 下一次获取
                bytesRead = fileChannel.read(buff);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
