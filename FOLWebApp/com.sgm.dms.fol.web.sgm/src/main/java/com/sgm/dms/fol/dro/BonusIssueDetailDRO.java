package com.sgm.dms.fol.dro;

/*****
 * 导出颁发明细
*
* @author Administrator
* TODO description
* @date 2015年12月29日
 */
public class BonusIssueDetailDRO {
	private Integer num;
	private String sapCode;
	private String batchNo;//批次号
	private String servName;//销售公司
	private String dealerTypeName;//渠道类型
	private String bonusTypeName;//奖金类型
	private String amount;//发放金额
	private String publishDateDisplay;//颁发日期
	
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getSapCode() {
		return sapCode;
	}
	public void setSapCode(String sapCode) {
		this.sapCode = sapCode;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
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
    
    public String getBonusTypeName() {
        return bonusTypeName;
    }
    
    public void setBonusTypeName(String bonusTypeName) {
        this.bonusTypeName = bonusTypeName;
    }
    public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
    public String getPublishDateDisplay() {
        return publishDateDisplay;
    }
    
    public void setPublishDateDisplay(String publishDateDisplay) {
        this.publishDateDisplay = publishDateDisplay;
    }
	
}
