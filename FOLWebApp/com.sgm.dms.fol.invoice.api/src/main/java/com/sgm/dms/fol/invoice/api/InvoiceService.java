
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : invoice.api
 *
 * @File name : InvoiceService.java
 *
 * @Author : wangfl
 *
 * @Date : 2016年5月19日
 *
 ----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2016年5月19日    wangfl    1.0
 *
 *
 *
 *
 ----------------------------------------------------------------------------------
 */
	
package com.sgm.dms.fol.invoice.api;

import java.util.List;

import com.sgm.dms.fol.invoice.dto.InvoiceDto;
import com.sgm.dms.fol.invoice.dto.WrOrderDTO;

/**
 * @author wangfl
 * 银票service
 * @date 2016年5月19日
 */

public interface InvoiceService {

	/**
	 * 
	 * 分页查询发票列表
	 * TODO description
	 * @date 2016年5月19日
	 * @param dto
	 * @return
	 */
	List<InvoiceDto> getPageList(InvoiceDto dto);
	List<InvoiceDto> getExpressPageList(InvoiceDto dto);

	/**
	 * 
	 * @author wangfl
	 * 根据条件查询发票总数
	 * @date 2016年5月19日
	 * @param dto
	 * @return
	 */
	int getListTotalNum(InvoiceDto dto);
	int getExpressListTotalNum(InvoiceDto dto);

	/**
	 * 
	 * @author wangfl
	 * 审核发票
	 * @date 2016年5月19日
	 * @param invoiceNo
	 * @param l
	 * @param wrOrderInvoiceSapDealSuccess
	 */
	void audit(String invoiceNo, String sapCode, long approveMan, int status, String approveMsg);

	/**
	 * 
	 * @author wangfl
	 * 保存备注
	 * @date 2016年5月20日
	 * @param invoiceNo
	 * @param remark
	 * @param logonId
	 */
	void saveInvoiceRemark(String invoiceNo, String sapCode, String remark, Long logonId);

	/**
	 * 
	 * @author wangfl
	 * 发票明细
	 * @date 2016年5月20日
	 * @param invoiceNo
	 * @return
	 */
	List<WrOrderDTO> getInvoiceDetailList(String invoiceNo, String sapCode);

}
