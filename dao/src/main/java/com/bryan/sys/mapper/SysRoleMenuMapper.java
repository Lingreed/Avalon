package com.bryan.sys.mapper;

import com.bryan.common.base.BaseMapper;
import com.bryan.sys.domain.SysMenu;
import com.bryan.sys.domain.SysRoleMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * ClassName: SysRoleMenu
 * Function: 系统角色菜单 mapper
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/1/26
 */
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {
    /**
     * 查询角色对应的菜单.
     * @param map
     * @return
     */
    List<SysMenu> findRoleMenus(Map<String,Object> map);

    /**
     * 查询角色对应的菜单id列表
     * @param map
     * @return
     */
    List<Integer> findRoleMenuIds(Map<String,Object> map);

    /**
     * @Title: findLeftAndPageButtons
     * @Description: 查询角色对应的左侧及页内按钮
     * @param map
     * @return
     */
    List<SysMenu> findLeftAndPageButtons(Map<String,Object> map);

    /**
     * @Title: findRoleHrefs
     * @Description: 查询角色对应的权限href
     * @param sysRoleId 角色id
     * @return
     */
    List<String> findRoleHrefs(@Param("sysRoleId")Integer sysRoleId );
}
