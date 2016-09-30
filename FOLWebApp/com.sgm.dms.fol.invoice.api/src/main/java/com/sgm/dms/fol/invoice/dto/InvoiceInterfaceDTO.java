
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : invoice.api
 *
 * @File name : InvoiceNoRecivceRequestDTO.java
 *
 * @Author : Lujinglei
 *
 * @Date : 2016年5月27日
 *
----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2016年5月27日    Lujinglei    1.0
 *
 *
 *
 *
----------------------------------------------------------------------------------
 */
	
package com.sgm.dms.fol.invoice.dto;

import java.math.BigDecimal;

import com.sgm.dms.fol.common.api.domain.BaseDTO;

/**
 * 
 *
 * @author Lujinglei
 *  description
 * @date 2016年5月27日
 */

public class InvoiceInterfaceDTO extends BaseDTO{
    private Long  id;         //主键
    private String invoiceNo;   //发票号
    private String invoiceTitle;//发票抬头
    private String docunmentType;//凭证借贷类型(DA:贷方,DN:借方)
    private String documentDate;//凭证时间
    private String postDate;    //发送时间
    private String postPeriod; //发送周期(月份)
    private String currency;    //货币种类
    private String documentHeadtext;//头信息描述，默认为1
    private BigDecimal gross;       // 含税金额
    private BigDecimal linetotal;   // 不含税金额
    private BigDecimal partCost;    // 配件费用
    private BigDecimal labourCost;  // 工时费用
    private BigDecimal otherCost;   // 其它费用
    private BigDecimal tax;         //税金
    private String expressNo;       // 快递单号
    private String ascCode;         // SAP代码
    private Integer processStatus;  //进度状态
    private Integer valid;          //有效性
    private String remark;          //备注
    
    

    
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    
    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
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
     * @return the ascCode
     */
    public String getAscCode() {
        return ascCode;
    }

    
    /**
     * @param ascCode the ascCode to set
     */
    public void setAscCode(String ascCode) {
        this.ascCode = ascCode;
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
    
}
