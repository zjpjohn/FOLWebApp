
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : invoice.api
 *
 * @File name : InvoiceNoInfoDTO.java
 *
 * @Author : Lujinglei
 *
 * @Date : 2016年6月2日
 *
----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2016年6月2日    Lujinglei    1.0
 *
 *
 *
 *
----------------------------------------------------------------------------------
 */
	
package com.sgm.dms.fol.invoice.dto;

import java.math.BigDecimal;

/**
 *
 * @author Lujinglei
 * TODO description
 * @date 2016年6月2日
 */

public class InvoiceNoInfoDTO {

    private String subjectCode; //科目代码
    private String positionKey; //借贷方标识（40：借方，19：贷方）
    private String specialG;    //特殊代码(贷方：G,借方不需要)
    private BigDecimal amount;      //金额
    private String taxCode;     //税码
    private String costCenter; //成本中心
    private String assignment;  //发票号
    private String sapText;    //SAP处理时间
    private String subjectName;//科目名称
    private BigDecimal gross;       // 含税金额
    private BigDecimal linetotal;   // 不含税金额
    private String  coCode;        //跨公司代码
    private String flowNo;
    private String invoiceNo;   //发票号
    private String invoiceTitle;//发票抬头
    private String docunmentType;//凭证借贷类型(DA:贷方,DN:借方)
    private String documentDate;//凭证时间
    private String postDate;    //发送时间
    private String postPeriod; //发送周期(月份)
    private String currency;    //货币种类
    private String documentHeadtext;//头信息描述，默认为1
    private BigDecimal partCost;    // 配件费用
    private BigDecimal labourCost;  // 工时费用
    private BigDecimal otherCost;   // 其它费用
    private BigDecimal tax;         //税金
    private String expressNo;       // 快递单号
    private String sapCode;         // SAP代码
    private Integer processStatus;  //进度状态
    private Integer valid;          //有效性
    private String remark;          //备注
    private String certificateType; //凭证借贷类型(DA(19):贷方,DN:借方(40))
    /**
     * @return the subjectCode
     */
    public String getSubjectCode() {
        return subjectCode;
    }
    
    /**
     * @param subjectCode the subjectCode to set
     */
    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }
    
    /**
     * @return the positionKey
     */
    public String getPositionKey() {
        return positionKey;
    }
    
    /**
     * @param positionKey the positionKey to set
     */
    public void setPositionKey(String positionKey) {
        this.positionKey = positionKey;
    }
    
    /**
     * @return the specialG
     */
    public String getSpecialG() {
        return specialG;
    }
    
    /**
     * @param specialG the specialG to set
     */
    public void setSpecialG(String specialG) {
        this.specialG = specialG;
    }
    
    
    
    /**
     * @return the amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    
    /**
     * @param amount the amount to set
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * @return the taxCode
     */
    public String getTaxCode() {
        return taxCode;
    }
    
    /**
     * @param taxCode the taxCode to set
     */
    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }
    
    /**
     * @return the costCenter
     */
    public String getCostCenter() {
        return costCenter;
    }
    
    /**
     * @param costCenter the costCenter to set
     */
    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }
    
    /**
     * @return the assignment
     */
    public String getAssignment() {
        return assignment;
    }
    
    /**
     * @param assignment the assignment to set
     */
    public void setAssignment(String assignment) {
        this.assignment = assignment;
    }
    
    
    /**
     * @return the sapText
     */
    public String getSapText() {
        return sapText;
    }

    
    /**
     * @param sapText the sapText to set
     */
    public void setSapText(String sapText) {
        this.sapText = sapText;
    }

    
    /**
     * @return the subjectName
     */
    public String getSubjectName() {
        return subjectName;
    }

    
    /**
     * @param subjectName the subjectName to set
     */
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
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
     * @return the coCode
     */
    public String getCoCode() {
        return coCode;
    }

    
    /**
     * @param coCode the coCode to set
     */
    public void setCoCode(String coCode) {
        this.coCode = coCode;
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
     * @return the processStatus
     */
    public Integer getProcessStatus() {
        return processStatus;
    }

    
    /**
     * @param processStatus the processStatus to set
     */
    public void setProcessStatus(Integer processStatus) {
        this.processStatus = processStatus;
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
     * @return the docunmentType
     */
    public String getDocunmentType() {
        return docunmentType;
    }
    
    /**
     * @param docunmentType the docunmentType to set
     */
    public void setDocunmentType(String docunmentType) {
        this.docunmentType = docunmentType;
    }
    
    /**
     * @return the documentDate
     */
    public String getDocumentDate() {
        return documentDate;
    }
    
    /**
     * @param documentDate the documentDate to set
     */
    public void setDocumentDate(String documentDate) {
        this.documentDate = documentDate;
    }
    
    /**
     * @return the postDate
     */
    public String getPostDate() {
        return postDate;
    }
    
    /**
     * @param postDate the postDate to set
     */
    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }
    
    /**
     * @return the postPeriod
     */
    public String getPostPeriod() {
        return postPeriod;
    }
    
    /**
     * @param postPeriod the postPeriod to set
     */
    public void setPostPeriod(String postPeriod) {
        this.postPeriod = postPeriod;
    }
    
    /**
     * @return the currency
     */
    public String getCurrency() {
        return currency;
    }
    
    /**
     * @param currency the currency to set
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    
    /**
     * @return the documentHeadtext
     */
    public String getDocumentHeadtext() {
        return documentHeadtext;
    }
    
    /**
     * @param documentHeadtext the documentHeadtext to set
     */
    public void setDocumentHeadtext(String documentHeadtext) {
        this.documentHeadtext = documentHeadtext;
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
     * @return the valid
     */
    public Integer getValid() {
        return valid;
    }
    
    /**
     * @param valid the valid to set
     */
    public void setValid(Integer valid) {
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
     * @return the certificateType
     */
    public String getCertificateType() {
        return certificateType;
    }

    
    /**
     * @param certificateType the certificateType to set
     */
    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }
 

}
