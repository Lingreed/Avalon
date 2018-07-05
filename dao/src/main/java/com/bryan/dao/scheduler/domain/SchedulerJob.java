package com.bryan.dao.scheduler.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/7/5
 */
@Table(name = "scheduler_job")
public class SchedulerJob implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 任务名称
     */
    @Column(name = "job_name")
    private String jobName;

    /**
     * 任务路径
     */
    @Column(name = "job_path")
    private String jobPath;

    /**
     * 任务说明
     */
    @Column(name = "job_explain")
    private String jobExplain;

    /**
     * 状态10启用,20禁用
     */
    private String state;

    /**
     * 添加时间
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
     * 获取任务名称
     *
     * @return 任务名称
     */
    public String getJobName() {
        return jobName;
    }

    /**
     * 设置任务名称
     *
     * @param jobName 要设置的任务名称
     */
    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    /**
     * 获取任务路径
     *
     * @return 任务路径
     */
    public String getJobPath() {
        return jobPath;
    }

    /**
     * 设置任务路径
     *
     * @param jobPath 要设置的任务路径
     */
    public void setJobPath(String jobPath) {
        this.jobPath = jobPath;
    }

    /**
     * 获取任务说明
     *
     * @return 任务说明
     */
    public String getJobExplain() {
        return jobExplain;
    }

    /**
     * 设置任务说明
     *
     * @param jobExplain 要设置的任务说明
     */
    public void setJobExplain(String jobExplain) {
        this.jobExplain = jobExplain;
    }

    /**
     * 获取状态10启用,20禁用
     *
     * @return 状态10启用, 20禁用
     */
    public String getState() {
        return state;
    }

    /**
     * 设置状态10启用,20禁用
     *
     * @param state 要设置的状态10启用,20禁用
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * 获取添加时间
     *
     * @return 添加时间
     */
    public Date getCtime() {
        return ctime;
    }

    /**
     * 设置添加时间
     *
     * @param ctime 要设置的添加时间
     */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

}
