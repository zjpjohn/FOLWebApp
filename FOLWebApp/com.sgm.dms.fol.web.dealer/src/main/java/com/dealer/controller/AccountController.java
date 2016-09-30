/*
 * Copyright 2015 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : web.sgm
 *
 * @File name : AccountController.java
 *
 * @Author : shenweiwei
 *
 * @Date : 2015年10月23日
 *
----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2015年10月23日    shenweiwei    1.0
 *
 *
 *
 *
----------------------------------------------------------------------------------
 */

package com.dealer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dealer.vo.DataReportVo;
import com.sgm.dms.fol.common.service.controller.impl.BaseController;
import com.sgm.dms.fol.common.service.utils.BeanMapper;
import com.sgm.dms.fol.common.service.utils.CookieUtil;
import com.sgm.dms.fol.common.service.utils.StringUtil;
import com.sgm.dms.fol.reserve.api.ReserveService;
import com.sgm.dms.fol.reserve.dto.DataReportDTO;
import com.sgm.dms.fol.reserve.dto.ReserveDTO;
import com.sgm.solution.framework.urlaccess.annotations.ValidationRequestUrl;

/*
 * 我的账户
 * @author shenweiwei
 * TODO description
 * @date 2015年10月23日
 */
@Controller
@RequestMapping("/datareport/account")
public class AccountController extends BaseController {
	// 日志
	protected Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private ReserveService reserveService;

	@RequestMapping(value = "/query")
	@ValidationRequestUrl
	@ResponseBody
	public Object query(HttpServletRequest request,HttpServletResponse response)
			throws Exception {
		return findAccountInfo(CookieUtil.getSapCode(request));
	}

	/**
	 * 到数据库查询账户信息
	 * @throws Exception 
	 */
	private Object findAccountInfo(String sapCode) throws Exception {
		ReserveDTO reserveDTO = new ReserveDTO();
		reserveDTO.setSapCode(sapCode);

		DataReportDTO dataReportDTO = reserveService.findReserveRecordWithDealer(reserveDTO);
		DataReportVo vo = BeanMapper.map(dataReportDTO, DataReportVo.class);

		StringUtil.fontFormatFinance(vo);
		
		return vo;
	}

}
