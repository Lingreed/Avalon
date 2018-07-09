package com.bryan.v100.service.sys.impl;

import com.bryan.common.base.BaseMapper;
import com.bryan.common.base.BaseServiceImpl;
import com.bryan.common.constant.GlobalConstant;
import com.bryan.dao.sys.domain.SysConfig;
import com.bryan.dao.sys.mapper.SysConfigMapper;
import com.bryan.v100.service.sys.SysConfigService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: SysConfig
 * Function:  serviceImpl
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/5
 */
@Service(value = "sysConfigService")
@Transactional
public class SysConfigServiceImpl extends BaseServiceImpl<SysConfig> implements SysConfigService {
    @Resource
    private SysConfigMapper sysConfigMapper;

    @Override
    public BaseMapper<SysConfig> getMapper() {
        return sysConfigMapper;
    }

    /**
     * @return Map<String,String> 返回类型
     * @Title: findCacheMap
     * @Description: 查询系统配置map
     */
    @Override
    public Map<String, Object> findCacheMap() {
        SysConfig searchConfig = new SysConfig();
        searchConfig.setState(GlobalConstant.STATE_ENABLE);
        searchConfig.setCache(GlobalConstant.PUB_ENABLE);
        List<SysConfig> list = sysConfigMapper.select(searchConfig);

        Map<String, Object> map = new HashMap<>();
        for (SysConfig sysConfig : list) {
            map.put(sysConfig.getCfgKey(), sysConfig.getCfgValue());
        }
        return map;
    }

    /**
     * 根据类别查询
     *
     * @param category 类别
     * @return
     */
    @Override
    public Map<String, String> findByCategory(String category) {
        SysConfig searchConfig = new SysConfig();
        searchConfig.setState(GlobalConstant.STATE_ENABLE);
        searchConfig.setCategory(category);
        List<SysConfig> list = sysConfigMapper.select(searchConfig);

        Map<String, String> map = new HashMap<>();
        for (SysConfig sysConfig : list) {
            map.put(sysConfig.getCfgKey(), sysConfig.getCfgValue());
        }
        return map;
    }

    /**
     * @param cfgKey
     * @return String 返回类型
     * @Title: findValue
     * @Description: 根据key获取系统配置的value
     */
    @Override
    public String findValue(String cfgKey) {
        SysConfig cfg = new SysConfig();
        cfg.setCfgKey(cfgKey);
        cfg.setState(GlobalConstant.STATE_ENABLE);
        SysConfig dbCfg = sysConfigMapper.selectOne(cfg);
        if (dbCfg == null) {
            return null;
        } else {
            return dbCfg.getCfgValue();
        }
    }
}
