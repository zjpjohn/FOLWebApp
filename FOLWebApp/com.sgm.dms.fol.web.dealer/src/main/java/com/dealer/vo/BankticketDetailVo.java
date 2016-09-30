package com.dealer.vo;

import java.io.Serializable;

import com.sgm.dms.fol.common.api.domain.PageVo;

/*****
 * 银票明细查询vo
*
* @author zhang bao
* TODO description
* @date 2016年1月20日
 */
/*
*
* @author Administrator
* TODO description
* @date 2016年1月20日
*/
	
public class BankticketDetailVo  extends PageVo implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String acceptanceNumber;//银票号码
    private String bankId;//银行简称
    private String issueDate;//出票日
    private String expirationDate;//到期日
    private Integer bankTicketStatus;//银票状态 -1,全部,0已到期,1未到期
	public String getAcceptanceNumber() {
		return acceptanceNumber;
	}
	public void setAcceptanceNumber(String acceptanceNumber) {
		this.acceptanceNumber = acceptanceNumber;
	}
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public String getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}
	public String getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	public Integer getBankTicketStatus() {
		return bankTicketStatus;
	}
	public void setBankTicketStatus(Integer bankTicketStatus) {
		this.bankTicketStatus = bankTicketStatus;
	};
    
  	
}
