package com.sgm.dms.fol.vo;

import java.math.BigDecimal;

import com.sgm.dms.fol.common.service.domains.BasePo;

/**
 * 银行银票审核
 * @author lujinglei
 *
 */
public class BankBankTicketLimitVerifyVo extends BasePo{
	private String auditStatus;//审核状态
	private Integer dealerType;//渠道类型
	private String auditMsg;
	private BigDecimal amount;//金额
	private String moneyDisplay;//金额展示
	private String bankAbbr;//银行缩写
	private String bankChName;//中文名称
	private String bankEnName;//英文名称
	private Long bankId;
	private String encryptId;
	private Integer beginNo;
	private Integer endNo;
	private String referTableId;
	private String businessCode;
	private String remark;
	private String status;
	public String getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
	public Integer getDealerType() {
		return dealerType;
	}
	public void setDealerType(Integer dealerType) {
		this.dealerType = dealerType;
	}
	public String getAuditMsg() {
		return auditMsg;
	}
	public void setAuditMsg(String auditMsg) {
		this.auditMsg = auditMsg;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getBankAbbr() {
		return bankAbbr;
	}
	public void setBankAbbr(String bankAbbr) {
		this.bankAbbr = bankAbbr;
	}
	public String getBankChName() {
		return bankChName;
	}
	public void setBankChName(String bankChName) {
		this.bankChName = bankChName;
	}
	public String getBankEnName() {
		return bankEnName;
	}
	public void setBankEnName(String bankEnName) {
		this.bankEnName = bankEnName;
	}
	public Long getBankId() {
		return bankId;
	}
	public void setBankId(Long bankId) {
		this.bankId = bankId;
	}

    public String getEncryptId() {
        return encryptId;
    }
    
    public void setEncryptId(String encryptId) {
        this.encryptId = encryptId;
    }
    public Integer getBeginNo() {
		return beginNo;
	}
	public void setBeginNo(Integer beginNo) {
		this.beginNo = beginNo;
	}
	public Integer getEndNo() {
		return endNo;
	}
	public void setEndNo(Integer endNo) {
		this.endNo = endNo;
	}
	public String getReferTableId() {
		return referTableId;
	}
	public void setReferTableId(String referTableId) {
		this.referTableId = referTableId;
	}
	public String getBusinessCode() {
		return businessCode;
	}
	public void setBusinessCode(String businessCode) {
		this.businessCode = businessCode;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
    
    public String getMoneyDisplay() {
        return moneyDisplay;
    }
    
    public void setMoneyDisplay(String moneyDisplay) {
        this.moneyDisplay = moneyDisplay;
    }
	
}
