package com.sxquan.manage.common.shiro;


import com.sxquan.core.util.ShopUtil;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author sxquan
 */
public class LoginFormFilter extends FormAuthenticationFilter {
	
	private static final Logger log = LoggerFactory.getLogger(LoginFormFilter.class);
 
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
	    HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		HttpServletResponse httpServletResponse = (HttpServletResponse)response;
		if (isLoginRequest(request, response)) {
            if (isLoginSubmission(request, response)) {
                return executeLogin(request, response);
            } else {
                if (log.isTraceEnabled()) {
                    log.trace("Login page view.");
                }
                return true;
            }
        } else {
            if (log.isTraceEnabled()) {
                log.trace("Attempting to access a path which requires authentication.  Forwarding to the " +
                        "Authentication url [" + getLoginUrl() + "]");
            }
            //如果是Ajax请求，不跳转登录
            if (ShopUtil.isAjaxRequest(httpServletRequest)){
    			httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
    		} else {
    			saveRequestAndRedirectToLogin(request, response);
    		}
            return false;
        }
	}
}