package com.bryan.controller.v100.sys;

import com.bryan.common.constant.ApiResult;
import com.bryan.common.exception.ServiceException;
import com.bryan.common.utils.BeanUtil;
import com.bryan.common.utils.StringUtil;
import com.bryan.controller.BaseController;
import com.bryan.controller.v100.vo.sys.dict.DictAddReq;
import com.bryan.controller.v100.vo.sys.dict.DictQueryReq;
import com.bryan.sys.domain.SysDict;
import com.bryan.sys.domain.SysUser;
import com.bryan.v100.service.sys.SysDictService;
import com.bryan.v100.service.sys.SysDictTypeService;
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
import java.util.Map;

/**
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/7
 */
@RestController
@RequestMapping("/api/v100/sys/sysDict")
public class SysDictController extends BaseController {
    @Resource
    private SysDictService sysDictService;

    @Resource
    private SysDictTypeService sysDictTypeService;

    @ResponseBody
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    public ApiResult addOrUpdate(HttpServletRequest request, HttpServletResponse response
            , HttpSession session, @RequestBody @Valid DictAddReq req) {

        SysUser sessionUser = getSessionUser();

        SysDict dict = new SysDict();
        BeanUtil.copyProperties(dict, req);

        if (dict.getId() == null || dict.getId() == 0) {
            //保存
            sysDictService.saveSysDict(dict);
        } else {
            sysDictService.updateSysDict(dict);
        }
        return ApiResult.newInstance();
    }

    @ResponseBody
    @RequestMapping("/findAll")
    public ApiResult findAll(HttpServletRequest request, HttpServletResponse response
            , HttpSession session, @RequestBody @Valid DictQueryReq req) {
        Example example = new Example(SysDict.class);
        Example.Criteria criteria = example.createCriteria();
        if (req.getSysDictTypeId() != null && req.getSysDictTypeId() != 0) {
            criteria.andEqualTo("sysDictTypeId", req.getSysDictTypeId());
        }
        if (StringUtil.isNotBlank(req.getDictCode())) {
            criteria.andEqualTo("dictCode", req.getDictCode());
        }
        List<SysDict> list = sysDictService.selectByExample(example);

        return ApiResult.newInstance().addResult(list);
    }

    @ResponseBody
    @RequestMapping(value = "/findPageList", method = RequestMethod.POST)
    public ApiResult findPageList(HttpServletRequest request, HttpServletResponse response
            , HttpSession session, @RequestBody @Valid DictQueryReq req) {
        Example example = new Example(SysDict.class);
        Example.Criteria criteria = example.createCriteria();
        if (req.getSysDictTypeId() != null && req.getSysDictTypeId() != 0) {
            criteria.andEqualTo("sysDictTypeId", req.getSysDictTypeId());
        } else {//为空的时候,默认不查询任何数据
            criteria.andEqualTo("sysDictTypeId", -1);
        }
        if (StringUtil.isNotBlank(req.getDictCode())) {
            criteria.andEqualTo("dictCode", req.getDictCode());
        }
        PageHelper.startPage(req.getPageNum(), req.getPageSize());

        List<SysDict> list = sysDictService.selectByExample(example);
        PageInfo<SysDict> paginInfo = new PageInfo<>(list);

        return ApiResult.newInstance().addResult(paginInfo);
    }

    /**
     * 查询字典初始化
     *
     * @param request
     * @param response
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/findInitDict")
    public ApiResult findInitDict(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        Map<String, List<SysDict>> dictModelList = sysDictService.findInitDict();
        return ApiResult.newInstance().addResult(dictModelList);
    }

    /**
     * 字典详情
     *
     * @param request
     * @param response
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/findDetail", method = RequestMethod.POST)
    public ApiResult findDetail(HttpServletRequest request, HttpServletResponse response, HttpSession session,
                                @RequestBody DictQueryReq req) {
        SysDict sysDict = sysDictService.selectByPrimaryKey(req.getDictId());
        if (sysDict == null) {
            throw new ServiceException("查询字典错误");
        }
        return ApiResult.newInstance().addResult(sysDict);
    }
}
