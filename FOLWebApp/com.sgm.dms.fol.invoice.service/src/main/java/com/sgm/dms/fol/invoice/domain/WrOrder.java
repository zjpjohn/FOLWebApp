package com.sgm.dms.fol.invoice.domain;

import java.math.BigDecimal;
import java.util.Date;

public class WrOrder {
    private Long wrId;

    private String wrNo;

    private String sapCode;

    private String wrDate;

    private String wrBatch;

    private String wrType;

    private String carAttr;

    private String workHoursCode;

    private Integer lineNo;

    private String gwmClaimVersionNo;

    private BigDecimal gross;

    private BigDecimal linetotal;

    private BigDecimal partCost;

    private BigDecimal labourCost;

    private BigDecimal otherCost;

    private BigDecimal tax;

    private String vin;

    private Short additional;

    private Short deduction;

    private String roNo;

    private String carSeries;

    private String carModel;

    private String businessUser;

    private Short vaild;

    private String remark;

    private Date createDate;

    private Long createBy;

    private Date updateDate;

    private Long updateBy;
    
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Long getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}

	public String getWarrantyCode() {
		return warrantyCode;
	}

	public void setWarrantyCode(String warrantyCode) {
		this.warrantyCode = warrantyCode;
	}

}