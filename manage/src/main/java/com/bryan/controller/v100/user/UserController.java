package com.bryan.controller.v100.user;

import com.bryan.common.constant.ApiResult;
import com.bryan.common.constant.user.UserConstant;
import com.bryan.common.exception.ServiceException;
import com.bryan.common.utils.BeanUtil;
import com.bryan.common.utils.DateUtil;
import com.bryan.common.utils.StringUtil;
import com.bryan.common.utils.excel.ExportExcel;
import com.bryan.controller.BaseController;
import com.bryan.controller.v100.vo.user.UserDataReq;
import com.bryan.controller.v100.vo.user.UserLockReq;
import com.bryan.controller.v100.vo.user.UserQueryReq;
import com.bryan.controller.v100.vo.user.corpInfo.CorpInfoQueryReq;
import com.bryan.controller.v100.vo.user.personInfo.PersonInfoQueryReq;
import com.bryan.dao.user.domain.User;
import com.bryan.dao.user.model.UserCorpInfoModel;
import com.bryan.dao.user.model.UserDataModel;
import com.bryan.dao.user.model.UserDetailModel;
import com.bryan.dao.user.model.UserPersonInfoModel;
import com.bryan.v100.service.user.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/3/1
 */
@RestController
@RequestMapping(value = "/api/v100/user/info")
public class UserController extends BaseController {

    @Resource
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/findPersonPageList", method = RequestMethod.POST)
    public ApiResult findPersonPageList(HttpServletRequest request, HttpServletResponse response,
                                        @RequestBody PersonInfoQueryReq req) {

        String loanTagStr = req.getLoanTag();
        String[] loanTags = loanTagStr.split(",");

        Map<String, Object> map = new HashMap<>();
        // 用户类型,个人
        map.put("utype", UserConstant.UTYPE_PERSON);
        // 担保机构
        map.put("loanTag", loanTags);
        if (StringUtil.isNotBlank(req.getRealName())) {
            map.put("realName", req.getRealName());
        }
        if (StringUtil.isNotBlank(req.getUserName())) {
            map.put("userName", req.getUserName());
        }
        if (StringUtil.isNotBlank(req.getMobile())) {
            map.put("mobile", req.getMobile());
        }
        if (StringUtil.isNotBlank(req.getIdNumber())) {
            map.put("idNumber", req.getIdNumber());
        }
        if (StringUtil.isNotBlank(req.getStartTime())) {
            map.put("startTime", req.getStartTime());
        }
        if (StringUtil.isNotBlank(req.getEndTime())) {
            map.put("endTime", req.getEndTime());
        }
        if (StringUtil.isNotBlank(req.getRealNameState())) {
            map.put("realNameState", req.getRealNameState());
        }
        if (StringUtil.isNotBlank(req.getState())) {
            map.put("state", req.getState());
        }
        if (req.getRegSource() != null && !req.getRegSource().isEmpty()) {
            map.put("regSource", req.getRegSource());
        }
        if (req.getClientType() != null && !req.getClientType().isEmpty()) {
            map.put("clientType", req.getClientType());
        }

        PageHelper.startPage(req.getPageNum(), req.getPageSize());

        List<UserPersonInfoModel> list = userService.findUserPersonInfoList(map);
        PageInfo<UserPersonInfoModel> pageInfo = new PageInfo<>(list);

        return ApiResult.newInstance().addResult(pageInfo);
    }

    /**
     * 导出个人用户列表
     *
     * @param request
     * @param response
     * @param req
     */
    @ResponseBody
    @RequestMapping(value = "exportPersonFile", method = RequestMethod.GET)
    public ApiResult exportPersonFile(HttpServletRequest request, HttpServletResponse response, PersonInfoQueryReq req) {

        String loanTagStr = req.getLoanTag();
        String[] loanTags = loanTagStr.split(",");

        Map<String, Object> map = new HashMap<>();
        // 个人用户
        map.put("utype", UserConstant.UTYPE_PERSON);
        // 担保机构
        map.put("loanTag", loanTags);
        if (StringUtil.isNotBlank(req.getRealName())) {
            map.put("realName", req.getRealName());
        }
        if (StringUtil.isNotBlank(req.getUserName())) {
            map.put("userName", req.getUserName());
        }
        if (StringUtil.isNotBlank(req.getMobile())) {
            map.put("mobile", req.getMobile());
        }
        if (StringUtil.isNotBlank(req.getIdNumber())) {
            map.put("idNumber", req.getIdNumber());
        }
        if (StringUtil.isNotBlank(req.getStartTime())) {
            map.put("startTime", req.getStartTime());
        }
        if (StringUtil.isNotBlank(req.getEndTime())) {
            map.put("endTime", req.getEndTime());
        }
        if (StringUtil.isNotBlank(req.getRealNameState())) {
            map.put("realNameState", req.getRealNameState());
        }
        if (StringUtil.isNotBlank(req.getState())) {
            map.put("state", req.getState());
        }
        if (req.getRegSource() != null && !req.getRegSource().isEmpty()) {
            map.put("regSource", req.getRegSource());
        }
        if (req.getClientType() != null && !req.getClientType().isEmpty()) {
            map.put("clientType", req.getClientType());
        }
        String title = "个人用户列表";
        if (UserConstant.LOAN_GUARANT.equals(req.getLoanTag())) {
            title = "担保人列表";
        }

        List<UserPersonInfoModel> list = userService.findUserPersonInfoList(map);
        String fileName = title + DateUtil.getDate("yyyyMMddHHmmss") + ".xlsx";
        try {
            new ExportExcel(title, UserPersonInfoModel.class).setDataList(list).write(response, fileName).dispose();
        } catch (IOException e) {
            throw new ServiceException("导出个人用户列表失败！失败信息：" + e.getMessage());
        }
        return ApiResult.newInstance();
    }

    @ResponseBody
    @RequestMapping(value = "/findCorpPageList", method = RequestMethod.POST)
    public ApiResult findCorpPageList(HttpServletRequest request, HttpServletResponse response,
                                      @RequestBody CorpInfoQueryReq req) {

        String loanTagStr = req.getLoanTag();
        String[] loanTags = loanTagStr.split(",");

        Map<String, Object> map = new HashMap<>();
        // 用户类型,企业
        map.put("utype", UserConstant.UTYPE_CORP);
        // 担保机构
        map.put("loanTag", loanTags);
        if (StringUtil.isNotBlank(req.getCorpName())) {
            map.put("corpName", req.getCorpName());
        }
        if (StringUtil.isNotBlank(req.getUserName())) {
            map.put("userName", req.getUserName());
        }
        if (StringUtil.isNotBlank(req.getMobile())) {
            map.put("mobile", req.getMobile());
        }
        if (StringUtil.isNotBlank(req.getAgencyCode())) {
            map.put("agencyCode", req.getAgencyCode());
        }
        if (StringUtil.isNotBlank(req.getStartTime())) {
            map.put("startTime", req.getStartTime());
        }
        if (StringUtil.isNotBlank(req.getEndTime())) {
            map.put("endTime", req.getEndTime());
        }
        if (StringUtil.isNotBlank(req.getRealNameState())) {
            map.put("realNameState", req.getRealNameState());
        }
        if (StringUtil.isNotBlank(req.getState())) {
            map.put("state", req.getState());
        }
        if (req.getRegSource() != null && !req.getRegSource().isEmpty()) {
            map.put("regSource", req.getRegSource());
        }
        if (req.getClientType() != null && !req.getClientType().isEmpty()) {
            map.put("clientType", req.getClientType());
        }

        PageHelper.startPage(req.getPageNum(), req.getPageSize());

        List<UserCorpInfoModel> list = userService.findUserCorpInfoList(map);
        PageInfo<UserCorpInfoModel> pageInfo = new PageInfo<>(list);

        return ApiResult.newInstance().addResult(pageInfo);
    }

    /**
     * 导出企业用户列表
     *
     * @param request
     * @param response
     * @param req
     */
    @RequestMapping(value = "/exportCorpFile", method = RequestMethod.GET)
    public ApiResult exportCorpFile(HttpServletRequest request, HttpServletResponse response,
                                    CorpInfoQueryReq req) {

        String loanTagStr = req.getLoanTag();
        String[] loanTags = loanTagStr.split(",");

        Map<String, Object> map = new HashMap<>();
        // 用户类型,企业
        map.put("utype", UserConstant.UTYPE_CORP);
        // 担保机构
        map.put("loanTag", loanTags);
        if (StringUtil.isNotBlank(req.getCorpName())) {
            map.put("corpName", req.getCorpName());
        }
        if (StringUtil.isNotBlank(req.getUserName())) {
            map.put("userName", req.getUserName());
        }
        if (StringUtil.isNotBlank(req.getMobile())) {
            map.put("mobile", req.getMobile());
        }
        if (StringUtil.isNotBlank(req.getAgencyCode())) {
            map.put("agencyCode", req.getAgencyCode());
        }
        if (StringUtil.isNotBlank(req.getStartTime())) {
            map.put("startTime", req.getStartTime());
        }
        if (StringUtil.isNotBlank(req.getEndTime())) {
            map.put("endTime", req.getEndTime());
        }
        if (StringUtil.isNotBlank(req.getRealNameState())) {
            map.put("realNameState", req.getRealNameState());
        }
        if (StringUtil.isNotBlank(req.getState())) {
            map.put("state", req.getState());
        }
        if (req.getRegSource() != null && !req.getRegSource().isEmpty()) {
            map.put("regSource", req.getRegSource());
        }
        if (req.getClientType() != null && !req.getClientType().isEmpty()) {
            map.put("clientType", req.getClientType());
        }

        String title = "企业用户列表";
        if (UserConstant.LOAN_GUARANT.equals(req.getLoanTag())) {
            title = "担保机构列表";
        }

        List<UserCorpInfoModel> list = userService.findUserCorpInfoList(map);
        String fileName = title + DateUtil.getDate("yyyyMMddHHmmss") + ".xlsx";
        try {
            new ExportExcel(title, UserCorpInfoModel.class).setDataList(list).write(response, fileName).dispose();
        } catch (IOException e) {
            throw new ServiceException("导出企业用户列表失败！失败信息：" + e.getMessage());
        }
        return ApiResult.newInstance();
    }

    /**
     * 用户详情
     *
     * @param request
     * @param response
     * @param req
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/findDetail", method = RequestMethod.POST)
    public ApiResult findDetail(HttpServletRequest request, HttpServletResponse response,
                                @RequestBody UserQueryReq req) {
        User user = userService.selectByPrimaryKey(req.getUserId());
        if (user == null) {
            throw new ServiceException("用户信息查询失败");
        }
        UserDetailModel detailModel = userService.findDetailByUser(user);
        return ApiResult.newInstance().addResult(detailModel);
    }

    /**
     * 更新用户状态10正常,20注销,30冻结
     *
     * @param request
     * @param response
     * @param req
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateUserState", method = RequestMethod.POST)
    public ApiResult updateUserState(HttpServletRequest request, HttpServletResponse response,
                                     @RequestBody @Valid UserLockReq req) {
        User user = userService.selectByPrimaryKey(req.getUserId());
        if (user == null) {
            throw new ServiceException("用户查询失败");
        }
        User updateUser = new User();
        updateUser.setId(user.getId());
        updateUser.setState(req.getState());
        userService.updateByPrimaryKeySelective(updateUser);
        return ApiResult.newInstance();
    }

    /**
     * @return ApiResult    返回类型
     * @Title: findUserData
     * @Description: 查询要用户数据
     */
    @ResponseBody
    @RequestMapping(value = "/findUserData", method = RequestMethod.POST)
    public ApiResult findUserData(HttpServletRequest request, HttpServletResponse response,
                                  @RequestBody UserDataReq req) {
        PageHelper.startPage(req.getPageNum(), req.getPageSize());

        Map<String, Object> map = BeanUtil.bean2Map(req);
        List<UserDataModel> list = userService.findUserData(map);
        PageInfo<UserDataModel> pageInfo = new PageInfo<>(list);

        return ApiResult.newInstance().addResult(pageInfo);
    }
}
