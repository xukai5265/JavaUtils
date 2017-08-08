package cn.xukai.java.json.bean;

import java.util.List;

/**
 * Crif规则引擎 : 前置策略传入参数(个人信息（申请人，配偶，保证人）)Bean.
 * @Class Name CrifFrontInBean
 * @author CHP_3.0 接口组
 * @Create In 2016年9月12日
 */
public class CrifFrontPersonalInBean extends BaseInfo implements IParamInfo {
    /** 序列化. */
    private static final long serialVersionUID = -4871184484769863055L;
    /** 个人信息-角色. */
    private String role;
    /** 个人信息-国籍. */
    private String nationality;
    /** 个人信息-是否退回后变更保证人. */
    private String returnFlg;
    /** 个人信息-年龄. */
    private String age;
    /** 个人信息-身份证号码. */
    private String idNum;
    /** 个人信息-同业借贷数量. */
    private String numOfLoanIndustry;
    /** 个人信息-行业. */
    private String industry;
    /** 个人信息-户籍所在省. */
    private String houseProvince;
    /** 个人信息-户籍城市. */
    private String houseCity;
    /** 个人信息-工作/居住所在省. */
    private String province;
    /** 个人信息-工作/居住所在城市. */
    private String city;
    /** 个人信息-是否在我司累计逾期天数超过30天（不含）. */
    private String overdue30CountFlg;
    /** 个人信息-近6个月逾期6天以上（不含）的次数. */
    private String overdue6TimesL6;
    /** 个人信息-关联的历史申请人逾期天数超过30天. */
    private String relateOverdue30CountFlg;
    /** 个人信息-关联的历史申请人逾期6天以上的次数. */
    private String relateOverdue6TimesL6;
    /** 个人信息-总收入. */
    private String totalIncome;
    /** 个人信息-总负债. */
    private String totalExpense;
    /** 个人信息-自述月还能力. */
    private String monthlyRepaymentAbility;
    /** 个人信息-客户自述收入. */
    private String income;
    /** 个人信息-家庭收入. */
    private String familyIncome;
    /** 个人信息-上次申请拒绝时间. */
    private String lastRejectDate;
    /** 个人信息-是否为他人的保证人在我司已有借款尚未结清. */
    private String unsetteledLoanC;
    /** 个人信息-是否在我司已有借款尚未结清（包含房借、车借). */
    private String unsetteledLoan;
    /** 个人信息-在此单位工作年限. */
    private String employYear;
    /** 个人信息-婚姻状况. */
    private String marriageStatus;
    /** 个人信息-有无父母联系方式. */
    private String parentsContactFlg;
    /** 个人信息-最近2个月贷款审批或信用卡审批征信查询次数. */
    private String creditInquireTimesL2;
    /** 个人信息-本人征信查询次数. */
    private String creditInquireTimesOwn;
    /** 个人信息-征信信息类型. */
    private String creditType;
    /** 个人信息-征信报告日期(YYYY-MM-DD). */
    private String creditDate;
    /** 个人信息-征信是否空白. */
    private String creditFlg;
    /** 个人信息-客户类型(1:工薪类;0：经营类). */
    private String customerType;
    /** 汇诚借款状态是否命中在途. */
    private String stateOnthewayHc;
    /** 快易借借款状态是否命中在途. */
    private String stateOnthewayKyj;
    /** 信审拒借的进件时间（汇诚）. */
    private String timeRefuseLetterHc;
    /** 信审拒借的进件时间（快易借）. */
    private String timeRefuseLetterKyj;
    /** 门店拒借的进件时间（汇诚）. */
    private String timeRefuseStore;
    /** 规则引擎拒借的进件时间（汇诚）. */
    private String timeRefuseEngineHc;
    /** 规则引擎拒借的进件时间（快易借）. */
    private String timeRefuseEngineKyj;
    /** 决策引擎拒借的进件时间（汇诚）. */
    private String timeRefuseDengine;
    /** 账户类型 0首次借款，1结清再借. */
    private String accountType;
    /** 神州融核验代码分值-不良信息. */
    private String badInfo;
    /** 共借人id. */ 
    private String coborrowerId;
    
    /** 简版信用卡信息. */
    private List<CrifFrontCreditSimpleInBean> creditSimpleList;
    /** 简版贷款信息. */
    private List<CrifFrontLoanSimpleInBean> loanSimpleList;
    /** 详版信用卡信息. */
    private List<CrifFrontCreditDetailInBean> creditDetailList;
    /** 详版贷款信息. */
    private List<CrifFrontLoanDetailInBean> loanDetailList;
    
    /**
     * 参数输出.
     * @return Bean中参数集合
     */
    public String getParam() {
        StringBuilder paramSb = new StringBuilder();
        paramSb.append("role=").append(role)
            .append(", nationality=").append(nationality)
            .append(", returnFlg=").append(returnFlg)
            .append(", age=").append(age)
            .append(", idNum=").append(idNum)
            .append(", numOfLoanIndustry=").append(numOfLoanIndustry)
            .append(", industry=").append(industry)
            .append(", houseProvince=").append(houseProvince)
            .append(", houseCity=").append(houseCity)
            .append(", province=").append(province)
            .append(", city=").append(city)
            .append(", overdue30CountFlg=").append(overdue30CountFlg)
            .append(", overdue6TimesL6=").append(overdue6TimesL6)
            .append(", relateOverdue30CountFlg=").append(relateOverdue30CountFlg)
            .append(", relateOverdue6TimesL6=").append(relateOverdue6TimesL6)
            .append(", totalIncome=").append(totalIncome)
            .append(", totalExpense=").append(totalExpense)
            .append(", monthlyRepaymentAbility=").append(monthlyRepaymentAbility)
            .append(", income=").append(income)
            .append(", familyIncome=").append(familyIncome)
            .append(", lastRejectDate=").append(lastRejectDate)
            .append(", unsetteledLoanC=").append(unsetteledLoanC)
            .append(", unsetteledLoan=").append(unsetteledLoan)
            .append(", employYear=").append(employYear)
            .append(", marriageStatus=").append(marriageStatus)
            .append(", parentsContactFlg=").append(parentsContactFlg)
            .append(", creditInquireTimesL2=").append(creditInquireTimesL2)
            .append(", creditInquireTimesOwn=").append(creditInquireTimesOwn)
            .append(", creditType=").append(creditType)
            .append(", stateOnthewayHc=").append(stateOnthewayHc)
            .append(", stateOnthewayKyj=").append(stateOnthewayKyj)
            .append(", timeRefuseLetterHc=").append(timeRefuseLetterHc)
            .append(", timeRefuseLetterKyj=").append(timeRefuseLetterKyj)
            .append(", timeRefuseStore=").append(timeRefuseStore)
            .append(", timeRefuseEngineHc=").append(timeRefuseEngineHc)
            .append(", timeRefuseEngineKyj=").append(timeRefuseEngineKyj)
            .append(", timeRefuseDengine=").append(timeRefuseDengine)
            .append(", accountType=").append(accountType)
            .append(", badInfo=").append(badInfo)
            .append(", coborrowerId=").append(coborrowerId)
            .append(", customerType=").append(customerType);
            
        paramSb.append(", creditSimpleList=[");
        if (creditSimpleList != null) {
            for (int i = 0; i < creditSimpleList.size(); i++) {
                paramSb.append("{");
                paramSb.append(creditSimpleList.get(i).getParam());
                paramSb.append("}");
            }
        }
        paramSb.append("]");
        
        paramSb.append(", loanSimpleList=[");
        if (loanSimpleList != null) {
            for (int i = 0; i < loanSimpleList.size(); i++) {
                paramSb.append("{");
                paramSb.append(loanSimpleList.get(i).getParam());
                paramSb.append("}");
            }
        }
        paramSb.append("]");
        
        paramSb.append(", creditDetailList=[");
        if (creditDetailList != null) {
            for (int i = 0; i < creditDetailList.size(); i++) {
                paramSb.append("{");
                paramSb.append(creditDetailList.get(i).getParam());
                paramSb.append("}");
            }
        }
        paramSb.append("]");
        
        paramSb.append(", loanDetailList=[");
        if (loanDetailList != null) {
            for (int i = 0; i < loanDetailList.size(); i++) {
                paramSb.append("{");
                paramSb.append(loanDetailList.get(i).getParam());
                paramSb.append("}");
            }
        }
        paramSb.append("]");
        return paramSb.toString();
    }

    /**
     * 个人信息-角色 的取得处理.
     *
     * @return 个人信息-角色
     */
    public String getRole() {
        return this.role;
    }

    /**
     * 个人信息-角色 的设定处理.
     *
     * @param role 个人信息-角色
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * 个人信息-国籍 的取得处理.
     *
     * @return 个人信息-国籍
     */
    public String getNationality() {
        return this.nationality;
    }

    /**
     * 个人信息-国籍 的设定处理.
     *
     * @param nationality 个人信息-国籍
     */
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    /**
     * 个人信息-是否退回后变更保证人 的取得处理.
     *
     * @return 个人信息-是否退回后变更保证人
     */
    public String getReturnFlg() {
        return this.returnFlg;
    }

    /**
     * 个人信息-是否退回后变更保证人 的设定处理.
     *
     * @param returnFlg 个人信息-是否退回后变更保证人
     */
    public void setReturnFlg(String returnFlg) {
        this.returnFlg = returnFlg;
    }

    /**
     * 个人信息-年龄 的取得处理.
     *
     * @return 个人信息-年龄
     */
    public String getAge() {
        return this.age;
    }

    /**
     * 个人信息-年龄 的设定处理.
     *
     * @param age 个人信息-年龄
     */
    public void setAge(String age) {
        this.age = age;
    }

    /**
     * 个人信息-身份证号码 的取得处理.
     *
     * @return 个人信息-身份证号码
     */
    public String getIdNum() {
        return this.idNum;
    }

    /**
     * 个人信息-身份证号码 的设定处理.
     *
     * @param idNum 个人信息-身份证号码
     */
    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    /**
     * 个人信息-同业借贷数量 的取得处理.
     *
     * @return 个人信息-同业借贷数量
     */
    public String getNumOfLoanIndustry() {
        return this.numOfLoanIndustry;
    }

    /**
     * 个人信息-同业借贷数量 的设定处理.
     *
     * @param numOfLoanIndustry 个人信息-同业借贷数量
     */
    public void setNumOfLoanIndustry(String numOfLoanIndustry) {
        this.numOfLoanIndustry = numOfLoanIndustry;
    }

    /**
     * 个人信息-行业 的取得处理.
     *
     * @return 个人信息-行业
     */
    public String getIndustry() {
        return this.industry;
    }

    /**
     * 个人信息-行业 的设定处理.
     *
     * @param industry 个人信息-行业
     */
    public void setIndustry(String industry) {
        this.industry = industry;
    }

    /**
     * 个人信息-户籍所在省 的取得处理.
     *
     * @return 个人信息-户籍所在省
     */
    public String getHouseProvince() {
        return this.houseProvince;
    }

    /**
     * 个人信息-户籍所在省 的设定处理.
     *
     * @param houseProvince 个人信息-户籍所在省
     */
    public void setHouseProvince(String houseProvince) {
        this.houseProvince = houseProvince;
    }

    /**
     * 个人信息-户籍城市 的取得处理.
     *
     * @return 个人信息-户籍城市
     */
    public String getHouseCity() {
        return this.houseCity;
    }

    /**
     * 个人信息-户籍城市 的设定处理.
     *
     * @param houseCity 个人信息-户籍城市
     */
    public void setHouseCity(String houseCity) {
        this.houseCity = houseCity;
    }

    /**
     * 个人信息-工作/居住所在省 的取得处理.
     *
     * @return 个人信息-工作/居住所在省
     */
    public String getProvince() {
        return this.province;
    }

    /**
     * 个人信息-工作/居住所在省 的设定处理.
     *
     * @param province 个人信息-工作/居住所在省
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 个人信息-工作/居住所在城市 的取得处理.
     *
     * @return 个人信息-工作/居住所在城市
     */
    public String getCity() {
        return this.city;
    }

    /**
     * 个人信息-工作/居住所在城市 的设定处理.
     *
     * @param city 个人信息-工作/居住所在城市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 个人信息-是否在我司累计逾期天数超过30天（不含） 的取得处理.
     *
     * @return 个人信息-是否在我司累计逾期天数超过30天（不含）
     */
    public String getOverdue30CountFlg() {
        return this.overdue30CountFlg;
    }

    /**
     * 个人信息-是否在我司累计逾期天数超过30天（不含） 的设定处理.
     *
     * @param overdue30CountFlg 个人信息-是否在我司累计逾期天数超过30天（不含）
     */
    public void setOverdue30CountFlg(String overdue30CountFlg) {
        this.overdue30CountFlg = overdue30CountFlg;
    }

    /**
     * 个人信息-近6个月逾期6天以上（不含）的次数 的取得处理.
     *
     * @return 个人信息-近6个月逾期6天以上（不含）的次数
     */
    public String getOverdue6TimesL6() {
        return this.overdue6TimesL6;
    }

    /**
     * 个人信息-近6个月逾期6天以上（不含）的次数 的设定处理.
     *
     * @param overdue6TimesL6 个人信息-近6个月逾期6天以上（不含）的次数
     */
    public void setOverdue6TimesL6(String overdue6TimesL6) {
        this.overdue6TimesL6 = overdue6TimesL6;
    }

    /**
     * 个人信息-关联的历史申请人逾期天数超过30天 的取得处理.
     *
     * @return 个人信息-关联的历史申请人逾期天数超过30天
     */
    public String getRelateOverdue30CountFlg() {
        return this.relateOverdue30CountFlg;
    }

    /**
     * 个人信息-关联的历史申请人逾期天数超过30天 的设定处理.
     *
     * @param relateOverdue30CountFlg 个人信息-关联的历史申请人逾期天数超过30天
     */
    public void setRelateOverdue30CountFlg(String relateOverdue30CountFlg) {
        this.relateOverdue30CountFlg = relateOverdue30CountFlg;
    }

    /**
     * 个人信息-关联的历史申请人逾期6天以上的次数 的取得处理.
     *
     * @return 个人信息-关联的历史申请人逾期6天以上的次数
     */
    public String getRelateOverdue6TimesL6() {
        return this.relateOverdue6TimesL6;
    }

    /**
     * 个人信息-关联的历史申请人逾期6天以上的次数 的设定处理.
     *
     * @param relateOverdue6TimesL6 个人信息-关联的历史申请人逾期6天以上的次数
     */
    public void setRelateOverdue6TimesL6(String relateOverdue6TimesL6) {
        this.relateOverdue6TimesL6 = relateOverdue6TimesL6;
    }

    /**
     * 个人信息-总收入 的取得处理.
     *
     * @return 个人信息-总收入
     */
    public String getTotalIncome() {
        return this.totalIncome;
    }

    /**
     * 个人信息-总收入 的设定处理.
     *
     * @param totalIncome 个人信息-总收入
     */
    public void setTotalIncome(String totalIncome) {
        this.totalIncome = totalIncome;
    }

    /**
     * 个人信息-总负债 的取得处理.
     *
     * @return 个人信息-总负债
     */
    public String getTotalExpense() {
        return this.totalExpense;
    }

    /**
     * 个人信息-总负债 的设定处理.
     *
     * @param totalExpense 个人信息-总负债
     */
    public void setTotalExpense(String totalExpense) {
        this.totalExpense = totalExpense;
    }

    /**
     * 个人信息-自述月还能力 的取得处理.
     *
     * @return 个人信息-自述月还能力
     */
    public String getMonthlyRepaymentAbility() {
        return this.monthlyRepaymentAbility;
    }

    /**
     * 个人信息-自述月还能力 的设定处理.
     *
     * @param monthlyRepaymentAbility 个人信息-自述月还能力
     */
    public void setMonthlyRepaymentAbility(String monthlyRepaymentAbility) {
        this.monthlyRepaymentAbility = monthlyRepaymentAbility;
    }

    /**
     * 个人信息-客户自述收入 的取得处理.
     *
     * @return 个人信息-客户自述收入
     */
    public String getIncome() {
        return this.income;
    }

    /**
     * 个人信息-客户自述收入 的设定处理.
     *
     * @param income 个人信息-客户自述收入
     */
    public void setIncome(String income) {
        this.income = income;
    }

    /**
     * 个人信息-家庭收入 的取得处理.
     *
     * @return 个人信息-家庭收入
     */
    public String getFamilyIncome() {
        return this.familyIncome;
    }

    /**
     * 个人信息-家庭收入 的设定处理.
     *
     * @param familyIncome 个人信息-家庭收入
     */
    public void setFamilyIncome(String familyIncome) {
        this.familyIncome = familyIncome;
    }

    /**
     * 个人信息-上次申请拒绝时间 的取得处理.
     *
     * @return 个人信息-上次申请拒绝时间
     */
    public String getLastRejectDate() {
        return this.lastRejectDate;
    }

    /**
     * 个人信息-上次申请拒绝时间 的设定处理.
     *
     * @param lastRejectDate 个人信息-上次申请拒绝时间
     */
    public void setLastRejectDate(String lastRejectDate) {
        this.lastRejectDate = lastRejectDate;
    }

    /**
     * 个人信息-是否为他人的保证人在我司已有借款尚未结清 的取得处理.
     *
     * @return 个人信息-是否为他人的保证人在我司已有借款尚未结清
     */
    public String getUnsetteledLoanC() {
        return this.unsetteledLoanC;
    }

    /**
     * 个人信息-是否为他人的保证人在我司已有借款尚未结清 的设定处理.
     *
     * @param unsetteledLoanC 个人信息-是否为他人的保证人在我司已有借款尚未结清
     */
    public void setUnsetteledLoanC(String unsetteledLoanC) {
        this.unsetteledLoanC = unsetteledLoanC;
    }

    /**
     * 个人信息-是否在我司已有借款尚未结清（包含房借、车借) 的取得处理.
     *
     * @return 个人信息-是否在我司已有借款尚未结清（包含房借、车借)
     */
    public String getUnsetteledLoan() {
        return this.unsetteledLoan;
    }

    /**
     * 个人信息-是否在我司已有借款尚未结清（包含房借、车借) 的设定处理.
     *
     * @param unsetteledLoan 个人信息-是否在我司已有借款尚未结清（包含房借、车借)
     */
    public void setUnsetteledLoan(String unsetteledLoan) {
        this.unsetteledLoan = unsetteledLoan;
    }

    /**
     * 个人信息-在此单位工作年限 的取得处理.
     *
     * @return 个人信息-在此单位工作年限
     */
    public String getEmployYear() {
        return this.employYear;
    }

    /**
     * 个人信息-在此单位工作年限 的设定处理.
     *
     * @param employYear 个人信息-在此单位工作年限
     */
    public void setEmployYear(String employYear) {
        this.employYear = employYear;
    }

    /**
     * 个人信息-婚姻状况 的取得处理.
     *
     * @return 个人信息-婚姻状况
     */
    public String getMarriageStatus() {
        return this.marriageStatus;
    }

    /**
     * 个人信息-婚姻状况 的设定处理.
     *
     * @param marriageStatus 个人信息-婚姻状况
     */
    public void setMarriageStatus(String marriageStatus) {
        this.marriageStatus = marriageStatus;
    }

    /**
     * 个人信息-有无父母联系方式 的取得处理.
     *
     * @return 个人信息-有无父母联系方式
     */
    public String getParentsContactFlg() {
        return this.parentsContactFlg;
    }

    /**
     * 个人信息-有无父母联系方式 的设定处理.
     *
     * @param parentsContactFlg 个人信息-有无父母联系方式
     */
    public void setParentsContactFlg(String parentsContactFlg) {
        this.parentsContactFlg = parentsContactFlg;
    }

    /**
     * 个人信息-最近2个月贷款审批或信用卡审批征信查询次数 的取得处理.
     *
     * @return 个人信息-最近2个月贷款审批或信用卡审批征信查询次数
     */
    public String getCreditInquireTimesL2() {
        return this.creditInquireTimesL2;
    }

    /**
     * 个人信息-最近2个月贷款审批或信用卡审批征信查询次数 的设定处理.
     *
     * @param creditInquireTimesL2 个人信息-最近2个月贷款审批或信用卡审批征信查询次数
     */
    public void setCreditInquireTimesL2(String creditInquireTimesL2) {
        this.creditInquireTimesL2 = creditInquireTimesL2;
    }

    /**
     * 个人信息-本人征信查询次数 的取得处理.
     *
     * @return 个人信息-本人征信查询次数
     */
    public String getCreditInquireTimesOwn() {
        return this.creditInquireTimesOwn;
    }

    /**
     * 个人信息-本人征信查询次数 的设定处理.
     *
     * @param creditInquireTimesOwn 个人信息-本人征信查询次数
     */
    public void setCreditInquireTimesOwn(String creditInquireTimesOwn) {
        this.creditInquireTimesOwn = creditInquireTimesOwn;
    }

    /**
     * 个人信息-征信信息类型 的取得处理.
     *
     * @return 个人信息-征信信息类型
     */
    public String getCreditType() {
        return this.creditType;
    }

    /**
     * 个人信息-征信信息类型 的设定处理.
     *
     * @param creditType 个人信息-征信信息类型
     */
    public void setCreditType(String creditType) {
        this.creditType = creditType;
    }

    /**
     * 个人信息-征信报告日期(YYYY-MM-DD) 的取得处理.
     *
     * @return 个人信息-征信报告日期(YYYY-MM-DD)
     */
    public String getCreditDate() {
        return this.creditDate;
    }

    /**
     * 个人信息-征信报告日期(YYYY-MM-DD) 的设定处理.
     *
     * @param creditDate 个人信息-征信报告日期(YYYY-MM-DD)
     */
    public void setCreditDate(String creditDate) {
        this.creditDate = creditDate;
    }
    
    /**
     * 个人信息-征信是否空白 的取得处理.
     *
     * @return 个人信息-征信是否空白
     */
    public String getCreditFlg() {
        return this.creditFlg;
    }

    /**
     * 个人信息-征信是否空白 的设定处理.
     *
     * @param creditFlg 个人信息-征信是否空白
     */
    public void setCreditFlg(String creditFlg) {
        this.creditFlg = creditFlg;
    }

    /**
     * 个人信息-客户类型(1:工薪类;0：经营类) 的取得处理.
     * @return 个人信息-客户类型(1:工薪类;0：经营类)
     */
    public String getCustomerType() {
        return customerType;
    }

    /**
     * 个人信息-客户类型(1:工薪类;0：经营类) 的设定处理.
     * @param customerType个人信息-客户类型(1:工薪类;0：经营类)
     */
    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }
    
    /**
     * 汇诚借款状态是否命中在途 的取得处理.
     *
     * @return 汇诚借款状态是否命中在途
     */
    public String getStateOnthewayHc() {
        return this.stateOnthewayHc;
    }

    /**
     * 汇诚借款状态是否命中在途 的设定处理.
     *
     * @param stateOnthewayHc 汇诚借款状态是否命中在途
     */
    public void setStateOnthewayHc(String stateOnthewayHc) {
        this.stateOnthewayHc = stateOnthewayHc;
    }

    /**
     * 快易借借款状态是否命中在途 的取得处理.
     *
     * @return 快易借借款状态是否命中在途
     */
    public String getStateOnthewayKyj() {
        return this.stateOnthewayKyj;
    }

    /**
     * 快易借借款状态是否命中在途 的设定处理.
     *
     * @param stateOnthewayKyj 快易借借款状态是否命中在途
     */
    public void setStateOnthewayKyj(String stateOnthewayKyj) {
        this.stateOnthewayKyj = stateOnthewayKyj;
    }

    /**
     * 信审拒借的进件时间（汇诚） 的取得处理.
     *
     * @return 信审拒借的进件时间（汇诚）
     */
    public String getTimeRefuseLetterHc() {
        return this.timeRefuseLetterHc;
    }

    /**
     * 信审拒借的进件时间（汇诚） 的设定处理.
     *
     * @param timeRefuseLetterHc 信审拒借的进件时间（汇诚）
     */
    public void setTimeRefuseLetterHc(String timeRefuseLetterHc) {
        this.timeRefuseLetterHc = timeRefuseLetterHc;
    }

    /**
     * 信审拒借的进件时间（快易借） 的取得处理.
     *
     * @return 信审拒借的进件时间（快易借）
     */
    public String getTimeRefuseLetterKyj() {
        return this.timeRefuseLetterKyj;
    }

    /**
     * 信审拒借的进件时间（快易借） 的设定处理.
     *
     * @param timeRefuseLetterKyj 信审拒借的进件时间（快易借）
     */
    public void setTimeRefuseLetterKyj(String timeRefuseLetterKyj) {
        this.timeRefuseLetterKyj = timeRefuseLetterKyj;
    }

    /**
     * 门店拒借的进件时间（汇诚） 的取得处理.
     *
     * @return 门店拒借的进件时间（汇诚）
     */
    public String getTimeRefuseStore() {
        return this.timeRefuseStore;
    }

    /**
     * 门店拒借的进件时间（汇诚） 的设定处理.
     *
     * @param timeRefuseStore 门店拒借的进件时间（汇诚）
     */
    public void setTimeRefuseStore(String timeRefuseStore) {
        this.timeRefuseStore = timeRefuseStore;
    }

    /**
     * 规则引擎拒借的进件时间（汇诚） 的取得处理.
     *
     * @return 规则引擎拒借的进件时间（汇诚）
     */
    public String getTimeRefuseEngineHc() {
        return this.timeRefuseEngineHc;
    }

    /**
     * 规则引擎拒借的进件时间（汇诚） 的设定处理.
     *
     * @param timeRefuseEngineHc 规则引擎拒借的进件时间（汇诚）
     */
    public void setTimeRefuseEngineHc(String timeRefuseEngineHc) {
        this.timeRefuseEngineHc = timeRefuseEngineHc;
    }

    /**
     * 规则引擎拒借的进件时间（快易借） 的取得处理.
     *
     * @return 规则引擎拒借的进件时间（快易借）
     */
    public String getTimeRefuseEngineKyj() {
        return this.timeRefuseEngineKyj;
    }

    /**
     * 规则引擎拒借的进件时间（快易借） 的设定处理.
     *
     * @param timeRefuseEngineKyj 规则引擎拒借的进件时间（快易借）
     */
    public void setTimeRefuseEngineKyj(String timeRefuseEngineKyj) {
        this.timeRefuseEngineKyj = timeRefuseEngineKyj;
    }

    /**
     * 决策引擎拒借的进件时间（汇诚） 的取得处理.
     *
     * @return 决策引擎拒借的进件时间（汇诚）
     */
    public String getTimeRefuseDengine() {
        return this.timeRefuseDengine;
    }

    /**
     * 决策引擎拒借的进件时间（汇诚） 的设定处理.
     *
     * @param timeRefuseDengine 决策引擎拒借的进件时间（汇诚）
     */
    public void setTimeRefuseDengine(String timeRefuseDengine) {
        this.timeRefuseDengine = timeRefuseDengine;
    }
    
    /**
     * 账户类型 0首次借款，1结清再借 的取得处理.
     *
     * @return 账户类型 0首次借款，1结清再借
     */
    public String getAccountType() {
        return this.accountType;
    }

    /**
     * 账户类型 0首次借款，1结清再借 的设定处理.
     *
     * @param accountType 账户类型 0首次借款，1结清再借
     */
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    /**
     * 神州融核验代码分值-不良信息 的取得处理.
     *
     * @return 神州融核验代码分值-不良信息
     */
    public String getBadInfo() {
		return badInfo;
	}

    /**
     * 神州融核验代码分值-不良信息 的设定处理.
     *
     * @param priskCode 神州融核验代码分值-不良信息
     */
    public void setBadInfo(String badInfo) {
		this.badInfo = badInfo;
	}
    
    /**
     * 共借人id 的设定处理.
     *
     * @param coborrowerId 共借人id
//     */
    public String getCoborrowerId() {
		return coborrowerId;
	}

    /**
     * 共借人id 的设定处理.
     *
     * @param coborrowerId 共借人id
     */
	public void setCoborrowerId(String coborrowerId) {
		this.coborrowerId = coborrowerId;
	}
    
    /**
     * 简版信用卡信息 的取得处理.
     * @return 简版信用卡信息
     */
    public List<CrifFrontCreditSimpleInBean> getCreditSimpleList() {
        return creditSimpleList;
    }

    /**
     * 简版信用卡信息 的设定处理.
     * @param creditSimpleList 简版信用卡信息
     */
    public void setCreditSimpleList(List<CrifFrontCreditSimpleInBean> creditSimpleList) {
        this.creditSimpleList = creditSimpleList;
    }

    /**
     * 简版贷款信息 的取得处理.
     * @return 简版贷款信息
     */
    public List<CrifFrontLoanSimpleInBean> getLoanSimpleList() {
        return loanSimpleList;
    }

    /**
     * 简版贷款信息 的设定处理.
     * @param loanSimpleList 简版贷款信息
     */
    public void setLoanSimpleList(List<CrifFrontLoanSimpleInBean> loanSimpleList) {
        this.loanSimpleList = loanSimpleList;
    }

    /**
     * 详版信用卡信息 的取得处理.
     * @return 详版信用卡信息
     */
    public List<CrifFrontCreditDetailInBean> getCreditDetailList() {
        return creditDetailList;
    }

    /**
     * 详版信用卡信息 的设定处理.
     * @param creditDetailList 详版信用卡信息
     */
    public void setCreditDetailList(List<CrifFrontCreditDetailInBean> creditDetailList) {
        this.creditDetailList = creditDetailList;
    }

    /**
     * 详版贷款信息 的取得处理.
     * @return 详版贷款信息
     */
    public List<CrifFrontLoanDetailInBean> getLoanDetailList() {
        return loanDetailList;
    }

    /**
     * 详版贷款信息 的设定处理.
     * @param loanDetailList 详版贷款信息
     */
    public void setLoanDetailList(List<CrifFrontLoanDetailInBean> loanDetailList) {
        this.loanDetailList = loanDetailList;
    }
}
