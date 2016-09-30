package com.sgm.dms.fol.bonus.dto;

import java.math.BigDecimal;

import com.sgm.dms.fol.common.api.domain.BaseDTO;
/******
 * 奖金发放log实体
 * @author zhang bao
 *
 */
public class BonusIssueFileRcTsDTO extends BaseDTO{
	
	
	private Integer 	id;
	private String 	flowNo;
	private String 	batchNo;
	private String 	tsId;
	private BigDecimal 	amount;
	private String 	remark;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFlowNo() {
		return flowNo;
	}
	public void setFlowNo(String flowNo) {
		this.flowNo = flowNo;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public String getTsId() {
		return tsId;
	}
	public void setTsId(String tsId) {
		this.tsId = tsId;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
		
	
	                                      
	                                      
	
	
	
	
}
