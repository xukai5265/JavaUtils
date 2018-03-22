package cn.xukai.java.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kaixu on 2017/7/12.
 */
public class Demo {
    public static void main(String[] args) {
        /*
        使用 assert（断言） 需要开启断言
        在 vm option 添加 -ea
         */
//        String str = "abcd";
//        assert (str.equals("abc"));
//        System.out.println("?");
        String json = "{\"id\":\"character varying\",\"apply_id\":\"character varying\",\"month_id\":\"character varying\",\"payback_type\":\"character varying\",\"create_time\":\"timestamp without time zone\",\"modify_time\":\"timestamp without time zone\",\"create_by\":\"character varying\",\"modify_by\":\"character varying\",\"order_num\":\"integer\",\"trade_amount\":\"numeric\",\"left_payback_amount\":\"numeric\"}";
//        JSONObject jb = JSONObject.parseObject(json);   无序
        JSONObject jb = JSONObject.parseObject(json,Feature.OrderedField); //有序
        System.out.println(jb.toJSONString());
//        JSON.parse(json, Feature.OrderedField);

    }

    @Test
    public void test1(){
        List<Person> personList = new ArrayList<>();
        Person person1 = new Person();
        person1.setName("刘德华");
        personList.add(person1);


        personList.remove(person1);
    }

    /**
     * 读取一个properties文件从给定的路径
     */
    @Test
    public void readProperties(){

    }
}
