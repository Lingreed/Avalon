package com.bryan.sys.model;

import com.bryan.sys.domain.SysOrg;

import java.util.List;

/**
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/22
 */
public class SysOrgModel extends SysOrg {

    private static final long serialVersionUID = 1L;

    /**
     * 子项
     */
    private List<SysOrgModel> child;

    /**
     * 子项目数量
     */
    private int childNum;

    /**
     * 深度
     */
    private int depth;


    public List<SysOrgModel> getChild() {
        return child;
    }

    public void setChild(List<SysOrgModel> child) {
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
