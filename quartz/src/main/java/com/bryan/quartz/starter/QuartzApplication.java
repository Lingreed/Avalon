package com.bryan.quartz.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@ComponentScan(value = "com.bryan")
@ServletComponentScan("com.bryan")
@EnableAsync
@EnableScheduling
public class QuartzApplication extends WebMvcConfigurerAdapter {
	
    public static ConfigurableApplicationContext ctx = null;

    public static void main(String[] args) {
    	
    	SpringApplication.run(QuartzApplication.class,args);
    	
    }
}