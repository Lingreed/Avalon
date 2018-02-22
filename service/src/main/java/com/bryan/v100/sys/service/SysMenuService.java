package com.bryan.v100.sys.service;

import com.bryan.common.base.BaseService;
import com.bryan.sys.domain.SysMenu;
import com.bryan.sys.model.SysMenuModel;

import java.util.List;

/**
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/22
 */
public interface SysMenuService extends BaseService<SysMenu> {

    void updateSysMenu(SysMenu menu);

    void saveSysMenu(SysMenu menu);


    List<SysMenuModel> findAllList();

    /**
     * 根据父级id,查询子级菜单.
     * @param menuId
     * @param depth 递归深度
     * @return
     */
    List<SysMenuModel> findChildList(Integer menuId, int depth);

    /**
     * 查询角色的菜单,拥有的角色和不拥有的角色
     * view code格式
     * @param roleId
     * @return
     */
    List<Integer> findRoleMenuList(Integer roleId);

    /**
     * 查询父级id数组
     * @return
     */
    List<Integer> findPids(Integer menuId, List<Integer> pids);
}
