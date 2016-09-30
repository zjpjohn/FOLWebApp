package com.sgm.dms.fol.vo;

/**
 * 奖金月度明细查询Vo
 * 
 * @author lujinglei
 * 
 */
public class BonusMonthDeatilVo {
	// 序号
	private Integer num;
	// 客户代码
	private String sapCode;
	// 渠道类型
	private String dealerType;
	// 奖金类型
	private String bonusType;
	// 年月
	private String bonusDate;
	// 有无差异
	private int isDiff;// 0 有，1无
	// 公司代码
	private String dealerCode;
	// 客户名称
	private String dealerName;
	// SAP过账日期
	private String postingDate;
	// FOL处理日期
	private String changeTime;
	// 变动前奖金余额
	private String beforeChangeAmount;
	// 变动类型
	private String referType;
	// 批次号
	private long id;
	// 详细信息
	private String referCode;
	//变动金额
	private String changeAmount;
	// FOL金额
	private String folBonusAmount;
	private String year;
	private String month;
	// token
	private String token;
	private String beginNo;
	private String endNo;
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


	public String getBonusType() {
		return bonusType;
	}

	public void setBonusType(String bonusType) {
		this.bonusType = bonusType;
	}

	public String getBonusDate() {
		return bonusDate;
	}

	public void setBonusDate(String bonusDate) {
		this.bonusDate = bonusDate;
	}

	public int getIsDiff() {
		return isDiff;
	}

	public void setIsDiff(int isDiff) {
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

	public String getBeforeChangeAmount() {
		return beforeChangeAmount;
	}

	public void setBeforeChangeAmount(String beforeChangeAmount) {
		this.beforeChangeAmount = beforeChangeAmount;
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getReferCode() {
		return referCode;
	}

	public void setReferCode(String referCode) {
		this.referCode = referCode;
	}


	public String getFolBonusAmount() {
		return folBonusAmount;
	}

	public void setFolBonusAmount(String folBonusAmount) {
		this.folBonusAmount = folBonusAmount;
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

	public String getBeginNo() {
		return beginNo;
	}

	public String getReferType() {
		return referType;
	}

	public void setReferType(String referType) {
		this.referType = referType;
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

	public String getChangeAmount() {
		return changeAmount;
	}

	public void setChangeAmount(String changeAmount) {
		this.changeAmount = changeAmount;
	}

}
