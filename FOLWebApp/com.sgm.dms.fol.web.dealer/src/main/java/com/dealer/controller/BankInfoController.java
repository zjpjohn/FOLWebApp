/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : web.sgm
*
* @File name : BankInfoController.java
*
* @Author : DELL
*
* @Date : 2016年1月18日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2016年1月18日    DELL    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/
	
package com.dealer.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sgm.dms.fol.bankTicket.api.BankInfoService;
import com.sgm.dms.fol.bankTicket.dto.BankInfoDTO;
import com.sgm.dms.fol.common.service.controller.impl.BaseController;
import com.sgm.dms.fol.common.service.utils.MapUtil;
import com.sgm.solution.framework.urlaccess.annotations.ValidationRequestUrl;

/*
*
* @author DELL
* TODO description
* @date 2016年1月18日
*/
@Controller
@RequestMapping("/bankTicket/bankInfo")
public class BankInfoController extends BaseController{
    // 日志
    protected Logger logger = LogManager.getLogger(this.getClass());
    
    @Autowired
    private BankInfoService bankInfoService;
    
    @RequestMapping("/findAll")
    @ResponseBody
    @ValidationRequestUrl
	public Object findBankInfoAll(HttpServletRequest request,HttpServletResponse response) throws Exception {

		List<BankInfoDTO> dtos = bankInfoService.getBankInfoAllList();
		// 响应
		return MapUtil.setQueryDataToMap(dtos, dtos == null ? 0L : dtos.size());
	}
    
}
