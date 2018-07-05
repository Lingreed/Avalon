package com.bryan.dao.sys.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * ClassName: SysDictType
 * Function: 字典类型实体
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/2
 */
@Table(name = "sys_dict_type")
public class SysDictType implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 类型标识
     */
    @Column(name = "type_code")
    private String typeCode;

    /**
     * 类型名称
     */
    @Column(name = "type_name")
    private String typeName;

    /**
     * 状态10启用,20禁用
     */
    private String state;

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
     * 获取类型标识
     *
     * @return 类型标识
     */
    public String getTypeCode() {
        return typeCode;
    }

    /**
     * 设置类型标识
     *
     * @param typeCode 要设置的类型标识
     */
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    /**
     * 获取类型名称
     *
     * @return 类型名称
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * 设置类型名称
     *
     * @param typeName 要设置的类型名称
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    /**
     * 获取状态10启用,20禁用
     *
     * @return 状态10启用, 20禁用
     */
    public String getState() {
        return state;
    }

    /**
     * 设置状态10启用,20禁用
     *
     * @param state 要设置的状态10启用,20禁用
     */
    public void setState(String state) {
        this.state = state;
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
