package com.dealer.vo;

import java.io.Serializable;

import com.sgm.dms.fol.common.api.domain.PageVo;

/****
 * 银票明细页面展示vo
*
* @author zhang bao
* TODO description
* @date 2016年1月20日
 */
public class BankTicketDetailFormVo  extends PageVo implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
    private String bankName;
    private String acceptanceNumber;//银票号码
    private String expirationDate;//到期日
    private String documentNumber;//凭证号
    private String bankId;//银行缩写
    private String issueDate;//出票日
    private Integer bankTicketStatus;//银票状态 -1,全部,0已到期,1未到期
    private String  bankTicketStatusName;//状态名称 
    private String amount;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getAcceptanceNumber() {
		return acceptanceNumber;
	}
	public void setAcceptanceNumber(String acceptanceNumber) {
		this.acceptanceNumber = acceptanceNumber;
	}
	public String getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	public String getDocumentNumber() {
		return documentNumber;
	}
	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
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
	public Integer getBankTicketStatus() {
		return bankTicketStatus;
	}
	public void setBankTicketStatus(Integer bankTicketStatus) {
		this.bankTicketStatus = bankTicketStatus;
	}
	public String getBankTicketStatusName() {
		return bankTicketStatusName;
	}
	public void setBankTicketStatusName(String bankTicketStatusName) {
		this.bankTicketStatusName = bankTicketStatusName;
	}
    
    public String getAmount() {
        return amount;
    }
    
    public void setAmount(String amount) {
        this.amount = amount;
    }
    

}
