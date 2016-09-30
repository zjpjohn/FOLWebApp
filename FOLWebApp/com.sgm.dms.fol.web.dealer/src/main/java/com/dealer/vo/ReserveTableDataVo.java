package com.dealer.vo;


public class ReserveTableDataVo {
	//主键
		private String sapCode;
		
		//名称
		private String dealerName;
			
		//冻结资金 
		private String frozenAmount;
			
		//总资金
		private String totalAmount;
			
		//渠道类型
		private String dealerType;
		
		//渠道类型名称
		private String dealerTypeName;
			
		//储备金类型
		private String reserveType;
		
		//储备金类型名称
	    private String reserveTypeName;
			
		//可用资金
		private String availAmount;
		
		//变化参考（bullngno或者其它）
		private String referCode;
		
		//变化类型（扣款，打款，冻结，解冻或系统问题补偿等）
		private String referType;
		
		//变动金额
		private String changeAmount;
		
		//变动日期
		private String changeTime;
		
		//变动前可用余额
		private String beforeAvailAmount;
		
		//变动后余额
	    private String afterChangeAmount;
		
		//开始日期
		private String startTime;
		
		//结束日期
		private String endTime;
		
		//储备金本月余额
	    private String reserveAmount;
	    
	    //储备金上月月余额
	    private String reserveLastAmount;
	    
	    //储备金变动增加的余额
	    private String reserveChangeAddAmount;
	    
	    //储备金变动减少的余额
	    private String reserveChangeSubtractAmount;
	    
	    //SAP储备金本月余额
	    private String sapReserveAmount;
	    
	    //SAP储备金上月月余额
	    private String sapReserveLastAmount;
	    
	    //余额是否有差异
	    private String difference;
	    
	    //余额差异
	    private String differenceAmount;
	    
	    //开始的客户代码
	    private String startSapCode;
	    
	    //结束的客户代码
	    private String endSapCode;
		
		//摘要
		private String remark;
		
		//业务码
	    private String businessCode;
	    
	 	//查询年份
	    private String year;
	    
	    //查询月份
	    private String month;

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

		public String getFrozenAmount() {
			return frozenAmount;
		}

		public void setFrozenAmount(String frozenAmount) {
			this.frozenAmount = frozenAmount;
		}

		public String getTotalAmount() {
			return totalAmount;
		}

		public void setTotalAmount(String totalAmount) {
			this.totalAmount = totalAmount;
		}

		public String getDealerType() {
			return dealerType;
		}

		public void setDealerType(String dealerType) {
			this.dealerType = dealerType;
		}

		public String getDealerTypeName() {
			return dealerTypeName;
		}

		public void setDealerTypeName(String dealerTypeName) {
			this.dealerTypeName = dealerTypeName;
		}

		public String getReserveType() {
			return reserveType;
		}

		public void setReserveType(String reserveType) {
			this.reserveType = reserveType;
		}

		public String getReserveTypeName() {
			return reserveTypeName;
		}

		public void setReserveTypeName(String reserveTypeName) {
			this.reserveTypeName = reserveTypeName;
		}

		public String getAvailAmount() {
			return availAmount;
		}

		public void setAvailAmount(String availAmount) {
			this.availAmount = availAmount;
		}

		public String getReferCode() {
			return referCode;
		}

		public void setReferCode(String referCode) {
			this.referCode = referCode;
		}

		public String getReferType() {
			return referType;
		}

		public void setReferType(String referType) {
			this.referType = referType;
		}

		public String getChangeAmount() {
			return changeAmount;
		}

		public void setChangeAmount(String changeAmount) {
			this.changeAmount = changeAmount;
		}

		public String getChangeTime() {
			return changeTime;
		}

		public void setChangeTime(String changeTime) {
			this.changeTime = changeTime;
		}

		public String getBeforeAvailAmount() {
			return beforeAvailAmount;
		}

		public void setBeforeAvailAmount(String beforeAvailAmount) {
			this.beforeAvailAmount = beforeAvailAmount;
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

		public String getReserveAmount() {
			return reserveAmount;
		}

		public void setReserveAmount(String reserveAmount) {
			this.reserveAmount = reserveAmount;
		}

		public String getReserveLastAmount() {
			return reserveLastAmount;
		}

		public void setReserveLastAmount(String reserveLastAmount) {
			this.reserveLastAmount = reserveLastAmount;
		}

		public String getReserveChangeAddAmount() {
			return reserveChangeAddAmount;
		}

		public void setReserveChangeAddAmount(String reserveChangeAddAmount) {
			this.reserveChangeAddAmount = reserveChangeAddAmount;
		}

		public String getReserveChangeSubtractAmount() {
			return reserveChangeSubtractAmount;
		}

		public void setReserveChangeSubtractAmount(String reserveChangeSubtractAmount) {
			this.reserveChangeSubtractAmount = reserveChangeSubtractAmount;
		}

		public String getSapReserveAmount() {
			return sapReserveAmount;
		}

		public void setSapReserveAmount(String sapReserveAmount) {
			this.sapReserveAmount = sapReserveAmount;
		}

		public String getSapReserveLastAmount() {
			return sapReserveLastAmount;
		}

		public void setSapReserveLastAmount(String sapReserveLastAmount) {
			this.sapReserveLastAmount = sapReserveLastAmount;
		}

		public String getDifference() {
			return difference;
		}

		public void setDifference(String difference) {
			this.difference = difference;
		}

		public String getDifferenceAmount() {
			return differenceAmount;
		}

		public void setDifferenceAmount(String differenceAmount) {
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

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}

		public String getBusinessCode() {
			return businessCode;
		}

		public void setBusinessCode(String businessCode) {
			this.businessCode = businessCode;
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
	    
	    
}
