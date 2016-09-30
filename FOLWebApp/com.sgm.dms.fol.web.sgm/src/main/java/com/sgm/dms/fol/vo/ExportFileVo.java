package com.sgm.dms.fol.vo;


/**
 * 储备金
 * 
 * @author lujinglei
 * 
 */
public class ExportFileVo {
	// 客户代码
	private String sapCode;

	// 客户名称
	private String dealerName;
	// 渠道类型
	private String dealerType;
	// 储备金类型
	private Integer reserveType;
	// 储备金余额
	private String totalAmount;
	// 冻结储备金
	private String frozenAmount;
	// 储备金可用余额
	private String availAmount;
	private String token;
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
	public Integer getReserveType() {
		return reserveType;
	}
	public void setReserveType(Integer reserveType) {
		this.reserveType = reserveType;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getFrozenAmount() {
		return frozenAmount;
	}
	public void setFrozenAmount(String frozenAmount) {
		this.frozenAmount = frozenAmount;
	}
	public String getAvailAmount() {
		return availAmount;
	}
	public void setAvailAmount(String availAmount) {
		this.availAmount = availAmount;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

	


}
