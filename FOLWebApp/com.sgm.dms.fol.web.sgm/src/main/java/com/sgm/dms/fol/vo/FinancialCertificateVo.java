
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : web.sgm
 *
 * @File name : FinancialCertificateVo.java
 *
 * @Author : Lujinglei
 *
 * @Date : 2016年5月10日
 *
----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2016年5月10日    Lujinglei    1.0
 *
 *
 *
 *
----------------------------------------------------------------------------------
 */
	
package com.sgm.dms.fol.vo;

import java.math.BigDecimal;

import com.sgm.dms.fol.common.api.domain.PageVo;

/**
 *
 * @author Lujinglei
 * TODO description
 * @date 2016年5月10日
 */

public class FinancialCertificateVo extends PageVo{
    
    private String sapCode;         // SAP代码

    private String invoiceNo;       // 发票号
    
    private String invoiceCertificate;// 发票凭证
    
    private String approveMan;      // 审核人
    
    private String createDate;      //提交sap时间
    
    private BigDecimal tax;         // 税金
    
    private BigDecimal gross;       // 含税金额
    
    private BigDecimal linetotal;   // 不含税金额
    
    private String startTime;
    
    private String endTime;
    
    private String token;
    
    private Integer num;

    
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
     * @return the createDate
     */
    public String getCreateDate() {
        return createDate;
    }


    
    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
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
    
}
