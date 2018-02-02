package com.bryan.v100.sys.service.impl;

import com.bryan.common.base.BaseMapper;
import com.bryan.common.base.BaseServiceImpl;
import com.bryan.sys.domain.SysDictType;
import com.bryan.sys.domain.SysUser;
import com.bryan.sys.mapper.SysDictTypeMapper;
import com.bryan.v100.sys.service.SysDictTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/2
 */
@Service(value="sysDictTypeService")
@Transactional
public class SysDictTypeServiceImpl extends BaseServiceImpl<SysDictType> implements SysDictTypeService {

    @Resource
    private SysDictTypeMapper sysDictTypeMapper;

    @Override
    public BaseMapper<SysDictType> getMapper() {
        return sysDictTypeMapper;
    }

    @Override
    public void saveSysDictType(SysDictType dictType, SysUser sessionUser) {

    }

    @Override
    public void updateSysDictType(SysDictType dictType, SysUser sessionUser) {

    }
}
