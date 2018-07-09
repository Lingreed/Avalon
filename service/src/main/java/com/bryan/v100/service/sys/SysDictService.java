package com.bryan.v100.service.sys;

import com.bryan.common.base.BaseService;
import com.bryan.dao.sys.domain.SysDict;

import java.util.List;
import java.util.Map;

/**
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/2
 */
public interface SysDictService extends BaseService<SysDict> {

    void updateSysDict(SysDict dict);

    void saveSysDict(SysDict dict);

    /**
     * 查询初始化字典列表
     */
    Map<String, List<SysDict>> findInitDict();

    /**
     * 返回字典redis缓存
     *
     * @return
     */
    Map<String, Map<String, String>> findDictMap();

    /**
     * 根据字典项类型及字典code,查询字典值
     *
     * @param typeCode
     * @param dictCode
     * @return
     */
    String findDictValue(String typeCode, String dictCode);
}
