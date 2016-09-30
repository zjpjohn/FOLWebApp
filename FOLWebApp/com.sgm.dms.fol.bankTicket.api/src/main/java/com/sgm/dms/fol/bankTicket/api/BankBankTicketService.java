package com.sgm.dms.fol.bankTicket.api;

import java.sql.SQLException;
import java.util.List;

import com.sgm.dms.fol.bankTicket.dto.BankBankTicketDTO;
import com.sgm.dms.fol.bankTicket.dto.BankInfoDTO;
import com.sgm.dms.fol.bankTicket.dto.BankTicketValidAmountDTO;
import com.sgm.dms.fol.common.api.exception.ServiceAppException;

/*****
 * 
*
* @author Administrator
* 银行银票service
* @date 2015年12月31日
 */
public interface BankBankTicketService {
	
	/**
	 * 保存银行银票信息
	 */
	public int saveBankBankTicket(BankBankTicketDTO bankBankTicketDTO,BankInfoDTO bankInfoDTO,long userId) throws ServiceAppException;
	
	/**
	 * 查询银行银票信息记录数
	 */
	public int findBankBankTicketDataCountByWhs(BankBankTicketDTO bankBankTicketDTO)throws ServiceAppException;
	
	/**
	 * 查询银行银票信息(带分页)
	 */
	public List<BankBankTicketDTO> findBankBankTicketDataByWhs(BankBankTicketDTO bankBankTicketDTO)throws ServiceAppException;
	
	/**
	 * 修改银行银票信息
	 */
	public int updateBankBankTicket(BankBankTicketDTO bankBankTicketDTO,BankInfoDTO bankInfoDTO,Long userId) throws ServiceAppException;

	/**
	 * 查询单条银行银票信息
	 */
	public BankBankTicketDTO findBankBankTicketDataById(Integer id)throws ServiceAppException;

	/**
	 * 更新银行银票审核
	 */
	public int updateBankBankTicketAudit(BankBankTicketDTO bankBankTicketDTO,BankInfoDTO bankInfoDTO,Long userId) throws ServiceAppException;

	/**
	 * 银行银票限额审核通过
	 */
	public void bankBankTicketAuditSuccess(List<BankBankTicketDTO> bankBankTicketDTOList,Long userId) throws ServiceAppException,SQLException;
	
	/**
     * 银行银票限额审核驳回
     */
    public void bankBankTicketAuditReject(List<BankBankTicketDTO> bankBankTicketDTOList,Long userId) throws ServiceAppException,SQLException;
	
	/**
     * 查询出银行的额度（今天到期额度，明天到期额度，后天年到期额度）
     */
	public List<BankTicketValidAmountDTO> findBankAvailAmount(BankTicketValidAmountDTO bankTicketValidAmountDTO) throws ServiceAppException;

	/**
     * 查询出银行的额度总数（今天到期额度，明天到期额度，后天年到期额度）
     */
    public int findBankAvailAmountCount(BankTicketValidAmountDTO bankTicketValidAmountDTO) throws ServiceAppException;
}
