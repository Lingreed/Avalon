package com.bryan.controller;

import com.bryan.common.constant.ApiResult;
import com.bryan.common.constant.GlobalConstant;
import com.bryan.common.exception.ServiceException;
import com.bryan.common.redis.RedisTemplateUtil;
import com.bryan.common.utils.OrderNoUtil;
import com.bryan.common.utils.StringUtil;
import com.bryan.common.utils.VerifyCodeUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: VcodeController
 * Function:  验证码controller
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/5
 */
@RequestMapping("/api/v100/vCode")
@Scope("prototype")
@RestController
public class VcodeController extends BaseController {

    @RequestMapping(value = "/findVcodeKey", method = RequestMethod.GET)
    public ApiResult findVcodeKey(HttpServletRequest request) {
        String vcodeKey = GlobalConstant.VCODE_PREFIX + OrderNoUtil.getRandomKey();
        String verifyCode = VerifyCodeUtil.generateVerifyCode(4);
        // 设置有效期2分钟
        RedisTemplateUtil.set(vcodeKey, verifyCode.toLowerCase(), 120);
        Map<String, String> map = new HashMap<>();
        map.put("vcodeKey", vcodeKey);
        map.put("url", "/imgCode");
        map.put("msg", "2分钟内有效");
        return ApiResult.newInstance().addResult(map);
    }

    /**
     * @param response
     * @throws IOException
     * @Title: sendVerifyCode
     * @Description: 生成图片验证码
     */
    @RequestMapping(value = "/imgCode", method = RequestMethod.GET)
    public void sendVerifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        // 获取vCodeKey对应验证码
        String vCodeKey = request.getParameter("vCodeKey");
        if (StringUtil.isEmpty(vCodeKey)) {
            throw new ServiceException("vCodeKey参数错误");
        }
        String vCode = RedisTemplateUtil.get(vCodeKey);
        if (StringUtil.isEmpty(vCode)) {
            throw new ServiceException("验证码已过期");
        }
        // 生成图片
        int w = 140, h = 40;
        VerifyCodeUtil.outputImage(w, h, response.getOutputStream(), vCode);
    }
}
