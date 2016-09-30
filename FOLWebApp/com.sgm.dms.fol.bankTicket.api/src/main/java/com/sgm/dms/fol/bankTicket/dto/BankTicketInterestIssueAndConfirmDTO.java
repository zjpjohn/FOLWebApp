/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : bankTicket.api
*
* @File name : BankTicketInterestIssueAndConfirmDTO.java
*
* @Author : DELL
*
* @Date : 2016年1月26日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2016年1月26日    DELL    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/
	
package com.sgm.dms.fol.bankTicket.dto;

import com.sgm.dms.fol.common.api.domain.BaseDTO;

/*
*
* @author DELL
* TODO description
* @date 2016年1月26日
*/

public class BankTicketInterestIssueAndConfirmDTO extends BaseDTO{

    private Long id;
    private Integer issueStatus;
    private Integer confirmStatus;
    private String issueStatusName;
    private String confirmStatusName;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Integer getIssueStatus() {
        return issueStatus;
    }
    
    public void setIssueStatus(Integer issueStatus) {
        this.issueStatus = issueStatus;
    }
    
    public Integer getConfirmStatus() {
        return confirmStatus;
    }
    
    public void setConfirmStatus(Integer confirmStatus) {
        this.confirmStatus = confirmStatus;
    }
    
    public String getIssueStatusName() {
        return issueStatusName;
    }
    
    public void setIssueStatusName(String issueStatusName) {
        this.issueStatusName = issueStatusName;
    }
    
    public String getConfirmStatusName() {
        return confirmStatusName;
    }
    
    public void setConfirmStatusName(String confirmStatusName) {
        this.confirmStatusName = confirmStatusName;
    }
    
    
}
