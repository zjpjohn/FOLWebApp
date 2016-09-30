/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : bankTicket.service
*
* @File name : BankTicketDeadLineDao.java
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
	
package com.sgm.dms.fol.bankTicket.repository;

import java.sql.SQLException;
import java.util.List;

import com.sgm.dms.fol.bankTicket.domain.BankTicketDeadLinePo;
import com.sgm.dms.fol.bankTicket.dto.BankTicketDeadLineDTO;

/*
*
* @author DELL
* TODO description
* @date 2016年1月11日
*/

public interface BankTicketDeadLineDao {
    
    /**
     * 新增银票期限
     */
    public int addBankTicketDeadLine(BankTicketDeadLinePo bankTicketDeadLinePo) throws SQLException;
    
    /**
     * 删除银票期限
     */
    public int deleteBankTicketDeadLine(BankTicketDeadLinePo bankTicketDeadLinePo) throws SQLException;
    
    /**
     * 根据条件查找银票期限
     */
    public List<BankTicketDeadLineDTO> findBankTicketDeadLine(BankTicketDeadLinePo bankTicketDeadLinePo) throws SQLException;

    /**
     * 根据条件查找银票期限总数
     */
    public int findBankTicketDeadLineCount(BankTicketDeadLinePo bankTicketDeadLinePo)throws SQLException;

    /**
     * 更新银票期限
     */
    public int updateBankTicketDeadLine(BankTicketDeadLinePo bankTicketDeadLinePo)throws SQLException;
    
    //根据sapcode 和bran查询期限信息
    public List<BankTicketDeadLineDTO> findBankTicketLimitBySapCode(String sapCode)throws SQLException;
    
    public List<BankTicketDeadLineDTO> findBankTicketLimitByBrandAndDealerType(String brand,Integer dealerType)throws SQLException;
}
