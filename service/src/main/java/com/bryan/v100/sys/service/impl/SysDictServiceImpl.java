package com.bryan.v100.sys.service.impl;

import com.bryan.common.base.BaseMapper;
import com.bryan.common.base.BaseServiceImpl;
import com.bryan.common.constant.GlobalConstant;
import com.bryan.common.constant.redis.RedisConstant;
import com.bryan.common.redis.RedisTemplateUtil;
import com.bryan.sys.domain.SysDict;
import com.bryan.sys.domain.SysDictType;
import com.bryan.sys.domain.SysUser;
import com.bryan.sys.mapper.SysDictMapper;
import com.bryan.v100.sys.service.SysDictService;
import com.bryan.v100.sys.service.SysDictTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/2
 */
@Service("sysDictService")
@Transactional
public class SysDictServiceImpl extends BaseServiceImpl<SysDict> implements SysDictService {

    @Resource
    private SysDictMapper sysDictMapper;

    @Resource
    private SysDictTypeService sysDictTypeService;

    @Override
    public BaseMapper<SysDict> getMapper() {
        return sysDictMapper;
    }

    @Override
    public void updateSysDict(SysDict dict, SysUser sessionUser) {

    }

    @Override
    public void saveSysDict(SysDict dict, SysUser sessionUser) {

    }

    @Override
    public Map<String, List<SysDict>> findInitDict() {
        return null;
    }

    @Override
    public Map<String, Map<String, String>> findDictMap() {
        // 字典列表
        Map<String,Map<String,String>> dictTypeMap = new HashMap<>();

        Example typeExample = new Example(SysDictType.class);
        typeExample.createCriteria().andEqualTo("state", GlobalConstant.STATE_ENABLE);
        List<SysDictType> dictTypeList = sysDictTypeService.selectByExample(typeExample);

        for (SysDictType dictType : dictTypeList) {
            Map<String,String> dictMap = new HashMap<>();
            // 查询字典项
            Example dictExample = new Example(SysDict.class);
            dictExample.createCriteria().andEqualTo("sysDictTypeId", dictType.getId());
            List<SysDict> dictList = super.selectByExample(dictExample);
            for (SysDict sysDict : dictList) {
                dictMap.put(sysDict.getDictCode(), sysDict.getDictValue());
            }
            dictTypeMap.put(dictType.getTypeCode(), dictMap);
        }

        return dictTypeMap;
    }

    @Override
    public String findDictValue(String typeCode, String dictCode) {
        return null;
    }
}
