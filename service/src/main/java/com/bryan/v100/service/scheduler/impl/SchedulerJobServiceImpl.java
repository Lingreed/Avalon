package com.bryan.v100.service.scheduler.impl;

import com.bryan.common.base.BaseMapper;
import com.bryan.common.base.BaseServiceImpl;
import com.bryan.dao.scheduler.domain.SchedulerJob;
import com.bryan.dao.scheduler.mapper.SchedulerJobMapper;
import com.bryan.v100.service.scheduler.SchedulerJobService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/7/9
 */
@Service(value = "schedulerJobService")
@Transactional
public class SchedulerJobServiceImpl extends BaseServiceImpl<SchedulerJob> implements SchedulerJobService {

    @Resource
    private SchedulerJobMapper schedulerJobMapper;

    @Override
    public BaseMapper<SchedulerJob> getMapper() {
        return schedulerJobMapper;
    }


}
