package com.bryan.controller.v100.vo.sys.roleMenu;

import javax.validation.constraints.NotNull;

/**
 * ClassName: MyRoleMenusReq
 * Function: 角色菜单的添加及修改
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/23
 */
public class MyRoleMenusReq {

    /**
     * 角色id
     */
    @NotNull(message = "角色id不能为空")
    private Integer roleId;

    /**
     * 导航菜单id
     */
    private Integer navMenuId;

    /**
     * 左侧按钮id
     */
    private Integer leftMenuId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getLeftMenuId() {
        return leftMenuId;
    }

    public void setLeftMenuId(Integer leftMenuId) {
        this.leftMenuId = leftMenuId;
    }

    public Integer getNavMenuId() {
        return navMenuId;
    }

    public void setNavMenuId(Integer navMenuId) {
        this.navMenuId = navMenuId;
    }

}
