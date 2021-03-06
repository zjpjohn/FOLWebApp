/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : web.sgm
*
* @File name : ExceptionHandingVo.java
*
* @Author : DELL
*
* @Date : 2016年1月22日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2016年1月22日    DELL    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/
	
package com.sgm.dms.fol.vo;

import com.sgm.dms.fol.common.api.domain.PageVo;

/*
*
* @author DELL
* TODO description
* @date 2016年1月22日
*/

public class ExceptionHandingVo extends PageVo implements java.io.Serializable{

    private static final long serialVersionUID = -1676641338485100348L;

    private Long id;
    private Long tsId;
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
    
    public Long getTsId() {
        return tsId;
    }
    
    public void setTsId(Long tsId) {
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
