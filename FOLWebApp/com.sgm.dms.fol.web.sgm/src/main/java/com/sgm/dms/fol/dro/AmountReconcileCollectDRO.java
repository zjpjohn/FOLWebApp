package com.sgm.dms.fol.dro;

/**
 * 奖金余额汇总表DRO
 * 
 * @author lujinglei
 * 
 */
public class AmountReconcileCollectDRO {
	// 序号
	private Integer num;
	// 品牌
	private String typeName;
	//客户代码
	private String sapCode;
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

}
