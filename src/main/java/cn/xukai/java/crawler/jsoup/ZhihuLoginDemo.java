package cn.xukai.java.crawler.jsoup;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


/**
 * 获取得到知乎的登录页面:
 *
 */
public class ZhihuLoginDemo {
    final static Logger log = LoggerFactory.getLogger(ZhihuLoginDemo.class);

    public static void main(String[] args) throws IOException {
        //创建HttpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //请求的目标网址
        String rr_url = "https://www.zhihu.com";

        HttpPost httpPost = new HttpPost(rr_url);

        //以Post方式请求，设置登录用户名和密码
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("account", "13231423298"));   //自己用户名
        nameValuePairs.add(new BasicNameValuePair("password", "xukai1989"));//自己密码

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);

            System.out.println(httpResponse);

            //获取请求头
            Header header = httpResponse.getFirstHeader("Location");
            if(header != null){

                //以Get方法请求得到重定向的URL
                HttpGet httpGet = new HttpGet(header.getValue());

                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                String res = httpClient.execute(httpGet, responseHandler);

                System.out.println(res);
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取登录页面
     * @param url 目标网址 https://www.zhihu.com
     * @return
     * @throws IOException
     */
    public static String getLoginPage(String url) throws IOException {
        //创建HttpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //请求方法
        HttpGet httpGet = new HttpGet(url);

        //发送请求，获得响应
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

        //判断响应码
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if(statusCode == 200){
            //获取网页实例
            String entity = EntityUtils.toString(httpResponse.getEntity());

            //Jsoup解析网页
            Document document = Jsoup.parse(entity);

            log.info(document.toString());
            return document.toString();
        }
        return null;
    }
}
