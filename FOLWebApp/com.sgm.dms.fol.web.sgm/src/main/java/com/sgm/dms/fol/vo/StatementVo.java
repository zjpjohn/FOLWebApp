/**
* @ClassName: demoAmountVo
* @Description: TODO
* @author: LUHUI
* @date: 2015年10月19日 上午9:42:56
* 
* 
*/
package com.sgm.dms.fol.vo;

import java.io.Serializable;
import java.util.Date;

import com.sgm.dms.fol.common.api.domain.PageVo;


public class StatementVo  extends PageVo  implements Serializable{

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
			
			private Integer Typy;

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

			public Integer getTypy() {
				return Typy;
			}

			public void setTypy(Integer typy) {
				Typy = typy;
			}
			
			

} 
