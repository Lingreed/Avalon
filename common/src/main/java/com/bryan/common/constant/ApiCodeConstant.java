package com.bryan.common.constant;

/**
 * ClassName: ApiCodeConstant
 * Function: api接口返回code定义
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/1/25
 */
public class ApiCodeConstant {
    /**
     * 服务器成功
     */
    public static final String CODE_SUCCESS = "200";

    /**
     * 服务器没找到
     */
    public static final String CODE_NOT_FOUND = "404";

    /**
     *  服务器目前无法使用（由于超载或停机维护）
     */
    public static final String CODE_SERVER_STOP = "503";

    /**
     * 请求参数错误或者逻辑错误
     */
    public static final String CODE_BIZ_ERROR = "400";

    /**
     *  未授权,无权限访问
     */
    public static final String CODE_ACCESS_ERROR = "403";

    /**
     * 请求超时
     */
    public static final String CODE_TIME_OUT = "408";

    /**
     * 系统异常
     */
    public static final String CODE_SYS_ERROR = "500";
}
