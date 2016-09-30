/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : bankTicket.service
*
* @File name : InterestRateHisPo.java
*
* @Author : DELL
*
* @Date : 2016年1月26日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2016年1月26日    DELL    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/
	
package com.sgm.dms.fol.bankTicket.domain;

import java.math.BigDecimal;

import com.sgm.dms.fol.common.service.domains.BasePo;

/*
*
* @author DELL
* TODO description
* @date 2016年1月26日
*/

public class InterestRateHisPo extends BasePo{
    private Long id;
    private BigDecimal annualInterestRate;
    private BigDecimal discountRate;
    private BigDecimal addedTaxRate;
    private Integer status;
    private String remark;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public BigDecimal getAnnualInterestRate() {
        return annualInterestRate;
    }
    
    public void setAnnualInterestRate(BigDecimal annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }
    
    public BigDecimal getDiscountRate() {
        return discountRate;
    }
    
    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }
    
    public BigDecimal getAddedTaxRate() {
        return addedTaxRate;
    }
    
    public void setAddedTaxRate(BigDecimal addedTaxRate) {
        this.addedTaxRate = addedTaxRate;
    }
    
    public Integer getStatus() {
        return status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
    
    public String getRemark() {
        return remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }

    
}
