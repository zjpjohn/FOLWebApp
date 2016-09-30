/*
* Copyright 2015 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : web.sgm
*
* @File name : ExportBillVo.java
*
* @Author : ZhangBao
*
* @Date : 2015年11月12日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2015年11月12日    ZhangBao    1.0
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
 * @date 2015年11月12日
 */

public class ExportBillDRO {
	
	private int num;//序号
	private String title;//标题
	private Date dbBillDate;//账单年月
	private Date effectiveDate;//生效日期
//	private String statusName;//是否发布
	private String status;
//	private String confirmName;//是否确认
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
//	public String getStatusName() {
//		return statusName;
//	}
//	public void setStatusName(String statusName) {
//		this.statusName = statusName;
//	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
//	public String getConfirmName() {
//		return confirmName;
//	}
//	public void setConfirmName(String confirmName) {
//		this.confirmName = confirmName;
//	}	

}
