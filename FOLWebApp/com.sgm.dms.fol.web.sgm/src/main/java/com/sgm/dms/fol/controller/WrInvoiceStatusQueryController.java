
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : web.sgm
 *
 * @File name : WrInvoiceStatusQueryController.java
 *
 * @Author : wangfl
 *
 * @Date : 2016年5月20日
 *
 ----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2016年5月20日    wangfl    1.0
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
import javax.validation.Valid;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sgm.dms.fol.common.api.constants.CookieConstant;
import com.sgm.dms.fol.common.api.constants.POConstant;
import com.sgm.dms.fol.common.api.exception.VoNotValidException;
import com.sgm.dms.fol.common.service.controller.impl.BaseController;
import com.sgm.dms.fol.common.service.utils.BeanMapper;
import com.sgm.dms.fol.common.service.utils.CookieUtil;
import com.sgm.dms.fol.common.service.utils.MapUtil;
import com.sgm.dms.fol.common.service.utils.StringUtil;
import com.sgm.dms.fol.invoice.api.InvoiceNoReciveService;
import com.sgm.dms.fol.invoice.api.InvoiceService;
import com.sgm.dms.fol.invoice.dto.InvoiceDto;
import com.sgm.dms.fol.invoice.dto.WrOrderDTO;
import com.sgm.dms.fol.invoice.functions.ZFIDMSOUT;
import com.sgm.dms.fol.vo.SaveRemarkReqVo;
import com.sgm.dms.fol.vo.WrInvoiceStatusDetailReqRespVo;
import com.sgm.dms.fol.vo.WrInvoiceStatusDetailReqVo;
import com.sgm.dms.fol.vo.WrInvoiceStatusQueryListReqVo;
import com.sgm.dms.fol.vo.WrInvoiceStatusQueryListRespVo;
import com.sgm.dms.fol.vo.WrInvoiceStatusRehandleVo;
import com.sgm.dms.fol.vo.WrInvoiceStatusSendBackReqVo;
import com.sgm.solution.framework.dataAuthority.annotations.SGMValidationRequest;
import com.sgm.solution.framework.dataAuthority.annotations.SGMValidationResource;
import com.sgm.solution.framework.dataAuthority.annotations.SGMValidationUserInfo;
import com.sgm.solution.framework.dataAuthority.services.Encryptor;

/**
 * @author wangfl
 * 索赔发票状态查询
 * @date 2016年5月20日
 */

@RestController
@RequestMapping("/invoice/statusQuery")
public class WrInvoiceStatusQueryController extends BaseController {
	private Logger logger = LogManager.getLogger(this.getClass());
	
	@Autowired
	private InvoiceService invoiceService;
	
	@Autowired
	private InvoiceNoReciveService invoiceNoReciveService;
	
	@RequestMapping("/query")
	public Map<String, Object> statusQueryInvoiceList(@RequestBody WrInvoiceStatusQueryListReqVo vo) throws Exception {
		//Vo转Dto
		InvoiceDto dto = BeanMapper.map(vo, InvoiceDto.class);
		
		//调用service查询
		List<InvoiceDto> pageList = invoiceService.getPageList(dto);
		int totalNum = invoiceService.getListTotalNum(dto);
		
		// 组装响应对象
		List<WrInvoiceStatusQueryListRespVo> voList = BeanMapper.mapList(pageList,WrInvoiceStatusQueryListRespVo.class);
		StringUtil.fontFormatFinance(voList);

		return MapUtil.setQueryDataToMap(voList, Long.valueOf(totalNum));
	}
	
	/**
	 * 
	 * @author wangfl
	 * 明细
	 * @date 2016年5月20日
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/detail")
	public Map<String, Object> detail(@Valid@RequestBody WrInvoiceStatusDetailReqVo vo, BindingResult result) throws Exception {
		
		if(result.hasErrors()) throw new VoNotValidException(this.getErrorMessages(result.getAllErrors()));
		
		//调用service查询
		List<WrOrderDTO> pageList = invoiceService.getInvoiceDetailList(vo.getInvoiceNo(), vo.getSapCode());
		
		// 组装响应对象
		List<WrInvoiceStatusDetailReqRespVo> voList = BeanMapper.mapList(pageList,WrInvoiceStatusDetailReqRespVo.class);
		StringUtil.fontFormatFinance(voList);

		return MapUtil.setQueryDataToMap(voList, Long.valueOf(voList.size()));
	}
	
	/**
	 * 
	 * @author wangfl
	 * 重处理
	 * @date 2016年5月20日
	 * @param vo
	 * @param result
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/rehandle")
	public Boolean rehandle(@Valid@RequestBody WrInvoiceStatusRehandleVo vo, BindingResult result, HttpServletRequest request) throws Exception{
	    //参数校验
		if(result.hasErrors()) throw new VoNotValidException(this.getErrorMessages(result.getAllErrors()));
		
		String[] invoiceNoArray = vo.getInvoiceNo().split(",");
		for (String invoiceNo : invoiceNoArray) {
			//调用sap接口，待开发
			
			ZFIDMSOUT zfidmsout = new ZFIDMSOUT();
		    try {
		        //调用SAP              
		        zfidmsout=invoiceNoReciveService.invokeByAnew(invoiceNo,zfidmsout);
		    } catch (Exception e) {
		        e.printStackTrace();
		        logger.error(e.getMessage(),e);
		        invoiceNoReciveService.resultDispose(invoiceNo, zfidmsout);
		    }
		}
		
		return true;
	}
	
	/**
	 * 
	 * @author wangfl
	 * 退回
	 * @date 2016年5月20日
	 * @param vo
	 * @param result
	 * @param request
	 * @return
	 * @throws NumberFormatException
	 * @throws Exception
	 */
	@RequestMapping("/sendBack")
	public Boolean sendBack(@RequestBody WrInvoiceStatusSendBackReqVo vo, BindingResult result, HttpServletRequest request) throws NumberFormatException, Exception{
		 //参数校验
		if(result.hasErrors()) throw new VoNotValidException(this.getErrorMessages(result.getAllErrors()));
		
		Long logonId = Long.parseLong(CookieUtil.getLogonId(request));
		
		//调用发票审核service 
		for (String invoiceNo : vo.getInvoiceNo().split(",")) {
			String[] split = invoiceNo.split("\\|\\|");
			invoiceService.audit(split[0], split[1], logonId, POConstant.WR_ORDER_INVOICE_SGM_RETURN, vo.getApproveMsg());
	    }
		
		return true;
	}
	
	/**
	 * 
	 * @author wangfl
	 * 保存备注
	 * @date 2016年5月20日
	 * @param voList
	 * @param result
	 * @param request
	 * @return
	 * @throws NumberFormatException
	 * @throws Exception
	 */
	@RequestMapping("/saveRemark")
	@SGMValidationRequest
	public Boolean saveRemark(@Valid@SGMValidationResource@RequestBody List<SaveRemarkReqVo> voList, BindingResult result, HttpServletRequest request,@SGMValidationUserInfo @CookieValue(CookieConstant.COOKIE_USER_ID) String userId ) throws NumberFormatException, Exception{
		//参数校验
		if(result.hasErrors()) throw new VoNotValidException(this.getErrorMessages(result.getAllErrors()));
		
		Long logonId = Long.parseLong(CookieUtil.getLogonId(request));
		
		//调用保存备注service 
		for (SaveRemarkReqVo vo : voList) {
			invoiceService.saveInvoiceRemark(vo.getInvoiceNo(),vo.getSapCode(), vo.getRemark(), logonId);
		}
		
		return true;
	}
	@RequestMapping("/saveRemark/getSign")
	public Map<String, Object> getSaveRemarkSign(@RequestBody List<SaveRemarkReqVo> voList,@CookieValue(CookieConstant.COOKIE_USER_ID) String userId) throws NumberFormatException, Exception{
		if(null != voList){
			for (SaveRemarkReqVo SaveRemarkReqVo : voList) {
				String[] refVales=new String[]{SaveRemarkReqVo.getRemark()};
		        String sign=Encryptor.generateSignFromResource(userId,refVales);
		        SaveRemarkReqVo.setSign(sign);
			}
			return MapUtil.setQueryDataToMap(voList, Long.parseLong(voList.size()+""));
		}

		return null;
	}
	
	
}
