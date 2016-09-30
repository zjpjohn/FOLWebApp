package com.sgm.dms.fol.returnallowance.domain;

import java.math.BigDecimal;
import java.util.Date;

public class AllowanceInvoiceInfo {
    private Long id;

    private Long returnOrderId;

    private String returnOrderNo;

    private String billingReverseNo;

    private String billingNo;

    private BigDecimal orderNetvalue;
    
    private String billingOrign;

    private String remark;

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

    public Long getReturnOrderId() {
        return returnOrderId;
    }

    public void setReturnOrderId(Long returnOrderId) {
        this.returnOrderId = returnOrderId;
    }

    public String getReturnOrderNo() {
        return returnOrderNo;
    }

    public void setReturnOrderNo(String returnOrderNo) {
        this.returnOrderNo = returnOrderNo == null ? null : returnOrderNo.trim();
    }

    public String getBillingReverseNo() {
        return billingReverseNo;
    }

    public void setBillingReverseNo(String billingReverseNo) {
        this.billingReverseNo = billingReverseNo == null ? null : billingReverseNo.trim();
    }

    public String getBillingNo() {
        return billingNo;
    }

    public void setBillingNo(String billingNo) {
        this.billingNo = billingNo == null ? null : billingNo.trim();
    }

    public BigDecimal getOrderNetvalue() {
        return orderNetvalue;
    }

    public void setOrderNetvalue(BigDecimal orderNetvalue) {
        this.orderNetvalue = orderNetvalue;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

	public String getBillingOrign() {
		return billingOrign;
	}

	public void setBillingOrign(String billingOrign) {
		this.billingOrign = billingOrign;
	}
    
}