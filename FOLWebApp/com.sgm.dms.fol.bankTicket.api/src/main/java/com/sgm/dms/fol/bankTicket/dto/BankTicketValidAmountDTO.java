package com.sgm.dms.fol.bankTicket.dto;

import java.math.BigDecimal;

/*****
 * 可用额度查询(dealer)
*
* @author zhang bao
* TODO description
* @date 2016年1月21日
 */
public class BankTicketValidAmountDTO {
		private Integer bankId;//银行id
		private String bankAbbr;//银行缩写
		private String bankChName;//银行中文名称
		private BigDecimal availAmount;//可用额度
		private BigDecimal exptdAmount;//今天到期额度
		private BigDecimal exptmrAmount;//明天到期到期额度
		private BigDecimal expaftertmrAmount;//后天到期
		private BigDecimal totalAmount;//总金额
		private Integer beginNo;
		private Integer endNo;
		public Integer getBankId() {
			return bankId;
		}
		public void setBankId(Integer bankId) {
			this.bankId = bankId;
		}
		public String getBankAbbr() {
			return bankAbbr;
		}
		public void setBankAbbr(String bankAbbr) {
			this.bankAbbr = bankAbbr;
		}
		public String getBankChName() {
			return bankChName;
		}
		public void setBankChName(String bankChName) {
			this.bankChName = bankChName;
		}
		
		
		public BigDecimal getExptdAmount() {
			return exptdAmount;
		}
		public void setExptdAmount(BigDecimal exptdAmount) {
			this.exptdAmount = exptdAmount;
		}
		public BigDecimal getExptmrAmount() {
			return exptmrAmount;
		}
		public void setExptmrAmount(BigDecimal exptmrAmount) {
			this.exptmrAmount = exptmrAmount;
		}
		public BigDecimal getExpaftertmrAmount() {
			return expaftertmrAmount;
		}
		public void setExpaftertmrAmount(BigDecimal expaftertmrAmount) {
			this.expaftertmrAmount = expaftertmrAmount;
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
        public BigDecimal getAvailAmount() {
            return availAmount;
        }
        public void setAvailAmount(BigDecimal availAmount) {
            this.availAmount = availAmount;
        }
        public BigDecimal getTotalAmount() {
            return totalAmount;
        }
        public void setTotalAmount(BigDecimal totalAmount) {
            this.totalAmount = totalAmount;
        }
		
		
		
		
		
}
