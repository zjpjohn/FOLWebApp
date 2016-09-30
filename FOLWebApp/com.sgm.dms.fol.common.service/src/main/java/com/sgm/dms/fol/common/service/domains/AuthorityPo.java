package com.sgm.dms.fol.common.service.domains;

import java.io.Serializable;
import java.util.Date;

public class AuthorityPo  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//角色 ID
	private Long RoleId;
	
	//角色名称
	private String RoleName;
	
	//角色描述 
	private String RoleDesc;
	
	//角色状态
	private Integer RoleStatus;
	
	//角色类型
	private Integer RoleType;
	
	//创建日期
	private Date CreateDate;
	//更新日期
	private Date UpdateDate;
	//更新人
	private Integer UpdateBy;
	
	private Integer CreateBy;
	
	private Integer Type;

	public Long getRoleId() {
		return RoleId;
	}

	public void setRoleId(Long roleId) {
		RoleId = roleId;
	}

	public String getRoleName() {
		return RoleName;
	}

	public void setRoleName(String roleName) {
		RoleName = roleName;
	}

	public String getRoleDesc() {
		return RoleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		RoleDesc = roleDesc;
	}

	public Integer getRoleStatus() {
		return RoleStatus;
	}

	public void setRoleStatus(Integer roleStatus) {
		RoleStatus = roleStatus;
	}

	public Integer getRoleType() {
		return RoleType;
	}

	public void setRoleType(Integer roleType) {
		RoleType = roleType;
	}

	public Date getCreateDate() {
		return CreateDate;
	}

	public void setCreateDate(Date createDate) {
		CreateDate = createDate;
	}

	public Date getUpdateDate() {
		return UpdateDate;
	}

	public void setUpdateDate(Date updateDate) {
		UpdateDate = updateDate;
	}

	public Integer getUpdateBy() {
		return UpdateBy;
	}

	public void setUpdateBy(Integer updateBy) {
		UpdateBy = updateBy;
	}

	public Integer getCreateBy() {
		return CreateBy;
	}

	public void setCreateBy(Integer createBy) {
		CreateBy = createBy;
	}

	public Integer getType() {
		return Type;
	}

	public void setTypy(Integer type) {
		Type = type;
	}
	
	
		
		
		
		
}