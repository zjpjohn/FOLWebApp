
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : web.sgm
 *
 * @File name : WrInvoiceStatusRehandleVo.java
 *
 * @Author : wangfl
 *
 * @Date : 2016年5月20日
 *
 ----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2016年5月20日    wangfl    1.0
 *
 *
 *
 *
 ----------------------------------------------------------------------------------
 */
	
package com.sgm.dms.fol.vo;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author wangfl
 * 重处理请求Vo
 * @date 2016年5月20日
 */

public class WrInvoiceStatusRehandleVo {
	/**发票号，多张发票用逗号","分开*/
	@NotBlank(message="发票号不能为空")
	private String invoiceNo;

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
}
