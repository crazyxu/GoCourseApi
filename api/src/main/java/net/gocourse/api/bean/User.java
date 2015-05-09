package net.gocourse.api.bean;

/**
 * Created by 徐灿 on 2015/5/5
 * Contact 384454501@qq.com
 * Introduce 用户
 */
public class User {
    //用户系统iD
    private int sysId;

    //用户类型Constant.arrUserType
    private int userType;

    //真实名
    private String realName;

    //学号
    private int uNo;

    //email
    private String email;

    //性别
    private int sex;

    //描述
    private String description;

    //头像地址
    private String avatar;

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getSysId() {
        return sysId;
    }

    public void setSysId(int sysId) {
        this.sysId = sysId;
    }

    public int getUno() {
        return uNo;
    }

    public void setUno(int uNo) {
        this.uNo = uNo;
    }

}
