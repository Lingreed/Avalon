package com.bryan.conf.interceptor;

import com.bryan.common.context.Global;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName: ResourceInterceptor  
 * Function:  资源后续处理
 */
public class ResourceInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		Global.CLIENT_TYPE_THREADLOCAL.remove();
		Global.IDFA_THREADLOCAL.remove();
		Global.IP_THREADLOCAL.remove();
		Global.TOKEN_THREADLOCAL.remove();
		Global.TRANSFER_LOCAL.remove();
		Global.UDID_THREADLOCAL.remove();
		Global.VERSION_THREADLOCAL.remove();
		Global.DICT_LOCAL.remove();
	}

}
