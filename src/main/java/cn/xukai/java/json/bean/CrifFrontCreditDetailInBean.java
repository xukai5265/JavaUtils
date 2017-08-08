package cn.xukai.java.json.bean;

import java.io.Serializable;

/**
 * Crif规则引擎 : 个人信息（申请人，配偶，保证人）-详版信用卡信息传入参数Bean.
 * @Class Name CrifFrontInBean
 * @author CHP_3.0 接口组
 * @Create In 2016年9月12日
 */
public class CrifFrontCreditDetailInBean implements Serializable {
    /** 序列化. */
    private static final long serialVersionUID = -2088386081915886638L;

    /** 详版信用卡信息-信用卡账户状态. */
    private String ccAccountStatusDd;
    /** 详版信用卡信息-信用卡当前逾期状态. */
    private String ccAccountStatusD;
    /** 详版信用卡信息-信用卡还款记录字段是否出现G(Y/N). */
    private String ccAccountGFlg;
    /** 详版信用卡信息-信用卡单张近24个月最大逾期期数. */
    private String ccOverdueStatusMaxL24D;
    /** 详版信用卡信息-信用卡单张近24个月逾期次数. */
    private String ccOverdueTimesL24D;
    
    /**
     * 参数输出.
     * @return Bean中参数集合
     */
    public String getParam() {
        StringBuilder paramSb = new StringBuilder();
        
        paramSb.append("ccAccountStatusDd=").append(ccAccountStatusDd)
            .append(", ccAccountStatusD=").append(ccAccountStatusD)
            .append(", ccAccountGFlg=").append(ccAccountGFlg)
            .append(", ccOverdueStatusMaxL24D=").append(ccOverdueStatusMaxL24D)
            .append(", ccOverdueTimesL24D=").append(ccOverdueTimesL24D);
            
        return paramSb.toString();
    }

    /**
     * 详版信用卡信息-信用卡账户状态 的取得处理.
     *
     * @return 详版信用卡信息-信用卡账户状态
     */
    public String getCcAccountStatusDd() {
        return this.ccAccountStatusDd;
    }

    /**
     * 详版信用卡信息-信用卡账户状态 的设定处理.
     *
     * @param ccAccountStatusDd 详版信用卡信息-信用卡账户状态
     */
    public void setCcAccountStatusDd(String ccAccountStatusDd) {
        this.ccAccountStatusDd = ccAccountStatusDd;
    }

    /**
     * 详版信用卡信息-信用卡当前逾期状态 的取得处理.
     *
     * @return 详版信用卡信息-信用卡当前逾期状态
     */
    public String getCcAccountStatusD() {
        return this.ccAccountStatusD;
    }

    /**
     * 详版信用卡信息-信用卡当前逾期状态 的设定处理.
     *
     * @param ccAccountStatusD 详版信用卡信息-信用卡当前逾期状态
     */
    public void setCcAccountStatusD(String ccAccountStatusD) {
        this.ccAccountStatusD = ccAccountStatusD;
    }

    /**
     * 信用卡还款记录字段是否出现G(Y/N) 的取得处理.
     *
     * @return 信用卡还款记录字段是否出现G(Y/N)
     */
    public String getCcAccountGFlg() {
        return this.ccAccountGFlg;
    }

    /**
     * 信用卡还款记录字段是否出现G(Y/N) 的设定处理.
     *
     * @param ccAccountGFlg 信用卡还款记录字段是否出现G(Y/N)
     */
    public void setCcAccountGFlg(String ccAccountGFlg) {
        this.ccAccountGFlg = ccAccountGFlg;
    }
    
    /**
     * 详版信用卡信息-信用卡单张近24个月最大逾期期数 的取得处理.
     *
     * @return 详版信用卡信息-信用卡单张近24个月最大逾期期数
     */
    public String getCcOverdueStatusMaxL24D() {
        return this.ccOverdueStatusMaxL24D;
    }

    /**
     * 详版信用卡信息-信用卡单张近24个月最大逾期期数 的设定处理.
     *
     * @param ccOverdueStatusMaxL24D 详版信用卡信息-信用卡单张近24个月最大逾期期数
     */
    public void setCcOverdueStatusMaxL24D(String ccOverdueStatusMaxL24D) {
        this.ccOverdueStatusMaxL24D = ccOverdueStatusMaxL24D;
    }

    /**
     * 详版信用卡信息-信用卡单张近24个月逾期次数 的取得处理.
     *
     * @return 详版信用卡信息-信用卡单张近24个月逾期次数
     */
    public String getCcOverdueTimesL24D() {
        return this.ccOverdueTimesL24D;
    }

    /**
     * 详版信用卡信息-信用卡单张近24个月逾期次数 的设定处理.
     *
     * @param ccOverdueTimesL24D 详版信用卡信息-信用卡单张近24个月逾期次数
     */
    public void setCcOverdueTimesL24D(String ccOverdueTimesL24D) {
        this.ccOverdueTimesL24D = ccOverdueTimesL24D;
    }
}
