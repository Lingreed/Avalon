package com.bryan.controller.v100.vo.user.corpInfo;

import com.bryan.controller.v100.vo.PageReq;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

/**
 * ClassName:CorpInfoQueryReq
 * Function: 企业用户
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/3/1
 */
public class CorpInfoQueryReq extends PageReq {
    /**
     * 用户主键
     */
    private Integer userId;

    /**
     * 用户类型:01个人;02企业
     */
    private String utype;

    /**
     * 用户用途类别:01借款用户,02投资用户,03担保用户
     */
    @NotEmpty(message = "用户用途类型必须传，01借款用户,02投资用户,03担保用户")
    private String loanTag;

    /**
     * 企业名称
     */
    private String corpName;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 组织机构代码
     */
    private String agencyCode;

    /**
     * 是否实名 10是 20否
     */
    private String realNameState;

    /**
     * 用户状态 10正常 20注销 30冻结
     */
    private String state;

    /**
     * 注册来源:01PC,02H5,03Android,04iOS,05邀请注册,06渠道注册,07后台录入
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

    public String getUtype() {
        return utype;
    }

    public void setUtype(String utype) {
        this.utype = utype;
    }

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
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

    public String getAgencyCode() {
        return agencyCode;
    }

    public void setAgencyCode(String agencyCode) {
        this.agencyCode = agencyCode;
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

    public List<String> getRegSource() {
        return regSource;
    }

    public void setRegSource(List<String> regSource) {
        this.regSource = regSource;
    }

    public String getLoanTag() {
        return loanTag;
    }

    public void setLoanTag(String loanTag) {
        this.loanTag = loanTag;
    }

    public List<String> getClientType() {
        return clientType;
    }

    public void setClientType(List<String> clientType) {
        this.clientType = clientType;
    }

}
