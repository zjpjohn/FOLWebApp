package com.sgm.dms.fol.returnallowance.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 退货单Dto
 * @author wangfl
 * @date 2016年8月17日
 */
public class ReturnOrderDto {
	private Long id;//主键id

    private String returnOrderNo;//退货证明单号

    private String sapCode;//经销商代码

    private String companyName;//公司名称

    private String taxBureauName;//税务局名称

    private String invoiceCode;//增值税专用发票代码

    private String invoiceNumber;//增值税专用发票号码

    private BigDecimal gross;//退货含税金额

    private BigDecimal tax;//税额

    private BigDecimal netAmount;//不含税金额

    private String reportDate;//上报日期

    private String reporter;//上报人

    private String acceptDate;//受理日期

    private String sgmRemark;//受理意见

    private String proposer;//申请人

    private String applyDate;//申请日期

    private Short discountOrderStatus;//折让单处理标记

    private Short valid;

    private Long createBy;

    private Date createDate;

    private Long updateBy;

    private Date updateDate;
    
    private String billingOrign;//销方公司

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReturnOrderNo() {
        return returnOrderNo;
    }

    public void setReturnOrderNo(String returnOrderNo) {
        this.returnOrderNo = returnOrderNo == null ? null : returnOrderNo.trim();
    }

    public String getSapCode() {
        return sapCode;
    }

    public void setSapCode(String sapCode) {
        this.sapCode = sapCode == null ? null : sapCode.trim();
    }

    public String getBillingOrign() {
		return billingOrign;
	}

	public void setBillingOrign(String billingOrign) {
		this.billingOrign = billingOrign;
	}

	public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getTaxBureauName() {
        return taxBureauName;
    }

    public void setTaxBureauName(String taxBureauName) {
        this.taxBureauName = taxBureauName == null ? null : taxBureauName.trim();
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode == null ? null : invoiceCode.trim();
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber == null ? null : invoiceNumber.trim();
    }

    public BigDecimal getGross() {
        return gross;
    }

    public void setGross(BigDecimal gross) {
        this.gross = gross;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(BigDecimal netAmount) {
        this.netAmount = netAmount;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate == null ? null : reportDate.trim();
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter == null ? null : reporter.trim();
    }

    public String getAcceptDate() {
        return acceptDate;
    }

    public void setAcceptDate(String acceptDate) {
        this.acceptDate = acceptDate == null ? null : acceptDate.trim();
    }

    public String getSgmRemark() {
        return sgmRemark;
    }

    public void setSgmRemark(String sgmRemark) {
        this.sgmRemark = sgmRemark == null ? null : sgmRemark.trim();
    }

    public String getProposer() {
        return proposer;
    }

    public void setProposer(String proposer) {
        this.proposer = proposer == null ? null : proposer.trim();
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate == null ? null : applyDate.trim();
    }

    public Short getDiscountOrderStatus() {
        return discountOrderStatus;
    }

    public void setDiscountOrderStatus(Short discountOrderStatus) {
        this.discountOrderStatus = discountOrderStatus;
    }

    public Short getValid() {
        return valid;
    }

    public void setValid(Short valid) {
        this.valid = valid;
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