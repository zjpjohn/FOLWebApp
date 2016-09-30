/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : common.service
*
* @File name : ExceptionHandingPo.java
*
* @Author : DELL
*
* @Date : 2016年1月25日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2016年1月25日    DELL    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/
	
package com.sgm.dms.fol.common.service.domains;


/*
*
* @author DELL
* TODO description
* @date 2016年1月25日
*/

public class ExceptionHandingPo extends BasePo{
    private Long id;
    private String tsId;
    private String sapCode;
    private String dealerName;
    private String amount;
    private String disposeNo;
    private String disposeDesc;
    private Long disposeUser;
    private Long referType;
    private String referCode;
    private Integer status;
    private String remark;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTsId() {
        return tsId;
    }
    
    public void setTsId(String tsId) {
        this.tsId = tsId;
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
    
    public String getAmount() {
        return amount;
    }
    
    public void setAmount(String amount) {
        this.amount = amount;
    }
    
    public String getDisposeNo() {
        return disposeNo;
    }
    
    public void setDisposeNo(String disposeNo) {
        this.disposeNo = disposeNo;
    }
    
    public String getDisposeDesc() {
        return disposeDesc;
    }
    
    public void setDisposeDesc(String disposeDesc) {
        this.disposeDesc = disposeDesc;
    }
    
    public Long getDisposeUser() {
        return disposeUser;
    }
    
    public void setDisposeUser(Long disposeUser) {
        this.disposeUser = disposeUser;
    }
    
    public Long getReferType() {
        return referType;
    }
    
    public void setReferType(Long referType) {
        this.referType = referType;
    }
    
    public String getReferCode() {
        return referCode;
    }
    
    public void setReferCode(String referCode) {
        this.referCode = referCode;
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
