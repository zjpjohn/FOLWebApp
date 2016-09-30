
package com.sgm.dms.fol.common.service.domains;

import java.util.Date;

public class UserPo extends BasePo {
	private long userId;
	private String orgCode;
	private String userAccount;
	private String userName;
	private String password;
	private String userPose;
	private int gender;
	private String handPhone;
	private String email;
	private Date birthday;
	private String addr;
	private String zipCode;
	private int userStatus;
	private Date lastSigninTime;
	private String userAuthority;
	private int userType;
	private int brandId;
	private String portalUser;
	private int portalUserStatus;
	private Date handlingTime;
	private String dealerId;
	private String memo;
	private String userNo;

	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserPose() {
		return userPose;
	}
	public void setUserPose(String userPose) {
		this.userPose = userPose;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getHandPhone() {
		return handPhone;
	}
	public void setHandPhone(String handPhone) {
		this.handPhone = handPhone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public int getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(int userStatus) {
		this.userStatus = userStatus;
	}
	public Date getLastSigninTime() {
		return lastSigninTime;
	}
	public void setLastSigninTime(Date lastSigninTime) {
		this.lastSigninTime = lastSigninTime;
	}
	public String getUserAuthority() {
		return userAuthority;
	}
	public void setUserAuthority(String userAuthority) {
		this.userAuthority = userAuthority;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public int getBrandId() {
		return brandId;
	}
	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}
	public String getPortalUser() {
		return portalUser;
	}
	public void setPortalUser(String portalUser) {
		this.portalUser = portalUser;
	}
	public int getPortalUserStatus() {
		return portalUserStatus;
	}
	public void setPortalUserStatus(int portalUserStatus) {
		this.portalUserStatus = portalUserStatus;
	}
	public Date getHandlingTime() {
		return handlingTime;
	}
	public void setHandlingTime(Date handlingTime) {
		this.handlingTime = handlingTime;
	}
	public String getDealerId() {
		return dealerId;
	}
	public void setDealerId(String dealerId) {
		this.dealerId = dealerId;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
}
