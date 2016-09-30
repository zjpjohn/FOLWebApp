/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : bankTicket.api
*
* @File name : BankTicketDeadLineService.java
*
* @Author : DELL
*
* @Date : 2016年1月11日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2016年1月11日    DELL    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/
	
package com.sgm.dms.fol.bankTicket.api;

import java.sql.SQLException;
import java.util.List;

import com.sgm.dms.fol.bankTicket.dto.BankTicketDeadLineDTO;
import com.sgm.dms.fol.common.api.exception.ServiceAppException;

/*
*
* @author DELL
* TODO description
* @date 2016年1月11日
*/

public interface BankTicketDeadLineService {
    
    public int addBankTicketDeadLineWithBrand(BankTicketDeadLineDTO bankTicketDeadLineDTO,Long userId) throws ServiceAppException;
    
    public int addBankTicketDeadLineWithSpecialDealer(BankTicketDeadLineDTO bankTicketDeadLineDTO,Long userId) throws ServiceAppException;
    
    public int deleteBankTicketDeadLine(BankTicketDeadLineDTO bankTicketDeadLineDTO,Long userId) throws ServiceAppException;
    
    public int deleteBankTicketDeadLineVer(BankTicketDeadLineDTO bankTicketDeadLineDTO,Long userId) throws ServiceAppException;
    
    public List<BankTicketDeadLineDTO> findBankTicketDeadLineWithBrand(BankTicketDeadLineDTO bankTicketDeadLineDTO) throws ServiceAppException;

    public int findBankTicketDeadLineCountWithBrand(BankTicketDeadLineDTO bankTicketDeadLineDTO)throws ServiceAppException;
    
    public List<BankTicketDeadLineDTO> findBankTicketDeadLineWithSpecialDealer(BankTicketDeadLineDTO bankTicketDeadLineDTO) throws ServiceAppException;

    public int findBankTicketDeadLineCountWithSpecialDealer(BankTicketDeadLineDTO bankTicketDeadLineDTO)throws ServiceAppException;

    public void updateBankTicketDeadLine(BankTicketDeadLineDTO bankTicketDeadLineDTO,Long userId)throws ServiceAppException,SQLException;
    
    public int updateAuidtstatus(BankTicketDeadLineDTO bankTicketDeadLineDTO,Long userId)throws ServiceAppException;
  
    /**
     * 银票期限审核通过
     */
    public void bankTicketDeadLineAuditSuccess(List<BankTicketDeadLineDTO> bankTicketDeadLineDTOList,Long userId) throws ServiceAppException,SQLException;
    
    /**
     * 银票期限审核驳回
     */
    public void bankTicketDeadLineAuditReject(List<BankTicketDeadLineDTO> bankTicketDeadLineDTOList,Long userId) throws ServiceAppException,SQLException;
}
