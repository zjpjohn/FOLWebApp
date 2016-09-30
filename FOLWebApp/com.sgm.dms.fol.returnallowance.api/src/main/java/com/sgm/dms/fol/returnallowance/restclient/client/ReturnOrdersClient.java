
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : returnallowance.api
 *
 * @File name : ReturnOrderList.java
 *
 * @Author : wangfl
 *
 * @Date : 2016年8月12日
 *
 ----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2016年8月12日    wangfl    1.0
 *
 *
 *
 *
 ----------------------------------------------------------------------------------
 */
	
package com.sgm.dms.fol.returnallowance.restclient.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.sgm.dms.fol.common.api.exception.ServiceBizException;
import com.sgm.dms.fol.common.service.services.impl.BaseService;
import com.sgm.dms.fol.common.service.utils.PropertiesUtil;
import com.sgm.dms.fol.returnallowance.restclient.dto.ReturnOrdersRespDto;

/**
 * 退货证明列表获取接口调用client
 * @author wangfl
 * @date 2016年8月12日
 */
@Component
public class ReturnOrdersClient extends BaseService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	public ReturnOrdersRespDto getReturnOrders(Integer beginNo, Integer endNo) throws IOException{
		//请求参数
		Map<String, Object> requestParams = new HashMap<String, Object>();
		Integer pageSize = (endNo-beginNo+1)/10*10;//pageSize=(endNo-beginNo+1)/10*10
		Integer currentPage = endNo/pageSize;//currentPage=endNo/pageSize
		requestParams.put("dealerCode", getSapCode());//dealerCode请求参数
		requestParams.put("pageSize", pageSize);//pageSize请求参数;
		requestParams.put("currentPage", currentPage);//currentPage请求参数
		
		//请求url
		String url = PropertiesUtil.getServiceURL("pol_return_orders_url")
				+ "?dealerCode={dealerCode}&pageSize={pageSize}&currentPage={currentPage}";
		
		//执行请求
		ResponseEntity<ReturnOrdersRespDto> responseEntity = null;
		try {
			responseEntity = restTemplate.getForEntity(url, ReturnOrdersRespDto.class, requestParams);
			return responseEntity.getBody();
		} catch (Exception e) {
			throw new ServiceBizException("退货证明列表获取接口调用发生异常", e);
		}
		
		//处理请求结果(成功:200 ,失败:412(业务异常)、550(系统异常))
		//int statusCode = responseEntity.getStatusCode().value();
	}

}
