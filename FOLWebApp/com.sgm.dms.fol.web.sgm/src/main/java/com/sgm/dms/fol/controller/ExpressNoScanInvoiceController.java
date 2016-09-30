
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : web.sgm
 *
 * @File name : ExpressNoScanInvoice.java
 *
 * @Author : wangfl
 *
 * @Date : 2016年5月18日
 *
 ----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2016年5月18日    wangfl    1.0
 *
 *
 *
 *
 ----------------------------------------------------------------------------------
 */

package com.sgm.dms.fol.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sgm.dms.fol.common.api.constants.POConstant;
import com.sgm.dms.fol.common.api.exception.VoNotValidException;
import com.sgm.dms.fol.common.service.controller.impl.BaseController;
import com.sgm.dms.fol.common.service.utils.BeanMapper;
import com.sgm.dms.fol.common.service.utils.CookieUtil;
import com.sgm.dms.fol.common.service.utils.MapUtil;
import com.sgm.dms.fol.common.service.utils.StringUtil;
import com.sgm.dms.fol.invoice.api.InvoiceService;
import com.sgm.dms.fol.invoice.dto.InvoiceDto;
import com.sgm.dms.fol.vo.AuditOrRejectReqVo;
import com.sgm.dms.fol.vo.ExpressNoScanInvoiceListReqVo;
import com.sgm.dms.fol.vo.ExpressNoScanInvoiceListRespVo;

/**
 * @author wangfl 
 * 快递单号扫描发票Controller
 * @date 2016年5月18日
 */
@RestController
@RequestMapping("/invoice/expressNoScanInvoice")
public class ExpressNoScanInvoiceController extends BaseController {
	private Logger logger = LogManager.getLogger(this.getClass());
	
	@Autowired
	private InvoiceService invoiceService;

	/**
	 * 
	 * @author wangfl
	 * 查询
	 * @date 2016年5月19日
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/query")
	public Map<String, Object> expressNoScanInvoiceList(@RequestBody ExpressNoScanInvoiceListReqVo vo) throws Exception {
		//Vo转Dto
		InvoiceDto dto = BeanMapper.map(vo, InvoiceDto.class);
		
		//调用service查询
		List<InvoiceDto> pageList = invoiceService.getExpressPageList(dto);
		int totalNum = invoiceService.getExpressListTotalNum(dto);
		
		// 组装响应对象
		List<ExpressNoScanInvoiceListRespVo> voList = BeanMapper.mapList(pageList,ExpressNoScanInvoiceListRespVo.class);
		StringUtil.fontFormatFinance(voList);

		Map<String, Object> responsedata = MapUtil.setQueryDataToMap(voList, Long.valueOf(totalNum));
		return responsedata;
	}
	
	/**
	 * 
	 * @author wangfl
	 * 同意
	 * @date 2016年5月19日
	 * @param vo
	 * @param result
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/auditSuccess")
	public Boolean auditSuccess(@RequestBody List<AuditOrRejectReqVo> vo, BindingResult result, HttpServletRequest request) throws Exception{
		//参数校验
		if(result.hasErrors()) throw new VoNotValidException(this.getErrorMessages(result.getAllErrors()));
		//
		Long logonId = Long.parseLong(CookieUtil.getLogonId(request));
		
		//调用发票审核service
		for (AuditOrRejectReqVo auditOrRejectReqVo : vo) {
			try {
				invoiceService.audit(auditOrRejectReqVo.getInvoiceNo(), auditOrRejectReqVo.getSapCode(), logonId, POConstant.WR_ORDER_INVOICE_SGM_SUCCESS, null);
			} catch (Exception e) {//审核失败时，状态改为SGM处理失败
				logger.error("", e);
				invoiceService.audit(auditOrRejectReqVo.getInvoiceNo(), auditOrRejectReqVo.getSapCode(), logonId, POConstant.WR_ORDER_INVOICE_SGM_FAIL, null);
			}
		}
		
		return true;
	}
	
	/**
	 * 
	 * @author wangfl
	 * 退回
	 * @date 2016年5月19日
	 * @param vo
	 * @param result
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/reject")
	public Boolean reject(@RequestBody List<AuditOrRejectReqVo> vo, BindingResult result, HttpServletRequest request) throws Exception{
		//参数校验
		if(result.hasErrors()) throw new VoNotValidException(this.getErrorMessages(result.getAllErrors()));
		//
		Long logonId = Long.parseLong(CookieUtil.getLogonId(request));
		
		//调用发票审核service 
		for (AuditOrRejectReqVo auditOrRejectReqVo : vo) {
			invoiceService.audit(auditOrRejectReqVo.getInvoiceNo(), auditOrRejectReqVo.getSapCode(), logonId, POConstant.WR_ORDER_INVOICE_SGM_RETURN, null);
		}
		
		return true;
	}
}
