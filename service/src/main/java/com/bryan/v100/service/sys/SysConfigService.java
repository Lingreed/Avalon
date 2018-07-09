package com.bryan.v100.service.sys;

import com.bryan.common.base.BaseService;
import com.bryan.dao.sys.domain.SysConfig;

import java.util.Map;

/**
 * ClassName: SysConfig
 * Function:  service
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/5
 */
public interface SysConfigService extends BaseService<SysConfig> {
    /**
     * @return Map<String,String> 返回类型
     * @Title: findCacheMap
     * @Description: 查询系统配置map
     */
    Map<String, Object> findCacheMap();

    /**
     * 根据类别查询
     *
     * @param category 类别
     * @return
     */
    Map<String, String> findByCategory(String category);

    /**
     * @param cfgKey
     * @return String 返回类型
     * @Title: findValue
     * @Description: 根据key获取系统配置的value
     */
    String findValue(String cfgKey);
}
