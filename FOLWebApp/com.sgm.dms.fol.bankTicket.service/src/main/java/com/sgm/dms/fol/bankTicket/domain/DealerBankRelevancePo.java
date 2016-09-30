/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : bankTicket.api
*
* @File name : DealerBankRelevanceDTO.java
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

import com.sgm.dms.fol.common.service.domains.BasePo;

/*
*
* @author DELL
* TODO description
* @date 2016年1月6日
*/

public class DealerBankRelevancePo extends BasePo{
    private Long id;//主键
    private Integer dealerType;//渠道类型
    private String dealerName;//经销商名称
    private String sapCode;//SAP代码
    private Long bankId;//银行ID(银行信息表的主键id)
    private Integer auditStatus;//审批状态(1.新增待审批,2.删除待审批,3,新增驳回,4.删除驳回, 5.审批通过)
    private Integer status;//有效性
    private String remark;//备注
    
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
    
    
    public String getDealerName() {
        return dealerName;
    }

    
    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    
    public String getSapCode() {
        return sapCode;
    }

    
    public void setSapCode(String sapCode) {
        this.sapCode = sapCode;
    }
    
    public Long getBankId() {
        return bankId;
    }
    
    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }
    
    public Integer getAuditStatus() {
        return auditStatus;
    }
    
    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
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
