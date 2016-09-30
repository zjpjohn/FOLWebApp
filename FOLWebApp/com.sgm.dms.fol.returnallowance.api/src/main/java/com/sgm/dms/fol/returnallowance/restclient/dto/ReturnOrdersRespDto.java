
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : returnallowance.api
 *
 * @File name : ReturnOrdersRespDto.java
 *
 * @Author : wangfl
 *
 * @Date : 2016年8月12日
 *
 ----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2016年8月12日    wangfl    1.0
 *
 *
 *
 *
 ----------------------------------------------------------------------------------
 */

package com.sgm.dms.fol.returnallowance.restclient.dto;

import java.util.List;

/**
 * 退货证明列表获取接口响应Dto
 * 
 * @author wangfl
 * @date 2016年8月12日
 */

public class ReturnOrdersRespDto {

	// 总记录数
	private Long total;

	private List<PartClaimReturnOrderDto> returnOrders;

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<PartClaimReturnOrderDto> getReturnOrders() {
		return returnOrders;
	}

	public void setReturnOrders(List<PartClaimReturnOrderDto> returnOrders) {
		this.returnOrders = returnOrders;
	}

	/*
	 * 退货证明DTO
	 * @author wangfl
	 * @date 2016年9月14日
	 */
	public static class PartClaimReturnOrderDto {
		// 退货证明单号
		private String returnOrderNo;
		// 公司名称
		private String companyName;
		// 税务局名称
		private String taxBureauName;
		// 增值税专用发票代码
		private String invoiceCode;
		// 增值税专用发票号码
		private String invoiceNumber;
		// 退货含税金额
		private Double gross;
		// 税额
		private Double tax;
		// 不含税金额
		private Double netAmount;
		// 上报日期
		private String reportDate;
		// 上报人
		private String reporter;
		// 受理日期
		private String acceptDate;
		// 受理意见
		private String sgmRemark;
		// 申请人
		private String proposer;
		// 申请日期
		private String applyDate;
		// 折让单处理标记
		private Integer discountOrderStatus;
		/**
		 * 电子发票信息
		 */
		private List<PartEinvoiceInfoDto> einvoiceInfos;

		public static class PartEinvoiceInfoDto {
			// 销售电子发票(正向)
			private String billingNo;
			// 不含税金额
			private Double orderNetvalue;
			// 退货电子发票
			private String billingReverseNo;
			// 销方公司
			private String billingOrign;

			public String getBillingNo() {
				return billingNo;
			}

			public void setBillingNo(String billingNo) {
				this.billingNo = billingNo;
			}

			public Double getOrderNetvalue() {
				return orderNetvalue;
			}

			public void setOrderNetvalue(Double orderNetvalue) {
				this.orderNetvalue = orderNetvalue;
			}

			public String getBillingReverseNo() {
				return billingReverseNo;
			}

			public void setBillingReverseNo(String billingReverseNo) {
				this.billingReverseNo = billingReverseNo;
			}

			public String getBillingOrign() {
				return billingOrign;
			}

			public void setBillingOrign(String billingOrign) {
				this.billingOrign = billingOrign;
			}
		}

		public String getReturnOrderNo() {
			return returnOrderNo;
		}

		public void setReturnOrderNo(String returnOrderNo) {
			this.returnOrderNo = returnOrderNo;
		}

		public String getCompanyName() {
			return companyName;
		}

		public void setCompanyName(String companyName) {
			this.companyName = companyName;
		}

		public String getTaxBureauName() {
			return taxBureauName;
		}

		public void setTaxBureauName(String taxBureauName) {
			this.taxBureauName = taxBureauName;
		}

		public String getInvoiceCode() {
			return invoiceCode;
		}

		public void setInvoiceCode(String invoiceCode) {
			this.invoiceCode = invoiceCode;
		}

		public String getInvoiceNumber() {
			return invoiceNumber;
		}

		public void setInvoiceNumber(String invoiceNumber) {
			this.invoiceNumber = invoiceNumber;
		}

		public Double getGross() {
			return gross;
		}

		public void setGross(Double gross) {
			this.gross = gross;
		}

		public Double getTax() {
			return tax;
		}

		public void setTax(Double tax) {
			this.tax = tax;
		}

		public Double getNetAmount() {
			return netAmount;
		}

		public void setNetAmount(Double netAmount) {
			this.netAmount = netAmount;
		}

		public String getReportDate() {
			return reportDate;
		}

		public void setReportDate(String reportDate) {
			this.reportDate = reportDate;
		}

		public String getReporter() {
			return reporter;
		}

		public void setReporter(String reporter) {
			this.reporter = reporter;
		}

		public String getAcceptDate() {
			return acceptDate;
		}

		public void setAcceptDate(String acceptDate) {
			this.acceptDate = acceptDate;
		}

		public String getSgmRemark() {
			return sgmRemark;
		}

		public void setSgmRemark(String sgmRemark) {
			this.sgmRemark = sgmRemark;
		}

		public String getProposer() {
			return proposer;
		}

		public void setProposer(String proposer) {
			this.proposer = proposer;
		}

		public String getApplyDate() {
			return applyDate;
		}

		public void setApplyDate(String applyDate) {
			this.applyDate = applyDate;
		}

		public Integer getDiscountOrderStatus() {
			return discountOrderStatus;
		}

		public void setDiscountOrderStatus(Integer discountOrderStatus) {
			this.discountOrderStatus = discountOrderStatus;
		}

		public List<PartEinvoiceInfoDto> getEinvoiceInfos() {
			return einvoiceInfos;
		}

		public void setEinvoiceInfos(List<PartEinvoiceInfoDto> einvoiceInfos) {
			this.einvoiceInfos = einvoiceInfos;
		}
		
	}

}
