/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : bankTicket.service
*
* @File name : BankTicketLimitAmountDao.java
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
	
package com.sgm.dms.fol.bankTicket.repository;

import java.sql.SQLException;
import java.util.List;

import com.sgm.dms.fol.bankTicket.domain.BankTicketLimitAmountPo;
import com.sgm.dms.fol.bankTicket.dto.BankTicketLimitAmountDTO;

/*
*
* @author DELL
* TODO description
* @date 2016年1月14日
*/

public interface BankTicketLimitAmountDao {
    
    /**
     * 根据条件查询银票限额
     */
    public List<BankTicketLimitAmountDTO> findBankTicketLimitAmountByWhere(BankTicketLimitAmountDTO bankTicketLimitAmountDTO) throws SQLException;

    /**
     * 新增银票限额
     */
    public int addBankTicketLimitAmount(BankTicketLimitAmountPo bankTicketLimitAmountPo) throws SQLException;

    /**
     * 根据条件查询银票限额总数
     */
    
    public int findBankTicketLimitAmountCountByWhere(BankTicketLimitAmountDTO bankTicketLimitAmountDTO) throws SQLException;

    /**
     * 根据更新银票限额信息
     */
    public int updateBankTicketLimitAmountById(BankTicketLimitAmountPo bankTicketLimitAmountPo) throws SQLException;

    /**
     * 根据删除银票限额
     */
    public int deleteBankTicketLimitAmountById(BankTicketLimitAmountPo bankTicketLimitAmountPo) throws SQLException;
}
