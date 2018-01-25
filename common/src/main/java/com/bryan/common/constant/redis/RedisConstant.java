package com.bryan.common.constant.redis;

/**
 * ClassName: RedisConstant
 * Function: redis缓存常量
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/1/25
 */
public class RedisConstant {
    /**
     * token 过期时间  测试环境下24小时,其他情况下30分钟;
     */
    public static final int TOKEN_TIME_OUT = 1*30*60;

    /**
     * 系统字典项缓存
     */
    public static final String CACHE_SYS_DICT = "cache_sys_dict";

    /**
     * 资金日志模版缓存
     */
    public static final String CACHE_LOG_TEMPLATE = "cache_log_template";

    /**
     * 系统配置缓存
     */
    public static final String CACHE_SYS_CONFIG = "cache_sys_config";

    /**
     * 富有配置缓存
     */
    public static final String CACHE_FUYOU = "cache_fuyou";

    /**
     * 系统菜单缓存
     */
    public static final String CACHE_SYS_MENU = "cache_sys_menu";

    /**
     * 角色菜单缓存
     */
    public static final String CACHE_ROLE_MENU = "cache_role_menu";

    /**
     * 角色菜单href缓存
     */
    public static final String CACHE_ROLE_MENU_HREF = "cache_role_menu_href";

    /**
     * 登录加载初始化字典缓存
     */
    public static final String CACHE_INIT_DICT = "cache_init_dict";

    /**
     * 系统曝光数据
     */
    public static final String SYS_EXPO_DATA = "sys_expo_data";

    /**
     * 系统曝光数据上次计算时间点
     */
    public static final String SYS_EXPO_DATA_DATE = "sys_expo_data_date";

    /**
     * 昨日系统曝光数据
     */
    public static final String SYS_EXPO_DATA_YESTERDAY = "sys_expo_data_yesterday";

    /**
     * 校验手机验证码
     */
    public static final String CHECK_SMS_CODE = "check_sms_code_";
}
