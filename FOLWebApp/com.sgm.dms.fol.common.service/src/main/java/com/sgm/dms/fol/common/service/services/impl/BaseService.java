
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : common.service
 *
 * @File name : BaseService.java
 *
 * @Author : wangfl
 *
 * @Date : 2016年8月9日
 *
 ----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2016年8月9日    wangfl    1.0
 *
 *
 *
 *
 ----------------------------------------------------------------------------------
 */
	
package com.sgm.dms.fol.common.service.services.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.sgm.dms.fol.common.service.utils.CookieUtil;
import com.sgm.dms.fol.common.service.utils.RSAUtil;

/**
 * service基类
 * @author wangfl
 * @date 2016年8月9日
 */

public class BaseService {
	private Logger logger = LogManager.getLogger(this.getClass());
	
	@Autowired
	private HttpServletRequest request;
	
	protected Long getUserId(){
		try {
			return CookieUtil.getUserId(request);
		} catch (Exception e) {
			logger.error("获取当前用户id失败", e);
			return -1L;//获取失败时，默认返回-1；
		}
	}
	
	protected String getSapCode(){
		try {
			return RSAUtil.decryptByPrivateKey(CookieUtil.getSapCode(request));
		} catch (Exception e) {
			logger.error("获取当前用户sapCode失败", e);
			return "";//获取失败时，默认返回""；
		}
	}

}
