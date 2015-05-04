package net.gocourse.api.action;

import android.util.Log;

import net.gocourse.api.bean.ActionResEntity;
import net.gocourse.api.imp.UserManagerInterface;
import net.gocourse.api.server.UserServer;
import net.gocourse.api.util.HttpReqUtil;
import net.gocourse.api.util.JsonHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 徐灿 on 2015/5/4
 * Contact 384454501@qq.com
 * Introduce 用户管理相关操作
 */
public class UserManager implements UserManagerInterface {
    private ActionResEntity resEntity;
    //构造函数，初始化ActionResEntity
    public UserManager(){
        resEntity=new ActionResEntity();
    }
    @Override
    public ActionResEntity userReg(String email, String pwd) {
        //从服务器获取的json格式字符串
        String jsonStr= UserServer.userReg(email,pwd);
        try{
            //将json格式字符串转化成Map格式
            Map<String,Object> jsonData=JsonHelper.jsonStrToMap(jsonStr);
            if (jsonData!=null){
                //获取用户操作状态
                boolean status=(boolean)jsonData.get("status");
                if (status){
                    //登录成功
                    resEntity.setResState(true);
                    Map<String,Object> data=new HashMap<String,Object>();
                    //token：用户验证标识
                    data.put("token",jsonData.get("token"));
                    //expire：用户在线状态有效期
                    data.put("expire",jsonData.get("expire"));
                    resEntity.setData(data);
                }else{
                    //登录失败
                    resEntity.setResState(false);
                    resEntity.setErrInfo((String)jsonData.get("msg"));
                }
                return resEntity;
            }
        }catch (Exception e){
            Log.e("UserManager->userReg", e.toString());
        }
        //执行到此，表示程序异常
        resEntity.setResState(false);
        resEntity.setErrInfo("系统异常");
        return resEntity;
    }

    @Override
    public ActionResEntity userLogin(String userName, String pwd) {
        //服务器返回的json格式字符串
        String jsonStr=UserServer.userLogin(userName,pwd);
        try{
            //将json格式字符串转化成Map格式
            Map<String,Object> jsonData=JsonHelper.jsonStrToMap(jsonStr);
            if (jsonData!=null) {
                //获取用户操作状态
                boolean status = (boolean) jsonData.get("status");
                if (status) {
                    //注册成功
                    resEntity.setResState(true);
                }else {
                    //注册失败
                    resEntity.setResState(false);
                    resEntity.setErrInfo((String)jsonData.get("msg"));
                }
                //结束函数
                return resEntity;
            }
        }catch (Exception e){
            Log.e("UserManager->userLogin",e.toString());
        }
        //执行到此，表示程序异常
        resEntity.setResState(false);
        resEntity.setErrInfo("系统异常");
        return resEntity;
    }
}
