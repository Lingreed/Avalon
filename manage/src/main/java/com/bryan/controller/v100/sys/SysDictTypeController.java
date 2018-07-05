package com.bryan.controller.v100.sys;

import com.bryan.common.constant.ApiResult;
import com.bryan.common.exception.ServiceException;
import com.bryan.common.utils.BeanUtil;
import com.bryan.common.utils.StringUtil;
import com.bryan.controller.BaseController;
import com.bryan.controller.v100.vo.sys.dict.DictTypeQueryReq;
import com.bryan.controller.v100.vo.sys.dict.DictTypeSaveOrUpdateReq;
import com.bryan.sys.domain.SysDictType;
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

/**
 * ClassName: SysDictTypeController
 * Function: 字典类型
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/22
 */
@RestController
@RequestMapping("/api/v100/sys/sysDictType")
public class SysDictTypeController extends BaseController {

    @Resource
    private SysDictTypeService sysDictTypeService;

    @ResponseBody
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    public ApiResult addAndUpdate(HttpServletRequest request,
                                  HttpServletResponse response, HttpSession session,
                                  @RequestBody @Valid DictTypeSaveOrUpdateReq req) {
        SysDictType dictType = new SysDictType();
        BeanUtil.copyProperties(dictType, req);

        if (dictType.getId() == null || dictType.getId() == 0) {
            //保存
            sysDictTypeService.saveSysDictType(dictType);
        } else {
            sysDictTypeService.updateSysDictType(dictType);
        }
        return ApiResult.newInstance();
    }

    @ResponseBody
    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    public ApiResult findAll(HttpServletRequest request,
                             HttpServletResponse response, HttpSession session, @RequestBody DictTypeQueryReq req) {
        Example example = new Example(SysDictType.class);
        Example.Criteria typeParam = example.createCriteria();
        if (StringUtil.isNotBlank(req.getState())) {
            typeParam.andEqualTo("state", req.getState());
        }

        List<SysDictType> list = sysDictTypeService.selectByExample(example);

        return ApiResult.newInstance().addResult(list);
    }

    @ResponseBody
    @RequestMapping(value = "/findPageList", method = RequestMethod.POST)
    public ApiResult findPageList(HttpServletRequest request,
                                  HttpServletResponse response, HttpSession session,
                                  @RequestBody DictTypeQueryReq req) {

        Example example = new Example(SysDictType.class);
        Example.Criteria criteria = example.createCriteria();

        if (StringUtil.isNotBlank(req.getState())) {
            criteria.andEqualTo("state", req.getState());
        }
        if (StringUtil.isNotBlank(req.getTypeCode())) {
            criteria.andEqualTo("typeCode", req.getTypeCode());
        }
        if (StringUtil.isNotBlank(req.getTypeName())) {
            criteria.andLike("typeName", StringUtil.getLike(req.getTypeName()));
        }
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        List<SysDictType> list = sysDictTypeService.selectByExample(example);
        PageInfo<SysDictType> pageInfo = new PageInfo<>(list);

        return ApiResult.newInstance().addResult(pageInfo);
    }

    @ResponseBody
    @RequestMapping(value = "/findDetail", method = RequestMethod.POST)
    public ApiResult findDetail(HttpServletRequest request,
                                HttpServletResponse response, HttpSession session,
                                @RequestBody DictTypeQueryReq req) {

        SysDictType sysDictType = sysDictTypeService.selectByPrimaryKey(req.getDictTypeId());
        if (sysDictType == null) {
            throw new ServiceException("字典项查询失败");
        }

        return ApiResult.newInstance().addResult(sysDictType);
    }
}
