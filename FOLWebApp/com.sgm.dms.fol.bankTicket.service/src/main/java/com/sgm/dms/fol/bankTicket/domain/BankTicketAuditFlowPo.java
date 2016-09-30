/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : bankTicket.service
*
* @File name : BankTicketAuditFlowPo.java
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

/*
*
* @author DELL
* TODO description
* @date 2016年1月6日
*/

public class BankTicketAuditFlowPo {
    private Long id;//主键
    private Long referTableId;//引用表的主键
    private Integer businessCode;//业务代码
    private Integer auditStatus;//审核状态
    private String auditMsg;//审核意见
    private Date auditDate;//审核日期
    private Integer statue;//有效性
    private String remark;//备注
    private Integer createBy;//创建者
    private Date createDate;//创建时间
    private Integer updateBy;//更新者
    private Date updateDate;//更新时间
    
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
    
    public Integer getStatue() {
        return statue;
    }
    
    public void setStatue(Integer statue) {
        this.statue = statue;
    }
    
    public String getRemark() {
        return remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    public Integer getCreateBy() {
        return createBy;
    }
    
    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }
    
    public Date getCreateDate() {
        return createDate;
    }
    
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
    public Integer getUpdateBy() {
        return updateBy;
    }
    
    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }
    
    public Date getUpdateDate() {
        return updateDate;
    }
    
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    
    
}
