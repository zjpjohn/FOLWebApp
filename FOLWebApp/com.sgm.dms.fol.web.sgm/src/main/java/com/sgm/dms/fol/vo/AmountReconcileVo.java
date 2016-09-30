package com.sgm.dms.fol.vo;

/**
 * 储备金余额对账Vo
 * 
 * @author lujinglei
 * 
 */
public class AmountReconcileVo {
	// 序号
	private Integer num;
	// 客户代码
	private String sapCode;
	private String currentDate;
	// 客户名称
	private String dealerName;
	// 渠道类型
	private String dealerType;
	// 储备金类型
	private Integer reserveType;
	// FOL储备金信息
	private String folReserve;
	// SAP储备金信息
	private String sapReserve;
	private String changeTime;
	// 储备金上月月余额
	private String reserveLastAmount;
	// 储备金变动增加的余额
	private String reserveChangeAddAmount;
	// 储备金变动减少的余额
	private String reserveChangeSubtractAmount;
	// 储备金本月余额
	private String reserveAmount;
	// SAP储备金上月月余额
	private String sapReserveLastAmount;
	// SAP储备金本月余额
	private String sapReserveAmount;
	// 余额差异
	private String differenceAmount;

	// 开始的客户代码
	private String startSapCode;

	// 结束的客户代码
	private String endSapCode;

	// 查询年份
	private Integer year;

	// 查询月份
	private Integer month;
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

	public String getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
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

	public Integer getReserveType() {
		return reserveType;
	}

	public void setReserveType(Integer reserveType) {
		this.reserveType = reserveType;
	}

	public String getFolReserve() {
		return folReserve;
	}

	public void setFolReserve(String folReserve) {
		this.folReserve = folReserve;
	}

	public String getSapReserve() {
		return sapReserve;
	}

	public void setSapReserve(String sapReserve) {
		this.sapReserve = sapReserve;
	}

	public String getChangeTime() {
		return changeTime;
	}

	public void setChangeTime(String changeTime) {
		this.changeTime = changeTime;
	}

	public String getReserveLastAmount() {
		return reserveLastAmount;
	}

	public void setReserveLastAmount(String reserveLastAmount) {
		this.reserveLastAmount = reserveLastAmount;
	}

	public String getReserveChangeAddAmount() {
		return reserveChangeAddAmount;
	}

	public void setReserveChangeAddAmount(String reserveChangeAddAmount) {
		this.reserveChangeAddAmount = reserveChangeAddAmount;
	}

	public String getReserveChangeSubtractAmount() {
		return reserveChangeSubtractAmount;
	}

	public void setReserveChangeSubtractAmount(
			String reserveChangeSubtractAmount) {
		this.reserveChangeSubtractAmount = reserveChangeSubtractAmount;
	}

	public String getReserveAmount() {
		return reserveAmount;
	}

	public void setReserveAmount(String reserveAmount) {
		this.reserveAmount = reserveAmount;
	}

	public String getSapReserveLastAmount() {
		return sapReserveLastAmount;
	}

	public void setSapReserveLastAmount(String sapReserveLastAmount) {
		this.sapReserveLastAmount = sapReserveLastAmount;
	}

	public String getSapReserveAmount() {
		return sapReserveAmount;
	}

	public void setSapReserveAmount(String sapReserveAmount) {
		this.sapReserveAmount = sapReserveAmount;
	}

	public String getDifferenceAmount() {
		return differenceAmount;
	}

	public void setDifferenceAmount(String differenceAmount) {
		this.differenceAmount = differenceAmount;
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
