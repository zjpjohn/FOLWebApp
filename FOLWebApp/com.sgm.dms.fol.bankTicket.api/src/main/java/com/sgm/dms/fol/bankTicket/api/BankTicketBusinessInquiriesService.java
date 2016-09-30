package com.sgm.dms.fol.bankTicket.api;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.sgm.dms.fol.bankTicket.dto.BankTicketDraftsOverdueInterestDTO;
import com.sgm.dms.fol.bankTicket.dto.BankTicketInterestDTO;
import com.sgm.dms.fol.bankTicket.dto.BankTicketInterestDeatilQueryDTO;
import com.sgm.dms.fol.bankTicket.dto.BankTicketInterestIssueAndConfirmDTO;
import com.sgm.dms.fol.bankTicket.dto.BankTicketInterestMonthDTO;
import com.sgm.dms.fol.common.api.exception.ServiceAppException;
import com.sgm.dms.fol.common.api.exception.ServiceBizException;

/*****
 * 银票业务查询SERVICE
*
* @author zhang bao
* TODO description
* @date 2016年1月13日
 */
public interface BankTicketBusinessInquiriesService {
	
	
	//票据贴息开票清单查询
	public List<BankTicketDraftsOverdueInterestDTO> findBankTicketInterestByWhere(BankTicketInterestMonthDTO bankTicketInterestMonthDTO) throws ServiceAppException;
	
	//票据贴息开票清单count
	public int findBankTicketInterestCountByWhere(BankTicketInterestMonthDTO bankTicketInterestMonthDTO)throws SQLException;
	
	//生成贴息凭证号号码
	public void gernerateBankTicketInvoiceNumber(BankTicketInterestMonthDTO request,long userId)throws ServiceAppException;

	//票据贴息查询
	public List<BankTicketInterestDeatilQueryDTO> findBankTicketInterestList(BankTicketInterestDTO bankTicketInterestDTO) throws ServiceAppException;

	//票据贴息总数
	public int findBankTicketInterestCount(BankTicketInterestDTO bankTicketInterestDTO) throws ServiceBizException;
	
	//导入贴息信息
	public void importInterestAmount(BankTicketInterestMonthDTO inquiries,String token,String filedId,long userId)throws ServiceAppException,IOException;

	//银票贴息月度发布
	public boolean bankTicketInterestIssue(BankTicketInterestIssueAndConfirmDTO bankTicketInterestIssueAndConfirmDTO) throws ServiceAppException;
	
	//银票贴息月度发布
    public boolean bankTicketInterestIssueConfirm(BankTicketInterestIssueAndConfirmDTO bankTicketInterestIssueAndConfirmDTO) throws ServiceAppException;
}
