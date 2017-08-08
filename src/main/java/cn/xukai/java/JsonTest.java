package cn.xukai.java;

import cn.xukai.java.json.bean.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kaixu on 2017/6/13.
 */

public class JsonTest {



    public static void main(String[] args) {
        JsonTest.test01();

//        JsonTest.test02();
//        JsonTest.test03();
//        JsonTest.test04();
//        System.out.println(isGoodJson("aaaa"));
    }



    //{"address":[{"province":"hb","city":"cd","county":"lp"},{"province":"hb","city":"cd","county":"kc"}],"name":"xukai","id":"1"}
    public static void test04(){
        Address add1 = new Address();
        add1.setProvince("hb");
        add1.setCity("cd");
        add1.setCounty("lp");

        Address add2 = new Address();
        add2.setProvince("hb");
        add2.setCity("cd");
        add2.setCounty("kc");

        List<Address> addresses = new ArrayList<>();
        addresses.add(add1);
        addresses.add(add2);


        Student stu = new Student();
        stu.setId("1");
        stu.setName("xukai");
        stu.setAddress(addresses);
        JSONObject json = (JSONObject) JSONObject.toJSON(stu);
        System.out.println(json.toJSONString());


    }

    public static void test03(){
        Map<String, String> map = new HashMap();
        map.put("id","1");
        map.put("name","xukai");
        map.put("address:","[{\"province\":\"hebei\",\"city\":\"chengde\",\"county\":\"luanping\"},{\"province\":\"beijing\",\"city\":\"cy\",\"county\":\"dyc\"}]");

        String result = JSON.toJSONString(map);

        System.out.println(result);

    }

    public static void test02(){
        //[{\"province\":\"hebei\",\"city\":\"chengde\",\"county\":\"luanping\"},{\"province\":\"beijing\",\"city\":\"cy\",\"county\":\"dyc\"}]
        //[{"province":"hebei","city":"chengde","county":"luanping"},{"province":"beijing","city":"cy","county":"dyc"}]
//        String json = "[{\"province\":\"hebei\",\"city\":\"chengde\",\"county\":\"luanping\"},{\"province\":\"beijing\",\"city\":\"cy\",\"county\":\"dyc\"}]";
        String json = "[{\\\"province\\\":\\\"hebei\\\",\\\"city\\\":\\\"chengde\\\",\\\"county\\\":\\\"luanping\\\"},{\\\"province\\\":\\\"beijing\\\",\\\"city\\\":\\\"cy\\\",\\\"county\\\":\\\"dyc\\\"}]";
        JSONObject jsonObject = new JSONObject();
        List<Address> jsonArray = JSON.parseArray(json, Address.class);
        System.out.println(jsonArray.toString());
    }
    public static void test01(){
        CrifFrontInBean inBean = new CrifFrontInBean();
        inBean.setAppNo("12345");
        inBean.setAppDate("2016-09-01");
        inBean.setAppAmt("40000");
        inBean.setProductType("A003");
        inBean.setAppGuarantorFlg("N");
        inBean.setAppGuarantorFigure("Y");
        inBean.setAppEstateProvideFlg("Y");
        List<CrifFrontPersonalInBean> guaranteeInfos = new ArrayList<CrifFrontPersonalInBean>();
        CrifFrontPersonalInBean item = new CrifFrontPersonalInBean();
        item.setRole("S");
        item.setNationality("中国");
        item.setReturnFlg("N");
        item.setAge("26");
        item.setIdNum("320803198802213838 ");
        item.setNumOfLoanIndustry("0");
        item.setHouseProvince("23000");
        item.setHouseCity("230800");
        item.setProvince("110000");
        item.setCity("110100");
        item.setOverdue30CountFlg("N");
        item.setOverdue6TimesL6("0");
        item.setRelateOverdue30CountFlg("N");
        item.setRelateOverdue6TimesL6("0");
        item.setLastRejectDate("2016-01-13");
        item.setUnsetteledLoanC("N");
        item.setUnsetteledLoan("N");
        item.setEmployYear("4");
        item.setMarriageStatus("0");
        item.setParentsContactFlg("Y");
        item.setCreditInquireTimesL2("0");
        item.setCreditInquireTimesOwn("0");
        item.setCreditType("1");
        item.setCreditFlg("Y");
        guaranteeInfos.add(item);

        item = new CrifFrontPersonalInBean();
        item.setRole("G");
        item.setNationality("中国");
        item.setReturnFlg("N");
        item.setAge("26");
        item.setIdNum("2308E00");
        item.setNumOfLoanIndustry("0");
        item.setHouseProvince("23000");
        item.setHouseCity("230800");
        item.setProvince("110000");
        item.setCity("110100");
        item.setOverdue30CountFlg("N");
        item.setOverdue6TimesL6("0");
        item.setRelateOverdue30CountFlg("N");
        item.setRelateOverdue6TimesL6("0");
        item.setLastRejectDate("2016-01-13");
        item.setUnsetteledLoanC("N");
        item.setUnsetteledLoan("N");
        item.setEmployYear("4");
        item.setMarriageStatus("0");
        item.setParentsContactFlg("Y");
        item.setCreditInquireTimesL2("0");
        item.setCreditInquireTimesOwn("0");
        item.setCreditType("1");
        item.setCreditFlg("Y");

        List<CrifFrontCreditSimpleInBean> creditSimpleList = new ArrayList<CrifFrontCreditSimpleInBean>();
        CrifFrontCreditSimpleInBean creditSimple = new CrifFrontCreditSimpleInBean();
        creditSimple.setCcDue90TimesL60S("0");
        creditSimple.setCcOverdueTimesL60S("0");
        creditSimpleList.add(creditSimple);
        creditSimple = new CrifFrontCreditSimpleInBean();
        creditSimple.setCcDue90TimesL60S("1");
        creditSimple.setCcOverdueTimesL60S("1");
        creditSimpleList.add(creditSimple);
        item.setCreditSimpleList(creditSimpleList);


        List<CrifFrontLoanSimpleInBean> loanSimpleList = new ArrayList<CrifFrontLoanSimpleInBean>();
        CrifFrontLoanSimpleInBean loanSimple = new CrifFrontLoanSimpleInBean();
        loanSimple.setPlOverdueTimesL60S("0");
        loanSimple.setPlDue90TimesL60S("0");
        loanSimpleList.add(loanSimple);
        loanSimple = new CrifFrontLoanSimpleInBean();
        loanSimple.setPlOverdueTimesL60S("10");
        loanSimple.setPlDue90TimesL60S("10");
        loanSimpleList.add(loanSimple);
        item.setLoanSimpleList(loanSimpleList);

        guaranteeInfos.add(item);
        inBean.setPersonalInfos(guaranteeInfos);
        JSONObject json = (JSONObject) JSONObject.toJSON(inBean);
        System.out.println(json.toJSONString());
    }
}
