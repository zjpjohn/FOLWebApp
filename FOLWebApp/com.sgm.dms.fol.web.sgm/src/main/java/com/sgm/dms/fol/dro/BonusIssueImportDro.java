package com.sgm.dms.fol.dro;

/**
 * 
 * Title: Class BonusIssueImportDro.java
 * Description:奖金发放版本导入dro
 *
 *
 * @author wangfl
 * @version 0.0.1
 */
public class BonusIssueImportDro {

	/** ASC编号 */
	private String sapCode;
	/** 流水号 */
	private String flowNo;
	/** 备注 */
	private String remark;
	/** 销售公司 */
	private String serv;
	/** 奖金1 */
	private String amount_first;
	/** 奖金2 */
	private String amount_second;
	
	public BonusIssueImportDro(){};
	
	public BonusIssueImportDro(String sapCode, String flowNo, String remark, String serv, String amount_first,
			String amount_second) {
		super();
		this.sapCode = sapCode;
		this.flowNo = flowNo;
		this.remark = remark;
		this.serv = serv;
		this.amount_first = amount_first;
		this.amount_second = amount_second;
	}
	
	public String getSapCode() {
		return sapCode;
	}
	public void setSapCode(String sapCode) {
		this.sapCode = sapCode;
	}
	public String getFlowNo() {
		return flowNo;
	}
	public void setFlowNo(String flowNo) {
		this.flowNo = flowNo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getServ() {
		return serv;
	}
	public void setServ(String serv) {
		this.serv = serv;
	}
	public String getAmount_first() {
		return amount_first;
	}
	public void setAmount_first(String amount_first) {
		this.amount_first = amount_first;
	}
	public String getAmount_second() {
		return amount_second;
	}
	public void setAmount_second(String amount_second) {
		this.amount_second = amount_second;
	}
	
}
