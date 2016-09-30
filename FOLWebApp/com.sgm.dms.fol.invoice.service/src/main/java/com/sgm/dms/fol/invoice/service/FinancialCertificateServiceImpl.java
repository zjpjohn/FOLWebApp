
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : invoice.service
 *
 * @File name : FinancialCertificateServiceImpl.java
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
import com.sgm.dms.fol.invoice.api.FinancialCertificateService;
import com.sgm.dms.fol.invoice.dto.InvoiceFollowDTO;
import com.sgm.dms.fol.invoice.repository.InvoiceDao;

/**
 *
 * @author Lujinglei
 * description  SAP每日财务凭证查询ServiceImpl
 * @date 2016年5月10日
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class FinancialCertificateServiceImpl implements FinancialCertificateService{


    protected Logger logger = LogManager.getLogger(this.getClass());
    @Autowired
    private InvoiceDao invoiceDao;
    
    /**
     *
     * @author Lujinglei
     * @date 2016年5月10日
     * @param invoiceFollowDTO
     * description  SAP每日财务凭证查询
     * @return
     * @throws ServiceAppException
     * (non-Javadoc)
     * @see com.sgm.dms.fol.invoice.api.FinancialCertificateService#getSAPFinancialCertificate(com.sgm.dms.fol.invoice.dto.InvoiceFollowDTO)
     */
    	
    @Override
    public List<InvoiceFollowDTO> getSAPFinancialCertificate(InvoiceFollowDTO invoiceFollowDTO) throws Exception {
        CommonUtils.filterSpecialWord(invoiceFollowDTO);
        List<InvoiceFollowDTO> list = null ;
        
        list = invoiceDao.getSAPFinancialCertificate(invoiceFollowDTO);
        
        return list;
    }

}
