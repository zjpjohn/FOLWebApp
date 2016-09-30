package com.sgm.dms.fol.invoice.domain;

import java.util.Date;

public class ExpressOrder {
    private String expressNo;

    private Short expressUnit;

    private Date changeTime;

    private String changeMsg;

    private String postUser;

    private String postUserPhone;

    private Short expressStatus;

    private Short valid;

    private String remark;

    private Long createBy;

    private Date createDate;

    private Long updateBy;

    private Date updateDate;

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo == null ? null : expressNo.trim();
    }

    public Short getExpressUnit() {
        return expressUnit;
    }

    public void setExpressUnit(Short expressUnit) {
        this.expressUnit = expressUnit;
    }

    public Date getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }

    public String getChangeMsg() {
        return changeMsg;
    }

    public void setChangeMsg(String changeMsg) {
        this.changeMsg = changeMsg == null ? null : changeMsg.trim();
    }

    public String getPostUser() {
        return postUser;
    }

    public void setPostUser(String postUser) {
        this.postUser = postUser == null ? null : postUser.trim();
    }

    public String getPostUserPhone() {
		return postUserPhone;
	}

	public void setPostUserPhone(String postUserPhone) {
		this.postUserPhone = postUserPhone;
	}

	public Short getExpressStatus() {
        return expressStatus;
    }

    public void setExpressStatus(Short expressStatus) {
        this.expressStatus = expressStatus;
    }

    public Short getValid() {
        return valid;
    }

    public void setValid(Short valid) {
        this.valid = valid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}