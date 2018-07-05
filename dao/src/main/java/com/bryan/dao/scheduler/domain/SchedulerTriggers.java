package com.bryan.dao.scheduler.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/7/5
 */
@Table(name = "scheduler_triggers")
public class SchedulerTriggers implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 定时器名称
     */
    @Column(name = "triggers_name")
    private String triggersName;

    /**
     * 定时器脚本
     */
    @Column(name = "triggers_script")
    private String triggersScript;

    /**
     * 定时器说明
     */
    @Column(name = "triggers_explain")
    private String triggersExplain;

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
     * 获取定时器名称
     *
     * @return 定时器名称
     */
    public String getTriggersName() {
        return triggersName;
    }

    /**
     * 设置定时器名称
     *
     * @param triggersName 要设置的定时器名称
     */
    public void setTriggersName(String triggersName) {
        this.triggersName = triggersName;
    }

    /**
     * 获取定时器脚本
     *
     * @return 定时器脚本
     */
    public String getTriggersScript() {
        return triggersScript;
    }

    /**
     * 设置定时器脚本
     *
     * @param triggersScript 要设置的定时器脚本
     */
    public void setTriggersScript(String triggersScript) {
        this.triggersScript = triggersScript;
    }

    /**
     * 获取定时器说明
     *
     * @return 定时器说明
     */
    public String getTriggersExplain() {
        return triggersExplain;
    }

    /**
     * 设置定时器说明
     *
     * @param triggersExplain 要设置的定时器说明
     */
    public void setTriggersExplain(String triggersExplain) {
        this.triggersExplain = triggersExplain;
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
