
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : invoice.api
 *
 * @File name : TI_ITEM.java
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

import com.sgm.dms.fol.common.api.domain.BaseDTO;

/**
 *
 * @author Lujinglei
 * TODO description
 * @date 2016年5月27日
 */

public class SapReturnItemsDTO extends BaseDTO{
    
    private Long id;
    private String  financeWarranty;//财务凭证
    private String  invoiceNo;      //发票号
    private String  ascCode;        //售后代码
    private Integer process_status;//处理状态
    private String  co_code;      //跨公司代码
    private String sgmResolveDate;//SGM完成时间
    private String sapResolveDate;//Sap完成时间
    private String remark;
    private Integer valid;
    private String processStatusName;
    private String startTime;    
    private String endTime;
    private Integer beginNo;
    private Integer endNo;
    
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
     * @return the financeWarranty
     */
    public String getFinanceWarranty() {
        return financeWarranty;
    }
    
    /**
     * @param financeWarranty the financeWarranty to set
     */
    public void setFinanceWarranty(String financeWarranty) {
        this.financeWarranty = financeWarranty;
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
     * @return the process_status
     */
    public Integer getProcess_status() {
        return process_status;
    }
    
    /**
     * @param process_status the process_status to set
     */
    public void setProcess_status(Integer process_status) {
        this.process_status = process_status;
    }
    
    /**
     * @return the co_code
     */
    public String getCo_code() {
        return co_code;
    }
    
    /**
     * @param co_code the co_code to set
     */
    public void setCo_code(String co_code) {
        this.co_code = co_code;
    }
    
    /**
     * @return the sgmResolveDate
     */
    public String getSgmResolveDate() {
        return sgmResolveDate;
    }
    
    /**
     * @param sgmResolveDate the sgmResolveDate to set
     */
    public void setSgmResolveDate(String sgmResolveDate) {
        this.sgmResolveDate = sgmResolveDate;
    }
    
    /**
     * @return the sapResolveDate
     */
    public String getSapResolveDate() {
        return sapResolveDate;
    }
    
    /**
     * @param sapResolveDate the sapResolveDate to set
     */
    public void setSapResolveDate(String sapResolveDate) {
        this.sapResolveDate = sapResolveDate;
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
     * @return the processStatusName
     */
    public String getProcessStatusName() {
        return processStatusName;
    }


    
    /**
     * @param processStatusName the processStatusName to set
     */
    public void setProcessStatusName(String processStatusName) {
        this.processStatusName = processStatusName;
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
     * @return the beginNo
     */
    public Integer getBeginNo() {
        return beginNo;
    }


    
    /**
     * @param beginNo the beginNo to set
     */
    public void setBeginNo(Integer beginNo) {
        this.beginNo = beginNo;
    }


    
    /**
     * @return the endNo
     */
    public Integer getEndNo() {
        return endNo;
    }


    
    /**
     * @param endNo the endNo to set
     */
    public void setEndNo(Integer endNo) {
        this.endNo = endNo;
    }
    
 
}
