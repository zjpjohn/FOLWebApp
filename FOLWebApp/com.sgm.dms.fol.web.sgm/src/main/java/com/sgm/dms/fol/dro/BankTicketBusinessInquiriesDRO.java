package com.sgm.dms.fol.dro;

/****
 * 银票超期利息查询vo
*
* @author Administrator
* TODO description
* @date 2016年1月14日
 */
public class BankTicketBusinessInquiriesDRO {
	private String sapCode;//
	private String dealerName;//客户名称
	private String 	interestAmount  ;//贴息金额
	private String 	unTaxAmount    ;//不函数金额
	private String 	addTaxAmount   ;//增值税金额
	private String invoiceNumber;//发票号码
	private String trackNumber;//EMS快递单号
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
