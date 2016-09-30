
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : web.dealer
 *
 * @File name : WrCollectController.java
 *
 * @Author : wangfl
 *
 * @Date : 2016年5月10日
 *
 ----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2016年5月10日    wangfl    1.0
 *
 *
 *
 *
 ----------------------------------------------------------------------------------
 */
	
package com.dealer.controller;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dealer.dro.WrOrderListDro;
import com.dealer.vo.CollectWrOrderReqVo;
import com.dealer.vo.QueryWrOrderListReqVo;
import com.dealer.vo.QueryWrOrderListRespVo;
import com.sgm.dms.fol.common.api.constants.CookieConstant;
import com.sgm.dms.fol.common.api.exception.ServiceBizException;
import com.sgm.dms.fol.common.api.exception.VoNotValidException;
import com.sgm.dms.fol.common.service.controller.impl.BaseController;
import com.sgm.dms.fol.common.service.utils.AutoPojo;
import com.sgm.dms.fol.common.service.utils.BeanMapper;
import com.sgm.dms.fol.common.service.utils.CookieUtil;
import com.sgm.dms.fol.common.service.utils.DateUtil;
import com.sgm.dms.fol.common.service.utils.MapUtil;
import com.sgm.dms.fol.common.service.utils.RSAUtil;
import com.sgm.dms.fol.common.service.utils.StringUtil;
import com.sgm.dms.fol.invoice.api.WrCollectService;
import com.sgm.dms.fol.invoice.dto.WrOrderDTO;
import com.sgm.dms.fol.invoice.restclient.wol_claimOrder.client.ClaimOrderClient;
import com.sgm.dms.fol.invoice.restclient.wol_claimOrder.dto.ClaimOrderDto;
import com.sgm.dms.fol.invoice.restclient.wol_claimOrder.dto.ClaimOrderListReqDto;
import com.sgm.dms.fol.invoice.restclient.wol_claimOrder.dto.ClaimOrderRespDto;
import com.sgm.dms.fol.invoice.wsclient.ClaimOperationWebService.client.ClaimOperationClient;
import com.sgm.dms.fol.reconciliation.api.ReconciliationManagementService;
import com.sgm.solution.framework.dataAuthority.annotations.SGMValidationRequest;
import com.sgm.solution.framework.dataAuthority.annotations.SGMValidationResource;
import com.sgm.solution.framework.dataAuthority.annotations.SGMValidationUserInfo;
import com.sgm.solution.framework.dataAuthority.services.Encryptor;
import com.sgm.solution.framework.urlaccess.annotations.ValidationRequestUrl;

/**
 * @author wangfl
 * 索赔月度财务汇总Controller
 * @date 2016年5月10日
 */

@RestController
@RequestMapping("/invoice/wrCollect")
public class WrCollectController extends BaseController {
	
	@Autowired
	private WrCollectService wrCollectService;
	
	@Autowired
	private ReconciliationManagementService reconciliationManagementService;
	
	@Autowired
	private ClaimOrderClient claimOrderClient;
	
	@Autowired
	private ClaimOperationClient claimOperationClient;
	
	
	/**
	 * 
	 * @author wangfl
	 * 从WOL系统，查询未汇总的索赔单
	 * @date 2016年5月10日
	 * @param vo
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/wrOrders")
	@ValidationRequestUrl
	public Map<String, Object> queryWrOrderList(@RequestBody QueryWrOrderListReqVo vo,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		//vo转dto
		ClaimOrderListReqDto reqDto = voToDto(vo, request,response);
		
		/* 调用rest请求接口，访问wol系统查询索赔单  */
		ClaimOrderRespDto claimOrderDto = claimOrderClient.getClaimOrderList(reqDto);
		
		//dto转vo
		List<QueryWrOrderListRespVo> wrOrderList = DtoToVo(claimOrderDto);
		
		//响应
		return MapUtil.setQueryDataToMap(wrOrderList, claimOrderDto == null ? 0L : claimOrderDto.getTotal());
	}


	/**
	 * 
	 * @author wangfl
	 * 汇总
	 * @date 2016年5月12日
	 * @param voList
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/collect")
	@SGMValidationRequest
	@ValidationRequestUrl
	public Boolean collect(@SGMValidationResource @Valid @RequestBody List<CollectWrOrderReqVo> voList,HttpServletRequest request,HttpServletResponse response, @SGMValidationUserInfo @CookieValue(CookieConstant.COOKIE_USER_ID) String userId) throws Exception{
		/**参数校验*/
		
		for (CollectWrOrderReqVo collectWrOrderReqVo : voList) {
			if(collectWrOrderReqVo.getWrNo() == null || collectWrOrderReqVo.getWarrantyCode() == null || collectWrOrderReqVo.getLineNo() == null){
				throw new VoNotValidException("索赔单号、索赔编码、行号不能为空");
			}
		}
		
		/** Vo转Dto */
		List<WrOrderDTO> wrOrderList = BeanMapper.mapList(voList, WrOrderDTO.class);
		
		String sapCode = RSAUtil.decryptByPrivateKey(CookieUtil.getSapCode(request));// 获取sapCode
		Long userIdTemp = CookieUtil.getUserId(request);// 获取当前用户userId
		
		BigDecimal totalGross = new BigDecimal(0);
		if (null != wrOrderList) {
			for (WrOrderDTO wrOrder : wrOrderList) {// 设置索赔单sapCode、创建人、修改人
				wrOrder.setSapCode(sapCode);
				wrOrder.setCreateBy(userIdTemp);
				wrOrder.setUpdateBy(userIdTemp);
				if(wrOrder.getGross() != null){//计算合并的总金额
					totalGross = totalGross.add(wrOrder.getGross());
				}
			}
		}
		if(totalGross.compareTo(new BigDecimal(0)) < 0){
			throw new ServiceBizException("合并的索赔单总金额为负数，不能合并。");
		}
		
		claimOperationClient.changePaymentToInvoice(wrOrderList);
		
		/** 调用汇总service */
		wrCollectService.collectWrOrder(wrOrderList);

		return true;
	}
	
	/**
	 * 获取汇总签名
	 * @author wangfl
	 * @date 2016年7月5日
	 * @param voList
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/collect/getSign")
	@ValidationRequestUrl
	public  Map<String, Object> getCollectSign(@RequestBody List<CollectWrOrderReqVo> voList,@CookieValue(CookieConstant.COOKIE_USER_ID) String userId,HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		if(null != voList){
			for (CollectWrOrderReqVo collectWrOrderReqVo : voList) {
				String[] refVales=new String[]{collectWrOrderReqVo.getGross().toString(),collectWrOrderReqVo.getLinetotal().toString(),collectWrOrderReqVo.getPartCost().toString(),collectWrOrderReqVo.getLabourCost().toString(),collectWrOrderReqVo.getOtherCost().toString(),collectWrOrderReqVo.getTax().toString()};
		        String sign=Encryptor.generateSignFromResource(userId,refVales);
		        collectWrOrderReqVo.setSign(sign);
			}
			return MapUtil.setQueryDataToMap(voList, Long.parseLong(voList.size()+""));
		}

		return null;
	}
	
	@RequestMapping("/exportExcel")
	@ValidationRequestUrl
	public ResponseEntity<byte[]> exportExcel(HttpServletRequest request,HttpServletResponse response) throws Exception{
		QueryWrOrderListReqVo vo = AutoPojo.bindRequestParam(request, QueryWrOrderListReqVo.class);
		
		// vo转dto
		ClaimOrderListReqDto reqDto = voToDto(vo,request,response);

		/* 调用rest请求接口，访问wol系统查询索赔单 */
		ClaimOrderRespDto claimOrderDto = claimOrderClient.getClaimOrderList(reqDto);

		// dto转vo
		List<WrOrderListDro> wrOrderDroList = DtoToDro(claimOrderDto);
		
		String[] header = { "序号", "索赔月", "批次  ", "索赔类型", "车辆属性 ", "工时代码","索赔单号  ","行号 ", "含税金额", "VIN " , "不含税金额" , "配件费用" , "工时费用" , "其他费用", "税金 ", "追加", "抵扣 ","工单号 ","车系 ","车型"};
		StringUtil.fontFormatFinance(wrOrderDroList);
		
		
		// 定义文件名称
		String fileName = "WR_ORDER_LIST" + DateUtil.date2str(new Date()) + ".xls";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDispositionFormData("attachment", fileName);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		// 导出
		ByteArrayOutputStream bos = reconciliationManagementService.<WrOrderListDro> exportXls4BillFile(wrOrderDroList,request.getParameter("token"), header, fileName);
		return new ResponseEntity<byte[]>(bos.toByteArray(), headers, HttpStatus.CREATED);
	}

	private ClaimOrderListReqDto voToDto(QueryWrOrderListReqVo vo,HttpServletRequest request,HttpServletResponse response) throws Exception {
		ClaimOrderListReqDto reqDto = new ClaimOrderListReqDto();
		//reqDto.setMonthBegin(vo.getStartWrDate());
		reqDto.setBatchNoBegin(vo.getStartWrBatch());
		//reqDto.setMonthEnd(vo.getEndWrDate());
		reqDto.setBatchNoEnd(vo.getEndWrBatch());
		reqDto.setWrType(vo.getWrType());
		reqDto.setSapCode(RSAUtil.decryptByPrivateKey(CookieUtil.getSapCode(request)));//从cookie获取sapCode
		reqDto.setBeginNo(vo.getBeginNo());
		reqDto.setEndNo(vo.getEndNo());
		return reqDto;
	}
	
	private List<QueryWrOrderListRespVo> DtoToVo(ClaimOrderRespDto claimOrderDto) {
		//dto转vo
		List<QueryWrOrderListRespVo> wrOrderList = new ArrayList<QueryWrOrderListRespVo>();
		if (null != claimOrderDto && null != claimOrderDto.getClaimOrderResultDTOList()) {
			QueryWrOrderListRespVo tempVo = null;
			for (ClaimOrderDto dto : claimOrderDto.getClaimOrderResultDTOList()) {
				tempVo = new QueryWrOrderListRespVo();
				tempVo.setWrDate(dto.getWarrantyMonth());
				tempVo.setWrBatch(dto.getBatchNo());
				tempVo.setWrType(dto.getClaimType());
				tempVo.setCarAttr(dto.getVehicleProperty());
				tempVo.setWorkHoursCode(dto.getLabourOperationCode());
				tempVo.setWrNo(dto.getClaimNo());
				tempVo.setLineNo(dto.getLineNo());
				tempVo.setGross(new BigDecimal(dto.getApprovedAmountTatal() + ""));
				tempVo.setVin(dto.getVin());
				tempVo.setLinetotal(new BigDecimal(dto.getApprovedAmount() + ""));
				tempVo.setPartCost(new BigDecimal(dto.getPartsAmountTotal() + ""));
				tempVo.setLabourCost(new BigDecimal(dto.getLabourAmountTotal() + ""));
				tempVo.setOtherCost(new BigDecimal(dto.getOtherAmountTotal() + ""));
				tempVo.setTax(new BigDecimal(dto.getApprovedAmountTax() + ""));
				tempVo.setAdditional(dto.getAppendFlag() + "");
				tempVo.setDeduction(dto.getDebitFlag() + "");
				tempVo.setRoNo(dto.getRoNo());
				tempVo.setCarSeries(dto.getSeries());
				tempVo.setCarModel(dto.getModel());
				tempVo.setSapCode(dto.getSapCode());
				tempVo.setWarrantyCode(dto.getWarrantyCode());
				wrOrderList.add(tempVo);
			}
		}
		return wrOrderList;
	}
	
	private List<WrOrderListDro> DtoToDro(ClaimOrderRespDto claimOrderDto) {
		//dto转vo
		List<WrOrderListDro> wrOrderList = new ArrayList<WrOrderListDro>();
		if (null != claimOrderDto && null != claimOrderDto.getClaimOrderResultDTOList()) {
			WrOrderListDro tempDro = null;
			int i = 0;
			for (ClaimOrderDto dto : claimOrderDto.getClaimOrderResultDTOList()) {
				tempDro = new WrOrderListDro();
				tempDro.setIndex(++i);
				tempDro.setWrDate(dto.getWarrantyMonth());
				tempDro.setWrBatch(dto.getBatchNo());
				tempDro.setWrType(dto.getClaimType());
				tempDro.setCarAttr(dto.getVehicleProperty());
				tempDro.setWorkHoursCode(dto.getLabourOperationCode());
				tempDro.setWrNo(dto.getClaimNo());
				tempDro.setLineNo(dto.getLineNo());
				tempDro.setGross(new BigDecimal(dto.getApprovedAmountTatal() + ""));
				tempDro.setVin(dto.getVin());
				tempDro.setLinetotal(new BigDecimal(dto.getApprovedAmount() + ""));
				tempDro.setPartCost(new BigDecimal(dto.getPartsAmountTotal() + ""));
				tempDro.setLabourCost(new BigDecimal(dto.getLabourAmountTotal() + ""));
				tempDro.setOtherCost(new BigDecimal(dto.getOtherAmountTotal() + ""));
				tempDro.setTax(new BigDecimal(dto.getApprovedAmountTax() + ""));
				tempDro.setAdditional(dto.getAppendFlag() + "");
				tempDro.setDeduction(dto.getDebitFlag() + "");
				tempDro.setRoNo(dto.getRoNo());
				tempDro.setCarSeries(dto.getSeries());
				tempDro.setCarModel(dto.getModel());
				wrOrderList.add(tempDro);
			}
		}
		return wrOrderList;
	}
}
