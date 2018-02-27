package com.bryan.controller.v100.vo.sys.user;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * ClassName: AddAndUpdateSysUser
 * Function: 添加管理人员
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/27
 */
public class SysUserSaveOrUpdateReq {
    /**
     * 用户id
     */
    private Integer id;

    /**
     * 机构id
     */
    @NotNull(message="请选择机构")
    private Integer orgId;

    /**
     * 角色列表
     */
    @NotEmpty(message="请选择角色列表")
    private List<Integer> sysRoleIds;

    /**
     * 用户名
     */
    @NotBlank(message="请填写用户名")
    private String userName;

    /**
     * 登录密码
     */
    private String loginPwd;

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

    /**
     * 头像地址
     */
    private String headPic;

    /**
     * 备注
     */
    private String remark;

    /**
     * 扩展字段1
     */
    private String ext1;

    /**
     * 扩展字段2
     */
    private String ext2;

    /**
     * 扩展字段3
     */
    private String ext3;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public List<Integer> getSysRoleIds() {
        return sysRoleIds;
    }

    public void setSysRoleIds(List<Integer> sysRoleIds) {
        this.sysRoleIds = sysRoleIds;
    }

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

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1;
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2;
    }

    public String getExt3() {
        return ext3;
    }

    public void setExt3(String ext3) {
        this.ext3 = ext3;
    }
}
