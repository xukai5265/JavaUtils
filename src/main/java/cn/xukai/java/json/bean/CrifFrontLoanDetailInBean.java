package cn.xukai.java.json.bean;

import java.io.Serializable;

/**
 * Crif规则引擎 : 个人信息（申请人，配偶，保证人）-简版贷款信息传入参数Bean.
 * @Class Name CrifFrontLoanSimpleInBean
 * @author CHP_3.0 接口组
 * @Create In 2016年9月12日
 */
public class CrifFrontLoanDetailInBean implements Serializable {
    /** 序列化. */
    private static final long serialVersionUID = -2347259807074727908L;

    /** 详版贷款信息-贷款账户状态. */
    private String plAccountStatusDd;
    /** 详版贷款信息-贷款当前逾期状态. */
    private String plAccountStatusD;
    /** 详版贷款信息-贷款单张近24个月最大期数. */
    private String plOverdueStatusMaxL24D;
    /** 详版贷款信息-贷款单张近24个月逾期次数. */
    private String plOverdueTimesL24D;
    /** 详版贷款信息-单笔贷款还款记录是否出现D，G，Z. */
    private String plPaymentDGZD;
    /** 详版贷款信息-贷款5级分类. */
    private String plAccountStyleD;
    
    /**
     * 参数输出.
     * @return Bean中参数集合
     */
    public String getParam() {
        StringBuilder paramSb = new StringBuilder();
        
        paramSb.append("plAccountStatusDd=").append(plAccountStatusDd)
            .append(", plAccountStatusD=").append(plAccountStatusD)
            .append(", plOverdueStatusMaxL24D=").append(plOverdueStatusMaxL24D)
            .append(", plOverdueTimesL24D=").append(plOverdueTimesL24D)
            .append(", plPaymentDGZD=").append(plPaymentDGZD)
            .append(", plAccountStyleD=").append(plAccountStyleD);
            
        return paramSb.toString();
    }

    /**
     * 详版贷款信息-贷款账户状态 的取得处理.
     *
     * @return 详版贷款信息-贷款账户状态
     */
    public String getPlAccountStatusDd() {
        return this.plAccountStatusDd;
    }

    /**
     * 详版贷款信息-贷款账户状态 的设定处理.
     *
     * @param plAccountStatusDd 详版贷款信息-贷款账户状态
     */
    public void setPlAccountStatusDd(String plAccountStatusDd) {
        this.plAccountStatusDd = plAccountStatusDd;
    }

    /**
     * 详版贷款信息-贷款当前逾期状态 的取得处理.
     *
     * @return 详版贷款信息-贷款当前逾期状态
     */
    public String getPlAccountStatusD() {
        return this.plAccountStatusD;
    }

    /**
     * 详版贷款信息-贷款当前逾期状态 的设定处理.
     *
     * @param plAccountStatusD 详版贷款信息-贷款当前逾期状态
     */
    public void setPlAccountStatusD(String plAccountStatusD) {
        this.plAccountStatusD = plAccountStatusD;
    }

    /**
     * 详版贷款信息-贷款单张近24个月最大期数 的取得处理.
     *
     * @return 详版贷款信息-贷款单张近24个月最大期数
     */
    public String getPlOverdueStatusMaxL24D() {
        return this.plOverdueStatusMaxL24D;
    }

    /**
     * 详版贷款信息-贷款单张近24个月最大期数 的设定处理.
     *
     * @param plOverdueStatusMaxL24D 详版贷款信息-贷款单张近24个月最大期数
     */
    public void setPlOverdueStatusMaxL24D(String plOverdueStatusMaxL24D) {
        this.plOverdueStatusMaxL24D = plOverdueStatusMaxL24D;
    }

    /**
     * 详版贷款信息-贷款单张近24个月逾期次数 的取得处理.
     *
     * @return 详版贷款信息-贷款单张近24个月逾期次数
     */
    public String getPlOverdueTimesL24D() {
        return this.plOverdueTimesL24D;
    }

    /**
     * 详版贷款信息-贷款单张近24个月逾期次数 的设定处理.
     *
     * @param plOverdueTimesL24D 详版贷款信息-贷款单张近24个月逾期次数
     */
    public void setPlOverdueTimesL24D(String plOverdueTimesL24D) {
        this.plOverdueTimesL24D = plOverdueTimesL24D;
    }

    /**
     * 详版贷款信息-单笔贷款还款记录是否出现D，G，Z 的取得处理.
     *
     * @return 详版贷款信息-单笔贷款还款记录是否出现D，G，Z
     */
    public String getPlPaymentDGZD() {
        return this.plPaymentDGZD;
    }

    /**
     * 详版贷款信息-单笔贷款还款记录是否出现D，G，Z 的设定处理.
     *
     * @param plPaymentDGZD 详版贷款信息-单笔贷款还款记录是否出现D，G，Z
     */
    public void setPlPaymentDGZD(String plPaymentDGZD) {
        this.plPaymentDGZD = plPaymentDGZD;
    }

    /**
     * 详版贷款信息-贷款5级分类 的取得处理.
     *
     * @return 详版贷款信息-贷款5级分类
     */
    public String getPlAccountStyleD() {
        return this.plAccountStyleD;
    }

    /**
     * 详版贷款信息-贷款5级分类 的设定处理.
     *
     * @param plAccountStyleD 详版贷款信息-贷款5级分类
     */
    public void setPlAccountStyleD(String plAccountStyleD) {
        this.plAccountStyleD = plAccountStyleD;
    }
}
