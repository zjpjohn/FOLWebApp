package com.sgm.dms.fol.vo;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.NotBlank;

import com.sgm.dms.fol.common.service.annotation.FileType;

/****
 * 银票超期利息查询vo
*
* @author Administrator
* TODO description
* @date 2016年1月14日
 */
public class BankTicketBusinessInquiriesVo {
    @NotBlank(message="年度不能为空")
	private String year;//年
    @NotBlank(message="月度不能为空")
	private String month;//月
	private String sapCode;//
	private String dealerName;//客户名称
	private BigDecimal 	interestAmount  ;//贴息金额
	private BigDecimal 	unTaxAmount    ;//不函数金额
	private BigDecimal 	addTaxAmount   ;//增值税金额
	private String invoiceNumber;//发票号码
	private String trackNumber;//EMS快递单号
	private String corpCode;//公司代码
	private String token;
	private Integer beginNo;
	private Integer endNo;
	private String filedId;
	@FileType(fileType = "txt", message="文件类型不对，只能上传格式为txt的文件")
	private String fileName;
	
	public String getCorpCode() {
		return corpCode;
	}
	public void setCorpCode(String corpCode) {
		this.corpCode = corpCode;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
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
	
	public BigDecimal getInterestAmount() {
		return interestAmount;
	}
	public void setInterestAmount(BigDecimal interestAmount) {
		this.interestAmount = interestAmount;
	}
	public BigDecimal getUnTaxAmount() {
		return unTaxAmount;
	}
	public void setUnTaxAmount(BigDecimal unTaxAmount) {
		this.unTaxAmount = unTaxAmount;
	}
	public BigDecimal getAddTaxAmount() {
		return addTaxAmount;
	}
	public void setAddTaxAmount(BigDecimal addTaxAmount) {
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
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getFiledId() {
		return filedId;
	}
	public void setFiledId(String filedId) {
		this.filedId = filedId;
	}
    
    public String getFileName() {
        return fileName;
    }
    
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
	
}
