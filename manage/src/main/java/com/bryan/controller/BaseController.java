package com.bryan.controller;

import com.bryan.common.constant.redis.RedisConstant;
import com.bryan.common.context.Global;
import com.bryan.common.exception.ServiceException;
import com.bryan.common.redis.RedisTemplateUtil;
import com.bryan.sys.model.SysUserLoginModel;
import com.github.pagehelper.StringUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/5
 */
@RestController
@Scope("prototype")
@RequestMapping("/api/v100")
public class BaseController {

    /**
     * @Title: setSessionUser
     * @Description: 保存上下文, 并且设置过期时间
     */
    public void setSessionUser(SysUserLoginModel sysUser, String token, HttpServletResponse response) {
        // 设置授权token对应用户缓存信息
        RedisTemplateUtil.set(token, sysUser, RedisConstant.TOKEN_TIME_OUT);
        // 返回授权authToken 到 cookie
        Cookie cookie = new Cookie("accessToken", token);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }

    /**
     * @param sysUser
     * @Title: updateSessionUser
     * @Description: 修改sessionUser
     */
    public void updateSessionUser(SysUserLoginModel sysUser) {
        // 设置授权token对应用户缓存信息
        RedisTemplateUtil.set(Global.getToken(), sysUser, RedisConstant.TOKEN_TIME_OUT);
    }

    public SysUserLoginModel getSessionUser() {
        return RedisTemplateUtil.get(Global.getToken());
    }

    /**
     * 图片验证码校验
     *
     * @param imgCode 验证码
     * @return
     */
    public void checkImgCode(String imgCode, String vCodeKey) {
        String oldVcode = RedisTemplateUtil.get(vCodeKey);
        if (StringUtil.isEmpty(oldVcode)) {
            throw new ServiceException("验证码已过期，请重新获取", "vCode");
        }
        if (StringUtil.isEmpty(imgCode)) {
            throw new ServiceException("请输入验证码", "vCode");
        }
        if (!oldVcode.equals(imgCode.toLowerCase())) {
            throw new ServiceException("验证码错误，请重新输入", "vCode");
        }
    }
}
