package com.dealer.vo;

/**
 * 奖金月度报表Vo(dealer)
 * 
 * @author lujinglei
 * 
 */
public class BonusMonthFormVo {
	// 序号
	private Integer num;
	// 客户代码
	private String sapCode;
	// 客户名称
	private String dealerName;
	// 查询年份
	private Integer year;
	// 查询月份
	private Integer month;
	// 本月期初余额
	private String beforeChangeAmount;
	// 新增颁发
	private String bonusAddAmount;
	// 订单解冻
	private String freezeAmount;
	// 订单扣款
	private String orderSub;
	// 订单冻结
	private String frozenAmount;
	// 退货奖金返回
	private String bonusSub;
	// 本月期末可用余额
	private String afterChangeAmount;
	// 开始年度
	private String startTime;
	// 结束年度
	private String endTime;
	private Object referType;
	private String beginNo;
	private String endNo;
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
	public String getDealerName() {
		return dealerName;
	}
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
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
	public String getBeforeChangeAmount() {
		return beforeChangeAmount;
	}
	public void setBeforeChangeAmount(String beforeChangeAmount) {
		this.beforeChangeAmount = beforeChangeAmount;
	}
	public String getBonusAddAmount() {
		return bonusAddAmount;
	}
	public void setBonusAddAmount(String bonusAddAmount) {
		this.bonusAddAmount = bonusAddAmount;
	}
	public String getFreezeAmount() {
		return freezeAmount;
	}
	public void setFreezeAmount(String freezeAmount) {
		this.freezeAmount = freezeAmount;
	}

	public String getOrderSub() {
		return orderSub;
	}
	public void setOrderSub(String orderSub) {
		this.orderSub = orderSub;
	}
	public String getFrozenAmount() {
		return frozenAmount;
	}
	public void setFrozenAmount(String frozenAmount) {
		this.frozenAmount = frozenAmount;
	}
	public String getBonusSub() {
		return bonusSub;
	}
	public void setBonusSub(String bonusSub) {
		this.bonusSub = bonusSub;
	}
	public String getAfterChangeAmount() {
		return afterChangeAmount;
	}
	public void setAfterChangeAmount(String afterChangeAmount) {
		this.afterChangeAmount = afterChangeAmount;
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
	public Object getReferType() {
		return referType;
	}
	public void setReferType(Object referType) {
		this.referType = referType;
	}
	
	public String getBeginNo() {
		return beginNo;
	}
	public void setBeginNo(String beginNo) {
		this.beginNo = beginNo;
	}
	public String getEndNo() {
		return endNo;
	}
	public void setEndNo(String endNo) {
		this.endNo = endNo;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

	
}
