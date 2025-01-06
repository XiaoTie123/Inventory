package com.applewear.crm.admin.controller.interceptor;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.applewear.crm.util.common.CommonConstants;
import com.applewear.crm.util.common.CommonUtil;

public class AdminInterceptor extends HandlerInterceptorAdapter {

	private List<String> ignoreUrls = null;
	
	{
		ignoreUrls = Arrays.asList("login.html", "logout.html");
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String uri = request.getRequestURI();
		if (!isIgnoreUrl(uri)) {
			if (request.getSession().getAttribute(CommonConstants.LOGIN_SESSION_KEY) == null) {
				response.sendRedirect("login.html");
				return false;
			}
		}
		return true;
	}

	private boolean isIgnoreUrl(String url) {
		for (String ignoreUrl : ignoreUrls) {
			if (CommonUtil.validString(ignoreUrl) && url.endsWith(ignoreUrl)) {
				return true;
			}
		}
		return false;
	}

}
