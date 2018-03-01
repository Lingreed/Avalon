package com.bryan.user.model;

import com.bryan.user.domain.User;
import com.bryan.user.domain.UserCorpInfo;
import com.bryan.user.domain.UserIdentify;
import com.bryan.user.domain.UserPersonInfo;

/**
 * ClassName: UserDetailModel
 * Function:  用户基本信息
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/3/1
 */
public class UserDetailModel extends User {
    /**
     * 邀请人用户名
     */
    private String inviteUserName;

    /**
     * 邀请人手机号
     */
    private String inviteMobile;

    /**
     * 邀请人真实姓名
     */
    private String inviteRealName;

    /**
     * 用户认证信息
     */
    private UserIdentify userIdentify;

    /**
     * 用户个人资料
     */
    private UserPersonInfo userPersonInfo;

    /**
     * 用户企业资料
     */
    private UserCorpInfo userCorpInfo;

//    /**
//     * 借款方信息:个人
//     */
//    private UserDebitPerson userDebitPerson;
//
//    /**
//     * 借款方信息:企业
//     */
//    private UserDebitCorp userDebitCorp;
//
//    /**
//     * 账户详情
//     */
//    private AccountDetail accountDetail;


    public String getInviteUserName() {
        return inviteUserName;
    }

    public void setInviteUserName(String inviteUserName) {
        this.inviteUserName = inviteUserName;
    }

    public String getInviteMobile() {
        return inviteMobile;
    }

    public void setInviteMobile(String inviteMobile) {
        this.inviteMobile = inviteMobile;
    }

    public String getInviteRealName() {
        return inviteRealName;
    }

    public void setInviteRealName(String inviteRealName) {
        this.inviteRealName = inviteRealName;
    }

    public UserIdentify getUserIdentify() {
        return userIdentify;
    }

    public void setUserIdentify(UserIdentify userIdentify) {
        this.userIdentify = userIdentify;
    }

    public UserPersonInfo getUserPersonInfo() {
        return userPersonInfo;
    }

    public void setUserPersonInfo(UserPersonInfo userPersonInfo) {
        this.userPersonInfo = userPersonInfo;
    }

    public UserCorpInfo getUserCorpInfo() {
        return userCorpInfo;
    }

    public void setUserCorpInfo(UserCorpInfo userCorpInfo) {
        this.userCorpInfo = userCorpInfo;
    }

//    public UserDebitPerson getUserDebitPerson() {
//        return userDebitPerson;
//    }
//
//    public void setUserDebitPerson(UserDebitPerson userDebitPerson) {
//        this.userDebitPerson = userDebitPerson;
//    }
//
//    public UserDebitCorp getUserDebitCorp() {
//        return userDebitCorp;
//    }
//
//    public void setUserDebitCorp(UserDebitCorp userDebitCorp) {
//        this.userDebitCorp = userDebitCorp;
//    }
//
//    public AccountDetail getAccountDetail() {
//        return accountDetail;
//    }
//
//    public void setAccountDetail(AccountDetail accountDetail) {
//        this.accountDetail = accountDetail;
//    }

}
