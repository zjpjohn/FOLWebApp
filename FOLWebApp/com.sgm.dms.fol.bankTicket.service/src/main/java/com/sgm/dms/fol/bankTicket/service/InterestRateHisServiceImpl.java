package com.sgm.dms.fol.bankTicket.service;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgm.dms.fol.bankTicket.api.InterestRateHisService;
import com.sgm.dms.fol.bankTicket.dto.TicketInterestRateDTO;
import com.sgm.dms.fol.bankTicket.repository.InterestRateHisDao;
import com.sgm.dms.fol.common.api.constants.POConstant;
import com.sgm.dms.fol.common.api.exception.ServiceAppException;
import com.sgm.dms.fol.common.service.utils.CommonUtils;
@Service("InterestRateHisService")
@Transactional(rollbackFor=Exception.class)
public class InterestRateHisServiceImpl implements InterestRateHisService{
	// 日志
	protected Logger logger = LogManager.getLogger(this.getClass());
	@Autowired
	private InterestRateHisDao inserestDao;
	
	/**
	 * 票据贴息当前年利率
	 */
	@Override
	public List<TicketInterestRateDTO> getInterestRateCurrent(TicketInterestRateDTO ticketInterestRateDTO)
			throws Exception {
	    	CommonUtils.filterSpecialWord(ticketInterestRateDTO);
			List<TicketInterestRateDTO> list ;
			
			list = inserestDao.getInterestRateCurrent(ticketInterestRateDTO);
		return list;
	}

	/**
	 * 票据贴息年化利率查询
	 */
	@Override
	public List<TicketInterestRateDTO> getInterestRateHis(TicketInterestRateDTO ticketInterestRateDTO)
			throws Exception {
			CommonUtils.filterSpecialWord(ticketInterestRateDTO);
			List<TicketInterestRateDTO> list = null;
			list = inserestDao.getInterestRateHis(ticketInterestRateDTO);
		return list;
	}
	/**
	 * 票据贴息年化利率审核通过
	 */
	@Override
	public int updateInterestRateCurAudit(TicketInterestRateDTO ticketInterestRateDTO) throws Exception {
		int result = 0;
		CommonUtils.filterSpecialWord(ticketInterestRateDTO);		
		ticketInterestRateDTO.setAuditStatus(POConstant.BANK_TICKET_AUDIT_SUCCESS);
		result = inserestDao.updateInterestRateCurAudit(ticketInterestRateDTO);

		return result;
	}
	/**
	 * 票据贴息年化利率驳回
	 */
	@Override
	public int updateInterestRateCurReject(TicketInterestRateDTO ticketInterestRateDTO) throws Exception {
		int result = 0;
			CommonUtils.filterSpecialWord(ticketInterestRateDTO);	
			ticketInterestRateDTO.setAuditStatus(POConstant.BANK_TICKET_UPDATE_REJECT);
			result = inserestDao.updateInterestRateCurAudit(ticketInterestRateDTO);
			return result;
	}
	/**
	 * 新增修改记录
	 */
	@Override
	public int updateInterestRateHis(TicketInterestRateDTO ticketInterestRateDTO) throws Exception {
			int result = 0;
			CommonUtils.filterSpecialWord(ticketInterestRateDTO);			
			ticketInterestRateDTO.setDiscountRate(POConstant.DISCOUNT_RATE);
			BigDecimal annualInterestrate = new BigDecimal(ticketInterestRateDTO.getAnnualInterestRate());
			String annRate = String.valueOf(annualInterestrate.divide(BigDecimal.valueOf(100)));
			ticketInterestRateDTO.setAnnualInterestRate(annRate);
			//检查审核状态
			check(ticketInterestRateDTO);
			result = inserestDao.updateInterestRateHis(ticketInterestRateDTO);

			return result;
	}
	//检查审核状态
	private void check(TicketInterestRateDTO ticketInterestRateDTO) throws Exception{
		List<TicketInterestRateDTO> list = inserestDao.getInterestRateHis(ticketInterestRateDTO);
		for (TicketInterestRateDTO dto : list) {
			if(dto.getAuditStatus() == POConstant.BANK_TICKET_UPDATE_UN_AUDIT){
				throw new ServiceAppException("提示：当前状态为待审核，不能予以修改！");
			}
		}
	}
	/**
	 * 票据贴息年化利率维审核查询
	 */
	@Override
	public List<TicketInterestRateDTO> getInterestRateAudit(TicketInterestRateDTO ticketInterestRateDTO)
			throws Exception {
			CommonUtils.filterSpecialWord(ticketInterestRateDTO);		
			List<TicketInterestRateDTO> list = null;
			list = inserestDao.getInterestRateAudit(ticketInterestRateDTO);
			return list;
	}



}
