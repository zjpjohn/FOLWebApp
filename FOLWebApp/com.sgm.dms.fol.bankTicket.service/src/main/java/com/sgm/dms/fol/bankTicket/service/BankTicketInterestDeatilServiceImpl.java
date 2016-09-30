/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : bankTicket.service
*
* @File name : BankTicketInterestDeatilServiceImpl.java
*
* @Author : DELL
*
* @Date : 2016年1月26日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2016年1月26日    DELL    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/
	
package com.sgm.dms.fol.bankTicket.service;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgm.dms.fol.bankTicket.api.BankTicketInterestDeatilService;
import com.sgm.dms.fol.bankTicket.dto.BankTicketDeadLineDTO;
import com.sgm.dms.fol.bankTicket.dto.BankTicketInterestDeatilQueryDTO;
import com.sgm.dms.fol.bankTicket.repository.BankTicketDeadLineDao;
import com.sgm.dms.fol.bankTicket.repository.BankTicketInterestDeatilDao;
import com.sgm.dms.fol.common.api.exception.ServiceAppException;
import com.sgm.dms.fol.common.api.exception.ServiceBizException;

/*
*
* @author DELL
* TODO description
* @date 2016年1月26日
*/
@Service("BankTicketInterestDeatilService")
@Transactional(rollbackFor=Exception.class)
public class BankTicketInterestDeatilServiceImpl implements BankTicketInterestDeatilService{
    protected Logger logger = LogManager.getLogger(this.getClass());
    
    @Autowired
    private BankTicketInterestDeatilDao bankTicketInterestDeatilDao;
    
    @Autowired
    private BankTicketDeadLineDao bankTicketDeadLineDao;

    /*
    *
    * @author DELL
    * @date 2016年1月26日
    * @param bankTicketInterestDeatilDTO
    * @return
    * (non-Javadoc)
    * @see com.sgm.dms.fol.bankTicket.api.BankTicketInterestDeatilService#findBankTicketInterestDeatilByWhere(com.sgm.dms.fol.bankTicket.dto.BankTicketInterestDeatilDTO)
    */
    	
    @Override
    public List<BankTicketInterestDeatilQueryDTO> findBankTicketInterestDeatilByWhere(BankTicketInterestDeatilQueryDTO bankTicketInterestDeatilDTO) throws ServiceAppException{
        List<BankTicketInterestDeatilQueryDTO> bankTicketInterestDeatillist;
        try {
            bankTicketInterestDeatillist=bankTicketInterestDeatilDao.findBankTicketInterestDeatilByWhere(bankTicketInterestDeatilDTO);
            
            if(bankTicketInterestDeatillist != null && bankTicketInterestDeatillist.size()>0){
                for (BankTicketInterestDeatilQueryDTO dto : bankTicketInterestDeatillist) {
                    if(dto.getDeadLineDay()==null||"".equals(dto.getDeadLineDay().toString())){
                        List<BankTicketDeadLineDTO> deadLinddtos=bankTicketDeadLineDao.findBankTicketLimitByBrandAndDealerType(dto.getSapCode().substring(0, 2), dto.getDealerType());
                        
                        if(deadLinddtos!=null&&deadLinddtos.size()>1){
                            throw new ServiceBizException("银票期限基础数据有误");
                        }else if(deadLinddtos!=null&&deadLinddtos.size()>0){
                            BankTicketDeadLineDTO bankTicketDeadLineDTO=deadLinddtos.get(0);
                            dto.setDeadLineDay(bankTicketDeadLineDTO.getDeadlineDay());
                            dto.setFreePeriodDay(bankTicketDeadLineDTO.getFreePeriodDay());
                        }
                    }
                } 
            }
        } catch (Exception e) {
           logger.error(e);
           throw(new ServiceAppException(e));
        } 
        return bankTicketInterestDeatillist;
    }

    /*
    *
    * @author DELL
    * @date 2016年1月26日
    * @param bankTicketInterestDeatilDTO
    * @return
    * @throws ServiceAppException
    * (non-Javadoc)
    * @see com.sgm.dms.fol.bankTicket.api.BankTicketInterestDeatilService#findBankTicketInterestDeatilCountByWhere(com.sgm.dms.fol.bankTicket.dto.BankTicketInterestDeatilQueryDTO)
    */
    	
    @Override
    public int findBankTicketInterestDeatilCountByWhere(BankTicketInterestDeatilQueryDTO bankTicketInterestDeatilDTO) throws ServiceAppException {
        try {
            int count=bankTicketInterestDeatilDao.findBankTicketInterestDeatilCountByWhere(bankTicketInterestDeatilDTO);
            return count;
        } catch (Exception e) {
            logger.error(e);
            throw(new ServiceAppException(e));
        }
        
    }

}
