package com.bryan.conf.init;

import com.bryan.common.constant.redis.RedisConstant;
import com.bryan.common.redis.RedisTemplateUtil;
import com.bryan.v100.sys.service.SysConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.annotation.Resource;

/**
 * ClassName: SysConfig
 * Function:  初始化系统配置
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/5
 */
@Configuration
@Order(98)
public class SystemInit implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(SystemInit.class);

    @Resource
    private SysConfigService configService;

    @Override
    public void run(String... args) throws Exception {
		/*初始化系统配置*/
        RedisTemplateUtil.hashSet(RedisConstant.CACHE_SYS_CONFIG, configService.findCacheMap());
        logger.info("初始化,系统配置到redis,,SUCCESS");
    }
}
