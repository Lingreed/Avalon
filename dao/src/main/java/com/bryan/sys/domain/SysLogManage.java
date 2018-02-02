package com.bryan.sys.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * ClassName: SysLogManage
 * Function: 系统后台日志实体
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/1/29
 */
@Table(name = "sys_log_manage")
public class SysLogManage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 日志标题
     */
    private String title;

    /**
     * 用户代理
     */
    @Column(name = "user_agent")
    private String userAgent;

    /**
     * 请求URI
     */
    @Column(name = "request_uri")
    private String requestUri;

    /**
     * 操作方式
     */
    private String method;

    /**
     * 操作提交的数据
     */
    private String params;

    /**
     * 异常信息
     */
    private String exception;

    /**
     * 创建者id
     */
    @Column(name = "cuser_id")
    private Integer cuserId;

    /**
     * 创建时间
     */
    private Date ctime;

    /**
     * 操作IP地址
     */
    private String cip;

    /**
     * 获取id
     *
     * @return id
     */
    public Integer getId(){
        return id;
    }

    /**
     * 设置id
     *
     * @param id 要设置的id
     */
    public void setId(Integer id){
        this.id = id;
    }

    /**
     * 获取日志标题
     *
     * @return 日志标题
     */
    public String getTitle(){
        return title;
    }

    /**
     * 设置日志标题
     *
     * @param title 要设置的日志标题
     */
    public void setTitle(String title){
        this.title = title;
    }

    /**
     * 获取用户代理
     *
     * @return 用户代理
     */
    public String getUserAgent(){
        return userAgent;
    }

    /**
     * 设置用户代理
     *
     * @param userAgent 要设置的用户代理
     */
    public void setUserAgent(String userAgent){
        this.userAgent = userAgent;
    }

    /**
     * 获取请求URI
     *
     * @return 请求URI
     */
    public String getRequestUri(){
        return requestUri;
    }

    /**
     * 设置请求URI
     *
     * @param requestUri 要设置的请求URI
     */
    public void setRequestUri(String requestUri){
        this.requestUri = requestUri;
    }

    /**
     * 获取操作方式
     *
     * @return 操作方式
     */
    public String getMethod(){
        return method;
    }

    /**
     * 设置操作方式
     *
     * @param method 要设置的操作方式
     */
    public void setMethod(String method){
        this.method = method;
    }

    /**
     * 获取操作提交的数据
     *
     * @return 操作提交的数据
     */
    public String getParams(){
        return params;
    }

    /**
     * 设置操作提交的数据
     *
     * @param params 要设置的操作提交的数据
     */
    public void setParams(String params){
        this.params = params;
    }

    /**
     * 获取异常信息
     *
     * @return 异常信息
     */
    public String getException(){
        return exception;
    }

    /**
     * 设置异常信息
     *
     * @param exception 要设置的异常信息
     */
    public void setException(String exception){
        this.exception = exception;
    }

    /**
     * 获取创建者id
     *
     * @return 创建者id
     */
    public Integer getCuserId(){
        return cuserId;
    }

    /**
     * 设置创建者id
     *
     * @param cuserId 要设置的创建者id
     */
    public void setCuserId(Integer cuserId){
        this.cuserId = cuserId;
    }

    /**
     * 获取创建时间
     *
     * @return 创建时间
     */
    public Date getCtime(){
        return ctime;
    }

    /**
     * 设置创建时间
     *
     * @param ctime 要设置的创建时间
     */
    public void setCtime(Date ctime){
        this.ctime = ctime;
    }

    /**
     * 获取操作IP地址
     *
     * @return 操作IP地址
     */
    public String getCip(){
        return cip;
    }

    /**
     * 设置操作IP地址
     *
     * @param cip 要设置的操作IP地址
     */
    public void setCip(String cip){
        this.cip = cip;
    }
}
