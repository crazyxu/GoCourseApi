package net.gocourse.api.imp;

import net.gocourse.api.bean.ActionResEntity;

/**
 * Created by 徐灿 on 2015/5/4
 * Contact 384454501@qq.com
 * Introduce 用户管理接口
 */
public interface UserManagerInterface {
    //用户注册
    public ActionResEntity userReg(String email,String pwd);

    //用户登录
    public ActionResEntity userLogin(String userName,String pwd);
}
