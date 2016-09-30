package com.sgm.dms.fol.vo;

/****
 * 保存接收银行银票参数
*
* @author zhang bao
* TODO description
* @date 2015年12月31日
 */
/*
*
* @author DELL
* TODO description
* @date 2016年2月15日
*/
	
public class BankBankTictetVo {
	private String maintainType;//维护类型
	private Integer dealerType;//渠道类型
	private String dealerTypeName;//
	private String amount;//金额
	private String moneyDisplay;
	private String bankAbbr;//银行缩写
	private String bankChName;//中文名称
	private String bankEnName;//英文名称
	private Integer auditStatus;
	private String auditStatusName;//审核状态名
	private String auditMsg;//审核意见
	private Long bankId;
	private String encryptId;
	
	public Integer getDealerType() {
		return dealerType;
	}
	public void setDealerType(Integer dealerType) {
		this.dealerType = dealerType;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
    public String getMoneyDisplay() {
        return moneyDisplay;
    }
    
    public void setMoneyDisplay(String moneyDisplay) {
        this.moneyDisplay = moneyDisplay;
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
	public String getMaintainType() {
		return maintainType;
	}
	public void setMaintainType(String maintainType) {
		this.maintainType = maintainType;
	}
    
    public Long getBankId() {
        return bankId;
    }
    
    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public String getEncryptId() {
        return encryptId;
    }
    
    public void setEncryptId(String encryptId) {
        this.encryptId = encryptId;
    }
    
    public Integer getAuditStatus() {
        return auditStatus;
    }
    
    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
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
    
    public String getDealerTypeName() {
        return dealerTypeName;
    }
    
    public void setDealerTypeName(String dealerTypeName) {
        this.dealerTypeName = dealerTypeName;
    }

}
