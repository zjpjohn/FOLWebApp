/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : bankTicket.api
*
* @File name : BankTicketServiceImpl.java
*
* @Author : DELL
*
* @Date : 2016年1月19日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2016年1月19日    DELL    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/
	
package com.sgm.dms.fol.bankTicket.api;

import java.sql.SQLException;
import java.util.List;

import com.sgm.dms.fol.bankTicket.dto.BankTicketCollectDTO;
import com.sgm.dms.fol.bankTicket.dto.BankTicketExceptionInventoryDTO;
import com.sgm.dms.fol.bankTicket.dto.QueryBankTicketInfoDTO;
import com.sgm.dms.fol.common.api.exception.ServiceAppException;

/*
*
* @author DELL
* TODO description
* @date 2016年1月19日
*/

public interface BankTicketService {

    /**
     * 根据条件获取银票信息
     */
    public List<QueryBankTicketInfoDTO> findBankTicketInfoByWhere(QueryBankTicketInfoDTO bankTicketInfoDTO) throws ServiceAppException;
    
    /**
     * 根据条件获取银票总数信息
     */
    public int findBankTicketInfoCountByWhere(QueryBankTicketInfoDTO bankTicketInfoDTO) throws ServiceAppException;

    /**
     * 根据条件查询出银票数据集
     */
    public List<BankTicketCollectDTO> findBankTicketCollectDataList(BankTicketCollectDTO bankTicketCollectDTO) throws ServiceAppException;
    
    /** 
     * 根据条件查询出银票的异常清单
     * @author DELL
     * TODO description
     * @date 2016年3月29日
     * @param bankTicketExceptionInventoryDTO
     * @return
     * @throws ServiceAppException
     */
    public List<BankTicketExceptionInventoryDTO> findBankTicketExceptionInventoryList(BankTicketExceptionInventoryDTO bankTicketExceptionInventoryDTO) throws ServiceAppException,SQLException;
}
