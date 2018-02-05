package com.bryan.conf.web;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;
import com.bryan.common.exception.ServiceException;
import com.bryan.conf.interceptor.ResourceInterceptor;
import com.bryan.conf.filter.CrosFilter;
import com.bryan.conf.filter.SessionFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.io.File;
import java.util.List;

/**
 * springMVC的HttpMessageConverter设置
 */
@Configuration
@ComponentScan(useDefaultFilters = false,
        includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = {RestController.class, Controller.class}))
public class MvcConfig extends WebMvcConfigurationSupport {

    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        
        FastJsonHttpMessageConverter4 fastConverter = new FastJsonHttpMessageConverter4();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat,
        		SerializerFeature.WriteMapNullValue,
        		SerializerFeature.WriteNullNumberAsZero,
        		SerializerFeature.WriteNullStringAsEmpty,
        		SerializerFeature.WriteNullBooleanAsFalse,
        		SerializerFeature.WriteDateUseDateFormat,
        		SerializerFeature.WriteNullListAsEmpty,
        		SerializerFeature.DisableCircularReferenceDetect
        		);
        fastConverter.setFastJsonConfig(fastJsonConfig);
        converters.add(fastConverter);
        addDefaultHttpMessageConverters(converters);
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
    	// Global ThreadLocal 资源清理
    	registry.addInterceptor(new ResourceInterceptor()).addPathPatterns("/**");
    }
    
    /**
     * @Title: CrosFilter
     * @Description: 跨域拦截器
     * @return
     */
    @Bean
    public FilterRegistrationBean crosFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new CrosFilter());
        registration.addUrlPatterns("/*");
        registration.setName("crosFilter");
        registration.setOrder(1);
        
        return registration;
    }
    
    /**
     * @Title: SessionFilter
     * @Description: 权限过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean sessionFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new SessionFilter());
        registration.addUrlPatterns("/*");
        registration.setName("sessionFilter");
        registration.setOrder(100);

        return registration;
    }
    
    @Bean
    public CommonsMultipartResolver multipartResolver() {
    	CommonsMultipartResolver resolver = null;
    	String tmpPath = "/data/img/tmp/";
    	try {
    		File file = new File(tmpPath);
    		if(!file.exists()){
    			file.mkdir();
    	    }
    		resolver = new CommonsMultipartResolver();
    		resolver.setDefaultEncoding("UTF-8");
    		resolver.setResolveLazily(true);
    		// 默认的是10240,最小的图片是10k;这里改成1k.
    		resolver.setMaxInMemorySize(1024);
    		resolver.setUploadTempDir(new FileSystemResource("/data/img/tmp/"));
    		resolver.setMaxUploadSize(2*1024*1024);//上传文件大小 2M 2*1024*1024
    		return resolver;
		} catch (Exception e) {
			throw new ServiceException("图片上传,设置临时文件目录错误");
		}
    }
}