package net.gocourse.test;

import android.app.Application;

/**
 * Created by 徐灿 on 2015/5/7
 * Contact 384454501@qq.com
 * Introduce MyApplication
 */
public class MyApplication extends Application {
    //token
    private String token;

    //expire
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

    public String getBarTitle() {
        return barTitle;
    }

    public void setBarTitle(String barTitle) {
        this.barTitle = barTitle;
    }

    private String barTitle;


    @Override
    public void onCreate() {
        super.onCreate();
    }
}
