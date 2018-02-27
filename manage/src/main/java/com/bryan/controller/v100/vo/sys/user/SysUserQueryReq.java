package com.bryan.controller.v100.vo.sys.user;

import com.bryan.controller.v100.vo.PageReq;

import java.util.List;

/**
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/27
 */
public class SysUserQueryReq extends PageReq {

    /**
     * 用户id
     */
    private Integer id;
    /**
     * 机构id
     */
    private Integer orgId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户状态10启用,20禁用,30冻结
     */
    private String state;

    /**
     * 工号
     */
    private String jobNo;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 性别字典项
     */
    private String sex;

    /**
     * 手机号
     */
    private String mobilePhone;

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

    /**
     * 角色列表
     */
    private List<Integer> roleIds;

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getJobNo() {
        return jobNo;
    }

    public void setJobNo(String jobNo) {
        this.jobNo = jobNo;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Integer> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Integer> roleIds) {
        this.roleIds = roleIds;
    }

}
