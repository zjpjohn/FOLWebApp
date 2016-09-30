
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : invoice.service
 *
 * @File name : ClaimOrderListRequestDto.java
 *
 * @Author : wangfl
 *
 * @Date : 2016年5月31日
 *
 ----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2016年5月31日    wangfl    1.0
 *
 *
 *
 *
 ----------------------------------------------------------------------------------
 */
	
package com.sgm.dms.fol.invoice.restclient.wol_claimOrder.dto;

import java.util.List;

/**
 * @author wangfl
 * 索赔单列表请求dto
 * @date 2016年5月31日
 */

public class ClaimOrderListReqDto {
	/**开始索赔月*/
	private String monthBegin;
	/**开始批次*/
	private String batchNoBegin;
	/**结束索赔月*/
	private String monthEnd;
	/**结束批次*/
	private String batchNoEnd;
	/**索赔类型*/
	private List<String> wrType;
	/**售后代码*/
	private String sapCode;
	/**开始序号*/
	private Integer beginNo;
	/**结束序号*/
	private Integer endNo;
	
	public String getMonthBegin() {
		return monthBegin;
	}
	public void setMonthBegin(String monthBegin) {
		this.monthBegin = monthBegin;
	}
	public String getBatchNoBegin() {
		return batchNoBegin;
	}
	public void setBatchNoBegin(String batchNoBegin) {
		this.batchNoBegin = batchNoBegin;
	}
	public String getMonthEnd() {
		return monthEnd;
	}
	public void setMonthEnd(String monthEnd) {
		this.monthEnd = monthEnd;
	}
	public String getBatchNoEnd() {
		return batchNoEnd;
	}
	public void setBatchNoEnd(String batchNoEnd) {
		this.batchNoEnd = batchNoEnd;
	}
	public List<String> getWrType() {
		return wrType;
	}
	public void setWrType(List<String> wrType) {
		this.wrType = wrType;
	}
	public String getSapCode() {
		return sapCode;
	}
	public void setSapCode(String sapCode) {
		this.sapCode = sapCode;
	}
	public Integer getBeginNo() {
		return beginNo;
	}
	public void setBeginNo(Integer beginNo) {
		this.beginNo = beginNo;
	}
	public Integer getEndNo() {
		return endNo;
	}
	public void setEndNo(Integer endNo) {
		this.endNo = endNo;
	}
}
