/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : web.sgm
*
* @File name : BankTicketDeadLineController.java
*
* @Author : DELL
*
* @Date : 2016年1月11日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2016年1月11日    DELL    1.0
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.sgm.dms.fol.bankTicket.api.BankTicketDeadLineService;
import com.sgm.dms.fol.bankTicket.dto.BankTicketDeadLineDTO;
import com.sgm.dms.fol.common.service.controller.impl.BaseController;
import com.sgm.dms.fol.common.service.utils.BeanMapper;
import com.sgm.dms.fol.common.service.utils.CookieUtil;
import com.sgm.dms.fol.common.service.utils.MapUtil;
import com.sgm.dms.fol.vo.BankTicketDeadLineVo;

/*
* 银票期限维护Controller
* @author DELL
* TODO description
* @date 2016年1月11日
*/
@Controller
@RequestMapping("/bankTicket/deadLine")
public class BankTicketDeadLineController extends BaseController{
    protected Logger logger = LogManager.getLogger(this.getClass());
    
    @Autowired
    private BankTicketDeadLineService bankTicketDeadLineService;

    /** 
     * 新增品牌期限
     * @author DELL
     * TODO description
     * @date 2016年3月23日
     * @param bankTicketDeadLineVo
     * @param request
     * @return
     * @throws Throwable
     */
    @RequestMapping("/addBrand")
    @ResponseBody
    public Object addBankTicketDeadLine(@RequestBody BankTicketDeadLineVo bankTicketDeadLineVo,HttpServletRequest request) throws Throwable{
        //转换DTO
        BankTicketDeadLineDTO bankTicketDeadLineDTO=BeanMapper.map(bankTicketDeadLineVo, BankTicketDeadLineDTO.class);
        
        //新增品牌期限
        bankTicketDeadLineService.addBankTicketDeadLineWithBrand(bankTicketDeadLineDTO, CookieUtil.getUserId(request));

        return true;
    }
    
    /** 
     * 新增特殊经销商期限
     * @author DELL
     * TODO description
     * @date 2016年3月23日
     * @param bankTicketDeadLineVo
     * @param request
     * @return
     * @throws Throwable
     */
    @RequestMapping("/addSpecialDealer")
    @ResponseBody
    public Object addBankTicketDeadLineWithSpecialDealer(@RequestBody BankTicketDeadLineVo bankTicketDeadLineVo,HttpServletRequest request) throws Throwable{
        //转换DTO
        BankTicketDeadLineDTO bankTicketDeadLineDTO=BeanMapper.map(bankTicketDeadLineVo, BankTicketDeadLineDTO.class);

        //新增特殊经销商期限
        bankTicketDeadLineService.addBankTicketDeadLineWithSpecialDealer(bankTicketDeadLineDTO, CookieUtil.getUserId(request));
          
        return true;
    }
    
    /** 
     * 删除银票期限
     * @author DELL
     * TODO description
     * @date 2016年3月23日
     * @param bankTicketDeadLineVo
     * @param request
     * @return
     * @throws Throwable
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Object deleteBankTicketDeadLine(@RequestBody BankTicketDeadLineVo bankTicketDeadLineVo,HttpServletRequest request) throws Throwable{   
        //转换DTO
        BankTicketDeadLineDTO bankTicketDeadLineDTO=BeanMapper.map(bankTicketDeadLineVo, BankTicketDeadLineDTO.class);
        
        //删除特殊经销商期限
        bankTicketDeadLineService.deleteBankTicketDeadLine(bankTicketDeadLineDTO, CookieUtil.getUserId(request));

        return true;
    }
    
    /** 
     * 查询品牌期限
     * @author DELL
     * TODO description
     * @date 2016年3月23日
     * @param bankTicketDeadLineVo
     * @return
     * @throws Throwable
     */
    @RequestMapping("/queryBrand")
    @ResponseBody
    public Object queryBankTicketDeadLineWithBrand(@RequestBody BankTicketDeadLineVo bankTicketDeadLineVo) throws Throwable{
        //转换DTO
        BankTicketDeadLineDTO bankTicketDeadLineDTO=BeanMapper.map(bankTicketDeadLineVo, BankTicketDeadLineDTO.class);
           
        //查询品牌期限
        List<BankTicketDeadLineDTO> deadLineList=bankTicketDeadLineService.findBankTicketDeadLineWithBrand(bankTicketDeadLineDTO);
            
        //批量转换成VO
        List<BankTicketDeadLineVo> bankTicketDeadLineVoList=BeanMapper.mapList(deadLineList, BankTicketDeadLineVo.class);
        
        //设置成前台需要的参数
        Map<String, Object> responsedata=MapUtil.setQueryDataToMap(bankTicketDeadLineVoList, Long.valueOf(bankTicketDeadLineService.findBankTicketDeadLineCountWithBrand(bankTicketDeadLineDTO)));
        
        return responsedata;
    }
    
    /** 
     * 查询特殊经销商期限
     * @author DELL
     * TODO description
     * @date 2016年3月23日
     * @param bankTicketDeadLineVo
     * @return
     * @throws Throwable
     */
    @RequestMapping("/querySpecialDealer")
    @ResponseBody
    public Object queryBankTicketDeadLineWithSpecialDealer(@RequestBody BankTicketDeadLineVo bankTicketDeadLineVo) throws Throwable{
        //转换DTO
        BankTicketDeadLineDTO bankTicketDeadLineDTO=BeanMapper.map(bankTicketDeadLineVo, BankTicketDeadLineDTO.class);
        //查询特殊经销商品牌期限
        List<BankTicketDeadLineDTO> deadLineList=bankTicketDeadLineService.findBankTicketDeadLineWithSpecialDealer(bankTicketDeadLineDTO);
        //批量转换成VO
        List<BankTicketDeadLineVo> bankTicketDeadLineVoList= BeanMapper.mapList(deadLineList, BankTicketDeadLineVo.class);
        //设置成前台需要的参数
        Map<String, Object> responsedata=MapUtil.setQueryDataToMap(bankTicketDeadLineVoList, Long.valueOf(bankTicketDeadLineService.findBankTicketDeadLineCountWithSpecialDealer(bankTicketDeadLineDTO)));
           
        return responsedata;
    }
    
    /** 
     * 修改银票期限
     * @author DELL
     * TODO description
     * @date 2016年3月23日
     * @param bankTicketDeadLineVo
     * @param request
     * @return
     * @throws Throwable
     */
    @RequestMapping("/updateDeadLine")
    @ResponseBody
    public Object updateDeadLine(@RequestBody BankTicketDeadLineVo bankTicketDeadLineVo,HttpServletRequest request) throws Throwable{
        //转换成DTO
        BankTicketDeadLineDTO bankTicketDeadLineDTO=BeanMapper.map(bankTicketDeadLineVo, BankTicketDeadLineDTO.class);

        //调用修改银票期限SERVICE
        bankTicketDeadLineService.updateBankTicketDeadLine(bankTicketDeadLineDTO, CookieUtil.getUserId(request));
  
        return true;
    }
    
}
