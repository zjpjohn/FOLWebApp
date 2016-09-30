/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : returnallowance.api
*
* @File name : AllowanceInvoiceInfoDto.java
*
* @Author : st78sr
*
* @Date : 2016年8月23日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2016年8月23日    st78sr    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/
	
package com.sgm.dms.fol.returnallowance.dto;

import java.math.BigDecimal;
import java.util.Date;

/*
*
* @author st78sr
* TODO description
* @date 2016年8月23日
*/

public class AllowanceInvoiceInfoDto {
	private Long id;

    private Long returnOrderId;

    private String returnOrderNo;

    private String billingReverseNo;

    private String billingNo;

    private BigDecimal orderNetvalue;
    
    private String billingOrign;

    private String remark;

    private Short valid;

    private Long createBy;

    private Date createDate;

    private Long updateBy;

    private Date updateDate;

    
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
     * @return the returnOrderId
     */
    public Long getReturnOrderId() {
        return returnOrderId;
    }

    
    /**
     * @param returnOrderId the returnOrderId to set
     */
    public void setReturnOrderId(Long returnOrderId) {
        this.returnOrderId = returnOrderId;
    }

    
    /**
     * @return the returnOrderNo
     */
    public String getReturnOrderNo() {
        return returnOrderNo;
    }

    
    /**
     * @param returnOrderNo the returnOrderNo to set
     */
    public void setReturnOrderNo(String returnOrderNo) {
        this.returnOrderNo = returnOrderNo;
    }

    
    /**
     * @return the billingReverseNo
     */
    public String getBillingReverseNo() {
        return billingReverseNo;
    }

    
    /**
     * @param billingReverseNo the billingReverseNo to set
     */
    public void setBillingReverseNo(String billingReverseNo) {
        this.billingReverseNo = billingReverseNo;
    }

    
    /**
     * @return the billingNo
     */
    public String getBillingNo() {
        return billingNo;
    }

    
    /**
     * @param billingNo the billingNo to set
     */
    public void setBillingNo(String billingNo) {
        this.billingNo = billingNo;
    }

    
    /**
     * @return the orderNetvalue
     */
    public BigDecimal getOrderNetvalue() {
        return orderNetvalue;
    }

    
    /**
     * @param orderNetvalue the orderNetvalue to set
     */
    public void setOrderNetvalue(BigDecimal orderNetvalue) {
        this.orderNetvalue = orderNetvalue;
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

    
    /**
     * @return the valid
     */
    public Short getValid() {
        return valid;
    }

    
    /**
     * @param valid the valid to set
     */
    public void setValid(Short valid) {
        this.valid = valid;
    }

    
    /**
     * @return the createBy
     */
    public Long getCreateBy() {
        return createBy;
    }

    
    /**
     * @param createBy the createBy to set
     */
    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    
    /**
     * @return the createDate
     */
    public Date getCreateDate() {
        return createDate;
    }

    
    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    
    /**
     * @return the updateBy
     */
    public Long getUpdateBy() {
        return updateBy;
    }

    
    /**
     * @param updateBy the updateBy to set
     */
    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    
    /**
     * @return the updateDate
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    
    /**
     * @param updateDate the updateDate to set
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }


	public String getBillingOrign() {
		return billingOrign;
	}


	public void setBillingOrign(String billingOrign) {
		this.billingOrign = billingOrign;
	}
    
}
