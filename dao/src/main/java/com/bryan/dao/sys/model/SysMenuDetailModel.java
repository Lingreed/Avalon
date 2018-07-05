package com.bryan.dao.sys.model;

import com.bryan.dao.sys.domain.SysMenu;

import java.util.List;

/**
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/22
 */
public class SysMenuDetailModel extends SysMenu {

    private static final long serialVersionUID = 1L;
    /**
     * 父级id集合
     */
    private List<Integer> pids;

    public List<Integer> getPids() {
        return pids;
    }

    public void setPids(List<Integer> pids) {
        this.pids = pids;
    }
}
