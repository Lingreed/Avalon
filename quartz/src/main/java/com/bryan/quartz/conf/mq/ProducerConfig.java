package com.bryan.quartz.conf.mq;

import com.bryan.common.mq.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * 初始化生产者
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/7/4
 */
@Configuration
@Order(value = 100)
public class ProducerConfig implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(ProducerConfig.class);

    @Override
    public void run(String... args) throws Exception {
        //启动rocketmq
        Producer.initMqProducer();
        logger.info("初始化rocketmq生产者,SUCCESS");
    }
}
