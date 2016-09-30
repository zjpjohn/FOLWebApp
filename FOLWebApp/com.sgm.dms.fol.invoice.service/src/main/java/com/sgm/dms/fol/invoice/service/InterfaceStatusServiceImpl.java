
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : invoice.service
 *
 * @File name : InterfaceStatusServiceImpl.java
 *
 * @Author : Lujinglei
 *
 * @Date : 2016年5月10日
 *
----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2016年5月10日    Lujinglei    1.0
 *
 *
 *
 *
----------------------------------------------------------------------------------
 */
	
package com.sgm.dms.fol.invoice.service;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgm.dms.fol.common.api.exception.ServiceAppException;
import com.sgm.dms.fol.common.service.utils.CommonUtils;
import com.sgm.dms.fol.invoice.api.InterfaceStatusService;
import com.sgm.dms.fol.invoice.dto.SapReturnItemsDTO;
import com.sgm.dms.fol.invoice.repository.InvoiceDao;

/**
 *
 * @author Lujinglei
 * description FOL-SAP接口状态查询ServiceImpl
 * @date 2016年5月10日
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class InterfaceStatusServiceImpl implements InterfaceStatusService{

    protected Logger logger = LogManager.getLogger(this.getClass());
    @Autowired
    private InvoiceDao invoiceDao;

    /**
     *
     * @author Lujinglei
     * @date 2016年5月10日
     * @param invoiceFollowDTO
     * description FOL-SAP接口状态查询
     * @return
     * @throws ServiceAppException
     * (non-Javadoc)
     * @see com.sgm.dms.fol.invoice.api.InterfaceStatusService#getFOLtoSAPInterfaceStatus(com.sgm.dms.fol.invoice.dto.InvoiceFollowDTO)
     */
    	
    @Override
    public List<SapReturnItemsDTO> getFOLtoSAPInterfaceStatus(SapReturnItemsDTO sapReturnItemsDTO) throws Exception {
        CommonUtils.filterSpecialWord(sapReturnItemsDTO);
        List<SapReturnItemsDTO> list = null ; 
        try {
            
            list = invoiceDao.getFOLtoSAPInterfaceStatus(sapReturnItemsDTO);
        } catch (Exception e) {
           e.printStackTrace();
        }
        
        return list;
    }

}
