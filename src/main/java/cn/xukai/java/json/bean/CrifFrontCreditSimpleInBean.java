package cn.xukai.java.json.bean;

import java.io.Serializable;

/**
 * Crif规则引擎 : 个人信息（申请人，配偶，保证人）-简版信用卡信息传入参数Bean.
 * @Class Name CrifFrontCreditSimpleInBean
 * @author CHP_3.0 接口组
 * @Create In 2016年9月12日
 */
public class CrifFrontCreditSimpleInBean implements Serializable {
    /** 序列化. */
    private static final long serialVersionUID = -8384690207021939890L;

    /** 简版信用卡信息-信用卡账户状态. */
    private String ccAccountStatusS;
    /** 简版信用卡信息-信用卡5年内出现90天以上的逾期次数. */
    private String ccDue90TimesL60S;
    /** 简版信用卡信息-信用卡销户日期. */
    private String ccAccountCancelDateS;
    /** 简版信用卡信息-信用卡开户日期. */
    private String ccAccountOpenDateS;
    /** 简版信用卡信息-信用卡单张5年内累计逾期次数. */
    private String ccOverdueTimesL60S;
    
    /**
     * 参数输出.
     * @return Bean中参数集合
     */
    public String getParam() {
        StringBuilder paramSb = new StringBuilder();
        
        paramSb.append("ccAccountStatusS=").append(ccAccountStatusS)
            .append(", ccDue90TimesL60S=").append(ccDue90TimesL60S)
            .append(", ccAccountCancelDateS=").append(ccAccountCancelDateS)
            .append(", ccAccountOpenDateS=").append(ccAccountOpenDateS)
            .append(", ccOverdueTimesL60S=").append(ccOverdueTimesL60S);
            
        return paramSb.toString();
    }

    /**
     * 简版信用卡信息-信用卡账户状态 的取得处理.
     *
     * @return 简版信用卡信息-信用卡账户状态
     */
    public String getCcAccountStatusS() {
        return this.ccAccountStatusS;
    }

    /**
     * 简版信用卡信息-信用卡账户状态 的设定处理.
     *
     * @param ccAccountStatusS 简版信用卡信息-信用卡账户状态
     */
    public void setCcAccountStatusS(String ccAccountStatusS) {
        this.ccAccountStatusS = ccAccountStatusS;
    }

    /**
     * 简版信用卡信息-信用卡5年内出现90天以上的逾期次数 的取得处理.
     *
     * @return 简版信用卡信息-信用卡5年内出现90天以上的逾期次数
     */
    public String getCcDue90TimesL60S() {
        return this.ccDue90TimesL60S;
    }

    /**
     * 简版信用卡信息-信用卡5年内出现90天以上的逾期次数 的设定处理.
     *
     * @param ccDue90TimesL60S 简版信用卡信息-信用卡5年内出现90天以上的逾期次数
     */
    public void setCcDue90TimesL60S(String ccDue90TimesL60S) {
        this.ccDue90TimesL60S = ccDue90TimesL60S;
    }

    /**
     * 简版信用卡信息-信用卡销户日期 的取得处理.
     *
     * @return 简版信用卡信息-信用卡销户日期
     */
    public String getCcAccountCancelDateS() {
        return this.ccAccountCancelDateS;
    }

    /**
     * 简版信用卡信息-信用卡销户日期 的设定处理.
     *
     * @param ccAccountCancelDateS 简版信用卡信息-信用卡销户日期
     */
    public void setCcAccountCancelDateS(String ccAccountCancelDateS) {
        this.ccAccountCancelDateS = ccAccountCancelDateS;
    }

    /**
     * 简版信用卡信息-信用卡开户日期 的取得处理.
     *
     * @return 简版信用卡信息-信用卡开户日期
     */
    public String getCcAccountOpenDateS() {
        return this.ccAccountOpenDateS;
    }

    /**
     * 简版信用卡信息-信用卡开户日期 的设定处理.
     *
     * @param ccAccountOpenDateS 简版信用卡信息-信用卡开户日期
     */
    public void setCcAccountOpenDateS(String ccAccountOpenDateS) {
        this.ccAccountOpenDateS = ccAccountOpenDateS;
    }

    /**
     * 简版信用卡信息-信用卡单张5年内累计逾期次数 的取得处理.
     *
     * @return 简版信用卡信息-信用卡单张5年内累计逾期次数
     */
    public String getCcOverdueTimesL60S() {
        return this.ccOverdueTimesL60S;
    }

    /**
     * 简版信用卡信息-信用卡单张5年内累计逾期次数 的设定处理.
     *
     * @param ccOverdueTimesL60S 简版信用卡信息-信用卡单张5年内累计逾期次数
     */
    public void setCcOverdueTimesL60S(String ccOverdueTimesL60S) {
        this.ccOverdueTimesL60S = ccOverdueTimesL60S;
    }
}
