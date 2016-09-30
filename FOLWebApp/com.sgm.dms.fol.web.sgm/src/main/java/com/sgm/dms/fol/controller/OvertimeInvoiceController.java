
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : web.sgm
 *
 * @File name : OvertimeInvoiceController.java
 *
 * @Author : Lujinglei
 *
 * @Date : 2016年5月10日
 *
----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2016年5月10日    Lujinglei    1.0
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sgm.dms.fol.common.service.controller.impl.BaseController;
import com.sgm.dms.fol.common.service.utils.BeanMapper;
import com.sgm.dms.fol.common.service.utils.MapUtil;
import com.sgm.dms.fol.invoice.api.OvertimeInvoiceService;
import com.sgm.dms.fol.invoice.dto.InvoiceFollowDTO;
import com.sgm.dms.fol.vo.OvertimeInvoiceVo;

/**
 *
 * @author Lujinglei
 * description 经销商提交超时未处理发票查询
 * @date 2016年5月10日
 */
@Controller
@RequestMapping("/overtime/invoice")
public class OvertimeInvoiceController extends BaseController{

    protected Logger logger= LogManager.getLogger(this.getClass());
    @Autowired
    private OvertimeInvoiceService overtimeInvoiceService;
    
    @RequestMapping(value = "/query",method=RequestMethod.POST)
    @ResponseBody
    public Object query(@RequestBody OvertimeInvoiceVo overtimeInvoiceVo) throws Throwable{
        
        InvoiceFollowDTO dto = BeanMapper.map(overtimeInvoiceVo, InvoiceFollowDTO.class);
        //调用OvertimeInvoiceService
        List<InvoiceFollowDTO> invocielist = overtimeInvoiceService.getOvertimeUnDealInvoice(dto);
        //批量转换成VO
        List<OvertimeInvoiceVo> voList = BeanMapper.mapList(invocielist, OvertimeInvoiceVo.class);
        //设置前台需要的对象
        Map<String, Object> responsedata = MapUtil.setQueryDataToMap(voList, Long.valueOf(getQueryTotalSize(overtimeInvoiceVo)));
        
        return responsedata;
        
    }
    private int getQueryTotalSize(OvertimeInvoiceVo overtimeInvoiceVo) throws Exception {
        overtimeInvoiceVo.setBeginNo(null);
        overtimeInvoiceVo.setEndNo(null);
        List<InvoiceFollowDTO> reservedtos = findReserveAmountByDTO(overtimeInvoiceVo);
        return reservedtos.size();
    }
    private List<InvoiceFollowDTO> findReserveAmountByDTO(OvertimeInvoiceVo overtimeInvoiceVo)
            throws Exception {
        InvoiceFollowDTO searchDto = BeanMapper.map(overtimeInvoiceVo, InvoiceFollowDTO.class);
        List<InvoiceFollowDTO> data = overtimeInvoiceService.getOvertimeUnDealInvoice(searchDto);

        logger.info("=====查询完成======");
        return data;
    }
}
