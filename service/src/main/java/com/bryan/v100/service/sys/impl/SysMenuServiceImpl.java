package com.bryan.v100.service.sys.impl;

import com.bryan.common.base.BaseMapper;
import com.bryan.common.base.BaseServiceImpl;
import com.bryan.common.constant.redis.RedisConstant;
import com.bryan.common.constant.sys.MenuConstant;
import com.bryan.common.exception.ServiceException;
import com.bryan.common.redis.RedisTemplateUtil;
import com.bryan.common.utils.BeanUtil;
import com.bryan.common.utils.DateUtil;
import com.bryan.sys.domain.SysMenu;
import com.bryan.sys.mapper.SysMenuMapper;
import com.bryan.sys.model.SysMenuModel;
import com.bryan.v100.service.sys.SysMenuService;
import com.bryan.v100.service.sys.SysRoleMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/22
 */
@Service(value = "sysMenuService")
@Transactional
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenu> implements SysMenuService {

    @Resource
    private SysMenuMapper sysMenuMapper;
    @Resource
    private SysRoleMenuService sysRoleMenuService;

    @Override
    public BaseMapper<SysMenu> getMapper() {
        return sysMenuMapper;
    }

    @Override
    public void updateSysMenu(SysMenu menu) {
        SysMenu sysMenu = super.selectByPrimaryKey(menu.getId());
        if (sysMenu == null) {
            throw new ServiceException("菜单查询失败");
        }

        // 下面几项不做修改
        menu.setMenuCode(null);
        menu.setCtime(null);
        menu.setMenuType(null);

        menu.setMtime(DateUtil.getNow());

        int count = super.updateByPrimaryKeySelective(menu);
        if (count != 1) {
            throw new ServiceException("更新菜单错误");
        }

        // 删除菜单列表缓存
        RedisTemplateUtil.delete(RedisConstant.CACHE_SYS_MENU);
        // 删除登录缓存
        RedisTemplateUtil.delete(RedisConstant.CACHE_ROLE_MENU);
    }

    @Override
    public void saveSysMenu(SysMenu menu) {
        //校验menuCode不能重复
        Example example = new Example(SysMenu.class);
        Example.Criteria codeParam = example.createCriteria();
        codeParam.andEqualTo("menuCode", menu.getMenuCode());

        SysMenu sysMenu = super.selectOneByExample(example);
        if (sysMenu != null) {
            throw new ServiceException("菜单标识或者菜单名称重复，请重新输入");
        }
        menu.setCtime(DateUtil.getNow());
        super.save(menu);

        // 删除菜单列表缓存
        RedisTemplateUtil.delete(RedisConstant.CACHE_SYS_MENU);
        // 删除登录缓存
        RedisTemplateUtil.delete(RedisConstant.CACHE_ROLE_MENU);
    }

    @Override
    public List<SysMenuModel> findAllList() {
        // 先从缓存获取
        List<SysMenuModel> cacheList = RedisTemplateUtil.get(RedisConstant.CACHE_SYS_MENU);
        if (cacheList == null || cacheList.isEmpty()) {
            // 缓存不存在,查询数据库
            List<SysMenuModel> list = findChildList(MenuConstant.PID_ROOT, 0);
            // 重新缓存结果
            RedisTemplateUtil.set(RedisConstant.CACHE_SYS_MENU, list);
            return list;
        } else {
            return cacheList;
        }
    }

    /**
     * 根据父级id,查询子级菜单.
     * @param menuId 当前菜单id
     * @param depth 递归深度,起始值0
     * @return
     */
    @Override
    public List<SysMenuModel> findChildList(Integer menuId, int depth) {
        List<SysMenuModel> modelList = new ArrayList<>();
        Example example = new Example(SysMenu.class);
        Example.Criteria pidParam = example.createCriteria();
        pidParam.andEqualTo("pid", menuId);
        int nextDepth = depth + 1;
        List<SysMenu> childList = super.selectByExample(example);
        for (SysMenu sysMenu : childList) {
            SysMenuModel menuModel = new SysMenuModel();
            BeanUtil.copyProperties(menuModel, sysMenu);
            menuModel.setDepth(depth);
            List<SysMenuModel> child = findChildList(sysMenu.getId(), nextDepth);
            menuModel.setChildNum(child.size());
            menuModel.setChild(child);
            modelList.add(menuModel);
        }
        return modelList;
    }

    /**
     * 查询角色的菜单,拥有的角色数组
     * view code 格式
     * @param roleId
     * @return
     */
    @Override
    public List<Integer> findRoleMenuList(Integer roleId) {
        return sysRoleMenuService.findRoleMenuIds(roleId);
    }

    /**
     * 查询父级id数组
     * @return
     */
    @Override
    public List<Integer> findPids(Integer menuId, List<Integer> pids) {
        SysMenu currMenu = sysMenuMapper.selectByPrimaryKey(menuId);
        if(currMenu != null && currMenu.getPid()>0){
            // 递归查询菜单
            pids.add(currMenu.getPid());
            findPids(currMenu.getPid(),pids);
        }
        return pids;
    }
}
