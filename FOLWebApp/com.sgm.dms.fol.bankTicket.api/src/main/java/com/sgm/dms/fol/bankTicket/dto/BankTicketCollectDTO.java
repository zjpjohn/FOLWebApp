/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : bankTicket.api
*
* @File name : BankTicketCollectDTO.java
*
* @Author : DELL
*
* @Date : 2016年1月19日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2016年1月19日    DELL    1.0
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
* @date 2016年1月19日
*/

public class BankTicketCollectDTO extends BaseDTO{
    
    private Integer dealerType;
    private String dealerName;
    private String sapCode;
    private String bankId;
    private String bankName;
    private BigDecimal totalAmount;//总金额
    private BigDecimal usedAmount;//已用金额
    private BigDecimal surplusAmount;//剩余金额
    private BigDecimal fiveDayWithInAmount;//5天内金额
    private String sapExecuteResult;
    private String folResult;
    private List<String> saicResultList;//查询时，saicResult的条件列表
    
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
    
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
    
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    public BigDecimal getUsedAmount() {
        return usedAmount;
    }
    
    public void setUsedAmount(BigDecimal usedAmount) {
        this.usedAmount = usedAmount;
    }
    
    public BigDecimal getSurplusAmount() {
        return surplusAmount;
    }
    
    public void setSurplusAmount(BigDecimal surplusAmount) {
        this.surplusAmount = surplusAmount;
    }
    
    public BigDecimal getFiveDayWithInAmount() {
        return fiveDayWithInAmount;
    }
    
    public void setFiveDayWithInAmount(BigDecimal fiveDayWithInAmount) {
        this.fiveDayWithInAmount = fiveDayWithInAmount;
    }

    
    public String getBankId() {
        return bankId;
    }

    
    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

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

	public List<String> getSaicResultList() {
		return saicResultList;
	}

	public void setSaicResultList(List<String> saicResultList) {
		this.saicResultList = saicResultList;
	}
}
