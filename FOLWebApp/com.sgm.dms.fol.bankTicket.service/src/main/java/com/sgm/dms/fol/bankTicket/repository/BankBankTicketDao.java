package com.sgm.dms.fol.bankTicket.repository;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import com.sgm.dms.fol.bankTicket.dto.BankBankTicketDTO;
import com.sgm.dms.fol.bankTicket.dto.BankInfoDTO;
import com.sgm.dms.fol.bankTicket.dto.QueryBankTicketTotalAmountDTO;

/*****
 * 银行银票信息dao
*
* @author Administrator
* TODO description
* @date 2015年12月31日
 */
public interface BankBankTicketDao {
	
	/****
	 * 保存银行银票信息
	 */
	public int saveBankBankTicket(BankBankTicketDTO bankBankTicketDTO) throws SQLException;
	
	/*****
	 * 查询银行银票信息(带分页)
	 */
	public List<BankBankTicketDTO> findBankBankTicketDataByWhs(BankBankTicketDTO bankBankTicketDTO)throws SQLException;
	
	/*****
	 * 查询银行银票信息记录数
	 */
	public int findBankBankTicketDataCountByWhs(BankBankTicketDTO bankBankTicketDTO)throws SQLException;
	
	/****
	 * 修改银行银票信息
	 */
	public int updateBankBankTicket(BankBankTicketDTO bankBankTicketDTO) throws SQLException;
	
	/*****
	 * 查询单条银行银票信息记录
	 */
	public BankBankTicketDTO findBankBankTicketDataById(Integer id)throws SQLException;

	/**
	 *  根据银行缩写查询出银行银票（排除渠道）
	 */
	public BankBankTicketDTO findBankBankTicketDataByBankAbbr(BankInfoDTO bankInfoDTO) throws SQLException;
	/**
	 *  查询出所有渠道的银行银票
	 */
	public List<BankBankTicketDTO> findAllDealerBankBankTicketData() throws SQLException;

	/**
	 * 查询出当前有效的银行银票金额总和
	 */
	public List<QueryBankTicketTotalAmountDTO> findUsedBankTicketTotalAmount(QueryBankTicketTotalAmountDTO queryBankTicketTotalAmountDTO) throws SQLException;
	
	/**
     * 查询出当前有效的银行经销商银票金额总和
     */
    public BigDecimal findUsedBankTicketDealerTotalAmount(QueryBankTicketTotalAmountDTO queryBankTicketTotalAmountDTO) throws SQLException;
}
