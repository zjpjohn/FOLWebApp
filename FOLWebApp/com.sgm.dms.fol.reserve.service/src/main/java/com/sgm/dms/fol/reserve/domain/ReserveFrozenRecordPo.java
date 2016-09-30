/*
* Copyright 2015 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : reserve.service
*
* @File name : ReserveFrozenRecordPo.java
*
* @Author : shenweiwei
*
* @Date : 2015年10月19日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2015年10月19日    shenweiwei    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/
	
package com.sgm.dms.fol.reserve.domain;

import java.math.BigDecimal;
import java.sql.Date;

import com.sgm.dms.fol.common.service.domains.BasePo;


/*
 *
 * @author shenweiwei
 * TODO description
 * @date 2015年10月19日
 */

public class ReserveFrozenRecordPo extends BasePo{
    private Long id;
    private Long tsId;
    private String dealerCode;
    private String dealerName;
    private BigDecimal beforeFreezeAmount;
    private BigDecimal freezeAmount;
    private BigDecimal afterFreezeAmount;
    private Date lastFreezeTime;
    private String referCode;
    private Long referType;
    private String remark;
    
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }
    
    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * @return the tsId
     */
    public Long getTsId() {
        return tsId;
    }
    
    /**
     * @param tsId the tsId to set
     */
    public void setTsId(Long tsId) {
        this.tsId = tsId;
    }
    
    /**
     * @return the dealerCode
     */
    public String getDealerCode() {
        return dealerCode;
    }
    
    /**
     * @param dealerCode the dealerCode to set
     */
    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }
    
    /**
     * @return the dealerName
     */
    public String getDealerName() {
        return dealerName;
    }
    
    /**
     * @param dealerName the dealerName to set
     */
    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }
    
    /**
     * @return the beforeFreezeAmount
     */
    public BigDecimal getBeforeFreezeAmount() {
        return beforeFreezeAmount;
    }
    
    /**
     * @param beforeFreezeAmount the beforeFreezeAmount to set
     */
    public void setBeforeFreezeAmount(BigDecimal beforeFreezeAmount) {
        this.beforeFreezeAmount = beforeFreezeAmount;
    }
    
    /**
     * @return the freezeAmount
     */
    public BigDecimal getFreezeAmount() {
        return freezeAmount;
    }
    
    /**
     * @param freezeAmount the freezeAmount to set
     */
    public void setFreezeAmount(BigDecimal freezeAmount) {
        this.freezeAmount = freezeAmount;
    }
    
    /**
     * @return the afterFreezeAmount
     */
    public BigDecimal getAfterFreezeAmount() {
        return afterFreezeAmount;
    }
    
    /**
     * @param afterFreezeAmount the afterFreezeAmount to set
     */
    public void setAfterFreezeAmount(BigDecimal afterFreezeAmount) {
        this.afterFreezeAmount = afterFreezeAmount;
    }
    
    /**
     * @return the lastFreezeTime
     */
    public Date getLastFreezeTime() {
        return lastFreezeTime;
    }
    
    /**
     * @param lastFreezeTime the lastFreezeTime to set
     */
    public void setLastFreezeTime(Date lastFreezeTime) {
        this.lastFreezeTime = lastFreezeTime;
    }
    
    /**
     * @return the referCode
     */
    public String getReferCode() {
        return referCode;
    }
    
    /**
     * @param referCode the referCode to set
     */
    public void setReferCode(String referCode) {
        this.referCode = referCode;
    }
    
    /**
     * @return the referType
     */
    public Long getReferType() {
        return referType;
    }
    
    /**
     * @param referType the referType to set
     */
    public void setReferType(Long referType) {
        this.referType = referType;
    }
    
    /**
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }
    
    /**
     * @param remark the remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

}
