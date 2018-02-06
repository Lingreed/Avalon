package com.bryan.common.config;

import com.bryan.common.constant.GlobalConstant;
import com.bryan.common.constant.redis.RedisConstant;
import com.bryan.common.redis.RedisTemplateUtil;

import java.math.BigDecimal;

/**
 * ClassName: SysConfig
 * Function:  系统配置工具类
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/6
 */
public class SysConfigUtil {
    /**
     * @param sysKey
     * @return String 返回类型
     * @Title: getString
     * @Description: 根据系统key，获取系统配置，值为String
     */
    public static String getString(SysKey sysKey) {
        return RedisTemplateUtil.hashGet(RedisConstant.CACHE_SYS_CONFIG, sysKey.getKey());
    }

    /**
     * @param sysKey
     * @return String 返回类型
     * @Title: getInteger
     * @Description: 根据系统key，获取系统配置，值为Integer
     */
    public static Integer getInteger(SysKey sysKey) {
        return Integer.parseInt((String) RedisTemplateUtil.hashGet(RedisConstant.CACHE_SYS_CONFIG, sysKey.getKey()));
    }

    /**
     * @param sysKey
     * @return String 返回类型
     * @Title: getValue
     * @Description: 根据系统key，获取系统配置，值为浮点型
     */
    public static BigDecimal getBigDecimal(SysKey sysKey) {
        return new BigDecimal((String) RedisTemplateUtil.hashGet(RedisConstant.CACHE_SYS_CONFIG, sysKey.getKey()));
    }

    /**
     * @return
     * @Title: isDebug
     * @Description:是否开启debug模式
     */
    public static boolean isDebug() {
        return GlobalConstant.STATE_ENABLE.equals(getString(SysKey.IS_DEBUG));
    }

    /**
     * @return
     * @Title: getYearDays
     * @Description: 每年天数
     */
    public static int getYearDays() {
        int yearDays = SysConfigUtil.getInteger(SysKey.YEAR_DAYS);
        if (yearDays <= 0) { // 默认365天
            yearDays = 365;
        }
        return yearDays;
    }
}
