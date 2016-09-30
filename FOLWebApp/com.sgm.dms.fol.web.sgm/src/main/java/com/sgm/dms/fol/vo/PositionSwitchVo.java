package com.sgm.dms.fol.vo;

import java.io.Serializable;

import com.sgm.dms.fol.common.api.domain.PageVo;

/**
 * 岗位切换VO
 * 
 * @author lujinglei
 * 
 */
public class PositionSwitchVo extends PageVo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String positionId;
	private String userAccount;
	private String loginId;
	private String positionName;

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

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

}
