package com.bryan.v100.sys.service;

import com.bryan.common.base.BaseService;
import com.bryan.sys.domain.SysDictType;
import com.bryan.sys.domain.SysUser;

/**
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/2
 */
public interface SysDictTypeService extends BaseService<SysDictType> {
    /**
     * 保存字典类型
     *
     * @param dictType
     * @param sessionUser
     */
    void saveSysDictType(SysDictType dictType, SysUser sessionUser);

    /**
     * 更新字典类型
     *
     * @param dictType
     * @param sessionUser
     */
    void updateSysDictType(SysDictType dictType, SysUser sessionUser);
}
