package com.bryan.controller.v100.vo.sys.role;

import com.bryan.controller.v100.vo.PageReq;

/**
 * ClassName: SysRoleQueryReq
 * Function: 查询
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/22
 */
public class SysRoleQueryReq extends PageReq {

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色标识
     */
    private String roleCode;

    /**
     * 角色类型字典项
     */
    private String roleTypeDictCode;

    /**
     * 状态10可用20禁用
     */
    private String state;

    /**
     * 是否可编辑
     */
    private String flagEdit;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
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

    public String getRoleTypeDictCode() {
        return roleTypeDictCode;
    }

    public void setRoleTypeDictCode(String roleTypeDictCode) {
        this.roleTypeDictCode = roleTypeDictCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getFlagEdit() {
        return flagEdit;
    }

    public void setFlagEdit(String flagEdit) {
        this.flagEdit = flagEdit;
    }
}
