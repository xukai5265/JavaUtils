package cn.xukai.java.crawler.jsoup;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.File;
import java.io.IOException;

/**
 * Created by kaixu on 2017/8/8.
 * html 加载 方式
 */
public class JsoupDemo {
    public static void main(String[] args) throws IOException {
        //从字符串中解析html
        String html = "<html><head><title>First parse</title></head>"
                + "<body><p>Parsed HTML into a doc.</p></body></html>";
        Document doc = Jsoup.parse(html);
        Element head = doc.head();
        System.out.println(head.text());

        // 从一个网站获取和解析HTML文档，并查找相关数据
        Document doc51 = Jsoup.connect("http://www.jb51.net/").get();
        System.out.println(doc51.title());

        //从文件中加载html信息
        File input = new File("/tmp/input.html");
        Document docFile = Jsoup.parse(input, "UTF-8", "http://www.jb51.net/");


    }
}
