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
import com.sgm.solution.framework.dataAuthority.annotations.SGMValidationFieldRefIds;
import com.sgm.solution.framework.dataAuthority.annotations.SGMValidationFieldSign;
import com.sgm.solution.framework.dataAuthority.annotations.SGMValidationResource;

@SGMValidationResource
public class AuthorityVo  extends PageVo  implements Serializable{

	private static final long serialVersionUID = 1L;
	//角色 ID
	@SGMValidationFieldRefIds(seq=1)
	private long roleId;
	
	//角色名称
	private String roleName;
	
	//角色描述 
	//@SGMValidationFieldRefIds(seq=2)
	private String roleDesc;
	
	//角色状态
	private Integer roleStatus;
	
	//角色类型
	@SGMValidationFieldRefIds(seq=3)
	private Integer roleType;
	//角色类型描述

	private String roleTypeDesc;
	
	//创建日期
	private Date createDate;
	//更新日期
	private Date updateDate;
	//更新人
	private Integer updateBy;
	
	private Integer createBy;
	private Integer type;
	private String positionId;
	
	private String optTionType; // add  and del
	
	private String optType ;// add and update role 
	
	private boolean isChecked;
	private String   menuName;
	private String   functionName;
	//权限ID集合
	@SGMValidationFieldRefIds(seq=4)
	private String menuId;
	private String methodId;
	
	@SGMValidationFieldSign
	private String sign;
	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

	public String getOptType() {
		return optType;
	}

	public void setOptType(String optType) {
		this.optType = optType;
	}

	public String getOptTionType() {
		return optTionType;
	}

	public void setOptTionType(String optTionType) {
		this.optTionType = optTionType;
	}

	public String getPositionId() {
		return positionId;
	}

	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}

	public String getRoleTypeDesc() {
		return roleTypeDesc;
	}

	public void setRoleTypeDesc(String roleTypeDesc) {
		this.roleTypeDesc = roleTypeDesc;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public Integer getRoleStatus() {
		return roleStatus;
	}

	public void setRoleStatus(Integer roleStatus) {
		this.roleStatus = roleStatus;
	}

	public Integer getRoleType() {
		return roleType;
	}

	public void setRoleType(Integer roleType) {
		this.roleType = roleType;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}

	public Integer getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

    
    public String getMethodId() {
        return methodId;
    }

    
    public void setMethodId(String methodId) {
        this.methodId = methodId;
    }
	
} 
