package com.sgm.dms.fol.vo;

/*****
 * 
*
* @author zhang bao
* 超期利息开票清单查询formvo
* @date 2016年1月13日
 */
public class BankTicketDraftsOverdueInterestFormVo {
    private Long id;//主键
	private String yearMonth;//年月
	private String sapCode;//客户名称
	private String dealerName;//客户名称
	private String 	interestAmount  ;//贴息金额
	private String 	unTaxAmount    ;//不函数金额
	private String 	addTaxAmount   ;//增值税金额
	private String invoiceNumber;//发票号码
	private String trackNumber;//EMS快递单号
	private String corpCode;//公司代码
	private String issueStatusName;//发放状态
	private String confirmStatusName;//确认状态
	private Integer issueStatus;//发放状态
 	private Integer beginNo;
	private Integer endNo;
	
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    public String getSapCode() {
		return sapCode;
	}
	public void setSapCode(String sapCode) {
		this.sapCode = sapCode;
	}
	public String getDealerName() {
		return dealerName;
	}
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	public String getInterestAmount() {
		return interestAmount;
	}
	public void setInterestAmount(String interestAmount) {
		this.interestAmount = interestAmount;
	}
	public String getUnTaxAmount() {
		return unTaxAmount;
	}
	public void setUnTaxAmount(String unTaxAmount) {
		this.unTaxAmount = unTaxAmount;
	}
	public String getAddTaxAmount() {
		return addTaxAmount;
	}
	public void setAddTaxAmount(String addTaxAmount) {
		this.addTaxAmount = addTaxAmount;
	}
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public String getTrackNumber() {
		return trackNumber;
	}
	public void setTrackNumber(String trackNumber) {
		this.trackNumber = trackNumber;
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
	public String getYearMonth() {
		return yearMonth;
	}
	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}
	public String getCorpCode() {
		return corpCode;
	}
	public void setCorpCode(String corpCode) {
		this.corpCode = corpCode;
	}

    
    public String getIssueStatusName() {
        return issueStatusName;
    }

    
    public void setIssueStatusName(String issueStatusName) {
        this.issueStatusName = issueStatusName;
    }

    
    public String getConfirmStatusName() {
        return confirmStatusName;
    }

    
    public void setConfirmStatusName(String confirmStatusName) {
        this.confirmStatusName = confirmStatusName;
    }

	public Integer getIssueStatus() {
		return issueStatus;
	}

	public void setIssueStatus(Integer issueStatus) {
		this.issueStatus = issueStatus;
	}

}
