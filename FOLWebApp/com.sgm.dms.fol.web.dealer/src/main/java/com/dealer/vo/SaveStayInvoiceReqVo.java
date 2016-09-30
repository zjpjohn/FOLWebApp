
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : web.dealer
 *
 * @File name : SaveStayInvoiceReqVo.java
 *
 * @Author : wangfl
 *
 * @Date : 2016年5月17日
 *
 ----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2016年5月17日    wangfl    1.0
 *
 *
 *
 *
 ----------------------------------------------------------------------------------
 */
	
package com.dealer.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.sgm.solution.framework.dataAuthority.annotations.SGMValidationFieldRefIds;
import com.sgm.solution.framework.dataAuthority.annotations.SGMValidationFieldSign;
import com.sgm.solution.framework.dataAuthority.annotations.SGMValidationResource;

/**
 * @author wangfl
 * 保存待开发票请求Vo
 * @date 2016年5月17日
 */
@SGMValidationResource
public class SaveStayInvoiceReqVo {
	@NotBlank(message="tsId不能为空")
	private String tsId;
	@SGMValidationFieldRefIds(seq=1)
	private String expressNo;
	@SGMValidationFieldRefIds(seq=2)
	private String invoiceNo;
	@SGMValidationFieldRefIds(seq=3)
	private String taxAmount;
	@SGMValidationFieldSign
	private String sign;
	
	public String getTsId() {
		return tsId;
	}
	public void setTsId(String tsId) {
		this.tsId = tsId;
	}
	public String getExpressNo() {
		return expressNo;
	}
	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public String getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(String taxAmount) {
		this.taxAmount = (taxAmount == null ? "" : taxAmount);
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
}
