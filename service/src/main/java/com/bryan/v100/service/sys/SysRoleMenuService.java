package com.bryan.v100.service.sys;

import com.bryan.common.base.BaseService;
import com.bryan.dao.sys.domain.SysRoleMenu;
import com.bryan.dao.sys.model.SysMenuModel;

import java.util.List;
import java.util.Map;

/**
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/1/26
 */
public interface SysRoleMenuService extends BaseService<SysRoleMenu> {

    /**
     * 查询角色及菜单id对应的数据.
     *
     * @param roleId
     * @param menuId
     */
    SysRoleMenu findSysRoleMenu(Integer roleId, Integer menuId);

    /**
     * 删除角色菜单
     *
     * @param roleId
     */
    void addRoleMenus(Integer roleId, List<Integer> menuIds);

    /**
     * 根据角色查询对应的左侧菜单权限
     *
     * @return
     */
    List<SysMenuModel> findRoleMenus(Integer sysRoleId, String menuType, int pid);

    /**
     * 初始化角色对应的导航 及左侧菜单.
     *
     * @param roleId
     * @return List<SysMenuModel>
     */
    List<SysMenuModel> findInitNavButtons(Integer roleId);

    /**
     * 查询角色对应的菜单ids
     *
     * @param roleId
     * @return
     */
    List<Integer> findRoleMenuIds(Integer roleId);

    /**
     * 查询该角色左侧按钮，对应的页面全部按钮
     *
     * @param sysRoleId
     * @param pid
     * @return Map<String,String>
     */
    Map<String, String> findPageMenus(Integer sysRoleId, int pid);

    /**
     * 查询角色有无权限
     *
     * @param roleId 角色id
     * @param href   链接
     * @return
     */
    boolean checkRoleMenuHref(Integer roleId, String href);
}
