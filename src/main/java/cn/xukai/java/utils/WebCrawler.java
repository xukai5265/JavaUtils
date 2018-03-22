package cn.xukai.java.utils;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.util.Cookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebCrawler {

	private static final Logger log = LoggerFactory.getLogger(WebCrawler.class);

	//private static WebClient webClient = null;

	private static WebCrawler instance;

	private static String alertMsg = "";

	public static final int DEFAULT_PAGE_TIME_OUT = 20000; // ms
	public static final int DEFAULT_JS_TIME_OUT = 50000;


	public synchronized static String getAlertMsg() {

		String msg = alertMsg;
		alertMsg = "";
		return msg;

	}

	public WebCrawler() {
		/*
		if (webClient == null) {
			webClient = getWebClient();
		}*/
	}

	public static WebCrawler getInstance() {
		if (instance == null) {
			synchronized (WebCrawler.class) {
				if (instance == null) {
					instance = new WebCrawler();
				}
			}
		}
		return instance;
	}

	public WebClient getWebClient() {
		//if (webClient == null) {
			WebClient webClient = new WebClient(BrowserVersion.CHROME);
			webClient.setRefreshHandler(new ThreadedRefreshHandler());
			webClient.getOptions().setCssEnabled(false);
			webClient.getOptions().setJavaScriptEnabled(true);
			webClient.getOptions().setThrowExceptionOnScriptError(false);
			webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
			webClient.getOptions().setPrintContentOnFailingStatusCode(false);
			webClient.getOptions().setRedirectEnabled(true);
			webClient.getOptions().setTimeout(DEFAULT_PAGE_TIME_OUT); // 15->60
			webClient.waitForBackgroundJavaScript(DEFAULT_JS_TIME_OUT); // 5s
			webClient.setAjaxController(new NicelyResynchronizingAjaxController());
			webClient.getOptions().setUseInsecureSSL(true); //
		return webClient;
	}
	

	public WebClient getNewWebClient() {
		//webClient = null; 
		//if (webClient == null) {
			WebClient webClient = new WebClient(BrowserVersion.CHROME);
			webClient.setRefreshHandler(new ThreadedRefreshHandler());
			webClient.getOptions().setCssEnabled(false);
			webClient.getOptions().setJavaScriptEnabled(true);
			webClient.getOptions().setThrowExceptionOnScriptError(false);
			webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
			webClient.getOptions().setPrintContentOnFailingStatusCode(false);
			webClient.getOptions().setRedirectEnabled(true);
			webClient.getOptions().setTimeout(DEFAULT_PAGE_TIME_OUT); // 15->60
			webClient.waitForBackgroundJavaScript(DEFAULT_JS_TIME_OUT); // 5s
			try{
				webClient.setAjaxController(new NicelyResynchronizingAjaxController());
			}catch(Exception e){
				log.info("setAjaxController exception:"+e.toString());
			}
			
			webClient.getOptions().setUseInsecureSSL(true); //
			webClient.getCookieManager().setCookiesEnabled(true);//开启cookie管理

			webClient.setAlertHandler(new AlertHandler() {
				@Override
				public void handleAlert(Page page, String message) {
					alertMsg = message;
				}
			});

		return webClient;
	}




	public WebClient insertCookie(WebClient webClient, List<Cookie> cookies) {
		for (Cookie e : cookies) {
			webClient.getCookieManager().addCookie(e);
		}
		return webClient;
	}



	private Map<String, String> SetMap(String company, String code) {
		Map<String, String> canshu = new HashMap<String, String>();
		canshu.put("vct", code);
		canshu.put("Submit", "继续");
		canshu.put("Submit", "继续");
		canshu.put("numIg", "");
		canshu.put("numDgc", "");
		canshu.put("numIgc", "");
		canshu.put("numIp", "0");
		canshu.put("pageSize", "3");
		canshu.put("strWhere", "PA='%" + company + "%'");
		canshu.put("numDg", "");
		canshu.put("numIpc", "");
		canshu.put("numUgc", "");
		canshu.put("numUgd", "");
		canshu.put("strLicenseCode", "");
		canshu.put("numIgd", "");
		canshu.put("showType", "1");
		canshu.put("numSortMethod", "");
		canshu.put("strSources", "");
		canshu.put("numUg", "");
		canshu.put("pageNow", "1");

		return canshu;
	}

}
