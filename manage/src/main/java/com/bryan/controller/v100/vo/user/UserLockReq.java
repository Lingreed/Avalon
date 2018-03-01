package com.bryan.controller.v100.vo.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/3/1
 */
public class UserLockReq {

    /**
     * 用户id
     */
    @NotNull(message = "用户id不能空")
    private Integer userId;

    /**
     * 状态:10正常,20注销,30冻结
     */
    @Pattern(regexp = "^(10|20|30){1}$", message = "用户状态参数错误,选择正确状态")
    private String state;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
