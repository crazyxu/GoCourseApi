package net.gocourse.api.imp;

import net.gocourse.api.bean.ActionResEntity;

/**
 * Created by 徐灿 on 2015/5/4
 * Contact 384454501@qq.com
 * Introduce 用户管理接口
 */
public interface UserInfoInterface {
    //获取个人信息
    public  ActionResEntity getUserInfo(String token);

}
