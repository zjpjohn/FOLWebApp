
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : web.dealer
 *
 * @File name : ReturnAllowanceInfoVo.java
 *
 * @Author : wangfl
 *
 * @Date : 2016年8月18日
 *
 ----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2016年8月18日    wangfl    1.0
 *
 *
 *
 *
 ----------------------------------------------------------------------------------
 */
	
package com.sgm.dms.fol.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgm.dms.fol.common.api.domain.BaseVo;

/**
 * 折让单信息Vo
 * @author wangfl
 * @date 2016年8月18日
 */

public class ReturnAllowanceInfoVo extends BaseVo{
	private Long id; //折让单主键ID
	private String allowanceNo; //折让单号
	private String reqBillNo; //红票申请单号
	private String expressNo; //快递单号
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date expressSendTime; //邮寄时间
	private String phone; //电话
	private String bankAccount; //银行账户
	private String address; //公司地址
	private String customerRemark; //客户备注
	private String companyName; //公司名称
	
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCustomerRemark() {
		return customerRemark;
	}
	public void setCustomerRemark(String customerRemark) {
		this.customerRemark = customerRemark;
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
	
	
}
