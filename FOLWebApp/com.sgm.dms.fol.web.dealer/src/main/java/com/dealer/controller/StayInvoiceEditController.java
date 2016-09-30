
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : web.dealer
 *
 * @File name : StayInvoiceEditController.java
 *
 * @Author : wangfl
 *
 * @Date : 2016年5月13日
 *
 ----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2016年5月13日    wangfl    1.0
 *
 *
 *
 *
 ----------------------------------------------------------------------------------
 */

package com.dealer.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.xml.datatype.DatatypeConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dealer.vo.DeleteStayInvoiceVo;
import com.dealer.vo.QueryStayInvoiceListReqVo;
import com.dealer.vo.QueryStayInvoiceListRespVo;
import com.dealer.vo.ReportStayInvoiceVo;
import com.dealer.vo.SaveStayInvoiceReqVo;
import com.dealer.vo.StayInvoiceDetailReqVo;
import com.dealer.vo.StayInvoiceDetailRespVo;
import com.dealer.vo.UpdateExpressNoReqVo;
import com.sgm.dms.fol.common.api.constants.CookieConstant;
import com.sgm.dms.fol.common.api.exception.MessageNotValidException;
import com.sgm.dms.fol.common.api.exception.VoNotValidException;
import com.sgm.dms.fol.common.service.controller.impl.BaseController;
import com.sgm.dms.fol.common.service.utils.BeanMapper;
import com.sgm.dms.fol.common.service.utils.CookieUtil;
import com.sgm.dms.fol.common.service.utils.MapUtil;
import com.sgm.dms.fol.common.service.utils.RSAUtil;
import com.sgm.dms.fol.common.service.utils.StringUtil;
import com.sgm.dms.fol.invoice.api.WrCollectService;
import com.sgm.dms.fol.invoice.dto.StayInvoiceDTO;
import com.sgm.dms.fol.invoice.dto.WrCollectDTO;
import com.sgm.dms.fol.invoice.dto.WrOrderDTO;
import com.sgm.dms.fol.invoice.wsclient.ClaimOperationWebService.SgmErrorFault;
import com.sgm.dms.fol.invoice.wsclient.ClaimOperationWebService.client.ClaimOperationClient;
import com.sgm.solution.framework.dataAuthority.annotations.SGMValidationRequest;
import com.sgm.solution.framework.dataAuthority.annotations.SGMValidationResource;
import com.sgm.solution.framework.dataAuthority.annotations.SGMValidationUserInfo;
import com.sgm.solution.framework.urlaccess.annotations.ValidationRequestUrl;

/**
 * @author wangfl TODO description
 * @date 2016年5月13日
 */

@RestController
@RequestMapping("/invoice/edit")
public class StayInvoiceEditController extends BaseController {

	@Autowired
	private WrCollectService wrCollectService;
	
	@Autowired
	private ClaimOperationClient claimOperationClient;

	/**
	 * 
	 * @author wangfl 分页查询
	 * @date 2016年5月13日
	 * @param vo
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/stayInvoiceList")
	@ValidationRequestUrl
	public Map<String, Object> queryStayInvoiceList(@RequestBody QueryStayInvoiceListReqVo vo,HttpServletRequest request,HttpServletResponse response) throws Exception {

			// Vo转Dto
			StayInvoiceDTO stayInvoiceDTO = BeanMapper.map(vo, StayInvoiceDTO.class);
			String sapCode = RSAUtil.decryptByPrivateKey(CookieUtil.getSapCode(request));// 获取sapCode
			stayInvoiceDTO.setSapCode(sapCode);

			// 调用service查询
			List<StayInvoiceDTO> stayInvoicePageList = wrCollectService.getStayInvoicePageList(stayInvoiceDTO);// 分页列表
			int totalNum = wrCollectService.getStayInvoiceTotalNum(stayInvoiceDTO);// 总数量

			// 组装响应对象
			List<QueryStayInvoiceListRespVo> voList = BeanMapper.mapList(stayInvoicePageList,
					QueryStayInvoiceListRespVo.class);
			StringUtil.fontFormatFinance(voList);

			Map<String, Object> responsedata = MapUtil.setQueryDataToMap(voList, Long.valueOf(totalNum));

		    return responsedata;
	}

	/**
	 * 
	 * @author wangfl 批量更新快递单号
	 * @date 2016年5月16日
	 * @param vo
	 * @return
	 */
	@RequestMapping("/bathchUpdateExpressNo")
	@SGMValidationRequest
	@ValidationRequestUrl
	public Boolean bathchUpdateExpressNo(@SGMValidationResource @Valid @RequestBody UpdateExpressNoReqVo vo, BindingResult result, @SGMValidationUserInfo @CookieValue(CookieConstant.COOKIE_USER_ID) String userId,HttpServletRequest request,HttpServletResponse response ) {

		// 参数校验
		if (result.hasErrors()) {
			throw new VoNotValidException(this.getErrorMessages(result.getAllErrors()));
		}

		// vo转Dto
		WrCollectDTO dto = BeanMapper.map(vo, WrCollectDTO.class);

		// 调用service处理
		wrCollectService.bathchUpdateExpressNo(dto);

		return true;
	}

	/**
	 * 
	 * @author wangfl 明细
	 * @date 2016年5月16日
	 * @param tsId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/detail")
	@ValidationRequestUrl
	public Map<String, Object> StayInvoiceDetail(@RequestBody StayInvoiceDetailReqVo vo,HttpServletRequest request,HttpServletResponse response) throws Exception {

			// 调用service
			List<WrOrderDTO> wrOrderDtoList = wrCollectService.getWrOrderListByTsId(vo.getTsId());
			List<StayInvoiceDetailRespVo> voList = BeanMapper.mapList(wrOrderDtoList, StayInvoiceDetailRespVo.class);
			StringUtil.fontFormatFinance(voList);


		Map<String, Object> responsedata = MapUtil.setQueryDataToMap(voList, Long.valueOf(voList.size()));
	    return responsedata;
	}

	/**
	 * 
	 * @author wangfl 删除待开发票并取消汇总
	 * @date 2016年5月17日
	 * @param vo
	 * @param result
	 * @return
	 * @throws DatatypeConfigurationException 
	 * @throws SgmErrorFault 
	 */
	@RequestMapping("/delete")
	@ValidationRequestUrl
	public Boolean delete(@Valid @RequestBody DeleteStayInvoiceVo vo, BindingResult result,HttpServletRequest request,HttpServletResponse response) throws SgmErrorFault, DatatypeConfigurationException {

		// 参数校验
		if (result.hasErrors()) {
			throw new VoNotValidException(this.getErrorMessages(result.getAllErrors()));
		}
		
		String[] splitTsId = vo.getTsId().split(",");

		for (String tsId : splitTsId) {
			List<WrOrderDTO> wrOrderList = wrCollectService.getWrOrderListByTsId(tsId);
			claimOperationClient.changeInvoiceToPayment(wrOrderList);
		}
		
		// 调用service处理
		wrCollectService.delStayInvoiceByTsId(vo.getTsId());
		
		

		return true;
	}

	/**
	 * 
	 * @author wangfl 保存
	 * @date 2016年5月17日
	 * @param voList
	 * @param result
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/save")
	@SGMValidationRequest
	@ValidationRequestUrl
	public Boolean save(@Valid @SGMValidationResource @RequestBody List<SaveStayInvoiceReqVo> voList, BindingResult result, @SGMValidationUserInfo @CookieValue(CookieConstant.COOKIE_USER_ID) String userId ,HttpServletRequest request,HttpServletResponse response) throws Exception {

		// 参数校验
		if (result.hasErrors()) {
			throw new VoNotValidException(this.getErrorMessages(result.getAllErrors()));
		}

		// Vo转Dto
		List<WrCollectDTO> dtoList = BeanMapper.mapList(voList, WrCollectDTO.class);
		for (WrCollectDTO wrCollectDTO : dtoList) {
			wrCollectDTO.setSapCode(RSAUtil.decryptByPrivateKey(CookieUtil.getSapCode(request)));
		}

		// 调用service处理
		wrCollectService.saveStayInvoice(dtoList);

		return true;
	}
	

	/**
	 * 
	 * @author wangfl 
	 * 上报
	 * @date 2016年5月17日
	 * @param vo
	 * @param result
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/report")
	@ValidationRequestUrl
	public Boolean report(@Valid @RequestBody ReportStayInvoiceVo vo, BindingResult result,HttpServletRequest request,HttpServletResponse response) throws Exception {

		// 参数校验
		if (result.hasErrors()) throw new MessageNotValidException(this.getErrorMessages(result.getAllErrors()));

		// 获取上报人
		Long logonId = Long.parseLong(CookieUtil.getLogonId(request));
		
		// 调用service处理
		wrCollectService.reportStayInvoice(vo.getTsId(), logonId, RSAUtil.decryptByPrivateKey(CookieUtil.getSapCode(request)));

		return true;
	}
}
