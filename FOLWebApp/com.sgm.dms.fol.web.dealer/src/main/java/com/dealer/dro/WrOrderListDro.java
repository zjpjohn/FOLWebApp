
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : web.dealer
 *
 * @File name : WrOrderListDro.java
 *
 * @Author : wangfl
 *
 * @Date : 2016年6月6日
 *
 ----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2016年6月6日    wangfl    1.0
 *
 *
 *
 *
 ----------------------------------------------------------------------------------
 */
	
package com.dealer.dro;

import java.math.BigDecimal;

/**
 * @author wangfl
 * 
 * @date 2016年6月6日
 */

public class WrOrderListDro {
	/** 序号 */
	private Integer index;
	/** 索赔月 */
	private String wrDate;
	/** 索赔批次  */
	private String wrBatch;
	/** 索赔类型   */
	private String wrType;
	/** 车辆属性    */
	private String carAttr;
	/** 工时代码    */
	private String workHoursCode;
	/** 索赔单号    */
	private String wrNo;
	/** 行号    */
	private Integer lineNo;
	/** 含税金额 */
	private BigDecimal gross;
	/** VIN */
	private String vin;
	/** 不含税金额  */
	private BigDecimal linetotal;
	/** 配件费用 */
	private BigDecimal partCost;
	/** 工时费用 */
	private BigDecimal labourCost;
	/** 其他费用 */
	private BigDecimal otherCost;
	/** 税金 */
	private BigDecimal tax;
	/** 追加 */
	private String additional;
	/** 抵扣 */
	private String deduction;
	/** 工单号 */
	private String roNo;
	/** 车系 */
	private String carSeries;
	/** 车型 */
	//private String CAR_MODEL;
	private String carModel;
	
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
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
	
}
