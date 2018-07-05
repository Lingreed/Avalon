package com.bryan.dao.user.mapper;

import com.bryan.common.base.BaseMapper;
import com.bryan.dao.stats.model.StatsModel;
import com.bryan.dao.stats.model.StatsResultModel;
import com.bryan.dao.user.domain.User;
import com.bryan.dao.user.model.UserCorpInfoModel;
import com.bryan.dao.user.model.UserDataModel;
import com.bryan.dao.user.model.UserPersonInfoModel;

import java.util.List;
import java.util.Map;

/**
 * ClassName: User
 * Function: 用户表 mapper
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/3/1
 */
public interface UserMapper extends BaseMapper<User> {
    /**
     * 查询个人用户列表信息
     * @param map
     * @return
     */
    List<UserPersonInfoModel> findUserPersonInfoList(Map<String,Object> map);

    /**
     * 查询企业用户列表信息
     * @param map
     * @return
     */
    List<UserCorpInfoModel> findUserCorpInfoList(Map<String,Object> map);

    /**
     * @Title: findGuarantUsers
     * @Description: 查询用户数据
     * @return List<UserDataModel>    返回类型
     */
    List<UserDataModel> findUsersData(Map<String,Object> map);

    /**
     * 查询最近12周每周注册人数
     * @return
     */
    List<StatsResultModel> findByTwelveWeek(StatsModel user);

    /**
     * 查询最近12月每月注册人数
     * @param user
     * @return
     */
    List<StatsResultModel> findByTwelveMonth(StatsModel user);

    /**
     * 查询最近30天每天注册的人数
     * @param user
     * @return
     */
    List<StatsResultModel> findByThirtyDay(StatsModel user);

    /**
     * 上个月人数
     * @param stats
     * @return
     */
    StatsResultModel findByBeforeMonthy(StatsModel stats);

    /**
     * 三个月内人数
     * @param stats
     * @return
     */
    StatsResultModel findByThreeMonthy(StatsModel stats);

    /**
     * 6个月内人数
     * @param stats
     * @return
     */
    StatsResultModel findBySixMonthy(StatsModel stats);

    /**
     * 总的人数
     * @param stats
     * @return
     */
    StatsResultModel findBySum(StatsModel stats);

    /**
     * 时间段内注册的人数
     * @param stats
     * @return
     */
    StatsResultModel findByRegisterNum(StatsModel stats);

    /**
     * 时间段内首次注册就实名的
     * @param stats 状态
     * @return StatsResultModel
     */
    StatsResultModel findFirstAutonym(StatsModel stats);

    /**
     * 根据用户id查询密码
     * @param userId 用户id
     * @return pwd
     */
    String selectPayPwdByUserId(Integer userId);

    /**
     * 查询手机号
     * @param userId yonghuid
     * @return mobile
     */
    String findMobile(Integer userId);

    /**
     * 邀请人数递增
     * @param userId
     * @return
     */
    int increaseInviteCount(Integer userId);
}
