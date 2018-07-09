package com.bryan.v100.service.sys.impl;

import com.bryan.common.base.BaseMapper;
import com.bryan.common.base.BaseServiceImpl;
import com.bryan.common.constant.GlobalConstant;
import com.bryan.common.constant.redis.RedisConstant;
import com.bryan.common.exception.ServiceException;
import com.bryan.common.redis.RedisTemplateUtil;
import com.bryan.common.utils.DateUtil;
import com.bryan.dao.sys.domain.SysDict;
import com.bryan.dao.sys.domain.SysDictType;
import com.bryan.dao.sys.mapper.SysDictMapper;
import com.bryan.v100.service.sys.SysDictService;
import com.bryan.v100.service.sys.SysDictTypeService;
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

    /**
     * 更新字典
     *
     * @param dict
     */
    @Override
    public void updateSysDict(SysDict dict) {
        SysDict dbDict = super.selectByPrimaryKey(dict.getId());
        if (dbDict == null) {
            throw new ServiceException("查询字典不存在");
        }
        //判断是否更新标识,不允许修改
        dict.setDictCode(null);

        dict.setMtime(DateUtil.getNow());
        int count = super.updateByPrimaryKeySelective(dict);
        if (count != 1) {
            throw new ServiceException("更新字典错误");
        }
        // 删除缓存登录字典
        RedisTemplateUtil.delete(RedisConstant.CACHE_INIT_DICT);
    }

    /**
     * 保存字典项
     *
     * @param dict
     */
    @Override
    public void saveSysDict(SysDict dict) {
        //判断类型是否存在
        SysDictType dictType = sysDictTypeService.selectByPrimaryKey(dict.getSysDictTypeId());
        if (dictType == null) {
            throw new ServiceException("字典类型查询失败");
        }
        //校验字典的标识不能为空
        Example example = new Example(SysDict.class);
        Example.Criteria codeParam = example.createCriteria();
        codeParam.andEqualTo("dictCode", dict.getDictCode());
        SysDict dbDict = super.selectOneByExample(example);
        if (dbDict != null) {
            throw new ServiceException("字典标识重复,请重新填写");
        }

        dict.setCtime(DateUtil.getNow());
        super.save(dict);

        // 删除缓存登录字典
        RedisTemplateUtil.delete(RedisConstant.CACHE_INIT_DICT);
    }

    /**
     * 查询初始化字典列表
     */
    @Override
    public Map<String, List<SysDict>> findInitDict() {
        Map<String, List<SysDict>> initDictMap = RedisTemplateUtil.get(RedisConstant.CACHE_INIT_DICT);
        if (initDictMap == null || initDictMap.isEmpty()) {
            // 查询库,并缓存
            Map<String, List<SysDict>> dictMap = new HashMap<>();

            Example typeExample = new Example(SysDictType.class);
            typeExample.createCriteria().andEqualTo("state", GlobalConstant.STATE_ENABLE);
            List<SysDictType> dictTypeList = sysDictTypeService.selectByExample(typeExample);

            for (SysDictType dictType : dictTypeList) {
                // 查询字典项
                Example dictExample = new Example(SysDict.class);
                dictExample.createCriteria().andEqualTo("sysDictTypeId", dictType.getId())
                        .andEqualTo("showTag", GlobalConstant.STATE_ENABLE);
                dictMap.put(dictType.getTypeCode(), super.selectByExample(dictExample));
            }
            // 缓存结果
            RedisTemplateUtil.set(RedisConstant.CACHE_INIT_DICT, dictMap);
            return dictMap;
        } else {
            return initDictMap;
        }
    }

    /**
     * 返回字典redis缓存
     *
     * @return
     */
    @Override
    public Map<String, Map<String, String>> findDictMap() {
        // 字典列表
        Map<String, Map<String, String>> dictTypeMap = new HashMap<>();

        Example typeExample = new Example(SysDictType.class);
        typeExample.createCriteria().andEqualTo("state", GlobalConstant.STATE_ENABLE);
        List<SysDictType> dictTypeList = sysDictTypeService.selectByExample(typeExample);

        for (SysDictType dictType : dictTypeList) {
            Map<String, String> dictMap = new HashMap<>();
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

    /**
     * 根据字典项类型及字典code,查询字典值
     *
     * @param typeCode
     * @param dictCode
     * @return
     */
    @Override
    public String findDictValue(String typeCode, String dictCode) {
        Example typeExample = new Example(SysDictType.class);
        typeExample.createCriteria().andEqualTo("typeCode", typeCode)
                .andEqualTo("state", GlobalConstant.STATE_ENABLE);
        SysDictType dictType = sysDictTypeService.selectOneByExample(typeExample);
        if (dictType == null) {
            return null;
        }
        Example dictExample = new Example(SysDict.class);
        dictExample.createCriteria().andEqualTo("sysDictTypeId", dictType.getId())
                .andEqualTo("dictCode", dictCode);
        SysDict dict = super.selectOneByExample(dictExample);
        if (dict == null) {
            return null;
        }
        return dict.getDictValue();
    }
}
