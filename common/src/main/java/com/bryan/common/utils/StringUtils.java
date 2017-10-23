package com.bryan.common.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2017/10/23
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    private static final char SEPARATOR = '_';

    /**
     * 替换掉HTML标签方法
     */
    public static String replaceHtml(String html) {
        if (isBlank(html)) {
            return "";
        }
        String regEx = "<.+?>";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(html);
        String s = m.replaceAll("");
        return s;
    }

    /**
     * 转换为Double类型
     */
    public static Double toDouble(Object val) {
        if (val == null) {
            return 0D;
        }
        try {
            return Double.valueOf(trim(val.toString()));
        } catch (Exception e) {
            return 0D;
        }
    }

    /**
     * 转换为Float类型
     */
    public static Float toFloat(Object val) {
        return toDouble(val).floatValue();
    }

    /**
     * 转换为Long类型
     */
    public static Long toLong(Object val) {
        return toDouble(val).longValue();
    }

    /**
     * 转换为Integer类型
     */
    public static Integer toInteger(Object val) {
        return toLong(val).intValue();
    }

    /**
     * 获得用户远程地址
     */
    public static String getRemoteAddr(HttpServletRequest request) {
        String remoteAddr = request.getHeader("X-Real-IP");
        if (isNotBlank(remoteAddr)) {
            remoteAddr = request.getHeader("X-Forwarded-For");
        } else if (isNotBlank(remoteAddr)) {
            remoteAddr = request.getHeader("Proxy-Client-IP");
        } else if (isNotBlank(remoteAddr)) {
            remoteAddr = request.getHeader("WL-Proxy-Client-IP");
        }
        return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
    }

    /**
     * 首字母大写
     *
     * @param s
     * @return
     */
    public static String firstCharUpperCase(String s) {
        StringBuffer sb = new StringBuffer(s.substring(0, 1).toUpperCase());
        sb.append(s.substring(1, s.length()));
        return sb.toString();
    }

    /**
     * 手机号中间四位隐藏
     *
     * @param mobile
     * @return
     */
    public static String mobileHidden(String mobile) {
        return mobile.substring(0, 3) + "****" + mobile.subSequence(7, mobile.length());
    }

    public static String beginDateString(String str) {
        return str + " 00:00:01";
    }

    public static String endDateString(String str) {
        return str + " 23:59:59";
    }

    /**
     * 字符串解码UTF-8
     *
     * @param str
     * @return
     */
    public static String urlDecoderUTF8(String str) {
        try {
            if (StringUtils.isNotBlank(str)) {
                return URLDecoder.decode(str, "utf-8");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getHideMobile(String mobile) {
        if (StringUtils.isNotBlank(mobile) && mobile.length() == 11) {
            return mobile.substring(0, 3) + "*****" + mobile.substring(8, 11);
        }
        return mobile;
    }

    public static String getFristImgStr(String content) {
        // 用正则匹配

        Pattern ATTR_PATTERN = Pattern.compile("<img[^<>]*?\\ssrc=['\"]?(.*?)['\"]?\\s.*?>", Pattern.CASE_INSENSITIVE);
        Matcher matcher = ATTR_PATTERN.matcher(content);
        String str = "";
        while (matcher.find() && isBlank(str)) {
            str += matcher.group(1) + ",";
        }
        return str;
    }
}
