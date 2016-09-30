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

public class BankTicketDeatilVo extends PageVo implements java.io.Serializable{

    private static final long serialVersionUID = 1L;
    
    private String sapCode;
    private String dealerName;
    private Long dealerType;
    private String bankName;
    private String acceptanceNumber;
    private String documentNumber;
    private Integer brandId;
    private String bankId;//银行简称
    private String issueDate;
    private String expirationDate;
    private String issueDateDisplay;
    private String startIssueDate;
    private String endIssueDate;
    private String amount;
    private String remark;
    private Integer bankTicketStatus;
    private String bankTicketStatusName;
    
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
    
    public Long getDealerType() {
        return dealerType;
    }
    
    public void setDealerType(Long dealerType) {
        this.dealerType = dealerType;
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
    
    public Integer getBrandId() {
        return brandId;
    }
    
    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
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
    
    public String getExpirationDate() {
        return expirationDate;
    }
    
    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
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
    
    public String getAmount() {
        return amount;
    }
    
    public void setAmount(String amount) {
        this.amount = amount;
    }
    
    public String getRemark() {
        return remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    public Integer getBankTicketStatus() {
        return bankTicketStatus;
    }
    
    public void setBankTicketStatus(Integer bankTicketStatus) {
        this.bankTicketStatus = bankTicketStatus;
    }
    
    public String getBankTicketStatusName() {
        return bankTicketStatusName;
    }
    
    public void setBankTicketStatusName(String bankTicketStatusName) {
        this.bankTicketStatusName = bankTicketStatusName;
    }
    
    
}
