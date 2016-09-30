/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : bankTicket.api
*
* @File name : BankTicketInterestDeatilService.java
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
	
package com.sgm.dms.fol.bankTicket.api;

import java.util.List;

import com.sgm.dms.fol.bankTicket.dto.BankTicketInterestDeatilQueryDTO;
import com.sgm.dms.fol.common.api.exception.ServiceAppException;

/*
*
* @author DELL
* TODO description
* @date 2016年1月26日
*/

public interface BankTicketInterestDeatilService {
    
    /**
     * find bankTicket interest deatil
     */
    public List<BankTicketInterestDeatilQueryDTO> findBankTicketInterestDeatilByWhere(BankTicketInterestDeatilQueryDTO bankTicketInterestDeatilDTO) throws ServiceAppException;

    /**
     * find bankTicket interest deatil count
     */
    public int findBankTicketInterestDeatilCountByWhere(BankTicketInterestDeatilQueryDTO bankTicketInterestDeatilDTO) throws ServiceAppException;
}
