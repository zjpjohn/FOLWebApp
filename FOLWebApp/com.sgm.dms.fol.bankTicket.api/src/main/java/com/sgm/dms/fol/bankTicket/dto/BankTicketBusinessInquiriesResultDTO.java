package com.sgm.dms.fol.bankTicket.dto;

/****
 * 调用SAP生成发票号码result
*
* @author Administrator
* TODO description
* @date 2016年1月14日
 */
public class BankTicketBusinessInquiriesResultDTO {
	private String sequence;//流水号
	private String belnr;//凭证号财务凭证编号
	private String msg;//错误消息,如果成功则为空 
	private String type;//返回状态结果S,成功,F失败
	private String xblnr;//银票引用号码
	
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public String getBelnr() {
		return belnr;
	}
	public void setBelnr(String belnr) {
		this.belnr = belnr;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getXblnr() {
		return xblnr;
	}
	public void setXblnr(String xblnr) {
		this.xblnr = xblnr;
	}
	
	
	
}
