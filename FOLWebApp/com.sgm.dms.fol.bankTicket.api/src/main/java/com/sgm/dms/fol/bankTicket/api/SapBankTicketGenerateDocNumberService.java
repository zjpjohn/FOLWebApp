package com.sgm.dms.fol.bankTicket.api;

import java.util.List;

import com.sgm.dms.fol.bankTicket.dto.BankTicketBusinessInquiriesRequestDTO;
import com.sgm.dms.fol.bankTicket.dto.BankTicketBusinessInquiriesResultDTO;
import com.sgm.dms.fol.common.api.exception.ServiceAppException;

/*****
 * 
*
* @author zhang bao
* 调用sap生成凭证号接口
* @date 2016年1月14日
 */
public interface SapBankTicketGenerateDocNumberService {
	
	//生成发票号码
	public BankTicketBusinessInquiriesResultDTO gernerateBankTicketInvoiceNumber(List<BankTicketBusinessInquiriesRequestDTO> request)throws ServiceAppException;
}
