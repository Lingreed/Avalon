package com.bryan.dao.user.mapper;

import com.bryan.common.base.BaseMapper;
import com.bryan.dao.user.domain.UserPersonInfo;

/**
 * ClassName: UserPersonInfo
 * Function: 用户个人信息表 mapper
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/3/1
 */
public interface UserPersonInfoMapper extends BaseMapper<UserPersonInfo> {

    /**
     * 根据userId查询身份证号码
     *
     * @param userId 用户id
     * @return
     */
    String selectIdNumberByUserId(int userId);

}
