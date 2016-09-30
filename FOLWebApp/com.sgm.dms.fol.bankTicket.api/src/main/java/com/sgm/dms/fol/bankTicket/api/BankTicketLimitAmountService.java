/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : bankTicket.api
*
* @File name : BankTicketLimitAmountService.java
*
* @Author : DELL
*
* @Date : 2016年1月14日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2016年1月14日    DELL    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/
	
package com.sgm.dms.fol.bankTicket.api;

import java.sql.SQLException;
import java.util.List;

import com.sgm.dms.fol.bankTicket.dto.BankTicketLimitAmountDTO;
import com.sgm.dms.fol.common.api.exception.ServiceAppException;

/*
*
* @author DELL
* TODO description
* @date 2016年1月14日
*/

public interface BankTicketLimitAmountService {
    
    /**
     * 根据条件查询银票限额
     */
    public List<BankTicketLimitAmountDTO> findBankTicketLimitAmountByWhere(BankTicketLimitAmountDTO bankTicketLimitAmountDTO) throws ServiceAppException;

    /**
     * 新增银票限额
     */
    public void addBankTicketLimitAmount(BankTicketLimitAmountDTO bankTicketLimitAmountDTO,Long userId) throws ServiceAppException,SQLException;

    /**
     * 根据条件查询银票限额总数
     */
    public int findBankTicketLimitAmountCountByWhere(BankTicketLimitAmountDTO bankTicketLimitAmountDTO) throws ServiceAppException;

    /**
     * 根据更新银票限额信息
     */
    public void updateBankTicketLimitAmountById(BankTicketLimitAmountDTO bankTicketLimitAmountDTO,Long userId) throws ServiceAppException,SQLException;

    /**
     * 根据删除银票限额
     */
    public void deleteBankTicketLimitAmountById(BankTicketLimitAmountDTO bankTicketLimitAmountDTO,Long userId) throws ServiceAppException,SQLException;
    
    /**
     * 审核待删除银票限额
     */
    public int deleteBankTicketLimitAmountVerById(BankTicketLimitAmountDTO bankTicketLimitAmountDTO,Long userId) throws ServiceAppException;
    
    /**
     * 更新银票限额审核
     */
    public int updateBankTicketLimitAmount(BankTicketLimitAmountDTO bankTicketLimitAmountDTO,Long userId) throws ServiceAppException;

    /**
     * 银票限额审核通过
     */
    public void bankTicketLimitAmountAuditSuccess(List<BankTicketLimitAmountDTO> bankTicketLimitAmountDTOList,Long userId) throws ServiceAppException,SQLException;

    /**
     * 银票限额审核驳回
     */
    public void bankTicketLimitAmountAuditReject(List<BankTicketLimitAmountDTO> bankTicketLimitAmountDTOList,Long userId) throws ServiceAppException,SQLException;

}
