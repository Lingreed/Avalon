package com.bryan.controller.v100.vo;

/**
 * ClassName: PageReq
 * Function: 分页参数封装
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/22
 */
public class PageReq {
    /**
     * 当前页数
     */
    public int pageNum=1;

    /**
     * 每页展示数据条数,默认20条
     */
    public int pageSize=20;

    /**
     * 开始时间
     */
    public String startTime;

    /**
     * 结束时间
     */
    public String endTime;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = ( pageNum<=0 ) ? 1:pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = ( pageSize<=0 )? 20:pageSize;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
