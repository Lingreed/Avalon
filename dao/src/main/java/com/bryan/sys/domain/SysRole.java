package com.bryan.sys.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * ClassName: SysRole
 * Function: 系统角色实体
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/22
 */
@Table(name = "sys_role")
public class SysRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 角色名称
     */
    @Column(name = "role_name")
    private String roleName;

    /**
     * 角色标识
     */
    @Column(name = "role_code")
    private String roleCode;

    /**
     * 角色类型字典项
     */
    @Column(name = "role_type")
    private String roleType;

    /**
     * 状态10可用20禁用
     */
    private String state;

    /**
     * 是否可编辑,01可以,02不可以
     */
    @Column(name = "edit_tag")
    private String editTag;

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
     * 获取角色名称
     *
     * @return 角色名称
     */
    public String getRoleName(){
        return roleName;
    }

    /**
     * 设置角色名称
     *
     * @param roleName 要设置的角色名称
     */
    public void setRoleName(String roleName){
        this.roleName = roleName;
    }

    /**
     * 获取角色标识
     *
     * @return 角色标识
     */
    public String getRoleCode(){
        return roleCode;
    }

    /**
     * 设置角色标识
     *
     * @param roleCode 要设置的角色标识
     */
    public void setRoleCode(String roleCode){
        this.roleCode = roleCode;
    }

    /**
     * 获取角色类型字典项
     *
     * @return 角色类型字典项
     */
    public String getRoleType(){
        return roleType;
    }

    /**
     * 设置角色类型字典项
     *
     * @param roleType 要设置的角色类型字典项
     */
    public void setRoleType(String roleType){
        this.roleType = roleType;
    }

    /**
     * 获取状态10可用20禁用
     *
     * @return 状态10可用20禁用
     */
    public String getState(){
        return state;
    }

    /**
     * 设置状态10可用20禁用
     *
     * @param state 要设置的状态10可用20禁用
     */
    public void setState(String state){
        this.state = state;
    }

    /**
     * 获取是否可编辑,01可以,02不可以
     *
     * @return 是否可编辑,01可以,02不可以
     */
    public String getEditTag(){
        return editTag;
    }

    /**
     * 设置是否可编辑,01可以,02不可以
     *
     * @param editTag 要设置的是否可编辑,01可以,02不可以
     */
    public void setEditTag(String editTag){
        this.editTag = editTag;
    }

    /**
     * 获取备注
     *
     * @return 备注
     */
    public String getRemark(){
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 要设置的备注
     */
    public void setRemark(String remark){
        this.remark = remark;
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
     * 获取更新时间
     *
     * @return 更新时间
     */
    public Date getMtime(){
        return mtime;
    }

    /**
     * 设置更新时间
     *
     * @param mtime 要设置的更新时间
     */
    public void setMtime(Date mtime){
        this.mtime = mtime;
    }
}
