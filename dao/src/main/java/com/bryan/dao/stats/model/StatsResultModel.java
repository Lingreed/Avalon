package com.bryan.dao.stats.model;

/**
 * ClassName: StatsResultModel
 * Function:  统计返回的参数
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/3/1
 */
public class StatsResultModel {
    /**
     * 结果
     */
    private String resultKey;

    /**
     * 每周出现的次数
     */
    private Double count;

    /**
     * 返回的结果对应的列表
     */
    private String dateResult;


    public String getDateResult() {
        return dateResult;
    }

    public void setDateResult(String dateResult) {
        this.dateResult = dateResult;
    }

    public String getResultKey() {
        return resultKey;
    }

    public void setResultKey(String resultKey) {
        this.resultKey = resultKey;
    }

    public Double getCount() {
        return count;
    }

    public void setCount(Double count) {
        this.count = count;
    }
}
