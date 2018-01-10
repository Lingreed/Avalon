package com.bryan.starter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@ComponentScan(value = "com.bryan",
        includeFilters = @ComponentScan.Filter(type=FilterType.ANNOTATION, value = {RestController.class, Controller.class}))
@EnableAsync
@EnableScheduling
@ServletComponentScan("com.bryan")
public class ManageApplication extends WebMvcConfigurerAdapter {
	private static final Logger logger = LoggerFactory.getLogger(ManageApplication.class);
    public static ConfigurableApplicationContext ctx = null;

    public static void main(String[] args) {
    	try {
			SpringApplication.run(ManageApplication.class, args);
		} catch (Exception e) {
			logger.error("ManageApplication run error", e);
		}
    }
}