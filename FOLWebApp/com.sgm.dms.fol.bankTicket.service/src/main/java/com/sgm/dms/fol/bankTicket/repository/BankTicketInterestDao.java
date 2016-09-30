package com.sgm.dms.fol.bankTicket.repository;

import java.sql.SQLException;
import java.util.List;

import com.sgm.dms.fol.bankTicket.dto.BankTicketInterestDTO;

/*****
 * 
*
* @author Administrator
* 贴息信息
* @date 2016年1月14日
 */
public interface BankTicketInterestDao {
	
    /**
     * 查询票据贴息信息
     */
	public List<BankTicketInterestDTO> findBankTicketInterestList(BankTicketInterestDTO bankTicketInterestDTO) throws SQLException; 

	/**
     * 查询票据贴息总数
     */
    public int findBankTicketInterestCount(BankTicketInterestDTO bankTicketInterestDTO) throws SQLException;
}
