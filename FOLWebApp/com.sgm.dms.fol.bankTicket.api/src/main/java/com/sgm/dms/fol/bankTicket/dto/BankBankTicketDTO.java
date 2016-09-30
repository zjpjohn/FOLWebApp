package com.sgm.dms.fol.bankTicket.dto;

import java.math.BigDecimal;
import java.util.List;

import com.sgm.dms.fol.common.api.domain.BaseDTO;

/****
 * 银行银票信息实体类
*
* @author zhang bao
* TODO description
* @date 2015年12月31日
 */
public class BankBankTicketDTO  extends BaseDTO{
	private Long id             ;//主键
	private Integer dealerType     ;//渠道类型
	private String dealerTypeName;//渠道类型名
	private Long bankId         ;//银行id
	private BigDecimal amount         ;//额度
	private BigDecimal moneyDisplay;//
	private Integer auditStatus ;//审批状态
	private String auditStatusName;//审核状态名
	private String auditMsg;//审核意见
	private Integer status         ;//是否有效状态
	private String remark         ;//备注 
	private Integer maintainType;//维护类型
	private String bankAbbr;//缩写
	private String bankChName;//银行中文名
	private String bankEnName;//银行英文名
	private Integer beginNo;
	private Integer endNo;
	private List<Integer> auditStatusList;
	private String encryptId;//加密过的ID

	public Integer getDealerType() {
		return dealerType;
	}
	public void setDealerType(Integer dealerType) {
		this.dealerType = dealerType;
	}
	public Long getBankId() {
		return bankId;
	}
	public void setBankId(Long bankId) {
		this.bankId = bankId;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
    public BigDecimal getMoneyDisplay() {
        return moneyDisplay;
    }
    
    public void setMoneyDisplay(BigDecimal moneyDisplay) {
        this.moneyDisplay = moneyDisplay;
    }
    public Integer getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
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
	
	public Integer getMaintainType() {
		return maintainType;
	}
	public void setMaintainType(Integer maintainType) {
		this.maintainType = maintainType;
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
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getBankAbbr() {
        return bankAbbr;
    }
    
    public void setBankAbbr(String bankAbbr) {
        this.bankAbbr = bankAbbr;
    }
    
    public String getBankChName() {
        return bankChName;
    }
    
    public void setBankChName(String bankChName) {
        this.bankChName = bankChName;
    }
    
    public String getBankEnName() {
        return bankEnName;
    }
    
    public void setBankEnName(String bankEnName) {
        this.bankEnName = bankEnName;
    }
    
    public String getDealerTypeName() {
        return dealerTypeName;
    }
    
    public void setDealerTypeName(String dealerTypeName) {
        this.dealerTypeName = dealerTypeName;
    }
    
    public String getAuditStatusName() {
        return auditStatusName;
    }
    
    public void setAuditStatusName(String auditStatusName) {
        this.auditStatusName = auditStatusName;
    }
    public String getAuditMsg() {
        return auditMsg;
    }
    public void setAuditMsg(String auditMsg) {
        this.auditMsg = auditMsg;
    }
	public List<Integer> getAuditStatusList() {
		return auditStatusList;
	}
	public void setAuditStatusList(List<Integer> auditStatusList) {
		this.auditStatusList = auditStatusList;
	}
    
    public String getEncryptId() {
        return encryptId;
    }
    
    public void setEncryptId(String encryptId) {
        this.encryptId = encryptId;
    }
	
}
