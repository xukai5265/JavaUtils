package cn.xukai.java.json.bean;

import java.io.Serializable;

/**
 * Crif规则引擎 : 个人信息（申请人，配偶，保证人）-简版贷款信息传入参数Bean.
 * @Class Name CrifFrontLoanSimpleInBean
 * @author CHP_3.0 接口组
 * @Create In 2016年9月12日
 */
public class CrifFrontLoanSimpleInBean implements Serializable {
    /** 序列化. */
    private static final long serialVersionUID = -535611430624233156L;
    /** 简版贷款信息-贷款单笔5年内累计逾期次数. */
    private String plOverdueTimesL60S;
    /** 简版贷款信息-贷款账户状态. */
    private String plAccountStatusS;
    /** 简版贷款信息-贷款5年内出现90天以上的逾期次数. */
    private String plDue90TimesL60S;
    /** 简版贷款信息-贷款结清日期. */
    private String plSetteledDateS;
    /** 简版贷款信息-贷款放款日期. */
    private String plDateS;
    
    /**
     * 参数输出.
     * @return Bean中参数集合
     */
    public String getParam() {
        StringBuilder paramSb = new StringBuilder();
        
        paramSb.append("plOverdueTimesL60S=").append(plOverdueTimesL60S)
            .append(", plAccountStatusS=").append(plAccountStatusS)
            .append(", plDue90TimesL60S=").append(plDue90TimesL60S)
            .append(", plSetteledDateS=").append(plSetteledDateS)
            .append(", plDateS=").append(plDateS);
            
        return paramSb.toString();
    }

    /**
     * 简版贷款信息-贷款单笔5年内累计逾期次数 的取得处理.
     *
     * @return 简版贷款信息-贷款单笔5年内累计逾期次数
     */
    public String getPlOverdueTimesL60S() {
        return this.plOverdueTimesL60S;
    }

    /**
     * 简版贷款信息-贷款单笔5年内累计逾期次数 的设定处理.
     *
     * @param plOverdueTimesL60S 简版贷款信息-贷款单笔5年内累计逾期次数
     */
    public void setPlOverdueTimesL60S(String plOverdueTimesL60S) {
        this.plOverdueTimesL60S = plOverdueTimesL60S;
    }

    /**
     * 简版贷款信息-贷款账户状态 的取得处理.
     *
     * @return 简版贷款信息-贷款账户状态
     */
    public String getPlAccountStatusS() {
        return this.plAccountStatusS;
    }

    /**
     * 简版贷款信息-贷款账户状态 的设定处理.
     *
     * @param plAccountStatusS 简版贷款信息-贷款账户状态
     */
    public void setPlAccountStatusS(String plAccountStatusS) {
        this.plAccountStatusS = plAccountStatusS;
    }

    /**
     * 简版贷款信息-贷款5年内出现90天以上的逾期次数 的取得处理.
     *
     * @return 简版贷款信息-贷款5年内出现90天以上的逾期次数
     */
    public String getPlDue90TimesL60S() {
        return this.plDue90TimesL60S;
    }

    /**
     * 简版贷款信息-贷款5年内出现90天以上的逾期次数 的设定处理.
     *
     * @param plDue90TimesL60S 简版贷款信息-贷款5年内出现90天以上的逾期次数
     */
    public void setPlDue90TimesL60S(String plDue90TimesL60S) {
        this.plDue90TimesL60S = plDue90TimesL60S;
    }

    /**
     * 简版贷款信息-贷款结清日期 的取得处理.
     *
     * @return 简版贷款信息-贷款结清日期
     */
    public String getPlSetteledDateS() {
        return this.plSetteledDateS;
    }

    /**
     * 简版贷款信息-贷款结清日期 的设定处理.
     *
     * @param plSetteledDateS 简版贷款信息-贷款结清日期
     */
    public void setPlSetteledDateS(String plSetteledDateS) {
        this.plSetteledDateS = plSetteledDateS;
    }

    /**
     * 简版贷款信息-贷款放款日期 的取得处理.
     *
     * @return 简版贷款信息-贷款放款日期
     */
    public String getPlDateS() {
        return this.plDateS;
    }

    /**
     * 简版贷款信息-贷款放款日期 的设定处理.
     *
     * @param plDateS 简版贷款信息-贷款放款日期
     */
    public void setPlDateS(String plDateS) {
        this.plDateS = plDateS;
    }
}
