package com.bryan.common.constant;

import com.bryan.common.utils.CollectionUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * ClassName: FilterField
 * Function: 字段过滤
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/1/25
 * @see ApiResult
 *
 */
public class FilterField {
    /**
     * 需转换参数，key为类名，value为类中需要过滤的字段集合
     */
    public static Map<String,Set<String>> filterFieldConfig = new HashMap<>();

    /**
     * 是否过滤此字段
     *
     * @param clazz 对象class
     * @param key 对象中字段key
     * @return true:过滤，false：不过滤
     */
    public static boolean isFilterField(Class<?> clazz,String key){
        if(!clazz.getName().startsWith("com.bryan")){
            return false;
        }
        Set<String> filterFieldSet = filterFieldConfig.get(clazz.getName());
        if(CollectionUtils.isNotEmpty(filterFieldSet) && filterFieldSet.contains(key)){
            return true;
        }
        return isFilterField(clazz.getSuperclass(), key);
    }
}
