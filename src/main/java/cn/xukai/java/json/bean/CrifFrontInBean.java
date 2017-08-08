package cn.xukai.java.json.bean;


import java.util.List;

/**
 * Crif规则引擎 : 前置策略传入参数Bean.
 * @Class Name CrifFrontInBean
 * @author CHP_3.0 接口组
 * @Create In 2016年9月12日
 */
public class CrifFrontInBean extends BaseInfo implements IParamInfo {
    /** 序列化. */
    private static final long serialVersionUID = -4871184484769863055L;
    /** 业务编号. */
    private String appNo;
    /** 申请日期. */
    private String appDate;
    /** 申请金额. */
    private String appAmt;
    /** 借款产品类型. */
    private String productType;
    /** 申请人年龄. */
    private String appAge;
    /** 申请人婚姻状况(0：未婚 1：已婚 2：离婚 3：丧偶). */
    private String appMarriageStatus;
    /** 学历(0：硕士以上 1：本科 2：大专 3：高中专 4：初中及以下). */
    private String appEducation;
    /** 性别. */
    private String appGender;
    /** 房产状况(0：商业按揭房 1：无按揭房 2：公积金按揭购房 3：自建房 4：租用 5：亲属住房 6：宿舍 7：其他). */
    private String appEstateSituation;
    /** 最早的贷记卡账龄(月). */
    private String appCcAgingEarliest;
    /** 最早贷款账龄(月). */
    private String appLoanAging;
    /** 最近的贷记卡账龄(月). */
    private String appCcAgingLastest;
    /** 贷款审批查询次数. */
    private String appLoanAppNum;
    /** 本人查询次数. */
    private String appInquireTimes;
    /** 正常贷记卡额度使用率. */
    private String appUtilizationCc;
    /** 发生过逾期的贷款个数. */
    private String appOverdueCount;
    /** 最近6个月贷款审批或信用卡审批征信查询次数. */
    private String appCreditInquireTimesL6;
    /** 近6个月是否获得贷款或贷记卡(Y / N). */
    private String appLoanCcFlg;
    /** 有无法人保证人(Y / N). */
    private String appGuarantorFaFlg;
    /** 有无配偶保证人(Y / N). */
    private String appGuarantorRFlg;
    /** 有无第三方保证人(Y / N). */
    private String appGuarantorFlg;
    /** 保证人影像资料是否为空(Y / N). */
    private String appGuarantorFigure;
    /** 企业税前营业收入. */
    private String appCompanyIncome;
    /** 企业年限. */
    private String appCompanyRegistrationYear;
    /** 有无提供房产证明(Y / N). */
    private String appEstateProvideFlg;
    /** 借款期数. */
    private String appLoanTerms;
    /** 总缴费年限. */
    private String appTolPeriod;
    /** 保单缴费方式. */
    private String appInsPaymenyType;
    /** 年缴保费金额. */
    private String appInsPaymenyAmtY;
    /** 保单已生效年限. */
    private String appInsEffectY;
    /** 保单状态. */
    private String appInsStatus;
    /** 保单剩余有效期限(月). */
    private String appInsRemainingMonths;
    /** 个人信息(配偶/保证人). */
    private List<CrifFrontPersonalInBean> personalInfos;
    /** 正常1元贷记卡额度使用率. */
    private String utilization1Cc;
    
    /**
     * 参数输出.
     * @return Bean中参数集合
     */
    public String getParam() {
        StringBuilder paramSb = new StringBuilder();
        
        paramSb.append("appNo=").append(appNo)
            .append(", appDate=").append(appDate)
            .append(", appAmt=").append(appAmt)
            .append(", productType=").append(productType)
            .append(", appAge=").append(appAge)
            .append(", appMarriageStatus=").append(appMarriageStatus)
            .append(", appEducation=").append(appEducation)
            .append(", appGender=").append(appGender)
            .append(", appEstateSituation=").append(appEstateSituation)
            .append(", appCcAgingEarliest=").append(appCcAgingEarliest)
            .append(", appLoanAging=").append(appLoanAging)
            .append(", appCcAgingLastest=").append(appCcAgingLastest)
            .append(", appLoanAppNum=").append(appLoanAppNum)
            .append(", appInquireTimes=").append(appInquireTimes)
            .append(", appUtilizationCc=").append(appUtilizationCc)
            .append(", appOverdueCount=").append(appOverdueCount)
            .append(", appCreditInquireTimesL6=").append(appCreditInquireTimesL6)
            .append(", appLoanCcFlg=").append(appLoanCcFlg)
            .append(", appGuarantorFaFlg=").append(appGuarantorFaFlg)
            .append(", appGuarantorRFlg=").append(appGuarantorRFlg)
            .append(", appGuarantorFlg=").append(appGuarantorFlg)
            .append(", appGuarantorFigure=").append(appGuarantorFigure)
            .append(", appCompanyIncome=").append(appCompanyIncome)
            .append(", appCompanyRegistrationYear=").append(appCompanyRegistrationYear)
            .append(", appEstateProvideFlg=").append(appEstateProvideFlg)
            .append(", appLoanTerms=").append(appLoanTerms)
            .append(", appTolPeriod=").append(appTolPeriod)
            .append(", appInsPaymenyType=").append(appInsPaymenyType)
            .append(", appInsPaymenyAmtY=").append(appInsPaymenyAmtY)
            .append(", appInsEffectY=").append(appInsEffectY)
            .append(", appInsStatus=").append(appInsStatus)
            .append(", utilization1Cc=").append(utilization1Cc)
            .append(", appInsRemainingMonths=").append(appInsRemainingMonths);

        paramSb.append(", personalInfos=[");
        if (personalInfos != null) {
            for (int i = 0; i < personalInfos.size(); i++) {
                paramSb.append("{");
                paramSb.append(personalInfos.get(i).getParam());
                paramSb.append("}");
            }
        }
        paramSb.append("]");
        return paramSb.toString();
    }

    /**
     * 业务编号 的取得处理.
     *
     * @return 业务编号
     */
    public String getAppNo() {
        return this.appNo;
    }

    /**
     * 业务编号 的设定处理.
     *
     * @param appNo 业务编号
     */
    public void setAppNo(String appNo) {
        this.appNo = appNo;
    }

    /**
     * 申请日期 的取得处理.
     *
     * @return 申请日期
     */
    public String getAppDate() {
        return this.appDate;
    }

    /**
     * 申请日期 的设定处理.
     *
     * @param appDate 申请日期
     */
    public void setAppDate(String appDate) {
        this.appDate = appDate;
    }

    /**
     * 申请金额 的取得处理.
     *
     * @return 申请金额
     */
    public String getAppAmt() {
        return this.appAmt;
    }

    /**
     * 申请金额 的设定处理.
     *
     * @param appAmt 申请金额
     */
    public void setAppAmt(String appAmt) {
        this.appAmt = appAmt;
    }

    /**
     * 借款产品类型 的取得处理.
     *
     * @return 借款产品类型
     */
    public String getProductType() {
        return this.productType;
    }

    /**
     * 借款产品类型 的设定处理.
     *
     * @param productType 借款产品类型
     */
    public void setProductType(String productType) {
        this.productType = productType;
    }

    /**
     * 申请人年龄 的取得处理.
     *
     * @return 申请人年龄
     */
    public String getAppAge() {
        return this.appAge;
    }

    /**
     * 申请人年龄 的设定处理.
     *
     * @param appAge 申请人年龄
     */
    public void setAppAge(String appAge) {
        this.appAge = appAge;
    }

    /**
     * 申请人婚姻状况(0：未婚 1：已婚 2：离婚 3：丧偶) 的取得处理.
     *
     * @return 申请人婚姻状况(0：未婚 1：已婚 2：离婚 3：丧偶)
     */
    public String getAppMarriageStatus() {
        return this.appMarriageStatus;
    }

    /**
     * 申请人婚姻状况(0：未婚 1：已婚 2：离婚 3：丧偶) 的设定处理.
     *
     * @param appMarriageStatus 申请人婚姻状况(0：未婚 1：已婚 2：离婚 3：丧偶)
     */
    public void setAppMarriageStatus(String appMarriageStatus) {
        this.appMarriageStatus = appMarriageStatus;
    }

    /**
     * 学历(0：硕士以上 1：本科 2：大专 3：高中专 4：初中及以下) 的取得处理.
     *
     * @return 学历(0：硕士以上 1：本科 2：大专 3：高中专 4：初中及以下)
     */
    public String getAppEducation() {
        return this.appEducation;
    }

    /**
     * 学历(0：硕士以上 1：本科 2：大专 3：高中专 4：初中及以下) 的设定处理.
     *
     * @param appEducation 学历(0：硕士以上 1：本科 2：大专 3：高中专 4：初中及以下)
     */
    public void setAppEducation(String appEducation) {
        this.appEducation = appEducation;
    }

    /**
     * 性别 的取得处理.
     *
     * @return 性别
     */
    public String getAppGender() {
        return this.appGender;
    }

    /**
     * 性别 的设定处理.
     *
     * @param appGender 性别
     */
    public void setAppGender(String appGender) {
        this.appGender = appGender;
    }

    /**
     * 房产状况(0：商业按揭房 1：无按揭房 2：公积金按揭购房 3：自建房 4：租用 5：亲属住房 6：宿舍 7：其他) 的取得处理.
     *
     * @return 房产状况(0：商业按揭房 1：无按揭房 2：公积金按揭购房 3：自建房 4：租用 5：亲属住房 6：宿舍 7：其他)
     */
    public String getAppEstateSituation() {
        return this.appEstateSituation;
    }

    /**
     * 房产状况(0：商业按揭房 1：无按揭房 2：公积金按揭购房 3：自建房 4：租用 5：亲属住房 6：宿舍 7：其他) 的设定处理.
     *
     * @param appEstateSituation 房产状况(0：商业按揭房 1：无按揭房 2：公积金按揭购房 3：自建房 4：租用 5：亲属住房 6：宿舍 7：其他)
     */
    public void setAppEstateSituation(String appEstateSituation) {
        this.appEstateSituation = appEstateSituation;
    }

    /**
     * 最早的贷记卡账龄(月) 的取得处理.
     *
     * @return 最早的贷记卡账龄(月)
     */
    public String getAppCcAgingEarliest() {
        return this.appCcAgingEarliest;
    }

    /**
     * 最早的贷记卡账龄(月) 的设定处理.
     *
     * @param appCcAgingEarliest 最早的贷记卡账龄(月)
     */
    public void setAppCcAgingEarliest(String appCcAgingEarliest) {
        this.appCcAgingEarliest = appCcAgingEarliest;
    }

    /**
     * 最早贷款账龄(月) 的取得处理.
     *
     * @return 最早贷款账龄(月)
     */
    public String getAppLoanAging() {
        return this.appLoanAging;
    }

    /**
     * 最早贷款账龄(月) 的设定处理.
     *
     * @param appLoanAging 最早贷款账龄(月)
     */
    public void setAppLoanAging(String appLoanAging) {
        this.appLoanAging = appLoanAging;
    }

    /**
     * 最近的贷记卡账龄(月) 的取得处理.
     *
     * @return 最近的贷记卡账龄(月)
     */
    public String getAppCcAgingLastest() {
        return this.appCcAgingLastest;
    }

    /**
     * 最近的贷记卡账龄(月) 的设定处理.
     *
     * @param appCcAgingLastest 最近的贷记卡账龄(月)
     */
    public void setAppCcAgingLastest(String appCcAgingLastest) {
        this.appCcAgingLastest = appCcAgingLastest;
    }

    /**
     * 贷款审批查询次数 的取得处理.
     *
     * @return 贷款审批查询次数
     */
    public String getAppLoanAppNum() {
        return this.appLoanAppNum;
    }

    /**
     * 贷款审批查询次数 的设定处理.
     *
     * @param appLoanAppNum 贷款审批查询次数
     */
    public void setAppLoanAppNum(String appLoanAppNum) {
        this.appLoanAppNum = appLoanAppNum;
    }

    /**
     * 本人查询次数 的取得处理.
     *
     * @return 本人查询次数
     */
    public String getAppInquireTimes() {
        return this.appInquireTimes;
    }

    /**
     * 本人查询次数 的设定处理.
     *
     * @param appInquireTimes 本人查询次数
     */
    public void setAppInquireTimes(String appInquireTimes) {
        this.appInquireTimes = appInquireTimes;
    }

    /**
     * 正常贷记卡额度使用率 的取得处理.
     *
     * @return 正常贷记卡额度使用率
     */
    public String getAppUtilizationCc() {
        return this.appUtilizationCc;
    }

    /**
     * 正常贷记卡额度使用率 的设定处理.
     *
     * @param appUtilizationCc 正常贷记卡额度使用率
     */
    public void setAppUtilizationCc(String appUtilizationCc) {
        this.appUtilizationCc = appUtilizationCc;
    }

    /**
     * 发生过逾期的贷款个数 的取得处理.
     *
     * @return 发生过逾期的贷款个数
     */
    public String getAppOverdueCount() {
        return this.appOverdueCount;
    }

    /**
     * 发生过逾期的贷款个数 的设定处理.
     *
     * @param appOverdueCount 发生过逾期的贷款个数
     */
    public void setAppOverdueCount(String appOverdueCount) {
        this.appOverdueCount = appOverdueCount;
    }

    /**
     * 最近6个月贷款审批或信用卡审批征信查询次数 的取得处理.
     *
     * @return 最近6个月贷款审批或信用卡审批征信查询次数
     */
    public String getAppCreditInquireTimesL6() {
        return this.appCreditInquireTimesL6;
    }

    /**
     * 最近6个月贷款审批或信用卡审批征信查询次数 的设定处理.
     *
     * @param appCreditInquireTimesL6 最近6个月贷款审批或信用卡审批征信查询次数
     */
    public void setAppCreditInquireTimesL6(String appCreditInquireTimesL6) {
        this.appCreditInquireTimesL6 = appCreditInquireTimesL6;
    }

    /**
     * 近6个月是否获得贷款或贷记卡(Y / N) 的取得处理.
     *
     * @return 近6个月是否获得贷款或贷记卡(Y / N)
     */
    public String getAppLoanCcFlg() {
        return this.appLoanCcFlg;
    }

    /**
     * 近6个月是否获得贷款或贷记卡(Y / N) 的设定处理.
     *
     * @param appLoanCcFlg 近6个月是否获得贷款或贷记卡(Y / N)
     */
    public void setAppLoanCcFlg(String appLoanCcFlg) {
        this.appLoanCcFlg = appLoanCcFlg;
    }

    /**
     * 有无法人保证人(Y / N) 的取得处理.
     *
     * @return 有无法人保证人(Y / N)
     */
    public String getAppGuarantorFaFlg() {
        return this.appGuarantorFaFlg;
    }

    /**
     * 有无法人保证人(Y / N) 的设定处理.
     *
     * @param appGuarantorFaFlg 有无法人保证人(Y / N)
     */
    public void setAppGuarantorFaFlg(String appGuarantorFaFlg) {
        this.appGuarantorFaFlg = appGuarantorFaFlg;
    }

    /**
     * 有无配偶保证人(Y / N) 的取得处理.
     *
     * @return 有无配偶保证人(Y / N)
     */
    public String getAppGuarantorRFlg() {
        return this.appGuarantorRFlg;
    }

    /**
     * 有无配偶保证人(Y / N) 的设定处理.
     *
     * @param appGuarantorRFlg 有无配偶保证人(Y / N)
     */
    public void setAppGuarantorRFlg(String appGuarantorRFlg) {
        this.appGuarantorRFlg = appGuarantorRFlg;
    }

    /**
     * 有无第三方保证人(Y / N) 的取得处理.
     *
     * @return 有无第三方保证人(Y / N)
     */
    public String getAppGuarantorFlg() {
        return this.appGuarantorFlg;
    }

    /**
     * 有无第三方保证人(Y / N) 的设定处理.
     *
     * @param appGuarantorFlg 有无第三方保证人(Y / N)
     */
    public void setAppGuarantorFlg(String appGuarantorFlg) {
        this.appGuarantorFlg = appGuarantorFlg;
    }

    /**
     * 保证人影像资料是否为空(Y / N) 的取得处理.
     *
     * @return 保证人影像资料是否为空(Y / N)
     */
    public String getAppGuarantorFigure() {
        return this.appGuarantorFigure;
    }

    /**
     * 保证人影像资料是否为空(Y / N) 的设定处理.
     *
     * @param appGuarantorFigure 保证人影像资料是否为空(Y / N)
     */
    public void setAppGuarantorFigure(String appGuarantorFigure) {
        this.appGuarantorFigure = appGuarantorFigure;
    }

    /**
     * 企业税前营业收入 的取得处理.
     *
     * @return 企业税前营业收入
     */
    public String getAppCompanyIncome() {
        return this.appCompanyIncome;
    }

    /**
     * 企业税前营业收入 的设定处理.
     *
     * @param appCompanyIncome 企业税前营业收入
     */
    public void setAppCompanyIncome(String appCompanyIncome) {
        this.appCompanyIncome = appCompanyIncome;
    }

    /**
     * 企业年限 的取得处理.
     *
     * @return 企业年限
     */
    public String getAppCompanyRegistrationYear() {
        return this.appCompanyRegistrationYear;
    }

    /**
     * 企业年限 的设定处理.
     *
     * @param appCompanyRegistrationYear 企业年限
     */
    public void setAppCompanyRegistrationYear(String appCompanyRegistrationYear) {
        this.appCompanyRegistrationYear = appCompanyRegistrationYear;
    }

    /**
     * 有无提供房产证明(Y / N) 的取得处理.
     *
     * @return 有无提供房产证明(Y / N)
     */
    public String getAppEstateProvideFlg() {
        return this.appEstateProvideFlg;
    }

    /**
     * 有无提供房产证明(Y / N) 的设定处理.
     *
     * @param appEstateProvideFlg 有无提供房产证明(Y / N)
     */
    public void setAppEstateProvideFlg(String appEstateProvideFlg) {
        this.appEstateProvideFlg = appEstateProvideFlg;
    }

    /**
     * 借款期数 的取得处理.
     *
     * @return 借款期数
     */
    public String getAppLoanTerms() {
        return this.appLoanTerms;
    }

    /**
     * 借款期数 的设定处理.
     *
     * @param appLoanTerms 借款期数
     */
    public void setAppLoanTerms(String appLoanTerms) {
        this.appLoanTerms = appLoanTerms;
    }

    /**
     * 总缴费年限 的取得处理.
     *
     * @return 总缴费年限
     */
    public String getAppTolPeriod() {
        return this.appTolPeriod;
    }

    /**
     * 总缴费年限 的设定处理.
     *
     * @param appTolPeriod 总缴费年限
     */
    public void setAppTolPeriod(String appTolPeriod) {
        this.appTolPeriod = appTolPeriod;
    }

    /**
     * 保单缴费方式 的取得处理.
     *
     * @return 保单缴费方式
     */
    public String getAppInsPaymenyType() {
        return this.appInsPaymenyType;
    }

    /**
     * 保单缴费方式 的设定处理.
     *
     * @param appInsPaymenyType 保单缴费方式
     */
    public void setAppInsPaymenyType(String appInsPaymenyType) {
        this.appInsPaymenyType = appInsPaymenyType;
    }

    /**
     * 年缴保费金额 的取得处理.
     *
     * @return 年缴保费金额
     */
    public String getAppInsPaymenyAmtY() {
        return this.appInsPaymenyAmtY;
    }

    /**
     * 年缴保费金额 的设定处理.
     *
     * @param appInsPaymenyAmtY 年缴保费金额
     */
    public void setAppInsPaymenyAmtY(String appInsPaymenyAmtY) {
        this.appInsPaymenyAmtY = appInsPaymenyAmtY;
    }

    /**
     * 保单已生效年限 的取得处理.
     *
     * @return 保单已生效年限
     */
    public String getAppInsEffectY() {
        return this.appInsEffectY;
    }

    /**
     * 保单已生效年限 的设定处理.
     *
     * @param appInsEffectY 保单已生效年限
     */
    public void setAppInsEffectY(String appInsEffectY) {
        this.appInsEffectY = appInsEffectY;
    }

    /**
     * 保单状态 的取得处理.
     *
     * @return 保单状态
     */
    public String getAppInsStatus() {
        return this.appInsStatus;
    }

    /**
     * 保单状态 的设定处理.
     *
     * @param appInsStatus 保单状态
     */
    public void setAppInsStatus(String appInsStatus) {
        this.appInsStatus = appInsStatus;
    }

    /**
     * 保单剩余有效期限(月) 的取得处理.
     *
     * @return 保单剩余有效期限(月)
     */
    public String getAppInsRemainingMonths() {
        return this.appInsRemainingMonths;
    }

    /**
     * 保单剩余有效期限(月) 的设定处理.
     *
     * @param appInsRemainingMonths 保单剩余有效期限(月)
     */
    public void setAppInsRemainingMonths(String appInsRemainingMonths) {
        this.appInsRemainingMonths = appInsRemainingMonths;
    }

    /**
     * 个人信息(配偶/保证人) 的取得处理.
     * @return 个人信息(配偶/保证人)
     */
    public List<CrifFrontPersonalInBean> getPersonalInfos() {
        return personalInfos;
    }

    /**
     * 个人信息(配偶/保证人) 的设定处理.
     * @param personalInfos 个人信息(配偶/保证人)
     */
    public void setPersonalInfos(List<CrifFrontPersonalInBean> personalInfos) {
        this.personalInfos = personalInfos;
    }

    /**
     * 正常1元贷记卡额度使用率 的取得处理.
     * @return utilization1Cc
     */
	public String getUtilization1Cc() {
		return utilization1Cc;
	}

	/**
     * 正常1元贷记卡额度使用率 的设定处理.
     * @param utilization1Cc  
     */
	public void setUtilization1Cc(String utilization1Cc) {
		this.utilization1Cc = utilization1Cc;
	}
}
