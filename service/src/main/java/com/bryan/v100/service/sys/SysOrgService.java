package com.bryan.v100.service.sys;

import com.bryan.common.base.BaseService;
import com.bryan.dao.sys.domain.SysOrg;
import com.bryan.dao.sys.domain.SysUser;
import com.bryan.dao.sys.model.SysOrgModel;

import java.util.List;

/**
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/22
 */
public interface SysOrgService extends BaseService<SysOrg> {
    void updateSysOrg(SysOrg org, SysUser sessionUser);

    void saveSysOrg(SysOrg org, SysUser sessionUser);

    SysOrg findSysOrgById(Integer id);

    List<SysOrgModel> findAllList(String state);

    /**
     * 查询父级组织机构ids
     *
     * @param pids
     * @return
     */
    List<Integer> findParentIds(Integer orgId, List<Integer> pids);

    /**
     * 查询用户组织机构ids
     *
     * @param pids
     * @return
     */
    List<Integer> findUserOrgIds(Integer orgId, List<Integer> pids);
}
