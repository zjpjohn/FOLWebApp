package com.sgm.dms.fol.vo;

import com.sgm.dms.fol.common.api.domain.PageVo;

/**
 * 资金余额报表Vo
 * @author lujinglei
 *
 */
public class FundBalanceStatementVo extends PageVo {

	private Integer num;
	private String reserveType;
	private String bonusType;
	private String sapCode;
	private String dealerName;
	private String dealerType;
	private String reserveAvailAmount;	//储备金可用余额
	private String bonusAvailAmount;	//奖金可用余额
	private String bonusAvailRatio;		//奖金使用比例
	private String maxOrderAmount;		//最大订购配件金额
	private String startSapCode;
	private String endSapCode;
	private String token;
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getReserveType() {
		return reserveType;
	}
	public void setReserveType(String reserveType) {
		this.reserveType = reserveType;
	}
	public String getBonusType() {
		return bonusType;
	}
	public void setBonusType(String bonusType) {
		this.bonusType = bonusType;
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
	public String getDealerType() {
		return dealerType;
	}
	public void setDealerType(String dealerType) {
		this.dealerType = dealerType;
	}
	public String getReserveAvailAmount() {
		return reserveAvailAmount;
	}
	public void setReserveAvailAmount(String reserveAvailAmount) {
		this.reserveAvailAmount = reserveAvailAmount;
	}
	public String getBonusAvailAmount() {
		return bonusAvailAmount;
	}
	public void setBonusAvailAmount(String bonusAvailAmount) {
		this.bonusAvailAmount = bonusAvailAmount;
	}
	public String getBonusAvailRatio() {
		return bonusAvailRatio;
	}
	public void setBonusAvailRatio(String bonusAvailRatio) {
		this.bonusAvailRatio = bonusAvailRatio;
	}
	public String getMaxOrderAmount() {
		return maxOrderAmount;
	}
	public void setMaxOrderAmount(String maxOrderAmount) {
		this.maxOrderAmount = maxOrderAmount;
	}
	public String getStartSapCode() {
		return startSapCode;
	}
	public void setStartSapCode(String startSapCode) {
		this.startSapCode = startSapCode;
	}
	public String getEndSapCode() {
		return endSapCode;
	}
	public void setEndSapCode(String endSapCode) {
		this.endSapCode = endSapCode;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
}
