package com.bryan.dao.user.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * ClassName: UserIdentify
 * Function: 用户认证信息实体
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/3/1
 */
@Table(name = "user_identify")
public class UserIdentify implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户主键
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 实名认证状态:10已认证,20未认证
     */
    @Column(name = "real_name_state")
    private String realNameState;

    /**
     * 实名认证时间
     */
    @Column(name = "real_name_time")
    private Date realNameTime;

    /**
     * 手机认证状态:10已认证,20未认证
     */
    @Column(name = "phone_state")
    private String phoneState;

    /**
     * 手机认证时间
     */
    @Column(name = "phone_time")
    private Date phoneTime;

    /**
     * 邮箱认证状态:10已认证.20未认证
     */
    @Column(name = "email_state")
    private String emailState;

    /**
     * 邮箱认证时间
     */
    @Column(name = "email_time")
    private Date emailTime;

    /**
     * 微信认证状态:10已认证,20未认证
     */
    @Column(name = "wechat_state")
    private String wechatState;

    /**
     * 微信认证时间
     */
    @Column(name = "wechat_time")
    private Date wechatTime;

    /**
     * 授权状态:10已授权,20未授权
     */
    @Column(name = "auth_state")
    private String authState;

    /**
     * 认证时间
     */
    @Column(name = "auth_time")
    private Date authTime;

    /**
     * 绑定银行卡状态:10绑定,20未绑定
     */
    @Column(name = "bind_card_state")
    private String bindCardState;

    /**
     * 绑卡时间
     */
    @Column(name = "bind_card_time")
    private Date bindCardTime;

    /**
     * 是否新手:10是,20不是
     */
    @Column(name = "new_invest_state")
    private String newInvestState;

    /**
     * 首投时间
     */
    @Column(name = "invest_time")
    private Date investTime;

    /**
     * 获取id
     *
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id 要设置的id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户主键
     *
     * @return 用户主键
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户主键
     *
     * @param userId 要设置的用户主键
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取实名认证状态:10已认证,20未认证
     *
     * @return 实名认证状态:10已认证,20未认证
     */
    public String getRealNameState() {
        return realNameState;
    }

    /**
     * 设置实名认证状态:10已认证,20未认证
     *
     * @param realNameState 要设置的实名认证状态:10已认证,20未认证
     */
    public void setRealNameState(String realNameState) {
        this.realNameState = realNameState;
    }

    /**
     * 获取实名认证时间
     *
     * @return 实名认证时间
     */
    public Date getRealNameTime() {
        return realNameTime;
    }

    /**
     * 设置实名认证时间
     *
     * @param realNameTime 要设置的实名认证时间
     */
    public void setRealNameTime(Date realNameTime) {
        this.realNameTime = realNameTime;
    }

    /**
     * 获取手机认证状态:10已认证,20未认证
     *
     * @return 手机认证状态:10已认证,20未认证
     */
    public String getPhoneState() {
        return phoneState;
    }

    /**
     * 设置手机认证状态:10已认证,20未认证
     *
     * @param phoneState 要设置的手机认证状态:10已认证,20未认证
     */
    public void setPhoneState(String phoneState) {
        this.phoneState = phoneState;
    }

    /**
     * 获取手机认证时间
     *
     * @return 手机认证时间
     */
    public Date getPhoneTime() {
        return phoneTime;
    }

    /**
     * 设置手机认证时间
     *
     * @param phoneTime 要设置的手机认证时间
     */
    public void setPhoneTime(Date phoneTime) {
        this.phoneTime = phoneTime;
    }

    /**
     * 获取邮箱认证状态:10已认证.20未认证
     *
     * @return 邮箱认证状态:10已认证.20未认证
     */
    public String getEmailState() {
        return emailState;
    }

    /**
     * 设置邮箱认证状态:10已认证.20未认证
     *
     * @param emailState 要设置的邮箱认证状态:10已认证.20未认证
     */
    public void setEmailState(String emailState) {
        this.emailState = emailState;
    }

    /**
     * 获取邮箱认证时间
     *
     * @return 邮箱认证时间
     */
    public Date getEmailTime() {
        return emailTime;
    }

    /**
     * 设置邮箱认证时间
     *
     * @param emailTime 要设置的邮箱认证时间
     */
    public void setEmailTime(Date emailTime) {
        this.emailTime = emailTime;
    }

    /**
     * 获取微信认证状态:10已认证,20未认证
     *
     * @return 微信认证状态:10已认证,20未认证
     */
    public String getWechatState() {
        return wechatState;
    }

    /**
     * 设置微信认证状态:10已认证,20未认证
     *
     * @param wechatState 要设置的微信认证状态:10已认证,20未认证
     */
    public void setWechatState(String wechatState) {
        this.wechatState = wechatState;
    }

    /**
     * 获取微信认证时间
     *
     * @return 微信认证时间
     */
    public Date getWechatTime() {
        return wechatTime;
    }

    /**
     * 设置微信认证时间
     *
     * @param wechatTime 要设置的微信认证时间
     */
    public void setWechatTime(Date wechatTime) {
        this.wechatTime = wechatTime;
    }

    /**
     * 获取授权状态:10已授权,20未授权
     *
     * @return 授权状态:10已授权,20未授权
     */
    public String getAuthState() {
        return authState;
    }

    /**
     * 设置授权状态:10已授权,20未授权
     *
     * @param authState 要设置的授权状态:10已授权,20未授权
     */
    public void setAuthState(String authState) {
        this.authState = authState;
    }

    /**
     * 获取认证时间
     *
     * @return 认证时间
     */
    public Date getAuthTime() {
        return authTime;
    }

    /**
     * 设置认证时间
     *
     * @param authTime 要设置的认证时间
     */
    public void setAuthTime(Date authTime) {
        this.authTime = authTime;
    }

    /**
     * 获取绑定银行卡状态:10绑定,20未绑定
     *
     * @return 绑定银行卡状态:10绑定,20未绑定
     */
    public String getBindCardState() {
        return bindCardState;
    }

    /**
     * 设置绑定银行卡状态:10绑定,20未绑定
     *
     * @param bindCardState 要设置的绑定银行卡状态:10绑定,20未绑定
     */
    public void setBindCardState(String bindCardState) {
        this.bindCardState = bindCardState;
    }

    /**
     * 获取绑卡时间
     *
     * @return 绑卡时间
     */
    public Date getBindCardTime() {
        return bindCardTime;
    }

    /**
     * 设置绑卡时间
     *
     * @param bindCardTime 要设置的绑卡时间
     */
    public void setBindCardTime(Date bindCardTime) {
        this.bindCardTime = bindCardTime;
    }

    /**
     * 获取是否新手:10是,20不是
     *
     * @return 是否新手:10是,20不是
     */
    public String getNewInvestState() {
        return newInvestState;
    }

    /**
     * 设置是否新手:10是,20不是
     *
     * @param newInvestState 要设置的是否新手:10是,20不是
     */
    public void setNewInvestState(String newInvestState) {
        this.newInvestState = newInvestState;
    }

    /**
     * 获取首投时间
     *
     * @return 首投时间
     */
    public Date getInvestTime() {
        return investTime;
    }

    /**
     * 设置首投时间
     *
     * @param investTime 要设置的首投时间
     */
    public void setInvestTime(Date investTime) {
        this.investTime = investTime;
    }

}
