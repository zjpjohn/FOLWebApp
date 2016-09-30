/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : web.sgm
*
* @File name : BankTicketExceptionInventoryDTO.java
*
* @Author : DELL
*
* @Date : 2016年3月29日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2016年3月29日    DELL    1.0
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
* 银票异常清单DTO
* @author DELL
* TODO description
* @date 2016年3月29日
*/

public class BankTicketExceptionInventoryDTO extends BaseDTO{
    
    private String sapCode;
    private String dealerName;
    private String bankName;
    private String issueDate;//出票日期
    private String acceptanceNumber;//银票号
    private String errorMsg;//错误信息
    private String corpCode;//公司代码
    private BigDecimal amount;//银票金额
    private Integer beginNo;//开始编号
    private Integer endNo;//结束编号
    
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
    
    public String getIssueDate() {
        return issueDate;
    }
    
    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }
    
    public String getAcceptanceNumber() {
        return acceptanceNumber;
    }
    
    public void setAcceptanceNumber(String acceptanceNumber) {
        this.acceptanceNumber = acceptanceNumber;
    }
    
    public String getErrorMsg() {
        return errorMsg;
    }
    
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
    
    public String getCorpCode() {
        return corpCode;
    }
    
    public void setCorpCode(String corpCode) {
        this.corpCode = corpCode;
    }
    
    public BigDecimal getAmount() {
        return amount;
    }
    
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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
    
    
}
