/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : web.sgm
*
* @File name : BankTicketInterestDeatilQueryVo.java
*
* @Author : DELL
*
* @Date : 2016年2月14日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2016年2月14日    DELL    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/

package com.sgm.dms.fol.vo;

import com.sgm.dms.fol.common.api.domain.PageVo;

/*
*
* @author DELL
* TODO description
* @date 2016年2月14日
*/

public class BankTicketInterestDeatilQueryVo extends PageVo implements java.io.Serializable {

    private static final long serialVersionUID = 3271510816000634221L;
    private Long       id;
    private Long       eventId;
    private String     sapCode;
    private String     dealerName;
    private Integer    dealerType;
    private Integer    bankId;
    private String disCountAmount;
    private String countAmount;
    private String addTaxAmount;
    private Integer    year;
    private Integer    month;
    private Long       interestRateId;
    private Integer    status;
    private String     remark;
    private String     acceptanceNumber;
    private String     documentNumber;
    private String     bankChName;
    private String amount;
    private String     issueDate;
    private String     expirationDate;
    private Integer    deadLineDay;
    private Integer    freePeriodDay;
    private String     createBy;
    private String     createDate;
    private String     updateBy;
    private String     updateDate;
    private String     startIssueDate;
    private String     endIssueDate;
    private String     startExpirationDate;
    private String     endExpirationDate;
    private Integer    documentNumberExists;
    private Integer    beginNo;
    private Integer    endNo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
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

    public Integer getDealerType() {
        return dealerType;
    }

    public void setDealerType(Integer dealerType) {
        this.dealerType = dealerType;
    }

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public String getDisCountAmount() {
        return disCountAmount;
    }

    public void setDisCountAmount(String disCountAmount) {
        this.disCountAmount = disCountAmount;
    }

    public String getCountAmount() {
        return countAmount;
    }

    public void setCountAmount(String countAmount) {
        this.countAmount = countAmount;
    }

    public String getAddTaxAmount() {
        return addTaxAmount;
    }

    public void setAddTaxAmount(String addTaxAmount) {
        this.addTaxAmount = addTaxAmount;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Long getInterestRateId() {
        return interestRateId;
    }

    public void setInterestRateId(Long interestRateId) {
        this.interestRateId = interestRateId;
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

    public String getAcceptanceNumber() {
        return acceptanceNumber;
    }

    public void setAcceptanceNumber(String acceptanceNumber) {
        this.acceptanceNumber = acceptanceNumber;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getBankChName() {
        return bankChName;
    }

    public void setBankChName(String bankChName) {
        this.bankChName = bankChName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Integer getDeadLineDay() {
        return deadLineDay;
    }

    public void setDeadLineDay(Integer deadLineDay) {
        this.deadLineDay = deadLineDay;
    }

    public Integer getFreePeriodDay() {
        return freePeriodDay;
    }

    public void setFreePeriodDay(Integer freePeriodDay) {
        this.freePeriodDay = freePeriodDay;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getStartIssueDate() {
        return startIssueDate;
    }

    public void setStartIssueDate(String startIssueDate) {
        this.startIssueDate = startIssueDate;
    }

    public String getEndIssueDate() {
        return endIssueDate;
    }

    public void setEndIssueDate(String endIssueDate) {
        this.endIssueDate = endIssueDate;
    }

    public String getStartExpirationDate() {
        return startExpirationDate;
    }

    public void setStartExpirationDate(String startExpirationDate) {
        this.startExpirationDate = startExpirationDate;
    }

    public String getEndExpirationDate() {
        return endExpirationDate;
    }

    public void setEndExpirationDate(String endExpirationDate) {
        this.endExpirationDate = endExpirationDate;
    }

    public Integer getDocumentNumberExists() {
        return documentNumberExists;
    }

    public void setDocumentNumberExists(Integer documentNumberExists) {
        this.documentNumberExists = documentNumberExists;
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

}
