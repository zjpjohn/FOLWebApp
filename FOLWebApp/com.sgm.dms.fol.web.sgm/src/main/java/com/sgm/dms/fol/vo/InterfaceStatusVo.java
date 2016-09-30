
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : web.dealer
 *
 * @File name : InterfaceStatusVo.java
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

import com.sgm.dms.fol.common.api.domain.PageVo;

/**
 *
 * @author Lujinglei
 * TODO description
 * @date 2016年5月10日
 */

public class InterfaceStatusVo extends PageVo{
    private String financeWarranty;// 发票凭证
    private String invoiceNo;       // 发票号
    private String ascCode;         // SAP代码
    private Short process_status;           // 受理状态   1-SGM未接收; 2-SGM待处理;3-SGM退回;4-SAP处理成功;5-SAP处理失败
    private String processStatusName;      // 受理状态名称
    private String remark;          // 备注
    private String sapResolveDate;        // 处理时间
    private String startTime;    
    private String endTime;
    
    /**
     * @return the invoiceNo
     */
    public String getInvoiceNo() {
        return invoiceNo;
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
    public Short getProcess_status() {
        return process_status;
    }


    
    /**
     * @param process_status the process_status to set
     */
    public void setProcess_status(Short process_status) {
        this.process_status = process_status;
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
 
    
    
}
