package com.sgm.dms.fol.bonus.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.sgm.dms.fol.common.api.domain.BaseDTO;
/****
 * 奖金颁发记录实体
*
* @author ZhangBao
* TODO description
* @date 2015年12月15日
 */
public class BonusIssueRecordDTO extends BaseDTO{
    private Integer id;//主键
	private String encryptId;			//加密主键
	private String sapCode;//经销商代码
	private Integer bonusType;//奖金类型
	private String bonusTypeName;
	private String batchNo;    //批次号
	private Date effectDate;    //生效日期
	private BigDecimal amount;//发放金额
	private Integer serv;//销售公司
	private String servName;
	private Date publishDate;//颁发日期
	private Integer status;     //是否有效(1,有效，2,无效)
	private String remark;      //备注
	private String beginDate;//开始日期
	private String endDate;//结束日期
	private Integer dealerType;//经销商类型
	private String dealerTypeName;
	private String issueErrMsgCode;//处理消息 
	private String executeMsg;//处理消息
	private Date executeDate;//处理日期
	private String executeDateDisplay;//处理日期
	private Integer issueStatus;//发放状态
	private String flowNo;//流水号
	private Integer beginNo;
	private Integer endNo;
	private Integer No;//序号
	private String dealerCode;
	private String fileId;
	private String publishDateDisplay;
	
	
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
	
	
	
	public String getSapCode() {
		return sapCode;
	}
	public void setSapCode(String sapCode) {
		this.sapCode = sapCode;
	}
	public Integer getBonusType() {
		return bonusType;
	}
	public void setBonusType(Integer bonusType) {
		this.bonusType = bonusType;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public Integer getServ() {
		return serv;
	}
	public void setServ(Integer serv) {
		this.serv = serv;
	}

    public Date getPublishDate() {
        return publishDate;
    }
    
    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
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
	public Integer getDealerType() {
		return dealerType;
	}
	public void setDealerType(Integer dealerType) {
		this.dealerType = dealerType;
	}
	public String getIssueErrMsgCode() {
		return issueErrMsgCode;
	}
	public void setIssueErrMsgCode(String issueErrMsgCode) {
		this.issueErrMsgCode = issueErrMsgCode;
	}
	public Date getExecuteDate() {
		return executeDate;
	}
	public void setExecuteDate(Date executeDate) {
		this.executeDate = executeDate;
	}
	public String getFlowNo() {
		return flowNo;
	}
	public void setFlowNo(String flowNo) {
		this.flowNo = flowNo;
	}
	public Integer getIssueStatus() {
		return issueStatus;
	}
	public void setIssueStatus(Integer issueStatus) {
		this.issueStatus = issueStatus;
	}
	public Integer getNo() {
		return No;
	}
	public void setNo(Integer no) {
		No = no;
	}
	public String getDealerCode() {
		return dealerCode;
	}
	public void setDealerCode(String dealerCode) {
		this.dealerCode = dealerCode;
	}
	public String getBonusTypeName() {
		return bonusTypeName;
	}
	public void setBonusTypeName(String bonusTypeName) {
		this.bonusTypeName = bonusTypeName;
	}
	public String getServName() {
		return servName;
	}
	public void setServName(String servName) {
		this.servName = servName;
	}
	public String getDealerTypeName() {
		return dealerTypeName;
	}
	public void setDealerTypeName(String dealerTypeName) {
		this.dealerTypeName = dealerTypeName;
	}
	public String getExecuteMsg() {
		return executeMsg;
	}
	public void setExecuteMsg(String executeMsg) {
		this.executeMsg = executeMsg;
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
    
    public String getExecuteDateDisplay() {
        return executeDateDisplay;
    }
    
    public void setExecuteDateDisplay(String executeDateDisplay) {
        this.executeDateDisplay = executeDateDisplay;
    }
    
    public String getPublishDateDisplay() {
        return publishDateDisplay;
    }
    
    public void setPublishDateDisplay(String publishDateDisplay) {
        this.publishDateDisplay = publishDateDisplay;
    }
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

    
}
