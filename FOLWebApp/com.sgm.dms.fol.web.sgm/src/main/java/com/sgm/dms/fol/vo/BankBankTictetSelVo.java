package com.sgm.dms.fol.vo;

/****
 * 查询接收银行银票参数
*
* @author zhang bao
* TODO description
* @date 2015年12月31日
 */
public class BankBankTictetSelVo {
	private Integer maintainType;//维护类型
	private Integer dealerType;//渠道类型
	private Integer auditStatus ;//审批状态
	private Integer beginNo;
	private Integer endNo;
	public Integer getDealerType() {
		return dealerType;
	}
	public void setDealerType(Integer dealerType) {
		this.dealerType = dealerType;
	}
	
	public Integer getMaintainType() {
		return maintainType;
	}
	public void setMaintainType(Integer maintainType) {
		this.maintainType = maintainType;
	}
	public Integer getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}
	public Integer getBeginNo() {
		return beginNo;
	}
	public void setBeginNo(Integer beginNo) {
		this.beginNo = beginNo;
	}
	public Integer getEndNo() {
		return endNo;
	}
	public void setEndNo(Integer endNo) {
		this.endNo = endNo;
	}
	
	
	
}
