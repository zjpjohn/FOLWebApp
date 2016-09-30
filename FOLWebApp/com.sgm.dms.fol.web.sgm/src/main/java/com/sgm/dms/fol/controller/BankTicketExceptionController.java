/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : web.sgm
*
* @File name : BankTicketExceptionController.java
*
* @Author : DELL
*
* @Date : 2016年3月29日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2016年3月29日    DELL    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/
	
package com.sgm.dms.fol.controller;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sgm.dms.fol.bankTicket.api.BankTicketService;
import com.sgm.dms.fol.bankTicket.dto.BankTicketExceptionInventoryDTO;
import com.sgm.dms.fol.common.service.controller.impl.BaseController;
import com.sgm.dms.fol.common.service.utils.AutoPojo;
import com.sgm.dms.fol.common.service.utils.BeanMapper;
import com.sgm.dms.fol.common.service.utils.DateUtil;
import com.sgm.dms.fol.common.service.utils.MapUtil;
import com.sgm.dms.fol.common.service.utils.StringUtil;
import com.sgm.dms.fol.dro.BankTicketExceptionInventoryDRO;
import com.sgm.dms.fol.reconciliation.api.ReconciliationManagementService;
import com.sgm.dms.fol.vo.BankTicketExceptionInventoryVo;

/*
*
* @author DELL
* TODO description
* @date 2016年3月29日
*/
@Controller
@RequestMapping("/bankticket/exception")
public class BankTicketExceptionController extends BaseController{
    
    @Autowired
    private BankTicketService bankTicketService;
    
    @Autowired
    private ReconciliationManagementService reconciliationManagementService;
    
    /** 
     * 银票异常清单
     * @author DELL
     * TODO description
     * @date 2016年3月29日
     * @return
     * @throws Throwable
     */
    @RequestMapping("/inventory")
    @ResponseBody
    public Object exceptionInventory(@RequestBody BankTicketExceptionInventoryVo bankTicketExceptionInventoryVo)throws Throwable{
        //数据初始化
        BankTicketExceptionInventoryDTO bankTicketExceptionInventoryDTO=BeanMapper.map(bankTicketExceptionInventoryVo, BankTicketExceptionInventoryDTO.class);
        
        //调用查询银票异常清单SERVICE
        List<BankTicketExceptionInventoryDTO> bankTicketExceptionInventoryDTOList=bankTicketService.findBankTicketExceptionInventoryList(bankTicketExceptionInventoryDTO);
        
        //批量转换成VO
        List<BankTicketExceptionInventoryVo> bankTicketExceptionInventoryVoList=BeanMapper.mapList(bankTicketExceptionInventoryDTOList, BankTicketExceptionInventoryVo.class);
        
        //设置成财务专用模式 
        StringUtil.fontFormatFinance(bankTicketExceptionInventoryVoList);
        
        //设置成前台需要的参数
        Map<String, Object> responsedata=MapUtil.setQueryDataToMap(bankTicketExceptionInventoryVoList, getExceptionInventoryCount(bankTicketExceptionInventoryDTO));
        
        return responsedata;
    }
    
    /** 
     * 获取异常清单总数
     * @author DELL
     * TODO description
     * @date 2016年3月29日
     * @return
     * @throws Exception
     */
    private long getExceptionInventoryCount(BankTicketExceptionInventoryDTO bankTicketExceptionInventoryDTO) throws Exception{
        bankTicketExceptionInventoryDTO.setBeginNo(null);
        bankTicketExceptionInventoryDTO.setEndNo(null);
        List<BankTicketExceptionInventoryDTO> bankTicketExceptionInventoryDTOList=bankTicketService.findBankTicketExceptionInventoryList(bankTicketExceptionInventoryDTO);
        return Long.valueOf(bankTicketExceptionInventoryDTOList.size());
    }
    
    /**
     * 银票异常清单查询结果导出
     * 
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/exportQueryResult")
    public ResponseEntity<byte[]> exportQueryResult(HttpServletRequest request) throws Throwable {
        // 数据初始化
        BankTicketExceptionInventoryVo bankTicketExceptionInventoryVo = AutoPojo.bindRequestParam(request,BankTicketExceptionInventoryVo.class);
        BankTicketExceptionInventoryDTO bankTicketExceptionInventoryDTO = BeanMapper.map(bankTicketExceptionInventoryVo, BankTicketExceptionInventoryDTO.class);
        
        // 设置导出文件header
        String[] header = { "序号","SAP客户代码", "客户名称", "银行", "出票日","银票号码","错误信息","公司代码", "金额"};
        // 查询数据
        List<BankTicketExceptionInventoryDTO> bankTicketExceptionInventoryDTOList=bankTicketService.findBankTicketExceptionInventoryList(bankTicketExceptionInventoryDTO);

        // 转换为要导出的数据
        List<BankTicketExceptionInventoryDRO> bankTicketExceptionInventoryDROList = BeanMapper.mapList(bankTicketExceptionInventoryDTOList,BankTicketExceptionInventoryDRO.class);
        
        // 转换成财务专用格式
        StringUtil.fontFormatFinance(bankTicketExceptionInventoryDROList);
        
        // 设置序号
        setLineNumber(bankTicketExceptionInventoryDROList);
        
        // 定义文件名称
        String fileName = "bankTicketException_" + DateUtil.date2str(new Date()) + ".xls";
        
        // 导出文件
        ByteArrayOutputStream bos = reconciliationManagementService.<BankTicketExceptionInventoryDRO> exportXls4BillFile(bankTicketExceptionInventoryDROList,
                                                   bankTicketExceptionInventoryVo.getToken(), header, fileName);

        return new ResponseEntity<byte[]>(bos.toByteArray(),
                getHeaders(fileName), HttpStatus.CREATED);
    }

    /**
     * 获取headers信息
     */
    private HttpHeaders getHeaders(String fileName) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", fileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return headers;
    }

    private void setLineNumber(List<BankTicketExceptionInventoryDRO> expList) {
        if (null != expList && expList.size() > 0) {
            for (int i = 0; i < expList.size(); i++) {
                BankTicketExceptionInventoryDRO vo = expList.get(i);
                vo.setNum(i + 1);
            }
        }
    }

}
