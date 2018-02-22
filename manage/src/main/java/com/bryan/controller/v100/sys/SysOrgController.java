package com.bryan.controller.v100.sys;

import com.bryan.common.constant.ApiResult;
import com.bryan.common.utils.BeanUtil;
import com.bryan.controller.BaseController;
import com.bryan.controller.v100.vo.sys.org.SysOrgQueryReq;
import com.bryan.controller.v100.vo.sys.org.SysOrgSaveOrUpdateReq;
import com.bryan.sys.domain.SysOrg;
import com.bryan.sys.domain.SysUser;
import com.bryan.sys.model.SysOrgDetailModel;
import com.bryan.sys.model.SysOrgModel;
import com.bryan.v100.sys.service.SysOrgService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ClassName: SysOrgController
 * Function:  组织机构
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/22
 */
@RestController
@RequestMapping("/api/v100/sys/sysOrg")
public class SysOrgController extends BaseController {

    @Resource
    private SysOrgService sysOrgService;

    @ResponseBody
    @RequestMapping(value="/findAll",method = RequestMethod.POST)
    public ApiResult findAll(HttpServletRequest request, HttpServletResponse response,
                             @RequestBody SysOrgQueryReq req){
        List<SysOrgModel> list = sysOrgService.findAllList(req.getState());
        return ApiResult.newInstance().addResult(list);
    }

    @ResponseBody
    @RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
    public ApiResult addAndUpdate(HttpServletRequest request, HttpServletResponse response,
                                  HttpSession session, @RequestBody SysOrgSaveOrUpdateReq req){
        SysUser sessionUser = getSessionUser( );
        SysOrg org = new SysOrg();
        BeanUtil.copyProperties(org,req);

        if(req.getId()!= 0 && req.getId() >0){
            //更新
            sysOrgService.updateSysOrg(org,sessionUser);
        }else{
            //新增
            sysOrgService.saveSysOrg(org,sessionUser);
        }
        return ApiResult.newInstance();
    }

    /**
     * 机构详情
     * @param request
     * @param response
     * @param req
     * @return
     */
    @RequestMapping(value="/findDetail", method=RequestMethod.POST)
    public ApiResult findDetail(HttpServletRequest request, HttpServletResponse response,
                                @RequestBody SysOrgQueryReq req){
        SysOrg org = sysOrgService.selectByPrimaryKey(req.getId());
        List<Integer> pids = sysOrgService.findParentIds(req.getId(), new ArrayList<Integer>());
        Collections.reverse(pids);// 排序重新处理下
        SysOrgDetailModel orgDetail = new SysOrgDetailModel();
        BeanUtil.copyProperties(orgDetail, org);
        orgDetail.setPids(pids);
        return ApiResult.newInstance().addResult(orgDetail);
    }
}
