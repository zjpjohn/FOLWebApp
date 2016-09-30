package com.dealer.vo;

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
    private String dealerName;
    private String sapCode;

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
