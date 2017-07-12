package cn.xukai.java.hive.udf;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.io.Text;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;
import java.util.ArrayList;

/**
 * Created by kaixu on 2017/7/10.
 *
 * create temporary function getJsonArray as 'cn.xukai.java.hive.udf.GetJsonArray';
 */
public class GetJsonArray extends UDF {
    /**
     * 解析json并返回对应子json字符串数组,例如
     add jar jar/bdp_udf_demo-1.0.0.jar;
     create temporary function getJsonArray as 'com.jd.bdp.util.udf.GetJsonArray';
     select getJsonArray(json字符串)
     * @param jsonArrayStr
     * @return
     * @throws HiveException
     */
    public ArrayList<Text> evaluate(String jsonArrayStr) throws JSONException {
        if(StringUtils.isBlank(jsonArrayStr)||StringUtils.isBlank(jsonArrayStr)){
            return null;
        }
        ArrayList<Text> textList = new ArrayList<Text>();
        if(!jsonArrayStr.trim().startsWith("[")){
            textList.add(new Text(jsonArrayStr));
        }else{
            JSONArray jsonArray = new JSONArray(new JSONTokener(jsonArrayStr));
            Text[] jsonTexts = new Text[jsonArray.length()];
            for(int i=0;i<jsonArray.length();i++){
                String json = jsonArray.getJSONObject(i).toString();
                textList.add(new Text(json));
            }
        }
        return textList;
    }
}
