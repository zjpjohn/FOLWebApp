package com.sgm.dms.fol.bonus.dto;

import java.util.Date;

import com.sgm.dms.fol.common.api.domain.BaseDTO;
/****
 *奖金颁发结果实体
*
* @author ZhangBao
* TODO description
* @date 2015年12月15日
 */
public class BonusIssueResultDTO extends BaseDTO{
	private Integer issueRecordId;			//发放id
	private String issueErrMsgCode;//发放结果code
	private Date executeDate;//处理日期
	private Integer executeStatus;    //处理结果
	private String remark;      //备注
	
	
	
	
	public Integer getIssueRecordId() {
		return issueRecordId;
	}
	public void setIssueRecordId(Integer issueRecordId) {
		this.issueRecordId = issueRecordId;
	}
	public String getIssueErrMsgCode() {
		return issueErrMsgCode;
	}
	public void setIssueErrMsgCode(String issueErrMsgCode) {
		this.issueErrMsgCode = issueErrMsgCode;
	}
	public Date getExecuteDate() {
		return executeDate;
	}
	public void setExecuteDate(Date executeDate) {
		this.executeDate = executeDate;
	}
	public Integer getExecuteStatus() {
		return executeStatus;
	}
	public void setExecuteStatus(Integer executeStatus) {
		this.executeStatus = executeStatus;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
	
}
