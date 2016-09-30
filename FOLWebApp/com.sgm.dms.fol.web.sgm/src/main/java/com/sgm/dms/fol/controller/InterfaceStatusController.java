
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : web.sgm
 *
 * @File name : InterfaceStatusController.java
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
import com.sgm.dms.fol.invoice.api.InterfaceStatusService;
import com.sgm.dms.fol.invoice.dto.SapReturnItemsDTO;
import com.sgm.dms.fol.vo.InterfaceStatusVo;

/**
 *
 * @author Lujinglei
 * description FOL-SAP接口状态查询
 * @date 2016年5月10日
 */
@Controller
@RequestMapping("/interface/status")
public class InterfaceStatusController extends BaseController{
    protected Logger logger= LogManager.getLogger(this.getClass());
    @Autowired
    private InterfaceStatusService interfaceStatusService;
    
    @RequestMapping(value = "/query",method=RequestMethod.POST)
    @ResponseBody
    public Object query(@RequestBody InterfaceStatusVo interfaceStatusVo) throws Throwable{
        
        SapReturnItemsDTO dto = BeanMapper.map(interfaceStatusVo, SapReturnItemsDTO.class);
        //调用InterfaceStatusService
        List<SapReturnItemsDTO> invocielist = interfaceStatusService.getFOLtoSAPInterfaceStatus(dto);
        //批量转换成VO
        List<InterfaceStatusVo> voList = BeanMapper.mapList(invocielist, InterfaceStatusVo.class);
        //设置前台需要的对象
        Map<String, Object> responsedata = MapUtil.setQueryDataToMap(voList, Long.valueOf(getQueryTotalSize(interfaceStatusVo)));
        
        return responsedata;
        
    }
    private int getQueryTotalSize(InterfaceStatusVo interfaceStatusVo) throws Exception {
        interfaceStatusVo.setBeginNo(null);
        interfaceStatusVo.setEndNo(null);
        List<SapReturnItemsDTO> reservedtos = findReserveAmountByDTO(interfaceStatusVo);
        return reservedtos.size();
    }
    private List<SapReturnItemsDTO> findReserveAmountByDTO(InterfaceStatusVo interfaceStatusVo)
            throws Exception {
        SapReturnItemsDTO searchDto = BeanMapper.map(interfaceStatusVo, SapReturnItemsDTO.class);
        List<SapReturnItemsDTO> data = interfaceStatusService.getFOLtoSAPInterfaceStatus(searchDto);

        logger.info("=====查询完成======");
        return data;
    }
}
