package com.bryan.conf.destory;

import com.bryan.common.mq.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.context.annotation.Configuration;

import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

@Configuration
public class DestroyConfig implements DisposableBean, ExitCodeGenerator {
	private static final Logger logger = LoggerFactory.getLogger(DestroyConfig.class);

	@Override
	public int getExitCode() {
		return 5;
	}

	@Override
	public void destroy() throws Exception {
		Producer.stopProducer();
		Enumeration<Driver> drivers = DriverManager.getDrivers();
		
		while (drivers.nextElement() != null) {
			DriverManager.deregisterDriver(drivers.nextElement());
		}
		logger.info("清除资源");
	}	

	
}
