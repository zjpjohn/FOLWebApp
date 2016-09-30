package com.sgm.dms.fol.vo;

import java.math.BigDecimal;

public class BonusDataVo {
		//客户代码
		private String sapCode;
		//客户名称
		private String dealerName;
		//本月期初余额
		private BigDecimal beforeChangeAmount;
		//本月期末金额
		private BigDecimal afterChangeAmount;
		//财务本月颁发
		private BigDecimal bonusAdd;
		//退货奖金返回
		private BigDecimal bonusSub;
		//本月减少(billing使用)
		private BigDecimal billBonus;
		//sap奖金信息本月初期
		private BigDecimal sapBeforeChangeAmount;
		//sap奖金信息本月期末
		private BigDecimal sapAfterChangeAmount;
		//余额差异
		private BigDecimal diffMoney;
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
		public BigDecimal getBeforeChangeAmount() {
			return beforeChangeAmount;
		}
		public void setBeforeChangeAmount(BigDecimal beforeChangeAmount) {
			this.beforeChangeAmount = beforeChangeAmount;
		}
		public BigDecimal getAfterChangeAmount() {
			return afterChangeAmount;
		}
		public void setAfterChangeAmount(BigDecimal afterChangeAmount) {
			this.afterChangeAmount = afterChangeAmount;
		}
		public BigDecimal getBonusAdd() {
			return bonusAdd;
		}
		public void setBonusAdd(BigDecimal bonusAdd) {
			this.bonusAdd = bonusAdd;
		}
		public BigDecimal getBonusSub() {
			return bonusSub;
		}
		public void setBonusSub(BigDecimal bonusSub) {
			this.bonusSub = bonusSub;
		}
		public BigDecimal getBillBonus() {
			return billBonus;
		}
		public void setBillBonus(BigDecimal billBonus) {
			this.billBonus = billBonus;
		}
		public BigDecimal getSapBeforeChangeAmount() {
			return sapBeforeChangeAmount;
		}
		public void setSapBeforeChangeAmount(BigDecimal sapBeforeChangeAmount) {
			this.sapBeforeChangeAmount = sapBeforeChangeAmount;
		}
		public BigDecimal getSapAfterChangeAmount() {
			return sapAfterChangeAmount;
		}
		public void setSapAfterChangeAmount(BigDecimal sapAfterChangeAmount) {
			this.sapAfterChangeAmount = sapAfterChangeAmount;
		}
		public BigDecimal getDiffMoney() {
			return diffMoney;
		}
		public void setDiffMoney(BigDecimal diffMoney) {
			this.diffMoney = diffMoney;
		}
		

}
