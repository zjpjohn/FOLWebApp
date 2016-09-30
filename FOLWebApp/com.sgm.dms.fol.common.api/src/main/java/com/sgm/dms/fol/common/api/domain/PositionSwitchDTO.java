package com.sgm.dms.fol.common.api.domain;

import java.io.Serializable;
import java.util.List;

import com.sgm.dms.fol.common.api.domain.PageVo;

/**
 * 岗位切换DTO
 * 
 * @author lujinglei
 * 
 */
public class PositionSwitchDTO extends PageVo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String userName;
	private long userStatus;
	private String positionId;
	private String userAccount;
	private String dealerId;
	private String loginId;
	private String sapCode;
	private String dealerName;
	private String userType;
	private List<String> permission;
	private String positionName;
	private String userId;

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

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
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

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
