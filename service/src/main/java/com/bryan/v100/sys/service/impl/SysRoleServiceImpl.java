package com.bryan.v100.sys.service.impl;

import com.bryan.common.base.BaseMapper;
import com.bryan.common.base.BaseServiceImpl;
import com.bryan.common.exception.ServiceException;
import com.bryan.common.utils.DateUtil;
import com.bryan.sys.domain.SysRole;
import com.bryan.sys.domain.SysUser;
import com.bryan.sys.mapper.SysRoleMapper;
import com.bryan.v100.sys.service.SysRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

/**
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/22
 */
@Service(value="sysRoleService")
@Transactional
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole> implements SysRoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;

    @Override
    public BaseMapper<SysRole> getMapper() {
        return sysRoleMapper;
    }

    @Override
    public void saveSysRole(SysRole role, SysUser sessionUser) {
        //角色名称和code不能重复
        Example example = new Example(SysRole.class);
        Example.Criteria codeParam = example.createCriteria();
        codeParam.andEqualTo("roleCode", role.getRoleCode());

        Example.Criteria nameParam = example.or();
        nameParam.andEqualTo("roleName", role.getRoleName());
        SysRole dbRole = super.selectOneByExample(example);
        if(dbRole != null){
            throw new ServiceException("角色名称或者标识重复,请重新输入");
        }

        role.setCtime(DateUtil.getNow());
        super.save(role);
    }

    @Override
    public void updateSysRole(SysRole role, SysUser sessionUser) {
        SysRole dbRole = super.selectByPrimaryKey(role.getId());
        if(dbRole == null){
            throw new ServiceException("角色记录查询失败");
        }
        if(dbRole.getRoleCode().equals(role.getRoleCode())){
            //角色code不更新
            role.setRoleCode(null);
        }else{
            //查询code是否重复
            Example example = new Example(SysRole.class);
            Example.Criteria codeParam = example.createCriteria();
            codeParam.andEqualTo("roleCode", role.getRoleCode());
            SysRole dbRoel = super.selectOneByExample(example);
            if(dbRoel != null){
                throw new ServiceException("角色标识不能重复,请重新输入");
            }
        }

        if(dbRole.getRoleName().equals(role.getRoleName())){
            //角色名称不更新
            role.setRoleName(null);
        }else{
            //校验角色名称不能重复
            Example example = new Example(SysRole.class);
            Example.Criteria codeParam = example.createCriteria();
            codeParam.andEqualTo("roleName", role.getRoleName());
            SysRole dbRoel = super.selectOneByExample(example);
            if(dbRoel != null){
                throw new ServiceException("角色名称不能重复,请重新输入");
            }
        }
        // 是否可以编辑,更新的时候,不可以修改
        role.setEditTag(null);
        role.setMtime(DateUtil.getNow());

        int count = super.updateByPrimaryKeySelective(role);
        if(count != 1){
            throw new  ServiceException("更新角色错误");
        }
    }
}
