package com.sgm.dms.fol.bonus.dto;

import java.math.BigDecimal;

import com.sgm.dms.fol.common.api.domain.BaseDTO;

public class BonusDTO extends BaseDTO{
	private String sapCode;
	private String dealerName;
	private BigDecimal totalAmount;
	private BigDecimal frozenAmount;
	private String lastChangeTime;
	private String dealerType;
	private String startSapCode;
	private String endSapCode;
	public String getDealerName() {
		return dealerName;
	}
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	public BigDecimal getFrozenAmount() {
		return frozenAmount;
	}
	public void setFrozenAmount(BigDecimal frozenAmount) {
		this.frozenAmount = frozenAmount;
	}
	public String getLastChangeTime() {
		return lastChangeTime;
	}
	public void setLastChangeTime(String lastChangeTime) {
		this.lastChangeTime = lastChangeTime;
	}
	public String getDealerType() {
		return dealerType;
	}
	public void setDealerType(String dealerType) {
		this.dealerType = dealerType;
	}
	public String getStartSapCode() {
		return startSapCode;
	}
	public void setStartSapCode(String startSapCode) {
		this.startSapCode = startSapCode;
	}
	public String getSapCode() {
		return sapCode;
	}
	public void setSapCode(String sapCode) {
		this.sapCode = sapCode;
	}
	public String getEndSapCode() {
		return endSapCode;
	}
	public void setEndSapCode(String endSapCode) {
		this.endSapCode = endSapCode;
	}
	
}
