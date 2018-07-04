package com.bryan.common.constant.mq;

public class MqConstant {
	
	/**
	 * rocketmq 组名称
	 * 暂且不使用分布式事务消息
	 */
	public static final String MQ_GROUP_NAME = "mq_yogu_p2p";
	
	/**
	 * 统一topic
	 */
	public static final String TOPIC = "topic_p2p";
	
	/**
	 * tag 调度
	 */
	public static final String TOPIC_TAG_SCHEDULER = "tag_scheduler";
	
	/**
	 * tag 项目
	 */
	public static final String TOPIC_TAG_PROJECT = "tag_project";
	
	/**
	 * tag 活动
	 */
	public static final String TOPIC_TAG_ACTT = "tag_actt";
	
	/**
	 * tag 消息(邮件\短信\站内信)
	 */
	public static final String TOPIC_TAG_MSG = "tag_msg";
	
	/**
	 * tag 银行接口
	 */
	public static final String TOPIC_TAG_BANK = "tag_bank";

	/**
	 * tag 统计相关处理
	 */
	public static final String TOPIC_TAG_STAT = "tag_stat";

    /**
     * tag 节日活动处理
     */
    public static final String TOPIC_TAG_DAY = "tag_day";

	/**
	 * 业务区分字段标识
	 */
	public static final String BIZ_TYPE = "bizType";
	
	
}
