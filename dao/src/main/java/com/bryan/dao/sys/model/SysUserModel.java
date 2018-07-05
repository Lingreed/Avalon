package com.bryan.dao.sys.model;

import com.bryan.dao.sys.domain.SysRole;

import java.util.Date;
import java.util.List;

/**
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/23
 */
public class SysUserModel {
    /**
     * 用户id
     */
    private Integer id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 状态
     */
    private String state;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 性别
     */
    private String sex;

    /**
     * 头像地址
     */
    private String headPic;

    /**
     * 工号
     */
    private String jobNo;

    /**
     * 最后一次登录ip
     */
    private String lastLoginIp;

    /**
     * 最后一次登录时间
     */
    private Date lastLoginTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 手机
     */
    private String mobilePhone;

    /**
     * 组织机构id
     */
    private Integer orgId;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 所属机构名字
     */
    private String orgName;

    /**
     * 创建时间
     */
    private Date cTime;

    /**
     * 角色列表
     */
    private List<SysRole> roleList;

    /**
     * 角色id
     */
    private List<Integer> sysRoleIds;

    /**
     * 组织机构ids数组
     */
    private List<Integer> sysOrgIds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public String getJobNo() {
        return jobNo;
    }

    public void setJobNo(String jobNo) {
        this.jobNo = jobNo;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public List<SysRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<SysRole> roleList) {
        this.roleList = roleList;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Date getcTime() {
        return cTime;
    }

    public void setcTime(Date cTime) {
        this.cTime = cTime;
    }

    public List<Integer> getSysRoleIds() {
        return sysRoleIds;
    }

    public void setSysRoleIds(List<Integer> sysRoleIds) {
        this.sysRoleIds = sysRoleIds;
    }

    public List<Integer> getSysOrgIds() {
        return sysOrgIds;
    }

    public void setSysOrgIds(List<Integer> sysOrgIds) {
        this.sysOrgIds = sysOrgIds;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
