package com.bryan.controller.v100.sys;

import com.bryan.common.constant.ApiResult;
import com.bryan.common.utils.BeanUtil;
import com.bryan.controller.BaseController;
import com.bryan.controller.v100.vo.sys.menu.SysMenuQueryReq;
import com.bryan.controller.v100.vo.sys.menu.SysMenuSaveOrUpdateReq;
import com.bryan.sys.domain.SysMenu;
import com.bryan.sys.model.SysMenuDetailModel;
import com.bryan.sys.model.SysMenuModel;
import com.bryan.v100.sys.service.SysMenuService;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ClassName:  SysMenuController
 * Function:   系统菜单
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/22
 */
@RestController
@RequestMapping("/api/v100/sys/sysMenu")
public class SysMenuController extends BaseController {

    @Resource
    private SysMenuService sysMenuService;

    /**
     * 查询列表，全部查询
     *
     * @param request
     * @param response
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ApiResult findAll(HttpServletRequest request, HttpServletResponse response,
                             HttpSession session) {

        List<SysMenuModel> list = sysMenuService.findAllList();

        return ApiResult.newInstance().addResult(list);

    }

    @ResponseBody
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    public ApiResult addAndUpdate(HttpServletRequest request, HttpServletResponse response,
                                  HttpSession session, @RequestBody SysMenuSaveOrUpdateReq req) {
        SysMenu menu = new SysMenu();
        BeanUtil.copyProperties(menu, req);

        //父级判断
        if (null == req.getPid()) {
            menu.setPid(0);
        }

        if (req.getId() != null && req.getId() > 0) {
            //更新
            sysMenuService.updateSysMenu(menu);
        } else {
            //新增
            sysMenuService.saveSysMenu(menu);
        }
        return ApiResult.newInstance();

    }

    /**
     * 查询菜单详情
     *
     * @param request
     * @param response
     * @param req
     * @return
     */
    @RequestMapping(value = "/findDetail", method = RequestMethod.POST)
    public ApiResult findDetail(HttpServletRequest request, HttpServletResponse response,
                                @RequestBody SysMenuQueryReq req) {
        Example example = new Example(SysMenu.class);
        example.createCriteria().andEqualTo("id", req.getMenuId());
        SysMenu sysMenu = sysMenuService.selectOneByExample(example);

        List<Integer> pids = sysMenuService.findPids(sysMenu.getId(), new ArrayList<Integer>());
        Collections.reverse(pids);

        SysMenuDetailModel menuDetail = new SysMenuDetailModel();
        BeanUtil.copyProperties(menuDetail, sysMenu);
        menuDetail.setPids(pids);

        return ApiResult.newInstance().addResult(menuDetail);
    }
}
