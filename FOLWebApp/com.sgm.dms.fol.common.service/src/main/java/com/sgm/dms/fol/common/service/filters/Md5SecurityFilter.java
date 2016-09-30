
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : common.service
 *
 * @File name : Md5SecurityFilter.java
 *
 * @Author : wangfl
 *
 * @Date : 2016年9月21日
 *
 ----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2016年9月21日    wangfl    1.0
 *
 *
 *
 *
 ----------------------------------------------------------------------------------
 */
	
package com.sgm.dms.fol.common.service.filters;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.HttpMethod;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;

import com.sgm.dms.fol.common.service.utils.BufferingRequestWrapper;
import com.sgm.dms.fol.common.service.utils.MD5Util;

/**
 * 防篡改（比对MD5校验和）
 * @author wangfl
 * @date 2016年9月21日
 */
@WebFilter(urlPatterns = "/*", initParams = @WebInitParam(name = "md5HeaderName", value = "Content-MD5", description = "MD5加密校验和的header头名字"))
public class Md5SecurityFilter implements Filter {
	private Logger logger = LogManager.getLogger(this.getClass());
	private String md5HeaderName = null;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		md5HeaderName = filterConfig.getInitParameter("md5HeaderName");
	}

	/**
	 * @author wangfl
	 * @date 2016年9月21日
	 * @param request
	 * @param response
	 * @param chain
	 * @throws IOException
	 * @throws ServletException
	 * (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		
		/*
		 * 只对HTTP请求方法是POST和PUT进行校验
		 */
		if(HttpMethod.POST.equals(httpServletRequest.getMethod()) || HttpMethod.PUT.equals(httpServletRequest.getMethod())){
			String requestMd5Security = httpServletRequest.getHeader(md5HeaderName);//请求header头中传过来的MD5校验和
			if(null != requestMd5Security && !"".equals(requestMd5Security = requestMd5Security.trim())){//暂只校验请求header中有Content-MD5的请求
				/*
				 * 服务器端根据body数据计算的MD5校验和
				 */
				BufferingRequestWrapper bufferingRequestWrapper = new BufferingRequestWrapper(httpServletRequest);
				BufferedReader reader = bufferingRequestWrapper.getReader();
				String lineStr = null;
				StringBuilder bodyStr = new StringBuilder();
				while((lineStr = reader.readLine()) != null){
					bodyStr.append(lineStr);
				}
				String serverMd5Security = MD5Util.string2MD5(bodyStr.toString());
				logger.info("serverMd5Security = " + serverMd5Security);
				
				/*
				 * 比对请求header头中传过来的MD5校验和 与 服务器端根据body数据计算的MD5校验和是否相等
				 */
				if (!requestMd5Security.equals(serverMd5Security))//md5校验不通过
					httpServletResponse.sendError(HttpStatus.BAD_REQUEST.value());
				
				/*
				 * 校验通过的filter通过
				 */
				chain.doFilter(bufferingRequestWrapper, response);return;
			}
		}
		
		/*
		 * 不需要校验的filter通过
		 */
		chain.doFilter(request, response);return;
	}

	@Override
	public void destroy() {}

}
