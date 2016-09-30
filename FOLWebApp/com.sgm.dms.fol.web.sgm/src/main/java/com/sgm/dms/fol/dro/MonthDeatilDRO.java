package com.sgm.dms.fol.dro;


public class MonthDeatilDRO {
	// 序号
	private Integer num;
	// 客户代码
	private String sapCode;

	// 客户名称
	private String dealerName;
	// 处理日期
	private String changeTime;
	// 变化类型（扣款，打款，冻结，解冻或系统问题补偿等）
	private String referType;
	// 变动前可用余额
	private String beforeAvailAmount;
	// 变动金额
	private String changeAmount;

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

	public String getChangeTime() {
		return changeTime;
	}

	public void setChangeTime(String changeTime) {
		this.changeTime = changeTime;
	}

	public String getReferType() {
		return referType;
	}

	public void setReferType(String referType) {
		this.referType = referType;
	}

	public String getBeforeAvailAmount() {
		return beforeAvailAmount;
	}

	public void setBeforeAvailAmount(String beforeAvailAmount) {
		this.beforeAvailAmount = beforeAvailAmount;
	}

	public String getChangeAmount() {
		return changeAmount;
	}

	public void setChangeAmount(String changeAmount) {
		this.changeAmount = changeAmount;
	}

}
