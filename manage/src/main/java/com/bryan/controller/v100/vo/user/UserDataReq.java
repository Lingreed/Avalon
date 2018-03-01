package com.bryan.controller.v100.vo.user;

import com.bryan.controller.v100.vo.PageReq;

/**
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/3/1
 */
public class UserDataReq extends PageReq {

    /**
     * 认证名称
     */
    private String authName;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户类型
     */
    private String utype;

    /**
     * 用户用途类别:01借款用户,02投资用户,03担保用户
     */
    private String loanTag;

    /**
     * 渠道标识
     */
    private String channelCode;

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public String getLoanTag() {
        return loanTag;
    }

    public void setLoanTag(String loanTag) {
        this.loanTag = loanTag;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

}
