
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : web.sgm
 *
 * @File name : WrInvoiceStatusQueryListReqVo.java
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

import com.sgm.dms.fol.common.api.domain.PageVo;

/**
 * @author wangfl
 * 索赔发票状态查询请求Vo
 * @date 2016年5月20日
 */
public class WrInvoiceStatusQueryListReqVo extends PageVo {
	/**经销商代码*/
	private String sapCode;
	/**发票号*/
	private String invoiceNo;
	/**快递单号*/
	private String expressNo;
	/**经销商名称*/
	private String dealerName;
	/**状态*/
	private Integer status;
	/**审核人*/
	private String approveMan;
	
	public String getSapCode() {
		return sapCode;
	}
	public void setSapCode(String sapCode) {
		this.sapCode = sapCode;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public String getExpressNo() {
		return expressNo;
	}
	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}
	public String getDealerName() {
		return dealerName;
	}
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getApproveMan() {
		return approveMan;
	}
	public void setApproveMan(String approveMan) {
		this.approveMan = approveMan;
	}
	
}
