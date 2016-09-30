package com.sgm.dms.fol.bankTicket.repository;

import java.sql.SQLException;
import java.util.List;

import com.sgm.dms.fol.bankTicket.dto.BankTicketCollectDTO;
import com.sgm.dms.fol.bankTicket.dto.BankTicketDraftsOverdueInterestDTO;
import com.sgm.dms.fol.bankTicket.dto.BankTicketExceptionInventoryDTO;
import com.sgm.dms.fol.bankTicket.dto.BankTicketInfoDTO;
import com.sgm.dms.fol.bankTicket.dto.BankTicketValidAmountDTO;
import com.sgm.dms.fol.bankTicket.dto.QueryBankTicketInfoDTO;

/****
 * 银票DAO
*
* @author zhang bao
* TODO description
* @date 2016年1月5日
 */
public interface BankTicketDAO {
	
	/***
	 * 根据月份银票信息
	 */
	public List<BankTicketInfoDTO> findBankTicketDataByYearMonth(String yearMonth) throws SQLException;
	
	/****
	 * 统计当月银票客户客户信息
	 */
	public List<BankTicketDraftsOverdueInterestDTO> findBnakTicketDealerInfoByYearMonth(BankTicketDraftsOverdueInterestDTO interest)throws SQLException;
	
	/*****
	 * 统计当月银票客户信息count
	 */
	public int findBnakTicketDealerInfoCountByYearMonth(String yearMonth)throws SQLException;
	/***
	 * 根据月份银票信息
	 */
	public Integer findBankTicketDataCountByYearMonth(String yearMonth) throws SQLException;
	
	/****
	 * 检测该月这些数据是否已经生成发票号
	 */
	public Integer findBankTicketIsHasInvoiceNumberBySapCodeAndTime(String sapCode,String yearMonth)throws SQLException;

	/**
	 * 根据条件获取银票信息
	 */
	public List<QueryBankTicketInfoDTO> findBankTicketInfoByWhere(QueryBankTicketInfoDTO bankTicketInfoDTO) throws SQLException;

	/**
     * 根据条件获取银票信息总数
     */
	public int findBankTicketInfoCountByWhere(QueryBankTicketInfoDTO bankTicketInfoDTO) throws SQLException;
	
	/**
	 * 根据条件查询银票总额度
	 */
	public List<BankTicketCollectDTO> findBankTicketTotalAmount(BankTicketCollectDTO bankTicketCollectDTO)throws SQLException;
	
	/**
     * 根据条件查询银票已使用额度
     */
    public List<BankTicketCollectDTO> findBankTicketUsedAmount(BankTicketCollectDTO bankTicketCollectDTO)throws SQLException;
    
    /**
     * 根据条件查询5天内到期额度
     */
    public List<BankTicketCollectDTO> findBankTicketFiveDayWithInAmount(BankTicketCollectDTO bankTicketCollectDTO)throws SQLException;

    /*****
     * 可用额度查询(dealer)
     */
    public List<BankTicketValidAmountDTO>  findBankTicketValidAmountByBankId(BankTicketValidAmountDTO bankTicketValidAmountDTO)throws SQLException;
    
    /****
     * 可用额度count查询(dealer)
     */
    public int findBankTicketValidAmountCountByBankId(Integer bankId)throws SQLException;

    /** 
     * 根据条件查询出银票的异常清单
     * @author DELL
     * TODO description
     * @date 2016年3月29日
     * @param bankTicketExceptionInventoryDTO
     * @return
     * @throws SqlException
     */
    public List<BankTicketExceptionInventoryDTO> findBankTicketExceptionInventoryList(BankTicketExceptionInventoryDTO bankTicketExceptionInventoryDTO) throws SQLException;

}
