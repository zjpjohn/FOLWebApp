/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : bankTicket.api
*
* @File name : BankTicketAuditFlowDTO.java
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
	
package com.sgm.dms.fol.bankTicket.dto;

import java.util.Date;

import com.sgm.dms.fol.common.api.domain.BaseDTO;

/*
*
* @author DELL
* TODO description
* @date 2016年1月6日
*/

public class BankTicketAuditFlowDTO extends BaseDTO{
    private Long id;//主键
    private Long referTableId;//引用表的主键
    private Integer businessCode;//业务代码
    private Integer auditStatus;//审核状态
    private String auditMsg;//审核意见
    private Date auditDate;//审核日期
    private Integer status;//有效性
    private String remark;//备注
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getReferTableId() {
        return referTableId;
    }
    
    public void setReferTableId(Long referTableId) {
        this.referTableId = referTableId;
    }
    
    public Integer getBusinessCode() {
        return businessCode;
    }
    
    public void setBusinessCode(Integer businessCode) {
        this.businessCode = businessCode;
    }
    
    public Integer getAuditStatus() {
        return auditStatus;
    }
    
    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }
    
    public String getAuditMsg() {
        return auditMsg;
    }
    
    public void setAuditMsg(String auditMsg) {
        this.auditMsg = auditMsg;
    }
    
    public Date getAuditDate() {
        return auditDate;
    }
    
    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
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
