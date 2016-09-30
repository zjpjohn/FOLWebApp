/*
* Copyright 2015 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : common.api
*
* @File name : FileDTO.java
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
	
package com.sgm.dms.fol.reconciliation.dto;

import java.util.Date;

/*
 *
 * @author ZhangBao
 * 大文件DTO
 * @date 2015年11月3日
 */

public class BillFileDTO {
	
	private long id;//主键id
	private String fileName;//文件名
	private Date  createDate;//创建日期
	private long createBy;//创建人
	private Date  updateDate;//更新日期
	private long updateBy;//更新人
	private String fileUrl;
	private String filedId;//文件编号
	private Integer status;//发布状态
	private String statusName;//状态名称
	private String billDate;//接收 文件账单日期
	private String effDate;
	private Date effectiveDate;//生效日期
	private Date dbBillDate;
	private int beginNo;
	private int endNo;
	private String title;
	private String token;
	
	
	/**
	 * @return the effDate
	 */
	public String getEffDate() {
		return effDate;
	}
	/**
	 * @param effDate the effDate to set
	 */
	public void setEffDate(String effDate) {
		this.effDate = effDate;
	}
	/**
	 * @return the statusName
	 */
	public String getStatusName() {
		return statusName;
	}
	/**
	 * @param statusName the statusName to set
	 */
	public void setStatusName(String statusName) {
		this.statusName = statusName;
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
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * @return the createBy
	 */
	public long getCreateBy() {
		return createBy;
	}
	/**
	 * @param createBy the createBy to set
	 */
	public void setCreateBy(long createBy) {
		this.createBy = createBy;
	}
	/**
	 * @return the updateDate
	 */
	public Date getUpdateDate() {
		return updateDate;
	}
	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	/**
	 * @return the updateBy
	 */
	public long getUpdateBy() {
		return updateBy;
	}
	/**
	 * @param updateBy the updateBy to set
	 */
	public void setUpdateBy(long updateBy) {
		this.updateBy = updateBy;
	}
	/**
	 * @return the fileUrl
	 */
	public String getFileUrl() {
		return fileUrl;
	}
	/**
	 * @param fileUrl the fileUrl to set
	 */
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
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
	 * @return the billDate
	 */
	public String getBillDate() {
		return billDate;
	}
	/**
	 * @param billDate the billDate to set
	 */
	public void setBillDate(String billDate) {
		this.billDate = billDate;
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
	
	
	

}
