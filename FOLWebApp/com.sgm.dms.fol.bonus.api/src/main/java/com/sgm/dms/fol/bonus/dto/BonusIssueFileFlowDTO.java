package com.sgm.dms.fol.bonus.dto;

import java.util.Date;

import com.sgm.dms.fol.common.api.domain.BaseDTO;
/****
 * 奖金导入版本实体
*
* @author ZhangBao
* TODO description
* @date 2015年12月15日
 */
public class BonusIssueFileFlowDTO extends BaseDTO{
	private Date operatorDate           ; 
	private String operatorMsg            ;  
	private Integer operatorStatus         ; 
	private String remark                 ; 
	private String batchNo;
	
	
	public Date getOperatorDate() {
		return operatorDate;
	}
	public void setOperatorDate(Date operatorDate) {
		this.operatorDate = operatorDate;
	}
	public String getOperatorMsg() {
		return operatorMsg;
	}
	public void setOperatorMsg(String operatorMsg) {
		this.operatorMsg = operatorMsg;
	}
	
	public Integer getOperatorStatus() {
		return operatorStatus;
	}
	public void setOperatorStatus(Integer operatorStatus) {
		this.operatorStatus = operatorStatus;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	
	
	                                      
	                                      
	
	
	
	
}
