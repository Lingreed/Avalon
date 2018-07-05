package com.bryan.v100.service.scheduler;

import com.bryan.common.base.BaseService;
import com.bryan.dao.scheduler.domain.SchedulerJob;
import com.bryan.dao.scheduler.domain.SchedulerTask;
import com.bryan.dao.scheduler.domain.SchedulerTriggers;

/**
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/7/5
 */
public interface SchedulerTaskService extends BaseService<SchedulerTask> {

    /**
     * 查询任务
     */
    SchedulerTask findByGroupJobTriggerName(String groupName, String jobName, String triggerName);

    /**
     * 更新调度task
     *
     * @param task
     * @param taskId
     */
    void updateScheduler(SchedulerTask task, Integer taskId);

    /**
     * 根据groupName删除
     *
     * @param groupName
     */
    void deleteByGroupName(String groupName);

    /**
     * @param job
     * @param trigger
     * @Title: deployTask
     * @Description: 部署调度
     */
    SchedulerTask deployTask(SchedulerJob job, SchedulerTriggers trigger);

}