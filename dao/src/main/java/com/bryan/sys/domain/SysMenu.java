package com.bryan.sys.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * ClassName: SysMenu
 * Function: 系统菜单实体
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/1/26
 */
@Table(name = "sys_menu")
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 父级ID
     */
    private Integer pid;

    /**
     * 菜单标识
     */
    @Column(name = "menu_code")
    private String menuCode;

    /**
     * 菜单名称
     */
    @Column(name = "menu_name")
    private String menuName;

    /**
     * 图标
     */
    private String icon;

    /**
     * 说明
     */
    private String discribtion;

    /**
     * 排序
     */
    @Column(name = "menu_sort")
    private Integer menuSort;

    /**
     * 菜单类型字典项
     */
    @Column(name = "menu_type")
    private String menuType;

    /**
     * 链接地址
     */
    private String href;

    /**
     * 权限值
     */
    @Column(name = "permission_value")
    private String permissionValue;

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
     * 获取父级ID
     *
     * @return 父级ID
     */
    public Integer getPid(){
        return pid;
    }

    /**
     * 设置父级ID
     *
     * @param pid 要设置的父级ID
     */
    public void setPid(Integer pid){
        this.pid = pid;
    }

    /**
     * 获取菜单标识
     *
     * @return 菜单标识
     */
    public String getMenuCode(){
        return menuCode;
    }

    /**
     * 设置菜单标识
     *
     * @param menuCode 要设置的菜单标识
     */
    public void setMenuCode(String menuCode){
        this.menuCode = menuCode;
    }

    /**
     * 获取菜单名称
     *
     * @return 菜单名称
     */
    public String getMenuName(){
        return menuName;
    }

    /**
     * 设置菜单名称
     *
     * @param menuName 要设置的菜单名称
     */
    public void setMenuName(String menuName){
        this.menuName = menuName;
    }

    /**
     * 获取图标
     *
     * @return 图标
     */
    public String getIcon(){
        return icon;
    }

    /**
     * 设置图标
     *
     * @param icon 要设置的图标
     */
    public void setIcon(String icon){
        this.icon = icon;
    }

    /**
     * 获取说明
     *
     * @return 说明
     */
    public String getDiscribtion(){
        return discribtion;
    }

    /**
     * 设置说明
     *
     * @param discribtion 要设置的说明
     */
    public void setDiscribtion(String discribtion){
        this.discribtion = discribtion;
    }

    /**
     * 获取排序
     *
     * @return 排序
     */
    public Integer getMenuSort(){
        return menuSort;
    }

    /**
     * 设置排序
     *
     * @param menuSort 要设置的排序
     */
    public void setMenuSort(Integer menuSort){
        this.menuSort = menuSort;
    }

    /**
     * 获取菜单类型字典项
     *
     * @return 菜单类型字典项
     */
    public String getMenuType(){
        return menuType;
    }

    /**
     * 设置菜单类型字典项
     *
     * @param menuType 要设置的菜单类型字典项
     */
    public void setMenuType(String menuType){
        this.menuType = menuType;
    }

    /**
     * 获取链接地址
     *
     * @return 链接地址
     */
    public String getHref(){
        return href;
    }

    /**
     * 设置链接地址
     *
     * @param href 要设置的链接地址
     */
    public void setHref(String href){
        this.href = href;
    }

    /**
     * 获取权限值
     *
     * @return 权限值
     */
    public String getPermissionValue(){
        return permissionValue;
    }

    /**
     * 设置权限值
     *
     * @param permissionValue 要设置的权限值
     */
    public void setPermissionValue(String permissionValue){
        this.permissionValue = permissionValue;
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
