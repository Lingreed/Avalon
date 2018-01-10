package com.bryan.conf.web;

import com.bryan.starter.ManageApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * 项目打包成war，放在tomcat中运行必备类
 */
public class ServletInitializer extends SpringBootServletInitializer {
	@Override
	  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	    return application.sources(ManageApplication.class);
	  }
}
