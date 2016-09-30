package com.sgm.dms.fol.dro;

/**
 * 奖金月度明细DRO
 * 
 * @author lujinglei
 * 
 */
public class BonusMonthDeatilDRO {
	// 序号
	private Integer num;
	//客户代码
	private String sapCode;
	// 客户名称
	private String dealerName;
	// SAP过账日期
	private String postingDate;
	// FOL处理日期
	private String changeTime;
	// 变动前奖金余额
	private String beforeChangeAmount;
	//变动金额
	private String changeAmount;
	// 变动类型
	private String referType;
	// 批次号
	private long id;
	// FOL金额
	private String folBonusAmount;

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

	public String getBeforeChangeAmount() {
		return beforeChangeAmount;
	}

	public void setBeforeChangeAmount(String beforeChangeAmount) {
		this.beforeChangeAmount = beforeChangeAmount;
	}
	
	public String getChangeAmount() {
		return changeAmount;
	}

	public void setChangeAmount(String changeAmount) {
		this.changeAmount = changeAmount;
	}

	public String getReferType() {
		return referType;
	}

	public void setReferType(String referType) {
		this.referType = referType;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFolBonusAmount() {
		return folBonusAmount;
	}

	public void setFolBonusAmount(String folBonusAmount) {
		this.folBonusAmount = folBonusAmount;
	}





}
