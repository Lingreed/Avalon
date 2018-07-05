package com.bryan.dao.sys.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * ClassName: SysRoleMenu
 * Function: 系统角色菜单实体
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/1/26
 */
@Table(name = "sys_role_menu")
public class SysRoleMenu implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 角色id
     */
    @Column(name = "sys_role_id")
    private Integer sysRoleId;

    /**
     * 菜单id
     */
    @Column(name = "sys_menu_id")
    private Integer sysMenuId;

    /**
     * 是否选中:10选中,20未选中
     */
    @Column(name = "select_state")
    private String selectState;

    /**
     * 创建时间
     */
    private Date ctime;

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
     * 获取角色id
     *
     * @return 角色id
     */
    public Integer getSysRoleId(){
        return sysRoleId;
    }

    /**
     * 设置角色id
     *
     * @param sysRoleId 要设置的角色id
     */
    public void setSysRoleId(Integer sysRoleId){
        this.sysRoleId = sysRoleId;
    }

    /**
     * 获取菜单id
     *
     * @return 菜单id
     */
    public Integer getSysMenuId(){
        return sysMenuId;
    }

    /**
     * 设置菜单id
     *
     * @param sysMenuId 要设置的菜单id
     */
    public void setSysMenuId(Integer sysMenuId){
        this.sysMenuId = sysMenuId;
    }

    /**
     * 获取是否选中:10选中,20未选中
     *
     * @return 是否选中:10选中,20未选中
     */
    public String getSelectState(){
        return selectState;
    }

    /**
     * 设置是否选中:10选中,20未选中
     *
     * @param selectState 要设置的是否选中:10选中,20未选中
     */
    public void setSelectState(String selectState){
        this.selectState = selectState;
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
}
