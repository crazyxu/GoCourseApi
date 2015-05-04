package net.gocourse.api.bean;

/**
 * Created by 徐灿 on 2015/5/4
 * Contact 384454501@qq.com
 * Introduce 网络请求处理结果
 */
public class HttpResEntity {
    //操作是否成功
    private boolean reqState;

    //操作失败，错误信息
    private String errInfo;

    //操作成功，结果内容
    private String reqInfo;
    public boolean isReqState() {
        return reqState;
    }

    public void setReqState(boolean reqState) {
        this.reqState = reqState;
    }

    public String getErrInfo() {
        return errInfo;
    }

    public void setErrInfo(String errInfo) {
        this.errInfo = errInfo;
    }

    public String getReqInfo() {
        return reqInfo;
    }

    public void setReqInfo(String reqInfo) {
        this.reqInfo = reqInfo;
    }


}
