package net.gocourse.api.server;

import android.util.Log;

import net.gocourse.api.bean.HttpResEntity;
import net.gocourse.api.util.Constant;
import net.gocourse.api.util.HttpReqUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 徐灿 on 2015/5/4
 * Contact 384454501@qq.com
 * Introduce 用户账号管理：注册，登录，注销，重置密码，忘记密码
 */
public class UserAccountServer {
    //注册
    public static String userReg(String email,String pwd){
        String url=Constant.ACTION_USER_REG;
        Map<String,Object> params=new HashMap<String, Object>();
        params.put("email",email);
        params.put("password",pwd);
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
        String url= Constant.ACTION_USER_LOGIN;
        Map<String,Object> params=new HashMap<String, Object>();
        params.put("username",username);
        params.put("password",pwd);
        params.put("client",Constant.CLIENT_TYPE);
        HttpResEntity resEntity=HttpReqUtil.doPost(url, params);
        if (resEntity.isReqState()){
            return resEntity.getReqInfo();
        }else{
            Log.i("UserServer/userLogin",resEntity.getErrInfo());
        }
        return null;
    }

    //修改密码
    public static String changPwd(String token,String oldPwd,String newPwd){
        String url=Constant.ACTION_USER_CHANG_PWD;
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("old_pwd",oldPwd);
        params.put("new_pwd",newPwd);
        params.put("__token",token);
        HttpResEntity resEntity=HttpReqUtil.doPost(url,params);
        if (resEntity.isReqState()){
            return resEntity.getReqInfo();
        }else{
            Log.i("UserServer/changPwd",resEntity.getErrInfo());
        }
        return null;
    }

    //注销
    public static String userLogout(String token){
        String url=Constant.ACTION_USER_LOGOUT;
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("__token",token);
        HttpResEntity resEntity=HttpReqUtil.doPost(url,params);
        if (resEntity.isReqState()){
            return resEntity.getReqInfo();
        }else{
            Log.i("UserServer/userLogout",resEntity.getErrInfo());
        }
        return null;
    }


}
