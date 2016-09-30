package com.sgm.dms.fol.bonus.dto;

import java.math.BigDecimal;

/**
 * 奖金对账DTO
 * 
 * @author lujinglei
 * 
 */
public class BonusReconcileDTO {
	// 序号
	private Integer num;
	// 客户代码
	private String sapCode;
	// 渠道类型
	private String dealerType;
	// 奖金类型
	private Integer bonusType;
	// 年月
	private String bonusDate;
	// 有无差异
	private String isDiff;// 0 有，1无
	// 公司代码
	private String dealerCode;
	// 客户名称
	private String dealerName;
	// SAP过账日期
	private String postingDate;
	// FOL处理日期
	private String changeTime;
	// 变动前奖金余额
	private BigDecimal beforeChangeAmount;
	// 变动类型
	private Object referType;
	// 批次号
	private String id;
	// 详细信息
	private String referCode;
	// FOL金额
	private BigDecimal folBonusAmount;
	// 品牌
	private String typeName;
	// 财务本月颁发
	private BigDecimal bonusAdd;
	//本月减少(billing使用)
	private BigDecimal billBonus;
	//新增颁发
	private BigDecimal bonusAddAmount;
	// 订单解冻
	private BigDecimal freezeAmount;
	// 订单扣款
	private BigDecimal orderSub;
	// 订单冻结
	private BigDecimal frozenAmount;
	// 退货奖金返回
	private BigDecimal bonusSub;
	private BigDecimal changeAmount;
	// 本月期末余额
	private BigDecimal afterChangeAmount;
	private BigDecimal currentDate;
	// FOL奖金信息
	private BigDecimal folBonus;
	// SAP奖金信息
	private BigDecimal sapReserve;
	// 奖金上月月余额
	private BigDecimal bonusLastAmount;
	// 奖金变动增加的余额
	private BigDecimal bonusChangeAddAmount;
	// 奖金变动减少的余额
	private BigDecimal bonusChangeSubtractAmount;
	// 奖金本月余额
	private BigDecimal bonusAmount;
	// SAP奖金上月月余额
	private BigDecimal sapBonusLastAmount;
	// SAP奖金本月余额
	private BigDecimal sapBonusAmount;
	// 余额差异
	private BigDecimal differenceAmount;

	// 开始的客户代码
	private String startSapCode;

	// 结束的客户代码
	private String endSapCode;

	// 开始年月
	private String startTime;
	// 结束年月
	private String endTime;

	// 查询年份
	private String year;

	// 查询月份
	private String month;
	// 备注
	private String remark;
	private Integer endNo;
	private Integer beginNo;
	private Object brandId;
	// 是否会滚 0,1
	private Integer isRollback;

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

	public Integer getBonusType() {
		return bonusType;
	}

	public void setBonusType(Integer bonusType) {
		this.bonusType = bonusType;
	}

	public String getBonusDate() {
		return bonusDate;
	}

	public void setBonusDate(String bonusDate) {
		this.bonusDate = bonusDate;
	}



	public String getIsDiff() {
		return isDiff;
	}

	public void setIsDiff(String isDiff) {
		this.isDiff = isDiff;
	}

	public String getDealerCode() {
		return dealerCode;
	}

	public void setDealerCode(String dealerCode) {
		this.dealerCode = dealerCode;
	}

	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	public String getPostingDate() {
		return postingDate;
	}

	public void setPostingDate(String postingDate) {
		this.postingDate = postingDate;
	}

	public String getChangeTime() {
		return changeTime;
	}

	public void setChangeTime(String changeTime) {
		this.changeTime = changeTime;
	}

	public BigDecimal getBeforeChangeAmount() {
		return beforeChangeAmount;
	}

	public void setBeforeChangeAmount(BigDecimal beforeChangeAmount) {
		this.beforeChangeAmount = beforeChangeAmount;
	}

	public Object getReferType() {
		return referType;
	}

	public void setReferType(Object referType) {
		this.referType = referType;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getReferCode() {
		return referCode;
	}

	public void setReferCode(String referCode) {
		this.referCode = referCode;
	}

	public BigDecimal getFolBonusAmount() {
		return folBonusAmount;
	}

	public void setFolBonusAmount(BigDecimal folBonusAmount) {
		this.folBonusAmount = folBonusAmount;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public BigDecimal getBonusAdd() {
		return bonusAdd;
	}

	public void setBonusAdd(BigDecimal bonusAdd) {
		this.bonusAdd = bonusAdd;
	}

	public BigDecimal getBillBonus() {
		return billBonus;
	}

	public void setBillBonus(BigDecimal billBonus) {
		this.billBonus = billBonus;
	}

	public BigDecimal getBonusAddAmount() {
		return bonusAddAmount;
	}

	public void setBonusAddAmount(BigDecimal bonusAddAmount) {
		this.bonusAddAmount = bonusAddAmount;
	}

	public BigDecimal getFreezeAmount() {
		return freezeAmount;
	}

	public void setFreezeAmount(BigDecimal freezeAmount) {
		this.freezeAmount = freezeAmount;
	}
	public BigDecimal getOrderSub() {
		return orderSub;
	}

	public void setOrderSub(BigDecimal orderSub) {
		this.orderSub = orderSub;
	}

	public BigDecimal getFrozenAmount() {
		return frozenAmount;
	}

	public void setFrozenAmount(BigDecimal frozenAmount) {
		this.frozenAmount = frozenAmount;
	}

	public BigDecimal getBonusSub() {
		return bonusSub;
	}

	public void setBonusSub(BigDecimal bonusSub) {
		this.bonusSub = bonusSub;
	}
	public BigDecimal getChangeAmount() {
		return changeAmount;
	}

	public void setChangeAmount(BigDecimal changeAmount) {
		this.changeAmount = changeAmount;
	}

	public BigDecimal getAfterChangeAmount() {
		return afterChangeAmount;
	}

	public void setAfterChangeAmount(BigDecimal afterChangeAmount) {
		this.afterChangeAmount = afterChangeAmount;
	}

	public BigDecimal getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(BigDecimal currentDate) {
		this.currentDate = currentDate;
	}

	public BigDecimal getFolBonus() {
		return folBonus;
	}

	public void setFolBonus(BigDecimal folBonus) {
		this.folBonus = folBonus;
	}

	public BigDecimal getSapReserve() {
		return sapReserve;
	}

	public void setSapReserve(BigDecimal sapReserve) {
		this.sapReserve = sapReserve;
	}

	public BigDecimal getBonusLastAmount() {
		return bonusLastAmount;
	}

	public void setBonusLastAmount(BigDecimal bonusLastAmount) {
		this.bonusLastAmount = bonusLastAmount;
	}

	public BigDecimal getBonusChangeAddAmount() {
		return bonusChangeAddAmount;
	}

	public void setBonusChangeAddAmount(BigDecimal bonusChangeAddAmount) {
		this.bonusChangeAddAmount = bonusChangeAddAmount;
	}

	public BigDecimal getBonusChangeSubtractAmount() {
		return bonusChangeSubtractAmount;
	}

	public void setBonusChangeSubtractAmount(
			BigDecimal bonusChangeSubtractAmount) {
		this.bonusChangeSubtractAmount = bonusChangeSubtractAmount;
	}

	public BigDecimal getBonusAmount() {
		return bonusAmount;
	}

	public void setBonusAmount(BigDecimal bonusAmount) {
		this.bonusAmount = bonusAmount;
	}

	public BigDecimal getSapBonusLastAmount() {
		return sapBonusLastAmount;
	}

	public void setSapBonusLastAmount(BigDecimal sapBonusLastAmount) {
		this.sapBonusLastAmount = sapBonusLastAmount;
	}

	public BigDecimal getSapBonusAmount() {
		return sapBonusAmount;
	}

	public void setSapBonusAmount(BigDecimal sapBonusAmount) {
		this.sapBonusAmount = sapBonusAmount;
	}

	public BigDecimal getDifferenceAmount() {
		return differenceAmount;
	}

	public void setDifferenceAmount(BigDecimal differenceAmount) {
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public Integer getIsRollback() {
		return isRollback;
	}

	public void setIsRollback(Integer isRollback) {
		this.isRollback = isRollback;
	}

	public Object getBrandId() {
		return brandId;
	}

	public void setBrandId(Object brandId) {
		this.brandId = brandId;
	}


}
