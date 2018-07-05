package com.bryan.dao.user.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * ClassName: User
 * Function: 用户表实体
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/3/1
 */
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 唯一标识
     */
    private String uuid;

    /**
     * 用户名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 实名认证后的名称.个人为真实姓名,企业为机构名称
     */
    @Column(name = "auth_name")
    private String authName;

    /**
     * 登录密码
     */
    @Column(name = "login_pwd")
    private String loginPwd;

    /**
     * 支付密码
     */
    @Column(name = "pay_pwd")
    private String payPwd;

    /**
     * 用户类型:01个人;02企业
     */
    private String utype;

    /**
     * 用户用途类别:01借款用户,02投资用户,03担保用户
     */
    @Column(name = "loan_tag")
    private String loanTag;

    /**
     * 状态:10正常,20注销,30冻结
     */
    private String state;

    /**
     * 用户头像地址
     */
    private String avatar;

    /**
     * 用户手机号码
     */
    private String mobile;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 客户端设备唯一标识符
     */
    @Column(name = "clinet_udid")
    private String clinetUdid;

    /**
     * 客户端广告商标记
     */
    @Column(name = "client_idfa")
    private String clientIdfa;

    /**
     * 注册来源:01普通注册,02邀请注册,03渠道注册,04后台录入
     */
    @Column(name = "reg_source")
    private String regSource;

    /**
     * 客户端:01PC,02H5,03Android,04iOS,99其他
     */
    @Column(name = "client_type")
    private String clientType;

    /**
     * 邀请码
     */
    @Column(name = "invite_code")
    private String inviteCode;

    /**
     * 邀请人数
     */
    @Column(name = "invite_count")
    private Integer inviteCount;

    /**
     * 渠道标识
     */
    @Column(name = "channel_code")
    private String channelCode;

    /**
     * 推广url
     */
    @Column(name = "source_url")
    private String sourceUrl;

    /**
     * 邀请注册二维码
     */
    private String qrcode;

    /**
     * 微信用户标识
     */
    @Column(name = "wx_openid")
    private String wxOpenid;

    /**
     * 第三方支付客户号
     */
    @Column(name = "pay_cust_no")
    private String payCustNo;

    /**
     * 第三方支付账户号
     */
    @Column(name = "pay_acct_no")
    private String payAcctNo;

    /**
     * 扩展字段1
     */
    private String ext1;

    /**
     * 扩展字段2
     */
    private String ext2;

    /**
     * 备注
     */
    private String remark;

    /**
     * 上次登录时间
     */
    @Column(name = "last_login_time")
    private Date lastLoginTime;

    /**
     * 上次登录ip
     */
    @Column(name = "last_login_ip")
    private String lastLoginIp;

    /**
     * 登录时间
     */
    @Column(name = "login_time")
    private Date loginTime;

    /**
     * 登录ip
     */
    @Column(name = "login_ip")
    private String loginIp;

    /**
     * 常用ip
     */
    @Column(name = "often_ip")
    private String oftenIp;

    /**
     * 首次投资时间
     */
    @Column(name = "first_invest_time")
    private Date firstInvestTime;

    /**
     * 添加时间
     */
    private Date ctime;

    /**
     * 添加ip
     */
    private String cip;

    /**
     * 更新时间
     */
    private Date mtime;

    /**
     * 修改ip
     */
    private String mip;

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
     * 获取唯一标识
     *
     * @return 唯一标识
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 设置唯一标识
     *
     * @param uuid 要设置的唯一标识
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * 获取用户名
     *
     * @return 用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名
     *
     * @param userName 要设置的用户名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取实名认证后的名称.个人为真实姓名,企业为机构名称
     *
     * @return 实名认证后的名称.个人为真实姓名, 企业为机构名称
     */
    public String getAuthName() {
        return authName;
    }

    /**
     * 设置实名认证后的名称.个人为真实姓名,企业为机构名称
     *
     * @param authName 要设置的实名认证后的名称.个人为真实姓名,企业为机构名称
     */
    public void setAuthName(String authName) {
        this.authName = authName;
    }

    /**
     * 获取登录密码
     *
     * @return 登录密码
     */
    public String getLoginPwd() {
        return loginPwd;
    }

    /**
     * 设置登录密码
     *
     * @param loginPwd 要设置的登录密码
     */
    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    /**
     * 获取支付密码
     *
     * @return 支付密码
     */
    public String getPayPwd() {
        return payPwd;
    }

    /**
     * 设置支付密码
     *
     * @param payPwd 要设置的支付密码
     */
    public void setPayPwd(String payPwd) {
        this.payPwd = payPwd;
    }

    /**
     * 获取用户类型:01个人;02企业
     *
     * @return 用户类型:01个人;02企业
     */
    public String getUtype() {
        return utype;
    }

    /**
     * 设置用户类型:01个人;02企业
     *
     * @param utype 要设置的用户类型:01个人;02企业
     */
    public void setUtype(String utype) {
        this.utype = utype;
    }

    /**
     * 获取用户用途类别:01借款用户,02投资用户,03担保用户
     *
     * @return 用户用途类别:01借款用户,02投资用户,03担保用户
     */
    public String getLoanTag() {
        return loanTag;
    }

    /**
     * 设置用户用途类别:01借款用户,02投资用户,03担保用户
     *
     * @param loanTag 要设置的用户用途类别:01借款用户,02投资用户,03担保用户
     */
    public void setLoanTag(String loanTag) {
        this.loanTag = loanTag;
    }

    /**
     * 获取状态:10正常,20注销,30冻结
     *
     * @return 状态:10正常,20注销,30冻结
     */
    public String getState() {
        return state;
    }

    /**
     * 设置状态:10正常,20注销,30冻结
     *
     * @param state 要设置的状态:10正常,20注销,30冻结
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * 获取用户头像地址
     *
     * @return 用户头像地址
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 设置用户头像地址
     *
     * @param avatar 要设置的用户头像地址
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 获取用户手机号码
     *
     * @return 用户手机号码
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置用户手机号码
     *
     * @param mobile 要设置的用户手机号码
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取用户邮箱
     *
     * @return 用户邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置用户邮箱
     *
     * @param email 要设置的用户邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取客户端设备唯一标识符
     *
     * @return 客户端设备唯一标识符
     */
    public String getClinetUdid() {
        return clinetUdid;
    }

    /**
     * 设置客户端设备唯一标识符
     *
     * @param clinetUdid 要设置的客户端设备唯一标识符
     */
    public void setClinetUdid(String clinetUdid) {
        this.clinetUdid = clinetUdid;
    }

    /**
     * 获取客户端广告商标记
     *
     * @return 客户端广告商标记
     */
    public String getClientIdfa() {
        return clientIdfa;
    }

    /**
     * 设置客户端广告商标记
     *
     * @param clientIdfa 要设置的客户端广告商标记
     */
    public void setClientIdfa(String clientIdfa) {
        this.clientIdfa = clientIdfa;
    }

    /**
     * 获取注册来源:01普通注册,02邀请注册,03渠道注册,04后台录入
     *
     * @return 注册来源:01普通注册,02邀请注册,03渠道注册,04后台录入
     */
    public String getRegSource() {
        return regSource;
    }

    /**
     * 设置注册来源:01普通注册,02邀请注册,03渠道注册,04后台录入
     *
     * @param regSource 要设置的注册来源:01普通注册,02邀请注册,03渠道注册,04后台录入
     */
    public void setRegSource(String regSource) {
        this.regSource = regSource;
    }

    /**
     * 获取客户端:01PC,02H5,03Android,04iOS,99其他
     *
     * @return 客户端:01PC,02H5,03Android,04iOS,99其他
     */
    public String getClientType() {
        return clientType;
    }

    /**
     * 设置客户端:01PC,02H5,03Android,04iOS,99其他
     *
     * @param clientType 要设置的客户端:01PC,02H5,03Android,04iOS,99其他
     */
    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    /**
     * 获取邀请码
     *
     * @return 邀请码
     */
    public String getInviteCode() {
        return inviteCode;
    }

    /**
     * 设置邀请码
     *
     * @param inviteCode 要设置的邀请码
     */
    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    /**
     * 获取邀请人数
     *
     * @return 邀请人数
     */
    public Integer getInviteCount() {
        return inviteCount;
    }

    /**
     * 设置邀请人数
     *
     * @param inviteCount 要设置的邀请人数
     */
    public void setInviteCount(Integer inviteCount) {
        this.inviteCount = inviteCount;
    }

    /**
     * 获取渠道标识
     *
     * @return 渠道标识
     */
    public String getChannelCode() {
        return channelCode;
    }

    /**
     * 设置渠道标识
     *
     * @param channelCode 要设置的渠道标识
     */
    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    /**
     * 获取推广url
     *
     * @return 推广url
     */
    public String getSourceUrl() {
        return sourceUrl;
    }

    /**
     * 设置推广url
     *
     * @param sourceUrl 要设置的推广url
     */
    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    /**
     * 获取邀请注册二维码
     *
     * @return 邀请注册二维码
     */
    public String getQrcode() {
        return qrcode;
    }

    /**
     * 设置邀请注册二维码
     *
     * @param qrcode 要设置的邀请注册二维码
     */
    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    /**
     * 获取微信用户标识
     *
     * @return 微信用户标识
     */
    public String getWxOpenid() {
        return wxOpenid;
    }

    /**
     * 设置微信用户标识
     *
     * @param wxOpenid 要设置的微信用户标识
     */
    public void setWxOpenid(String wxOpenid) {
        this.wxOpenid = wxOpenid;
    }

    /**
     * 获取第三方支付客户号
     *
     * @return 第三方支付客户号
     */
    public String getPayCustNo() {
        return payCustNo;
    }

    /**
     * 设置第三方支付客户号
     *
     * @param payCustNo 要设置的第三方支付客户号
     */
    public void setPayCustNo(String payCustNo) {
        this.payCustNo = payCustNo;
    }

    /**
     * 获取第三方支付账户号
     *
     * @return 第三方支付账户号
     */
    public String getPayAcctNo() {
        return payAcctNo;
    }

    /**
     * 设置第三方支付账户号
     *
     * @param payAcctNo 要设置的第三方支付账户号
     */
    public void setPayAcctNo(String payAcctNo) {
        this.payAcctNo = payAcctNo;
    }

    /**
     * 获取扩展字段1
     *
     * @return 扩展字段1
     */
    public String getExt1() {
        return ext1;
    }

    /**
     * 设置扩展字段1
     *
     * @param ext1 要设置的扩展字段1
     */
    public void setExt1(String ext1) {
        this.ext1 = ext1;
    }

    /**
     * 获取扩展字段2
     *
     * @return 扩展字段2
     */
    public String getExt2() {
        return ext2;
    }

    /**
     * 设置扩展字段2
     *
     * @param ext2 要设置的扩展字段2
     */
    public void setExt2(String ext2) {
        this.ext2 = ext2;
    }

    /**
     * 获取备注
     *
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 要设置的备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取上次登录时间
     *
     * @return 上次登录时间
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * 设置上次登录时间
     *
     * @param lastLoginTime 要设置的上次登录时间
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * 获取上次登录ip
     *
     * @return 上次登录ip
     */
    public String getLastLoginIp() {
        return lastLoginIp;
    }

    /**
     * 设置上次登录ip
     *
     * @param lastLoginIp 要设置的上次登录ip
     */
    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    /**
     * 获取登录时间
     *
     * @return 登录时间
     */
    public Date getLoginTime() {
        return loginTime;
    }

    /**
     * 设置登录时间
     *
     * @param loginTime 要设置的登录时间
     */
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    /**
     * 获取登录ip
     *
     * @return 登录ip
     */
    public String getLoginIp() {
        return loginIp;
    }

    /**
     * 设置登录ip
     *
     * @param loginIp 要设置的登录ip
     */
    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    /**
     * 获取常用ip
     *
     * @return 常用ip
     */
    public String getOftenIp() {
        return oftenIp;
    }

    /**
     * 设置常用ip
     *
     * @param oftenIp 要设置的常用ip
     */
    public void setOftenIp(String oftenIp) {
        this.oftenIp = oftenIp;
    }

    /**
     * 获取首次投资时间
     *
     * @return 首次投资时间
     */
    public Date getFirstInvestTime() {
        return firstInvestTime;
    }

    /**
     * 设置首次投资时间
     *
     * @param firstInvestTime 要设置的首次投资时间
     */
    public void setFirstInvestTime(Date firstInvestTime) {
        this.firstInvestTime = firstInvestTime;
    }

    /**
     * 获取添加时间
     *
     * @return 添加时间
     */
    public Date getCtime() {
        return ctime;
    }

    /**
     * 设置添加时间
     *
     * @param ctime 要设置的添加时间
     */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    /**
     * 获取添加ip
     *
     * @return 添加ip
     */
    public String getCip() {
        return cip;
    }

    /**
     * 设置添加ip
     *
     * @param cip 要设置的添加ip
     */
    public void setCip(String cip) {
        this.cip = cip;
    }

    /**
     * 获取更新时间
     *
     * @return 更新时间
     */
    public Date getMtime() {
        return mtime;
    }

    /**
     * 设置更新时间
     *
     * @param mtime 要设置的更新时间
     */
    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }

    /**
     * 获取修改ip
     *
     * @return 修改ip
     */
    public String getMip() {
        return mip;
    }

    /**
     * 设置修改ip
     *
     * @param mip 要设置的修改ip
     */
    public void setMip(String mip) {
        this.mip = mip;
    }
}
