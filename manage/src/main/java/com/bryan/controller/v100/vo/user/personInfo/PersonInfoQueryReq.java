package com.bryan.controller.v100.vo.user.personInfo;

import com.bryan.controller.v100.vo.PageReq;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

/**
 * ClassName:PersonInfoQueryReq
 * Function: 个人用户信息查询
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/3/1
 */
public class PersonInfoQueryReq extends PageReq {

    /**
     * 用户主键
     */
    private Integer userId;

    /**
     * 用户类型:01个人;02企业
     */
    private String utype;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 身份证号
     */
    private String idNumber;

    /**
     * 用户用途类别:01借款用户,02投资用户,03担保用户
     */
    @NotEmpty(message = "用户用途类型必须传，01借款用户,02投资用户,03担保用户")
    private String loanTag;

    /**
     * 是否实名 10是 20否
     */
    private String realNameState;

    /**
     * 用户状态 10正常 20注销 30冻结
     */
    private String state;

    /**
     * 注册来源:01普通注册,02邀请注册,03渠道注册,04后台录入
     */
    private List<String> regSource;

    /**
     * 客户端:01PC,02H5,03Android,04iOS,99其他
     */
    private List<String> clientType;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getLoanTag() {
        return loanTag;
    }

    public void setLoanTag(String loanTag) {
        this.loanTag = loanTag;
    }

    public String getRealNameState() {
        return realNameState;
    }

    public void setRealNameState(String realNameState) {
        this.realNameState = realNameState;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUtype() {
        return utype;
    }

    public void setUtype(String utype) {
        this.utype = utype;
    }

    public List<String> getRegSource() {
        return regSource;
    }

    public void setRegSource(List<String> regSource) {
        this.regSource = regSource;
    }

    public List<String> getClientType() {
        return clientType;
    }

    public void setClientType(List<String> clientType) {
        this.clientType = clientType;
    }

}
