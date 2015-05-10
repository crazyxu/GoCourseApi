package net.gocourse.api.util;

/**
 * Created by 徐灿 on 2015/5/5
 * Contact 384454501@qq.com
 * Introduce
 */
public class Constant {
    //用户类型
    public static String[] arrUserType=new String[]{"学生","教师","管理员"};

    //性别
    public static String[] arrUserSex=new String[]{"男","女"};

    ///action url
    //登录
    public final static String ACTION_USER_LOGIN="http://loveyu.website/gocourse/user_action/login";
    //注册
    public final static String ACTION_USER_REG="http://loveyu.website/gocourse/user_action/register";
    //改密
    public final static String ACTION_USER_CHANG_PWD="http://loveyu.website/gocourse/user/change_password";
    //注销
    public final static String ACTION_USER_LOGOUT="http://loveyu.website/gocourse/user_action/logout";

    //获取个人信息
    public final static String ACTION_USER_INFO="http://loveyu.website/gocourse/user/info";

    public final static String CLIENT_TYPE="android";
}
