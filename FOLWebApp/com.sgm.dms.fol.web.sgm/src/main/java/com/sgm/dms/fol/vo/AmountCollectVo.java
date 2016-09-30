package com.sgm.dms.fol.vo;

/**
 * 奖金余额汇总VO
 * 
 * @author lujinglei
 * 
 */
public class AmountCollectVo {
	// 序号
	private Integer num;
	//客户代码
	private String sapCode;
	// 品牌
	private String typeName;
	// 年月
	private String bonusDate;
	// 本月期初余额
	private String beforeChangeAmount;
	// 财务本月颁发
	private String bonusAdd;
	// 退货奖金返回
	private String bonusSub;
	// 订单使用
	private String orderSub;
	// 本月期末余额
	private String afterChangeAmount;
	private Object brandId;
	private String year;
	private String month;
	private Integer endNo;
	private Integer beginNo;
	private String differenceAmount;
	// token
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

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getBonusDate() {
		return bonusDate;
	}

	public void setBonusDate(String bonusDate) {
		this.bonusDate = bonusDate;
	}

	public String getBeforeChangeAmount() {
		return beforeChangeAmount;
	}

	public void setBeforeChangeAmount(String beforeChangeAmount) {
		this.beforeChangeAmount = beforeChangeAmount;
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

	public String getOrderSub() {
		return orderSub;
	}

	public void setOrderSub(String orderSub) {
		this.orderSub = orderSub;
	}

	public String getAfterChangeAmount() {
		return afterChangeAmount;
	}

	public void setAfterChangeAmount(String afterChangeAmount) {
		this.afterChangeAmount = afterChangeAmount;
	}


	public Object getBrandId() {
		return brandId;
	}

	public void setBrandId(Object brandId) {
		this.brandId = brandId;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getEndNo() {
		return endNo;
	}

	public void setEndNo(Integer endNo) {
		this.endNo = endNo;
	}

	public Integer getBeginNo() {
		return beginNo;
	}

	public void setBeginNo(Integer beginNo) {
		this.beginNo = beginNo;
	}

	public String getDifferenceAmount() {
		return differenceAmount;
	}

	public void setDifferenceAmount(String differenceAmount) {
		this.differenceAmount = differenceAmount;
	}

}
