package cn.xukai.java.chaojiying;

import cn.xukai.java.utils.WebCrawler;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
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
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;

public class ZhengzhouTest extends AbstractChaoJiYingHandler{
	
	private static final String LEN_MIN = "0";
	private static final String TIME_ADD = "0";
	private static final String STR_DEBUG = "a";
	
	@Override
	public String getVerifycode(File file) throws Exception {
		// TODO Auto-generated method stub
		return super.getVerifycodeByChaoJiYing("5000", LEN_MIN, TIME_ADD, STR_DEBUG, file.getAbsolutePath()); //6003 复杂计算题
	}

	public static void main(String[] args) throws Exception {

		org.apache.commons.logging.LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
		java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF);
		java.util.logging.Logger.getLogger("org.apache.commons.httpclient").setLevel(Level.OFF);
		
/*		
		long startWC = System.currentTimeMillis(); 
		WebClient webClient = WebCrawler.getInstance().getNewWebClient();
		long endWC = System.currentTimeMillis(); 
		System.out.println("初始化WC用时:" + (endWC-startWC));
		String url = "http://218.28.166.74:8080/zzsbonline/login.jsp";
		WebRequest webRequest = new WebRequest(new URL(url),HttpMethod.GET);
		HtmlPage searchPage = webClient.getPage(webRequest); 
		HtmlTextInput username = (HtmlTextInput) searchPage.getFirstByXPath("//input[@id='txtCardNo']");
		HtmlPasswordInput passwordInput = (HtmlPasswordInput) searchPage.getFirstByXPath("//input[@id='txtPasswrd']");
		HtmlElement button = (HtmlElement) searchPage.querySelector("#btnSubmit");
		username.setText("410923198502023023");
		passwordInput.setText("HANhan123"); 
		
		HtmlImage image = (HtmlImage) searchPage.querySelector("#codeImg");
		String imageName ="D:\\img\\"+ UUID.randomUUID() + ".jpg";
		System.out.println("imageName-------"+imageName);
		File file = new File(imageName);
		image.saveAs(file);  
		String chaoJiYingResult = getVerifycodeByChaoJiYing("1902", imageName); 
		
		System.out.println("chaoJiYingResult--------"+chaoJiYingResult);
		HtmlTextInput checkCodeYan = (HtmlTextInput) searchPage.getFirstByXPath("//input[@id='checkCodeYan']");
		checkCodeYan.setText(chaoJiYingResult);  
		HtmlPage htmlpage = button.click();   
		Thread.sleep(3000L); 
		System.out.println(htmlpage.asXml());
 */ 
		
		long startWC = System.currentTimeMillis(); 
		WebClient webClient = WebCrawler.getInstance().getNewWebClient();
		long endWC = System.currentTimeMillis(); 
		System.out.println("初始化WC用时:" + (endWC-startWC)); 
		
		String url = "http://218.28.166.74:8080/zzsbonline/login.jsp";
		WebRequest webRequest = new WebRequest(new URL(url),HttpMethod.GET);
		HtmlPage searchPage = webClient.getPage(webRequest); 
		HtmlImage image = (HtmlImage) searchPage.querySelector("#codeImg");
		String imageName ="D:\\img\\"+ UUID.randomUUID() + ".jpg";
		System.out.println("imageName-------"+imageName);
		File file = new File(imageName);
		image.saveAs(file);  
		String chaoJiYingResult = getVerifycodeByChaoJiYing("1902", imageName); 
		Gson gson = new GsonBuilder().create();
		String code = (String) gson.fromJson(chaoJiYingResult, Map.class).get("pic_str");
		System.out.println("code---------"+code);
		
		String password = MD5Util.getMD5("HANhan123").toLowerCase();
		System.out.println("password---------"+password);
		
		WebRequest  requestSettings = new WebRequest(new URL("http://218.28.166.74:8080/zzsbonline/usersAction!userLogin"), HttpMethod.POST); 
		
		requestSettings.setRequestParameters(new ArrayList<NameValuePair>());
		requestSettings.getRequestParameters().add(new NameValuePair("cardid", "410923198502023023"));
		requestSettings.getRequestParameters().add(new NameValuePair("password", password));
		requestSettings.getRequestParameters().add(new NameValuePair("vcode", code));
		
		
		requestSettings.setAdditionalHeader("Host", "218.28.166.74:8080");
		requestSettings.setAdditionalHeader("Origin", "http://218.28.166.74:8080");
		requestSettings.setAdditionalHeader("Referer", "http://218.28.166.74:8080/zzsbonline/login.jsp");
		requestSettings.setAdditionalHeader("X-Requested-With", "XMLHttpRequest"); 
		
		requestSettings.setCharset(Charset.forName("UTF-8"));
		
		Page page = webClient.getPage(requestSettings); 
		
		String html = page.getWebResponse().getContentAsString();
		
		System.out.println("html--------"+html);
		
		
		
		String loginAction = "http://218.28.166.74:8080/zzsbonline/loginAction";
		WebRequest webRequest2 = new WebRequest(new URL(loginAction),HttpMethod.GET);
		HtmlPage loginActionPage = webClient.getPage(webRequest2); 
		
		System.out.println(loginActionPage.asXml());
	}
	
 
}


