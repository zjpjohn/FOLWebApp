
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : invoice.api
 *
 * @File name : FinancialCertificateService.java
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
	
package com.sgm.dms.fol.invoice.api;

import java.util.List;

import com.sgm.dms.fol.common.api.exception.ServiceAppException;
import com.sgm.dms.fol.invoice.dto.InvoiceFollowDTO;

/**
 *
 * @author Lujinglei
 * description SAP每日财务凭证查询Service
 * @date 2016年5月10日
 */

public interface FinancialCertificateService {
    /**
     * 
     *
     * @author Lujinglei
     * description SAP每日财务凭证查询
     * @date 2016年5月10日
     * @return
     * @throws ServiceAppException
     */
    public List<InvoiceFollowDTO> getSAPFinancialCertificate(InvoiceFollowDTO invoiceFollowDTO) throws Exception;

}
