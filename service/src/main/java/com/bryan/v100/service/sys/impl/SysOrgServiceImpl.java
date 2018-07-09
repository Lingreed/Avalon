package com.bryan.v100.service.sys.impl;

import com.bryan.common.base.BaseMapper;
import com.bryan.common.base.BaseServiceImpl;
import com.bryan.common.constant.GlobalConstant;
import com.bryan.common.exception.ServiceException;
import com.bryan.common.utils.BeanUtil;
import com.bryan.common.utils.DateUtil;
import com.bryan.common.utils.StringUtil;
import com.bryan.dao.sys.domain.SysOrg;
import com.bryan.dao.sys.domain.SysUser;
import com.bryan.dao.sys.mapper.SysOrgMapper;
import com.bryan.dao.sys.model.SysOrgModel;
import com.bryan.v100.service.sys.SysOrgService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/22
 */
@Service(value = "sysOrgService")
@Transactional
public class SysOrgServiceImpl extends BaseServiceImpl<SysOrg> implements SysOrgService {

    @Resource
    private SysOrgMapper sysOrgMapper;

    @Override
    public BaseMapper<SysOrg> getMapper() {
        return sysOrgMapper;
    }

    @Override
    public void updateSysOrg(SysOrg org, SysUser sessionUser) {
        SysOrg sysOrg = super.selectByPrimaryKey(org.getId());
        if (sysOrg == null) {
            throw new ServiceException("机构查询失败");
        }
        if (sysOrg.getId() == 1) {
            // 如果是第一个,顶层pid为0
            sysOrg.setPid(0);
        }
        if (sysOrg.getOrgName().equals(org.getOrgName())) {
            //机构名称不更新
            org.setOrgName(null);
        } else {
            //机构名称是否重复
            Example example = new Example(SysOrg.class);
            Example.Criteria nameParam = example.createCriteria();
            nameParam.andEqualTo("orgName", org.getOrgName());
            SysOrg sysOrgNow = super.selectOneByExample(example);
            if (sysOrgNow != null) {
                throw new ServiceException("机构名称重复");
            }
        }
        org.setMtime(DateUtil.getNow());

        int count = super.updateByPrimaryKeySelective(org);
        if (count != 1) {
            throw new ServiceException("更新机构错误");
        }
    }

    @Override
    public void saveSysOrg(SysOrg org, SysUser sessionUser) {
        Example example = new Example(SysOrg.class);
        Example.Criteria nameParam = example.createCriteria();
        nameParam.andEqualTo("orgName", org.getOrgName());
        SysOrg sysOrg = super.selectOneByExample(example);
        if (sysOrg != null) {
            throw new ServiceException("机构名称不能重复，请重新输入");
        }
        org.setState(GlobalConstant.STATE_ENABLE);
        org.setMtime(DateUtil.getNow());
        super.save(org);
    }

    @Override
    public SysOrg findSysOrgById(Integer id) {
        Example example = new Example(SysOrg.class);
        Example.Criteria idParam = example.createCriteria();
        idParam.andEqualTo("id", id);
        List<SysOrg> list = super.selectByExample(example);
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }

    @Override
    public List<SysOrgModel> findAllList(String state) {
        return findChildList(0, state, 0);
    }

    public List<SysOrgModel> findChildList(Integer pid, String state, int depth) {
        List<SysOrgModel> modelList = new ArrayList<>();

        Example example = new Example(SysOrg.class);
        example.orderBy("orgSort").asc();
        Example.Criteria pidParam = example.createCriteria();
        pidParam.andEqualTo("pid", pid);
        if (StringUtil.isNotBlank(state)) {
            pidParam.andEqualTo("state", state);
        }
        List<SysOrg> parentList = super.selectByExample(example);
        int nextDepth = depth + 1;
        for (SysOrg sysOrg : parentList) {
            SysOrgModel orgModel = new SysOrgModel();
            BeanUtil.copyProperties(orgModel, sysOrg);
            List<SysOrgModel> child = findChildList(sysOrg.getId(), state, nextDepth);
            orgModel.setChild(child);
            orgModel.setDepth(depth);
            orgModel.setChildNum(child.size());
            modelList.add(orgModel);
        }
        return modelList;
    }

    /**
     * 查询父级组织机构ids
     *
     * @param pids
     * @return
     */
    @Override
    public List<Integer> findParentIds(Integer orgId, List<Integer> pids) {
        SysOrg org = sysOrgMapper.selectByPrimaryKey(orgId);
        if (org != null && org.getPid() > 0) {
            pids.add(org.getPid());
            findParentIds(org.getPid(), pids);
        }
        return pids;
    }

    /**
     * 查询用户组织机构ids
     *
     * @param pids
     * @return
     */
    @Override
    public List<Integer> findUserOrgIds(Integer orgId, List<Integer> pids) {
        SysOrg org = sysOrgMapper.selectByPrimaryKey(orgId);
        if (org != null) {
            pids.add(org.getId());
            findParentIds(org.getPid(), pids);
        }
        return pids;
    }
}
