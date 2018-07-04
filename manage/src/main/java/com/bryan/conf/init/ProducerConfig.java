package com.bryan.conf.init;

import com.bryan.common.mq.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
@Order(99)
public class ProducerConfig implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(ProducerConfig.class);
	
	@Override
	public void run(String... args) throws Exception {
		//启动rocketmq
	    Producer.initMqProducer();
	    logger.info("初始化rocketmq生产者,SUCCESS");
	}

}
