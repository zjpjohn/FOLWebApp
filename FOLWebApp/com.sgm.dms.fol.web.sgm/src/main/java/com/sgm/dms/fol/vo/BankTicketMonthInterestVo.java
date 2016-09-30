/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : web.sgm
*
* @File name : BankTicketMonthInterestVo.java
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
	
package com.sgm.dms.fol.vo;

import java.math.BigDecimal;

import com.sgm.dms.fol.common.api.domain.PageVo;

/*
*
* @author DELL
* TODO description
* @date 2016年1月26日
*/

public class BankTicketMonthInterestVo extends PageVo implements java.io.Serializable{

    private static final long serialVersionUID = 5462124897034622404L;
    
    private Long id;
    private String ids;//选择的所有记录的id，每个id用逗号分隔。
    private String sapCode;
    private BigDecimal discountAmount;
    private BigDecimal countAmount;
    private BigDecimal addTaxAmount;
    private String year;
    private String month;
    private Integer issueStatus;
    private Integer comfirmStatus;
    private Integer status;
    private String remark;
    
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
    
    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }
    
    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }
    
    public BigDecimal getCountAmount() {
        return countAmount;
    }
    
    public void setCountAmount(BigDecimal countAmount) {
        this.countAmount = countAmount;
    }
    
    public BigDecimal getAddTaxAmount() {
        return addTaxAmount;
    }
    
    public void setAddTaxAmount(BigDecimal addTaxAmount) {
        this.addTaxAmount = addTaxAmount;
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
    
    public Integer getIssueStatus() {
        return issueStatus;
    }
    
    public void setIssueStatus(Integer issueStatus) {
        this.issueStatus = issueStatus;
    }
    
    public Integer getComfirmStatus() {
        return comfirmStatus;
    }
    
    public void setComfirmStatus(Integer comfirmStatus) {
        this.comfirmStatus = comfirmStatus;
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

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
    
}
