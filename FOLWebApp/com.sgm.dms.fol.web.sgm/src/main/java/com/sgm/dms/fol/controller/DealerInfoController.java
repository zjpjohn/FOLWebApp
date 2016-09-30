/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : web.sgm
*
* @File name : DealerInfoController.java
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

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sgm.dms.fol.common.api.domain.DealerInfoDTO;
import com.sgm.dms.fol.common.api.services.DealerService;
import com.sgm.dms.fol.common.service.controller.impl.BaseController;
import com.sgm.dms.fol.common.service.utils.BeanMapper;
import com.sgm.dms.fol.common.service.utils.MapUtil;
import com.sgm.dms.fol.common.service.utils.StringUtil;
import com.sgm.dms.fol.reserve.api.ReserveService;
import com.sgm.dms.fol.reserve.dto.DataReportDTO;
import com.sgm.dms.fol.vo.DataReportVo;
import com.sgm.dms.fol.vo.DealerInfoVo;
import com.sgm.dms.fol.vo.PageListRespVo;

/*
*
* 经销商Controller
* @date 2016年1月22日
*/

@RestController
@RequestMapping("/dealerInfo")
public class DealerInfoController extends BaseController{
    
    @Autowired
    private ReserveService reserveService;
    
    @Autowired
    private DealerService dealerService;
    
    /**
     * 
     * 查询
     * @param dealerInfoVo
     * @return
     * @throws Throwable
     */
    @RequestMapping(value="/query",method=RequestMethod.POST)
    public Object getDealerInfo(@RequestBody DealerInfoVo dealerInfoVo) throws Throwable{
        //转换数据库需要查询的数据
        DealerInfoDTO dealerInfoDTO=BeanMapper.map(dealerInfoVo, DealerInfoDTO.class);
        
        //去数据库查询
        List<DataReportDTO> datareportdtos=reserveService.getReserveAndBonusInfoList(dealerInfoDTO);

        //转换数据
        List<DataReportVo> dataReportVoList=convertData(datareportdtos);
        
        //设置成前台需要的格式的参数
        Map<String, Object> responsedata=MapUtil.setQueryDataToMap(dataReportVoList, Long.valueOf(reserveService.getReserveAndBonusInfoCount(dealerInfoDTO)));

        return responsedata;
    }
    
    
    @RequestMapping(value = "/dealerInfoPageList", method = RequestMethod.POST, consumes = "application/json", produces = "application/json;charset=UTF-8")
    public PageListRespVo<DealerInfoVo> dealerInfoPageList(@RequestBody DealerInfoVo dealerInfoVo){
    	//Vo转Dto
        DealerInfoDTO dealerInfoDTO=BeanMapper.map(dealerInfoVo, DealerInfoDTO.class);
        
        //数据库查询service
        List<DealerInfoDTO> voList = dealerService.dealerInfoPageList(dealerInfoDTO);
    	
    	return new PageListRespVo<DealerInfoVo>(BeanMapper.mapList(voList, DealerInfoVo.class), dealerService.getDealerInfoListNum(dealerInfoDTO));
    }
    
    
    
    /**
     * 转换数据
     */
    private List<DataReportVo> convertData(List<DataReportDTO> datareportdtos) throws Exception{
      //批量转换成Vo
      List<DataReportVo> dataReportVoList = BeanMapper.mapList(datareportdtos, DataReportVo.class);

      //批量设置成财务专用格式
      StringUtil.fontFormatFinance(dataReportVoList);
      
      return dataReportVoList;
    }
}
