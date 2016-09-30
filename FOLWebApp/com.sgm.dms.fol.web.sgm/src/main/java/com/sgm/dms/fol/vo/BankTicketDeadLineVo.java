/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : web.sgm
*
* @File name : BankTicketDeadLineVo.java
*
* @Author : DELL
*
* @Date : 2016年1月11日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2016年1月11日    DELL    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/
	
package com.sgm.dms.fol.vo;

import java.util.Date;

import com.sgm.dms.fol.common.api.domain.PageVo;

/*
*
* @author DELL
* TODO description
* @date 2016年1月11日
*/

public class BankTicketDeadLineVo extends PageVo implements java.io.Serializable{
    private static final long serialVersionUID = 2972294234873269941L;
    
    private String encryptId;
    private Integer dealerType;//渠道类型
    private Integer brandId;//品牌
    private String dealerTypeName;
    private String brandName;
    private String sapCode;
    private String dealerName;
    private Integer deadlineDay;//票据天数
    private Integer freePeriodDay;//免息期（天）
    private Date effectDate;//起效日
    private Date expireDate;//到期日
    private String effectDateDisplay;
    private String expireDateDisplay;
    private Integer auditStatus;
    private String auditStatusName;
    private String auditMsg;
    
    public Integer getDealerType() {
        return dealerType;
    }
    
    public void setDealerType(Integer dealerType) {
        this.dealerType = dealerType;
    }
    
    public String getSapCode() {
        return sapCode;
    }
    
    public void setSapCode(String sapCode) {
        this.sapCode = sapCode;
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
    
    public Date getExpireDate() {
        return expireDate;
    }
    
    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public String getEncryptId() {
        return encryptId;
    }

    public void setEncryptId(String encryptId) {
        this.encryptId = encryptId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    
    public String getDealerTypeName() {
        return dealerTypeName;
    }

    
    public void setDealerTypeName(String dealerTypeName) {
        this.dealerTypeName = dealerTypeName;
    }

    
    public String getBrandName() {
        return brandName;
    }

    
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    
    public String getDealerName() {
        return dealerName;
    }

    
    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    
    public String getAuditStatusName() {
        return auditStatusName;
    }

    
    public void setAuditStatusName(String auditStatusName) {
        this.auditStatusName = auditStatusName;
    }

    
    public String getAuditMsg() {
        return auditMsg;
    }

    
    public void setAuditMsg(String auditMsg) {
        this.auditMsg = auditMsg;
    }

    
    public String getEffectDateDisplay() {
        return effectDateDisplay;
    }
    public void setEffectDateDisplay(String effectDateDisplay) {
        this.effectDateDisplay = effectDateDisplay;
    }

    
    public String getExpireDateDisplay() {
        return expireDateDisplay;
    }

    
    public void setExpireDateDisplay(String expireDateDisplay) {
        this.expireDateDisplay = expireDateDisplay;
    }

	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}
    
}
