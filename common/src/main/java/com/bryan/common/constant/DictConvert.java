package com.bryan.common.constant;

import com.bryan.common.context.Global;
import com.bryan.common.utils.CollectionUtils;
import com.bryan.common.utils.DictUtil;
import com.bryan.common.utils.StringUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: DictConvert
 * Function: 字典字段转换
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/1/25
 * @see ApiResult
 */
public class DictConvert {
    /**
     * 需转换参数，key为类名，value中key为字段名，value为dict中code值
     */
    public static Map<String,Map<String,String>> dictConvertConfig = new HashMap<>();

    /**
     * 判断className类中fileName字段是否需要转换
     *
     * @param className 类名
     * @param fieldName 字段名
     * @return
     */
    public static boolean isDictFile(String className,String fieldName){
        return !CollectionUtils.isEmpty(dictConvertConfig.get(className))
                && StringUtil.isNotEmpty(dictConvertConfig.get(className).get(fieldName));
    }

    /**
     * 获取字典项解释字段
     * @param clazz 对象class
     * @param key 对象中字段key
     * @param value 对象中字段value
     */
    public static String getDictFieldValue(Class<?> clazz,String key,Object value){
        if(!clazz.getName().startsWith("com.bryan")){
            return null;
        }
        if(isDictFile(clazz.getName(),key)){
            Map<String,Map<String,String>> dictTypeMap = Global.DICT_LOCAL.get();
            if(CollectionUtils.isEmpty(dictTypeMap)){
                dictTypeMap = DictUtil.getDictType();
                Global.DICT_LOCAL.set(dictTypeMap);
            }
            String dictType = dictConvertConfig.get(clazz.getName()).get(key);
            Map<String,String> dictMap = dictTypeMap.get(dictType);
            if(dictMap != null && value != null){
                return dictMap.get(value.toString());
            }
            return null;
        }
        return getDictFieldValue(clazz.getSuperclass(), key, value);
    }
}
