package com.sgm.dms.fol.common.api.domain;

import java.io.Serializable;
import java.util.Date;

public class AuthorityDTO extends PageDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//角色 ID
	private long roleId;
	
	//角色名称
	private String roleName;
	
	//角色描述 
	private String roleDesc;
	
	//角色状态
	private Long roleStatus;
	
	//角色类型
	private Long roleType;
	
	//角色类型描述
	private String roleTypeDesc;
	
	//创建日期
	private Date createDate;
	//更新日期
	private Date updateDate;
	//更新人
	private Long updateBy;
	
	private Long createBy;
	
	private Integer type;
	
	private String positionId;
	
	private boolean isChecked;//是否选中  0:选中   其他不选中
	
	//权限ID集合
	private String menuId;
	private String methodId;

    
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

    
    public Long getRoleStatus() {
        return roleStatus;
    }

    
    public void setRoleStatus(Long roleStatus) {
        this.roleStatus = roleStatus;
    }

    
    public Long getRoleType() {
        return roleType;
    }

    
    public void setRoleType(Long roleType) {
        this.roleType = roleType;
    }

    
    public String getRoleTypeDesc() {
        return roleTypeDesc;
    }

    
    public void setRoleTypeDesc(String roleTypeDesc) {
        this.roleTypeDesc = roleTypeDesc;
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

    
    public Long getUpdateBy() {
        return updateBy;
    }

    
    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    
    public Long getCreateBy() {
        return createBy;
    }

    
    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    
    public Integer getType() {
        return type;
    }

    
    public void setType(Integer type) {
        this.type = type;
    }

    
    public String getPositionId() {
        return positionId;
    }

    
    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    
    public boolean isChecked() {
        return isChecked;
    }

    
    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    
    public String getMenuId() {
        return menuId;
    }

    
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }


    
    public String getMethodId() {
        return methodId;
    }


    
    public void setMethodId(String methodId) {
        this.methodId = methodId;
    }

}