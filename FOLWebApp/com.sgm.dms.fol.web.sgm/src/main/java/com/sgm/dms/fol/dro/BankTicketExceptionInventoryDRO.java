/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : web.sgm
*
* @File name : BankTicketExceptionInventoryDRO.java
*
* @Author : DELL
*
* @Date : 2016年3月30日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2016年3月30日    DELL    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/
	
package com.sgm.dms.fol.dro;


/*
*
* @author DELL
* TODO description
* @date 2016年3月30日
*/

public class BankTicketExceptionInventoryDRO {

    // 序号
    private Integer num;
    private String sapCode;
    private String dealerName;
    private String bankName;
    private String issueDate;
    private String acceptanceNumber;
    private String errorMsg;//错误信息
    private String corpCode;//公司代码
    private String amount;//银票金额

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
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
    
    public String getAmount() {
        return amount;
    }
    
    public void setAmount(String amount) {
        this.amount = amount;
    }
    
    
}
