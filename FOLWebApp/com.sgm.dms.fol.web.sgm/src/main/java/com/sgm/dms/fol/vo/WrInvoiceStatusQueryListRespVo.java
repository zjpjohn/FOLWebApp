
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : web.sgm
 *
 * @File name : WrInvoiceStatusQueryListRespVo.java
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

import java.math.BigDecimal;

/**
 * @author wangfl
 * 索赔发票状态查询响应Vo
 * @date 2016年5月20日
 */

public class WrInvoiceStatusQueryListRespVo {
	/**经销商代码*/
	private String sapCode;
	/**发票号*/
	private String invoiceNo;
	/**状态名*/
	private String statusName;
	/**快递单号*/
    private String expressNo;
    /**操作备注*/
    private String approveMsg;
    /**不含税金额*/
    private BigDecimal linetotal;
    /**税额*/
    private BigDecimal tax;
    /**总金额*/
    private BigDecimal gross;
    /**月份*/
    private String month;
    /**审核人*/
    private String approveMan;
    /**备注*/
    private String remark;
    /**状态*/
    private Short status;
    
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
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getExpressNo() {
		return expressNo;
	}
	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}
	public String getApproveMsg() {
		return approveMsg;
	}
	public void setApproveMsg(String approveMsg) {
		this.approveMsg = approveMsg;
	}
	public BigDecimal getLinetotal() {
		return linetotal;
	}
	public void setLinetotal(BigDecimal linetotal) {
		this.linetotal = linetotal;
	}
	public BigDecimal getTax() {
		return tax;
	}
	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}
	public BigDecimal getGross() {
		return gross;
	}
	public void setGross(BigDecimal gross) {
		this.gross = gross;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getApproveMan() {
		return approveMan;
	}
	public void setApproveMan(String approveMan) {
		this.approveMan = approveMan;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}

}
