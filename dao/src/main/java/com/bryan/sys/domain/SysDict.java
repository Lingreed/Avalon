package com.bryan.sys.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * ClassName: SysDict
 * Function: 系统字典实体
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/2
 */
@Table(name = "sys_dict")
public class SysDict implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 字典项id
     */
    @Column(name = "sys_dict_type_id")
    private Integer sysDictTypeId;

    /**
     * 是否显示,主要用在登录加载字典项10显示,20不显示
     */
    @Column(name = "show_tag")
    private String showTag;

    /**
     * 字典标识
     */
    @Column(name = "dict_code")
    private String dictCode;

    /**
     * 字典值
     */
    @Column(name = "dict_value")
    private String dictValue;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date ctime;

    /**
     * 更新时间
     */
    private Date mtime;

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
     * 获取字典项id
     *
     * @return 字典项id
     */
    public Integer getSysDictTypeId() {
        return sysDictTypeId;
    }

    /**
     * 设置字典项id
     *
     * @param sysDictTypeId 要设置的字典项id
     */
    public void setSysDictTypeId(Integer sysDictTypeId) {
        this.sysDictTypeId = sysDictTypeId;
    }

    /**
     * 获取是否显示,主要用在登录加载字典项10显示,20不显示
     *
     * @return 是否显示, 主要用在登录加载字典项10显示, 20不显示
     */
    public String getShowTag() {
        return showTag;
    }

    /**
     * 设置是否显示,主要用在登录加载字典项10显示,20不显示
     *
     * @param showTag 要设置的是否显示,主要用在登录加载字典项10显示,20不显示
     */
    public void setShowTag(String showTag) {
        this.showTag = showTag;
    }

    /**
     * 获取字典标识
     *
     * @return 字典标识
     */
    public String getDictCode() {
        return dictCode;
    }

    /**
     * 设置字典标识
     *
     * @param dictCode 要设置的字典标识
     */
    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    /**
     * 获取字典值
     *
     * @return 字典值
     */
    public String getDictValue() {
        return dictValue;
    }

    /**
     * 设置字典值
     *
     * @param dictValue 要设置的字典值
     */
    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
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

    /**
     * 获取更新时间
     *
     * @return 更新时间
     */
    public Date getMtime() {
        return mtime;
    }

    /**
     * 设置更新时间
     *
     * @param mtime 要设置的更新时间
     */
    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }
}
