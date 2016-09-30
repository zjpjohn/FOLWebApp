package com.sgm.dms.fol.vo;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.NotBlank;

import com.sgm.dms.fol.common.service.annotation.FileType;

/****
 * 奖金颁发vo
*
* @author ZhangBao
* TODO description
* @date 2015年12月15日
 */
public class BonusIssueVo {
	private String encryptId;
	private Integer dealerType;//渠道类型
	private Integer bonusType;//奖金类型
	private String beginDate;//上传开始日期
	private String endDate;//上传结束日期
	private String batchNo;//批次号
	@NotBlank(message="filed不能为空")
	private String filedId;
	@NotBlank(message="token不能为空")
	private String token;
	private Integer beginNo;
	private Integer endNo;
	private Object data;
	private String type;//标示符 1:上传和审核,2:发放
	private Integer issueStatus;//发放状态
	private BigDecimal amount;//发放金额
	private String flowNo;//流水号
	private String sapCode;
	@FileType(fileType = "csv", message="文件类型不对，只能上传格式为csv的文件")
	private String fileName;//文件名字
	
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
	public Integer getDealerType() {
		return dealerType;
	}
	public void setDealerType(Integer dealerType) {
		this.dealerType = dealerType;
	}
	public Integer getBonusType() {
		return bonusType;
	}
	public void setBonusType(Integer bonusType) {
		this.bonusType = bonusType;
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
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public String getFiledId() {
		return filedId;
	}
	public void setFiledId(String filedId) {
		this.filedId = filedId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getIssueStatus() {
		return issueStatus;
	}
	public void setIssueStatus(Integer issueStatus) {
		this.issueStatus = issueStatus;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getFlowNo() {
		return flowNo;
	}
	public void setFlowNo(String flowNo) {
		this.flowNo = flowNo;
	}

    public String getEncryptId() {
        return encryptId;
    }
    
    public void setEncryptId(String encryptId) {
        this.encryptId = encryptId;
    }
    public String getSapCode() {
		return sapCode;
	}
	public void setSapCode(String sapCode) {
		this.sapCode = sapCode;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
