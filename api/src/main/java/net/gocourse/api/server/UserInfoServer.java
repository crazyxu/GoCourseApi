package net.gocourse.api.server;

import android.util.Log;

import net.gocourse.api.bean.HttpResEntity;
import net.gocourse.api.util.Constant;
import net.gocourse.api.util.HttpReqUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 徐灿 on 2015/5/10
 * Contact 384454501@qq.com
 * Introduce 用户信息管理：获取个人信息，完善信息
 */
public class UserInfoServer {
    /**
     * 获取个人信息
     * @param token
     * @return
     */
    public static String getUserInfo(String token){
        String url= Constant.ACTION_USER_INFO;
        Map<String,Object> params=new HashMap<String, Object>();
        params.put("__token",token);
        HttpResEntity resEntity= HttpReqUtil.doPost(url, params);
        if (resEntity.isReqState()){
            return resEntity.getReqInfo();
        }else{
            Log.i("UserServer/userInfo", resEntity.getErrInfo());
        }
        return null;
    }
}
