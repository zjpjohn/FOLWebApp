package com.sgm.dms.fol.vo;

import java.util.Date;

import com.sgm.dms.fol.common.service.domains.BasePo;

/**
 * 银票期限.限额及特殊经销商审核Vo
 * @author lujinglei
 *
 */
public class BankTicketDeadLineVerifyVo extends BasePo{
		private Long id;
	    private Integer dealerType;//渠道类型
	    private Integer brandId;//品牌
	    private String dealerTypeName;
	    private String brandName;
	    private String sapCode;
	    private String dealerName;
	    private Integer deadlineDay;//票据天数
	    private Integer freePeriodDay;//免息期（天）
	    private Date effectDate;//起效日
	    private Date expireDate;//到期日
	    private String effectDateDisplay;
	    private String expireDateDisplay;
	    private String auditStatusName;//审核状态名
	    private String auditMsg; //审核意见
	    private String amountLimit; //票据限额
		private Integer beginNo;
		private Integer endNo;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public Integer getDealerType() {
			return dealerType;
		}
		public void setDealerType(Integer dealerType) {
			this.dealerType = dealerType;
		}
		public Integer getBrandId() {
			return brandId;
		}
		public void setBrandId(Integer brandId) {
			this.brandId = brandId;
		}
		public String getDealerTypeName() {
			return dealerTypeName;
		}
		public void setDealerTypeName(String dealerTypeName) {
			this.dealerTypeName = dealerTypeName;
		}
		public String getBrandName() {
			return brandName;
		}
		public void setBrandName(String brandName) {
			this.brandName = brandName;
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
		public Integer getDeadlineDay() {
			return deadlineDay;
		}
		public void setDeadlineDay(Integer deadlineDay) {
			this.deadlineDay = deadlineDay;
		}
		public Integer getFreePeriodDay() {
			return freePeriodDay;
		}
		public void setFreePeriodDay(Integer freePeriodDay) {
			this.freePeriodDay = freePeriodDay;
		}
		public Date getEffectDate() {
			return effectDate;
		}
		public void setEffectDate(Date effectDate) {
			this.effectDate = effectDate;
		}
		public Date getExpireDate() {
			return expireDate;
		}
		public void setExpireDate(Date expireDate) {
			this.expireDate = expireDate;
		}
		public String getEffectDateDisplay() {
			return effectDateDisplay;
		}
		public void setEffectDateDisplay(String effectDateDisplay) {
			this.effectDateDisplay = effectDateDisplay;
		}
		public String getExpireDateDisplay() {
			return expireDateDisplay;
		}
		public void setExpireDateDisplay(String expireDateDisplay) {
			this.expireDateDisplay = expireDateDisplay;
		}
		public String getAuditStatusName() {
			return auditStatusName;
		}
		public void setAuditStatusName(String auditStatusName) {
			this.auditStatusName = auditStatusName;
		}
		public String getAuditMsg() {
			return auditMsg;
		}
		public void setAuditMsg(String auditMsg) {
			this.auditMsg = auditMsg;
		}
		public String getAmountLimit() {
			return amountLimit;
		}
		public void setAmountLimit(String amountLimit) {
			this.amountLimit = amountLimit;
		}
		public Integer getBeginNo() {
			return beginNo;
		}
		public void setBeginNo(Integer beginNo) {
			this.beginNo = beginNo;
		}
		public Integer getEndNo() {
			return endNo;
		}
		public void setEndNo(Integer endNo) {
			this.endNo = endNo;
		}
	    
}
