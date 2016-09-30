/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : bankTicket.api
*
* @File name : QueryBankTicketTotalAmountDTO.java
*
* @Author : DELL
*
* @Date : 2016年2月21日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2016年2月21日    DELL    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/
	
package com.sgm.dms.fol.bankTicket.dto;

import java.math.BigDecimal;
import java.util.List;

import com.sgm.dms.fol.common.api.domain.BaseDTO;

/*
*
* @author DELL
* TODO description
* @date 2016年2月21日
*/

public class QueryBankTicketTotalAmountDTO extends BaseDTO{
    private Long bankId;
    private String sapExecuteResult;
    private String folResult;
    private Integer valid;
    private List<String> saicResultList;
    private Integer dealerType;
    private BigDecimal availAmount;

    public String getSapExecuteResult() {
        return sapExecuteResult;
    }
    
    public void setSapExecuteResult(String sapExecuteResult) {
        this.sapExecuteResult = sapExecuteResult;
    }
    
    public String getFolResult() {
        return folResult;
    }
    
    public void setFolResult(String folResult) {
        this.folResult = folResult;
    }
    
    public Integer getValid() {
        return valid;
    }
    
    public void setValid(Integer valid) {
        this.valid = valid;
    }
    

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public Integer getDealerType() {
        return dealerType;
    }

    public void setDealerType(Integer dealerType) {
        this.dealerType = dealerType;
    }

    public List<String> getSaicResultList() {
        return saicResultList;
    }

    public void setSaicResultList(List<String> saicResultList) {
        this.saicResultList = saicResultList;
    }

    public BigDecimal getAvailAmount() {
        return availAmount;
    }

    public void setAvailAmount(BigDecimal availAmount) {
        this.availAmount = availAmount;
    }

    
}
