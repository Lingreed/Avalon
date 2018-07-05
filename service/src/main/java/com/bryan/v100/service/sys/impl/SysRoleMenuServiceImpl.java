package com.bryan.v100.service.sys.impl;

import com.bryan.common.base.BaseMapper;
import com.bryan.common.base.BaseServiceImpl;
import com.bryan.common.constant.redis.RedisConstant;
import com.bryan.common.constant.sys.MenuConstant;
import com.bryan.common.context.Global;
import com.bryan.common.redis.RedisTemplateUtil;
import com.bryan.common.utils.BeanUtil;
import com.bryan.common.utils.CollectionUtils;
import com.bryan.common.utils.StringUtil;
import com.bryan.sys.domain.SysMenu;
import com.bryan.sys.domain.SysRoleMenu;
import com.bryan.sys.mapper.SysMenuMapper;
import com.bryan.sys.mapper.SysRoleMenuMapper;
import com.bryan.sys.model.SysMenuModel;
import com.bryan.v100.service.sys.SysMenuService;
import com.bryan.v100.service.sys.SysRoleMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/1/29
 */
@Service(value = "sysRoleMenuService")
@Transactional
public class SysRoleMenuServiceImpl extends BaseServiceImpl<SysRoleMenu> implements SysRoleMenuService {

    @Resource
    private SysRoleMenuMapper sysRoleMenuMapper;
    @Resource
    private SysMenuMapper sysMenuMapper;
    @Resource
    private SysMenuService sysMenuService;

    @Override
    public BaseMapper<SysRoleMenu> getMapper() {
        return sysRoleMenuMapper;
    }

    /**
     * 查询角色及菜单id对应的数据.
     *
     * @param roleId
     * @param menuId
     */
    @Override
    public SysRoleMenu findSysRoleMenu(Integer roleId, Integer menuId) {
        Example example = new Example(SysRoleMenu.class);
        example.createCriteria().andEqualTo("sysRoleId", roleId)
                .andEqualTo("sysMenuId", menuId);

        List<SysRoleMenu> list = sysRoleMenuMapper.selectByExample(example);
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }

    /**
     * 添加菜单按钮
     *
     * @param roleId
     */
    @Override
    public void addRoleMenus(Integer roleId, List<Integer> menuIds) {
        //删除角色老的按钮
        Example example = new Example(SysRoleMenu.class);
        example.createCriteria().andEqualTo("sysRoleId", roleId);
        sysRoleMenuMapper.deleteByExample(example);

        Set<Integer> menuSet = new HashSet<>();

        for (Integer menuId : menuIds) {
            if (!menuSet.contains(menuId)) {
                menuSet.add(menuId);
                List<Integer> pList = new ArrayList<>();
                pList = sysMenuService.findPids(menuId, pList);
                menuSet.addAll(pList);
            }
        }

        List<Integer> roleMenuIdList = new ArrayList<>(menuSet);
        Collections.sort(roleMenuIdList);

        // 获得添加按钮的合集
        for (Integer menuId : roleMenuIdList) {
            SysRoleMenu newRoleMenu = new SysRoleMenu();
            newRoleMenu.setSysRoleId(roleId);
            newRoleMenu.setSysMenuId(menuId);
            if (menuIds.contains(menuId)) {
                newRoleMenu.setSelectState(MenuConstant.SELECT_STATE_YES);
            } else {
                newRoleMenu.setSelectState(MenuConstant.SELECT_STATE_NOT);
            }
            sysRoleMenuMapper.insert(newRoleMenu);
        }
        // 删除角色的菜单缓存
        RedisTemplateUtil.hashDel(RedisConstant.CACHE_ROLE_MENU, String.valueOf(roleId));
        RedisTemplateUtil.hashDel(RedisConstant.CACHE_ROLE_MENU_HREF, String.valueOf(roleId));
    }

    /**
     * 查询角色对应的指定类型菜单.
     *
     * @return
     */
    @Override
    public List<SysMenuModel> findRoleMenus(Integer sysRoleId, String menuType, int pid) {
        List<SysMenuModel> modelList = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("sysRoleId", sysRoleId);
        if (StringUtil.isNotBlank(menuType)) {
            map.put("menuType", menuType);
        }
        if (pid > 0) {
            map.put("pid", pid);
        }
        List<SysMenu> list = sysRoleMenuMapper.findRoleMenus(map);
        for (SysMenu sysMenu : list) {
            SysMenuModel menuModel = new SysMenuModel();
            BeanUtil.copyProperties(menuModel, sysMenu);
            List<SysMenuModel> childList = findRoleMenus(sysRoleId, menuType, sysMenu.getId());
            menuModel.setChild(childList);
            modelList.add(menuModel);
        }
        return modelList;
    }

    /**
     * 初始化角色对应的导航 及左侧菜单.
     *
     * @param roleId
     * @return List<SysMenuModel>
     */
    @Override
    public List<SysMenuModel> findInitNavButtons(Integer roleId) {
        List<SysMenuModel> modelList = RedisTemplateUtil.hashGet(RedisConstant.CACHE_ROLE_MENU, String.valueOf(roleId));
        if (modelList == null || modelList.isEmpty()) {
            // 查询数据库,并缓存
            List<SysMenuModel> initList = new ArrayList<>();
            // 查询角色对应的顶部导航按钮
            List<SysMenu> list;
            if (roleId == Global.ADMIN_ROLE_ID) {
                SysMenu adminMenu = new SysMenu();
                adminMenu.setMenuType(MenuConstant.TYPE_MENU_NAV);
                adminMenu.setPid(MenuConstant.PID_NAV_BUTTON);
                list = sysMenuMapper.select(adminMenu);
            } else {
                Map<String, Object> map = new HashMap<>();
                map.put("sysRoleId", roleId);
                map.put("menuType", MenuConstant.TYPE_MENU_NAV);
                map.put("pid", MenuConstant.PID_NAV_BUTTON);
                list = sysRoleMenuMapper.findRoleMenus(map);
            }
            for (SysMenu navButton : list) {
                SysMenuModel menuModel = new SysMenuModel();
                BeanUtil.copyProperties(menuModel, navButton);
                //查询导航菜单下的左侧按钮
                List<SysMenuModel> leftButtons = findRoleMenus(roleId, MenuConstant.TYPE_MENU_LEFT, navButton.getId());
                if (leftButtons.isEmpty()) {// 没有左侧菜单的顶级菜单过滤掉
                    continue;
                }
                menuModel.setChild(leftButtons);
                initList.add(menuModel);
            }
            // 缓存角色对应的菜单
            RedisTemplateUtil.hashSet(RedisConstant.CACHE_ROLE_MENU, String.valueOf(roleId), initList);

            return initList;
        } else {
            return modelList;
        }
    }

    /**
     * 查询角色对应的菜单ids
     *
     * @param roleId
     * @return
     */
    @Override
    public List<Integer> findRoleMenuIds(Integer roleId) {
        Map<String, Object> map = new HashMap<>();
        map.put("sysRoleId", roleId);
        return sysRoleMenuMapper.findRoleMenuIds(map);
    }

    /**
     * 查询该角色左侧按钮，对应的页面全部按钮
     *
     * @param sysRoleId
     * @param pid
     * @return Map<String,String>
     */
    @Override
    public Map<String, String> findPageMenus(Integer sysRoleId, int pid) {
        Map<String, String> map = new HashMap<>();

        Map<String, Object> param = new HashMap<>();
        param.put("sysRoleId", sysRoleId);
        if (pid > 0) {
            param.put("pid", pid);
        }
        List<SysMenu> list = sysRoleMenuMapper.findRoleMenus(param);
        for (SysMenu sysMenu : list) {
            map.put(sysMenu.getMenuCode(), sysMenu.getMenuName());
            // 递归查询当前按钮的子按钮
            findPageMenus(sysRoleId, sysMenu.getId());
        }
        return map;
    }

    /**
     * 查询角色有无权限
     *
     * @param roleId 角色id
     * @param href   链接
     * @return
     */
    @Override
    public boolean checkRoleMenuHref(Integer roleId, String href) {
        Set<String> hrefSet = RedisTemplateUtil.hashGet(RedisConstant.CACHE_ROLE_MENU_HREF, String.valueOf(roleId));
        /*查询角色所有菜单权限*/
        if (CollectionUtils.isEmpty(hrefSet)) {
            List<String> hrefList = sysRoleMenuMapper.findRoleHrefs(roleId);
            if (CollectionUtils.isNotEmpty(hrefList)) {
                hrefSet = new HashSet<>();
                hrefSet.addAll(hrefList);
                RedisTemplateUtil.hashSet(RedisConstant.CACHE_ROLE_MENU_HREF, String.valueOf(roleId), hrefSet);
            }
        }
        if (CollectionUtils.isNotEmpty(hrefSet)) {
            return hrefSet.contains(href);
        }
        return false;
    }
}
