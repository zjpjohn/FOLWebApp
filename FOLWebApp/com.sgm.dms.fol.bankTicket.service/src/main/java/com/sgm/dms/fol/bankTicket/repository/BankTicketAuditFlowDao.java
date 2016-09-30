/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : bankTicket.service
*
* @File name : BankTicketAuditFlowDao.java
*
* @Author : DELL
*
* @Date : 2016年1月6日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2016年1月6日    DELL    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/
	
package com.sgm.dms.fol.bankTicket.repository;

import java.sql.SQLException;

import com.sgm.dms.fol.bankTicket.dto.BankTicketAuditFlowDTO;

/*
*
* @author DELL
* TODO description
* @date 2016年1月6日
*/

public interface BankTicketAuditFlowDao {
    
    /**
     * 新增银票审核流水记录
     */
    public int addBankTicketAuditFlowRecord(BankTicketAuditFlowDTO bankTicketAuditFlowDTO) throws SQLException;
}
