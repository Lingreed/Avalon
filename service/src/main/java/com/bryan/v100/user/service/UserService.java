package com.bryan.v100.user.service;

import com.bryan.common.base.BaseService;
import com.bryan.user.domain.User;
import com.bryan.user.model.UserCorpInfoModel;
import com.bryan.user.model.UserDataModel;
import com.bryan.user.model.UserDetailModel;
import com.bryan.user.model.UserPersonInfoModel;

import java.util.List;
import java.util.Map;

/**
 * ClassName: UserService
 * Function: 用户相关业务处理
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/3/1
 */
public interface UserService extends BaseService<User> {
    /**
     * 根据手机号查询用户
     * @param mobile 手机号
     * @return
     */
    User findByMobile(String mobile);

    /**
     * 查询个人用户列表信息
     * @return List<UserPersonInfoModel>
     */
    List<UserPersonInfoModel> findUserPersonInfoList(Map<String, Object> map);

    /**
     * 查询企业用户列表信息
     * @param map
     * @return List<UserCorpInfoModel>
     */
    List<UserCorpInfoModel> findUserCorpInfoList(Map<String, Object> map);

    /**
     * 查询用户详情
     * @param user
     * @return UserDetailModel
     */
    UserDetailModel findDetailByUser(User user);

    /**
     * 保存user信息
     * @param user 需要保存用户信息
     */
    User initSave(User user);

    /**
     * 修改用户信息
     * @param user
     */
    void update(User user);

    /**
     * @Title: findGuarantUsers
     * @Description: 查询用户数据
     * @param map 查询参数
     * @return List<UserDataModel>
     */
    List<UserDataModel> findUserData(Map<String,Object> map);

    /**
     * @Title: findById
     * @Description: 根据用户id查询
     * @param userId
     * @return
     */
    User findById(Integer userId);

    /**
     * 投资用户注册
     */
    User investUserRegister(User user);

    /**
     * 登录
     */
    User login(User user);

    /**
     * 修改密码
     * @param userId 用户id
     * @param password 密码
     */
    void updatePwd(Integer userId, String password);

    /**
     * 修改支付密码
     * @param userId 用户id
     * @param password 密码
     */
    void updatePayPwd(Integer userId, String password);

    /**
     * 查询手机号
     * @param userId yonghuid
     * @return mobile
     */
    String findMobile(Integer userId);
}
