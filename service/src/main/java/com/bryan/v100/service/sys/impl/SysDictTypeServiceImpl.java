package com.bryan.v100.service.sys.impl;

import com.bryan.common.base.BaseMapper;
import com.bryan.common.base.BaseServiceImpl;
import com.bryan.common.constant.GlobalConstant;
import com.bryan.common.constant.redis.RedisConstant;
import com.bryan.common.exception.ServiceException;
import com.bryan.common.redis.RedisTemplateUtil;
import com.bryan.common.utils.DateUtil;
import com.bryan.sys.domain.SysDictType;
import com.bryan.sys.mapper.SysDictTypeMapper;
import com.bryan.v100.service.sys.SysDictTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

/**
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/2
 */
@Service(value = "sysDictTypeService")
@Transactional
public class SysDictTypeServiceImpl extends BaseServiceImpl<SysDictType> implements SysDictTypeService {

    @Resource
    private SysDictTypeMapper sysDictTypeMapper;

    @Override
    public BaseMapper<SysDictType> getMapper() {
        return sysDictTypeMapper;
    }

    /**
     * 保存字典类型
     *
     * @param dictType
     */
    @Override
    public void saveSysDictType(SysDictType dictType) {
        //typCode 或者 typeName 不能重复
        Example example = new Example(SysDictType.class);
        Example.Criteria codeCr = example.createCriteria();
        codeCr.andEqualTo("typeCode", dictType.getTypeCode());

        Example.Criteria nameCr = example.or();
        nameCr.andEqualTo("typeName", dictType.getTypeName());
        SysDictType dbDictType = super.selectOneByExample(example);

        if (dbDictType != null) {
            throw new ServiceException("标识或者名称有重复,请修改");
        }

        dictType.setCtime(DateUtil.getNow());
        dictType.setState(GlobalConstant.STATE_ENABLE);
        super.save(dictType);

        // 删除缓存登录字典
        RedisTemplateUtil.delete(RedisConstant.CACHE_INIT_DICT);
    }

    /**
     * 更新字典类型
     *
     * @param dictType
     */
    @Override
    public void updateSysDictType(SysDictType dictType) {
        SysDictType dbDictType = super.selectByPrimaryKey(dictType.getId());
        if (dbDictType == null) {
            throw new ServiceException("字典类型查询不存在");
        }
        //更新
        Example example = new Example(SysDictType.class);

        // code不允许修改
        dictType.setTypeCode(null);

        if (dbDictType.getTypeName().equals(dictType.getTypeName())) {
            //无更新
            dictType.setTypeName(null);
        } else {
            //校验名称
            example.clear();
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("typeName", dictType.getTypeName());
            SysDictType checkDictType = super.selectOneByExample(example);
            if (checkDictType != null) {
                throw new ServiceException("名称有重复,请重新填写");
            }
        }
        dictType.setMtime(DateUtil.getNow());
        int count = super.updateByPrimaryKeySelective(dictType);
        if (count != 1) {
            throw new ServiceException("更新字典类型错误");
        }
        // 删除缓存登录字典
        RedisTemplateUtil.delete(RedisConstant.CACHE_INIT_DICT);
    }
}
