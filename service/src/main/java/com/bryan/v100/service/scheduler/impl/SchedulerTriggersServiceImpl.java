package com.bryan.v100.service.scheduler.impl;

import com.bryan.common.base.BaseMapper;
import com.bryan.common.base.BaseServiceImpl;
import com.bryan.dao.scheduler.domain.SchedulerTriggers;
import com.bryan.dao.scheduler.mapper.SchedulerTriggersMapper;
import com.bryan.v100.service.scheduler.SchedulerTriggersService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/7/9
 */
@Service(value = "schedulerTriggersService")
@Transactional
public class SchedulerTriggersServiceImpl extends BaseServiceImpl<SchedulerTriggers> implements SchedulerTriggersService {

    @Resource
    private SchedulerTriggersMapper schedulerTriggersMapper;

    @Override
    public BaseMapper<SchedulerTriggers> getMapper() {
        return schedulerTriggersMapper;
    }


}
