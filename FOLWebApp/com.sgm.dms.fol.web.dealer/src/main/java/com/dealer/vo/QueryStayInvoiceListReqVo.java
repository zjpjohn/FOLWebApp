
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : web.dealer
 *
 * @File name : QueryStayInvoiceListReqVo.java
 *
 * @Author : wangfl
 *
 * @Date : 2016年5月13日
 *
 ----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2016年5月13日    wangfl    1.0
 *
 *
 *
 *
 ----------------------------------------------------------------------------------
 */
	
package com.dealer.vo;

import com.sgm.dms.fol.common.api.domain.PageVo;

/**
 * @author wangfl
 * 代开发票编辑列表查询请求Vo
 * @date 2016年5月13日
 */

public class QueryStayInvoiceListReqVo extends PageVo {

	private String test;

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}
	
}
