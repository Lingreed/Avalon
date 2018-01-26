package com.bryan.common.base;

import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * BaseService接口
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/1/26
 */
public interface BaseService<T> {

    int save(List<T> t);

    int delete(T t);

    int deleteByPrimaryKey(Integer id);

    int save(T t);

    int insertSelective(T t);

    List<T> select(T t);

    List<T> selectAll();

    List<T> selectByExample(Example example);

    T selectByPrimaryKey(Integer id);

    int selectCount(T t);

    int selectCountByExample(Example example);

    T selectOne(T t);

    int updateByExampleSelective(T t, Example example);

    int updateByPrimaryKeySelective(T t);

    T selectOneByExample(Example example);

    int deleteByExample(Example example);
}
