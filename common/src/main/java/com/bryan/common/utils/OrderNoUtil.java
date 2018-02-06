package com.bryan.common.utils;

import com.bryan.common.config.SysConfigUtil;
import com.bryan.common.config.SysKey;

import java.text.SimpleDateFormat;
import java.util.UUID;

/**
 * 工具类-订单号生成
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/5
 */
public class OrderNoUtil {
    /**
     * 时间格式化
     */
    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMdd");

    /**
     * 产生唯一 的序列号。
     *
     * @return String
     */
    public static String getSerialNumber() {
        int hashCode = UUID.randomUUID().toString().hashCode();
        if (hashCode < 0) {
            hashCode = -hashCode;
        }
        return SDF.format(DateUtil.getNow()).substring(2, 8) + String.format("%010d", hashCode);
    }


    /**
     * 10位不重复随机数
     *
     * @return String
     */
    public static String getRandomNo() {
        int hashCode = UUID.randomUUID().toString().hashCode();
        if (hashCode < 0) {
            hashCode = -hashCode;
        }
        return String.format("%010d", hashCode);
    }

    /**
     * 自动生成用户名
     *
     * @return String
     */
    public static String getUserName() {
        int hashCode = UUID.randomUUID().toString().hashCode();
        if (hashCode < 0) {
            hashCode = -hashCode;
        }
        return SysConfigUtil.getString(SysKey.USER_NAME_PREFIX) + String.format("%010d", hashCode);
    }

    /**
     * 生成体验券名称
     *
     * @return String
     */
    public static String getExpeNO() {
        int hashCode = UUID.randomUUID().toString().hashCode();
        if (hashCode < 0) {
            hashCode = -hashCode;
        }
        return "EXPE" + SDF.format(DateUtil.getNow()).substring(2, 8) + String.format("%010d", hashCode);
    }

    /**
     * @return
     * @Title: getUuid
     * @Description: 获取uuid
     */
    public static String getUuid() {
        return UUID.randomUUID().toString();
    }

    /**
     * @return
     * @Title: getRandomKey
     * @Description: 获取变量key
     */
    public static String getRandomKey() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static void main(String[] args) {
        System.out.println(String.format("%03d", 2));
    }

}
