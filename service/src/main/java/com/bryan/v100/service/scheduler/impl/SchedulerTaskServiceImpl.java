package com.bryan.v100.service.scheduler.impl;

import com.bryan.common.base.BaseMapper;
import com.bryan.common.base.BaseServiceImpl;
import com.bryan.common.constant.scheduler.SchedulerConstant;
import com.bryan.common.utils.DateUtil;
import com.bryan.common.utils.OrderNoUtil;
import com.bryan.dao.scheduler.domain.SchedulerJob;
import com.bryan.dao.scheduler.domain.SchedulerTask;
import com.bryan.dao.scheduler.domain.SchedulerTriggers;
import com.bryan.dao.scheduler.mapper.SchedulerTaskMapper;
import com.bryan.v100.service.scheduler.SchedulerTaskService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

/**
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/7/9
 */
@Service(value = "schedulerTaskService")
@Transactional
public class SchedulerTaskServiceImpl extends BaseServiceImpl<SchedulerTask> implements SchedulerTaskService {

    @Resource
    private SchedulerTaskMapper schedulerTaskMapper;

    @Override
    public BaseMapper<SchedulerTask> getMapper() {
        return schedulerTaskMapper;
    }

    @Override
    public SchedulerTask findByGroupJobTriggerName(String groupName, String jobName, String triggerName) {
        SchedulerTask task = new SchedulerTask();
        task.setGroupName(groupName);
        task.setJobName(jobName);
        task.setTriggersName(triggerName);
        return selectOne(task);
    }

    /**
     * 更新调度task
     *
     * @param task
     * @param taskId
     */
    @Override
    public void updateScheduler(SchedulerTask task, Integer taskId) {
        Example example = new Example(SchedulerTask.class);
        example.createCriteria().andEqualTo("id", taskId);
        schedulerTaskMapper.updateByExampleSelective(task, example);
    }

    /**
     * 根据groupName删除
     *
     * @param groupName
     */
    @Override
    public void deleteByGroupName(String groupName) {
        Example example = new Example(SchedulerTask.class);
        example.createCriteria().andEqualTo("groupName", groupName);
        schedulerTaskMapper.deleteByExample(example);
    }

    /**
     * @param job
     * @param trigger
     * @Title: deployTask
     * @Description: 部署调度
     */
    @Override
    public SchedulerTask deployTask(SchedulerJob job, SchedulerTriggers trigger) {
        // 部署的时候,默认使用路径的最后方法名称命名
        String jobPath = job.getJobPath();
        String[] paths = jobPath.split("\\.");
        String groupName = "group_" + OrderNoUtil.getRandomNo() + "_" + paths[paths.length - 1];
        String jobName = "job_" + OrderNoUtil.getRandomNo() + "_" + paths[paths.length - 1];
        String triggerName = "trigger_" + OrderNoUtil.getRandomNo() + "_" + paths[paths.length - 1];
        SchedulerTask task = new SchedulerTask();
        task.setCron(trigger.getTriggersScript());
        task.setCtime(DateUtil.getNow());
        task.setGroupName(groupName);
        task.setId(null);
        task.setJobName(jobName);
        task.setPath(jobPath);
        task.setState(SchedulerConstant.OPT_TAG_DEPLOY);
        task.setTriggersName(triggerName);
        task.setTaskExplain(job.getJobExplain());

        schedulerTaskMapper.insertSelective(task);

        return task;
    }


}
