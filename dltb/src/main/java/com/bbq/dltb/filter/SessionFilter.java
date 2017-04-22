package com.bbq.dltb.filter;


import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.alibaba.fastjson.JSON;
import com.bbq.dltb.common.util.IpUtil;
import com.bbq.dltb.dal.model.AccessLog;
import com.bbq.dltb.service.AccessLogService;

@Component
public class SessionFilter implements Filter {

	private static final Logger log = LoggerFactory.getLogger(SessionFilter.class);
	private static ExecutorService threadPool = Executors.newFixedThreadPool(2);
	@Autowired
	private AccessLogService accessLogService;

	public void init(FilterConfig filterConfig) throws ServletException {
		if(accessLogService == null){
			log.info("从spring中获取AccessLogService实例");
			ServletContext context = filterConfig.getServletContext();
			ApplicationContext ac = WebApplicationContextUtils
							.getWebApplicationContext(context);
			accessLogService = (AccessLogService) ac.getBean("accessLogService");
		}
	}
	
	private static final String[] openUrlArr = new String[] { "login", "getValidateCode", "getEmailValidateCode",
			"register", "ftl/","static/","file/","/swagger","/picLibList","/listHomePageProduct",
			"/listProduct"};

	private boolean isOpenUrl(String url) {
		for (String openUrl : openUrlArr) {
			if (url.indexOf(openUrl) >= 0) {
				return true;
			}
		}
		return false;
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		long t1 = System.currentTimeMillis();
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String url = httpRequest.getRequestURL().toString();
		if (!isOpenUrl(url)) {// 如果不是openUrl，则需要校验登录状态
			if (!url.matches("^.*/login.*$") && !url.matches("^.*/index.*$") && !url.matches("^.*/api-docs.*")) {
				if (httpRequest.getSession().getAttribute("user") == null) {
//					httpResponse.setStatus(401);
				}
			}
		}
		if (httpResponse.getStatus() == 200) {
			chain.doFilter(httpRequest, httpResponse);
		}
		long t2 = System.currentTimeMillis();
		insertAccessLog(httpRequest, t2-t1);
	}
	//记录访问日志
	private void insertAccessLog(HttpServletRequest httpRequest, Long timeMs){
		String uri = httpRequest.getRequestURI();
		if(uri.indexOf("/dltb/static/") >= 0){
			return;
		}
		final AccessLog accessLog = new AccessLog();
		accessLog.setAccessDate(new Date());
		accessLog.setCostTime(timeMs.intValue());
		accessLog.setIp(IpUtil.getIpAddr(httpRequest));
//		accessLog.setRemark(remark);
		accessLog.setResUri(uri);
		accessLog.setParam(JSON.toJSONString(httpRequest.getParameterMap()));
		threadPool.submit(new Thread(){
			public void run() {
				accessLogService.insertAccessLog(accessLog);
			}
		});
		
	}

	public void destroy() {
	}

}
