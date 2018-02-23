package com.bryan.v100.sys.service;

import com.bryan.common.base.BaseService;
import com.bryan.sys.domain.SysRole;
import com.bryan.sys.domain.SysUserRole;

import java.util.List;

/**
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/23
 */
public interface SysUserRoleService extends BaseService<SysUserRole> {

    /**
     * 根据用户id查询用户角色
     *
     * @param userId
     * @return
     */
    List<SysRole> findSysUserRoleList(Integer userId);

    /**
     * 根据用户id及角色id查询用户指定角色
     *
     * @param sysUserId
     * @param roleId
     * @return
     */
    SysUserRole findUserRole(Integer sysUserId, Integer roleId);
}
