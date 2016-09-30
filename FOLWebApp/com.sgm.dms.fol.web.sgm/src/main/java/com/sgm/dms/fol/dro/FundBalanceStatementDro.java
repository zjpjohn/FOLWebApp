package com.sgm.dms.fol.dro;

/**
 * 资金余额报表Dro
 * @author lujinglei
 *
 */
public class FundBalanceStatementDro {
	private Integer num;
	private String sapCode;
	private String dealerName;
	private String reserveAvailAmount;	//储备金可用余额
	private String bonusAvailAmount;	//奖金可用余额
	private String bonusAvailRatio;		//奖金使用比例
	private String maxOrderAmount;		//最大订购配件金额
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
	
	
}
