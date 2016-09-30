/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : web.sgm
*
* @File name : findBankTicketCollectController.java
*
* @Author : DELL
*
* @Date : 2016年1月19日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2016年1月19日    DELL    1.0
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

import com.sgm.dms.fol.bankTicket.api.BankTicketService;
import com.sgm.dms.fol.bankTicket.dto.BankTicketCollectDTO;
import com.sgm.dms.fol.common.service.controller.impl.BaseController;
import com.sgm.dms.fol.common.service.utils.BeanMapper;
import com.sgm.dms.fol.common.service.utils.MapUtil;
import com.sgm.dms.fol.common.service.utils.StringUtil;
import com.sgm.dms.fol.vo.BankTicketCollectVo;
import com.sgm.dms.fol.vo.BankTicketDeatilVo;

/**
 * 银票汇总查询Controller
 * @author DELL
 * TODO description
 * @date 2016年1月19日
 */
@Controller
@RequestMapping("/bankTicket/collect")
public class BankTicketCollectController extends BaseController{
    // 日志
    protected Logger logger = LogManager.getLogger(this.getClass());
    
    @Autowired
    private BankTicketService BankTicketService;
    
    @RequestMapping("/query")
    @ResponseBody
    public Object findBankTicketCollect(@RequestBody BankTicketDeatilVo bankTicketDeatilVo) throws Throwable{

        //转换查询需要的数据
        BankTicketCollectDTO bankTicketCollectDTO=BeanMapper.map(bankTicketDeatilVo, BankTicketCollectDTO.class);
        
        //数据库查询银票汇总记录
        List<BankTicketCollectDTO> dtos=BankTicketService.findBankTicketCollectDataList(bankTicketCollectDTO);
        
        //转换财务专用格式
        List<BankTicketCollectVo> vos=StringUtil.fontFormatFinance(BeanMapper.mapList(dtos, BankTicketCollectVo.class));

        //转换前台需要的格式
        Map<String, Object> responsedata=MapUtil.setQueryDataToMap(vos, Long.valueOf(dtos.size()));
            
        return responsedata;
    }
}
