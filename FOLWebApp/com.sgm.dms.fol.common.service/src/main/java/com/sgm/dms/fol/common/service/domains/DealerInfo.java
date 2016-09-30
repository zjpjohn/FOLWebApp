package com.sgm.dms.fol.common.service.domains;

import java.util.Date;

public class DealerInfo {
    private String sapCode;

    private Long officalDealerId;

    private String dealerCode;

    private String dealerFullname;

    private String dealerName;

    private String email;

    private String dealerType;

    private String dealerTypeName;

    private String sapDealerType;

    private String sapDealerTypeName;

    private Long statusCode;

    private Date createDate;

    private Long createBy;

    private Date updateDate;

    private Long updateBy;

    private String warrantyCode;

    public String getSapCode() {
        return sapCode;
    }

    public void setSapCode(String sapCode) {
        this.sapCode = sapCode == null ? null : sapCode.trim();
    }

    public Long getOfficalDealerId() {
        return officalDealerId;
    }

    public void setOfficalDealerId(Long officalDealerId) {
        this.officalDealerId = officalDealerId;
    }

    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode == null ? null : dealerCode.trim();
    }

    public String getDealerFullname() {
        return dealerFullname;
    }

    public void setDealerFullname(String dealerFullname) {
        this.dealerFullname = dealerFullname == null ? null : dealerFullname.trim();
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName == null ? null : dealerName.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getDealerType() {
        return dealerType;
    }

    public void setDealerType(String dealerType) {
        this.dealerType = dealerType == null ? null : dealerType.trim();
    }

    public String getDealerTypeName() {
        return dealerTypeName;
    }

    public void setDealerTypeName(String dealerTypeName) {
        this.dealerTypeName = dealerTypeName == null ? null : dealerTypeName.trim();
    }

    public String getSapDealerType() {
        return sapDealerType;
    }

    public void setSapDealerType(String sapDealerType) {
        this.sapDealerType = sapDealerType == null ? null : sapDealerType.trim();
    }

    public String getSapDealerTypeName() {
        return sapDealerTypeName;
    }

    public void setSapDealerTypeName(String sapDealerTypeName) {
        this.sapDealerTypeName = sapDealerTypeName == null ? null : sapDealerTypeName.trim();
    }

    public Long getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Long statusCode) {
        this.statusCode = statusCode;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public String getWarrantyCode() {
        return warrantyCode;
    }

    public void setWarrantyCode(String warrantyCode) {
        this.warrantyCode = warrantyCode == null ? null : warrantyCode.trim();
    }
}