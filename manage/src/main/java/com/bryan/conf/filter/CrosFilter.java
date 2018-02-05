package com.bryan.conf.filter;


import com.bryan.common.utils.StringUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: CrosFilter  
 * Function: 跨域处理
 */
public class CrosFilter implements Filter {
	
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        // 先从origin获取,再从Host获取,仍然为空取*
        String origin = request.getHeader("Origin");
        if(StringUtil.isEmpty(origin)){
        	origin = request.getHeader("Host");
        }
        if(StringUtil.isEmpty(origin)){
        	origin = "*";
        }
        response.setHeader("Access-Control-Allow-Origin", origin);
        response.setHeader("Access-Control-Allow-Credentials","true");
        response.setHeader("Access-Control-Allow-Methods", "POST,OPTIONS,GET");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "accept,x-requested-with,Content-Type,accessToken");
        
        if(request.getMethod().equals("OPTIONS")){
        	//状态码204表示响应成功，但是没有返回响应信息
            response.setStatus(204);
            return;
        }
        chain.doFilter(req, res);
    }

    public void init(FilterConfig filterConfig) {
    	
    }

    public void destroy() {}
	

}
