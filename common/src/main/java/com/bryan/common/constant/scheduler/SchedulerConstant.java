package com.bryan.common.constant.scheduler;

/**
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/7/5
 */
public class SchedulerConstant {

    public static final String biz_type = "";

    /**
     * 运行,代表调度已经部署成功\执行成功
     */
    public static final String SCHEDULER_STATE_RUN = "10";

    /**
     * 暂停,代表调度已经被暂停
     */
    public static final String SCHEDULER_STATE_STOP = "20";

    /**
     * 立即执行,tag=10
     */
    public static final String OPT_TAG_INSTALL = "10";

    /**
     * 立即部署,tag=20
     */
    public static final String OPT_TAG_DEPLOY = "20";

    /**
     * 立即删除,tag=30
     */
    public static final String OPT_TAG_DELETE = "30";

    /**
     * 立即暂停,tag=40
     */
    public static final String OPT_TAG_STOP = "40";

    /**
     * 恢复,tag=50
     */
    public static final String OPT_TAG_RECOVERY = "50";

}