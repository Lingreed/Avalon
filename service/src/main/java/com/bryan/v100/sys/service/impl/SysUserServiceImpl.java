package com.bryan.v100.sys.service.impl;

import com.bryan.common.base.BaseMapper;
import com.bryan.common.base.BaseServiceImpl;
import com.bryan.common.constant.sys.SysUserConstant;
import com.bryan.common.context.Global;
import com.bryan.common.exception.ServiceException;
import com.bryan.common.security.Digests;
import com.bryan.common.utils.DateUtil;
import com.bryan.common.utils.RegexUtil;
import com.bryan.common.utils.StringUtil;
import com.bryan.sys.domain.SysOrg;
import com.bryan.sys.domain.SysRole;
import com.bryan.sys.domain.SysUser;
import com.bryan.sys.domain.SysUserRole;
import com.bryan.sys.mapper.SysUserMapper;
import com.bryan.sys.mapper.SysUserRoleMapper;
import com.bryan.sys.model.SysUserModel;
import com.bryan.v100.sys.service.SysOrgService;
import com.bryan.v100.sys.service.SysRoleService;
import com.bryan.v100.sys.service.SysUserRoleService;
import com.bryan.v100.sys.service.SysUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/23
 */
@Service(value = "sysUserService")
@Transactional
public class SysUserServiceImpl extends BaseServiceImpl<SysUser> implements SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private SysUserRoleMapper sysUserRoleMapper;
    @Resource
    private SysUserRoleService sysUserRoleService;
    @Resource
    private SysRoleService sysRoleService;
    @Resource
    private SysOrgService sysOrgService;

    @Override
    public BaseMapper<SysUser> getMapper() {
        return sysUserMapper;
    }

    /**
     * 添加管理员
     *
     * @param roleIds
     * @param sysUser
     * @param sessionUser
     */
    @Override
    public void saveSysUser(List<Integer> roleIds, SysUser sysUser, SysUser sessionUser) {
        Date now = DateUtil.getNow();
        //校验机构是否存在
        SysOrg org = sysOrgService.selectByPrimaryKey(sysUser.getOrgId());
        if (org == null) {
            throw new ServiceException("组织机构查询失败");
        }
        //校验用户名是否有重复的
        Example nameExample = new Example(SysUser.class);
        nameExample.createCriteria().andEqualTo("userName", sysUser.getUserName());
        SysUser expUser = super.selectOneByExample(nameExample);
        if (expUser != null) {
            throw new ServiceException("用户名已经存在,请重新填写");
        }

        if (StringUtil.isNotEmpty(sysUser.getMobilePhone())) {
            Example phoneExample = new Example(SysUser.class);
            phoneExample.createCriteria().andEqualTo("mobilePhone", sysUser.getMobilePhone());
            expUser = super.selectOneByExample(phoneExample);
            if (expUser != null) {
                throw new ServiceException("手机号已经存在,请重新填写");
            }
        }
        sysUser.setId(null);// 设置为空,保证返回id
        //密码处理
        sysUser.setLoginPwd(Digests.entryptPassword(sysUser.getLoginPwd()));
        //初始化状态
        sysUser.setState(SysUserConstant.STATE_ENABLE);
        sysUser.setCtime(DateUtil.getNow());
        super.save(sysUser);

        //添加用户角色
        for (Integer roleId : roleIds) {
            // 判断角色是否存在
            SysRole role = sysRoleService.selectByPrimaryKey(roleId);
            if (role == null) {
                throw new ServiceException("角色不存在");
            }
            SysUserRole userRole = new SysUserRole();
            userRole.setCtime(now);
            userRole.setSysRoleId(role.getId());
            userRole.setSysUserId(sysUser.getId());
            sysUserRoleService.save(userRole);
        }
    }

    /**
     * 更新管理员
     *
     * @param roleIds
     * @param sysUser
     * @param sessionUser
     */
    @Override
    public void updateSysuser(List<Integer> roleIds, SysUser sysUser, SysUser sessionUser) {
        Integer sysuserId = sysUser.getId();
        SysUser dbSysUser = super.selectByPrimaryKey(sysuserId);
        if (dbSysUser == null) {
            throw new ServiceException("更新用户查询不存在");
        }
        //校验机构是否存在
        SysOrg org = sysOrgService.selectByPrimaryKey(sysUser.getOrgId());
        if (org == null) {
            throw new ServiceException("组织机构查询失败");
        }
        //密码不能更新
        if (StringUtil.isNotBlank(sysUser.getLoginPwd())) {
            // 密码不为空,更新密码
            if (!RegexUtil.isPassword(sysUser.getLoginPwd())) {
                throw new ServiceException("请输入6-18位数字字母组合密码");
            }
            sysUser.setLoginPwd(Digests.entryptPassword(sysUser.getLoginPwd()));
        } else {//为空不修改密码
            sysUser.setLoginPwd(null);
        }
        //如果手机号
        String dbMobile = dbSysUser.getMobilePhone();// 数据库手机号
        if (StringUtil.isNotEmpty(sysUser.getMobilePhone())) {// 如果修改手机不为空
            if (sysUser.getMobilePhone().equals(dbMobile)) {
                //手机号无更新
                sysUser.setMobilePhone(null);
            } else {
                //校验手机号是否重复
                Example example = new Example(SysUser.class);
                Example.Criteria phoneParam = example.createCriteria();
                phoneParam.andEqualTo("mobilePhone", sysUser.getMobilePhone());
                SysUser dbUser = super.selectOneByExample(example);
                if (dbUser != null) {
                    throw new ServiceException("手机号已经存在,请重新填写");
                }
            }
        }

        //更新用户名
        if (dbSysUser.getUserName().equals(sysUser.getUserName())) {
            //手机号无更新
            sysUser.setUserName(null);
        } else {
            //校验用户名是否重复
            Example example = new Example(SysUser.class);
            Example.Criteria userParam = example.createCriteria();
            userParam.andEqualTo("userName", sysUser.getUserName());
            SysUser dbUser = super.selectOneByExample(example);
            if (dbUser != null) {
                throw new ServiceException("用户名已经存在,请重新填写");
            }
        }

        sysUser.setMtime(DateUtil.getNow());
        int updateCount = super.updateByPrimaryKeySelective(sysUser);
        if (updateCount != 1) {
            throw new ServiceException("更新管理人员错误");
        }
        //更新角色,删除已存角色,并添加 新的角色
        Example userRoleExample = new Example(SysUserRole.class);
        userRoleExample.createCriteria().andEqualTo("sysUserId", sysuserId);
        sysUserRoleService.deleteByExample(userRoleExample);
        for (Integer roleId : roleIds) {
            // 判断角色是否存在
            SysRole role = sysRoleService.selectByPrimaryKey(roleId);
            if (role == null) {
                throw new ServiceException("角色不存在");
            }
            SysUserRole userRole = new SysUserRole();
            userRole.setCtime(DateUtil.getNow());
            userRole.setSysRoleId(roleId);
            userRole.setSysUserId(sysUser.getId());
            sysUserRoleService.save(userRole);
        }
    }

    /**
     * 查询用户及角色信息
     *
     * @param map
     * @return
     */
    @Override
    public List<SysUserModel> findSysUserList(Map<String, Object> map) {
        List<SysUserModel> listModel = sysUserMapper.findSysUserList(map);
        for (SysUserModel sysUserModel : listModel) {
            //查询角色并返回
            Map<String, Object> roleMap = new HashMap<>();
            roleMap.put("sysUserId", sysUserModel.getId());
            List<SysRole> roleList = sysUserRoleMapper.findSysUserRoleList(roleMap);
            sysUserModel.setRoleList(roleList);
        }
        return listModel;
    }

    @Override
    public void updateLoginInfo(SysUser sysUser) {
        SysUser updateUser = new SysUser();
        updateUser.setId(sysUser.getId());
        updateUser.setLastLoginIp(Global.getIP());
        updateUser.setLastLoginTime(DateUtil.getNow());

        super.updateByPrimaryKeySelective(updateUser);
    }

    /**
     * 查询管理员详情
     *
     * @param id
     * @return
     */
    @Override
    public SysUserModel findDetail(Integer id) {
        Map<String, Object> map = new HashMap<>();
        map.put("sysUserId", id);
        List<SysUserModel> list = this.findSysUserList(map);
        if (list.size() > 0) {
            SysUserModel userModel = list.get(0);
            //查询角色并返回
            Map<String, Object> roleMap = new HashMap<>();
            roleMap.put("sysUserId", userModel.getId());
            List<SysRole> roleList = sysUserRoleMapper.findSysUserRoleList(roleMap);
            userModel.setRoleList(roleList);

            // 角色ids
            List<Integer> roleIds = new ArrayList<>();
            for (SysRole sysRole : roleList) {
                roleIds.add(sysRole.getId());
            }
            userModel.setSysRoleIds(roleIds);

            //组织机构ids
            List<Integer> orgIds = sysOrgService.findUserOrgIds(userModel.getOrgId(), new ArrayList<Integer>());
            userModel.setSysOrgIds(orgIds);
            return userModel;
        } else {
            return null;
        }
    }
}
