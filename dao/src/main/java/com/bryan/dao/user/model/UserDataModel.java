package com.bryan.dao.user.model;

/**
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/3/1
 */
public class UserDataModel {

    /**
     * 用户id
     */
    private Integer id;

    /**
     * 认证名称
     */
    private String authName;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户类型:01个人;02企业
     */
    private String utype;

    /**
     * 状态:10正常,20注销,30冻结
     */
    private String state;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUtype() {
        return utype;
    }

    public void setUtype(String utype) {
        this.utype = utype;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
