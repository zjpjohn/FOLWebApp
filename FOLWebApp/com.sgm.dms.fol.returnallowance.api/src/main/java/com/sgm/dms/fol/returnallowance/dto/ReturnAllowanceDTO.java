/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : returnallowance.service
*
* @File name : ReturnAllowanceDTO.java
*
* @Author : st78sr
*
* @Date : 2016年8月1日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2016年8月1日    st78sr    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/

package com.sgm.dms.fol.returnallowance.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.sgm.dms.fol.common.api.domain.BaseDTO;

/*
*
* @author st78sr
* TODO description
* @date 2016年8月1日
*/

public class ReturnAllowanceDTO extends BaseDTO implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private Integer id; //主键ID
    private String allowanceNo; //折让单号
    private Long claimReturnOrderId; //退货证明ID
    private String returnOrderNo;//退货证明单号
    private String filedId; //XML附件fileId
    private String sapCode; //SAP代码，前端传过来是逗号分隔的，如2100011,2100022
    private List<String> sapCodeList; //SAP代码List
    private BigDecimal lineTotal; //不含税金额
    private BigDecimal tax; //税额
    private String reqBillNo; //红票申请单号
    private String expressNo; //快递单号
    private Date expressSendTime; //邮寄时间
    private String phone; //电话
    private String bankAccount; //银行账户
    private String companyName; //公司名称
    private String address; //公司地址
    private Date applyDate; //申请时间
    private String applyDateDisplay; //
    private Date applyDateStart; //申请时间-结束日期
    private Date applyDateEnd; //申请时间-开始日期
    private Integer applicant; //申请人
    private String applicantName; //申请人姓名
    private String customerRemark; //客户备注
    private Date reportDate; //上报时间
    private String reportDateDisplay; //
    private Integer reporter; //上报人
    private String reporterName;//上报人姓名
    private Integer status; //处理状态，code
    private String statusDesc; //处理状态，描述
    private Date approveDate; //受理日期
    private String approveDateDisplay; //
    private Date approveDateStart; //受理日期-结束日期
    private Date approveDateEnd; //受理日期-开始日期
    private Integer approveMan; //审核人
    private String approveManName;//审核人姓名
    private String approveMsg; //受理意见
    private String remark; //备注
    private Integer valid; //是否有效：0.无效 1.有效
    private String dealerName; //经销商简称
    private List<String> companyCode; //公司代码
    private List<Integer> statusList;
    private String billingReverseNo;//索赔电子发票号
    private String billingNo;//销售电子发票号
    private String sellerName;//销方名称
    
    private Integer beginNo; //开始行
    private Integer endNo; //结束行
    
    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }
    
    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }
    
    /**
     * @return the allowanceNo
     */
    public String getAllowanceNo() {
        return allowanceNo;
    }
    public String getReturnOrderNo() {
		return returnOrderNo;
	}

	public void setReturnOrderNo(String returnOrderNo) {
		this.returnOrderNo = returnOrderNo;
	}

	/**
     * @param allowanceNo the allowanceNo to set
     */
    public void setAllowanceNo(String allowanceNo) {
        this.allowanceNo = allowanceNo;
    }
    
    public Long getClaimReturnOrderId() {
		return claimReturnOrderId;
	}

	public void setClaimReturnOrderId(Long claimReturnOrderId) {
		this.claimReturnOrderId = claimReturnOrderId;
	}

	public String getFiledId() {
		return filedId;
	}

	public void setFiledId(String filedId) {
		this.filedId = filedId;
	}

	/**
     * @return the sapCode
     */
    public String getSapCode() {
        return sapCode;
    }
    
    /**
     * @param sapCode the sapCode to set
     */
    public void setSapCode(String sapCode) {
        this.sapCode = sapCode;
    }
    
    /**
     * @return the lineTotal
     */
    public BigDecimal getLineTotal() {
        return lineTotal;
    }
    
    /**
     * @param lineTotal the lineTotal to set
     */
    public void setLineTotal(BigDecimal lineTotal) {
        this.lineTotal = lineTotal;
    }
    
    /**
     * @return the tax
     */
    public BigDecimal getTax() {
        return tax;
    }
    
    /**
     * @param tax the tax to set
     */
    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }
    
    /**
     * @return the reqBillNo
     */
    public String getReqBillNo() {
        return reqBillNo;
    }
    
    /**
     * @param reqBillNo the reqBillNo to set
     */
    public void setReqBillNo(String reqBillNo) {
        this.reqBillNo = reqBillNo;
    }
    
    /**
     * @return the expressNo
     */
    public String getExpressNo() {
        return expressNo;
    }
    
    /**
     * @param expressNo the expressNo to set
     */
    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }
    
    /**
     * @return the expressSendTime
     */
    public Date getExpressSendTime() {
        return expressSendTime;
    }
    
    /**
     * @param expressSendTime the expressSendTime to set
     */
    public void setExpressSendTime(Date expressSendTime) {
        this.expressSendTime = expressSendTime;
    }
    
    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }
    
    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    /**
     * @return the bankAccount
     */
    public String getBankAccount() {
        return bankAccount;
    }
    
    /**
     * @param bankAccount the bankAccount to set
     */
    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }
    
    /**
     * @return the companyName
     */
    public String getCompanyName() {
        return companyName;
    }
    
    /**
     * @param companyName the companyName to set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }
    
    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }
    
    /**
     * @return the applyDate
     */
    public Date getApplyDate() {
        return applyDate;
    }
    
    /**
     * @param applyDate the applyDate to set
     */
    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }
    
    /**
     * @return the applyDateStart
     */
    public Date getApplyDateStart() {
        return applyDateStart;
    }
    
    /**
     * @param applyDateStart the applyDateStart to set
     */
    public void setApplyDateStart(Date applyDateStart) {
        this.applyDateStart = applyDateStart;
    }
    
    /**
     * @return the applyDateEnd
     */
    public Date getApplyDateEnd() {
        return applyDateEnd;
    }
    
    /**
     * @param applyDateEnd the applyDateEnd to set
     */
    public void setApplyDateEnd(Date applyDateEnd) {
        this.applyDateEnd = applyDateEnd;
    }
    
    /**
     * @return the applicant
     */
    public Integer getApplicant() {
        return applicant;
    }
    
    /**
     * @param applicant the applicant to set
     */
    public void setApplicant(Integer applicant) {
        this.applicant = applicant;
    }
    
    /**
     * @return the customerRemark
     */
    public String getCustomerRemark() {
        return customerRemark;
    }
    
    /**
     * @param customerRemark the customerRemark to set
     */
    public void setCustomerRemark(String customerRemark) {
        this.customerRemark = customerRemark;
    }
    
    /**
     * @return the reportDate
     */
    public Date getReportDate() {
        return reportDate;
    }
    
    /**
     * @param reportDate the reportDate to set
     */
    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }
    
    /**
     * @return the reporter
     */
    public Integer getReporter() {
        return reporter;
    }
    
    /**
     * @param reporter the reporter to set
     */
    public void setReporter(Integer reporter) {
        this.reporter = reporter;
    }
    
    /**
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }
    
    /**
     * @param status the status to set
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
    
    /**
     * @return the approveDate
     */
    public Date getApproveDate() {
        return approveDate;
    }
    
    /**
     * @param approveDate the approveDate to set
     */
    public void setApproveDate(Date approveDate) {
        this.approveDate = approveDate;
    }
    
    /**
     * @return the approveDateStart
     */
    public Date getApproveDateStart() {
        return approveDateStart;
    }
    
    /**
     * @param approveDateStart the approveDateStart to set
     */
    public void setApproveDateStart(Date approveDateStart) {
        this.approveDateStart = approveDateStart;
    }
    
    /**
     * @return the approveDateEnd
     */
    public Date getApproveDateEnd() {
        return approveDateEnd;
    }
    
    /**
     * @param approveDateEnd the approveDateEnd to set
     */
    public void setApproveDateEnd(Date approveDateEnd) {
        this.approveDateEnd = approveDateEnd;
    }
    
    /**
     * @return the approveMan
     */
    public Integer getApproveMan() {
        return approveMan;
    }
    
    /**
     * @param approveMan the approveMan to set
     */
    public void setApproveMan(Integer approveMan) {
        this.approveMan = approveMan;
    }
    
    /**
     * @return the approveMsg
     */
    public String getApproveMsg() {
        return approveMsg;
    }
    
    /**
     * @param approveMsg the approveMsg to set
     */
    public void setApproveMsg(String approveMsg) {
        this.approveMsg = approveMsg;
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
    public Integer getValid() {
        return valid;
    }
    
    /**
     * @param valid the valid to set
     */
    public void setValid(Integer valid) {
        this.valid = valid;
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
    
    
    public List<String> getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(List<String> companyCode) {
		this.companyCode = companyCode;
	}

	/**
     * @return the beginNo
     */
    public Integer getBeginNo() {
        return beginNo;
    }
    
    /**
     * @param beginNo the beginNo to set
     */
    public void setBeginNo(Integer beginNo) {
        this.beginNo = beginNo;
    }
    
    /**
     * @return the endNo
     */
    public Integer getEndNo() {
        return endNo;
    }
    
    /**
     * @param endNo the endNo to set
     */
    public void setEndNo(Integer endNo) {
        this.endNo = endNo;
    }
    
    /**
     * @return the serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    
    /**
     * @return the applyDateDisplay
     */
    public String getApplyDateDisplay() {
        return applyDateDisplay;
    }

    
    /**
     * @param applyDateDisplay the applyDateDisplay to set
     */
    public void setApplyDateDisplay(String applyDateDisplay) {
        this.applyDateDisplay = applyDateDisplay;
    }

    
    /**
     * @return the reportDateDisplay
     */
    public String getReportDateDisplay() {
        return reportDateDisplay;
    }

    
    /**
     * @param reportDateDisplay the reportDateDisplay to set
     */
    public void setReportDateDisplay(String reportDateDisplay) {
        this.reportDateDisplay = reportDateDisplay;
    }

    
    /**
     * @return the approveDateDisplay
     */
    public String getApproveDateDisplay() {
        return approveDateDisplay;
    }

    
    /**
     * @param approveDateDisplay the approveDateDisplay to set
     */
    public void setApproveDateDisplay(String approveDateDisplay) {
        this.approveDateDisplay = approveDateDisplay;
    }

    
    /**
     * @return the sapCodeList
     */
    public List<String> getSapCodeList() {
        return sapCodeList;
    }

    
    /**
     * @param sapCodeList the sapCodeList to set
     */
    public void setSapCodeList(List<String> sapCodeList) {
        this.sapCodeList = sapCodeList;
    }

    
    /**
     * @return the statusDesc
     */
    public String getStatusDesc() {
        return statusDesc;
    }

    
    /**
     * @param statusDesc the statusDesc to set
     */
    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public String getReporterName() {
		return reporterName;
	}

	public void setReporterName(String reporterName) {
		this.reporterName = reporterName;
	}

	public String getApproveManName() {
		return approveManName;
	}

	public void setApproveManName(String approveManName) {
		this.approveManName = approveManName;
	}

	public List<Integer> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<Integer> statusList) {
		this.statusList = statusList;
	}

	public String getBillingReverseNo() {
		return billingReverseNo;
	}

	public void setBillingReverseNo(String billingReverseNo) {
		this.billingReverseNo = billingReverseNo;
	}

	public String getBillingNo() {
		return billingNo;
	}

	public void setBillingNo(String billingNo) {
		this.billingNo = billingNo;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
    
}
