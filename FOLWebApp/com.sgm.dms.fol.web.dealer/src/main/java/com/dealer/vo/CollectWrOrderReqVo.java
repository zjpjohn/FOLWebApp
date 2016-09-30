
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : web.dealer
 *
 * @File name : CollectWrOrderReqVo.java
 *
 * @Author : wangfl
 *
 * @Date : 2016年5月12日
 *
 ----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2016年5月12日    wangfl    1.0
 *
 *
 *
 *
 ----------------------------------------------------------------------------------
 */

package com.dealer.vo;

import java.math.BigDecimal;

import com.sgm.solution.framework.dataAuthority.annotations.SGMValidationFieldRefIds;
import com.sgm.solution.framework.dataAuthority.annotations.SGMValidationFieldSign;
import com.sgm.solution.framework.dataAuthority.annotations.SGMValidationResource;

/**
 * @author wangfl 索赔单汇总请求Vo
 * @date 2016年5月12日
 */
@SGMValidationResource
public class CollectWrOrderReqVo {

	private String wrNo;

	private String wrDate;

	private String wrBatch;

	private String wrType;

	private String carAttr;

	private String workHoursCode;

	private Integer lineNo;

	private String gwmClaimVersionNo;

	@SGMValidationFieldRefIds(seq=1)
	private BigDecimal gross;

	@SGMValidationFieldRefIds(seq=2)
	private BigDecimal linetotal;

	@SGMValidationFieldRefIds(seq=3)
	private BigDecimal partCost;

	@SGMValidationFieldRefIds(seq=4)
	private BigDecimal labourCost;

	@SGMValidationFieldRefIds(seq=5)
	private BigDecimal otherCost;

	@SGMValidationFieldRefIds(seq=6)
	private BigDecimal tax;

	private String vin;

	private Short additional;

	private Short deduction;

	private String roNo;

	private String carSeries;

	private String carModel;

	private String businessUser;

	private String warrantyCode;

	@SGMValidationFieldSign
    private String sign;

	public String getWrNo() {
		return wrNo;
	}

	public void setWrNo(String wrNo) {
		this.wrNo = wrNo;
	}

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

	public Integer getLineNo() {
		return lineNo;
	}

	public void setLineNo(Integer lineNo) {
		this.lineNo = lineNo;
	}

	public String getGwmClaimVersionNo() {
		return gwmClaimVersionNo;
	}

	public void setGwmClaimVersionNo(String gwmClaimVersionNo) {
		this.gwmClaimVersionNo = gwmClaimVersionNo;
	}

	public BigDecimal getGross() {
		return gross;
	}

	public void setGross(BigDecimal gross) {
		this.gross = gross;
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

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public Short getAdditional() {
		return additional;
	}

	public void setAdditional(String additional) {
		if ("是".equals(additional)) {
			this.additional = 1;
		} else {
			this.additional = 0;
		}
	}

	public Short getDeduction() {
		return deduction;
	}

	public void setDeduction(String deduction) {
		if ("是".equals(deduction.trim())) {
			this.deduction = 1;
		} else {
			this.deduction = 0;
		}
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

	public String getBusinessUser() {
		return businessUser;
	}

	public void setBusinessUser(String businessUser) {
		this.businessUser = businessUser;
	}

	public String getWarrantyCode() {
		return warrantyCode;
	}

	public void setWarrantyCode(String warrantyCode) {
		this.warrantyCode = warrantyCode;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

}
