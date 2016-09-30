package com.sgm.dms.fol.bankTicket.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgm.dms.fol.bankTicket.api.BankTicketInterestMonthService;
import com.sgm.dms.fol.bankTicket.dto.BankTicketDraftsOverdueInterestDTO;
import com.sgm.dms.fol.bankTicket.dto.BankTicketInterestMonthDTO;
import com.sgm.dms.fol.bankTicket.repository.BankTicketInterestMonthDao;
import com.sgm.dms.fol.common.api.exception.ServiceAppException;

@Service("bankTicketInterestMonthService")
@Transactional(rollbackFor=Exception.class)
public class BankTicketInterestMonthServiceImpl implements BankTicketInterestMonthService {

	@Autowired
	private BankTicketInterestMonthDao bankTicketInterestMonthDao;

	@Override
	public List<BankTicketDraftsOverdueInterestDTO> findBankTicketInterestMonthByWhere(
			BankTicketInterestMonthDTO bankTicketInterestMonthDTO) throws ServiceAppException{
		List<BankTicketDraftsOverdueInterestDTO> resultList = null;
		try {
			resultList = bankTicketInterestMonthDao.findBankTicketInterestMonthByWhere(bankTicketInterestMonthDTO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return resultList;
	}

	@Override
	public Integer findBankTicketInterestMonthTotalNumWhere(BankTicketInterestMonthDTO bankTicketInterestMonthDTO)  throws ServiceAppException{
		return bankTicketInterestMonthDao.findBankTicketInterestMonthTotalNumWhere(bankTicketInterestMonthDTO);
	}
	
}
