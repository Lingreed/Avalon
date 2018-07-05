package com.bryan.v100.service.sys.impl;

import com.bryan.common.base.BaseMapper;
import com.bryan.common.base.BaseServiceImpl;
import com.bryan.sys.domain.SysLogManage;
import com.bryan.sys.mapper.SysLogManageMapper;
import com.bryan.v100.service.sys.SysLogManageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * ClassName: SysLogManage
 * Function: 系统后台日志 serviceImpl
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/1
 */
@Service(value = "sysLogManageService")
@Transactional
public class SysLogManageServiceImpl extends BaseServiceImpl<SysLogManage> implements SysLogManageService {

    @Resource
    private SysLogManageMapper sysLogManageMapper;

    @Override
    public BaseMapper<SysLogManage> getMapper() {
        return sysLogManageMapper;
    }
}
