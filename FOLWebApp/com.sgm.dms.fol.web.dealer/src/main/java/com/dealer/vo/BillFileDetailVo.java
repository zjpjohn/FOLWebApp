/*
* Copyright 2015 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : web.sgm
*
* @File name : BillFileDetailVo.java
*
* @Author : ZhangBao
*
* @Date : 2015年11月3日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2015年11月3日    ZhangBao    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/
	
package com.dealer.vo;

import java.util.Date;

/*
 *
 * @author ZhangBao
 * TODO description
 * @date 2015年11月3日
 */

public class BillFileDetailVo {
	private Integer id;//文件ID
	private String dealerCode;//客户代码
	private String dealerName;//客户名称
	private String currentDate;
	private Date billDate;
	private Date  yearMon;//year+month
	private String token;
	private String filedId;
	private int beginNo;
	private int endNo;
	
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
	 * @return the year
	 */
	
	
	/**
	 * @return the yearMon
	 */
	public Date getYearMon() {
		return yearMon;
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
	 * @param yearMon the yearMon to set
	 */
	public void setYearMon(Date yearMon) {
		this.yearMon = yearMon;
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
	public String getFiledId() {
		return filedId;
	}
	public void setFiledId(String filedId) {
		this.filedId = filedId;
	}
	
	

}
