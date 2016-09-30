package com.sgm.dms.fol.vo;

import com.sgm.dms.fol.common.api.domain.PageVo;

public class BonusVo  extends PageVo{
	
	//客户代码
	private String sapCode;
	//渠道类型
	private String dealerType;
	//奖金类型
	private String bonusType;
	//年月
	private String bonusDate;
	//有无差异
	private String isDiff;//0 有，1无
	//客户代码起始值
	private String startSapCode;
	//客户代码截止值
    private String endSapCode;
    private String token;
    private Object data;
    private String changeTime;
    private Integer year;
    private Integer month;
    private String bonusLastAmount;	//本月期初余额
    private String bonusAmount; 	//本月期末余额(FOL)
    private String sapBonusLastAmount; //sap本月期初
    private String sapBonusAmount;	//sap本月期末
    private String dealerName;
    private String billBonus;
	private String differenceAmount;
	// 财务本月颁发
	private String bonusAdd;
	// 退货奖金返回
	private String bonusSub;
	public String getSapCode() {
		return sapCode;
	}
	public void setSapCode(String sapCode) {
		this.sapCode = sapCode;
	}
	public String getDealerType() {
		return dealerType;
	}
	public void setDealerType(String dealerType) {
		this.dealerType = dealerType;
	}
	public String getBonusType() {
		return bonusType;
	}
	public void setBonusType(String bonusType) {
		this.bonusType = bonusType;
	}
	public String getBonusDate() {
		return bonusDate;
	}
	public void setBonusDate(String bonusDate) {
		this.bonusDate = bonusDate;
	}

	public String getIsDiff() {
		return isDiff;
	}
	public void setIsDiff(String isDiff) {
		this.isDiff = isDiff;
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
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getChangeTime() {
		return changeTime;
	}
	public void setChangeTime(String changeTime) {
		this.changeTime = changeTime;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}

	public String getBonusLastAmount() {
		return bonusLastAmount;
	}
	public void setBonusLastAmount(String bonusLastAmount) {
		this.bonusLastAmount = bonusLastAmount;
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
	public String getDealerName() {
		return dealerName;
	}
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}
	public String getBillBonus() {
		return billBonus;
	}
	public void setBillBonus(String billBonus) {
		this.billBonus = billBonus;
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
	public String getDifferenceAmount() {
		return differenceAmount;
	}
	public void setDifferenceAmount(String differenceAmount) {
		this.differenceAmount = differenceAmount;
	}


}
