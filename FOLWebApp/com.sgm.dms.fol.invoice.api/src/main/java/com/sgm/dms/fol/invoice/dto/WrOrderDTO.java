package com.sgm.dms.fol.invoice.dto;

import java.math.BigDecimal;

import com.sgm.dms.fol.common.api.domain.BaseDTO;

public class WrOrderDTO extends BaseDTO{
    private Long wrId;                   // 索赔单ID

    private String wrNo;                 // 索赔单号

    private String sapCode;              // SAP代码

    private String wrDate;               // 索赔月

    private String wrBatch;              // 索赔批次

    private String wrType;                // 索赔类型

    private String carAttr;              // 车辆属性

    private String workHoursCode;        // 工时代码

    private Integer lineNo;              // 行号

    private String gwmClaimVersionNo;    // GWM版本号

    private BigDecimal gross;            // 含税金额

    private BigDecimal linetotal;        // 不含税金额

    private BigDecimal partCost;         // 配件费用

    private BigDecimal labourCost;       // 工时费用

    private BigDecimal otherCost;        // 其他费用

    private BigDecimal tax;              // 税金

    private String vin;                  // VIN

    private Short additional;            // 追加

    private Short deduction;             // 抵扣

    private String roNo;                 // 工单号

    private String carSeries;           // 车系

    private String carModel;            // 车型

    private String businessUser;           // 业务接待

    private Short vaild;                 // 有效性

    private String remark;               // 备注
    
    private String tsId;                //对应的汇总表里面的tsId
    
    private String invoiceNo;           //发票号
    
    private String expressNo;          //快递单号
    
    private String warrantyCode;       //索赔编码

	public Long getWrId() {
		return wrId;
	}

	public void setWrId(Long wrId) {
		this.wrId = wrId;
	}

	public String getWrNo() {
		return wrNo;
	}

	public void setWrNo(String wrNo) {
		this.wrNo = wrNo;
	}

	public String getSapCode() {
		return sapCode;
	}

	public void setSapCode(String sapCode) {
		this.sapCode = sapCode;
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

	public void setAdditional(Short additional) {
		this.additional = additional;
	}

	public Short getDeduction() {
		return deduction;
	}

	public void setDeduction(Short deduction) {
		this.deduction = deduction;
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

	public Short getVaild() {
		return vaild;
	}

	public void setVaild(Short vaild) {
		this.vaild = vaild;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTsId() {
		return tsId;
	}

	public void setTsId(String tsId) {
		this.tsId = tsId;
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

	public String getWarrantyCode() {
		return warrantyCode;
	}

	public void setWarrantyCode(String warrantyCode) {
		this.warrantyCode = warrantyCode;
	}
	
}