/**
* @ClassName: UserSearchDTO
* @Description: TODO
* @author: wangyx
* @date: 2015年6月23日 上午16:41:52
* 
* 
*/
package com.sgm.dms.fol.common.api.domain;

import java.io.Serializable;
import java.util.List;


public class UserSearchDTO extends PageDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private String userName;
	private long userStatus;
	private String positionId;
	private String userAccount;
	private String dealerId;
	private String userId;
	private String sapCode;
	private String dealerName;
	private String userType;
	private List<String> permission;
	private String roleIds;//岗位对应的角色，当多个角色时，用逗号分开。
	private String dPPositionCode;//PORTAL对应的岗位代码
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public long getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(long userStatus) {
		this.userStatus = userStatus;
	}
	public String getPositionId() {
		return positionId;
	}
	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public String getDealerId() {
		return dealerId;
	}
	public void setDealerId(String dealerId) {
		this.dealerId = dealerId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSapCode() {
		return sapCode;
	}
	public void setSapCode(String sapCode) {
		this.sapCode = sapCode;
	}
	public String getDealerName() {
		return dealerName;
	}
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public List<String> getPermission() {
		return permission;
	}
	public void setPermission(List<String> permission) {
		this.permission = permission;
	}
	public String getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}
	public String getdPPositionCode() {
		return dPPositionCode;
	}
	public void setdPPositionCode(String dPPositionCode) {
		this.dPPositionCode = dPPositionCode;
	}
	
}