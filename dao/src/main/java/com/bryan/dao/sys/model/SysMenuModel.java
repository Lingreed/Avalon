package com.bryan.dao.sys.model;

import com.bryan.dao.sys.domain.SysMenu;

import java.util.List;

/**
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/1/26
 */
public class SysMenuModel extends SysMenu {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 子项目
     */
    private List<SysMenuModel> child;

    /**
     * 子项目数量
     */
    private int childNum;

    /**
     * 深度
     */
    private int depth;

    public List<SysMenuModel> getChild() {
        return child;
    }

    public void setChild(List<SysMenuModel> child) {
        this.child = child;
    }

    public int getChildNum() {
        return childNum;
    }

    public void setChildNum(int childNum) {
        this.childNum = childNum;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
