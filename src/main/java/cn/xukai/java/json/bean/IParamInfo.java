package cn.xukai.java.json.bean;

/**
 * 服务之间传递参数对象接口.
 * <p>共同接口通过调用此接口， 导出Bean中参数信息，记录到接口模块中</p>
 * @Class Name GeneralInfo
 * @author CHP_3.0 接口组
 * @Create In 2015年12月24日
 */
public interface IParamInfo {

    /**
     * 参数输出.
     * @return Bean中参数集合
     */
    public String getParam();
}
