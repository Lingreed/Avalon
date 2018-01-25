package com.bryan.common.utils;

import com.bryan.common.constant.redis.RedisConstant;
import com.bryan.common.redis.RedisTemplateUtil;

import java.util.Map;

/**
 * ClassName: DictUtil
 * Function:  字典工具类
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/1/25
 */
public class DictUtil {
    /**
     * 获取字典项值.
     * @param typeCode
     * @param dictCode
     * @return
     */
    public static String getDictValue(String typeCode, String dictCode){
        Map<String,String> dictMap = getDictMap(typeCode);
        if(dictMap == null){
            return "";
        }else{
            return dictMap.get(dictCode);
        }
    }

    /**
     * 字典项,对应的字典列表
     * @param typeCode
     * @return
     */
    public static Map<String, String> getDictMap(String typeCode){
        // 从 redis 获取
        Map<String,String> dictMap = RedisTemplateUtil.hashGet(RedisConstant.CACHE_SYS_DICT, typeCode);
        return dictMap;
    }

    /**
     * 字典项,获取整个字典Map合集,dictType 为key,该类型的字典合集map为value
     * @return
     */
    public static Map<String,Map<String,String>> getDictType( ){
        // 从 redis 获取
        Map<String,Map<String,String>> dictTypeMap = RedisTemplateUtil.hashGetAll(RedisConstant.CACHE_SYS_DICT);
        return dictTypeMap;
    }
}
