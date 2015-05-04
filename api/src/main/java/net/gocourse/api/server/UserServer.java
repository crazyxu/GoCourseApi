package net.gocourse.api.server;

import android.util.Log;

import net.gocourse.api.bean.HttpResEntity;
import net.gocourse.api.util.HttpReqUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 徐灿 on 2015/5/4
 * Contact 384454501@qq.com
 * Introduce
 */
public class UserServer {
    //注册
    public static String userReg(String email,String pwd){
        String url="http://loveyu.website/gocourse/user_action/register";
        Map<String,Object> params=new HashMap<String, Object>();
        params.put("email","384454501@qq.com");
        params.put("password","123456");
        HttpResEntity resEntity= HttpReqUtil.doPost(url, params);
        if (resEntity.isReqState()){
            return resEntity.getReqInfo();
        }else{
            Log.i("UserServer/userReg", resEntity.getErrInfo());
        }
        return null;
    }

    //登录
    public static String userLogin(String username,String pwd){
        String url="http://loveyu.website/gocourse/user_action/login";
        Map<String,Object> params=new HashMap<String, Object>();
        params.put("username","384454501@qq.com");
        params.put("password","123456");
        HttpResEntity resEntity=HttpReqUtil.doPost(url, params);
        if (resEntity.isReqState()){
            return resEntity.getReqInfo();
        }else{
            Log.i("UserServer/userLogin",resEntity.getErrInfo());
        }
        return null;
    }
}
