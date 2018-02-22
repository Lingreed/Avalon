package com.bryan.controller.v100.vo.sys.menu;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/22
 */
public class SysMenuSaveOrUpdateReq {
    /**
     * Id
     */
    private Integer id;

    /**
     * 父级id
     */
    private Integer pid;


    /**
     * 菜单标识
     */
    @NotBlank(message = "菜单标识不能为空")
    private String menuCode;

    /**
     * 菜单名称
     */
    @NotBlank(message = "菜单名称不能为空")
    private String menuName;

    /**
     * 图标
     */
    @NotBlank(message = "图标不能为空")
    private String icon;

    /**
     * 说明
     */
    private String discribtion;

    /**
     * 排序
     */
    @NotBlank(message = "排序不能为空")
    private Integer menuSort;

    /**
     * 菜单类型字典项
     */
    @NotBlank(message = "菜单类型字典项不能为空")
    private String menuType;

    /**
     * 链接地址
     */
    @NotBlank(message = "链接地址不能为空")
    private String href;

    /**
     * 权限值
     */
    private String permissionValue;

    /**
     * 备注
     */
    private String remark;

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

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDiscribtion() {
        return discribtion;
    }

    public void setDiscribtion(String discribtion) {
        this.discribtion = discribtion;
    }

    public Integer getMenuSort() {
        return menuSort;
    }

    public void setMenuSort(Integer menuSort) {
        this.menuSort = menuSort;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getPermissionValue() {
        return permissionValue;
    }

    public void setPermissionValue(String permissionValue) {
        this.permissionValue = permissionValue;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
