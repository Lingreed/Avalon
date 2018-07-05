package com.bryan.dao.stats.model;

import java.util.Date;
import java.util.List;

/**
 * ClassName: StatsModel
 * Function:  统计表需要的数据
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/3/1
 */
public class StatsModel {
    /**
     * 实名认证后的名称.个人为真实姓名,企业为机构名称
     */
    private String authTag;

    /**
     * 客户端:01PC,02H5,03Android,04iOS,99其他
     */
    private List<String> clientType;

    /**
     * 注册来源:01普通注册,02邀请注册,03渠道注册,04后台录入
     */
    private List<String> regSource;

    /**
     * 类型 ：01日, 02周 ,03月
     */
    private String type;

    /**
     * 年利率 0-5 个选择 6为xml条件时用
     */
    private String rateOne;

    private String rateTwo;

    private String rateThree;

    private String rateFour;

    private String rateFive;

    /**
     * ratewhere条件处理
     */
    private String rateWhere;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 渠道名称
     */
    private String channelCode;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }


    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getRateOne() {
        return rateOne;
    }

    public void setRateOne(String rateOne) {
        this.rateOne = rateOne;
    }

    public String getRateTwo() {
        return rateTwo;
    }

    public void setRateTwo(String rateTwo) {
        this.rateTwo = rateTwo;
    }

    public String getRateThree() {
        return rateThree;
    }

    public void setRateThree(String rateThree) {
        this.rateThree = rateThree;
    }

    public String getRateFour() {
        return rateFour;
    }

    public void setRateFour(String rateFour) {
        this.rateFour = rateFour;
    }

    public String getRateFive() {
        return rateFive;
    }

    public void setRateFive(String rateFive) {
        this.rateFive = rateFive;
    }

    public String getRateWhere() {
        return rateWhere;
    }

    public void setRateWhere(String rateWhere) {
        this.rateWhere = rateWhere;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAuthTag() {
        return authTag;
    }

    public void setAuthTag(String authTag) {
        this.authTag = authTag;
    }

    public List<String> getClientType() {
        return clientType;
    }

    public void setClientType(List<String> clientType) {
        this.clientType = clientType;
    }

    public List<String> getRegSource() {
        return regSource;
    }

    public void setRegSource(List<String> regSource) {
        this.regSource = regSource;
    }

}
