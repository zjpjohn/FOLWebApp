package com.sgm.dms.fol.vo;


/**
 * 储备金月度明细查询VO
 * 
 * @author lujinglei
 * 
 */
public class MonthDeatilVo {
	// 序号
	private Integer num;
	// 客户代码
	private String sapCode;
	// 渠道类型
	private String dealerType;
	// 储备金类型
	private Integer reserveType;
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
	private String currentDate;

	private String token;

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

	public String getDealerType() {
		return dealerType;
	}

	public void setDealerType(String dealerType) {
		this.dealerType = dealerType;
	}

	public Integer getReserveType() {
		return reserveType;
	}

	public void setReserveType(Integer reserveType) {
		this.reserveType = reserveType;
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

	public String getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	
}
