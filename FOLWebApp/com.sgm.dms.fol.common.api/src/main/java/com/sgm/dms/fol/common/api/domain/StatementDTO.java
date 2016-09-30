package com.sgm.dms.fol.common.api.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class StatementDTO  implements Serializable {

	private static final long serialVersionUID = 1L;
	//岗位编号
	private String positionId;
	
	//岗位名称
	private String positionName;
	
	//岗位介绍
	private String positionDesc;
	
	//备注
	private String remark;
	
	//状态
	private BigDecimal status;
	
	//岗位类型
	private BigDecimal positionType;
	
	//创建日期
	private Date positionDate;
	//创建人
	private BigDecimal createBy;
	//更新日期
	private Date updateDate;
	//更新人
	private BigDecimal updateBy;
	//对应的岗位代码 
	private String dpPositionCode;
	
	
	public String getPositionId() {
		return positionId;
	}
	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public String getPositionDesc() {
		return positionDesc;
	}
	public void setPositionDesc(String positionDesc) {
		this.positionDesc = positionDesc;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public BigDecimal getStatus() {
		return status;
	}
	public void setStatus(BigDecimal status) {
		this.status = status;
	}
	public BigDecimal getPositionType() {
		return positionType;
	}
	public void setPositionType(BigDecimal positionType) {
		this.positionType = positionType;
	}
	public Date getPositionDate() {
		return positionDate;
	}
	public void setPositionDate(Date positionDate) {
		this.positionDate = positionDate;
	}
	public BigDecimal getCreateBy() {
		return createBy;
	}
	public void setCreateBy(BigDecimal createBy) {
		this.createBy = createBy;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public BigDecimal getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(BigDecimal updateBy) {
		this.updateBy = updateBy;
	}
	public String getDpPositionCode() {
		return dpPositionCode;
	}
	public void setDpPositionCode(String dpPositionCode) {
		this.dpPositionCode = dpPositionCode;
	}
	
	
}