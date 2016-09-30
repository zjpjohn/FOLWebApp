
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : common.service
 *
 * @File name : LogUtil.java
 *
 * @Author : wangfl
 *
 * @Date : 2016年9月20日
 *
 ----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2016年9月20日    wangfl    1.0
 *
 *
 *
 *
 ----------------------------------------------------------------------------------
 */

package com.sgm.dms.fol.common.service.utils;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * 日志工具类
 * 
 * @author wangfl
 * @date 2016年9月20日
 */

public class LogUtil {
	private static Logger logger = LogManager.getLogger(LogUtil.class);

	public static void getRequestLog(HttpServletRequest request) {
		/*
		 * 打印请求日志
		 */
		StringBuilder requestMsg = new StringBuilder("\n---------------------------------------------------------\n");
		// 起始行
		requestMsg.append(request.getProtocol()).append(" ");// 协议
		requestMsg.append(request.getRequestURI());// URL
		Enumeration<?> parameterNames = request.getParameterNames();// Parameter参数
		StringBuilder parameterStr = new StringBuilder();
		while (parameterNames.hasMoreElements()) {
			Object nextElement = parameterNames.nextElement();
			if (parameterStr.length() == 0)
				parameterStr.append("?");
			else
				parameterStr.append("&");
			parameterStr.append(nextElement).append("=").append(request.getParameter(nextElement.toString()));
		}
		
		requestMsg.append(parameterStr).append(" ");

		requestMsg.append(request.getMethod());// METHOD
		requestMsg.append("\n");
		// Header头
		Enumeration<?> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String headerName = (String) headerNames.nextElement();
			requestMsg.append(headerName).append(":").append(request.getHeader(headerName)).append("\n");
		}
		requestMsg.append("\n");
		
		logger.info(requestMsg);
	}
}
