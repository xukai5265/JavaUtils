package cn.xukai.java.hive.udf;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
/**
 * Created by kaixu on 2017/7/10.
 * create temporary function getJsonObject as 'cn.xukai.java.hive.udf.GetJsonObject';
 */
public class GetJsonObject extends UDF {
    /**
     * 解析json并返回对应的值。例如
     add jar jar/bdp_udf_demo-1.0.0.jar;
     create temporary function getJsonObject as 'com.jd.bdp.util.udf.GetJsonObject';
     select getJsonObject(json字符串,key值)
     * @param jsonStr
     * @param objName
     * @return
     */
    public String evaluate(String jsonStr,String objName) throws JSONException {
        if(StringUtils.isBlank(jsonStr)|| StringUtils.isBlank(objName)){
            return null;
        }
        JSONObject jsonObject = new JSONObject(new JSONTokener(jsonStr));
        Object objValue = jsonObject.get(objName);
        if(objValue==null){
            return null;
        }
        return objValue.toString();
    }
}
