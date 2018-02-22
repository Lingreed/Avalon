package com.bryan.v100.sys.service.impl;

import com.bryan.common.base.BaseMapper;
import com.bryan.common.base.BaseServiceImpl;
import com.bryan.common.constant.redis.RedisConstant;
import com.bryan.common.redis.RedisTemplateUtil;
import com.bryan.common.utils.CollectionUtils;
import com.bryan.sys.domain.SysRoleMenu;
import com.bryan.sys.mapper.SysRoleMenuMapper;
import com.bryan.sys.model.SysMenuModel;
import com.bryan.v100.sys.service.SysRoleMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/1/29
 */
@Service(value="sysRoleMenuService")
@Transactional
public class SysRoleMenuServiceImpl extends BaseServiceImpl<SysRoleMenu> implements SysRoleMenuService {

    @Resource
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public BaseMapper<SysRoleMenu> getMapper() {
        return sysRoleMenuMapper;
    }

    @Override
    public SysRoleMenu findSysRoleMenu(Integer roleId, Integer menuId) {
        return null;
    }

    @Override
    public void addRoleMenus(Integer roleId, List<Integer> menuIds) {

    }

    @Override
    public List<SysMenuModel> findRoleMenus(Integer sysRoleId, String menuType, int pid) {
        return null;
    }

    @Override
    public List<SysMenuModel> findInitNavButtons(Integer roleId) {
        return null;
    }

    /**
     * 查询角色对应的菜单ids
     * @param roleId
     * @return
     */
    @Override
    public List<Integer> findRoleMenuIds(Integer roleId) {
        Map<String,Object> map = new HashMap<>();
        map.put("sysRoleId", roleId);
        return sysRoleMenuMapper.findRoleMenuIds(map);
    }

    @Override
    public Map<String, String> findPageMenus(Integer sysRoleId, int pid) {
        return null;
    }

    /**
     * 查询角色有无权限
     * @param roleId 角色id
     * @param href 链接
     * @return
     */
    @Override
    public boolean checkRoleMenuHref(Integer roleId, String href) {
        Set<String> hrefSet = RedisTemplateUtil.hashGet(RedisConstant.CACHE_ROLE_MENU_HREF, String.valueOf(roleId));
        /*查询角色所有菜单权限*/
        if(CollectionUtils.isEmpty(hrefSet)){
            List<String> hrefList = sysRoleMenuMapper.findRoleHrefs(roleId);
            if(CollectionUtils.isNotEmpty(hrefList)){
                hrefSet = new HashSet<>();
                hrefSet.addAll(hrefList);
                RedisTemplateUtil.hashSet(RedisConstant.CACHE_ROLE_MENU_HREF,String.valueOf(roleId),hrefSet);
            }
        }
        if(CollectionUtils.isNotEmpty(hrefSet)){
            return hrefSet.contains(href);
        }
        return false;
    }
}
