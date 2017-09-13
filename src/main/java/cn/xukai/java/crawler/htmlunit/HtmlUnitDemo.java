package cn.xukai.java.crawler.htmlunit;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import com.gargoylesoftware.htmlunit.util.Cookie;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Created by kaixu on 2017/8/8.
 */
public class HtmlUnitDemo {

    @Test
    public void Email126Login(){
        try {
            WebClient webClient = new WebClient(BrowserVersion.CHROME);
            //参数设置
//            // 1 启动JS
//            webClient.getOptions().setJavaScriptEnabled(true);
//            // 2 禁用Css，可避免自动二次请求CSS进行渲染
//            webClient.getOptions().setCssEnabled(false);
//            //3 启动客户端重定向
//            webClient.getOptions().setRedirectEnabled(true);
//            // 4 运行错误时，是否抛出异常
//            webClient.getOptions().setThrowExceptionOnScriptError(false);
//            // 5 设置超时
//            webClient.getOptions().setTimeout(50000);
//            //6 设置忽略证书
//            //webClient.getOptions().setUseInsecureSSL(true);
//            //7 设置Ajax
//            //webClient.setAjaxController(new NicelyResynchronizingAjaxController());
//            //8设置cookie
//            webClient.getCookieManager().setCookiesEnabled(true);
            //获取页面
            HtmlPage page = webClient.getPage("http://cn.bing.com/");
            // 根据form的名字获取页面表单，也可以通过索引来获取：page.getForms().get(0)
            HtmlForm form = page.getFormByName("login126");
            System.out.println(form);
            HtmlInput username = form.getInputByName("email");
            System.out.println(username);

        }catch (Exception e){
            System.err.println( "Exception: " + e );
        }
    }

    /**
     * 百度高级搜索
     */
    @Test
    public void HtmlUnitBaiduAdvanceSearch() {
        try{
            // 得到浏览器对象，直接New一个就能得到，现在就好比说你得到了一个浏览器了
            WebClient webclient = new WebClient(BrowserVersion.CHROME);
            // 这里是配置一下不加载css和javaScript,配置起来很简单，是不是
            webclient.getOptions().setCssEnabled(false);
            webclient.getOptions().setJavaScriptEnabled(false);
            // 做的第一件事，去拿到这个网页，只需要调用getPage这个方法即可
            HtmlPage htmlpage = webclient
                    .getPage("http://news.baidu.com/advanced_news.html");
            // 根据名字得到一个表单，查看上面这个网页的源代码可以发现表单的名字叫“f”
            final HtmlForm form = htmlpage.getFormByName("f");
            System.out.println("form:"+form);
            // 同样道理，获取”百度一下“这个按钮
            final HtmlSubmitInput button = form.getInputByValue("百度一下");
            System.out.println("button:"+button);
            // 得到搜索框
            final HtmlTextInput textField = form.getInputByName("q1");

            System.out.println(textField);

            // 最近周星驰比较火呀，我这里设置一下在搜索框内填入”周星驰“
            textField.setValueAttribute("周星驰");
            // 输入好了，我们点一下这个按钮
            final HtmlPage nextPage = button.click();
            // 我把结果转成String
            System.out.println(nextPage);

            String result = nextPage.asXml();

            System.out.println(result);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
