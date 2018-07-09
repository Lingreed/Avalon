package com.bryan.quartz.conf.mq;

import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.bryan.common.constant.mq.MqConstant;
import com.bryan.quartz.task.QuartzSchedulerTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/7/4
 */
public class MqListener implements MessageListenerConcurrently {

    private static final Logger logger = LoggerFactory.getLogger(MqListener.class);

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext paramConsumeConcurrentlyContext) {
        try {
            for (MessageExt msg : msgs) {
                String topic = msg.getTopic();
                String body = new String(msg.getBody());
                String tags = msg.getTags();
                String keys = msg.getKeys();
                String msgId = msg.getMsgId();
                logger.info("消费消息开始,topic:{},tags:{},keys:{},msgId:{}", topic, tags, keys, msgId);

                if (MqConstant.TOPIC_TAG_SCHEDULER.equals(tags)) {
                    // 调度消息
                    new QuartzSchedulerTask().doTask(body);

                } else if (MqConstant.TOPIC_TAG_PROJECT.equals(tags)) {
                    // 项目消息
//                    new ProjectTask().doTask(body);

                } else if (MqConstant.TOPIC_TAG_ACTT.equals(tags)) {
                    // 活动消息
//                    new ActtTask().doTask(body);

                } else if (MqConstant.TOPIC_TAG_MSG.equals(tags)) {
                    // 短信邮件站内信
//                    new MsgTask().doTask(body);

                } else if (MqConstant.TOPIC_TAG_BANK.equals(tags)) {
                    // 银行api接口
//                    new BankTask().doTask(body);

                } else if (MqConstant.TOPIC_TAG_STAT.equals(tags)) {
                    // 统计
//                    new StatisticsTask().doTask(body);

                } else if (MqConstant.TOPIC_TAG_DAY.equals(tags)) {
                    // 节日
//                    new DayTask().doTask(body);

                } else {
                    logger.info("tags不在业务范围内，后续消费");
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                }
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        } catch (Exception e) {
            logger.error("mq监听消息异常:{}", e);
            // 异常重新触发
            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
        }
    }
}
