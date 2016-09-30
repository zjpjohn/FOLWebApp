/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : bankTicket.service
*
* @File name : BankTicketAuditFlowServiceImpl.java
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
	
package com.sgm.dms.fol.bankTicket.service;

import java.sql.SQLException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgm.dms.fol.bankTicket.api.BankTicketAuditFlowService;
import com.sgm.dms.fol.bankTicket.dto.BankTicketAuditFlowDTO;
import com.sgm.dms.fol.bankTicket.repository.BankTicketAuditFlowDao;
import com.sgm.dms.fol.common.service.utils.CommonUtils;

/*
*
* @author DELL
* TODO description
* @date 2016年1月6日
*/
@Service("BankTicketAuditFlowService")
@Transactional(rollbackFor=Exception.class)
public class BankTicketAuditFlowServiceImpl implements BankTicketAuditFlowService{
    protected Logger logger = LogManager.getLogger(this.getClass());
    
    @Autowired
    private BankTicketAuditFlowDao bankTicketAuditFlowDao;

    /*
    *
    * @author DELL
    * @date 2016年1月6日
    * @param auditStatus
    * @param auditMsg
    * @return
    * @throws SQLException
    * (non-Javadoc)
    * @see com.sgm.dms.fol.bankTicket.api.BankTicketAuditFlowService#addBankTicketAuditFlowRecord(java.lang.Integer, java.lang.String)
    */
    	
    @Override
    public int addBankTicketAuditFlowRecord(Integer auditStatus, String auditMsg,Integer businessCode,Long referTableId,Long userId) throws SQLException {
        BankTicketAuditFlowDTO bankTicketAuditFlowDTO=new BankTicketAuditFlowDTO();
        bankTicketAuditFlowDTO.setAuditStatus(auditStatus);
        bankTicketAuditFlowDTO.setAuditMsg(auditMsg);
        bankTicketAuditFlowDTO.setBusinessCode(businessCode);
        bankTicketAuditFlowDTO.setReferTableId(referTableId);
        bankTicketAuditFlowDTO.setCommonAddData(userId);
        bankTicketAuditFlowDTO.setCommonUpdateData(userId);
        CommonUtils.filterSpecialWord(bankTicketAuditFlowDTO);
        int result=bankTicketAuditFlowDao.addBankTicketAuditFlowRecord(bankTicketAuditFlowDTO);
        return result;
    }
  

}
