
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : web.dealer
 *
 * @File name : StayInvoiceDetailRespVo.java
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

/**
 * @author wangfl
 * 代开发票明细响应Vo
 * @date 2016年5月16日
 */

public class StayInvoiceDetailRespVo {
	/**事务号*/
	private String tsId;
	/**索赔类型*/
	private String wrType;
	/**配件费用*/
	private BigDecimal partCost;
	/**工时费用*/
	private BigDecimal labourCost;
	/**其他费用*/
	private BigDecimal otherCost;
	/**税金*/
	private BigDecimal tax;
	/**不含税金额*/
	private BigDecimal linetotal;
	/**含税金额*/
	private BigDecimal gross;
	
	public String getTsId() {
		return tsId;
	}
	public void setTsId(String tsId) {
		this.tsId = tsId;
	}
	public String getWrType() {
		return wrType;
	}
	public void setWrType(String wrType) {
		this.wrType = wrType;
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
	public BigDecimal getTax() {
		return tax;
	}
	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}
	public BigDecimal getLinetotal() {
		return linetotal;
	}
	public void setLinetotal(BigDecimal linetotal) {
		this.linetotal = linetotal;
	}
	public BigDecimal getGross() {
		return gross;
	}
	public void setGross(BigDecimal gross) {
		this.gross = gross;
	}

}
