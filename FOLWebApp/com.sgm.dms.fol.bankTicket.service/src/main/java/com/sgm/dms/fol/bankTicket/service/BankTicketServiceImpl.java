/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : bankTicket.service
*
* @File name : BankTicketServiceImpl.java
*
* @Author : DELL
*
* @Date : 2016年1月19日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2016年1月19日    DELL    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/
	
package com.sgm.dms.fol.bankTicket.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgm.dms.fol.bankTicket.api.BankTicketService;
import com.sgm.dms.fol.bankTicket.dto.BankTicketCollectDTO;
import com.sgm.dms.fol.bankTicket.dto.BankTicketExceptionInventoryDTO;
import com.sgm.dms.fol.bankTicket.dto.QueryBankTicketInfoDTO;
import com.sgm.dms.fol.bankTicket.repository.BankTicketDAO;
import com.sgm.dms.fol.common.api.constants.POConstant;
import com.sgm.dms.fol.common.api.exception.ServiceAppException;

/*
*
* @author DELL
* TODO description
* @date 2016年1月19日
*/
@Service("BankTicketService")
@Transactional(rollbackFor=Exception.class)
public class BankTicketServiceImpl implements BankTicketService{
    protected Logger logger = LogManager.getLogger(this.getClass());
    @Autowired
    private BankTicketDAO bankTicketDAO;
    
    /*
    *
    * @author DELL
    * @date 2016年1月19日
    * @param bankTicketInfoDTO
    * @return
    * @throws Exception
    * (non-Javadoc)
    * @see com.sgm.dms.fol.bankTicket.api.BankTicketService#findBankTicketInfoByWhere(com.sgm.dms.fol.bankTicket.dto.QueryBankTicketInfoDTO)
    */
    	
    @Override
    public List<QueryBankTicketInfoDTO> findBankTicketInfoByWhere(QueryBankTicketInfoDTO queryBankTicketInfoDTO) throws ServiceAppException {
        List<QueryBankTicketInfoDTO> list;
        try {
            list=bankTicketDAO.findBankTicketInfoByWhere(queryBankTicketInfoDTO);
            
        } catch (Exception e) {
            logger.error(e);
            throw(new ServiceAppException(e));
        }
        return list;
    }

    /*
    *
    * @author DELL
    * @date 2016年1月19日
    * @param bankTicketInfoDTO
    * @return
    * @throws ServiceBizException
    * (non-Javadoc)
    * @see com.sgm.dms.fol.bankTicket.api.BankTicketService#findBankTicketInfoCountByWhere(com.sgm.dms.fol.bankTicket.dto.QueryBankTicketInfoDTO)
    */
    	
    @Override
    public int findBankTicketInfoCountByWhere(QueryBankTicketInfoDTO bankTicketInfoDTO) throws ServiceAppException {
        int count;
        try {
            count=bankTicketDAO.findBankTicketInfoCountByWhere(bankTicketInfoDTO);
        } catch (Exception e) {
            logger.error(e);
            throw(new ServiceAppException(e));
        }
        return count;
    }

    /*
    *
    * @author DELL
    * @date 2016年1月19日
    * @param bankTicketCollectDTO
    * @return
    * @throws ServiceBizException
    * (non-Javadoc)
    * @see com.sgm.dms.fol.bankTicket.api.BankTicketService#findBankTicketCollectDataList(com.sgm.dms.fol.bankTicket.dto.BankTicketCollectDTO)
    */
    	
    @Override
    public List<BankTicketCollectDTO> findBankTicketCollectDataList(BankTicketCollectDTO bankTicketCollectDTO) throws ServiceAppException {
        try {
            List<BankTicketCollectDTO> totalAmountDataList=bankTicketDAO.findBankTicketTotalAmount(bankTicketCollectDTO);
        
            bankTicketCollectDTO.setSapExecuteResult(POConstant.SQ_RECEIVE_BANKTICKET_CHECK_RESULT_SUC);
            bankTicketCollectDTO.setFolResult(POConstant.FOL_CHECK_BANK_TICKET_SUC);
            List<String> saicResultList = new ArrayList<String>();
            saicResultList.add(POConstant.SQ_SIGN_BANK_TICKET_EXECUTE_SUC);
            saicResultList.add(POConstant.SQ_SIGN_BANK_TICKET_EXECUTED);
            bankTicketCollectDTO.setSaicResultList(saicResultList);
            
            List<BankTicketCollectDTO> usedAmountDataList=bankTicketDAO.findBankTicketUsedAmount(bankTicketCollectDTO);
            
            List<BankTicketCollectDTO> fiveDayWithInAmountDataList=bankTicketDAO.findBankTicketFiveDayWithInAmount(bankTicketCollectDTO);
            
            
            for (BankTicketCollectDTO totalamountdto : totalAmountDataList) {
            	totalamountdto.setUsedAmount(new BigDecimal("0"));
                for (BankTicketCollectDTO usedamountdto : usedAmountDataList) {
                    if((usedamountdto.getBankId()!=null&&totalamountdto.getBankId().equals(usedamountdto.getBankId()))){
                    	if(null == totalamountdto.getDealerType() || totalamountdto.getDealerType().equals(usedamountdto.getDealerType())){
                    		if(null != usedamountdto.getUsedAmount()){
                    			totalamountdto.setUsedAmount(usedamountdto.getUsedAmount().add(totalamountdto.getUsedAmount()));
                    		}
                    		totalamountdto.setSurplusAmount(totalamountdto.getTotalAmount().multiply(BigDecimal.valueOf(POConstant.BANK_TICKET_AMOUNT_BASE_UNIT)).subtract(usedamountdto.getUsedAmount()));
                    	}
                    }
                }
            }
            
            for (BankTicketCollectDTO totalamountdto : totalAmountDataList) {
                for (BankTicketCollectDTO fivedaywithinamountdto : fiveDayWithInAmountDataList) {
                	 if((fivedaywithinamountdto.getBankId()!= null && totalamountdto.getBankId().equals(fivedaywithinamountdto.getBankId()))){
	                     	if(null == totalamountdto.getDealerType() || totalamountdto.getDealerType().equals(fivedaywithinamountdto.getDealerType())){
	                            totalamountdto.setFiveDayWithInAmount(fivedaywithinamountdto.getFiveDayWithInAmount());
                         	}
                        }
                }
            }
            
            for (BankTicketCollectDTO totalamountdto : totalAmountDataList) {
                totalamountdto.setTotalAmount(totalamountdto.getTotalAmount().multiply(BigDecimal.valueOf(POConstant.BANK_TICKET_AMOUNT_BASE_UNIT)));
                if(totalamountdto.getUsedAmount()==null){
                    totalamountdto.setUsedAmount(BigDecimal.valueOf(0));
                }
                if(totalamountdto.getSurplusAmount()==null){
                    totalamountdto.setSurplusAmount(totalamountdto.getTotalAmount().subtract(totalamountdto.getUsedAmount())); 
                }
                if(totalamountdto.getFiveDayWithInAmount()==null){
                    totalamountdto.setFiveDayWithInAmount(BigDecimal.valueOf(0));
                }
            }
            
            return totalAmountDataList;
        } catch (SQLException e) {            
            logger.error(e);
            throw(new ServiceAppException(e));
        }
        
    }

    /**
     * 根据条件查询出银票的异常清单
     * @author DELL
     * @date 2016年3月29日
     * @param bankTicketExceptionInventoryDTO
     * @return
     * @throws ServiceAppException
     * (non-Javadoc)
     * @see com.sgm.dms.fol.bankTicket.api.BankTicketService#findBankTicketExceptionInventoryList(com.sgm.dms.fol.bankTicket.dto.BankTicketExceptionInventoryDTO)
     */
    	
    @Override
    public List<BankTicketExceptionInventoryDTO> findBankTicketExceptionInventoryList(BankTicketExceptionInventoryDTO bankTicketExceptionInventoryDTO) throws ServiceAppException,SQLException {
        List<BankTicketExceptionInventoryDTO> bankTicketExceptionInventoryDTOList=bankTicketDAO.findBankTicketExceptionInventoryList(bankTicketExceptionInventoryDTO);
        return bankTicketExceptionInventoryDTOList;
    }
	

}
