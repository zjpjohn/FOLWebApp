/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : web.sgm
*
* @File name : BankTicketCollectVo.java
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

public class BankTicketCollectVo extends PageVo implements java.io.Serializable{
    private static final long serialVersionUID = 44654945299531305L;
    
    private Integer dealerType;
    private String dealerName;
    private String sapCode;
    private String bankId;
    private String bankName;
    private String totalAmount;//总金额
    private String usedAmount;//已用金额
    private String surplusAmount;//剩余金额
    private String fiveDayWithInAmount;//5天内金额
    
    public Integer getDealerType() {
        return dealerType;
    }
    
    public void setDealerType(Integer dealerType) {
        this.dealerType = dealerType;
    }
    
    public String getDealerName() {
        return dealerName;
    }
    
    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }
    
    public String getSapCode() {
        return sapCode;
    }
    
    public void setSapCode(String sapCode) {
        this.sapCode = sapCode;
    }
    
    public String getBankName() {
        return bankName;
    }
    
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    
    public String getTotalAmount() {
        return totalAmount;
    }
    
    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    public String getUsedAmount() {
        return usedAmount;
    }
    
    public void setUsedAmount(String usedAmount) {
        this.usedAmount = usedAmount;
    }
    
    public String getSurplusAmount() {
        return surplusAmount;
    }
    
    public void setSurplusAmount(String surplusAmount) {
        this.surplusAmount = surplusAmount;
    }
    
    public String getFiveDayWithInAmount() {
        return fiveDayWithInAmount;
    }
    
    public void setFiveDayWithInAmount(String fiveDayWithInAmount) {
        this.fiveDayWithInAmount = fiveDayWithInAmount;
    }

    
    public String getBankId() {
        return bankId;
    }

    
    public void setBankId(String bankId) {
        this.bankId = bankId;
    }
    
    
}
