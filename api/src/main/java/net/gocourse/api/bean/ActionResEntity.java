package net.gocourse.api.bean;

import java.util.Map;

/**
 * Created by 徐灿 on 2015/5/4
 * Contact 384454501@qq.com
 * Introduce 客户端请求结果返回实例
 */
public class ActionResEntity {
    //请求是否成功
    private boolean resState;

    //请求错误信息
    private String errInfo;

    //请求成功数据
    private Map<String,Object> resData;

    public boolean isResState() {
        return resState;
    }

    public void setResState(boolean resState) {
        this.resState = resState;
    }

    public String getErrInfo() {
        return errInfo;
    }

    public void setErrInfo(String errInfo) {
        this.errInfo = errInfo;
    }

    public Map<String, Object> getResData() {
        return resData;
    }

    public void setResData(Map<String, Object> resData) {
        this.resData = resData;
    }

    @Override
    public String toString() {
        return "ActionResEntity{" + ";\n" +
                "errInfo=" + errInfo + ";\n" +
                "resState=" + resState + ";\n" +
                "resData=" + resData + ";\n" +
                '}';
    }
}
