package com.sgm.dms.fol.bankTicket.dto;

import com.sgm.dms.fol.common.api.domain.BaseDTO;

public class TicketInterestRateDTO extends BaseDTO{
    private Long id;
    private String annualInterestRate;//年利率
    private String discountRate;//税率
    private String addedTaxRate;//增值税率
    private String auditMsg;
    private Integer auditStatus;
    private String auditDate;
    private Integer status;
    private String remark;
    private String auditStatusName;
    private Integer beginNo;
    private Integer endNo;
    private String currentInterestRate;//当前利率
    private String unAuditRateCur;//当前待审核年利率
    private String historyInterestRate;//历史利率
    private String sign;
    
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAnnualInterestRate() {
		return annualInterestRate;
	}
	public void setAnnualInterestRate(String annualInterestRate) {
		this.annualInterestRate = annualInterestRate;
	}
	public String getDiscountRate() {
		return discountRate;
	}
	public void setDiscountRate(String discountRate) {
		this.discountRate = discountRate;
	}
	public String getAddedTaxRate() {
		return addedTaxRate;
	}
	public void setAddedTaxRate(String addedTaxRate) {
		this.addedTaxRate = addedTaxRate;
	}
	public String getAuditMsg() {
		return auditMsg;
	}
	public void setAuditMsg(String auditMsg) {
		this.auditMsg = auditMsg;
	}
	public Integer getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}
	public String getAuditDate() {
		return auditDate;
	}
	public void setAuditDate(String auditDate) {
		this.auditDate = auditDate;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getAuditStatusName() {
		return auditStatusName;
	}
	public void setAuditStatusName(String auditStatusName) {
		this.auditStatusName = auditStatusName;
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
	public String getCurrentInterestRate() {
		return currentInterestRate;
	}
	public void setCurrentInterestRate(String currentInterestRate) {
		this.currentInterestRate = currentInterestRate;
	}
	public String getUnAuditRateCur() {
		return unAuditRateCur;
	}
	public void setUnAuditRateCur(String unAuditRateCur) {
		this.unAuditRateCur = unAuditRateCur;
	}
	public String getHistoryInterestRate() {
		return historyInterestRate;
	}
	public void setHistoryInterestRate(String historyInterestRate) {
		this.historyInterestRate = historyInterestRate;
	}

}
