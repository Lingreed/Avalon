package com.bryan.quartz.conf.quartz;

import com.bryan.common.exception.ServiceException;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.io.IOException;
import java.util.Properties;

/**
 * 定时任务配置
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/7/5
 */
@Configuration
@Order(99999)// 放在最后启动
public class QuartzConfig {

    private static final Logger logger = LoggerFactory.getLogger(QuartzConfig.class);

    @Bean
    public Scheduler scheduler() {
        try {
            SchedulerFactory schedulerFactory = new StdSchedulerFactory(quartzProperties());
            Scheduler scheduler = schedulerFactory.getScheduler();
            scheduler.start();
            return scheduler;
        } catch (Exception e) {
            logger.error("初始化quartz-job错误", e);
            throw new ServiceException("初始化quartz-job错误");
        }
    }

    public Properties quartzProperties() throws IOException {
        Properties prop = new Properties();
        prop.put("org.quartz.scheduler.instanceName", "ServerScheduler");
        prop.put("org.quartz.scheduler.instanceId", "AUTO");

        prop.put("org.quartz.scheduler.rmi.export", "false");
        prop.put("org.quartz.scheduler.rmi.proxy", "false");
        prop.put("org.quartz.scheduler.wrapJobExecutionInUserTransaction", "false");

        prop.put("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
        prop.put("org.quartz.threadPool.threadCount", "500");
        prop.put("org.quartz.threadPool.threadPriority", "9");
        prop.put("org.quartz.threadPool.threadsInheritContextClassLoaderOfInitializingThread", "true");

        prop.put("org.quartz.jobStore.class", "org.quartz.impl.jdbcjobstore.JobStoreTX");
        prop.put("org.quartz.jobStore.driverDelegateClass", "org.quartz.impl.jdbcjobstore.StdJDBCDelegate");
        prop.put("org.quartz.jobStore.misfireThreshold", "60000");
        prop.put("org.quartz.jobStore.useProperties", "false");
        prop.put("org.quartz.jobStore.tablePrefix", "QRTZ_");
        prop.put("org.quartz.jobStore.dataSource", "myDS");
        prop.put("org.quartz.jobStore.isClustered", "false");
        prop.put("org.quartz.jobStore.clusterCheckinInterval", "15000");

        prop.put("org.quartz.dataSource.myDS.connectionProvider.class", "com.yogu.p2p.quartz.conf.quartz.DruidConnectionProvider");

        // 由于重写了connectionProvider,因此下面的配置不需要了.直接使用datasource
        //prop.put("org.quartz.dataSource.myDS.driver", jdbcParams.getDriver());
        //prop.put("org.quartz.dataSource.myDS.URL", jdbcParams.getUrl());
        //prop.put("org.quartz.dataSource.myDS.user", jdbcParams.getUser());
        //prop.put("org.quartz.dataSource.myDS.password", jdbcParams.getPassword());
        //prop.put("org.quartz.dataSource.myDS.maxConnections", "30");
        //prop.put("org.quartz.dataSource.myDS.validationQuery", "select count(1)");

        return prop;
    }

}
