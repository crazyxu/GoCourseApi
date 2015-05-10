package net.gocourse.api.bean;

import java.util.Map;

/**
 * Created by 徐灿 on 2015/5/10
 * Contact 384454501@qq.com
 * Introduce 用户信息
 */
public class UserInfo {
    //用户系统iD
    private int id;

    //用户类型
    private String user_type;

    //真实名
    private String name;

    //默认头像地址
    private String avatar;

    //更多头像
    private Map<String,Object> avatar_more;

    //学号
    private String uid;

    //email
    private String email;

    //性别
    private String sex;

    //性别
    private int sex_flag;

    //描述
    private String description;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Map<String,Object> getAvatar_more() {
        return avatar_more;
    }

    public void setAvatar_more(Map<String,Object> avatar_more) {
        this.avatar_more = avatar_more;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getSex_flag() {
        return sex_flag;
    }

    public void setSex_flag(int sex_flag) {
        this.sex_flag = sex_flag;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }
}


