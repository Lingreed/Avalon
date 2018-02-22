package com.bryan.controller.v100.vo.sys.role;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/22
 */
public class SysRoleSaveOrUpdateReq {

    /**
     * 角色id
     */
    private Integer id;

    /**
     * 角色名称
     */
    @NotBlank(message="角色名称不能为空")
    private String roleName;

    /**
     * 角色code标识
     */
    @NotBlank(message="角色code不能为空")
    private String roleCode;

    /**
     * 角色类型字典项
     */
    private String roleType;

    /**
     * 状态10可用20禁用
     */
    @NotBlank(message="状态必须填写")
    String state;

    /**
     * 是否可编辑,01可以,02不可以,默认可以
     */
    private String editTag = "01";

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getEditTag() {
        return editTag;
    }

    public void setEditTag(String editTag) {
        this.editTag = editTag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
