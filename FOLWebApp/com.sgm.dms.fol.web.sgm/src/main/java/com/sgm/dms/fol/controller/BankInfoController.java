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
	
package com.sgm.dms.fol.controller;

import java.util.List;
import java.util.Map;

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

/**
 * 银行信息Controller
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
    
    /**
     * 查询所有的银行信息
     */
    @RequestMapping("/findAll")
    @ResponseBody
    public Object findBankInfoAll() throws Throwable{
        //数据库查询所有银行信息
        List<BankInfoDTO> dtos=bankInfoService.getBankInfoAllList();
        
        //设置前台需要的数据
        Map<String, Object> responsedata=MapUtil.setQueryDataToMap(dtos, Long.valueOf(dtos.size()));
           
        return responsedata;
    }
    
}
