package com.sgm.dms.fol.vo;

import java.io.Serializable;

import com.sgm.dms.fol.common.api.domain.PageVo;
import com.sgm.solution.framework.dataAuthority.annotations.SGMValidationFieldRefIds;
import com.sgm.solution.framework.dataAuthority.annotations.SGMValidationFieldSign;
import com.sgm.solution.framework.dataAuthority.annotations.SGMValidationResource;

/**
 * 
 * @ClassName: UserSearchVo
 * @Description: TODO
 * @author: wangyx
 * @date: 2015年6月23日 下午3:31:27
 *
 */
@SGMValidationResource
public class UserSearchVo extends PageVo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String userName;
	private long userStatus;
	@SGMValidationFieldRefIds(seq=1)
	private String positionId;
	@SGMValidationFieldRefIds(seq=2)
	private String userAccount;
	@SGMValidationFieldSign
	private String sign;
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
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	
}
