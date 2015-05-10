package net.gocourse.api.action;

import android.util.Log;

import net.gocourse.api.bean.ActionResEntity;
import net.gocourse.api.bean.JsonBean;
import net.gocourse.api.bean.UserInfo;
import net.gocourse.api.bean.VerifyJsonBean;
import net.gocourse.api.imp.UserAccountInterface;
import net.gocourse.api.imp.UserInfoInterface;
import net.gocourse.api.server.UserAccountServer;
import net.gocourse.api.server.UserInfoServer;
import net.gocourse.api.util.JsonHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 徐灿 on 2015/5/10
 * Contact 384454501@qq.com
 * Introduce 用户信息操作
 */
public class UserInfoAction implements UserInfoInterface {
    //传递给移动端数据对象
    private ActionResEntity resEntity;

    //构造函数，初始化ActionResEntity
    public UserInfoAction(){
        resEntity=new ActionResEntity();
    }
    @Override
    public ActionResEntity getUserInfo(String token) {
        try{
            //从服务器获取的json格式字符串
            String jsonStr= UserInfoServer.getUserInfo(token);
            //实例化JsonBean
            JsonBean jsonBean=new JsonBean();
            //获取数据
            JsonHelper.jsonStrToBean(jsonBean, jsonStr);
            //判断是否获取成功
            if (jsonBean.getStatus()){
                resEntity.setResState(true);
                //将jsonBean中的data（JsonObject）再次解析为VerifyJsonBean对象
                UserInfo userInfo=new UserInfo();
                JsonHelper.jsonStrToBean(userInfo, jsonBean.getData().toString());
                //将UserInfo对象存在data中
                Map<String,Object> map=new HashMap<String,Object>();
                //添加token和expire数据值
                map.put("userInfo",userInfo);
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
}
