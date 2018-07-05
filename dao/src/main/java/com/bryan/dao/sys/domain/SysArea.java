package com.bryan.dao.sys.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * ClassName: SysArea
 * Function: 省市区代码实体
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/7
 */
@Table(name = "sys_area")
public class SysArea implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 父id
     */
    private Integer pid;

    /**
     * 级别1省2市3区
     */
    @Column(name = "area_level")
    private Integer areaLevel;

    /**
     * 省区市编码
     */
    @Column(name = "area_code")
    private String areaCode;

    /**
     * 省市区名称
     */
    @Column(name = "area_name")
    private String areaName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date ctime;

    /**
     * 获取id
     *
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id 要设置的id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取父id
     *
     * @return 父id
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * 设置父id
     *
     * @param pid 要设置的父id
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * 获取级别1省2市3区
     *
     * @return 级别1省2市3区
     */
    public Integer getAreaLevel() {
        return areaLevel;
    }

    /**
     * 设置级别1省2市3区
     *
     * @param areaLevel 要设置的级别1省2市3区
     */
    public void setAreaLevel(Integer areaLevel) {
        this.areaLevel = areaLevel;
    }

    /**
     * 获取省区市编码
     *
     * @return 省区市编码
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * 设置省区市编码
     *
     * @param areaCode 要设置的省区市编码
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    /**
     * 获取省市区名称
     *
     * @return 省市区名称
     */
    public String getAreaName() {
        return areaName;
    }

    /**
     * 设置省市区名称
     *
     * @param areaName 要设置的省市区名称
     */
    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    /**
     * 获取备注
     *
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 要设置的备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取创建时间
     *
     * @return 创建时间
     */
    public Date getCtime() {
        return ctime;
    }

    /**
     * 设置创建时间
     *
     * @param ctime 要设置的创建时间
     */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
}
