package com.bryan.quartz.conf.mq;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.bryan.common.config.SysConfigUtil;
import com.bryan.common.config.SysKey;
import com.bryan.common.constant.mq.MqConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * 消费者配置,
 * 系统默认使用一个消费者
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/7/4
 */
@Configuration
@Order(value = 200)
public class ConsumerConfig implements CommandLineRunner {

    private static Logger logger = LoggerFactory.getLogger(ConsumerConfig.class);

    @Override
    public void run(String... args) throws Exception {
        try {

            DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(MqConstant.MQ_GROUP_NAME);

            consumer.setNamesrvAddr(SysConfigUtil.getString(SysKey.NAMESRV_ADDR));
            //consumer.setInstanceName("acctConsumer");默认不设置名称

            //订阅指定topic_p2p下所有的tag
            consumer.subscribe(MqConstant.TOPIC, "*");

            //设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费,如果非第一次启动，那么按照上次消费的位置继续消费
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

            //默认msgs里只有一条消息，可以通过设置consumeMessageBatchMaxSize参数来批量接收消息
            consumer.registerMessageListener(new MqListener());

            consumer.start();//Consumer对象在使用之前必须要调用start初始化，初始化一次即可
            logger.info("consumer started....");
        } catch (Exception e) {
            logger.error("consumer start error.", e);
        }
    }
}
