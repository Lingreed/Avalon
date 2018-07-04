package com.bryan.quartz.conf.db;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * 数据源配置
 */
@Configuration
@EnableTransactionManagement
@Order(1)
public class DataSourceConfig {
    @Autowired
    private JdbcParams jdbcParams;

    @Bean(name = "dataSource")
    @Primary
    public DataSource dataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        try {
            druidDataSource.setDriverClassName(jdbcParams.getDriver());
            druidDataSource.setUrl(jdbcParams.getUrl());
            druidDataSource.setUsername(jdbcParams.getUser());
            druidDataSource.setPassword(jdbcParams.getPassword());
            druidDataSource.setInitialSize(jdbcParams.getInitialSize());
            druidDataSource.setMinIdle(jdbcParams.getMinIdle());
            druidDataSource.setMaxActive(jdbcParams.getMaxActive());
            druidDataSource.setFilters(jdbcParams.getFilters());
            
            druidDataSource.setDefaultAutoCommit(false);

            //获取链接最大等待时间,单位毫秒
            druidDataSource.setMaxWait(10000);
            
            //testWhileIdle的判断依据，详细看testWhileIdle属性的说明
            druidDataSource.setTimeBetweenEvictionRunsMillis(100000);
            //连接保持空闲而不被驱逐的最长时间
            druidDataSource.setMinEvictableIdleTimeMillis(100000);
            
            //不影响性能，且保证安全性。 申请连接的时候检测，如果空闲时间大于 timeBetweenEvictionRunsMillis
            //执行validationQuery检测连接是否有效
            druidDataSource.setTestWhileIdle(true);
            
            //归还连接时执行validationQuery检测连接是否有效， 做了这个配置会降低性能
            druidDataSource.setTestOnBorrow(false);
            //归还连接时执行validationQuery检测连接是否有效， 做了这个配置会降低性能
            druidDataSource.setTestOnReturn(false);
            
            druidDataSource.setPoolPreparedStatements(jdbcParams.getPoolPreparedStatements());
            druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(jdbcParams.getMaxPoolPreparedStatementPerConnectionSize());
           
            druidDataSource.setFilters("stat,wall");
        } catch (SQLException e) {
            throw new RuntimeException("DruidDataSource init failed!");
        }
        return druidDataSource;
    }
}