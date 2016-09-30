/*
* Copyright 2015 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : web.sgm
*
* @File name : BillFileVo.java
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
	
package com.sgm.dms.fol.vo;

import java.util.Date;

/*
 *
 * @author ZhangBao
 * TODO description
 * @date 2015年11月13日
 */

public class BillFileVo {
	private Integer id;//文件ID
	private String currentDate;
	private Date billDate;
	private Date  yearMon;//year+month
	private String token;
	private int beginNo;
	private int endNo;
	private String filedId;
	private String fileName;
	private Date effectiveDate;
	private Date dbBillDate;

	
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
	 * @return the currentDate
	 */
	public String getCurrentDate() {
		return currentDate;
	}
	/**
	 * @param currentDate the currentDate to set
	 */
	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}
	/**
	 * @return the billDate
	 */
	public Date getBillDate() {
		return billDate;
	}
	/**
	 * @param billDate the billDate to set
	 */
	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}
	/**
	 * @return the yearMon
	 */
	public Date getYearMon() {
		return yearMon;
	}
	/**
	 * @param yearMon the yearMon to set
	 */
	public void setYearMon(Date yearMon) {
		this.yearMon = yearMon;
	}
	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}
	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}
	/**
	 * @return the beginNo
	 */
	public int getBeginNo() {
		return beginNo;
	}
	/**
	 * @param beginNo the beginNo to set
	 */
	public void setBeginNo(int beginNo) {
		this.beginNo = beginNo;
	}
	/**
	 * @return the endNo
	 */
	public int getEndNo() {
		return endNo;
	}
	/**
	 * @param endNo the endNo to set
	 */
	public void setEndNo(int endNo) {
		this.endNo = endNo;
	}
	/**
	 * @return the filedId
	 */
	public String getFiledId() {
		return filedId;
	}
	/**
	 * @param filedId the filedId to set
	 */
	public void setFiledId(String filedId) {
		this.filedId = filedId;
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
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
