/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : web.dealer
*
* @File name : BankTicketInterestController.java
*
* @Author : DELL
*
* @Date : 2016年1月26日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2016年1月26日    DELL    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/
	
package com.dealer.controller;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dealer.dro.BankTicketInterestDro;
import com.dealer.dro.BankTicketInterestFormDro;
import com.dealer.vo.BankTicketInterestVo;
import com.dealer.vo.BankTicketMonthInterestVo;
import com.sgm.dms.fol.bankTicket.api.BankTicketBusinessInquiriesService;
import com.sgm.dms.fol.bankTicket.api.BankTicketInterestMonthService;
import com.sgm.dms.fol.bankTicket.dto.BankTicketDraftsOverdueInterestDTO;
import com.sgm.dms.fol.bankTicket.dto.BankTicketInterestIssueAndConfirmDTO;
import com.sgm.dms.fol.bankTicket.dto.BankTicketInterestMonthDTO;
import com.sgm.dms.fol.common.service.controller.impl.BaseController;
import com.sgm.dms.fol.common.service.utils.AutoPojo;
import com.sgm.dms.fol.common.service.utils.BeanMapper;
import com.sgm.dms.fol.common.service.utils.CookieUtil;
import com.sgm.dms.fol.common.service.utils.DateUtil;
import com.sgm.dms.fol.common.service.utils.MapUtil;
import com.sgm.dms.fol.common.service.utils.RSAUtil;
import com.sgm.dms.fol.common.service.utils.StringUtil;
import com.sgm.dms.fol.reconciliation.api.ReconciliationManagementService;
import com.sgm.solution.framework.urlaccess.annotations.ValidationRequestUrl;

/*
*
* @author DELL
* TODO description
* @date 2016年1月26日
*/
@Controller
@RequestMapping("/bankTicket/businessInquiries")
public class BankTicketInterestInventoryController extends BaseController{
    // 日志
    protected Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    private BankTicketBusinessInquiriesService bankTicketBusinessInquiriesService;
    
    @Autowired
    private BankTicketInterestMonthService bankTicketInterestMonthService;
    
    @Autowired
	private ReconciliationManagementService reconciliationManagementService;
    
    @RequestMapping("/query")
    @ResponseBody
    @ValidationRequestUrl
    public Object queryBankTicketInterest(@RequestBody BankTicketInterestVo bankTicketInterestVo,HttpServletRequest request,HttpServletResponse response) throws Exception{
		BankTicketInterestMonthDTO bankTicketInterestDTO = BeanMapper.map(bankTicketInterestVo,
				BankTicketInterestMonthDTO.class);
		bankTicketInterestDTO.setSapCode(RSAUtil.decryptByPrivateKey(CookieUtil.getSapCode(request)));
		List<BankTicketDraftsOverdueInterestDTO> results = bankTicketInterestMonthService
				.findBankTicketInterestMonthByWhere(bankTicketInterestDTO);

		List<BankTicketInterestDro> data = BeanMapper.mapList(results, BankTicketInterestDro.class);

		// 响应
        return MapUtil.setQueryDataToMap(StringUtil.fontFormatFinance(data), Long.parseLong(bankTicketInterestMonthService.findBankTicketInterestMonthTotalNumWhere(bankTicketInterestDTO)+""));
    }
    
    @RequestMapping("/confirm")
    @ResponseBody
    @ValidationRequestUrl
    public Boolean confirmBankTicket(@RequestBody BankTicketMonthInterestVo bankTicketMonthInterestVo,HttpServletRequest request,HttpServletResponse response) throws Exception{
		BankTicketInterestIssueAndConfirmDTO bankTicketInterestIssueAndConfirmDTO = BeanMapper
				.map(bankTicketMonthInterestVo, BankTicketInterestIssueAndConfirmDTO.class);
		boolean result = bankTicketBusinessInquiriesService
				.bankTicketInterestIssueConfirm(bankTicketInterestIssueAndConfirmDTO);

		if (!result) {
			throw (new Exception("发布失败"));
		}
		return true;
    }
    
    /**
     * 
     * 导出票据贴息开票清单
     *
     * @author wangfl
     * @version 
     * @param request
     * @return
     * @throws Exception
     */
	@RequestMapping("/exportInterestAmount")
	@ValidationRequestUrl
	public ResponseEntity<byte[]> exportInterestAmount(HttpServletRequest request,HttpServletResponse response) throws Exception{
		BankTicketInterestVo vo = AutoPojo.bindRequestParam(request, BankTicketInterestVo.class);
		BankTicketInterestMonthDTO bankTicketInterestDTO = BeanMapper.map(vo, BankTicketInterestMonthDTO.class);
		// 设置标题
		String[] header = { "序号", "SAP客户代码 ", "客户名称", "贴息（含税价）", "金额（不含税价）","增值税 ","确认状态 ", "发票号码 ", "EMS快递单号 " };
		
		bankTicketInterestDTO.setSapCode(RSAUtil.decryptByPrivateKey(CookieUtil.getSapCode(request)));
		// 查询数据
		List<BankTicketDraftsOverdueInterestDTO> data = bankTicketInterestMonthService.findBankTicketInterestMonthByWhere(bankTicketInterestDTO);
		
		List<BankTicketInterestFormDro> expList = BeanMapper.mapList(data,BankTicketInterestFormDro.class);
		StringUtil.fontFormatFinance(expList);
		
		// 设置序号
		if (null != expList && expList.size() > 0) {
			for (int i = 0; i < expList.size(); i++) {
				BankTicketInterestFormDro formDro = expList.get(i);
				formDro.setNum(i + 1);
			}
		}
		
		// 定义文件名称
		String fileName = "BankTicketInterest_monthForm_" + DateUtil.date2str(new Date())+ ".xls";
				
		// 导出
		ByteArrayOutputStream bos = reconciliationManagementService.<BankTicketInterestFormDro> exportXls4BillFiles(expList,vo.getToken(), header, fileName);
		return new ResponseEntity<byte[]>(bos.toByteArray(),getHeaders(fileName), HttpStatus.CREATED);
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
}
