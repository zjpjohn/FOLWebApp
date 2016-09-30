package com.sgm.dms.fol.bankTicket.api;

import java.util.List;

import com.sgm.dms.fol.bankTicket.dto.TicketInterestRateDTO;

/**
 * 票据贴息年化利率Service
 * @author lujinglei
 *
 */
public interface InterestRateHisService {
	/**
	 * 票据贴息当前年利率维护查询
	 */
	public List<TicketInterestRateDTO> getInterestRateCurrent(TicketInterestRateDTO ticketInterestRateDTO) throws Exception;
	/**
	 * 票据贴息年化利率维护查询
	 */
	public List<TicketInterestRateDTO> getInterestRateHis(TicketInterestRateDTO ticketInterestRateDTO) throws Exception;
	/**
	 * 票据贴息年化利率审核查询
	 */
	public List<TicketInterestRateDTO> getInterestRateAudit(TicketInterestRateDTO ticketInterestRateDTO) throws Exception;
	/**
	 *  票据贴息年化利率审核通过
	 */
	public int updateInterestRateCurAudit(TicketInterestRateDTO ticketInterestRateDTO) throws Exception;
	/**
	 *  票据贴息年化利率驳回
	 */
	public int updateInterestRateCurReject(TicketInterestRateDTO ticketInterestRateDTO) throws Exception;
	/**
	 * 修改票据贴息年化利率维护
	 */
	public int updateInterestRateHis(TicketInterestRateDTO ticketInterestRateDTO) throws Exception;

}
