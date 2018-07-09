package com.bryan.quartz.conf.db;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import java.util.Properties;

@Configuration
@AutoConfigureAfter(MyBatisConfig.class)
public class MyBatisMapperScannerConfig {
	
	@Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("com.bryan.dao.**.mapper");
        Properties properties = new Properties();
        // 里要特别注意，不要把MyMapper放到 basePackage中，也就是不能同其他Mapper一样被扫描到。 
        properties.setProperty("mappers", "com.bryan.common.base.BaseMapper");
        properties.setProperty("notEmpty", "false");
        properties.setProperty("IDENTITY", "MYSQL");
        properties.setProperty("ORDER","AFTER");
        mapperScannerConfigurer.setProperties(properties);
        return mapperScannerConfigurer;
    }
}
