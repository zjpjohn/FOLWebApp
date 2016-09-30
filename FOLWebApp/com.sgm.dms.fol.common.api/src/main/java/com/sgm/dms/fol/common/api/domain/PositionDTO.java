package com.sgm.dms.fol.common.api.domain;

import java.io.Serializable;
import java.util.Date;

public class PositionDTO extends PageDto implements Serializable {

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
	private Long status;
	
	//岗位类型
	private Integer positionType;
	
	//创建日期
	private Date createDate;
	//创建人
	private Long createBy;
	//更新日期
	private Date updateDate;
	//更新人
	private Long updateBy;
	//对应的岗位代码 
	private String dpPositionCode;
	//角色列表
	private String roles;
	private boolean isChecked;
	
	
	public boolean isChecked() {
		return isChecked;
	}
	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	
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
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	public Integer getPositionType() {
		return positionType;
	}
	public void setPositionType(Integer positionType) {
		this.positionType = positionType;
	}
	
	
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Long getCreateBy() {
		return createBy;
	}
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Long getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}
	public String getDpPositionCode() {
		return dpPositionCode;
	}
	public void setDpPositionCode(String dpPositionCode) {
		this.dpPositionCode = dpPositionCode;
	}

	
	
}                