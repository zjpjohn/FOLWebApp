/*
* Copyright 2015 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : common.api
*
* @File name : ResponseVo.java
*
* @Author : ShenWeiwei
*
* @Date : 2015年10月27日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2015年10月27日    ShenWeiwei    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/
	
package com.sgm.dms.fol.common.api.domain;

import com.sgm.dms.fol.common.api.constants.StateConstant;


/**
 * 状态响应
 * @author wangfl
 * @date 2016年9月23日
 */
public class ResponseVo{
	private int state;//响应状态码
    private String message;//响应状态信息说明
    
    public ResponseVo(){//默认响应成功状态
    	this.state = StateConstant.SUCCESS;
    }
	public ResponseVo(int state, String message) {
		this.state = state;
		this.message = message;
	}
	
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
