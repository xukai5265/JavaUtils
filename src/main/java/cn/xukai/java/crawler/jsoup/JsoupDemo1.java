package cn.xukai.java.crawler.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by kaixu on 2017/8/8.
 * 获取搜索页面的链接地址
 *
 */
public class JsoupDemo1 {
    public static void main(String[] args) {
        Document doc = null;
        try {
            doc = Jsoup.connect("http://news.baidu.com/ns?cl=2&rn=20&tn=news&word=%E7%99%BE%E5%88%86%E7%82%B9").get();
            Element ele = doc.select("div#content_left").first();
            Elements divEles = ele.select("div.result");
            System.out.println(divEles.size());
            Element ele1 = doc.select("p#page").first();
            System.out.println(ele1.text());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
