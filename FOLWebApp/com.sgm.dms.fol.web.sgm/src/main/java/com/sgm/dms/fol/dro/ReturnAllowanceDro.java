
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : web.sgm
 *
 * @File name : ReturnAllowanceDro.java
 *
 * @Author : wangfl
 *
 * @Date : 2016年9月19日
 *
 ----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2016年9月19日    wangfl    1.0
 *
 *
 *
 *
 ----------------------------------------------------------------------------------
 */
	
package com.sgm.dms.fol.dro;

import java.math.BigDecimal;

/**
 * 折让单导出Dro
 * @author wangfl
 * @date 2016年9月19日
 */

public class ReturnAllowanceDro {
	private String sapCode;//ASC代码
	private String dealerName; //ASC名称
	private String expressNo; //快递单号
	private String allowanceNo; //折让单号
	private BigDecimal lineTotal; //金额（不含税）
	private BigDecimal tax; //税额
	private String returnOrderNo;//退货证明单号
	private String billingReverseNo;//索赔发票号
	private String billingNo;//销售发票号
	private String approveDateDisplay;//受理日期
	private String statusDesc; //折让单状态
	private String sellerName;//所属公司
	
	public String getSapCode() {
		return sapCode;
	}
	public void setSapCode(String sapCode) {
		this.sapCode = sapCode;
	}
	public String getDealerName() {
		return dealerName;
	}
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}
	public String getExpressNo() {
		return expressNo;
	}
	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}
	public String getAllowanceNo() {
		return allowanceNo;
	}
	public void setAllowanceNo(String allowanceNo) {
		this.allowanceNo = allowanceNo;
	}
	public BigDecimal getLineTotal() {
		return lineTotal;
	}
	public void setLineTotal(BigDecimal lineTotal) {
		this.lineTotal = lineTotal;
	}
	public BigDecimal getTax() {
		return tax;
	}
	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}
	public String getReturnOrderNo() {
		return returnOrderNo;
	}
	public void setReturnOrderNo(String returnOrderNo) {
		this.returnOrderNo = returnOrderNo;
	}
	public String getBillingReverseNo() {
		return billingReverseNo;
	}
	public void setBillingReverseNo(String billingReverseNo) {
		this.billingReverseNo = billingReverseNo;
	}
	public String getBillingNo() {
		return billingNo;
	}
	public void setBillingNo(String billingNo) {
		this.billingNo = billingNo;
	}
	public String getApproveDateDisplay() {
		return approveDateDisplay;
	}
	public void setApproveDateDisplay(String approveDateDisplay) {
		this.approveDateDisplay = approveDateDisplay;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	
}
