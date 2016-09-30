
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : web.dealer
 *
 * @File name : AllowanceAddReqVo.java
 *
 * @Author : wangfl
 *
 * @Date : 2016年8月10日
 *
 ----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2016年8月10日    wangfl    1.0
 *
 *
 *
 *
 ----------------------------------------------------------------------------------
 */

package com.dealer.vo;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgm.dms.fol.returnallowance.restclient.dto.ReturnOrdersRespDto.PartClaimReturnOrderDto;

/**
 * 折让单新增请求Vo
 * 
 * @author wangfl
 * @date 2016年8月10日
 */

public class AllowanceAddReqVo {
	/**
	 * 折让单信息
	 */
	private Long id; // 折让单主键ID
	@NotBlank(message = "折让单号不能为空")
	private String allowanceNo; // 折让单号
	@NotBlank(message = "红票通知单号不能为空")
	private String reqBillNo; // 红票申请单号
	@NotBlank(message = "快递单号不能为空")
	private String expressNo; // 快递单号
	@NotNull(message = "邮寄时间不能为空")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date expressSendTime; // 邮寄时间
	@NotBlank(message = "电话不能为空")
	private String phone; // 电话
	@NotBlank(message = "银行账户不能为空")
	private String bankAccount; // 银行账户
	@NotBlank(message = "公司地址不能为空")
	private String address; // 公司地址
	@NotBlank(message = "客户备注不能为空")
	private String customerRemark; // 客户备注
	
	@NotBlank(message = "退货证明单号不能为空")
	private String returnOrderNo;// 退货证明单号
	//选中的折让单信息
	private PartClaimReturnOrderDto returnOrderInfo;
	
	@NotBlank(message = "XML必选")
	private String filedId;// 文件filedId

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

	public String getFiledId() {
		return filedId;
	}

	public void setFiledId(String filedId) {
		this.filedId = filedId;
	}

	public String getReturnOrderNo() {
		return returnOrderNo;
	}

	public void setReturnOrderNo(String returnOrderNo) {
		this.returnOrderNo = returnOrderNo;
	}

	public PartClaimReturnOrderDto getReturnOrderInfo() {
		return returnOrderInfo;
	}

	public void setReturnOrderInfo(PartClaimReturnOrderDto returnOrderInfo) {
		this.returnOrderInfo = returnOrderInfo;
	}
}
