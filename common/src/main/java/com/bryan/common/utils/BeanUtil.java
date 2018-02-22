package com.bryan.common.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.mybatis.mapper.entity.Example;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.*;

/**
 * bean工具类
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/22
 */
public class BeanUtil {

    private static Logger logger = LoggerFactory.getLogger(BeanUtil.class);

    /**
     * 属性copy
     *
     * @param dest
     * @param orig 来源
     */
    public static void copyProperties(Object dest, Object orig) {
        try {
            BeanUtils.copyProperties(dest, orig);
        } catch (Exception e) {
            logger.error("属性拷贝异常", e);
        }
    }

    /**
     * map,直接转出bean
     *
     * @param map
     * @param obj
     */
    public static void map2Bean(Map<String, Object> map, Object obj) {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo
                    .getPropertyDescriptors();

            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                if (map.containsKey(key)) {
                    Object value = map.get(key);
                    // 得到property对应的setter方法
                    Method setter = property.getWriteMethod();
                    setter.invoke(obj, value);
                }
            }
        } catch (Exception e) {
            logger.error("map2Bean,error", e);
        }
    }

    public static Map<String, Object> bean2Map(Object obj) {
        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo
                    .getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);
                    if (value != null) {
                        if (value instanceof String) {
                            // string 类型
                            if (!"".equals(String.valueOf(value))) {
                                map.put(key, value);
                            }
                        } else if (value instanceof Collection) {
                            // 集合类型
                            if (!((ArrayList<?>) value).isEmpty()) {
                                map.put(key, value);
                            }
                        } else {
                            map.put(key, value);
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("bean2Map,error", e);
        }
        return map;
    }

    /**
     * @param obj
     * @param entityClass
     * @return
     * @Title: bean2Example
     * @Description: bean 转换成 example
     */
    public static Example bean2Example(Object obj, Class<?> entityClass) {
        if (obj == null) {
            return null;
        }
        Example example = new Example(entityClass);
        Example.Criteria criteria = example.createCriteria();
        try {
            // 获取domain声明字段
            BeanInfo entityInfo = Introspector.getBeanInfo(entityClass);
            PropertyDescriptor[] entityFields = entityInfo
                    .getPropertyDescriptors();
            Set<String> fieldSet = new HashSet<>();
            for (PropertyDescriptor entityDes : entityFields) {
                fieldSet.add(entityDes.getName());
            }
            // 封装查询条件
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo
                    .getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);
                    // 不为空或Null,且在domain里边的字段设置成查询参数
                    if (value != null && !"".equals(String.valueOf(value))
                            && fieldSet.contains(key)) {
                        if (key.toLowerCase().contains("name")
                                || key.toLowerCase().contains("title")) {
                            criteria.andLike(key, "%" + value + "%");
                        } else {
                            criteria.andEqualTo(key, value);
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("bean2Example,error", e);
        }
        return example;
    }
}
