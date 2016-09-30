/*
* Copyright 2015 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : web.sgm
*
* @File name : ExportBillDetailVo.java
*
* @Author : ZhangBao
*
* @Date : 2015年11月13日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2015年11月13日    ZhangBao    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/
	
package com.sgm.dms.fol.dro;

import java.util.Date;

/*
 *
 * @author ZhangBao
 * TODO description
 * @date 2015年11月13日
 */

public class ExportBillDetailDRO {
	private int num;//序号
	private String dealerCode;//客户代码
	private String dealerName;//客户名称
	private String title;//标题
	private Date dbBillDate;//账单年月
	private Date effectiveDate;//生效日期
	private String status;//是否发布
	private String confirmName;//是否确认
	/**
	 * @return the num
	 */
	public int getNum() {
		return num;
	}
	/**
	 * @param num the num to set
	 */
	public void setNum(int num) {
		this.num = num;
	}
	/**
	 * @return the dealerCode
	 */
	public String getDealerCode() {
		return dealerCode;
	}
	/**
	 * @param dealerCode the dealerCode to set
	 */
	public void setDealerCode(String dealerCode) {
		this.dealerCode = dealerCode;
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
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the dbBillDate
	 */
	public Date getDbBillDate() {
		return dbBillDate;
	}
	/**
	 * @param dbBillDate the dbBillDate to set
	 */
	public void setDbBillDate(Date dbBillDate) {
		this.dbBillDate = dbBillDate;
	}
	/**
	 * @return the effectiveDate
	 */
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	/**
	 * @param effectiveDate the effectiveDate to set
	 */
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	public String getConfirmName() {
		return confirmName;
	}
	public void setConfirmName(String confirmName) {
		this.confirmName = confirmName;
	}
	
}
