package com.bryan.v100.service.sys;

import com.bryan.common.base.BaseService;
import com.bryan.dao.sys.domain.SysRole;
import com.bryan.dao.sys.domain.SysUser;

/**
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/22
 */
public interface SysRoleService extends BaseService<SysRole> {

    void saveSysRole(SysRole role, SysUser sessionUser);

    void updateSysRole(SysRole role, SysUser sessionUser);

}
