package cn.xukai.java.crawler.webCollector;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;
import cn.edu.hfut.dmic.webcollector.plugin.nextfilter.HashSetNextFilter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Created by kaixu on 2017/9/12.
 */
public class WxAccountCrawler extends BreadthCrawler {
    public static final Logger LOG = LoggerFactory.getLogger(WxAccountCrawler.class);

    protected String historyKeysPath;
    protected BufferedWriter historyKeysWriter;

    public WxAccountCrawler(String crawlPath, String historyKeysPath) throws Exception {
        super(crawlPath, false);
        this.historyKeysPath = historyKeysPath;
        LOG.info("initializing history-keys-filter ......");
        this.setNextFilter(new HistoryKeysFilter(historyKeysPath));
        LOG.info("creating history-keys-writer");
        historyKeysWriter = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(historyKeysPath, true), "utf-8"));

    }

    public void visit(Page page, CrawlDatums next) {
        String account = page.meta("account");

        if (page.matchType("account_search")) {
            //对于账号搜索页面

            //抽取公众号文章列表页URL
            Element accountLinkEle = page.select("p.tit>a").first();
            //防止搜索结果为空
            if (accountLinkEle == null) {
                LOG.info("公众号\"" + account + "\"不存在，请给出准确的公众号名");
                return;
            }
            //防止公众号名错误
//            String detectedAccount = accountLinkEle.text().trim();
//            if (!account.equals(detectedAccount)) {
//                LOG.info("公众号\"" + account + "\"与搜索结果\"" + detectedAccount + "\"名称不符，请给出准确的公众号名");
//                return;
//            }
            String accountUrl = accountLinkEle.attr("abs:href");
            next.add(new CrawlDatum(accountUrl, "article_list").meta("account", account));

        } else if (page.matchType("article_list")) {
            //对于公众号文章列表页

            String prefix = "msgList = ";
            String suffix = "seajs.use";
            int startIndex = page.html().indexOf(prefix) + prefix.length();
            int endIndex = page.html().indexOf(suffix);
            String jsonStr = page.html().substring(startIndex, endIndex).trim();
            if(jsonStr.endsWith(";")){
                jsonStr = jsonStr.substring(0,jsonStr.lastIndexOf(";"));
            }
            JSONObject json = JSON.parseObject(jsonStr);
            JSONArray articleJSONArray = json.getJSONArray("list");
            for (int i = 0; i < articleJSONArray.size(); i++) {
                JSONObject articleJSON = articleJSONArray.getJSONObject(i).getJSONObject("app_msg_ext_info");
                int isMulti = articleJSON.getIntValue("is_multi");
                if(isMulti==1){
                    JSONArray multiAppMsgItemList = articleJSON.getJSONArray("multi_app_msg_item_list");
                    String [] multiAppMsgItemStrs = getJsonToStringArray(multiAppMsgItemList);
                    for(String multiAppMsgItemStr :multiAppMsgItemStrs){
                        JSONObject jsonObject = JSON.parseObject(multiAppMsgItemStr);
                        String title = jsonObject.getString("title").trim();
                        String key = account + "_" + title;
                        String contentUrl = jsonObject.getString("content_url").replace("&amp;", "&");
                        String articleUrl = "http://mp.weixin.qq.com" + contentUrl;
                        next.add(new CrawlDatum(articleUrl, "article").key(key).meta("account", account));
                    }
                }
                String title = articleJSON.getString("title").trim();
                String key = account + "_" + title;
                String contentUrl = articleJSON.getString("content_url").replace("&amp;", "&");
                String articleUrl = "http://mp.weixin.qq.com" + contentUrl;
                System.out.println("访问URL："+articleUrl);
                next.add(new CrawlDatum(articleUrl, "article").key(key).meta("account", account));
            }

        } else if (page.matchType("article")) {
            //对于文章页
            //抽取标题、内容等信息，此处仅print少数信息作为参考
            String title = page.select("h2.rich_media_title").first().text().trim();
            String date = page.select("em#post-date").first().text().trim();
            String content = page.select("div.rich_media_content").first().text().trim();

            try {
                writeHistoryKey(page.key());
                JSONObject articleJSON = new JSONObject();
                articleJSON.put("account", account);
                articleJSON.put("title", title);
                articleJSON.put("date", date);
                articleJSON.put("content", content);
                System.out.println(articleJSON);
            } catch (IOException ex) {
                LOG.info("writer exception", ex);
            }

        }
    }

    public synchronized void writeHistoryKey(String key) throws IOException {
        historyKeysWriter.write(key + "\n");
    }
    private String[] getJsonToStringArray(JSONArray jsonArray) {
        String[] arr = new String[jsonArray.size()];
        for (int i = 0; i < jsonArray.size(); i++) {
            arr[i] = jsonArray.getString(i);
        }
        return arr;
    }

    @Override
    public void start(int depth) throws Exception {
        super.start(depth);
        //关闭文件，保存history keys
        historyKeysWriter.close();
        LOG.info("save history keys");
    }

    public void addAccount(String account) throws UnsupportedEncodingException {
        String seedUrl = "http://weixin.sogou.com/weixin?type=1&"
                + "s_from=input&ie=utf8&query=" + URLEncoder.encode(account, "utf-8");
        CrawlDatum seed = new CrawlDatum(seedUrl, "account_search").meta("account", account);
        addSeed(seed);
    }

    //该示例读取文件中的key进行文章去重
    //线上应用请老老实实用数据库
    public class HistoryKeysFilter extends HashSetNextFilter {

        //读取历史文章标题，用于去重
        public HistoryKeysFilter(String historyKeysPath) throws Exception {
            File historyFile = new File(historyKeysPath);
            if (historyFile.exists()) {
                FileInputStream fis = new FileInputStream(historyKeysPath);
                BufferedReader reader = new BufferedReader(new InputStreamReader(fis, "utf-8"));
                String line;
                while ((line = reader.readLine()) != null) {
                    this.add(line);
                }
                reader.close();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        WxAccountCrawler crawler = new WxAccountCrawler("crawl_weixin", "wx_history.txt");
        crawler.addAccount("xiaomigongsi0406");
        //        crawler.addAccount("ZEALER订阅号");
        crawler.setThreads(5);
        crawler.start(20);
    }
}
