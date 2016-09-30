package com.sgm.dms.fol.dro;


public class AmountReconcileDRO {
	// 序号
	private Integer num;
	// 客户代码
	private String sapCode;

	// 客户名称
	private String dealerName;
	// 储备金上月月余额
	private String reserveLastAmount;
	// 储备金变动增加的余额
	private String reserveChangeAddAmount;
	// 储备金变动减少的余额
	private String reserveChangeSubtractAmount;
	// 储备金本月余额
	private String reserveAmount;
	private String sapReserveLastAmount;
	// SAP储备金本月余额
	private String sapReserveAmount;
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

}
