package com.bryan.dao.scheduler.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/7/5
 */
@Table(name = "scheduler_task")
public class SchedulerTask implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 调度任务表达式
     */
    private String cron;

    /**
     * 调度任务路径
     */
    private String path;

    /**
     * 自定义调度组名称
     */
    @Column(name = "group_name")
    private String groupName;

    /**
     * 自定义任务名称
     */
    @Column(name = "job_name")
    private String jobName;

    /**
     * 自定义触发器名称
     */
    @Column(name = "triggers_name")
    private String triggersName;

    /**
     * 状态:10运行中,20已暂停
     */
    private String state;

    /**
     * 说明
     */
    private String taskExplain;

    /**
     *
     */
    private Date ctime;

    /**
     * 获取id
     *
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id 要设置的id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取调度任务表达式
     *
     * @return 调度任务表达式
     */
    public String getCron() {
        return cron;
    }

    /**
     * 设置调度任务表达式
     *
     * @param cron 要设置的调度任务表达式
     */
    public void setCron(String cron) {
        this.cron = cron;
    }

    /**
     * 获取调度任务路径
     *
     * @return 调度任务路径
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置调度任务路径
     *
     * @param path 要设置的调度任务路径
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 获取自定义调度组名称
     *
     * @return 自定义调度组名称
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * 设置自定义调度组名称
     *
     * @param groupName 要设置的自定义调度组名称
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * 获取自定义任务名称
     *
     * @return 自定义任务名称
     */
    public String getJobName() {
        return jobName;
    }

    /**
     * 设置自定义任务名称
     *
     * @param jobName 要设置的自定义任务名称
     */
    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    /**
     * 获取自定义触发器名称
     *
     * @return 自定义触发器名称
     */
    public String getTriggersName() {
        return triggersName;
    }

    /**
     * 设置自定义触发器名称
     *
     * @param triggersName 要设置的自定义触发器名称
     */
    public void setTriggersName(String triggersName) {
        this.triggersName = triggersName;
    }

    /**
     * 获取状态:10运行中,20已暂停
     *
     * @return 状态:10运行中,20已暂停
     */
    public String getState() {
        return state;
    }

    /**
     * 设置状态:10运行中,20已暂停
     *
     * @param state 要设置的状态:10运行中,20已暂停
     */
    public void setState(String state) {
        this.state = state;
    }

    public String getTaskExplain() {
        return taskExplain;
    }

    public void setTaskExplain(String taskExplain) {
        this.taskExplain = taskExplain;
    }

    /**
     * 获取
     *
     * @return
     */
    public Date getCtime() {
        return ctime;
    }

    /**
     * 设置
     *
     * @param ctime 要设置的
     */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

}
