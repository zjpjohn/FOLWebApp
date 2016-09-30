/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : web.dealer
*
* @File name : BankTAvailAmountController.java
*
* @Author : DELL
*
* @Date : 2016年1月21日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2016年1月21日    DELL    1.0
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

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dealer.vo.BankInfoVo;
import com.dealer.vo.BankTicketValidAmountVo;
import com.sgm.dms.fol.bankTicket.api.BankBankTicketService;
import com.sgm.dms.fol.bankTicket.dto.BankTicketValidAmountDTO;
import com.sgm.dms.fol.common.service.controller.impl.BaseController;
import com.sgm.dms.fol.common.service.utils.BeanMapper;
import com.sgm.dms.fol.common.service.utils.MapUtil;
import com.sgm.dms.fol.common.service.utils.StringUtil;
import com.sgm.solution.framework.urlaccess.annotations.ValidationRequestUrl;

/*
*
* @author DELL
* TODO description
* @date 2016年1月21日
*/
@Controller
@RequestMapping("/bankTicket/bankAvailAmount")
public class BankAvailAmountController extends BaseController{
    // 日志
    protected Logger logger = LogManager.getLogger(this.getClass());
    
    @Autowired
    private BankBankTicketService bankBankTicketService;
    
    @RequestMapping("/query")
    @ValidationRequestUrl
    @ResponseBody
    public Map<String, Object> findBankAvailAmount(@RequestBody BankInfoVo bankInfoVo,HttpServletRequest request,HttpServletResponse response) throws Exception{
            BankTicketValidAmountDTO bankTicketValidAmountDTO=BeanMapper.map(bankInfoVo, BankTicketValidAmountDTO.class);
            List<BankTicketValidAmountDTO> dtos=bankBankTicketService.findBankAvailAmount(bankTicketValidAmountDTO);
            
            return MapUtil.setQueryDataToMap(StringUtil.fontFormatFinance(BeanMapper.mapList(dtos, BankTicketValidAmountVo.class)),Long.parseLong(bankBankTicketService.findBankAvailAmountCount(bankTicketValidAmountDTO)+""));
    }
}
