
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : web.dealer
 *
 * @File name : StayInvoiceDetailReqVo.java
 *
 * @Author : wangfl
 *
 * @Date : 2016年5月16日
 *
 ----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2016年5月16日    wangfl    1.0
 *
 *
 *
 *
 ----------------------------------------------------------------------------------
 */
	
package com.dealer.vo;


/**
 * @author wangfl
 * 代开发票明细请求Vo
 * @date 2016年5月16日
 */

public class StayInvoiceDetailReqVo {
	/**事务号*/
	private String tsId;

	public String getTsId() {
		return tsId;
	}

	public void setTsId(String tsId) {
		this.tsId = tsId;
	}
	
}
