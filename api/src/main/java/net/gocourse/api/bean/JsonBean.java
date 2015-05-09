package net.gocourse.api.bean;

import org.json.JSONObject;

/**
 * Created by 徐灿 on 2015/5/9
 * Contact 384454501@qq.com
 * Introduce 与服务器接收json数据相匹配
 */
public class JsonBean {
    //当前操作状态，true或者false，只有当操作成功时才返回true
    private boolean status;

    //当前状态码，0为正常状态，
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    //服务器在异常状态下返回的消息提示，一般为错误提示
    private String msg;

    //所请求的数据，部分数据请求可能无此数据，返回NULL
    //此json数据再次解析
    private JSONObject data;

}
