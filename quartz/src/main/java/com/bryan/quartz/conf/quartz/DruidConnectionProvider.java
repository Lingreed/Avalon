package com.bryan.quartz.conf.quartz;

import com.alibaba.druid.pool.DruidDataSource;
import com.bryan.common.utils.SpringContextHolder;
import org.quartz.utils.ConnectionProvider;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/7/5
 */
public class DruidConnectionProvider implements ConnectionProvider {

    private DruidDataSource dataSource;

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    @Override
    public void shutdown() throws SQLException {
        dataSource.close();
    }

    @Override
    public void initialize() throws SQLException {
        dataSource = SpringContextHolder.getBean("dataSource");
    }

}
