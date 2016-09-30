package com.sgm.dms.fol.vo;

import java.util.Date;

/***
 * 奖金发放页面展示数据vo
*
* @author ZhangBao
* TODO description
* @date 2015年12月15日
 */
public class BonusIssueDataVo {
	private String encryptId;			//主键
	private String batchNo;    //批次号
	private Date effectDate;    //生效日期
	private Integer matchState; // (审核状态)1,待审核 2,重新审核,3,审核通过
	private Integer status;     //是否有效(1,有效，2,无效)
	private String remark;      //备注
	private Integer bonusType;//奖金类型
	private Integer dealerType;//渠道类型
	private Integer issueStatus;//发放状态
	private String dealerTypeName;//渠道名称
	private String bonusTypeName;//奖金类型名称
	private String matchStateName;//审核名称
	private String issueStatusName;//发放名称
	private String publishDate;//颁发日期
	private Date  createDate;
	private String uploadDate;//上传日期

    public String getEncryptId() {
        return encryptId;
    }
    
    public void setEncryptId(String encryptId) {
        this.encryptId = encryptId;
    }
    public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public Date getEffectDate() {
		return effectDate;
	}
	public void setEffectDate(Date effectDate) {
		this.effectDate = effectDate;
	}
	public Integer getMatchState() {
		return matchState;
	}
	public void setMatchState(Integer matchState) {
		this.matchState = matchState;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getBonusType() {
		return bonusType;
	}
	public void setBonusType(Integer bonusType) {
		this.bonusType = bonusType;
	}
	public Integer getDealerType() {
		return dealerType;
	}
	public void setDealerType(Integer dealerType) {
		this.dealerType = dealerType;
	}
	public Integer getIssueStatus() {
		return issueStatus;
	}
	public void setIssueStatus(Integer issueStatus) {
		this.issueStatus = issueStatus;
	}
	public String getDealerTypeName() {
		return dealerTypeName;
	}
	public void setDealerTypeName(String dealerTypeName) {
		this.dealerTypeName = dealerTypeName;
	}
	public String getBonusTypeName() {
		return bonusTypeName;
	}
	public void setBonusTypeName(String bonusTypeName) {
		this.bonusTypeName = bonusTypeName;
	}
	public String getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(String uploadDate) {
		this.uploadDate = uploadDate;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getMatchStateName() {
		return matchStateName;
	}
	public void setMatchStateName(String matchStateName) {
		this.matchStateName = matchStateName;
	}
	public String getIssueStatusName() {
		return issueStatusName;
	}
	public void setIssueStatusName(String issueStatusName) {
		this.issueStatusName = issueStatusName;
	}
	public String getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
	

}
