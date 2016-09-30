/**
* @ClassName: demoAmountVo
* @Description: TODO
* @author: LuHhui
* @date: 2015年10月9日 上午9:42:56
* 
* 
*/
package com.sgm.dms.fol.vo;

import java.io.Serializable;
import java.util.Date;

import com.sgm.dms.fol.common.api.domain.PageVo;


public class AuthorityAndPositionVo  extends PageVo  implements Serializable{

	private static final long serialVersionUID = 1L;
			//角色 ID
			private Integer RoleId;
			
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
			
			//----------------------
			//岗位编号
			private String positionId;
			
			//岗位名称
			private String positionName;
			
			//岗位介绍
			private String positionDesc;
			
			//备注
			private String remark;
			
			//状态
			private Integer status;
			
			//岗位类型
			private Integer positionType;
			
			//创建日期
			private Date positionDate;
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
			public Integer getStatus() {
				return status;
			}
			public void setStatus(Integer status) {
				this.status = status;
			}
			public Integer getPositionType() {
				return positionType;
			}
			public void setPositionType(Integer positionType) {
				this.positionType = positionType;
			}
			public Date getPositionDate() {
				return positionDate;
			}
			public void setPositionDate(Date positionDate) {
				this.positionDate = positionDate;
			}
			public String getDpPositionCode() {
				return dpPositionCode;
			}
			public void setDpPositionCode(String dpPositionCode) {
				this.dpPositionCode = dpPositionCode;
			}
	

			public Integer getRoleId() {
				return RoleId;
			}

			public void setRoleId(Integer roleId) {
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

			public void setType(Integer type) {
				Type = type;
			}
			
			

} 
