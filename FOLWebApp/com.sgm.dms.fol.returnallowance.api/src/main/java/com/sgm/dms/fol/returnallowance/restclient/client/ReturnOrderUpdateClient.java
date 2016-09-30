/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : returnallowance.api
*
* @File name : ReturnOrderUpdateClient.java
*
* @Author : st78sr
*
* @Date : 2016年8月18日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2016年8月18日    st78sr    1.0
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
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.sgm.dms.fol.common.api.exception.ServiceAppException;
import com.sgm.dms.fol.common.api.exception.ServiceBizException;
import com.sgm.dms.fol.common.service.services.impl.BaseService;
import com.sgm.dms.fol.common.service.utils.PropertiesUtil;


/*
*
* @author st78sr
* TODO description
* @date 2016年8月18日
*/
@Component
public class ReturnOrderUpdateClient extends BaseService {

    @Autowired
    private RestTemplate restTemplate;
    
    //SGM端：在FOL折让单状态发生变更时，通过此方法，根据退货证明单号，去更新POL的退货证明表中的“折让单处理状态”字段
    public void updateReturnOrder4SGM(String returnOrderNo, Integer discountOrderStatus, String sapCode){
        //请求参数
        Map<String, Object> urlVariables = new HashMap<String, Object>();
        urlVariables.put("dealerCode", sapCode);//dealerCode请求参数
        urlVariables.put("returnOrderNo", returnOrderNo);//returnOrderNo请求参数
        urlVariables.put("discountOrderStatus", discountOrderStatus);//discountOrderStatus请求参数
        
        //请求url
        String url = null;
		try {
			url = PropertiesUtil.getServiceURL("pol_return_order_update_url")
			        + "?dealerCode={dealerCode}&returnOrderNo={returnOrderNo}&discountOrderStatus={discountOrderStatus}";
		} catch (IOException e) {
			throw new ServiceAppException("获取pol退货证明状态更新接口url失败", e);
		}
        
        //执行请求
        try {
            restTemplate.put(url, null, urlVariables);
        } catch (Exception e) {
            throw new ServiceBizException("退货证明状态更新接口调用发生未知异常", e);
        }
        
        //处理请求结果(成功:200 ,失败:412(业务异常)、550(系统异常))
//        int statusCode = responseEntity.getStatusCode().value();
//        switch (statusCode) {
//        case 412:
//            throw new ServiceBizException("退货证明状态更新接口调用发生业务异常");
//        case 550:
//            throw new ServiceBizException("退货证明状态更新接口调用发生系统异常");
//        case 200:
//            //成功
//            logger.info("退货证明状态更新接口调用end，响应结果为：\n" + new JSONObject(responseEntity.getBody()));// 响应结果日志
//        default:
//            throw new ServiceBizException("退货证明状态更新接口返回未知statusCode，程序无法处理。");
//        }
    }
    
    //Dealer端：在FOL折让单状态发生变更时，通过此方法，根据退货证明单号，去更新POL的退货证明表中的“折让单处理状态”字段
    public void updateReturnOrder4Dealer(String returnOrderNo, Integer discountOrderStatus) {
        //获取经销商代码
        String sapCode = getSapCode();
        
        updateReturnOrder4SGM(returnOrderNo,discountOrderStatus,sapCode);
    }
}
