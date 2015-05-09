package net.gocourse.api.bean;

/**
 * Created by 徐灿 on 2015/5/9
 * Contact 384454501@qq.com
 * Introduce 用于用户验证的jsonBean
 */
public class VerifyJsonBean {
    //用户在线标识
    private String token;
    //用户登录状态有效期
    private int expire;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }

}
