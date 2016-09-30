package com.sgm.dms.fol.returnallowance.domain;

import java.math.BigDecimal;
import java.util.Date;

public class ReturnOrder {
    private Long id;

    private String returnOrderNo;

    private String sapCode;

    private String companyName;

    private String taxBureauName;

    private String invoiceCode;

    private String invoiceNumber;

    private BigDecimal gross;

    private BigDecimal tax;

    private BigDecimal netAmount;

    private String reportDate;

    private String reporter;

    private String acceptDate;

    private String sgmRemark;

    private String proposer;

    private String applyDate;

    private Short discountOrderStatus;

    private Short valid;

    private Long createBy;

    private Date createDate;

    private Long updateBy;

    private Date updateDate;

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