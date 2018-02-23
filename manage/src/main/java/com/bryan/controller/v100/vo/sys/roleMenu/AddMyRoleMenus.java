package com.bryan.controller.v100.vo.sys.roleMenu;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * ClassName: AddMyRoleMenus
 * Function:  添加菜单
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/23
 */
public class AddMyRoleMenus {

    /**
     * 角色id
     */
    @NotNull(message="角色不能为空")
    private Integer roleId;

    @NotEmpty(message="请选择菜单")
    private List<Integer> menuIds;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public List<Integer> getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(List<Integer> menuIds) {
        this.menuIds = menuIds;
    }
}
