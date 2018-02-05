package com.bryan.conf.init;

import com.bryan.common.constant.redis.RedisConstant;
import com.bryan.common.redis.RedisTemplateUtil;
import com.bryan.v100.sys.service.SysDictService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.annotation.Resource;

/**
 * ClassName: DictConfig
 * Function:  字典项启动加载
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/2
 */
@Configuration
@Order(98)
public class DictInit implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(DictInit.class);

    @Resource
    private SysDictService sysDictService;

    @Override
    public void run(String... args) throws Exception {
        // 缓存新的字典,每次后台启动都会覆盖缓存
        RedisTemplateUtil.hashSet(RedisConstant.CACHE_SYS_DICT, sysDictService.findDictMap());
        logger.info("初始化,字典项到redis,SUCCESS");
    }
}
