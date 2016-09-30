/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : web.sgm
*
* @File name : BankTicketDeatilController.java
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
import com.sgm.dms.fol.bankTicket.dto.QueryBankTicketInfoDTO;
import com.sgm.dms.fol.common.service.controller.impl.BaseController;
import com.sgm.dms.fol.common.service.utils.BeanMapper;
import com.sgm.dms.fol.common.service.utils.MapUtil;
import com.sgm.dms.fol.common.service.utils.StringUtil;
import com.sgm.dms.fol.vo.BankTicketDeatilVo;
import com.sgm.dms.fol.vo.QueryBankTicketInfoVo;

/**
* 银票明细查询Controller
* @author DELL
* TODO description
* @date 2016年1月19日
*/
@Controller
@RequestMapping("/bankTicket/deatil")
public class BankTicketDeatilController extends BaseController{
    protected Logger logger = LogManager.getLogger(this.getClass());
    
    @Autowired
    private BankTicketService bankTicketService;

    /** 
     * 银票明细查询
     * @author DELL
     * TODO description
     * @date 2016年3月24日
     * @param bankTicketDeatilVo
     * @return
     * @throws Throwable
     */
    @RequestMapping("/query")
    @ResponseBody
    public Object queryBankTicketDeatil(@RequestBody BankTicketDeatilVo bankTicketDeatilVo) throws Throwable{       
        //数据初始化
        QueryBankTicketInfoDTO bankTicketInfoDTO=BeanMapper.map(bankTicketDeatilVo, QueryBankTicketInfoDTO.class);
        
        //查询银票明细
        List<QueryBankTicketInfoDTO> datas=bankTicketService.findBankTicketInfoByWhere(bankTicketInfoDTO);
        
        //批量转换成VO
        List<QueryBankTicketInfoVo> queryBankTicketInfoVoList=BeanMapper.mapList(datas, QueryBankTicketInfoVo.class);
        
        //转换成财务专用格式
        StringUtil.fontFormatFinance(queryBankTicketInfoVoList);
        
        //设置前台需要的数据
        Map<String, Object> responsedata=MapUtil.setQueryDataToMap(queryBankTicketInfoVoList,Long.valueOf(bankTicketService.findBankTicketInfoCountByWhere(bankTicketInfoDTO)));
        
        return responsedata;
    }
}
