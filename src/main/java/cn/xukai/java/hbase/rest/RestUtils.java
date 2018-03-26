package cn.xukai.java.hbase.rest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 *
 * https://www.jianshu.com/p/0a9018c60805
 * Created by kaixu on 2018/3/26.
 */
public class RestUtils {

    /**
     * scan 扫描 (get请求)
     * @param tableName
     * @param startRowkey
     * @param endRowkey
     * @param params
     * @param xmlOrJson
     * @return
     */
    public static String scan(String tableName,String startRowkey,String endRowkey,String[] params,String xmlOrJson){
        String uriAPI = "http://hadoop-3:8081/" + tableName + "/*?startrow=" + startRowkey+"&endrow="+endRowkey;
        String result = "";
        HttpGet getR = new HttpGet(uriAPI);
        try {
            getR.setHeader("accept", xmlOrJson);
            HttpResponse httpResponse = new DefaultHttpClient().execute(getR);
            // 其中HttpGet是HttpUriRequst的子类
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode == 200 || statusCode == 403) {
                HttpEntity httpEntity = httpResponse.getEntity();
                result = EntityUtils.toString(httpEntity);// 取出应答字符串
                // 一般来说都要删除多余的字符
                // 去掉返回结果中的"\r"字符，否则会在结果字符串后面显示一个小方格
                // result.replaceAll("\r", "");
            } else {
                getR.abort();
                result = "没有返回正确的状态码，请仔细检查请求表名及参数格式！";
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            result = e.getMessage().toString();
        } catch (IOException e) {
            e.printStackTrace();
            result = e.getMessage().toString();
        }
        return result;
    }


    /**
     * 写一条数据
     * @param tableName
     * @param jsonStr
     * @return
     */
    public static String writeRowInTableByJson(String tableName, String jsonStr) {
        String uriAPI = "hadoop-3:8081/" + tableName + "/fakerow";
        StringBuilder result = new StringBuilder();
        HttpPut put = new HttpPut(uriAPI);
        try {
            put.addHeader("Accept", "application/json");
            put.addHeader("Content-Type", "application/json");
            // JSONObject jsonObject = JSONObject.fromObject(jsonStr);
            StringEntity input = null;
            try {
                input = new StringEntity(jsonStr);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            put.setEntity(input);
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpResponse httpResponse = httpClient.execute(put);
            int status = httpResponse.getStatusLine().getStatusCode();
            if ( status != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + httpResponse.getStatusLine().getStatusCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader((httpResponse.getEntity().getContent())));
            String output;
            while ((output = br.readLine()) != null) {
                result.append(output);
            }
            result.append("-code:"+status);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result.toString();
    }
    /**
     * http://example:8080/TableName/rowkey
     * 获取表中一条记录
     * @param tableName
     * @param rowkey
     * @param paramInfo
     * @param xmlOrJson
     * @return
     */
    public static String getRowKey(String tableName, String rowkey,String paramInfo, String xmlOrJson) {
        String uriAPI = "http://hadoop-3:8081/" + tableName + "/" + rowkey;
        System.out.println("uriAPI:"+uriAPI);
        String result = "";
        HttpGet getR = new HttpGet(uriAPI);
        try {
            getR.setHeader("accept", xmlOrJson);
            HttpResponse httpResponse = new DefaultHttpClient().execute(getR);
            // 其中HttpGet是HttpUriRequst的子类
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode == 200 || statusCode == 403) {
                HttpEntity httpEntity = httpResponse.getEntity();
                result = EntityUtils.toString(httpEntity);// 取出应答字符串
                // 一般来说都要删除多余的字符
                // 去掉返回结果中的"\r"字符，否则会在结果字符串后面显示一个小方格
                // result.replaceAll("\r", "");
            } else {
                getR.abort();
                result = "没有返回正确的状态码，请仔细检查请求表名及参数格式！";
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            result = e.getMessage().toString();
        } catch (IOException e) {
            e.printStackTrace();
            result = e.getMessage().toString();
        }
        return result;
    }
    /**
     * 获取hbase 中的表
     * @param acceptInfo
     * @return
     */
    public static String getTableList(String acceptInfo){
        String uriAPI = "http://hadoop-3:8081/";
        String result = "";
        HttpGet httpRequst = new HttpGet(uriAPI);
        try {
            httpRequst.setHeader("accept", acceptInfo);
            HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequst);
            // 其中HttpGet是HttpUriRequst的子类
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode == 200 || statusCode == 403) {
                HttpEntity httpEntity = httpResponse.getEntity();
                result = EntityUtils.toString(httpEntity);// 取出应答字符串
                // 一般来说都要删除多余的字符
                // 去掉返回结果中的"\r"字符，否则会在结果字符串后面显示一个小方格
                // result.replaceAll("\r", "");
            } else {
                httpRequst.abort();
                result = "异常的返回码:"+statusCode;
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            result = e.getMessage().toString();
        } catch (IOException e) {
            e.printStackTrace();
            result = e.getMessage().toString();
        }
        return result;
    }


    /**
     * 解码
     * @param str
     * @return
     */
    public static byte[] decode(String str){
        byte[] bt = null;
        try {
            sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
            bt = decoder.decodeBuffer( str );
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bt;
    }


    public static void main(String[] args) {
        String res = RestUtils.getRowKey("t2","r2","cf:a","application/json");
        System.out.println(res);
        JSONObject jsonObject = JSON.parseObject(res);
        JSONArray row = jsonObject.getJSONArray("Row");
        System.out.println(row);
        for(int i=0;i<row.size();i++){
            JSONObject jsonObject1 = row.getJSONObject(i);
            System.out.println("key: "+new String(decode(jsonObject1.getString("key"))));
            JSONArray cell = jsonObject1.getJSONArray("Cell");
            for(int j=0;j<cell.size();j++){
                JSONObject jo = cell.getJSONObject(j);
                System.out.println("column:"+new String(decode(jo.getString("column"))));
                System.out.println("value:"+new String(decode(jo.getString("$"))));
                System.out.println("timestamp:"+jo.getString("timestamp"));
            }
        }
    }

}
