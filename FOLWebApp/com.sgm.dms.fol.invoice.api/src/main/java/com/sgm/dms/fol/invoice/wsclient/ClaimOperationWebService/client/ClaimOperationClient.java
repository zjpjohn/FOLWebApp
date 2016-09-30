
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : invoice.api
 *
 * @File name : ClaimOperationClient.java
 *
 * @Author : wangfl
 *
 * @Date : 2016年6月7日
 *
 ----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2016年6月7日    wangfl    1.0
 *
 *
 *
 *
 ----------------------------------------------------------------------------------
 */
	
package com.sgm.dms.fol.invoice.wsclient.ClaimOperationWebService.client;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsClientProxy;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
//import org.apache.cxf.transport.http.HTTPConduit;
//import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.springframework.stereotype.Component;

import com.sgm.dms.fol.common.api.constants.CodeConstant;
import com.sgm.dms.fol.common.api.constants.POConstant;
import com.sgm.dms.fol.common.api.exception.ServiceBizException;
import com.sgm.dms.fol.invoice.dto.WrOrderDTO;
import com.sgm.dms.fol.invoice.wsclient.ClaimOperationWebService.ChangeInvoiceToPayment;
import com.sgm.dms.fol.invoice.wsclient.ClaimOperationWebService.ChangeInvoiceToPaymentCompensation;
import com.sgm.dms.fol.invoice.wsclient.ClaimOperationWebService.ChangePaymentToInvoice;
import com.sgm.dms.fol.invoice.wsclient.ClaimOperationWebService.ClaimOperationCompensationRequestVO;
import com.sgm.dms.fol.invoice.wsclient.ClaimOperationWebService.ClaimOperationRequestVO;
import com.sgm.dms.fol.invoice.wsclient.ClaimOperationWebService.ClaimOperationResultVO;
import com.sgm.dms.fol.invoice.wsclient.ClaimOperationWebService.ClaimOperationWebService;
import com.sgm.dms.fol.invoice.wsclient.ClaimOperationWebService.SgmErrorFault;
import com.sgm.dms.fol.invoice.wsclient.ClaimOperationWebService.SimpleClaimOrderVO;
import com.sgm.dms.fol.invoice.wsclient.ClaimOperationWebService.impl.ClaimOperationWebServiceImplService;
import com.sgm.dms.fol.invoice.wsclient.commonheader.SGMCommonHeaderType;

/**
 * @author wangfl
 * 索赔操作调用客户端
 * @date 2016年6月7日
 */
@Component
public class ClaimOperationClient {
	private Logger logger = LogManager.getLogger(this.getClass());
	
	private ClaimOperationWebService claimOperationWebService;
	
	private void addHeaderAndLogInterceptor() {
		//增加过滤器调用
		if(null == claimOperationWebService){
			ClaimOperationWebServiceImplService factory = new ClaimOperationWebServiceImplService();
			claimOperationWebService = factory.getClaimOperationWebServiceImplPort();
		}
		Client client = JaxWsClientProxy.getClient(this.claimOperationWebService);
		client.getInInterceptors().add(new LoggingInInterceptor());//输入流日志拦截器
		//client.getOutInterceptors().add(new CommonHeaderInterceptor(CodeConstant.WOL_PROJECT_NAME));//输出Header头添加拦截器
		client.getOutInterceptors().add(new LoggingOutInterceptor());//输出流日志拦截器
		
		/*//连接超时时间设置
		HTTPConduit http = (HTTPConduit) client.getConduit();
		HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
		httpClientPolicy.setConnectionTimeout(5000);
		httpClientPolicy.setAllowChunking(false);
		httpClientPolicy.setReceiveTimeout(5000);
		http.setClient(httpClientPolicy);*/
	}
	
	
	public void changePaymentToInvoice(List<WrOrderDTO> wrOrderList) throws SgmErrorFault, DatatypeConfigurationException {
		//组装请求对象
		ClaimOperationRequestVO reqVo = new ClaimOperationRequestVO();
		reqVo.setCrosscheckAmount(wrOrderList.size());
		reqVo.setTsId(UUID.randomUUID().toString());//tsId设为UUID
		reqVo.setTsTime(new Date().getTime());
		
		List<SimpleClaimOrderVO> simpleClaimOrder = reqVo.getSimpleClaimOrder();
		
		SimpleClaimOrderVO claimVo = null;
		for (WrOrderDTO WrOrderDTO : wrOrderList) {
			claimVo = new SimpleClaimOrderVO();
			claimVo.setChangeTime(new Date().getTime());
			claimVo.setClaimNo(WrOrderDTO.getWrNo());
			claimVo.setCrosscheckBeforeStatus(POConstant.CLAIM_STATUS_PAYMENT);
			claimVo.setCrosscheckAfterStatus(POConstant.CLAIM_STATUS_INVOICE);
			claimVo.setLineNo(WrOrderDTO.getLineNo());
			//claimVo.setReasonCode(null);
			//claimVo.setReferCode(null);
			//claimVo.setReferType(0);
			//claimVo.setRemark(null);
			claimVo.setWarrantyCode(WrOrderDTO.getWarrantyCode());
			
			
			simpleClaimOrder.add(claimVo);
		}
		
		ChangePaymentToInvoice body = new ChangePaymentToInvoice();
		body.setArg0(reqVo);
		
		//添加响应头
		addHeaderAndLogInterceptor();
		
		SGMCommonHeaderType headerType = new SGMCommonHeaderType();
		headerType.setMessageId(UUID.randomUUID().toString());
		headerType.setFrom(CodeConstant.PROJECT_NAME);
		headerType.setTo(CodeConstant.WOL_PROJECT_NAME);
		GregorianCalendar gcal =new GregorianCalendar();
		XMLGregorianCalendar xgcal= DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
		headerType.setTimestamp(xgcal);
		try {
			ClaimOperationResultVO changePaymentToInvoice = claimOperationWebService.changePaymentToInvoice(headerType, reqVo);
			
			if(changePaymentToInvoice == null || changePaymentToInvoice.getResultCode() != 0){
				ServiceBizException serviceBizException = new ServiceBizException("索赔单操作WEBSERVICE_changePaymentToInvoice调用异常");
				logger.error("索赔单操作WEBSERVICE_changePaymentToInvoice调用异常", serviceBizException);
				throw serviceBizException;
			}
		} catch (SgmErrorFault e) {
			logger.error("索赔单操作WEBSERVICE_changePaymentToInvoice调用异常:"+e.getMessage());
			//异常时进行补偿操作
			ClaimOperationCompensationRequestVO compensationRequestVO = new ClaimOperationCompensationRequestVO();
			compensationRequestVO.setTsId(reqVo.getTsId());
			claimOperationWebService.changePaymentToInvoiceCompensation(headerType,compensationRequestVO);
			
			throw new ServiceBizException("索赔单操作WEBSERVICE_changePaymentToInvoice调用异常", e);
		}
	}
	
	public void changeInvoiceToPayment(List<WrOrderDTO> wrOrderList) throws SgmErrorFault, DatatypeConfigurationException {
		//组装请求对象
		ClaimOperationRequestVO reqVo = new ClaimOperationRequestVO();
		reqVo.setCrosscheckAmount(wrOrderList.size());
		reqVo.setTsId(UUID.randomUUID().toString());//tsId设为UUID
		reqVo.setTsTime(new Date().getTime());
		
		List<SimpleClaimOrderVO> simpleClaimOrder = reqVo.getSimpleClaimOrder();
		
		SimpleClaimOrderVO claimVo = null;
		for (WrOrderDTO WrOrderDTO : wrOrderList) {
			claimVo = new SimpleClaimOrderVO();
			claimVo.setChangeTime(new Date().getTime());
			claimVo.setClaimNo(WrOrderDTO.getWrNo());
			claimVo.setCrosscheckBeforeStatus(POConstant.CLAIM_STATUS_INVOICE);
			claimVo.setCrosscheckAfterStatus(POConstant.CLAIM_STATUS_PAYMENT);
			claimVo.setLineNo(WrOrderDTO.getLineNo());
			//claimVo.setReferCode(null);
			//claimVo.setReasonCode(null);
			//claimVo.setReferType(0);
			//claimVo.setRemark(null);
			claimVo.setWarrantyCode(WrOrderDTO.getWarrantyCode());
			
			simpleClaimOrder.add(claimVo);
		}
		
		ChangeInvoiceToPayment body = new ChangeInvoiceToPayment();
		body.setArg0(reqVo);
		
		//添加响应头
		addHeaderAndLogInterceptor();
		
		SGMCommonHeaderType headerType = new SGMCommonHeaderType();
		headerType.setMessageId(UUID.randomUUID().toString());
		headerType.setFrom(CodeConstant.PROJECT_NAME);
		headerType.setTo(CodeConstant.WOL_PROJECT_NAME);
		GregorianCalendar gcal =new GregorianCalendar();
		XMLGregorianCalendar xgcal= DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
		headerType.setTimestamp(xgcal);
		
		try {
			ClaimOperationResultVO changeInvoiceToPayment = claimOperationWebService.changeInvoiceToPayment(headerType, reqVo);
			
			if(changeInvoiceToPayment == null || changeInvoiceToPayment.getResultCode() != 0){
				ServiceBizException serviceBizException = new ServiceBizException("索赔单操作WEBSERVICE_changeInvoiceToPayment调用异常");
				logger.error("索赔单操作WEBSERVICE_changeInvoiceToPayment调用异常", serviceBizException);
				throw serviceBizException;
			}
		} catch (SgmErrorFault e) {
			logger.error("索赔单操作WEBSERVICE_changeInvoiceToPayment调用异常:" + e.getMessage());
			//异常时进行补偿操作
			ChangeInvoiceToPaymentCompensation compensationBody = new ChangeInvoiceToPaymentCompensation();
			ClaimOperationCompensationRequestVO compensationRequestVO = new ClaimOperationCompensationRequestVO();
			compensationRequestVO.setTsId(reqVo.getTsId());
			compensationBody.setArg0(compensationRequestVO);
			claimOperationWebService.changeInvoiceToPaymentCompensation(headerType, compensationRequestVO);
			
			throw new ServiceBizException("索赔单操作WEBSERVICE_changeInvoiceToPayment调用异常", e);
		}
	}

}
