
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : invoice.service
 *
 * @File name : InvoiceServiceImpl.java
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
	
package com.sgm.dms.fol.invoice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgm.dms.fol.common.service.utils.CommonUtils;
import com.sgm.dms.fol.invoice.api.InvoiceService;
import com.sgm.dms.fol.invoice.dto.InvoiceDto;
import com.sgm.dms.fol.invoice.dto.WrOrderDTO;
import com.sgm.dms.fol.invoice.repository.InvoiceDao;

/**
 * @author wangfl
 * @date 2016年5月19日
 */

@Service
@Transactional(rollbackFor=Exception.class)
public class InvoiceServiceImpl implements InvoiceService {
	
	@Autowired
	private InvoiceDao invoiceDao;

	@Override
	public List<InvoiceDto> getPageList(InvoiceDto dto) {
	    CommonUtils.filterSpecialWord(dto);
		return invoiceDao.findPageList(dto);
	}
	@Override
	public List<InvoiceDto> getExpressPageList(InvoiceDto dto) {
	    CommonUtils.filterSpecialWord(dto);
		return invoiceDao.findExpressPageList(dto);
	}

	@Override
	public int getListTotalNum(InvoiceDto dto) {
	    CommonUtils.filterSpecialWord(dto);
		return invoiceDao.findListTotalNum(dto);
	}
	@Override
	public int getExpressListTotalNum(InvoiceDto dto) {
	    CommonUtils.filterSpecialWord(dto);
		return invoiceDao.findExpressListTotalNum(dto);
	}

	@Override
	public void audit(String invoiceNo, String sapCode, long approveMan, int status, String approveMsg) {
		invoiceDao.updateInvoiceStatus(invoiceNo, sapCode, status, approveMan, approveMsg);
	}

	@Override
	public void saveInvoiceRemark(String invoiceNo, String sapCode, String remark, Long logonId) {
		invoiceDao.saveInvoiceRemark(invoiceNo,sapCode, remark, logonId);
	}

	@Override
	public List<WrOrderDTO> getInvoiceDetailList(String invoiceNo, String sapCode) {
		return invoiceDao.findInvoiceDetailList(invoiceNo,sapCode);
	}

}
