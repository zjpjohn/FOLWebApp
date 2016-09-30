/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : bankTicket.api
*
* @File name : BankTicketDeadLineDTO.java
*
* @Author : DELL
*
* @Date : 2016年1月6日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2016年1月6日    DELL    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/
	
package com.sgm.dms.fol.bankTicket.domain;

import java.util.Date;
import java.util.List;

import com.sgm.dms.fol.common.service.domains.BasePo;

/*
*
* @author DELL
* TODO description
* @date 2016年1月6日
*/

public class BankTicketDeadLinePo extends BasePo{
    private Long id;
    private Integer dealerType;
    private Integer brandId;
    private Integer deadlineDay;
    private Integer freePeriodDay;
    private Date effectDate;
    private Date expireDate;
    private String sapCode;
    private String dealerName;
    private Integer type;
    private Integer auditStatus;
    private Integer status;
    private String remark;
    private Integer beginNo;
    private Integer endNo;
    private List<Integer> auditStatusStrs;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
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
    
    public Integer getDeadlineDay() {
        return deadlineDay;
    }
    
    public void setDeadlineDay(Integer deadlineDay) {
        this.deadlineDay = deadlineDay;
    }
    
    public Integer getFreePeriodDay() {
        return freePeriodDay;
    }
    
    public void setFreePeriodDay(Integer freePeriodDay) {
        this.freePeriodDay = freePeriodDay;
    }
    
    public Date getEffectDate() {
        return effectDate;
    }
    
    public void setEffectDate(Date effectDate) {
        this.effectDate = effectDate;
    }
    
    public Integer getType() {
        return type;
    }
    
    public void setType(Integer type) {
        this.type = type;
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

    public String getSapCode() {
        return sapCode;
    }

    public void setSapCode(String sapCode) {
        this.sapCode = sapCode;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
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

	public List<Integer> getAuditStatusStrs() {
		return auditStatusStrs;
	}

	public void setAuditStatusStrs(List<Integer> auditStatusStrs) {
		this.auditStatusStrs = auditStatusStrs;
	}
    
    
}
