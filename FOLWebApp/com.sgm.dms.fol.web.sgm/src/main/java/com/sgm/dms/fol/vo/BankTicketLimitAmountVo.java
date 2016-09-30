/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : web.sgm
*
* @File name : BankTicketLimitAmountVo.java
*
* @Author : DELL
*
* @Date : 2016年1月14日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2016年1月14日    DELL    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/
	
package com.sgm.dms.fol.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.sgm.dms.fol.common.api.domain.PageVo;

/*
*
* @author DELL
* TODO description
* @date 2016年1月14日
*/

public class BankTicketLimitAmountVo extends PageVo{
    private String encryptId;//主键
    private Integer dealerType;//渠道类型
    private String dealerTypeName;    
    private Integer brandId;//品牌
    private String brandName;
    private String amountLimit;//票据限额
    private Date effectDate;//起效日
    private String effectDateDisplay;
    private Date expireDate;//到期日
    private String expireDateDisplay;
    private String sapCode;//经销商代码
    private String dealerName;//经销商名称
    private Integer type;//普通还是特殊(1,普通,2,特殊)
    private Integer auditStatus;//审批状态
    private String auditStatusName;
    private String auditMsg;
    private Integer status;//备注
    private String remark;
    private Integer beginNo;
    private Integer endNo;
    private BigDecimal moneyDisplay;

    public String getEncryptId() {
        return encryptId;
    }

    public void setEncryptId(String encryptId) {
        this.encryptId = encryptId;
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
    
    public String getAmountLimit() {
        return amountLimit;
    }
    
    public void setAmountLimit(String amountLimit) {
        this.amountLimit = amountLimit;
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
    
    public Integer getType() {
        return type;
    }
    
    public void setType(Integer type) {
        this.type = type;
    }
    
    public Integer getAuditStatus() {
        return auditStatus;
    }
    
    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }
    
    public String getRemark() {
        return remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
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

    
    public Integer getStatus() {
        return status;
    }

    
    public void setStatus(Integer status) {
        this.status = status;
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

    
    public BigDecimal getMoneyDisplay() {
        return moneyDisplay;
    }

    
    public void setMoneyDisplay(BigDecimal moneyDisplay) {
        this.moneyDisplay = moneyDisplay;
    }
    
    
}
