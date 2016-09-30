/**
* @ClassName: UserSearchDTO
* @Description: TODO
* @author: wangyx
* @date: 2015年6月23日 上午16:41:52
* 
* 
*/
package com.dealer.vo;

import java.io.Serializable;

import com.sgm.dms.fol.common.api.domain.PageVo;


public class UserSearchVo extends PageVo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String userId;
	private String userAccount;
	private String positionId;
	private String positionName;
	private String dealerName;
	private String sapCode;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
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
	public String getDealerName() {
		return dealerName;
	}
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}
	public String getSapCode() {
		return sapCode;
	}
	public void setSapCode(String sapCode) {
		this.sapCode = sapCode;
	}

	
	
}