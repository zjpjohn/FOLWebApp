/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : web.sgm
*
* @File name : ExceptionHandingController.java
*
* @Author : DELL
*
* @Date : 2016年1月22日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2016年1月22日    DELL    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/
	
package com.sgm.dms.fol.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sgm.dms.fol.bonus.api.BonusExceptionHandingService;
import com.sgm.dms.fol.common.api.constants.CodeConstant;
import com.sgm.dms.fol.common.api.constants.StateConstant;
import com.sgm.dms.fol.common.api.domain.ExceptionHandingDTO;
import com.sgm.dms.fol.common.api.domain.ResponseDTO;
import com.sgm.dms.fol.common.service.utils.BeanMapper;
import com.sgm.dms.fol.common.service.utils.CookieUtil;
import com.sgm.dms.fol.reserve.api.ReserveExceptionHandingService;
import com.sgm.dms.fol.vo.ExceptionHandingVo;
import com.sgm.solution.framework.urlaccess.annotations.ValidationRequestUrl;


/*
* 异常处理Controller
* @date 2016年1月22日
*/
@Controller
@RequestMapping("/exceptionhanding")
public class ExceptionHandingController {

    // 日志
    protected Logger logger = LogManager.getLogger(this.getClass());
    
    @Autowired
    private ReserveExceptionHandingService reserveExceptionHandingService;
    
    @Autowired
    private BonusExceptionHandingService bonusExceptionHandingService;
    
    @RequestMapping("/amount")
    @ValidationRequestUrl
    @ResponseBody
    public Object amountHanding(@RequestBody ExceptionHandingVo exceptionHandingVo,HttpServletRequest request) throws Throwable{
        ResponseDTO responseDTO=new ResponseDTO();
        try {
            Boolean result=false;
            ExceptionHandingDTO exceptionHandingDTO=BeanMapper.map(exceptionHandingVo, ExceptionHandingDTO.class);
            if(CodeConstant.RESERVE_AMOUNT_EX_HANDING_ADD.equals(exceptionHandingDTO.getReferType()+"")||
                CodeConstant.RESERVE_AMOUNT_EX_HANDING_SUBTRACT.equals(exceptionHandingDTO.getReferType()+"")){
                result=reserveExceptionHandingService.reserveAmountExceptionHanding(exceptionHandingDTO, CookieUtil.getUserId(request));
            }else if(CodeConstant.BONUS_AMOUNT_EX_HANDING_ADD.equals(exceptionHandingDTO.getReferType()+"")||
                    CodeConstant.BONUS_AMOUNT_EX_HANDING_SUBTRACT.equals(exceptionHandingDTO.getReferType()+"")){
                result=bonusExceptionHandingService.bonusAmountExceptionHanding(exceptionHandingDTO, CookieUtil.getUserId(request));
            }else{
                throw(new Exception("数据出错"));
            }
           
        
            if(!result){                
                throw(new Exception("保存失败"));
            }
            
            responseDTO.setState(StateConstant.SUCCESS);
        } catch (Exception e) {
            logger.error(e);
            responseDTO.setState(StateConstant.DATABASE_ERROR);
            responseDTO.setMessage(e.getMessage());
        }
        return responseDTO;
    }
    
}
