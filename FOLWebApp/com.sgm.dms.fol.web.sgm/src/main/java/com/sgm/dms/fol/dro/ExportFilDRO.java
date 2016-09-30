package com.sgm.dms.fol.dro;


/**
 * 储备金导出结果类
 * 
 * @author lujinglei
 * 
 */
public class ExportFilDRO {
	// 序号
	private Integer num;
	// 客户代码
	private String sapCode;

	// 客户名称
	private String dealerName;
	// 渠道类型
	private String dealerTypeName;
	// 储备金类型
	private String reserveTypeName;
	// 储备金余额
	private String totalAmount;
	// 冻结储备金
	private String frozenAmount;
	// 储备金可用余额
	private String availAmount;
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
	public String getDealerTypeName() {
		return dealerTypeName;
	}
	public void setDealerTypeName(String dealerTypeName) {
		this.dealerTypeName = dealerTypeName;
	}
	public String getReserveTypeName() {
		return reserveTypeName;
	}
	public void setReserveTypeName(String reserveTypeName) {
		this.reserveTypeName = reserveTypeName;
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
	

}
