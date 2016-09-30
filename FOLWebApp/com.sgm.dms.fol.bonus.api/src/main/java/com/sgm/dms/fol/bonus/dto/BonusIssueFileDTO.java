package com.sgm.dms.fol.bonus.dto;

import java.util.Date;

import com.sgm.dms.fol.common.api.domain.BaseDTO;
/****
 * 奖金导入版本实体
*
* @author ZhangBao
* TODO description
* @date 2015年12月15日
 */
public class BonusIssueFileDTO extends BaseDTO{
	private Integer id;			//主键
	private String batchNo;    //批次号
	private Date effectDate;    //生效日期
	private Integer matchState; // (审核状态)1,待审核 2,重新审核,3,审核通过
	private Integer status;     //是否有效(1,有效，2,无效)
	private String remark;      //备注
	private Integer bonusType;//奖金类型
	private Integer dealerType;//渠道类型
	private String beginDate;//开始日期
	private String endDate;//结束日期
	private Integer issueStatus;//发放状态
	private Date publishDate;//颁发日期
	private String filedId;
	private Integer beginNo;
	private String type;
	private Integer endNo;
	private String encryptId;

    public String getEncryptId() {
        return encryptId;
    }
    
    public void setEncryptId(String encryptId) {
        this.encryptId = encryptId;
    }
    public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Integer getBeginNo() {
		return beginNo;
	}
	public void setBeginNo(Integer beginNo) {
		this.beginNo = beginNo;
	}
	public Integer getEndNo() {
		return endNo;
	}
	public void setEndNo(Integer endNo) {
		this.endNo = endNo;
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
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	public String getFiledId() {
		return filedId;
	}
	public void setFiledId(String filedId) {
		this.filedId = filedId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	
}
