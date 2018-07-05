package com.bryan.quartz.scheduler;

import com.bryan.common.constant.scheduler.SchedulerConstant;
import com.bryan.common.utils.Reflections;
import com.bryan.common.utils.SpringContextHolder;
import com.bryan.dao.scheduler.domain.SchedulerTask;
import com.bryan.v100.service.scheduler.SchedulerTaskService;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.mybatis.mapper.entity.Example;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/7/5
 */
public class JobRegisterHandle {

    private static final Logger _log = LoggerFactory.getLogger(JobRegisterHandle.class);

    private static SchedulerTaskService schedulerTaskService = SpringContextHolder.getBean(SchedulerTaskService.class);

    private static Scheduler scheduler = SpringContextHolder.getBean(Scheduler.class);

    /**
     * 部署调度任务
     * @param model
     * @throws SchedulerException
     */
    public static void deployScheduler(SchedulerTask model) throws SchedulerException {
        _log.info("任务路径：{}", model.getPath());
        Class<? extends Job> aClaz = Reflections.reflex(model.getPath());
        JobDetail job = newJob(aClaz).withIdentity(model.getJobName(),model.getGroupName()).build();
        // //创建一个触发器的名称
        Trigger trigger = newTrigger().withIdentity(model.getTriggersName(), model.getGroupName())
                .withSchedule(cronSchedule(model.getCron())).startNow().build();
        // 设置调度相关的Job
        scheduler.scheduleJob(job, trigger);

        // 部署成功,修改状态位运行
        model.setState(SchedulerConstant.SCHEDULER_STATE_RUN);
        schedulerTaskService.updateScheduler(model, model.getId());
    }

    /**
     * 暂停调度任务
     *
     * @param model
     * @throws SchedulerException
     */
    public static void suspendScheduler(SchedulerTask model) throws SchedulerException {
        Class<? extends Job> aClaz = Reflections.reflex(model.getPath());
        JobDetail job = newJob(aClaz).withIdentity(model.getJobName(), model.getGroupName()).build();
        scheduler.pauseJob(job.getKey());

        // 部署成功,修改状态位运行
        model.setState(SchedulerConstant.SCHEDULER_STATE_STOP);
        schedulerTaskService.updateScheduler(model, model.getId());
    }

    /**
     * 批量暂停所有调度任务
     * @throws SchedulerException
     */
    public static void batchesSuspendScheduler() throws SchedulerException {
        scheduler.pauseAll();
    }

    /**
     * 恢复调度任务
     * @param model
     * @throws SchedulerException
     */
    public static void recoveryScheduler(SchedulerTask model) throws SchedulerException {
        Class<? extends Job> aClaz = Reflections.reflex(model.getPath());
        // 设置调度相关的Job
        JobDetail job = newJob(aClaz).withIdentity(model.getJobName(), model.getGroupName()).build();
        //异常往外抛
        scheduler.resumeJob(job.getKey());

        // 部署成功,修改状态位运行
        model.setState(SchedulerConstant.SCHEDULER_STATE_RUN);
        schedulerTaskService.updateScheduler(model, model.getId());
    }

    /**
     * 调度立即执行
     *
     * @param model
     * @throws SchedulerException
     */
    public static void instantScheduler(SchedulerTask model) throws SchedulerException {
        Class<? extends Job> aClaz = Reflections.reflex(model.getPath());
        // 设置调度相关的Job
        JobDetail job = newJob(aClaz).withIdentity(model.getJobName(), model.getGroupName()).build();
        scheduler.triggerJob(job.getKey());
    }

    /**
     * 批量暂停所有调度任务
     *
     * @throws SchedulerException
     */
    public static void batchesRecoveryScheduler() throws SchedulerException {
        scheduler.resumeAll();
    }

    /**
     * 删除调度任务
     *
     * @param model
     * @throws SchedulerException
     */
    public static void deleteScheduler(SchedulerTask model) throws SchedulerException {
        Class<? extends Job> aClaz = Reflections.reflex(model.getPath());
        JobDetail job = newJob(aClaz).withIdentity(model.getJobName(), model.getGroupName()).build();
        scheduler.deleteJob(job.getKey());

        // 部署成功,修改状态位运行
        model.setState(SchedulerConstant.SCHEDULER_STATE_RUN);
        schedulerTaskService.deleteByGroupName(model.getGroupName());
    }

    /**
     * 删除任务列表
     * @param groupName
     */
    public static void deleteTaskInfoChange(String groupName){
        Example example = new Example(SchedulerTask.class);
        example.createCriteria().andEqualTo("groupName",groupName);
        schedulerTaskService.deleteByExample(example);
    }
}
