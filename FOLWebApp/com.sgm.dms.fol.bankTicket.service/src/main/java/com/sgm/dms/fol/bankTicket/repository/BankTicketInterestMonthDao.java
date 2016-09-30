package com.sgm.dms.fol.bankTicket.repository;

import java.sql.SQLException;
import java.util.List;

import com.sgm.dms.fol.bankTicket.dto.BankTicketDraftsOverdueInterestDTO;
import com.sgm.dms.fol.bankTicket.dto.BankTicketInterestIssueAndConfirmDTO;
import com.sgm.dms.fol.bankTicket.dto.BankTicketInterestMonthDTO;

/*****
 * 
*
* @author Administrator
* 贴息月度信息
* @date 2016年1月14日
 */
public interface BankTicketInterestMonthDao {
	
	/****
	 * 检测该月该用户是否已统计贴息
	 */
	public Integer  findBankTicketIsHasInvoiceNumberBySapCodeAndTime(String sapCode,String year,String Month)throws SQLException;
	
	/*****
	 * 保存用户统计贴息信息
	 */
	public Integer saveBankTicketInterestMonth(BankTicketInterestMonthDTO bankTicketInterestMonthDTO)throws SQLException;
	
	/*****
	 * 更新用户统计贴息信息
	 */
	public Integer updateBankTicketInterestMonth(BankTicketInterestMonthDTO bankTicketInterestMonthDTO)throws SQLException;
	
	
	/****
	 * 根据sapcode 和年月更新发票号码和快递单号
	 */
	public int updateBankTicketInterestMonthBySapCodeAndTime(BankTicketInterestMonthDTO bankTicketInterestMonthDTO)throws SQLException;
	
	/**
	 * 根据ID更新发布状态或者确认状态 
	 */
	public int updateBankTicketInterestIssueStatusOrConfirmStatusById(BankTicketInterestIssueAndConfirmDTO bankTicketInterestIssueAndConfirmDTO) throws SQLException;

	/**
	 * 根据条件查询月度贴息
	 */
	public List<BankTicketDraftsOverdueInterestDTO> findBankTicketInterestMonthByWhere(BankTicketInterestMonthDTO bankTicketInterestMonthDTO) throws SQLException;
	
	public Integer findBankTicketInterestMonthTotalNumWhere(BankTicketInterestMonthDTO bankTicketInterestMonthDTO);
	
}
