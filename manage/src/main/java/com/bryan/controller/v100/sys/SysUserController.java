package com.bryan.controller.v100.sys;

import com.bryan.common.constant.ApiResult;
import com.bryan.common.constant.GlobalConstant;
import com.bryan.common.constant.sys.SysUserConstant;
import com.bryan.common.exception.ServiceException;
import com.bryan.common.security.Digests;
import com.bryan.common.utils.BeanUtil;
import com.bryan.common.utils.CollectionUtils;
import com.bryan.common.utils.OrderNoUtil;
import com.bryan.common.utils.StringUtil;
import com.bryan.controller.BaseController;
import com.bryan.controller.v100.vo.sys.user.SysUserLoginReq;
import com.bryan.controller.v100.vo.sys.user.SysUserQueryReq;
import com.bryan.controller.v100.vo.sys.user.SysUserSaveOrUpdateReq;
import com.bryan.dao.sys.domain.SysOrg;
import com.bryan.dao.sys.domain.SysRole;
import com.bryan.dao.sys.domain.SysUser;
import com.bryan.dao.sys.model.SysMenuModel;
import com.bryan.dao.sys.model.SysUserLoginModel;
import com.bryan.dao.sys.model.SysUserModel;
import com.bryan.v100.service.sys.SysOrgService;
import com.bryan.v100.service.sys.SysRoleMenuService;
import com.bryan.v100.service.sys.SysUserRoleService;
import com.bryan.v100.service.sys.SysUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/27
 */
@RestController
@RequestMapping("/api/v100/sys/sysUser")
public class SysUserController extends BaseController {

    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysUserRoleService sysUserRoleService;
    @Resource
    private SysRoleMenuService sysRoleMenuService;
    @Resource
    private RedisTemplate<String, String> redisTemplate;
    @Resource
    private SysOrgService sysOrgService;

    @ResponseBody
    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    public ApiResult doLogin(HttpServletRequest request, HttpServletResponse response, @RequestBody @Valid SysUserLoginReq req) {

        Example example = new Example(SysUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userName", req.getUserName());
        SysUser sysUser = sysUserService.selectOneByExample(example);

        if (null == sysUser) {
            throw new ServiceException("用户不存在");
        }

        if (!SysUserConstant.STATE_ENABLE.equals(sysUser.getState())) {
            throw new ServiceException("用户状态被锁定,请联系管理员解锁");
        }

        if (!Digests.validatePassword(req.getLoginPwd(), sysUser.getLoginPwd())) {
            throw new ServiceException("用户密码错误,请重新输入");
        }

        SysUserLoginModel loginModel = new SysUserLoginModel();
        BeanUtil.copyProperties(loginModel, sysUser);

        /*设置返回对象*/
        Map<String, Object> result = fillLoginUser(sysUser);

        //保存登录上下文
        String accessToken = OrderNoUtil.getUuid();
        loginModel.setRoleId((Integer) result.get("roleId"));
        setSessionUser(loginModel, accessToken, response);

        sysUserService.updateLoginInfo(sysUser);//更新登录信息

        return ApiResult.newInstance().addResult(result);
    }

    @RequestMapping(value = "/doLoginOut", method = RequestMethod.GET)
    public ApiResult doLoginOut(HttpServletRequest request, HttpServletResponse response,
                                HttpSession session) {
        String sessionId = session.getId();
        session.removeAttribute(GlobalConstant.SESSION_USER_MANAGE);
        // 删除redis缓存的session
        Set<String> keys = redisTemplate.keys("*" + sessionId + "*");
        redisTemplate.delete(keys);
        return ApiResult.newInstance();
    }

    /**
     * 添加并修改管理员
     *
     * @param req
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    public ApiResult addAndUpdate(@RequestBody @Valid SysUserSaveOrUpdateReq req) {

        SysUser sessionUser = getSessionUser();

        SysUser sysUser = new SysUser();
        BeanUtil.copyProperties(sysUser, req);

        if (sysUser.getId() == null || sysUser.getId() == 0) {
            //保存管理员
            if (StringUtil.isEmpty(req.getLoginPwd())) {
                throw new ServiceException("请输入密码");
            }
            sysUserService.saveSysUser(req.getSysRoleIds(), sysUser, sessionUser);
        } else {
            //更新管理员
            sysUserService.updateSysuser(req.getSysRoleIds(), sysUser, sessionUser);
        }
        return ApiResult.newInstance();
    }

    /**
     * 查询所有列表
     *
     * @param request
     * @param response
     * @param session
     * @return
     */
    @RequestMapping(value = "/findPageList", method = RequestMethod.POST)
    public ApiResult findPageList(HttpServletRequest request, HttpServletResponse response,
                                  HttpSession session, @RequestBody SysUserQueryReq req) {
        Map<String, Object> map = new HashMap<>();
        if (req.getOrgId() != null) {
            map.put("orgId", req.getOrgId());
        }
        if (StringUtil.isNotBlank(req.getUserName())) {
            map.put("userName", req.getUserName());
        }
        if (StringUtil.isNotBlank(req.getState())) {
            map.put("state", req.getState());
        }
        if (StringUtil.isNotBlank(req.getJobNo())) {
            map.put("jobNo", req.getJobNo());
        }
        if (StringUtil.isNotBlank(req.getRealName())) {
            map.put("realName", req.getRealName());
        }
        if (StringUtil.isNotBlank(req.getSex())) {
            map.put("sexDictCode", req.getSex());
        }
        if (StringUtil.isNotBlank(req.getMobilePhone())) {
            map.put("mobilePhone", req.getMobilePhone());
        }
        if (StringUtil.isNotBlank(req.getEmail())) {
            map.put("email", req.getEmail());
        }
        if (req.getRoleIds() != null && req.getRoleIds().size() > 0) {
            map.put("roleIds", req.getRoleIds());
        }
        if (StringUtil.isNotBlank(req.getStartTime())) {
            map.put("startTime", req.getStartTime());
        }
        if (StringUtil.isNotBlank(req.getEndTime())) {
            map.put("endTime", req.getEndTime());
        }

        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        List<SysUserModel> list = sysUserService.findSysUserList(map);
        PageInfo<SysUserModel> pageInfo = new PageInfo<>(list);

        return ApiResult.newInstance().addResult(pageInfo);

    }

    /**
     * 查询用户详情
     *
     * @param req
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/findDetail", method = RequestMethod.POST)
    public ApiResult findDetail(@RequestBody SysUserQueryReq req) {

        SysUserModel userModel = sysUserService.findDetail(req.getId());

        return ApiResult.newInstance().addResult(userModel);
    }

    /**
     * 封装登录返回信息
     *
     * @param sysUser
     * @return
     */
    public Map<String, Object> fillLoginUser(SysUser sysUser) {
        Map<String, Object> sessionUser = new HashMap<>();
        sessionUser.put("headPic", sysUser.getHeadPic());
        sessionUser.put("email", StringUtil.getHideEmail(sysUser.getEmail()));
        sessionUser.put("jobNo", sysUser.getJobNo());
        sessionUser.put("mobilePhone", StringUtil.getHideMobile(sysUser.getMobilePhone()));
        sessionUser.put("realName", sysUser.getRealName());
        sessionUser.put("userName", sysUser.getUserName());

		/* 查询用户角色 */
        List<SysRole> roleList = sysUserRoleService.findSysUserRoleList(sysUser.getId());
        if (CollectionUtils.isEmpty(roleList)) {
            throw new ServiceException("此用户无后台角色");
        }
        sessionUser.put("roleList", roleList);//将角色信息放入session

		/* 登录默认使用第一个角色拥有的菜单 */
        SysRole firstRole = roleList.get(0);
        //默认的角色
        sessionUser.put("roleName", firstRole.getRoleName());
        sessionUser.put("roleId", firstRole.getId());

        // 查询角色拥有的左侧菜单列表
        List<SysMenuModel> initNavButton = sysRoleMenuService.findInitNavButtons(firstRole.getId());
        sessionUser.put("initNavButton", initNavButton);

        // 组织机构
        SysOrg org = sysOrgService.findSysOrgById(sysUser.getOrgId());
        String sysOrgName = "";
        if (org != null) {
            sysOrgName = org.getOrgName();
        }
        sessionUser.put("sysOrgName", sysOrgName);
        return sessionUser;
    }
}
