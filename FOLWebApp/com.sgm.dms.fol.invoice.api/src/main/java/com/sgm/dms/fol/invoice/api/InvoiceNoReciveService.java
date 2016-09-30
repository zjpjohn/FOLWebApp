
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : invoice.api
 *
 * @File name : InvocieNoReciveService.java
 *
 * @Author : Lujinglei
 *
 * @Date : 2016年5月27日
 *
----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2016年5月27日    Lujinglei    1.0
 *
 *
 *
 *
----------------------------------------------------------------------------------
 */
	
package com.sgm.dms.fol.invoice.api;

import java.util.List;

import com.sgm.dms.fol.common.api.exception.ServiceAppException;
import com.sgm.dms.fol.invoice.dto.InvoiceNoInfoDTO;
import com.sgm.dms.fol.invoice.functions.ZFIDMSOUT;

/**
 *
 * @author Lujinglei
 * description 
 * @date 2016年5月27日 
 */

public interface InvoiceNoReciveService {
    
    //调用SAP接口
    public ZFIDMSOUT invoiceNoRecive(InvoiceNoInfoDTO invoiceNoInfo,ZFIDMSOUT result) throws ServiceAppException;
	
    //查询发票信息
    public List<InvoiceNoInfoDTO> getInvoiceInfo() throws ServiceAppException;
    
    //重处理专门调用
    public ZFIDMSOUT invokeByAnew(String invoiceNo,ZFIDMSOUT result) throws ServiceAppException;
    
    //返回结果处理
    public void resultDispose(InvoiceNoInfoDTO invoiceNoInfoDTO,ZFIDMSOUT result) throws ServiceAppException;
    
    //重处理业务结果处理
    public void resultDispose(String invoiceNo,ZFIDMSOUT result) throws ServiceAppException;

    
}
