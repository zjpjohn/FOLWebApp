package com.sgm.dms.fol.dro;

public class BonusReconcileDRO {
	// 序号
	private Integer num;
	// 客户代码
	private String sapCode;
	// 客户名称
	private String dealerName;
	// 本月期初余额
	private String bonusLastAmount;
	// 财务本月颁发
	private String bonusAdd;
	// 退货奖金返回
	private String bonusSub;
	// 本月减少(billing使用)
	private String billBonus;
	// 本月期末金额FOL
	private String bonusAmount;
	// sap奖金信息本月初期
	private String sapBonusLastAmount;
	// sap奖金信息本月期末
	private String sapBonusAmount;
	// 余额差异
	private String differenceAmount;
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
	public String getBonusLastAmount() {
		return bonusLastAmount;
	}
	public void setBonusLastAmount(String bonusLastAmount) {
		this.bonusLastAmount = bonusLastAmount;
	}
	public String getBonusAdd() {
		return bonusAdd;
	}
	public void setBonusAdd(String bonusAdd) {
		this.bonusAdd = bonusAdd;
	}
	public String getBonusSub() {
		return bonusSub;
	}
	public void setBonusSub(String bonusSub) {
		this.bonusSub = bonusSub;
	}
	public String getBillBonus() {
		return billBonus;
	}
	public void setBillBonus(String billBonus) {
		this.billBonus = billBonus;
	}
	public String getBonusAmount() {
		return bonusAmount;
	}
	public void setBonusAmount(String bonusAmount) {
		this.bonusAmount = bonusAmount;
	}
	public String getSapBonusLastAmount() {
		return sapBonusLastAmount;
	}
	public void setSapBonusLastAmount(String sapBonusLastAmount) {
		this.sapBonusLastAmount = sapBonusLastAmount;
	}
	public String getSapBonusAmount() {
		return sapBonusAmount;
	}
	public void setSapBonusAmount(String sapBonusAmount) {
		this.sapBonusAmount = sapBonusAmount;
	}
	public String getDifferenceAmount() {
		return differenceAmount;
	}
	public void setDifferenceAmount(String differenceAmount) {
		this.differenceAmount = differenceAmount;
	}

}
