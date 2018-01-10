package com.bryan.common.base;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * ClassName: BaseMapper  
 * Function: 公共Mapper方法
 */
public interface BaseMapper<T> extends Mapper<T>,MySqlMapper<T> {

}
