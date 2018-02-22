package com.bryan.controller.v100.vo.sys.menu;

/**
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/22
 */
public class SysMenuQueryReq {

    private Integer menuId;

    /**
     * 父级ID
     */
    private Integer pid;

    /**
     * 菜单标识
     */
    private String menuCode;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单类型字典项
     */
    private String menuType;

    /**
     * 链接地址
     */
    private String href;

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
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
}
