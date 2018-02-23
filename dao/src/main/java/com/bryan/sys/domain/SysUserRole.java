package com.bryan.sys.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * ClassName: SysUserRole
 * Function: 系统用户角色实体
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/23
 */
@Table(name = "sys_user_role")
public class SysUserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 系统用户id
     */
    @Column(name = "sys_user_id")
    private Integer sysUserId;

    /**
     * 系统角色id
     */
    @Column(name = "sys_role_id")
    private Integer sysRoleId;

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
     * 获取系统用户id
     *
     * @return 系统用户id
     */
    public Integer getSysUserId() {
        return sysUserId;
    }

    /**
     * 设置系统用户id
     *
     * @param sysUserId 要设置的系统用户id
     */
    public void setSysUserId(Integer sysUserId) {
        this.sysUserId = sysUserId;
    }

    /**
     * 获取系统角色id
     *
     * @return 系统角色id
     */
    public Integer getSysRoleId() {
        return sysRoleId;
    }

    /**
     * 设置系统角色id
     *
     * @param sysRoleId 要设置的系统角色id
     */
    public void setSysRoleId(Integer sysRoleId) {
        this.sysRoleId = sysRoleId;
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
