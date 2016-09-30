
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : common.service
 *
 * @File name : FolCommonsRequestLoggingFilter.java
 *
 * @Author : wangfl
 *
 * @Date : 2016年8月29日
 *
 ----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2016年8月29日    wangfl    1.0
 *
 *
 *
 *
 ----------------------------------------------------------------------------------
 */
	
package com.sgm.dms.fol.common.service.filters;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.sgm.dms.fol.common.service.utils.BufferingRequestWrapper;

/**
 * @author wangfl
 * @date 2016年8月29日
 */
@WebFilter(urlPatterns = "/*", initParams = @WebInitParam(name = "excludeFile", value = "png,jpg,gif,ico", description = "不拦截的文件类型后缀"))
public class FolCommonsRequestLoggingFilter implements Filter {
	private Logger logger = LogManager.getLogger(this.getClass());
	
	// 文件白名单
	private List<String> excludeFileList = new ArrayList<String>();
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// 初始化文件白名单
		String excludeFile = filterConfig.getInitParameter("excludeFile");
		String[] files = excludeFile.split(",");
		for (String file : files) {
			if (!"".equals(file = file.trim()))
				excludeFileList.add(file);
		}
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		
		/*
		 * 把servletRequest包装成可多次读取body的BufferingRequestWrapper
		 */
		BufferingRequestWrapper bufferingRequestWrapper = new BufferingRequestWrapper((HttpServletRequest) servletRequest);
		
		// 排除不需要检查的文件类型
		String upperCaseURI = bufferingRequestWrapper.getRequestURI().toUpperCase();
		for (String excludeFile : excludeFileList) {
			if (upperCaseURI.endsWith(excludeFile.toUpperCase())) {
				filterChain.doFilter(servletRequest, servletResponse);
				return;
			}
		}
		
		/*
		 * 打印请求日志
		 */
		StringBuilder requestMsg = new StringBuilder("\n---------------------------------------------------------\n");
		//起始行
		requestMsg.append(bufferingRequestWrapper.getProtocol()).append(" ");//协议
		requestMsg.append(bufferingRequestWrapper.getRequestURI());//URL
		Enumeration<?> parameterNames = bufferingRequestWrapper.getParameterNames();//Parameter参数
		StringBuilder parameterStr = new StringBuilder();
		while(parameterNames.hasMoreElements()){
			Object nextElement = parameterNames.nextElement();
			if (parameterStr.length() == 0)
				parameterStr.append("?");
			else
				parameterStr.append("&");
			parameterStr.append(nextElement).append("=").append(bufferingRequestWrapper.getParameter(nextElement.toString()));
		}
		requestMsg.append(parameterStr).append(" ");
		
		requestMsg.append(bufferingRequestWrapper.getMethod());//METHOD
		requestMsg.append("\n");
		//Header头
		Enumeration<?> headerNames = bufferingRequestWrapper.getHeaderNames();
		while(headerNames.hasMoreElements()){
			String headerName = (String)headerNames.nextElement();
			requestMsg.append(headerName).append(":").append(bufferingRequestWrapper.getHeader(headerName)).append("\n");
		}
		requestMsg.append("\n");
		//body
		BufferedReader reader = bufferingRequestWrapper.getReader();
		String lineStr = null;
		StringBuilder bodyStr = new StringBuilder();
		while((lineStr = reader.readLine()) != null){
			bodyStr.append(lineStr);
		}
		requestMsg.append(bodyStr);
		requestMsg.append("\n---------------------------------------------------------");
		logger.info(requestMsg);
		
		filterChain.doFilter(bufferingRequestWrapper, servletResponse);
	}

	@Override
	public void destroy() {
	}

}
