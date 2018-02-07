package com.bryan.controller.v100.vo.sys.area;

import java.util.Date;

/**
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/7
 */
public class SysAreaQueryReq {
    private Integer id;

    /**
     * 父id
     */
    private Integer pid;

    /**
     * 级别1省2市3区
     */
    private Integer areaLevel;

    /**
     * 省区市编码
     */
    private String areaCode;

    /**
     * 省市区名称
     */
    private String areaName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 备注
     */
    private String memo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getAreaLevel() {
        return areaLevel;
    }

    public void setAreaLevel(Integer areaLevel) {
        this.areaLevel = areaLevel;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
