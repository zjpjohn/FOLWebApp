
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : web.dealer
 *
 * @File name : QueryStayInvoiceListRespVo.java
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

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author wangfl
 *  代开发票编辑列表查询响应Vo
 * @date 2016年5月16日
 */

public class QueryStayInvoiceListRespVo {
	/**事务号*/
	private String tsId;
	/**快递单号*/
	private String expressNo;
	/**发票号*/
	private String invoiceNo;
	/**月份*/
	private String wrDate;
	/**配件金额*/
	private BigDecimal partCost;
	/**工时金额*/
	private BigDecimal labourCost;
	/**其他费用*/
	private BigDecimal otherCost;
	/**不含税金额*/
	private BigDecimal linetotal;
	/**税金*/
	private BigDecimal tax;
	/**发票税额 */
	private BigDecimal taxAmount;
	/**含税金额*/
	private BigDecimal gross;
	/**申请人*/
	private String userName;           
	/**申请日期*/
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date createDate;
	/**旧发票号*/
	private String oldInvoiceNo;
	
	private String invoiceTitle = "上汽通用汽车销售有限公司";
	
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
	public String getWrDate() {
		return wrDate;
	}
	public void setWrDate(String wrDate) {
		this.wrDate = wrDate;
	}
	public BigDecimal getPartCost() {
		return partCost;
	}
	public void setPartCost(BigDecimal partCost) {
		this.partCost = partCost;
	}
	public BigDecimal getLabourCost() {
		return labourCost;
	}
	public void setLabourCost(BigDecimal labourCost) {
		this.labourCost = labourCost;
	}
	public BigDecimal getOtherCost() {
		return otherCost;
	}
	public void setOtherCost(BigDecimal otherCost) {
		this.otherCost = otherCost;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getOldInvoiceNo() {
		return oldInvoiceNo;
	}
	public void setOldInvoiceNo(String oldInvoiceNo) {
		this.oldInvoiceNo = oldInvoiceNo;
	}
	public String getInvoiceTitle() {
		return invoiceTitle;
	}
	public void setInvoiceTitle(String invoiceTitle) {
		this.invoiceTitle = invoiceTitle;
	}
	public BigDecimal getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}
	public void setTaxAmount(String taxAmount) {
		this.taxAmount = new BigDecimal(taxAmount);
	}
	
}
