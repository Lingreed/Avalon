package com.bryan.dao.sys.mapper;

import com.bryan.common.base.BaseMapper;
import com.bryan.dao.sys.domain.SysRole;
import com.bryan.dao.sys.domain.SysUserRole;

import java.util.List;
import java.util.Map;

/**
 * ClassName: SysUserRole
 * Function: 系统用户角色 mapper
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/23
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    List<SysRole> findSysUserRoleList(Map<String, Object> map);
}
