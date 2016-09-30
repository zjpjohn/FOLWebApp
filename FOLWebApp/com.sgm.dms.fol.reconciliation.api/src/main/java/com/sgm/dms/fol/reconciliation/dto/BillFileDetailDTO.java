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
 * 小文件DTO
 * @date 2015年11月3日
 */

public class BillFileDetailDTO {
	private long id;//主键id
	private String title;//标题
	private String dealerCode;//客户代码
	private String dealerName;//客户姓名
	private long  parentId;//父文件ID;
	private long  status;//状态 1:已签收0:未签收
	private Date  createDate;//创建日期
	private long createBy;//创建人
	private Date  updateDate;//更新日期
	private long updateBy;//更新人
	private String billDate;//接收 文件账单日期
	private String effDate;
	private Date effectiveDate;//生效日期
	private int beginNo;//开始条数
	private int endNo;//结束条数
	private String fileUrl;
	private String filedId;//文件编号
	private Date dbBillDate;//保存到数据库
	private String statusName;//签收 名称
	private long isDel;//是否有效 0 有效 1 无效
	private long isConfirm;//是否确认
	private String confirmName;
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
	 * @return the parentId
	 */
	public long getParentId() {
		return parentId;
	}
	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(long parentId) {
		this.parentId = parentId;
	}
	
	/**
	 * @return the status
	 */
	public long getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(long status) {
		this.status = status;
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
	 * @return the isDel
	 */
	public long getIsDel() {
		return isDel;
	}
	/**
	 * @param isDel the isDel to set
	 */
	public void setIsDel(long isDel) {
		this.isDel = isDel;
	}
	/**
	 * @return the isConfirm
	 */
	public long getIsConfirm() {
		return isConfirm;
	}
	/**
	 * @param isConfirm the isConfirm to set
	 */
	public void setIsConfirm(long isConfirm) {
		this.isConfirm = isConfirm;
	}
	/**
	 * @return the confirmName
	 */
	public String getConfirmName() {
		return confirmName;
	}
	/**
	 * @param confirmName the confirmName to set
	 */
	public void setConfirmName(String confirmName) {
		this.confirmName = confirmName;
	}
	
	
}
