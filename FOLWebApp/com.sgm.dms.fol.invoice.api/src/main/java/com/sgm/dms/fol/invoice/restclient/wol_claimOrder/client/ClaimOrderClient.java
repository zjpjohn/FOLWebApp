
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : invoice.service
 *
 * @File name : ClaimOrderClient.java
 *
 * @Author : wangfl
 *
 * @Date : 2016年6月2日
 *
 ----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2016年6月2日    wangfl    1.0
 *
 *
 *
 *
 ----------------------------------------------------------------------------------
 */
	
package com.sgm.dms.fol.invoice.restclient.wol_claimOrder.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.sgm.dms.fol.common.api.exception.ServiceAppException;
import com.sgm.dms.fol.common.service.utils.PropertiesUtil;
import com.sgm.dms.fol.invoice.restclient.wol_claimOrder.dto.ClaimOrderListReqDto;
import com.sgm.dms.fol.invoice.restclient.wol_claimOrder.dto.ClaimOrderRespDto;

/**
 * @author wangfl
 * 调用wol系统中索赔单列表获取接口
 * @date 2016年6月2日
 */

@Component
public class ClaimOrderClient {
	
	@Autowired
	private RestTemplate restTemplate;
	
	public ClaimOrderRespDto getClaimOrderList(ClaimOrderListReqDto request) {
		ClaimOrderRespDto result = null;

		try {
			result = restTemplate.postForObject(PropertiesUtil.getServiceURL("wol_vehicle_claimOrder_url"), request, ClaimOrderRespDto.class);
		} catch (Exception e) {
			throw new ServiceAppException("调用wol系统中的索赔单列表获取接口异常", e);
		}

		return result;
	}
	
}
