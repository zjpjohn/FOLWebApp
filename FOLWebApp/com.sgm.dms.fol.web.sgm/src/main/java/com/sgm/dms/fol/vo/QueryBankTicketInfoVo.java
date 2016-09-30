/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : web.sgm
*
* @File name : QueryBankTicketInfoVo.java
*
* @Author : DELL
*
* @Date : 2016年2月14日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2016年2月14日    DELL    1.0
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
* @date 2016年2月14日
*/

public class QueryBankTicketInfoVo extends PageVo implements java.io.Serializable{

    private static final long serialVersionUID = -2134174524876817956L;

    private String acceptanceNumber;//银票号码,长度为30位
    private String sapCode;//经销商代码
    private String dealerName;//经销商名称
    private String amount;//金额
    private String bankId;//出票行简称;
    private String bankName;//银行名称
    private String issueDate;//出票日
    private String expirationDate;//到期日 
    private Integer dealerType;//渠道类型
    private Integer brandId;//品牌
    private String documentNumber;//凭证号码
    private Integer bankTicketStatus;//银票状态（已到期，未到期）
    private String bankTicketStatusName;//银票状态名（已到期，未到期）
    private Integer beginNo;
    private Integer endNo;
    
    public String getAcceptanceNumber() {
        return acceptanceNumber;
    }
    
    public void setAcceptanceNumber(String acceptanceNumber) {
        this.acceptanceNumber = acceptanceNumber;
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
    
    public String getAmount() {
        return amount;
    }
    
    public void setAmount(String amount) {
        this.amount = amount;
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
    
    public String getExpirationDate() {
        return expirationDate;
    }
    
    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
    
    public Integer getDealerType() {
        return dealerType;
    }
    
    public void setDealerType(Integer dealerType) {
        this.dealerType = dealerType;
    }
    
    public Integer getBrandId() {
        return brandId;
    }
    
    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }
    
    public String getDocumentNumber() {
        return documentNumber;
    }
    
    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
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
