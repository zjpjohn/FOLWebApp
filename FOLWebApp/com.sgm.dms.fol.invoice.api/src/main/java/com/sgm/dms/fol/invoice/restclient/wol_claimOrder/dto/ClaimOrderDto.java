package com.sgm.dms.fol.invoice.restclient.wol_claimOrder.dto;

/**
 * 
 * @author wangfl
 * 索赔单Dto
 * @date 2016年6月2日
 */
public class ClaimOrderDto {
	/**索赔月*/
    private String  warrantyMonth;
    /**索赔批次*/
    private String  batchNo;
    /**索赔类型*/
    private String  claimType;
    /**车辆属性*/
    private String  vehicleProperty;
    /**工时代码*/
    private String  labourOperationCode;
    /**索赔单号*/
    private String  claimNo;
    /**行号*/
    private int     lineNo;
    /**含税金额*/
    private double  approvedAmountTatal;
    /**VIN*/
    private String  vin;
    /**不含税金额*/
    private double  approvedAmount;
    /**配件费用*/
    private double  partsAmountTotal;
    /**工时费用*/
    private double  labourAmountTotal;
    /**其他费用*/
    private double  otherAmountTotal;
    /**税金*/
    private double  approvedAmountTax;
    /**追加*/
    private int     appendFlag;
    /**抵扣*/
    private int     debitFlag;
    /**工单号*/
    private String  roNo;
    /**车系*/
    private String  series;
    /**车型*/
    private String  model;
    /**售后代码*/
    private String  sapCode;
    /**索赔代码*/
    private String warrantyCode;
    
    public String getWarrantyMonth() {
        return warrantyMonth;
    }
    
    public void setWarrantyMonth(String warrantyMonth) {
        this.warrantyMonth = warrantyMonth;
    }
    
    public String getBatchNo() {
    	String tempBatchNo = batchNo != null && batchNo.length() >=8 ? batchNo.substring(6, 8) : "";
    	
        if("01".equals(tempBatchNo)){
        	return "第一批次";
        }
        if("02".equals(tempBatchNo)){
        	return "第二批次";
        }
        
        return "";
    }
    
    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }
    
    public String getClaimType() {
        return claimType;
    }
    
    public void setClaimType(String claimType) {
        this.claimType = claimType;
    }
    
    public String getVehicleProperty() {
        return vehicleProperty;
    }
    
    public void setVehicleProperty(String vehicleProperty) {
        this.vehicleProperty = vehicleProperty;
    }
    
    public String getLabourOperationCode() {
        return labourOperationCode;
    }
    
    public void setLabourOperationCode(String labourOperationCode) {
        this.labourOperationCode = labourOperationCode;
    }
    
    public String getClaimNo() {
        return claimNo;
    }
    
    public void setClaimNo(String claimNo) {
        this.claimNo = claimNo;
    }
    
    public int getLineNo() {
        return lineNo;
    }
    
    public void setLineNo(int lineNo) {
        this.lineNo = lineNo;
    }
    
    public double getApprovedAmountTatal() {
        return approvedAmountTatal;
    }
    
    public void setApprovedAmountTatal(double approvedAmountTatal) {
        this.approvedAmountTatal = approvedAmountTatal;
    }
    
    public String getVin() {
        return vin;
    }
    
    public void setVin(String vin) {
        this.vin = vin;
    }
    
    public double getApprovedAmount() {
        return approvedAmount;
    }
    
    public void setApprovedAmount(double approvedAmount) {
        this.approvedAmount = approvedAmount;
    }
    
    public double getPartsAmountTotal() {
        return partsAmountTotal;
    }
    
    public void setPartsAmountTotal(double partsAmountTotal) {
        this.partsAmountTotal = partsAmountTotal;
    }
    
    public double getLabourAmountTotal() {
        return labourAmountTotal;
    }
    
    public void setLabourAmountTotal(double labourAmountTotal) {
        this.labourAmountTotal = labourAmountTotal;
    }
    
    public double getOtherAmountTotal() {
        return otherAmountTotal;
    }
    
    public void setOtherAmountTotal(double otherAmountTotal) {
        this.otherAmountTotal = otherAmountTotal;
    }
    
    public double getApprovedAmountTax() {
        return approvedAmountTax;
    }
    
    public void setApprovedAmountTax(double approvedAmountTax) {
        this.approvedAmountTax = approvedAmountTax;
    }
    
    public int getAppendFlag() {
        return appendFlag;
    }
    
    public void setAppendFlag(int appendFlag) {
        this.appendFlag = appendFlag;
    }
    
    public int getDebitFlag() {
        return debitFlag;
    }
    
    public void setDebitFlag(int debitFlag) {
        this.debitFlag = debitFlag;
    }
    
    public String getRoNo() {
        return roNo;
    }
    
    public void setRoNo(String roNo) {
        this.roNo = roNo;
    }
    
    public String getSeries() {
        return series;
    }
    
    public void setSeries(String series) {
        this.series = series;
    }
    
    public String getModel() {
        return model;
    }
    
    public void setModel(String model) {
        this.model = model;
    }
    
	public String getSapCode() {
		return sapCode;
	}

	public void setSapCode(String sapCode) {
		this.sapCode = sapCode;
	}

	public String getWarrantyCode() {
		return warrantyCode;
	}

	public void setWarrantyCode(String warrantyCode) {
		this.warrantyCode = warrantyCode;
	}
}
