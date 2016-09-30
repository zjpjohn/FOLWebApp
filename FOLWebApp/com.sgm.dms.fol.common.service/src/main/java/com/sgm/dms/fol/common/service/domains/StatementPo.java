package com.sgm.dms.fol.common.service.domains;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.sgm.dms.fol.common.api.domain.PageVo;

public class StatementPo extends PageVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
		//角色 ID
		private BigDecimal RoleId;
		
		//角色名称
		private String RoleName;
		
		//角色描述 
		private String RoleDesc;
		
		//角色状态
		private BigDecimal RoleStatus;
		
		//角色类型
		private BigDecimal RolrType;
		
		//创建日期
		private Date CreateDate;
		//更新日期
		private Date UpdateDate;
		//更新人
		private BigDecimal UpdateBy;
		
		private BigDecimal CreateBy;
		
		private BigDecimal Typy;
		
		public BigDecimal getRoleId() {
			return RoleId;
		}
		public void setRoleId(BigDecimal roleId) {
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
		public BigDecimal getRoleStatus() {
			return RoleStatus;
		}
		public void setRoleStatus(BigDecimal roleStatus) {
			RoleStatus = roleStatus;
		}
		public BigDecimal getRolrType() {
			return RolrType;
		}
		public void setRolrType(BigDecimal rolrType) {
			RolrType = rolrType;
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
		public BigDecimal getUpdateBy() {
			return UpdateBy;
		}
		public void setUpdateBy(BigDecimal updateBy) {
			UpdateBy = updateBy;
		}
		public BigDecimal getCreateBy() {
			return CreateBy;
		}
		public void setCreateBy(BigDecimal createBy) {
			CreateBy = createBy;
		}
		public BigDecimal getTypy() {
			return Typy;
		}
		public void setTypy(BigDecimal typy) {
			Typy = typy;
		}
		
		
		
}