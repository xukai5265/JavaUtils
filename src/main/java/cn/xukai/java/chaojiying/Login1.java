package cn.xukai.java.chaojiying;

import cn.xukai.java.utils.WebCrawler;
import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.NameValuePair;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.module.ocr.utils.AbstractChaoJiYingHandler;

import java.io.File;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by kaixu on 2017/11/2.
 *
 */
public class Login1 extends AbstractChaoJiYingHandler {
    private static final String LEN_MIN = "0";
    private static final String TIME_ADD = "0";
    private static final String STR_DEBUG = "a";

    @Override
    public String getVerifycode(File file) throws Exception {
        // TODO Auto-generated method stub
        return super.getVerifycodeByChaoJiYing("5000", LEN_MIN, TIME_ADD, STR_DEBUG, file.getAbsolutePath()); //6003 澶嶆潅璁＄畻棰?
    }

    public static void main(String[] args)throws Exception{
        test3();
//        local();
//        server();
    }

    public static void local()throws Exception{
        long startWC = System.currentTimeMillis();
        WebClient webClient = WebCrawler.getInstance().getNewWebClient();
        long endWC = System.currentTimeMillis();
        System.out.println("初始化WC用时:" + (endWC-startWC));

//        String url = "http://10.167.170.23:44444/login";
//        WebRequest webRequest = new WebRequest(new URL(url),HttpMethod.GET);
//        HtmlPage searchPage = webClient.getPage(webRequest);
        WebRequest webRequest1 = new WebRequest(new URL("http://10.167.170.23:44444/login"), HttpMethod.POST);
        webRequest1.setAdditionalHeader("Content-Type","application/x-www-form-urlencoded");
        webRequest1.setAdditionalHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        webRequest1.setAdditionalHeader("Accept-Encoding","gzip, deflate, br");
        webRequest1.setAdditionalHeader("Accept-Language","zh-CN,zh;q=0.8");
        webRequest1.setAdditionalHeader("Cache-Control","max-age=0");

        webRequest1.setRequestBody("username=xiongda&password=111111");
        HtmlPage login = webClient.getPage(webRequest1);
        System.out.println("登陆成功!!!");




        String loginAction = "http://10.167.170.23:44444/chartOne";
        WebRequest webRequest2 = new WebRequest(new URL(loginAction),HttpMethod.GET);
        HtmlPage loginActionPage = webClient.getPage(webRequest2);

        System.out.println("login action ..."+loginActionPage.asXml());


//        WebRequest  createHiveTable = new WebRequest(new URL("http://localhost:8080/metadata/generate/hive/table"), HttpMethod.POST);
//
//        createHiveTable.setRequestParameters(new ArrayList<NameValuePair>());
//        createHiveTable.getRequestParameters().add(new NameValuePair("id", "1"));
//        createHiveTable.getRequestParameters().add(new NameValuePair("dsId", "3"));
//        createHiveTable.getRequestParameters().add(new NameValuePair("businessSystem", "chp"));
//        createHiveTable.getRequestParameters().add(new NameValuePair("model", "jk"));
//        createHiveTable.getRequestParameters().add(new NameValuePair("hiveDb", "srdb 数据源"));
//
//
//        createHiveTable.setAdditionalHeader("Host", "localhost:8080");
//        createHiveTable.setAdditionalHeader("Origin", "http://localhost:8080");
//        createHiveTable.setAdditionalHeader("Referer", "http://localhost:8080/metadata/list");
//
//
//        createHiveTable.setCharset(Charset.forName("UTF-8"));
//
//
//        HtmlPage hivepage = webClient.getPage(createHiveTable);
//
//
//        System.out.println(hivepage.asXml());
    }


    public static void server()throws Exception{
        long startWC = System.currentTimeMillis();
        WebClient webClient = WebCrawler.getInstance().getNewWebClient();
        long endWC = System.currentTimeMillis();
        System.out.println("初始化WC用时:" + (endWC-startWC));

        String url = "http://10.167.222.103:8080/open/showLogin";
        WebRequest webRequest = new WebRequest(new URL(url),HttpMethod.GET);
        HtmlPage searchPage = webClient.getPage(webRequest);
        HtmlImage image = (HtmlImage) searchPage.querySelector("#kaptchaImage");
        String imageName ="D:\\img\\"+ UUID.randomUUID() + ".jpg";
        System.out.println("imageName-------"+imageName);
        File file = new File(imageName);
        image.saveAs(file);
        String chaoJiYingResult = getVerifycodeByChaoJiYing("1902", imageName);
        Gson gson = new GsonBuilder().create();
        String code = (String) gson.fromJson(chaoJiYingResult, Map.class).get("pic_str");
        System.out.println("code---------"+code);

        String password = "1";
        System.out.println("password---------"+password);

        WebRequest  requestSettings = new WebRequest(new URL("http://10.167.222.103:8080/open/login"), HttpMethod.POST);

        requestSettings.setRequestParameters(new ArrayList<NameValuePair>());
        requestSettings.getRequestParameters().add(new NameValuePair("username", "sysadmin"));
        requestSettings.getRequestParameters().add(new NameValuePair("password", password));
        requestSettings.getRequestParameters().add(new NameValuePair("authCode", code));


        requestSettings.setAdditionalHeader("Host", "10.167.222.103:8080");
        requestSettings.setAdditionalHeader("Origin", "http://10.167.222.103:8080");
        requestSettings.setAdditionalHeader("Referer", "http://10.167.222.103:8080/open/showLogin");
        requestSettings.setAdditionalHeader("Upgrade-Insecure-Requests", "1");

        requestSettings.setCharset(Charset.forName("UTF-8"));

        Page page = webClient.getPage(requestSettings);

        String html = page.getWebResponse().getContentAsString();

        System.out.println("html--------"+html);



        String loginAction = "http://10.167.222.103:8080/datasource/list";
        WebRequest webRequest2 = new WebRequest(new URL(loginAction),HttpMethod.GET);
        HtmlPage loginActionPage = webClient.getPage(webRequest2);

        System.out.println("login action ..."+loginActionPage.asXml());


        WebRequest  createHiveTable = new WebRequest(new URL("http://10.167.222.103:8080/metadata/generate/hive/table"), HttpMethod.POST);

        createHiveTable.setRequestParameters(new ArrayList<NameValuePair>());
        createHiveTable.getRequestParameters().add(new NameValuePair("id", "1"));
        createHiveTable.getRequestParameters().add(new NameValuePair("dsId", "3"));
        createHiveTable.getRequestParameters().add(new NameValuePair("businessSystem", "chp"));
        createHiveTable.getRequestParameters().add(new NameValuePair("model", "jk"));
        createHiveTable.getRequestParameters().add(new NameValuePair("hiveDb", "srdb 数据源"));


        createHiveTable.setAdditionalHeader("Host", "10.167.222.103:8080");
        createHiveTable.setAdditionalHeader("Origin", "http://10.167.222.103:8080");
        createHiveTable.setAdditionalHeader("Referer", "http://10.167.222.103:8080/metadata/list");


        createHiveTable.setCharset(Charset.forName("UTF-8"));


        HtmlPage hivepage = webClient.getPage(createHiveTable);

        System.out.println(hivepage.asXml());
    }



    public static void test3() throws Exception{
        WebClient webClient = WebCrawler.getInstance().getNewWebClient();
        WebRequest webRequest1 = new WebRequest(new URL("http://10.167.170.23:44444/login"), HttpMethod.POST);
        webRequest1.setAdditionalHeader("Content-Type","application/x-www-form-urlencoded");
        webRequest1.setAdditionalHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        webRequest1.setAdditionalHeader("Accept-Encoding","gzip, deflate, br");
        webRequest1.setAdditionalHeader("Accept-Language","zh-CN,zh;q=0.8");
        webRequest1.setAdditionalHeader("Cache-Control","max-age=0");

        webRequest1.setRequestBody("username=xiongda&password=111111");
        HtmlPage login = webClient.getPage(webRequest1);
        System.out.println("登陆成功!!!");
        WebRequest webRequest = new WebRequest(new URL("http://10.167.170.23:44444/chartOne"), HttpMethod.GET);
//添加消息头
//        webRequest.setAdditionalHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
//        webRequest.setAdditionalHeader("Content-Type", "text/html;charset=UTF-8");
//        webRequest.setAdditionalHeader("Accept-Encoding", "gzip, deflate, br");
//        webRequest.setAdditionalHeader("Accept-Language", "zh-CN,zh;q=0.8");


//        webClient.getOptions().setJavaScriptEnabled(true);
//        webClient.getOptions().setCssEnabled(true);
        HtmlPage htmlPage = webClient.getPage(webRequest);
        webClient.waitForBackgroundJavaScript(10000);
        System.out.println(htmlPage.asXml());
        System.out.println(htmlPage.querySelector("#chart_1>div>svg"));
    }
}
