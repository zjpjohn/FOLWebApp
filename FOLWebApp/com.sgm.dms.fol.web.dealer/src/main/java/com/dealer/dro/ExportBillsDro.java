package com.dealer.dro;

import java.util.Date;

/**
 * 对账单导出Dro（DEALER）
 * 
 * @author lujinglei
 * 
 */
public class ExportBillsDro {

	private int num;// 序号
	private String title;// 标题
	private Date dbBillDate;// 账单年月
	private Date effectiveDate;// 生效日期
	private String status; // 状态
	private String confirmName;// 是否确认

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDbBillDate() {
		return dbBillDate;
	}

	public void setDbBillDate(Date dbBillDate) {
		this.dbBillDate = dbBillDate;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getConfirmName() {
		return confirmName;
	}

	public void setConfirmName(String confirmName) {
		this.confirmName = confirmName;
	}

}
