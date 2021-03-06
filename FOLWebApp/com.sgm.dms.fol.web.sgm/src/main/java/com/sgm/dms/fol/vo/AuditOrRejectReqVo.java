
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : web.sgm
 *
 * @File name : AuditOrRejectReqVo.java
 *
 * @Author : wangfl
 *
 * @Date : 2016年5月19日
 *
 ----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2016年5月19日    wangfl    1.0
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
 * 发票统一或者退回请求Vo
 * @date 2016年5月19日
 */

public class AuditOrRejectReqVo {
	/**发票号*/
	@NotBlank(message="发票号不能为空")
	private String invoiceNo;
	@NotBlank(message="经销商代码不能为空")
	private String sapCode;

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getSapCode() {
		return sapCode;
	}

	public void setSapCode(String sapCode) {
		this.sapCode = sapCode;
	}
	
}
