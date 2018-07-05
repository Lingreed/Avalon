package com.bryan.controller.v100.sys;

import com.bryan.common.constant.ApiResult;
import com.bryan.common.exception.ServiceException;
import com.bryan.controller.BaseController;
import com.bryan.controller.v100.vo.sys.roleMenu.AddMyRoleMenus;
import com.bryan.controller.v100.vo.sys.roleMenu.MyRoleMenusReq;
import com.bryan.sys.domain.SysRoleMenu;
import com.bryan.sys.domain.SysUser;
import com.bryan.sys.domain.SysUserRole;
import com.bryan.sys.model.SysMenuModel;
import com.bryan.sys.model.SysUserLoginModel;
import com.bryan.v100.service.sys.SysMenuService;
import com.bryan.v100.service.sys.SysRoleMenuService;
import com.bryan.v100.service.sys.SysUserRoleService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * ClassName:  SysRoleMenuController
 * Function:   角色菜单|授权
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/23
 */
@RestController
@RequestMapping("/api/v100/sys/sysRoleMenu")
public class SysRoleMenuController extends BaseController {
    @Resource
    private SysRoleMenuService sysRoleMenuService;
    @Resource
    private SysUserRoleService sysUserRoleService;
    @Resource
    private SysMenuService sysMenuService;

    /**
     * 选择角色查询角色对应的导航按钮
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/findRoleInitButton", method= RequestMethod.POST)
    public ApiResult findMyNavButtons(HttpServletRequest request, HttpServletResponse response,
                                      HttpSession session, @RequestBody MyRoleMenusReq req){
        SysUserLoginModel user = getSessionUser();
        // 查询用户是否有此角色
        SysUserRole userRole = sysUserRoleService.findUserRole(user.getId(), req.getRoleId());
        if(userRole == null){
            throw new ServiceException("角色查询失败");
        }

        List<SysMenuModel> list = sysRoleMenuService.findInitNavButtons(req.getRoleId());

        user.setRoleId(req.getRoleId());
        updateSessionUser(user);//设置登录用户信息

        return ApiResult.newInstance().addResult(list);
    }

    /**
     * 点击左侧菜单,查询页面按钮
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/findPageButtons", method=RequestMethod.POST)
    public ApiResult findPageButtons(HttpServletRequest request, HttpServletResponse response,
                                     HttpSession session,@RequestBody @Valid MyRoleMenusReq req){
        SysUser user = getSessionUser( );
        // 查询用户是否有此角色
        SysUserRole userRole = sysUserRoleService.findUserRole(user.getId(), req.getRoleId());
        if(userRole == null){
            throw new ServiceException("角色查询失败");
        }

        SysRoleMenu roleMenu = sysRoleMenuService.findSysRoleMenu(req.getRoleId(), req.getLeftMenuId());
        if(roleMenu == null){
            throw new ServiceException("查询左侧菜单记录失败");
        }
        Map<String,String> map = sysRoleMenuService.findPageMenus(req.getRoleId(), req.getLeftMenuId());

        return ApiResult.newInstance().addResult(map);
    }


    /**
     * 添加或修改角色对应的菜单
     * @param request
     * @param response
     * @param session
     * @param req
     * @return
     */
    @RequestMapping(value="/saveOrUpdateRoleMenus", method=RequestMethod.POST)
    public ApiResult addOrUpdateMyRoleMenus(HttpServletRequest request, HttpServletResponse response,
                                            HttpSession session,@RequestBody @Valid AddMyRoleMenus req){
        // 添加角色菜单按钮
        sysRoleMenuService.addRoleMenus(req.getRoleId(),req.getMenuIds());
        return ApiResult.newInstance();
    }

    /**
     * 查询角色对应的菜单
     * @param request
     * @param response
     * @param session
     * @param req
     * @return
     */
    @RequestMapping(value="/findMyRoleMenus", method=RequestMethod.POST)
    public ApiResult findMyRoleMenus(HttpServletRequest request, HttpServletResponse response,
                                     HttpSession session, @RequestBody MyRoleMenusReq  req){

        List<Integer> list = sysMenuService.findRoleMenuList(req.getRoleId());
        return ApiResult.newInstance().addResult(list);
    }
}
