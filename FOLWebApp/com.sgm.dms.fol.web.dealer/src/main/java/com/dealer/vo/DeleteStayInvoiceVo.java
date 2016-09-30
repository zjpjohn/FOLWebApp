
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : web.dealer
 *
 * @File name : UpdateExpressNoReqVo.java
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

import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * @author wangfl
 * 删除并取消汇总待开发票
 * @date 2016年5月17日
 */
public class DeleteStayInvoiceVo {
	/**事务号，多个事务号用逗号","分开*/
	@NotBlank(message="事务号不能为空")
	private String tsId;

	public String getTsId() {
		return tsId;
	}

	public void setTsId(String tsId) {
		this.tsId = tsId;
	}
}
