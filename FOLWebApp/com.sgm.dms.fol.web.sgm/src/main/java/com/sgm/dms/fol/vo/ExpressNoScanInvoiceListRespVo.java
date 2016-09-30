package com.sgm.dms.fol.vo;

import java.math.BigDecimal;

public class ExpressNoScanInvoiceListRespVo {
	/**发票号*/
	private String invoiceNo;
	/**经销商代码*/
	private String sapCode;
	/**快递单号*/
    private String expressNo;
    /**不含税金额*/
    private BigDecimal linetotal;
    /**税额*/
    private BigDecimal tax;
    /**总金额*/
    private BigDecimal gross;
    /**一般索赔*/
    private BigDecimal generalWr;
    /**二手车索赔*/
    private BigDecimal usedCarWr;
    /**市场活动*/
    private BigDecimal marketWr;
    /**月份*/
    private String month;
    /**状态*/
    private Short status;
	/**状态名*/
	private String statusName;
	
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public String getSapCode() {
		return sapCode;
	}
	public void setSapCode(String sapCode) {
		this.sapCode = sapCode;
	}
	public String getExpressNo() {
		return expressNo;
	}
	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
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
	public BigDecimal getGeneralWr() {
		return generalWr;
	}
	public void setGeneralWr(BigDecimal generalWr) {
		this.generalWr = generalWr;
	}
	public BigDecimal getUsedCarWr() {
		return usedCarWr;
	}
	public void setUsedCarWr(BigDecimal usedCarWr) {
		this.usedCarWr = usedCarWr;
	}
	public BigDecimal getMarketWr() {
		return marketWr;
	}
	public void setMarketWr(BigDecimal marketWr) {
		this.marketWr = marketWr;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
}
