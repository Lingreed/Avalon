package com.bryan.v100.service.sys;

import com.bryan.common.base.BaseService;
import com.bryan.sys.domain.SysUser;
import com.bryan.sys.model.SysUserModel;

import java.util.List;
import java.util.Map;

/**
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/23
 */
public interface SysUserService extends BaseService<SysUser> {

    void saveSysUser(List<Integer> roleIds, SysUser sysUser, SysUser sessionUser);

    void updateSysuser(List<Integer> roleIds, SysUser sysUser,
                       SysUser sessionUser);

    List<SysUserModel> findSysUserList(Map<String, Object> map);

    void updateLoginInfo(SysUser sysUser);

    SysUserModel findDetail(Integer id);
}
