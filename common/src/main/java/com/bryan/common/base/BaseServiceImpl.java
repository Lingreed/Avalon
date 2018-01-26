package com.bryan.common.base;

import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * ClassName: BaseServiceImpl
 * Function: 公共服务类
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/1/26
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {

    public static final String HASH_ALGORITHM = "SHA-1";

    public static final int HASH_INTERATIONS = 1024;

    public static final int SALT_SIZE = 8;

    /**
     * 获取mapper
     */
    public abstract BaseMapper<T> getMapper();

    /**
     * 批量插入
     */
    @Override
    public int save(List<T> t){
        return getMapper().insertList(t);
    }

    /**
     * 插入
     */
    @Override
    public int save(T t){
        return getMapper().insert(t);
    }

    /**
     * 删除实体
     * @param t
     * @return
     */
    @Override
    public int delete(T t){
        return getMapper().delete(t);
    }

    /**
     * 根据example删除
     * @param example
     * @return
     */
    @Override
    public int deleteByExample(Example example){
        return getMapper().deleteByExample(example);
    }

    /**
     * 根据 id 删除
     * @param id
     * @return
     */
    @Override
    public int deleteByPrimaryKey(Integer id){
        return getMapper().deleteByPrimaryKey(id);
    }

    /**
     * 选择保存对象
     * @param t
     * @return
     */
    @Override
    public int insertSelective(T t){
        return getMapper().insertSelective(t);
    }

    /**
     * 根据domain查询
     * @param t
     * @return
     */
    @Override
    public List<T> select(T t){
        return getMapper().select(t);
    }

    /**
     * 查询所有列表
     * @return
     */
    @Override
    public List<T> selectAll(){
        return getMapper().selectAll();
    }

    /**
     * 根据exmple属性查询list
     */
    @Override
    public List<T> selectByExample(Example example){
        return getMapper().selectByExample(example);
    }

    /**
     * 根据exmple属性查询单个
     */
    @Override
    public T selectOneByExample(Example example){
        List<T> list = getMapper().selectByExample(example);
        if(list.size()>0){
            return list.get(0);
        }else{
            return null;
        }
    }

    /**
     * 根据id查询domain
     * @param id
     * @return
     */
    @Override
    public T selectByPrimaryKey(Integer id){
        return getMapper().selectByPrimaryKey(id);
    }

    /**
     * 查询数量
     */
    @Override
    public int selectCount(T t){
        return getMapper().selectCount(t);
    }

    /**
     *
     */
    @Override
    public int selectCountByExample(Example example){
        return getMapper().selectCountByExample(example);
    }

    @Override
    public T selectOne(T t){
        return getMapper().selectOne(t);
    }

    /**
     * 选择更新
     * @param t
     * @param example
     * @return
     */
    @Override
    public int updateByExampleSelective(T t, Example example){
        return getMapper().updateByExampleSelective(t, example);
    }

    /**
     * 根据id选择更新
     * @param t
     * @return
     */
    @Override
    public int updateByPrimaryKeySelective(T t){
        return getMapper().updateByPrimaryKeySelective(t);
    }

}
