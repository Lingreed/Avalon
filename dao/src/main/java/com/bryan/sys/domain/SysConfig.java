package com.bryan.sys.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * ClassName: SysConfig
 * Function: 实体
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/5
 */
@Table(name = "sys_config")
public class SysConfig implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 类别
     */
    private String category;

    /**
     * 配置key
     */
    @Column(name = "cfg_key")
    private String cfgKey;

    /**
     * 配置value
     */
    @Column(name = "cfg_value")
    private String cfgValue;

    /**
     * 状态：10可用，20禁用
     */
    private String state;

    /**
     * 是否缓存，10是，20否
     */
    private String cache;

    /**
     * 是否展示：10展示，20不展示
     */
    @Column(name = "show_tag")
    private String showTag;

    /**
     *
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
     * 获取配置key
     *
     * @return 配置key
     */
    public String getCfgKey() {
        return cfgKey;
    }

    /**
     * 设置配置key
     *
     * @param cfgKey 要设置的配置key
     */
    public void setCfgKey(String cfgKey) {
        this.cfgKey = cfgKey;
    }

    /**
     * 获取配置value
     *
     * @return 配置value
     */
    public String getCfgValue() {
        return cfgValue;
    }

    /**
     * 设置配置value
     *
     * @param cfgValue 要设置的配置value
     */
    public void setCfgValue(String cfgValue) {
        this.cfgValue = cfgValue;
    }

    /**
     * 获取状态：10可用，20禁用
     *
     * @return 状态：10可用，20禁用
     */
    public String getState() {
        return state;
    }

    /**
     * 设置状态：10可用，20禁用
     *
     * @param state 要设置的状态：10可用，20禁用
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * 获取是否展示：10展示，20不展示
     *
     * @return 是否展示：10展示，20不展示
     */
    public String getShowTag() {
        return showTag;
    }

    /**
     * 设置是否展示：10展示，20不展示
     *
     * @param showTag 要设置的是否展示：10展示，20不展示
     */
    public void setShowTag(String showTag) {
        this.showTag = showTag;
    }

    /**
     * 获取
     *
     * @return
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置
     *
     * @param remark 要设置的
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCache() {
        return cache;
    }

    public void setCache(String cache) {
        this.cache = cache;
    }
}
