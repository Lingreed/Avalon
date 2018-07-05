package com.bryan.dao.user.model;

import com.bryan.common.utils.excel.ExcelField;

import java.util.Date;

/**
 * ClassName:UserPersonInfoModel
 * Function: 个人用户信息
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/3/1
 */
public class UserPersonInfoModel {
    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 用户名
     */
    @ExcelField(title = "用户名", align = 1, sort = 1)
    private String userName;

    /**
     * 真实姓名
     */
    @ExcelField(title = "真实姓名", align = 1, sort = 2)
    private String realName;

    /**
     * 证件号码
     */
    @ExcelField(title = "证件号码", align = 1, sort = 2)
    private String idNumber;

    /**
     * 第三方支付客户号
     */
    @ExcelField(title = "第三方支付客户号", align = 2, sort = 3)
    private String payCustNo;

    /**
     * 第三方支付账户号
     */
    @ExcelField(title = "第三方支付账户号", align = 2, sort = 4)
    private String payAcctNo;

    /**
     * 创建时间
     */
    @ExcelField(title = "注册时间", align = 2, sort = 5)
    private Date ctime;

    /**
     * 创建IP
     */
    @ExcelField(title = "注册IP", align = 2, sort = 6)
    private String cip;

    /**
     * 用户类型 01个人 02企业
     */
    @ExcelField(title = "用户类型", align = 2, sort = 7, dictType = "utype")
    private String utype;

    /**
     * 是否借款人 01借款人 02投资人
     */
    @ExcelField(title = "是否借款人", align = 2, sort = 8, dictType = "loan_tag")
    private String loanTag;

    /**
     * 状态 10正常 20注销 30冻结
     */
    @ExcelField(title = "状态", align = 1, sort = 9, dictType = "user_state")
    private String state;

    /**
     * 用户手机号
     */
    @ExcelField(title = "手机号", align = 2, sort = 10)
    private String mobile;

    /**
     * 用户邮箱
     */
    @ExcelField(title = "邮箱", align = 3, sort = 11)
    private String email;

    /**
     * 注册来源:01普通注册,02邀请注册,03渠道注册,04后台录入
     */
    @ExcelField(title = "注册来源", align = 2, sort = 12, dictType = "reg_source")
    private String regSource;

    /**
     * 邀请码
     */
    private String inviteCode;

    /**
     * 是否实名认证 10是 20否
     */
    @ExcelField(title = "是否开户", align = 1, sort = 13, dictType = "real_name_state")
    private String realNameState;

    /**
     * 客户端:01PC,02H5,03Android,04iOS,99其他
     */
    @ExcelField(title = "客户端", align = 1, sort = 14, dictType = "client_type")
    private String clientType;

    /**
     * 邀请人数
     */
    private String inviteCount;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPayCustNo() {
        return payCustNo;
    }

    public void setPayCustNo(String payCustNo) {
        this.payCustNo = payCustNo;
    }

    public String getPayAcctNo() {
        return payAcctNo;
    }

    public void setPayAcctNo(String payAcctNo) {
        this.payAcctNo = payAcctNo;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public String getCip() {
        return cip;
    }

    public void setCip(String cip) {
        this.cip = cip;
    }

    public String getInviteCount() {
        return inviteCount;
    }

    public void setInviteCount(String inviteCount) {
        this.inviteCount = inviteCount;
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

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getRealNameState() {
        return realNameState;
    }

    public void setRealNameState(String realNameState) {
        this.realNameState = realNameState;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getRegSource() {
        return regSource;
    }

    public void setRegSource(String regSource) {
        this.regSource = regSource;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

}
