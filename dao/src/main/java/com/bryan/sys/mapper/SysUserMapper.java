package com.bryan.sys.mapper;

import com.bryan.common.base.BaseMapper;
import com.bryan.sys.domain.SysUser;
import com.bryan.sys.model.SysUserModel;

import java.util.List;
import java.util.Map;

/**
 * ClassName: SysUser
 * Function: 系统用户 mapper
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/23
 */
public interface SysUserMapper  extends BaseMapper<SysUser> {

    List<SysUserModel> findSysUserList(Map<String,Object> map);
}
