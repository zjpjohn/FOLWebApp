
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : web.dealer
 *
 * @File name : ClaimBillVo.java
 *
 * @Author : Lujinglei
 *
 * @Date : 2016年5月3日
 *
----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2016年5月3日    Lujinglei    1.0
 *
 *
 *
 *
----------------------------------------------------------------------------------
 */
	
package com.dealer.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.sgm.dms.fol.common.api.domain.PageVo;
import com.sgm.solution.framework.dataAuthority.annotations.SGMValidationFieldRefIds;
import com.sgm.solution.framework.dataAuthority.annotations.SGMValidationFieldSign;
import com.sgm.solution.framework.dataAuthority.annotations.SGMValidationResource;

/**
 *
 * @author Lujinglei
 * TODO 索赔发票跟踪查询VO
 * @date 2016年5月3日
 */
@SGMValidationResource
public class InvoiceFollowVo extends PageVo{
    
    private Integer isChecked;
    @SGMValidationFieldRefIds(seq=1)
    private String invoiceNo;        //发票号
    private String expressNo;       // 快递单号
    private String wrDate;          //索赔月
    private String wrType;          //索赔类型
    private String flowNo;            // 流水号

    private String invoiceTitle;    // 发票抬头

    private BigDecimal gross;       // 含税金额

    private BigDecimal linetotal;   // 不含税金额

    private BigDecimal partCost;    // 配件费用

    private BigDecimal labourCost;  // 工时费用

    private BigDecimal tax;         // 税金

    private String sapCode;         // SAP代码

    private String proposer;        // 申请人

    private Date applyDate;         // 申请时间

    private String reporter;        // 上报人

    private String reportDate;        // 上报时间

    private String year;            // 年份

    private String month;           // 月份

    private String invoiceCertificate;// 发票凭证
    @SGMValidationFieldRefIds(seq=2)
    private Short status;           // 受理状态   1-SGM未接收; 2-SGM待处理;3-SGM退回;4-SAP处理成功;5-SAP处理失败
    
    private String statusName;      // 受理状态名称
    
    private String postSapDate;       //受理時間
    
    private BigDecimal otherCost;   // 其它费用

    private String approveMan;      // 审核人

    private String oldInvoiceNum;   // 旧发票号

    private Short valid;            // 有效状态     

    private String remark;          // 备注
    private String startTime;
    private String endTime;
    
    private String token;
    
    private Integer num;
    @SGMValidationFieldSign
    private String sign;
    
    /**
     * @return the isChecked
     */
    public Integer getIsChecked() {
        return isChecked;
    }

    
    /**
     * @param isChecked the isChecked to set
     */
    public void setIsChecked(Integer isChecked) {
        this.isChecked = isChecked;
    }

    
    /**
     * @return the invoiceNo
     */
    public String getInvoiceNo() {
        return invoiceNo;
    }

    
    /**
     * @param invoiceNo the invoiceNo to set
     */
    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    
    
    /**
     * @return the expressNo
     */
    public String getExpressNo() {
        return expressNo;
    }


    
    /**
     * @param expressNo the expressNo to set
     */
    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }


    /**
     * @return the wrDate
     */
    public String getWrDate() {
        return wrDate;
    }

    
    /**
     * @param wrDate the wrDate to set
     */
    public void setWrDate(String wrDate) {
        this.wrDate = wrDate;
    }

    
    
    /**
     * @return the wrType
     */
    public String getWrType() {
        return wrType;
    }


    
    /**
     * @param wrType the wrType to set
     */
    public void setWrType(String wrType) {
        this.wrType = wrType;
    }


    
    
    /**
     * @return the flowNo
     */
    public String getFlowNo() {
        return flowNo;
    }


    
    /**
     * @param flowNo the flowNo to set
     */
    public void setFlowNo(String flowNo) {
        this.flowNo = flowNo;
    }


    /**
     * @return the invoiceTitle
     */
    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    
    /**
     * @param invoiceTitle the invoiceTitle to set
     */
    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    
    /**
     * @return the gross
     */
    public BigDecimal getGross() {
        return gross;
    }

    
    /**
     * @param gross the gross to set
     */
    public void setGross(BigDecimal gross) {
        this.gross = gross;
    }

    
    /**
     * @return the linetotal
     */
    public BigDecimal getLinetotal() {
        return linetotal;
    }

    
    /**
     * @param linetotal the linetotal to set
     */
    public void setLinetotal(BigDecimal linetotal) {
        this.linetotal = linetotal;
    }

    
    /**
     * @return the partCost
     */
    public BigDecimal getPartCost() {
        return partCost;
    }

    
    /**
     * @param partCost the partCost to set
     */
    public void setPartCost(BigDecimal partCost) {
        this.partCost = partCost;
    }

    
    /**
     * @return the labourCost
     */
    public BigDecimal getLabourCost() {
        return labourCost;
    }

    
    /**
     * @param labourCost the labourCost to set
     */
    public void setLabourCost(BigDecimal labourCost) {
        this.labourCost = labourCost;
    }

    
    /**
     * @return the tax
     */
    public BigDecimal getTax() {
        return tax;
    }

    
    /**
     * @param tax the tax to set
     */
    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    
    /**
     * @return the sapCode
     */
    public String getSapCode() {
        return sapCode;
    }

    
    /**
     * @param sapCode the sapCode to set
     */
    public void setSapCode(String sapCode) {
        this.sapCode = sapCode;
    }

    
    /**
     * @return the proposer
     */
    public String getProposer() {
        return proposer;
    }

    
    /**
     * @param proposer the proposer to set
     */
    public void setProposer(String proposer) {
        this.proposer = proposer;
    }

    
    /**
     * @return the applyDate
     */
    public Date getApplyDate() {
        return applyDate;
    }

    
    /**
     * @param applyDate the applyDate to set
     */
    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    
    /**
     * @return the reporter
     */
    public String getReporter() {
        return reporter;
    }

    
    /**
     * @param reporter the reporter to set
     */
    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    
    
    /**
     * @return the reportDate
     */
    public String getReportDate() {
        return reportDate;
    }


    
    /**
     * @param reportDate the reportDate to set
     */
    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }


    /**
     * @return the year
     */
    public String getYear() {
        return year;
    }

    
    /**
     * @param year the year to set
     */
    public void setYear(String year) {
        this.year = year;
    }

    
    /**
     * @return the month
     */
    public String getMonth() {
        return month;
    }

    
    /**
     * @param month the month to set
     */
    public void setMonth(String month) {
        this.month = month;
    }

    
    /**
     * @return the invoiceCertificate
     */
    public String getInvoiceCertificate() {
        return invoiceCertificate;
    }

    
    /**
     * @param invoiceCertificate the invoiceCertificate to set
     */
    public void setInvoiceCertificate(String invoiceCertificate) {
        this.invoiceCertificate = invoiceCertificate;
    }

    
    /**
     * @return the status
     */
    public Short getStatus() {
        return status;
    }

    
    /**
     * @param status the status to set
     */
    public void setStatus(Short status) {
        this.status = status;
    }
    
    
    /**
     * @return the statusName
     */
    public String getStatusName() {
        return statusName;
    }


    
    /**
     * @param statusName the statusName to set
     */
    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }


    /**
     * @return the postSapDate
     */
    public String getPostSapDate() {
        return postSapDate;
    }

    
    /**
     * @param postSapDate the postSapDate to set
     */
    public void setPostSapDate(String postSapDate) {
        this.postSapDate = postSapDate;
    }

    
    /**
     * @return the otherCost
     */
    public BigDecimal getOtherCost() {
        return otherCost;
    }

    
    /**
     * @param otherCost the otherCost to set
     */
    public void setOtherCost(BigDecimal otherCost) {
        this.otherCost = otherCost;
    }

    
    /**
     * @return the approveMan
     */
    public String getApproveMan() {
        return approveMan;
    }

    
    /**
     * @param approveMan the approveMan to set
     */
    public void setApproveMan(String approveMan) {
        this.approveMan = approveMan;
    }

    
    /**
     * @return the oldInvoiceNum
     */
    public String getOldInvoiceNum() {
        return oldInvoiceNum;
    }

    
    /**
     * @param oldInvoiceNum the oldInvoiceNum to set
     */
    public void setOldInvoiceNum(String oldInvoiceNum) {
        this.oldInvoiceNum = oldInvoiceNum;
    }

    
    /**
     * @return the valid
     */
    public Short getValid() {
        return valid;
    }

    
    /**
     * @param valid the valid to set
     */
    public void setValid(Short valid) {
        this.valid = valid;
    }

    
    /**
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    
    /**
     * @param remark the remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    
    /**
     * @return the startTime
     */
    public String getStartTime() {
        return startTime;
    }

    
    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    
    /**
     * @return the endTime
     */
    public String getEndTime() {
        return endTime;
    }

    
    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    
    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    
    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    
    /**
     * @return the num
     */
    public Integer getNum() {
        return num;
    }

    
    /**
     * @param num the num to set
     */
    public void setNum(Integer num) {
        this.num = num;
    }


    
    /**
     * @return the sign
     */
    public String getSign() {
        return sign;
    }


    
    /**
     * @param sign the sign to set
     */
    public void setSign(String sign) {
        this.sign = sign;
    }
    

    
 
}
