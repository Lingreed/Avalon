package com.bryan.dao.sys.model;


import com.bryan.dao.sys.domain.SysUser;

/**
 * ClassName: SysUserLoginModel
 * Function: 后台系统登录用户model
 */
public class SysUserLoginModel extends SysUser {

    private static final long serialVersionUID = -2449982824459356335L;

    private Integer roleId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
