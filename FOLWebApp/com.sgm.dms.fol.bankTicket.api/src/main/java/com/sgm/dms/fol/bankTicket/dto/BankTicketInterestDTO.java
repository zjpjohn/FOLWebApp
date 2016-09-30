/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : bankTicket.api
*
* @File name : BankTicketInterestDTO.java
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
	
package com.sgm.dms.fol.bankTicket.dto;

import java.math.BigDecimal;

import com.sgm.dms.fol.common.api.domain.BaseDTO;

/*
*
* @author DELL
* TODO description
* @date 2016年1月18日
*/

public class BankTicketInterestDTO extends BaseDTO{
    private Long id;
    private String sapCode;
    private String dealerName;
    private String bankName;
    private String acceptanceNumber;
    private String corpCode;
    private String referenceCode;
    private String expirationDate;
    private String bankMismatch;
    private String status;
    private String saicResult;
    private String folResult;
    private String errorMsg;
    private Integer valid;
    private String remark;
    private String signInDate;
    private String documentNumber;//凭证号
    private String bankId;
    private String issueDate;
    private String issueDateDisplay;
    private String startIssueDate;
    private String endIssueDate;
    private Integer documentNumberExists;//是否有凭证号
    private BigDecimal amount;
    private BigDecimal disCountAmount;//贴息金额(含税价)
    private BigDecimal countAmount;//不含税价金额
    private BigDecimal addedTaxAmount;//增值税
    private Integer deadLineDay;//票据天数
    private Integer InterestDay;//计息天数   
    private Integer beginNo;
    private Integer endNo;
    private String year;//年份
    private String month;//月份
    
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
    
    public String getDealerName() {
        return dealerName;
    }
    
    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }
    
    public String getBankName() {
        return bankName;
    }
    
    public void setBankName(String bankName) {
        this.bankName = bankName;
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
   
    public BigDecimal getAmount() {
        return amount;
    }

    
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    
    public Integer getDocumentNumberExists() {
        return documentNumberExists;
    }

    
    public void setDocumentNumberExists(Integer documentNumberExists) {
        this.documentNumberExists = documentNumberExists;
    }

    
    public BigDecimal getDisCountAmount() {
        return disCountAmount;
    }

    
    public void setDisCountAmount(BigDecimal disCountAmount) {
        this.disCountAmount = disCountAmount;
    }

    
    public BigDecimal getCountAmount() {
        return countAmount;
    }

    
    public void setCountAmount(BigDecimal countAmount) {
        this.countAmount = countAmount;
    }

    
    public BigDecimal getAddedTaxAmount() {
        return addedTaxAmount;
    }

    
    public void setAddedTaxAmount(BigDecimal addedTaxAmount) {
        this.addedTaxAmount = addedTaxAmount;
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

    
    public String getReferenceCode() {
        return referenceCode;
    }

    
    public void setReferenceCode(String referenceCode) {
        this.referenceCode = referenceCode;
    }

    
    public String getExpirationDate() {
        return expirationDate;
    }

    
    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    
    public String getBankMismatch() {
        return bankMismatch;
    }

    
    public void setBankMismatch(String bankMismatch) {
        this.bankMismatch = bankMismatch;
    }

    
    public String getStatus() {
        return status;
    }

    
    public void setStatus(String status) {
        this.status = status;
    }

    
    public String getSaicResult() {
        return saicResult;
    }

    
    public void setSaicResult(String saicResult) {
        this.saicResult = saicResult;
    }

    
    public String getErrorMsg() {
        return errorMsg;
    }

    
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
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

    
    public String getSignInDate() {
        return signInDate;
    }

    
    public void setSignInDate(String signInDate) {
        this.signInDate = signInDate;
    }

    
    public String getCorpCode() {
        return corpCode;
    }

    
    public void setCorpCode(String corpCode) {
        this.corpCode = corpCode;
    }

    
    public String getFolResult() {
        return folResult;
    }

    
    public void setFolResult(String folResult) {
        this.folResult = folResult;
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
