package com.dealer.dro;

import com.sgm.dms.fol.common.api.domain.BaseDTO;

/**
 * 
 * Title: Class BankTicketInterestFormDro.java
 * Description:票据贴息开票清单报表
 *
 *
 * @author wangfl
 * @version 0.0.1
 */

public class BankTicketInterestDro extends BaseDTO{
	private Long id;
	/** 序号 */
	private Integer num;
	/** 客户代码 */
	private String sapCode;
	/** 客户名称  */
	private String dealerName;
	/** 贴息(含税价)  */
	private String interestAmount;
	/** 金额（不含税价）  */
	private String unTaxAmount;
	/** 增值税  */
	private String addTaxAmount;
	/** 确认状态   */
	private String confirmStatusName;
	/** 发票号码   */
	private String invoiceNumber;
	/** EMS快递单号   */
	private String trackNumber;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getSapCode() {
		return sapCode;
	}
	public void setSapCode(String sapCode) {
		this.sapCode = sapCode;
	}
	public String getDealerName() {
		return dealerName;
	}
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}
	
	public String getInterestAmount() {
		return interestAmount;
	}
	public void setInterestAmount(String interestAmount) {
		this.interestAmount = interestAmount;
	}
	public String getUnTaxAmount() {
		return unTaxAmount;
	}
	public void setUnTaxAmount(String unTaxAmount) {
		this.unTaxAmount = unTaxAmount;
	}
	public String getAddTaxAmount() {
		return addTaxAmount;
	}
	public void setAddTaxAmount(String addTaxAmount) {
		this.addTaxAmount = addTaxAmount;
	}
	public String getConfirmStatusName() {
		return confirmStatusName;
	}
	public void setConfirmStatusName(String confirmStatusName) {
		this.confirmStatusName = confirmStatusName;
	}
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public String getTrackNumber() {
		return trackNumber;
	}
	public void setTrackNumber(String trackNumber) {
		this.trackNumber = trackNumber;
	}
	
}
