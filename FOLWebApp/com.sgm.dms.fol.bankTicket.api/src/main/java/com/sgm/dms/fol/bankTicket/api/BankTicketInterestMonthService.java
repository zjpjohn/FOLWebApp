package com.sgm.dms.fol.bankTicket.api;

import java.util.List;

import com.sgm.dms.fol.bankTicket.dto.BankTicketDraftsOverdueInterestDTO;
import com.sgm.dms.fol.bankTicket.dto.BankTicketInterestMonthDTO;
import com.sgm.dms.fol.common.api.exception.ServiceAppException;

/**
 * 
 * Title: Class BankTicketInterestMonthService.java
 * Description:银票贴息月度service 
 *
 *
 * @author wangfl
 * @version 0.0.1
 */
public interface BankTicketInterestMonthService {
	
	public List<BankTicketDraftsOverdueInterestDTO> findBankTicketInterestMonthByWhere(BankTicketInterestMonthDTO bankTicketInterestMonthDTO) throws ServiceAppException;
	
	public Integer findBankTicketInterestMonthTotalNumWhere(BankTicketInterestMonthDTO bankTicketInterestMonthDTO) throws ServiceAppException;

}
