package com.sgm.dms.fol.vo;

/**
 * 储备金变动明细查询VO 变动余额
 * 
 * @author lujinglei
 * 
 */
public class DeatilChangeVo {
	// 序号
	private Integer num;
	// 客户代码
	private String sapCode;
	// 渠道类型
	private String dealerType;
	// 储备金类型
	private Integer reserveType;
	private String currentDate;
	// 变动的开始日期
	private String startTime;

	// 变动的结束日期
	private String endTime;

	// 客户名称
	private String dealerName;
	// 变动日期
	private String changeTime;
	// 变动前可用余额
	private String beforeAvailAmount;
	// 变动余额
	private String changeAmount;
	// 变动后余额
	private String afterChangeAmount;
	// 冻结前储备金余额
	private String beforeFrozenAmount;
	// 冻结余额
	private String frozenAmount;
	// 冻结后储备金余额
	private String afterFrozenAmount;
	// 变化类型（扣款，打款，冻结，解冻或系统问题补偿等）
	private String referType;
	// 摘要
	private String remark;
	// 业务码
	private Integer businessCode;
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
	public String getCurrentDate() {
		return currentDate;
	}
	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
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
	public String getAfterChangeAmount() {
		return afterChangeAmount;
	}
	public void setAfterChangeAmount(String afterChangeAmount) {
		this.afterChangeAmount = afterChangeAmount;
	}
	public String getBeforeFrozenAmount() {
		return beforeFrozenAmount;
	}
	public void setBeforeFrozenAmount(String beforeFrozenAmount) {
		this.beforeFrozenAmount = beforeFrozenAmount;
	}
	public String getFrozenAmount() {
		return frozenAmount;
	}
	public void setFrozenAmount(String frozenAmount) {
		this.frozenAmount = frozenAmount;
	}
	public String getAfterFrozenAmount() {
		return afterFrozenAmount;
	}
	public void setAfterFrozenAmount(String afterFrozenAmount) {
		this.afterFrozenAmount = afterFrozenAmount;
	}
	public String getReferType() {
		return referType;
	}
	public void setReferType(String referType) {
		this.referType = referType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getBusinessCode() {
		return businessCode;
	}
	public void setBusinessCode(Integer businessCode) {
		this.businessCode = businessCode;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}



}
