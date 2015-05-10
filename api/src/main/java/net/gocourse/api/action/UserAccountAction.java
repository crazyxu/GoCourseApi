package net.gocourse.api.action;

import android.util.Log;

import net.gocourse.api.bean.ActionResEntity;
import net.gocourse.api.bean.JsonBean;
import net.gocourse.api.bean.VerifyJsonBean;
import net.gocourse.api.imp.UserAccountInterface;
import net.gocourse.api.server.UserAccountServer;
import net.gocourse.api.util.JsonHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 徐灿 on 2015/5/4
 * Contact 384454501@qq.com
 * Introduce 用户管理相关操作
 */
public class UserAccountAction implements UserAccountInterface {
    //传递给移动端数据对象
    private ActionResEntity resEntity;

    //构造函数，初始化ActionResEntity
    public UserAccountAction(){
        resEntity=new ActionResEntity();
    }

    /**
     * 登录
     * @param email
     * @param pwd
     * @return
     */
    @Override
    public ActionResEntity userLogin(String email, String pwd) {
        try{
            //从服务器获取的json格式字符串
            String jsonStr= UserAccountServer.userLogin(email, pwd);
            //实例化JsonBean
            JsonBean jsonBean=new JsonBean();
            //获取数据
            JsonHelper.jsonStrToBean(jsonBean, jsonStr);
            //判断是否登录成功
            if (jsonBean.getStatus()){
                resEntity.setResState(true);
                //将jsonBean中的data（JsonObject）再次解析为VerifyJsonBean对象
                VerifyJsonBean verifyJsonBean=new VerifyJsonBean();
                JsonHelper.jsonStrToBean(verifyJsonBean, jsonBean.getData().toString());
                Map<String,Object> map=new HashMap<String,Object>();
                //添加token和expire数据值
                map.put("token",verifyJsonBean.getToken());
                map.put("expire",verifyJsonBean.getExpire());
                resEntity.setResData(map);
            }else{
                //失败
                resEntity.setResState(false);
                resEntity.setErrInfo(jsonBean.getMsg());
            }
            return resEntity;
        }catch (Exception e){
            Log.e("UserManager->userLogin", e.toString());
        }
        //执行到此，表示程序异常
        resEntity.setResState(false);
        resEntity.setErrInfo("系统异常");
        return resEntity;
    }

    /**
     * 注册
     * @param userName
     * @param pwd
     * @return
     */
    @Override
    public ActionResEntity userReg(String userName, String pwd) {
        //服务器返回的json格式字符串
        String jsonStr= UserAccountServer.userReg(userName, pwd);
        try{
            //实例化JsonBean
            JsonBean jsonBean=new JsonBean();
            //获取数据
            JsonHelper.jsonStrToBean(jsonBean,jsonStr);
            //判断是否登录成功
            if (jsonBean.getStatus()){
                resEntity.setResState(true);
            }else{
                //失败
                resEntity.setResState(false);
                resEntity.setErrInfo(jsonBean.getMsg());
            }
             return resEntity;

        }catch (Exception e){
            Log.e("UserManager->userReg",e.toString());
        }
        //执行到此，表示程序异常
        resEntity.setResState(false);
        resEntity.setErrInfo("系统异常");
        return resEntity;
    }

    /**
     * 修改密码
     * @param token
     * @param oldPwd
     * @param newPwd
     * @return
     */
    @Override
    public ActionResEntity changPwd(String token, String oldPwd, String newPwd) {
        //服务器返回的json格式字符串
        String jsonStr= UserAccountServer.changPwd(token, oldPwd, newPwd);
        try{
            //实例化JsonBean
            JsonBean jsonBean=new JsonBean();
            //获取数据
            JsonHelper.jsonStrToBean(jsonBean,jsonStr);
            //判断是否登录成功
            if (jsonBean.getStatus()){
                resEntity.setResState(true);
                //将jsonBean中的data（JsonObject）再次解析为VerifyJsonBean对象
                VerifyJsonBean verifyJsonBean=new VerifyJsonBean();
                JsonHelper.jsonStrToBean(verifyJsonBean, jsonBean.getData().toString());
                Map<String,Object> map=new HashMap<String,Object>();
                //添加token和expire数据值
                map.put("token",verifyJsonBean.getToken());
                map.put("expire",verifyJsonBean.getExpire());
                resEntity.setResData(map);
            }else{
                resEntity.setResState(false);
                resEntity.setErrInfo(jsonBean.getMsg());
            }
            return resEntity;
        }catch (Exception e){
            Log.e("UserManager->changPwd",e.toString());
        }
        //执行到此，表示程序异常
        resEntity.setResState(false);
        resEntity.setErrInfo("系统异常");
        return resEntity;
    }

    //注销
    @Override
    public ActionResEntity userLogout(String token) {
        try{
            //从服务器获取的json格式字符串
            String jsonStr= UserAccountServer.userLogout(token);
            //实例化JsonBean
            JsonBean jsonBean=new JsonBean();
            //获取数据
            JsonHelper.jsonStrToBean(jsonBean, jsonStr);
            //判断是否登录成功
            if (jsonBean.getStatus()){
                resEntity.setResState(true);
            }else{
                //失败
                resEntity.setResState(false);
                resEntity.setErrInfo(jsonBean.getMsg());
            }
            return resEntity;
        }catch (Exception e){
            Log.e("UserManager->userLogin", e.toString());
        }
        //执行到此，表示程序异常
        resEntity.setResState(false);
        resEntity.setErrInfo("系统异常");
        return resEntity;
    }
}
