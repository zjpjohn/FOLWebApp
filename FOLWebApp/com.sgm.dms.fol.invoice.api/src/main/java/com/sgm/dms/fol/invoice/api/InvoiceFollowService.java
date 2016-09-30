
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : invoice.api
 *
 * @File name : InvoiceFollowService.java
 *
 * @Author : Lujinglei
 *
 * @Date : 2016年5月9日
 *
----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2016年5月9日    Lujinglei    1.0
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
 * TODO description
 * @date 2016年5月9日
 */

public interface InvoiceFollowService {
     /**
      * 
      *
      * @author Lujinglei
      * description 查询索赔发票所有信息
      * @date 2016年5月9日
      * @param invoiceFollowDTO
      * @return
      * @throws ServiceAppException
      */
    public List<InvoiceFollowDTO> getInvoiceFollow(InvoiceFollowDTO invoiceFollowDTO) throws Exception;
    
    
    public List<InvoiceFollowDTO> getInvoiceDeatil(InvoiceFollowDTO invoiceFollowDTO) throws Exception;

    /**
     * 
     *
     * @author Lujinglei
     * description 作废
     * @date 2016年5月9日
     * @param invoiceFollowDTO
     * @return
     * @throws ServiceAppException
     */
    public int cancelInvoice(InvoiceFollowDTO invoiceFollowDTO) throws Exception;
    /**
     * 
     *
     * @author Lujinglei
     * description 重做
     * @date 2016年5月9日
     * @param invoiceFollowDTO
     * @return
     * @throws ServiceAppException
     */
    public int anewInvoice(InvoiceFollowDTO invoiceFollowDTO) throws Exception;
}
