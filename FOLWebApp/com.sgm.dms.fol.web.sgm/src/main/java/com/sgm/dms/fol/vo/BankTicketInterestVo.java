/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : web.sgm
*
* @File name : BankTicketInterestVo.java
*
* @Author : DELL
*
* @Date : 2016年1月18日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2016年1月18日    DELL    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/
	
package com.sgm.dms.fol.vo;

import com.sgm.dms.fol.common.api.domain.PageVo;

/*
*
* @author DELL
* TODO description
* @date 2016年1月18日
*/

public class BankTicketInterestVo extends PageVo implements java.io.Serializable{

    private static final long serialVersionUID = 1L;
    
    private String sapCode;
    private String dealerName;
    private String bankChName;
    private String acceptanceNumber;
    private String documentNumber;
    private String bankId;
    private String issueDate;
    private String expirationDate;
    private String issueDateDisplay;
    private String startIssueDate;
    private String endIssueDate;
    private String startExpirationDate;
    private String endExpirationDate;
    private Integer documentNumberExists;//是否有凭证号
    private String amount;
    private String disCountAmount;//贴息金额(含税价)
    private String countAmount;//不含税价金额
    private String addTaxAmount;//增值税
    private Integer deadLineDay;//票据天数
    private Integer InterestDay;//计息天数
    private String remark;
    private Integer year;
    private Integer month;
    
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

    public String getBankChName() {
        return bankChName;
    }

    
    public void setBankChName(String bankChName) {
        this.bankChName = bankChName;
    }

    public String getAcceptanceNumber() {
        return acceptanceNumber;
    }
    
    public void setAcceptanceNumber(String acceptanceNumber) {
        this.acceptanceNumber = acceptanceNumber;
    }
    
    public String getDocumentNumber() {
        return documentNumber;
    }
    
    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }
    
    public String getBankId() {
        return bankId;
    }
    
    public void setBankId(String bankId) {
        this.bankId = bankId;
    }
    
    public String getIssueDate() {
        return issueDate;
    }
    
    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }
    
    public String getIssueDateDisplay() {
        return issueDateDisplay;
    }
    
    public void setIssueDateDisplay(String issueDateDisplay) {
        this.issueDateDisplay = issueDateDisplay;
    }
    
    public String getStartIssueDate() {
        return startIssueDate;
    }
    
    public void setStartIssueDate(String startIssueDate) {
        this.startIssueDate = startIssueDate;
    }
    
    public String getEndIssueDate() {
        return endIssueDate;
    }
    
    public void setEndIssueDate(String endIssueDate) {
        this.endIssueDate = endIssueDate;
    }

    
    public Integer getDocumentNumberExists() {
        return documentNumberExists;
    }

    
    public void setDocumentNumberExists(Integer documentNumberExists) {
        this.documentNumberExists = documentNumberExists;
    }

    public String getAmount() {
        return amount;
    }

    
    public void setAmount(String amount) {
        this.amount = amount;
    }

    
    public String getDisCountAmount() {
        return disCountAmount;
    }

    
    public void setDisCountAmount(String disCountAmount) {
        this.disCountAmount = disCountAmount;
    }

    
    public String getCountAmount() {
        return countAmount;
    }

    
    public void setCountAmount(String countAmount) {
        this.countAmount = countAmount;
    }

    public String getAddTaxAmount() {
        return addTaxAmount;
    }

    
    public void setAddTaxAmount(String addTaxAmount) {
        this.addTaxAmount = addTaxAmount;
    }

    public Integer getDeadLineDay() {
        return deadLineDay;
    }

    
    public void setDeadLineDay(Integer deadLineDay) {
        this.deadLineDay = deadLineDay;
    }

    
    public Integer getInterestDay() {
        return InterestDay;
    }

    
    public void setInterestDay(Integer interestDay) {
        InterestDay = interestDay;
    }

    
    public String getExpirationDate() {
        return expirationDate;
    }

    
    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

    
    public String getStartExpirationDate() {
        return startExpirationDate;
    }

    
    public void setStartExpirationDate(String startExpirationDate) {
        this.startExpirationDate = startExpirationDate;
    }

    
    public String getEndExpirationDate() {
        return endExpirationDate;
    }

    
    public void setEndExpirationDate(String endExpirationDate) {
        this.endExpirationDate = endExpirationDate;
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

}
