/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : web.sgm
*
* @File name : BankTicketLimitAmountController.java
*
* @Author : DELL
*
* @Date : 2016年1月14日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2016年1月14日    DELL    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/
	
package com.sgm.dms.fol.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sgm.dms.fol.bankTicket.api.BankTicketLimitAmountService;
import com.sgm.dms.fol.bankTicket.dto.BankTicketLimitAmountDTO;
import com.sgm.dms.fol.common.api.constants.POConstant;
import com.sgm.dms.fol.common.api.constants.TypeConstant;
import com.sgm.dms.fol.common.service.utils.BeanMapper;
import com.sgm.dms.fol.common.service.utils.CookieUtil;
import com.sgm.dms.fol.common.service.utils.MapUtil;
import com.sgm.dms.fol.common.service.utils.StringUtil;
import com.sgm.dms.fol.vo.BankTicketLimitAmountVo;

/*
* 银票限额controller
* @author DELL
* TODO description
* @date 2016年1月14日
*/
@Controller
@RequestMapping("/bankTicket/limitAmount")
public class BankTicketLimitAmountController {
    
    protected Logger logger = LogManager.getLogger(this.getClass());
    
    @Autowired
    private BankTicketLimitAmountService bankTicketLimitAmountService;

    /** 
     * 银票限额查询
     * @author DELL
     * TODO description
     * @date 2016年3月23日
     * @param bankTicketLimitAmountVo
     * @return
     * @throws Throwable
     */
    @RequestMapping("/query")
    @ResponseBody
    public Object findBankTicketLimitAmount(@RequestBody BankTicketLimitAmountVo bankTicketLimitAmountVo) throws Throwable{
        //数据初始化
        BankTicketLimitAmountDTO bankTicketLimitAmountDTO=initBankTicketLimitAmountDTOWithQuery(bankTicketLimitAmountVo);
            
        //数据库查询银票信息
        List<BankTicketLimitAmountDTO> dtolist=bankTicketLimitAmountService.findBankTicketLimitAmountByWhere(bankTicketLimitAmountDTO);
        
        //批量转换成VO
        List<BankTicketLimitAmountVo> bankTicketLimitAmountVoList=BeanMapper.mapList(dtolist, BankTicketLimitAmountVo.class);
        
        //设置财务专用格式
        StringUtil.fontFormatFinance(bankTicketLimitAmountVoList);
        Map<String, Object> responsedata=MapUtil.setQueryDataToMap(bankTicketLimitAmountVoList,Long.valueOf(bankTicketLimitAmountService.findBankTicketLimitAmountCountByWhere(bankTicketLimitAmountDTO)));
        return responsedata;
    }
    
    /** 
     * 数据初始化
     * @author DELL
     * TODO description
     * @date 2016年3月23日
     * @param bankTicketLimitAmountVo
     * @return
     * @throws Exception
     */
    private BankTicketLimitAmountDTO initBankTicketLimitAmountDTOWithQuery(BankTicketLimitAmountVo bankTicketLimitAmountVo) throws Exception{
        BankTicketLimitAmountDTO bankTicketLimitAmountDTO=BeanMapper.map(bankTicketLimitAmountVo, BankTicketLimitAmountDTO.class);
        bankTicketLimitAmountDTO.setType(TypeConstant.BANK_TICKET_LIMIT_AMOUNT_TYPE);
        
        return bankTicketLimitAmountDTO;
    }
    
    /** 
     * 新增银票限额
     * @author DELL
     * TODO description
     * @date 2016年3月23日
     * @param bankTicketLimitAmountVo
     * @param request
     * @return
     * @throws Throwable
     */
    @RequestMapping("/add")
    @ResponseBody
    public Object addBankTicketLimitAmount(@RequestBody BankTicketLimitAmountVo bankTicketLimitAmountVo,HttpServletRequest request) throws Throwable{

        //数据初始化
        BankTicketLimitAmountDTO bankTicketLimitAmountDTO=initBankTicketLimitAmountDTOWithAdd(bankTicketLimitAmountVo);
        
        //调用新增银票限额的SERVICE
        bankTicketLimitAmountService.addBankTicketLimitAmount(bankTicketLimitAmountDTO, CookieUtil.getUserId(request));
    
        return true;
    }
    
    /** 
     * 数据初始化
     * @author DELL
     * TODO description
     * @date 2016年3月23日
     * @param bankTicketLimitAmountVo
     * @return
     * @throws Exception
     */
    private BankTicketLimitAmountDTO initBankTicketLimitAmountDTOWithAdd(BankTicketLimitAmountVo bankTicketLimitAmountVo) throws Exception{
        BankTicketLimitAmountDTO bankTicketLimitAmountDTO=BeanMapper.map(bankTicketLimitAmountVo, BankTicketLimitAmountDTO.class);
        bankTicketLimitAmountDTO.setType(TypeConstant.BANK_TICKET_LIMIT_AMOUNT_TYPE);
        return bankTicketLimitAmountDTO;
    }
    
    /** 
     * 查询特殊经销商的银票限额
     * @author DELL
     * TODO description
     * @date 2016年3月23日
     * @param bankTicketLimitAmountVo
     * @return
     * @throws Throwable
     */
    @RequestMapping("/querySpecialDealer")
    @ResponseBody
    public Object findBankTicketLimitAmountWithSpecialDealer(@RequestBody BankTicketLimitAmountVo bankTicketLimitAmountVo) throws Throwable{
        //数据初始化
        BankTicketLimitAmountDTO bankTicketLimitAmountDTO=initBankTicketLimitAmountDTOWithQuerySpecialDealer(bankTicketLimitAmountVo);
        
        //查询特殊经销商的银票限额数据
        List<BankTicketLimitAmountDTO> dtolist=bankTicketLimitAmountService.findBankTicketLimitAmountByWhere(bankTicketLimitAmountDTO);
        
        //批量转换成VO
        List<BankTicketLimitAmountVo> bankTicketLimitAmountVoList=BeanMapper.mapList(dtolist, BankTicketLimitAmountVo.class);
        
        //转换成财务专用格式
        StringUtil.fontFormatFinance(bankTicketLimitAmountVoList);
        
        //设置成前台专用数据
        Map<String, Object> responsedata=MapUtil.setQueryDataToMap(bankTicketLimitAmountVoList, Long.valueOf(bankTicketLimitAmountService.findBankTicketLimitAmountCountByWhere(bankTicketLimitAmountDTO)));
            
        return responsedata;
    }
    
    /** 
     * 数据初始化
     * @author DELL
     * TODO description
     * @date 2016年3月23日
     * @param bankTicketLimitAmountVo
     * @return
     * @throws Exception
     */
    private BankTicketLimitAmountDTO initBankTicketLimitAmountDTOWithQuerySpecialDealer(BankTicketLimitAmountVo bankTicketLimitAmountVo) throws Exception{
        BankTicketLimitAmountDTO bankTicketLimitAmountDTO=BeanMapper.map(bankTicketLimitAmountVo, BankTicketLimitAmountDTO.class);
        bankTicketLimitAmountDTO.setType(TypeConstant.SPECIAL_DEALER_BANK_TICKET_LIMIT_AMOUNT_TYPE);
        
        return bankTicketLimitAmountDTO;
    }
    
    /** 
     * 新增特殊经销商的银票限额
     * @author DELL
     * TODO description
     * @date 2016年3月23日
     * @param bankTicketLimitAmountVo
     * @param request
     * @return
     * @throws Throwable
     */
    @RequestMapping("/addSpecialDealer")
    @ResponseBody
    public Object addBankTicketLimitAmountWithSpecialDealer(@RequestBody BankTicketLimitAmountVo bankTicketLimitAmountVo,HttpServletRequest request) throws Throwable{

        //数据初始化
        BankTicketLimitAmountDTO bankTicketLimitAmountDTO=initBankTicketLimitAmountDTOWithAddSpecialDealer(bankTicketLimitAmountVo);
        
        //调用新增银票限额SERVICE    
        bankTicketLimitAmountService.addBankTicketLimitAmount(bankTicketLimitAmountDTO, CookieUtil.getUserId(request));

        return true;
    }
    
    /** 
     * 数据初始化
     * @author DELL
     * TODO description
     * @date 2016年3月23日
     * @param bankTicketLimitAmountVo
     * @return
     * @throws Exception
     */
    private BankTicketLimitAmountDTO initBankTicketLimitAmountDTOWithAddSpecialDealer(BankTicketLimitAmountVo bankTicketLimitAmountVo) throws Exception{
        BankTicketLimitAmountDTO bankTicketLimitAmountDTO=BeanMapper.map(bankTicketLimitAmountVo, BankTicketLimitAmountDTO.class);
        bankTicketLimitAmountDTO.setType(TypeConstant.SPECIAL_DEALER_BANK_TICKET_LIMIT_AMOUNT_TYPE);
        return bankTicketLimitAmountDTO;
    }
    
    /** 
     * 删除银票限额
     * @author DELL
     * TODO description
     * @date 2016年3月23日
     * @param bankTicketLimitAmountVo
     * @param request
     * @return
     * @throws Throwable
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Object deleteBankTicketLimitAmount(@RequestBody BankTicketLimitAmountVo bankTicketLimitAmountVo,HttpServletRequest request) throws Throwable{
        //初始化数据
        BankTicketLimitAmountDTO bankTicketLimitAmountDTO=initBankTicketLimitAmountWithDelete(bankTicketLimitAmountVo);
        
        //调用删除银票限额Service
        bankTicketLimitAmountService.deleteBankTicketLimitAmountById(bankTicketLimitAmountDTO, CookieUtil.getUserId(request));
  
        return true;
    }
    
    /** 
     * 初始化数据
     * @author DELL
     * TODO description
     * @date 2016年3月23日
     * @param bankTicketLimitAmountVo
     * @return
     * @throws Exception
     */
    private BankTicketLimitAmountDTO initBankTicketLimitAmountWithDelete(BankTicketLimitAmountVo bankTicketLimitAmountVo) throws Exception{
        bankTicketLimitAmountVo.setAmountLimit(bankTicketLimitAmountVo.getAmountLimit().replaceAll(",", ""));
        BankTicketLimitAmountDTO bankTicketLimitAmountDTO=BeanMapper.map(bankTicketLimitAmountVo, BankTicketLimitAmountDTO.class);
        bankTicketLimitAmountDTO.setAmountLimit(bankTicketLimitAmountDTO.getAmountLimit().divide(BigDecimal.valueOf(POConstant.BANK_TICKET_AMOUNT_BASE_UNIT)));
        return bankTicketLimitAmountDTO;
    }
    
    
    @RequestMapping("/update")
    @ResponseBody
    public Object updateBankTicketLimitAmount(@RequestBody BankTicketLimitAmountVo bankTicketLimitAmountVo,HttpServletRequest request) throws Throwable{

        //转换DTO
        BankTicketLimitAmountDTO bankTicketLimitAmountDTO=BeanMapper.map(bankTicketLimitAmountVo, BankTicketLimitAmountDTO.class);
            
        //调用更新银票限额Service
        bankTicketLimitAmountService.updateBankTicketLimitAmountById(bankTicketLimitAmountDTO, CookieUtil.getUserId(request));
            
        return true;
    }
}
