/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : web.sgm
*
* @File name : BankTicketInterestController.java
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
	
package com.sgm.dms.fol.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sgm.dms.fol.bankTicket.api.BankTicketBusinessInquiriesService;
import com.sgm.dms.fol.bankTicket.dto.BankTicketInterestDTO;
import com.sgm.dms.fol.bankTicket.dto.BankTicketInterestDeatilQueryDTO;
import com.sgm.dms.fol.common.service.controller.impl.BaseController;
import com.sgm.dms.fol.common.service.utils.BeanMapper;
import com.sgm.dms.fol.common.service.utils.MapUtil;
import com.sgm.dms.fol.common.service.utils.StringUtil;
import com.sgm.dms.fol.vo.BankTicketInterestVo;

/*
* 票据贴息查询Controller
* @author DELL
* TODO description
* @date 2016年1月18日
*/

@Controller
@RequestMapping("/bankTicket/bankTicketInterest")
public class BankTicketInterestController extends BaseController{
    // 日志
    protected Logger logger = LogManager.getLogger(this.getClass());
    
    @Autowired
    private BankTicketBusinessInquiriesService bankTicketBusinessInquiriesService;

    /** 
     * 票据贴息查询
     * @author DELL
     * TODO description
     * @date 2016年3月24日
     * @param bankTicketInterestVo
     * @return
     * @throws Throwable
     */
    @RequestMapping("/query")
    @ResponseBody
    public Object queryBankTicketInterest(@RequestBody BankTicketInterestVo bankTicketInterestVo) throws Throwable{
        //数据初始化
        BankTicketInterestDTO bankTicketInterestDTO=BeanMapper.map(bankTicketInterestVo, BankTicketInterestDTO.class);
        
        //查询票据贴息
        List<BankTicketInterestDeatilQueryDTO> results=bankTicketBusinessInquiriesService.findBankTicketInterestList(bankTicketInterestDTO);
        
        //批量转换成VO
        List<BankTicketInterestVo> volist=BeanMapper.mapList(results,BankTicketInterestVo.class);
        
        //转换成财务专用格式
        StringUtil.fontFormatFinance(volist);
        
        //设置前台需要的数据
        Map<String, Object> responsedata=MapUtil.setQueryDataToMap(volist,Long.valueOf(bankTicketBusinessInquiriesService.findBankTicketInterestCount(bankTicketInterestDTO)));
        
        return responsedata;
    }
}
