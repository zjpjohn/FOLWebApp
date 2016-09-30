package com.sgm.dms.fol.bankTicket.domain;

import com.sgm.dms.fol.common.service.domains.BasePo;

/****
 * 银行银票信息实体类
*
* @author zhang bao
* TODO description
* @date 2015年12月31日
 */
public class BankBankTicketPo extends BasePo{
	private Long id             ;//主键
	private String dealerType     ;//渠道类型
	private String bankId         ;//银行id
	private String amount         ;//额度
	private String approvalStatus ;//审批状态
	private String status         ;//是否有效状态
	private String remark         ;//备注

	public String getDealerType() {
		return dealerType;
	}
	public void setDealerType(String dealerType) {
		this.dealerType = dealerType;
	}
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
	
	
	
}
