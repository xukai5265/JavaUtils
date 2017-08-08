package cn.xukai.java.hive.udf;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentLengthException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorConverters;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.io.Text;

import java.util.ArrayList;

/**
 * Created by kaixu on 2017/7/10.
 * 用法：
 * step1. create temporary function json_array as 'cn.xukai.java.hive.udf.GenericUDFJSONArray';
 * step2.
 *    {
         "initFlag": "0",
         "reportJason": [
         {
         "checkBox": "01",
         "sysChooseFlag": "0",
         "userChooseFlag": "0"
         },
         {
         "checkBox": "02",
         "sysChooseFlag": "0",
         "userChooseFlag": "0"
         },
         {
         "checkBox": "03",
         "sysChooseFlag": "0",
         "userChooseFlag": "0"
         }
         ]
      }
    目的将上面json 解析成如下格式
     -------------------------------------------------------------------------------------------------——
     选择表
     loan_code、r_id、initFlag、checkBox、sysChooseFlag、userChooseFlag
     0001       001    0          01            0            0
     0002       002    0          02            0            0
     0003       003    0          03            0            0
     -------------------------------------------------------------------------------------------------——
    step 3
         create temporary table tmp_credit_risk as
         select
             loan_code,
             r_id,
             getJsonObject(regexp_replace(credit_json,'/com.creditharmony.approve.common.entity.CreditJson',''),'initFlag') initFlag,
             t1.checkBox,
             t1.sysChooseFlag,
             t1.userChooseFlag
             from ods.t_jk_credit_risk
             lateral view explode(json_array(
                case when instr(credit_json,'reportJason') =='0' then '' else getJsonObject(credit_json,'reportJason') end
             )
             ) t as item
         lateral view json_tuple(item,'checkBox','sysChooseFlag','userChooseFlag') t1 as checkBox,sysChooseFlag,userChooseFlag;
 */
public class GenericUDFJSONArray extends GenericUDF {
    private transient ObjectInspectorConverters.Converter converter;
    /*
       来处理该UDF的输入参数的类型信息

       Hive 中operator处理的数据都是Object加上一个ObjectInspector对象，我们可以非常方便的通过该
       ObjectInspector对象了解到上游传过来的值是什么，如果是Struct对象，可以进一步了解到有多少了
       Filed，进而获取每个Filed的值，而且通过Serde可以方便的完成数据的序列化操作。
     */
    @Override
    public ObjectInspector initialize(ObjectInspector[] objectInspectors) throws UDFArgumentException {
        if (objectInspectors.length!=1)
            throw new UDFArgumentLengthException("the function json_array(jsonArrayStr) takes exactly 1 arguments");
        converter = ObjectInspectorConverters.getConverter(objectInspectors[0], PrimitiveObjectInspectorFactory.writableStringObjectInspector);

        return ObjectInspectorFactory.getStandardListObjectInspector(PrimitiveObjectInspectorFactory.writableStringObjectInspector);
    }

    @Override
    public Object evaluate(DeferredObject[] deferredObjects) throws HiveException {
        assert (deferredObjects.length == 1);
        if(deferredObjects[0].get()==null)
            return null;
        String jsonArrayStr = ((Text)converter.convert(deferredObjects[0].get())).toString();
        if(StringUtils.isNotEmpty(jsonArrayStr)){
            try{
                JsonParser jsonParser = new JsonParser();
                JsonElement jsonElement = jsonParser.parse(jsonArrayStr);
                if (jsonElement.isJsonArray()){
                    JsonArray jsonArray = jsonElement.getAsJsonArray();
                    ArrayList<Text> result = new ArrayList<>();
                    for(JsonElement json:jsonArray){
                        if(json.isJsonArray() || json.isJsonObject()){
                            result.add(new Text(json.toString()));
                        }else if(json.isJsonPrimitive()){
                            result.add(new Text(json.getAsString()));
                        }else if(json.isJsonNull()) {

                        }
                    }
                    return result;
                }
            }catch (Exception e){

            }
        }
        return null;
    }

    @Override
    public String getDisplayString(String[] strings) {
        return null;
    }
}
