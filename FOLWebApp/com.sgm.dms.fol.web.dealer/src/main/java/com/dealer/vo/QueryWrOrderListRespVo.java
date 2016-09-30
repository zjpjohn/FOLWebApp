
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : web.dealer
 *
 * @File name : QueryWrOrderListVo.java
 *
 * @Author : wangfl
 *
 * @Date : 2016年5月10日
 *
 ----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2016年5月10日    wangfl    1.0
 *
 *
 *
 *
 ----------------------------------------------------------------------------------
 */
	
package com.dealer.vo;

import java.math.BigDecimal;

import com.sgm.dms.fol.common.api.domain.PageVo;

/**
 * @author wangfl
 * 查询未汇总的索赔单响应Vo
 * @date 2016年5月10日
 */

public class QueryWrOrderListRespVo extends PageVo {
	/** 是否已生成发票 */
	//private Integer IS_COMPILE_INVOICE;
	//private Integer isCompileInvoice;
	/** 索赔月 */
	//private String WR_DATE;
	private String wrDate;
	/** 索赔批次  */
	//private String WR_BATCH;
	private String wrBatch;
	/** 索赔类型   */
	//private String WR_TYPE;
	private String wrType;
	/** 车辆属性    */
	//private String CAR_ATTR;
	private String carAttr;
	/** 工时代码    */
	//private String WORK_HOURS_CODE;
	private String workHoursCode;
	/** 索赔单号    */
	//private String WR_NUM;
	private String wrNo;
	/** 行号    */
	//private Integer LINE_NO;
	private Integer lineNo;
	/** GWM版本号    */
	//private String GWM_CLAIM_VERSION_NO;
	/*private String gwmClaimVersionNo;*/
	/** 含税金额 */
	//private BigDecimal GROSS;
	private BigDecimal gross;
	/** VIN */
	//private String VIN;
	private String vin;
	/** 不含税金额  */
	//private BigDecimal LINETOTAL;
	private BigDecimal linetotal;
	/** 配件费用 */
	//private BigDecimal PART_COST;
	private BigDecimal partCost;
	/** 工时费用 */
	//private BigDecimal LABOUR_COST;
	private BigDecimal labourCost;
	/** 其他费用 */
	//private BigDecimal OTHER_COST;
	private BigDecimal otherCost;
	/** 税金 */
	//private BigDecimal TAX;
	private BigDecimal tax;
	/** 追加 */
	//private Integer ADDITIONAL;
	private String additional;
	/** 抵扣 */
	//private Integer DEDUCTION;
	private String deduction;
	/** 工单号 */
	//private String RO_NO;
	private String roNo;
	/** 车系 */
	//private String CAR_SERIES;
	private String carSeries;
	/** 车型 */
	//private String CAR_MODEL;
	private String carModel;
	/** 业务接待 */
	//private String BUSINESS_USER;
	/*private String businessUser;*/
	/** 售后代码 */
	//private String SAP_CODE;
	private String sapCode;
	
	/*public Integer getIsCompileInvoice() {
		return isCompileInvoice;
	}
	public void setIsCompileInvoice(Integer isCompileInvoice) {
		this.isCompileInvoice = isCompileInvoice;
	}*/
	/**索赔代码*/
    private String warrantyCode;
	
	public String getWrDate() {
		return wrDate;
	}
	public void setWrDate(String wrDate) {
		this.wrDate = wrDate;
	}
	public String getWrBatch() {
		return wrBatch;
	}
	public void setWrBatch(String wrBatch) {
		this.wrBatch = wrBatch;
	}
	public String getWrType() {
		return wrType;
	}
	public void setWrType(String wrType) {
		this.wrType = wrType;
	}
	public String getCarAttr() {
		return carAttr;
	}
	public void setCarAttr(String carAttr) {
		this.carAttr = carAttr;
	}
	public String getWorkHoursCode() {
		return workHoursCode;
	}
	public void setWorkHoursCode(String workHoursCode) {
		this.workHoursCode = workHoursCode;
	}
	public String getWrNo() {
		return wrNo;
	}
	public void setWrNo(String wrNo) {
		this.wrNo = wrNo;
	}
	public Integer getLineNo() {
		return lineNo;
	}
	public void setLineNo(Integer lineNo) {
		this.lineNo = lineNo;
	}
	/*public String getGwmClaimVersionNo() {
		return gwmClaimVersionNo;
	}
	public void setGwmClaimVersionNo(String gwmClaimVersionNo) {
		this.gwmClaimVersionNo = gwmClaimVersionNo;
	}*/
	public BigDecimal getGross() {
		return gross;
	}
	public void setGross(BigDecimal gross) {
		this.gross = gross;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public BigDecimal getLinetotal() {
		return linetotal;
	}
	public void setLinetotal(BigDecimal linetotal) {
		this.linetotal = linetotal;
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
	public String getRoNo() {
		return roNo;
	}
	public void setRoNo(String roNo) {
		this.roNo = roNo;
	}
	public String getCarSeries() {
		return carSeries;
	}
	public void setCarSeries(String carSeries) {
		this.carSeries = carSeries;
	}
	public String getCarModel() {
		return carModel;
	}
	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}
	/*public String getBusinessUser() {
		return businessUser;
	}
	public void setBusinessUser(String businessUser) {
		this.businessUser = businessUser;
	}*/
	public String getSapCode() {
		return sapCode;
	}
	public void setSapCode(String sapCode) {
		this.sapCode = sapCode;
	}
	public String getAdditional() {
		return additional != null && "1".equals(additional) ? "是":"否";
	}
	public void setAdditional(String additional) {
		this.additional = additional;
	}
	public String getDeduction() {
		return deduction != null && "1".equals(deduction) ? "是":"否";
	}
	public void setDeduction(String deduction) {
		this.deduction = deduction;
	}
	public String getWarrantyCode() {
		return warrantyCode;
	}
	public void setWarrantyCode(String warrantyCode) {
		this.warrantyCode = warrantyCode;
	}
	
}
