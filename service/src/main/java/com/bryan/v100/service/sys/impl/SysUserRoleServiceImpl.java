package com.bryan.v100.service.sys.impl;

import com.bryan.common.base.BaseMapper;
import com.bryan.common.base.BaseServiceImpl;
import com.bryan.sys.domain.SysRole;
import com.bryan.sys.domain.SysUserRole;
import com.bryan.sys.mapper.SysUserRoleMapper;
import com.bryan.v100.service.sys.SysUserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/23
 */
@Service(value="sysUserRoleService")
@Transactional
public class SysUserRoleServiceImpl extends BaseServiceImpl<SysUserRole> implements SysUserRoleService {

    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public BaseMapper<SysUserRole> getMapper() {
        return sysUserRoleMapper;
    }

    /**
     * 根据用户id查询用户角色
     * @param userId
     * @return
     */
    @Override
    public List<SysRole> findSysUserRoleList(Integer userId){
        Map<String,Object> map = new HashMap<>();
        map.put("sysUserId", userId);
        return sysUserRoleMapper.findSysUserRoleList(map);
    }

    /**
     * 查询用户及角色id对应的用户角色
     */
    @Override
    public SysUserRole findUserRole(Integer sysUserId,Integer roleId){
        Example example = new Example(SysUserRole.class);
        example.createCriteria().andEqualTo("sysUserId", sysUserId)
                .andEqualTo("sysRoleId", roleId);
        List<SysUserRole> list = sysUserRoleMapper.selectByExample(example);
        if(list.isEmpty()){
            return null;
        }else{
            return list.get(0);
        }
    }
}
