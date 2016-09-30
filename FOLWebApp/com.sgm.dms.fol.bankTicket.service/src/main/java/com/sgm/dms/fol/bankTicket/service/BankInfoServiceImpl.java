/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : bankTicket.service
*
* @File name : BankInfoServiceImpl.java
*
* @Author : DELL
*
* @Date : 2016年1月6日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2016年1月6日    DELL    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/
	
package com.sgm.dms.fol.bankTicket.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgm.dms.fol.bankTicket.api.BankInfoService;
import com.sgm.dms.fol.bankTicket.dto.BankInfoDTO;
import com.sgm.dms.fol.bankTicket.repository.BankInfoDao;
import com.sgm.dms.fol.common.api.exception.ServiceAppException;

/*
*
* @author DELL
* TODO description
* @date 2016年1月6日
*/
@Service("BankInfoService")
@Transactional(rollbackFor=Exception.class)
public class BankInfoServiceImpl implements BankInfoService{
    protected Logger logger = LogManager.getLogger(this.getClass());
    
    @Autowired
    private BankInfoDao bankInfoDao;
    
    /*
    *
    * @author DELL
    * @date 2016年1月6日
    * @param bankInfoDTO
    * @return
    * @throws SQLException
    * (non-Javadoc)
    * @see com.sgm.dms.fol.bankTicket.api.BankInfoService#findBankinfo(com.sgm.dms.fol.bankTicket.dto.BankInfoDTO)
    */
    	
    @Override
    public BankInfoDTO findBankinfo(BankInfoDTO bankInfoDTO) throws ServiceAppException {
        BankInfoDTO dto;
        try {
            dto = bankInfoDao.findBankinfo(bankInfoDTO);
        } catch (SQLException e) {
            logger.error(e);
            throw new ServiceAppException(e);
        }
      
        return dto;
    }

    /*
    *
    * @author DELL
    * @date 2016年1月7日
    * @return
    * @throws ServiceBizException
    * (non-Javadoc)
    * @see com.sgm.dms.fol.bankTicket.api.BankInfoService#getBankInfoAllList()
    */
    	
    @Override
    public List<BankInfoDTO> getBankInfoAllList() throws ServiceAppException {
        List<BankInfoDTO> list;
        try {
            list=bankInfoDao.getBankInfoAllList();
        } catch (SQLException e) {
            logger.error(e);
            throw new ServiceAppException(e);
        }
        return list;
    }

}
