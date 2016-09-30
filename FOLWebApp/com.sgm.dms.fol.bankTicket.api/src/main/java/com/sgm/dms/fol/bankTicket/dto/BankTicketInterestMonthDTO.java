package com.sgm.dms.fol.bankTicket.dto;

import java.math.BigDecimal;

import com.sgm.dms.fol.common.api.domain.BaseDTO;

/***
 * 银票贴息统计dto
*
* @author zhang bao
* TODO description
* @date 2016年1月14日
 */
public class BankTicketInterestMonthDTO  extends BaseDTO{
		private Long 	id               ;//主键
		private String 	sapCode         ;
		private String dealerName;//名称 
		private BigDecimal 	interestAmount  ;//贴息金额
		private BigDecimal 	unTaxAmount    ;//不函数金额
		private BigDecimal 	addTaxAmount   ;//增值税金额
		private String 	year             ;//年度
		private String 	month            ;//月度
		private String 	certificateNo   ;//贴息凭证号
		private String 	flag             ;//结果
		private Integer 	valid            ;//是否有效
		private String 	remark           ;//备注
//		private String corpCode;//公司代码
		private String invoiceNumber;//发票号码
		private String trackNumber;//快递单号
		private Integer beginNo;
		private Integer endNo;
		private String issueStatusName;//发放状态名
		private String confirmStatusName;//确认状态名
		private Integer issueStatus;//发放状态
		private Integer confirmStatus;//确认状态
		
		public String getInvoiceNumber() {
			return invoiceNumber;
		}
		public void setInvoiceNumber(String invoiceNumber) {
			this.invoiceNumber = invoiceNumber;
		}
		public String getTrackNumber() {
			return trackNumber;
		}
		public void setTrackNumber(String trackNumber) {
			this.trackNumber = trackNumber;
		}
		
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getSapCode() {
			return sapCode;
		}
		public void setSapCode(String sapCode) {
			this.sapCode = sapCode;
		}
		public BigDecimal getInterestAmount() {
			return interestAmount;
		}
		public void setInterestAmount(BigDecimal interestAmount) {
			this.interestAmount = interestAmount;
		}
		public BigDecimal getUnTaxAmount() {
			return unTaxAmount;
		}
		public void setUnTaxAmount(BigDecimal unTaxAmount) {
			this.unTaxAmount = unTaxAmount;
		}
		public BigDecimal getAddTaxAmount() {
			return addTaxAmount;
		}
		public void setAddTaxAmount(BigDecimal addTaxAmount) {
			this.addTaxAmount = addTaxAmount;
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
		public String getCertificateNo() {
			return certificateNo;
		}
		public void setCertificateNo(String certificateNo) {
			this.certificateNo = certificateNo;
		}
		public String getFlag() {
			return flag;
		}
		public void setFlag(String flag) {
			this.flag = flag;
		}
		public Integer getValid() {
			return valid;
		}
		public void setValid(Integer valid) {
			this.valid = valid;
		}
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
//		public String getCorpCode() {
//			return corpCode;
//		}
//		public void setCorpCode(String corpCode) {
//			this.corpCode = corpCode;
//		}
		public String getDealerName() {
			return dealerName;
		}
		public void setDealerName(String dealerName) {
			this.dealerName = dealerName;
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
        
        public String getIssueStatusName() {
            return issueStatusName;
        }
        
        public void setIssueStatusName(String issueStatusName) {
            this.issueStatusName = issueStatusName;
        }
        
        public String getConfirmStatusName() {
            return confirmStatusName;
        }
        
        public void setConfirmStatusName(String confirmStatusName) {
            this.confirmStatusName = confirmStatusName;
        }
        
        public Integer getIssueStatus() {
            return issueStatus;
        }
        
        public void setIssueStatus(Integer issueStatus) {
            this.issueStatus = issueStatus;
        }
        
        public Integer getConfirmStatus() {
            return confirmStatus;
        }
        
        public void setConfirmStatus(Integer confirmStatus) {
            this.confirmStatus = confirmStatus;
        }

        
}
