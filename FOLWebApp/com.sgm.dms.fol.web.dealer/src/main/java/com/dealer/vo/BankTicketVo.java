/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : web.dealer
*
* @File name : BankTicketVo.java
*
* @Author : DELL
*
* @Date : 2016年1月21日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2016年1月21日    DELL    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/
	
package com.dealer.vo;

import com.sgm.dms.fol.common.api.domain.PageVo;

/*
*
* @author DELL
* TODO description
* @date 2016年1月21日
*/

public class BankTicketVo extends PageVo implements java.io.Serializable{

    private static final long serialVersionUID = 6116737236438126668L;

    private String acceptanceNumber;
    private String bankId;//银行简称
    private String bankName;//银行名称
    private String issueDate;
    private String expireDate;
    private String expirationDate;//到期日
    private String startIssueDate;
    private String endIssueDate;
    private String amount;
    private Integer bankTicketStatus;
    private String bankTicketStatusName;
    
    public String getAcceptanceNumber() {
        return acceptanceNumber;
    }
    
    public void setAcceptanceNumber(String acceptanceNumber) {
        this.acceptanceNumber = acceptanceNumber;
    }
    
    public String getBankId() {
        return bankId;
    }
    
    public void setBankId(String bankId) {
        this.bankId = bankId;
    }
    
    public String getBankName() {
        return bankName;
    }
    
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    
    public String getIssueDate() {
        return issueDate;
    }

    
    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    
    public String getExpireDate() {
        return expireDate;
    }

    
    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
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

    
    public String getExpirationDate() {
        return expirationDate;
    }

    
    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
    
    
}
