
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : common.api
 *
 * @File name : SysRespContants.java
 *
 * @Author : wangfl
 *
 * @Date : 2016年9月23日
 *
 ----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2016年9月23日    wangfl    1.0
 *
 *
 *
 *
 ----------------------------------------------------------------------------------
 */
	
package com.sgm.dms.fol.common.api.constants;

/**
 * 系统响应状态常量
 * @author wangfl
 * @date 2016年9月23日
 */
public enum SysRespStatus {
	SUCCESS																	(2_00, "成功"),
	DATABASE_ERROR															(9_00, "数据库错误"),
	DATA_ERROR																(8_00, "数据错误"),
	APP_ERROR																(7_00, "应用错误"),
	HTTP_ERROR																(4_00, "http错误");
	
	

	private int statusCode;//状态码
	private String statusText;//状态说明
	
	private SysRespStatus(int statusCode, String statusText) {
		this.statusCode = statusCode;
		this.statusText = statusText;
	}
	
	public int satusCode() {
		return statusCode;
	}
	public String statusText() {
		return statusText;
	}
	public static SysRespStatus valueOf(int statusCode) {
		for (SysRespStatus status : values()) {
			if (status.statusCode == statusCode) {
				return status;
			}
		}
		throw new IllegalArgumentException("No matching statusCode for [" + statusCode + "]");
	}
}
