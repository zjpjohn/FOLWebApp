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
	
package com.sgm.dms.fol.bankTicket.dto;

import java.util.List;

import com.sgm.dms.fol.common.api.domain.BaseDTO;

/*
*
* @author DELL
* TODO description
* @date 2016年1月6日
*/

public class DealerBankRelevanceDTO extends BaseDTO{
    private Long id;//主键
    private Integer dealerType;//渠道类型
    private String dealerTypeName;//渠道类型名称
    private String dealerName;//经销商名称
    private Integer brand;//品牌
    private String brandName;//品牌名称
    private String sapCode;//SAP代码
    private String bankAbbr;//银行缩写
    private Long bankId;//银行ID(银行信息表的主键id)
    private String bankChName;
    private Integer auditStatus;//审批状态(1.新增待审批,2.删除待审批,3,新增驳回,4.删除驳回, 5.审批通过)
    private String auditStatusName;//审核状态名称
    private String auditMsg;//审核状态
    private Integer status;//有效性
    private String remark;//备注
    private Integer beginNo;
    private Integer endNo;
    private List<Integer> auditStatusList;
    private String encryptId;
    
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

    public Integer getBrand() {
        return brand;
    }

    public void setBrand(Integer brand) {
        this.brand = brand;
    }

    public String getBankAbbr() {
        return bankAbbr;
    }

    public void setBankAbbr(String bankAbbr) {
        this.bankAbbr = bankAbbr;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
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

    
    public String getBankChName() {
        return bankChName;
    }

    
    public void setBankChName(String bankChName) {
        this.bankChName = bankChName;
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

	public List<Integer> getAuditStatusList() {
		return auditStatusList;
	}

	public void setAuditStatusList(List<Integer> auditStatusList) {
		this.auditStatusList = auditStatusList;
	}

    
    public String getEncryptId() {
        return encryptId;
    }

    
    public void setEncryptId(String encryptId) {
        this.encryptId = encryptId;
    }
    
}
