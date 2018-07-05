package com.bryan.dao.sys.model;

import com.bryan.dao.sys.domain.SysOrg;

import java.util.List;

/**
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/22
 */
public class SysOrgDetailModel extends SysOrg {

    private static final long serialVersionUID = 1L;
    /**
     * 父级id
     */
    private List<Integer> pids;

    public List<Integer> getPids() {
        return pids;
    }

    public void setPids(List<Integer> pids) {
        this.pids = pids;
    }
}
