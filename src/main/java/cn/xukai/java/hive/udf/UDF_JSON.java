package cn.xukai.java.hive.udf;

import com.alibaba.fastjson.JSON;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kaixu on 2017/6/14.
 */
public class UDF_JSON extends GenericUDF {
    ObjectInspector[] objIn;
    ObjectInspector objOut;
    Map<String, String> map = new HashMap();

    public Object evaluate(GenericUDF.DeferredObject[] args) throws HiveException
    {
        if (args.length < 1) return null;
        this.map.clear();

        String[] strs = new String[2];
        for (int i = 0; i < args.length; i++) {

            String str = args[i].get().toString();
            strs[0] = str.substring(0, str.indexOf(":"));
            strs[1] = str.substring(str.indexOf(":") + 1);
            this.map.put(strs[0], strs[1]);
        }
        String result = JSON.toJSONString(this.map);

        return result;
    }

    public String getDisplayString(String[] arg0)
    {
        return null;
    }

    public ObjectInspector initialize(ObjectInspector[] args)
            throws UDFArgumentException
    {
        this.objIn = args;

        return
                PrimitiveObjectInspectorFactory.getPrimitiveJavaObjectInspector(PrimitiveObjectInspector.PrimitiveCategory.STRING);
    }

    public static boolean isGoodJson(String json) {

        try {
            new JsonParser().parse(json);
            return true;
        } catch (JsonParseException e) {
            System.out.println("bad json: " + json);
            return false;
        }
    }
}
