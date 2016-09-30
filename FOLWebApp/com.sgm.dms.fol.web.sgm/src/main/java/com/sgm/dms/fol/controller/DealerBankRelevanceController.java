/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : web.sgm
*
* @File name : DealerBankRelevanceController.java
*
* @Author : DELL
*
* @Date : 2016年1月6日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2016年1月6日    DELL    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/
	
package com.sgm.dms.fol.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sgm.dms.fol.bankTicket.api.BankInfoService;
import com.sgm.dms.fol.bankTicket.api.DealerBankRelevanceService;
import com.sgm.dms.fol.bankTicket.dto.BankInfoDTO;
import com.sgm.dms.fol.bankTicket.dto.DealerBankRelevanceDTO;
import com.sgm.dms.fol.common.service.controller.impl.BaseController;
import com.sgm.dms.fol.common.service.utils.BeanMapper;
import com.sgm.dms.fol.common.service.utils.CookieUtil;
import com.sgm.dms.fol.common.service.utils.MapUtil;
import com.sgm.dms.fol.vo.DealerBankRelevanceVo;

/**
 * 银行特殊经销商关系controller
 * @author DELL
 * TODO description
 * @date 2016年1月6日
 */
@Controller
@RequestMapping("/bankTicket/dealerBankRelevance")
public class DealerBankRelevanceController extends BaseController{
    protected Logger logger = LogManager.getLogger(this.getClass());
    
    @Autowired
    private DealerBankRelevanceService dealerBankRelevanceService;
    
    @Autowired
    private BankInfoService bankInfoService;
    
    /**
     * 查询银行特殊经销商关系
     */
    @RequestMapping(value="/query",method=RequestMethod.POST)
    @ResponseBody
    public Object findDealerBankRelevanceList(@RequestBody DealerBankRelevanceVo dealerBankRelevanceVo) throws Throwable{
        
        //转换数据库查询需要的对象
        DealerBankRelevanceDTO dealerBankRelevanceDTO=BeanMapper.map(dealerBankRelevanceVo, DealerBankRelevanceDTO.class);
            
        //去数据库查询数据
        List<DealerBankRelevanceDTO> dtos=dealerBankRelevanceService.findDealerBankRelevanceByWhere(dealerBankRelevanceDTO);
           
        //设置前台需要的数据
        Map<String, Object> responsedata=MapUtil.setQueryDataToMap(BeanMapper.mapList(dtos, DealerBankRelevanceVo.class), Long.valueOf(dealerBankRelevanceService.findDealerBankRelevanceCountByWhere(dealerBankRelevanceDTO))); 

        return responsedata;
    }
    
    /**
     * 新增银行特殊经销商关系
     */
    @RequestMapping(value="/add",method=RequestMethod.POST)
    @ResponseBody
    public Object addDealerBankRelevance(@RequestBody DealerBankRelevanceVo dealerBankRelevanceVo,HttpServletRequest request) throws Throwable{
        //设置保存到数据库需要的对象
        DealerBankRelevanceDTO dealerBankRelevanceDTO=BeanMapper.map(dealerBankRelevanceVo, DealerBankRelevanceDTO.class);
           
        //调用保存的service
        dealerBankRelevanceService.addDealerBankRelevance(dealerBankRelevanceDTO,CookieUtil.getUserId(request));

        return true;
    }
    
    /**
     * 删除银行特殊经销商关系
     */
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    @ResponseBody
    public Object deleteDealerBankRelevance(@RequestBody DealerBankRelevanceVo dealerBankRelevanceVo,HttpServletRequest request) throws Throwable{
        //设置保存到数据库需要的对象
        DealerBankRelevanceDTO dealerBankRelevanceDTO=BeanMapper.map(dealerBankRelevanceVo, DealerBankRelevanceDTO.class);
        
        //调用删除的service
        dealerBankRelevanceService.deleteDealerBankRelevance(dealerBankRelevanceDTO,CookieUtil.getUserId(request));
         
        return true;
    }
    
    /**
     * 获取所有的银行信息
     */
    @RequestMapping(value="/getBankInfoAllList",method=RequestMethod.POST)
    @ResponseBody
    public Object getBankInfoAllList() throws Throwable{
        
        //查询所有的银行信息
        List<BankInfoDTO> bankinfolist=bankInfoService.getBankInfoAllList();  
        
        return bankinfolist;
    }
}
