package com.bryan.conf.filter;

import com.alibaba.fastjson.JSON;

import com.bryan.common.constant.ApiCodeConstant;
import com.bryan.common.constant.ApiResult;
import com.bryan.common.constant.redis.RedisConstant;
import com.bryan.common.context.Global;
import com.bryan.common.exception.ServiceException;
import com.bryan.common.redis.RedisTemplateUtil;
import com.bryan.common.utils.BodyReaderHttpServletRequestWrapper;
import com.bryan.common.utils.DateUtil;
import com.bryan.common.utils.SpringContextHolder;
import com.bryan.common.utils.StringUtil;
import com.bryan.sys.domain.SysLogManage;
import com.bryan.sys.model.SysUserLoginModel;
import com.bryan.v100.async.service.AsyncLogService;
import com.bryan.v100.sys.service.SysRoleMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class SessionFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(SessionFilter.class);

    /**
     * 不需要登录的url
     */
    private static Set<String> urlSet = null;

    /**
     * 不需要记录日志url
     */
    private static Set<String> logSet = null;

    /**
     * 访问不需要校验的href
     */
    private static Set<String> notCheckHrefSet = null;


    static {

		/*不需要登录url，不需要带版本号/api/v100*/
        urlSet = new HashSet<>();
        urlSet.add("/sys/sysUser/doLogin");
        urlSet.add("/sys/sysRoleMenu/findPageButtons");
        urlSet.add("/vcode");// 验证码

		/*不需要记录日志的url*/
        logSet = new HashSet<>();
        logSet.add("/sys/sysUser/doLogin");
        logSet.add("/vcode");

		/*访问不需要校验的href*/
        notCheckHrefSet = new HashSet<>();
        notCheckHrefSet.add("/sys/sysDict/findInitDict");
        notCheckHrefSet.add("/index/getInfo");
        notCheckHrefSet.add("/sys/sysRoleMenu/findRoleInitButton");
        notCheckHrefSet.add("/project/projectExp/findAll");
        notCheckHrefSet.add("/actt/acttRule/findRules");
        notCheckHrefSet.add("/user/info/findUserData");
        notCheckHrefSet.add("/sys/sysProtocol/findAll");
        notCheckHrefSet.add("/file/imgUpload");
        notCheckHrefSet.add("/file/fileUpload");
        notCheckHrefSet.add("/file/imgUpload");
        notCheckHrefSet.add("/file/loadExcelTemplate/send_msg_template");
        notCheckHrefSet.add("/file/loadExcelTemplate/user");
        notCheckHrefSet.add("/sys/sysUser/doLoginOut");
        notCheckHrefSet.add("/sys/sysOrg/findAll");
        notCheckHrefSet.add("/sys/sysRole/findAll");
        notCheckHrefSet.add("/vCode/findVcodeKey");
        notCheckHrefSet.add("/vCode/imgCode");
        notCheckHrefSet.add("/sys/sysUser/findPageList");// 角色的地方有查询角色下用户列表
        notCheckHrefSet.add("/sys/sysMenu/findAll");//分配权限需要查询所有的菜单
        notCheckHrefSet.add("/account/accountCash/findDetail");//提现详情查询
        notCheckHrefSet.add("/channel/info/findAll");//查询所有渠道
        notCheckHrefSet.add("/doc/docColumn/findAll");//栏目列表查询所有
        notCheckHrefSet.add("/doc/docQaType/findPageAll");//问答分类所有列表
        notCheckHrefSet.add("/project/projectExpe/findAll");//体验项目记录
        notCheckHrefSet.add("/actt/acttRule/findPoints");//活动所有节点
        notCheckHrefSet.add("/channel/info/findChannel");//所有渠道列表


        notCheckHrefSet.add("/sys/sysArea/createParseJson");//所有渠道列表

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain chain) {
        try {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;

            // 根据请求信息获取登录凭证,凭证可以通过header cookie 参数传递
            Global.IP_THREADLOCAL.set(getIpAddress(request));
            String token = getReqToken(request);
            Global.TOKEN_THREADLOCAL.set(token);

            // 请求方法
            String method = request.getMethod().toLowerCase();
            // 请求content-type, 如果获取不到赋值空
            String contentType = request.getContentType();
            if (contentType == null) {
                contentType = "";
            }
            contentType = contentType.toLowerCase();

            String url = request.getRequestURI();
            if (StringUtil.isNotEmpty(url) && url.length() > 9) {
                // 截取掉签名版本号，版本号统一/api/v100这种格式
                url = url.substring(9, url.length());
            } else {
                // 请求地址必须是9位以上
                throw new ServiceException("请求地址错误");
            }
            String agent = request.getHeader("user-agent");
            // 用户id
            Integer userId = null;

            // 获取redis存储用户信息
            SysUserLoginModel sessionUser = RedisTemplateUtil.get(token);
            if (!urlSet.contains(url)) {
                //需要登录校验
                if (sessionUser == null) {
                    //返回失败信息
                    ApiResult result = ApiResult.newInstance().addCode(ApiCodeConstant.CODE_ACCESS_ERROR)
                            .addMsg("请登录");
                    printJson(response, JSON.toJSONString(result));
                    return;
                } else {
                    userId = sessionUser.getId();
                    /*判断用户权限，超级管理员不需要权限校验*/
                    if (sessionUser.getRoleId() != Global.ADMIN_ROLE_ID) {
	        	    	/*不需要校验的href，过滤掉*/
                        if (!notCheckHrefSet.contains(url)) {
                            SysRoleMenuService sysRoleMenuService = SpringContextHolder.getBean(SysRoleMenuService.class);
                            boolean isAuth = sysRoleMenuService.checkRoleMenuHref(sessionUser.getRoleId(), url);
                            if (!isAuth) {
                                logger.info("请求url：{}，没有权限", url);
                                ApiResult result = ApiResult.newInstance().addCode(ApiCodeConstant.CODE_ACCESS_ERROR).addMsg("您没有权限");
                                printJson(response, JSON.toJSONString(result));
                                return;
                            }
                        }
                    }
                    // 延续token缓存时间
                    RedisTemplateUtil.expire(token, RedisConstant.TOKEN_TIME_OUT);
                }
            }

            // 请求日志处理
            BodyReaderHttpServletRequestWrapper requestWrapper = null;
            if (!logSet.contains(url)) {
                // 如果请求内容是json，记录请求记录，form表单或者上传文件不记录信息
                if (contentType.contains("json")) {
                    requestWrapper = new BodyReaderHttpServletRequestWrapper(request);
                    String bodyStr = requestWrapper.getBody();
                    AsyncLogService AsyncLogService = SpringContextHolder.getBean(AsyncLogService.class);
                    AsyncLogService.saveLogManage(fillLog(userId, method, bodyStr, url, agent));
                }
            }

            if (requestWrapper == null) {
                // 如果请求内容未读取过，直接返回
                chain.doFilter(servletRequest, response);
            } else {
                chain.doFilter(requestWrapper, response);
            }
        } catch (Exception e) {
            logger.error("sessionFilter,拦截器异常", e);
        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

    /**
     * @param request
     * @return
     * @Title: getReqToken
     * @Description: 获取授权token, 授权的authToken
     */
    public String getReqToken(HttpServletRequest request) {
        String accessToken = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie != null) {
                    String name = cookie.getName();
                    if ("accessToken".equals(name)) {
                        accessToken = cookie.getValue();
                        break;
                    }
                }
            }
        }
        if (StringUtil.isEmpty(accessToken)) {//如果cookie没有,从header里边获取
            accessToken = request.getHeader("authToken");
        }
        if (StringUtil.isEmpty(accessToken)) {//header没有从请求参数获取
            accessToken = request.getParameter("accessToken");
        }
        if (StringUtil.isEmpty(accessToken)) {
            accessToken = "";
        }
        return accessToken;
    }

    /**
     * 返回数据
     *
     * @param response
     */
    private void printJson(HttpServletResponse response, String responseObject) {
        // 将实体对象转换为JSON Object转换
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.write(responseObject);
            out.flush();
        } catch (IOException e) {
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    @Override
    public void destroy() {

    }

    /**
     * @param request
     * @return
     * @Title: getIpAddress
     * @Description: 获取ip
     */
    public String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
    }

    /**
     * @param userId
     * @param method
     * @param bodyStr
     * @param url
     * @param agent
     * @return
     * @Title: fillLog
     * @Description: 日志
     */
    private SysLogManage fillLog(Integer userId, String method, String bodyStr, String url, String agent) {
        SysLogManage log = new SysLogManage();
        log.setCip(Global.getIP());
        log.setCtime(DateUtil.getNow());
        log.setCuserId(userId);
        log.setException(null);
        log.setId(null);
        log.setMethod(method);
        log.setParams(bodyStr);
        log.setRequestUri(url);
        log.setTitle("");
        log.setUserAgent(agent);

        return log;
    }

}
