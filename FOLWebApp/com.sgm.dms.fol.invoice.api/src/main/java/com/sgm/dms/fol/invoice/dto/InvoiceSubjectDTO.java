
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : invoice.api
 *
 * @File name : InvoiceNoReciveResultDTO.java
 *
 * @Author : Lujinglei
 *
 * @Date : 2016年6月1日
 *
----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2016年6月1日    Lujinglei    1.0
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
 * @author Lujinglei
 * TODO description
 * @date 2016年6月1日
 */

public class InvoiceSubjectDTO extends BaseDTO{

    private Long  id;
    private Long invoiceId;   //发票接口ID
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
    private Integer  valid;          //有效性
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
     * @return the invoiceId
     */
    public Long getInvoiceId() {
        return invoiceId;
    }


    
    /**
     * @param invoiceId the invoiceId to set
     */
    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }


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
