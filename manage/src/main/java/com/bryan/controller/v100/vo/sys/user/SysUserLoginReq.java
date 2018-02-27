package com.bryan.controller.v100.vo.sys.user;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * ClassName: DoLoginReq
 * Function: 登录请求
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/27
 */
public class SysUserLoginReq {
    /**
     * 用户名
     */
    @NotEmpty(message = "用户名不能为空")
    private String userName;

    /**
     * 登录密码
     */
    @NotEmpty(message = "登录密码不能为空")
    private String loginPwd;

    /**
     * 验证码
     */
    private String validCode;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getValidCode() {
        return validCode;
    }

    public void setValidCode(String validCode) {
        this.validCode = validCode;
    }
}
