package com.bryan.controller.v100.sys;

import com.bryan.common.constant.ApiResult;
import com.bryan.common.utils.BeanUtil;
import com.bryan.common.utils.StringUtil;
import com.bryan.controller.BaseController;
import com.bryan.controller.v100.vo.sys.role.SysRoleQueryReq;
import com.bryan.controller.v100.vo.sys.role.SysRoleSaveOrUpdateReq;
import com.bryan.dao.sys.domain.SysRole;
import com.bryan.dao.sys.domain.SysUser;
import com.bryan.v100.service.sys.SysRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * ClassName: SysRoleController
 * Function:  系统角色
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/22
 */
@RestController
@RequestMapping("/api/v100/sys/sysRole")
public class SysRoleController extends BaseController {

    @Resource
    private SysRoleService sysRoleService;

    @ResponseBody
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    public ApiResult addAndUpdate(HttpServletRequest request, HttpServletResponse response,
                                  HttpSession session, @RequestBody @Valid SysRoleSaveOrUpdateReq req) {
        SysUser sessionUser = getSessionUser();
        SysRole role = new SysRole();
        BeanUtil.copyProperties(role, req);

        if (req.getId() != null && req.getId() > 0) {
            sysRoleService.updateSysRole(role, sessionUser);
        } else {
            sysRoleService.saveSysRole(role, sessionUser);
        }
        return ApiResult.newInstance();

    }

    @ResponseBody
    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    public ApiResult findAll(HttpServletRequest request, HttpServletResponse response,
                             @RequestBody SysRoleQueryReq req) {
        Example example = new Example(SysRole.class);
        if (StringUtil.isNotBlank(req.getState())) {
            example.createCriteria().andEqualTo("state", req.getState());
        }
        List<SysRole> list = sysRoleService.selectByExample(example);
        return ApiResult.newInstance().addResult(list);

    }

    @ResponseBody
    @RequestMapping(value = "/findPageList", method = RequestMethod.POST)
    public ApiResult findPageList(HttpServletRequest request, HttpServletResponse response,
                                  HttpSession session, @RequestBody SysRoleQueryReq req) {
        Example example = new Example(SysRole.class);
        Example.Criteria param = example.createCriteria();
        if (StringUtil.isNotBlank(req.getRoleCode())) {
            param.andLike("roleCode", "%" + req.getRoleCode() + "%");
        }
        if (StringUtil.isNotBlank(req.getRoleName())) {
            param.andLike("roleName", "%" + req.getRoleName() + "%");
        }
        if (StringUtil.isNotBlank(req.getState())) {
            param.andEqualTo("state", req.getState());
        }
        if (StringUtil.isNotBlank(req.getStartTime())) {
            param.andGreaterThanOrEqualTo("ctime", req.getStartTime());
        }
        if (StringUtil.isNotBlank(req.getEndTime())) {
            param.andLessThanOrEqualTo("ctime", req.getEndTime());
        }

        PageHelper.startPage(req.getPageNum(), req.getPageSize());

        List<SysRole> list = sysRoleService.selectByExample(example);
        PageInfo<SysRole> pageInfo = new PageInfo<>(list);

        return ApiResult.newInstance().addResult(pageInfo);

    }

    @RequestMapping(value = "/findDetail", method = RequestMethod.POST)
    public ApiResult findDetail(HttpServletRequest request, HttpServletResponse response,
                                @RequestBody SysRoleQueryReq req) {
        Example example = new Example(SysRole.class);
        example.createCriteria().andEqualTo("id", req.getRoleId());
        SysRole role = sysRoleService.selectOneByExample(example);
        return ApiResult.newInstance().addResult(role);
    }
}
