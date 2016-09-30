package com.dealer.dro;
/**
 * 奖金月度报表(dealer)
 * @author lujinglei
 *
 */
public class BonusMonthFormDro {
		// 序号
		private Integer num;
		// 客户代码
		private String sapCode;
		// 客户名称
		private String dealerName;
		// 年度
		private Integer year;
		// 月度
		private Integer month;
		// 本月期初余额
		private String beforeChangeAmount;
		// 新增颁发
		private String bonusAddAmount;
		// 奖金解冻
		private String freezeAmount;
		// 退货奖金返回
		private String bonusSub;
		// 订单扣款
		private String orderSub;
		// 订单冻结
		private String frozenAmount;
		// 本月期末可用余额
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

	
}
