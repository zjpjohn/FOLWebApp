/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : returnallowance.service
*
* @File name : ReturnAllowancePO.java
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
	
package com.sgm.dms.fol.returnallowance.domain;

import java.math.BigDecimal;
import java.util.Date;

/*
*
* @author st78sr
* TODO description
* @date 2016年8月1日
*/

public class ReturnAllowancePO {
    private Long id; //主键ID
    private String allowanceNo; //折让单号
    private Long claimReturnOrderId; //退货证明ID
    private String returnOrderNo;//退货证明单号
    private String filedId;//文件fileId
    @Deprecated
    private String fieldId; //XML附件fileId
    private String sapCode; //SAP代码
    private BigDecimal linetotal; //不含税金额
    private BigDecimal tax; //税额
    private String reqBillNo; //红票申请单号
    private String expressNo; //快递单号
    private Date expressSendTime; //邮寄时间
    private String phone; //电话
    private String bankAccount; //银行账户
    private String companyName; //公司名称
    private String address; //公司地址
    private Date applyDate; //申请时间
    private Integer applicant; //申请人
    private String customerRemark; //客户备注
    private Date reportDate; //上报时间
    private Integer reporter; //上报人
    private Integer status; //处理状态
    private Date approveDate; //受理日期
    private Integer approveMan; //审核人
    private String approveMsg; //受理意见
    private String remark; //备注
    private Integer valid; //是否有效：0.无效 1.有效
    private Integer createBy; //创建人
    private Date createDate; //创建日期
    private Integer updateBy; //更新人
    private Date updateDate; //更新日期
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAllowanceNo() {
		return allowanceNo;
	}
	public void setAllowanceNo(String allowanceNo) {
		this.allowanceNo = allowanceNo;
	}
	public Long getClaimReturnOrderId() {
		return claimReturnOrderId;
	}
	public void setClaimReturnOrderId(Long claimReturnOrderId) {
		this.claimReturnOrderId = claimReturnOrderId;
	}
	public String getReturnOrderNo() {
		return returnOrderNo;
	}
	public void setReturnOrderNo(String returnOrderNo) {
		this.returnOrderNo = returnOrderNo;
	}
	public String getFiledId() {
		return filedId;
	}
	public void setFiledId(String filedId) {
		this.filedId = filedId;
	}
	public String getFieldId() {
		return fieldId;
	}
	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}
	public String getSapCode() {
		return sapCode;
	}
	public void setSapCode(String sapCode) {
		this.sapCode = sapCode;
	}
	public BigDecimal getLinetotal() {
		return linetotal;
	}
	public void setLinetotal(BigDecimal linetotal) {
		this.linetotal = linetotal;
	}
	public BigDecimal getTax() {
		return tax;
	}
	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}
	public String getReqBillNo() {
		return reqBillNo;
	}
	public void setReqBillNo(String reqBillNo) {
		this.reqBillNo = reqBillNo;
	}
	public String getExpressNo() {
		return expressNo;
	}
	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}
	public Date getExpressSendTime() {
		return expressSendTime;
	}
	public void setExpressSendTime(Date expressSendTime) {
		this.expressSendTime = expressSendTime;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	public Integer getApplicant() {
		return applicant;
	}
	public void setApplicant(Integer applicant) {
		this.applicant = applicant;
	}
	public String getCustomerRemark() {
		return customerRemark;
	}
	public void setCustomerRemark(String customerRemark) {
		this.customerRemark = customerRemark;
	}
	public Date getReportDate() {
		return reportDate;
	}
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}
	public Integer getReporter() {
		return reporter;
	}
	public void setReporter(Integer reporter) {
		this.reporter = reporter;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getApproveDate() {
		return approveDate;
	}
	public void setApproveDate(Date approveDate) {
		this.approveDate = approveDate;
	}
	public Integer getApproveMan() {
		return approveMan;
	}
	public void setApproveMan(Integer approveMan) {
		this.approveMan = approveMan;
	}
	public String getApproveMsg() {
		return approveMsg;
	}
	public void setApproveMsg(String approveMsg) {
		this.approveMsg = approveMsg;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getValid() {
		return valid;
	}
	public void setValid(Integer valid) {
		this.valid = valid;
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
