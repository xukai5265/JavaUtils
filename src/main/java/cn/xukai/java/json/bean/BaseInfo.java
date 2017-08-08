package cn.xukai.java.json.bean;

import java.io.Serializable;

/**
 * Service传递Bean通用父类.
 * @Class Name BaseInfo
 * @author CHP_3.0 接口组
 * @Create In 2015年12月24日
 */
public class BaseInfo implements Serializable {
    /** 序列化. */
    private static final long serialVersionUID = 6173745208876850215L;

    /** 序列编号. */
    private String serialNum;
    
    /**
     * 构造方法.
     */
    public BaseInfo() {
        
    }

    /**
     * 序列编号 的取得处理.
     * @return 序列编号
     */
    public String getSerialNum() {
        return serialNum;
    }

    /**
     * 序列编号 的设定处理.
     * @param serialNum 序列编号
     */
    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }
}
