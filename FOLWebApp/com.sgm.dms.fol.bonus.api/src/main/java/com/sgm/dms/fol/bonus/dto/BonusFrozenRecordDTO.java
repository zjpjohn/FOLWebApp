package com.sgm.dms.fol.bonus.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.sgm.dms.fol.common.api.domain.BaseDTO;

public class BonusFrozenRecordDTO  extends BaseDTO{
		// 流水号
		private Integer id;

		// 交易ID
		private String tsId;

		// 经销商代码
		private String sapCode;

		// 经销商简称
		private String dealerName;

		// 冻结前的奖金金额
		private BigDecimal beforeFreezeAmount;

		// 变动的奖金金额
		private BigDecimal freezeAmount;

		// 变动后的奖金金额
		private BigDecimal afterFreezeAmount;

		// 变化参考
		private String referCode;

		// 变化类型
		private long referType;

		// 备注
		private String remark;

		// 最后冻结日期
		private Date lastFreezeTime;
		
		//回滚标识 
		private Integer isRollback;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getTsId() {
			return tsId;
		}

		public void setTsId(String tsId) {
			this.tsId = tsId;
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

		public BigDecimal getBeforeFreezeAmount() {
			return beforeFreezeAmount;
		}

		public void setBeforeFreezeAmount(BigDecimal beforeFreezeAmount) {
			this.beforeFreezeAmount = beforeFreezeAmount;
		}

		public BigDecimal getFreezeAmount() {
			return freezeAmount;
		}

		public void setFreezeAmount(BigDecimal freezeAmount) {
			this.freezeAmount = freezeAmount;
		}

		public BigDecimal getAfterFreezeAmount() {
			return afterFreezeAmount;
		}

		public void setAfterFreezeAmount(BigDecimal afterFreezeAmount) {
			this.afterFreezeAmount = afterFreezeAmount;
		}

		public String getReferCode() {
			return referCode;
		}

		public void setReferCode(String referCode) {
			this.referCode = referCode;
		}

		public long getReferType() {
			return referType;
		}

		public void setReferType(long referType) {
			this.referType = referType;
		}

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}

		public Date getLastFreezeTime() {
			return lastFreezeTime;
		}

		public void setLastFreezeTime(Date lastFreezeTime) {
			this.lastFreezeTime = lastFreezeTime;
		}

		public Integer getIsRollback() {
			return isRollback;
		}

		public void setIsRollback(Integer isRollback) {
			this.isRollback = isRollback;
		}

}
