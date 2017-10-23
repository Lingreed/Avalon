package com.bryan.common.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * cookie工具类
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2017/10/23
 */
public class CookieUtils {

    /**
     * 设置 Cookie（生成时间为1天）
     *
     * @param response
     * @param name     名称
     * @param value    值
     * @param domain
     */
    public static void setCookie(HttpServletResponse response, String name, String value, String domain) {
        setCookie(response, name, value, 60 * 60 * 24, domain);
    }

    /**
     * 设置 Cookie
     *
     * @param response
     * @param name     名称
     * @param value    值
     * @param path
     * @param domain
     */
    public static void setCookie(HttpServletResponse response, String name, String value, String path, String domain) {
        setCookie(response, name, value, path, 60 * 60 * 24, domain);
    }

    /**
     * 设置 Cookie
     *
     * @param response
     * @param name   名称
     * @param value  值
     * @param maxAge 生存时间（单位秒）
     * @param domain
     */
    public static void setCookie(HttpServletResponse response, String name, String value, int maxAge, String domain) {
        setCookie(response, name, value, "/", maxAge, domain);
    }

    /**
     * 设置 Cookie
     *
     * @param response
     * @param name     名称
     * @param value    值
     * @param path     路径
     * @param maxAge   生存时间（单位秒）
     * @param domain
     */
    public static void setCookie(HttpServletResponse response, String name, String value, String path, int maxAge,
                                 String domain) {
        Cookie cookie = new Cookie(name, null);
        cookie.setPath(path);
        cookie.setMaxAge(maxAge);
        if (StringUtils.isNotBlank(domain)) {
            cookie.setDomain(domain);
        }
        try {
            cookie.setValue(URLEncoder.encode(value, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.addCookie(cookie);
    }

    /**
     * 获得指定Cookie的值
     *
     * @param name 名称
     * @return 值
     */
    public static String getCookie(HttpServletRequest request, String name) {
        return getCookie(request, null, name, false);
    }

    /**
     * 获得指定Cookie的值，并删除。
     *
     * @param name 名称
     * @return 值
     */
    public static String getCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        return getCookie(request, response, name, true);
    }

    /**
     * 获得指定Cookie的值
     *
     * @param request  请求对象
     * @param response 响应对象
     * @param name     名字
     * @param isRemove 是否移除
     * @return 值
     */
    public static String getCookie(HttpServletRequest request, HttpServletResponse response, String name,
                                   boolean isRemove) {
        String value = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    try {
                        value = URLDecoder.decode(cookie.getValue(), "utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    if (isRemove) {
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                    }
                }
            }
        }
        return value;
    }
}
