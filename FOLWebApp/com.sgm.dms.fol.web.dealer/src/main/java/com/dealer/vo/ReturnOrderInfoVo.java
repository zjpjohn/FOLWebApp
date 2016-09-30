
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : web.dealer
 *
 * @File name : ReturnOrderInfoVo.java
 *
 * @Author : wangfl
 *
 * @Date : 2016年8月18日
 *
 ----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2016年8月18日    wangfl    1.0
 *
 *
 *
 *
 ----------------------------------------------------------------------------------
 */
	
package com.dealer.vo;

import java.math.BigDecimal;

/**
 * 退货证明信息Vo
 * @author wangfl
 * @date 2016年8月18日
 */

public class ReturnOrderInfoVo {

    private String returnOrderNo;//退货证明单号

    private String invoiceNumber;//增值税专用发票号码

    private BigDecimal gross;//退货含税金额

    private String reportDate;//上报日期

    private String reporter;//上报人

    private String acceptDate;//受理日期

    private String sgmRemark;//受理意见

    private String proposer;//申请人

    private String applyDate;//申请日期

	public String getReturnOrderNo() {
		return returnOrderNo;
	}

	public void setReturnOrderNo(String returnOrderNo) {
		this.returnOrderNo = returnOrderNo;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public BigDecimal getGross() {
		return gross;
	}

	public void setGross(BigDecimal gross) {
		this.gross = gross;
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

}
