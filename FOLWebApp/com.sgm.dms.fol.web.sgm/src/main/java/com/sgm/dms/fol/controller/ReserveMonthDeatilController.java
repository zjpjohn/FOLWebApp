/*
 * Copyright 2015 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : web.sgm
 *
 * @File name : ReserveMonthDeatilController.java
 *
 * @Author : shenweiwei
 *
 * @Date : 2015年10月20日
 *
----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2015年10月20日    shenweiwei    1.0
 *
 *
 *
 *
----------------------------------------------------------------------------------
 */

package com.sgm.dms.fol.controller;

import java.io.ByteArrayOutputStream;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sgm.dms.fol.common.api.domain.CodeDTO;
import com.sgm.dms.fol.common.api.services.CodeService;
import com.sgm.dms.fol.common.service.controller.impl.BaseController;
import com.sgm.dms.fol.common.service.utils.AutoPojo;
import com.sgm.dms.fol.common.service.utils.BeanMapper;
import com.sgm.dms.fol.common.service.utils.MapUtil;
import com.sgm.dms.fol.common.service.utils.StringUtil;
import com.sgm.dms.fol.dro.MonthDeatilDRO;
import com.sgm.dms.fol.reconciliation.api.ReconciliationManagementService;
import com.sgm.dms.fol.reserve.api.ReserveService;
import com.sgm.dms.fol.reserve.dto.ReserveDTO;
import com.sgm.dms.fol.vo.MonthDeatilVo;
import com.sgm.dms.fol.vo.ReserveTableDataVo;
import com.sgm.dms.fol.vo.ReserveVo;
import com.sgm.solution.framework.urlaccess.annotations.ValidationRequestUrl;

/*
 * 储备金月度明细查询
 * @author shenweiwei
 * TODO description
 * @date 2015年10月20日
 */
@Controller
@RequestMapping("/reserve/reservemonthdeatil")
public class ReserveMonthDeatilController extends BaseController {
	// 日志
	protected Logger logger = LogManager.getLogger(this.getClass());
	@Autowired
	private CodeService codeService;
	@Autowired
	private ReserveService reserveService;
	@Autowired
	private ReconciliationManagementService reconciliationManagementService;

	/**
	 * 储备金月度明细查询
	 * 
	 * @throws SQLException
	 */
	@RequestMapping(value = "/query", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ValidationRequestUrl
	@ResponseBody
	public Object getReserveMonthDeatil(@Validated @RequestBody ReserveVo reserveVo) throws Throwable {
		//储备金月度明细查询
		List<ReserveVo> list = findReserveMonthDeatilToDataBase(reserveVo);

		//批量转换VO
		List<ReserveTableDataVo> reserveVos = BeanMapper.mapList(list,ReserveTableDataVo.class);
		
		//金额转换成财务专用的模式
		StringUtil.fontFormatFinance(reserveVos);

		//设置前台需要的对象
		Map<String, Object> responsedata=MapUtil.setQueryDataToMap(reserveVos, Long.valueOf(getQueryTotalSize(reserveVo)));
		
		return responsedata;
	}

	/**
	 * 查询code数据
	 */
	@RequestMapping(value = "/findCodeTypeNames", method = RequestMethod.POST)
	@ValidationRequestUrl
	@ResponseBody
	public Object findCodeTypeNames() throws Throwable{
		List<List<?>> responsedata = findCodeTypeNamesToDataBase();			
		return responsedata;
	}

	/**
	 * 到 数据库查询初始数据
	 */
	private List<List<?>> findCodeTypeNamesToDataBase() throws SQLException {
		String[] columns = { "渠道类型" };
		List<List<?>> codeDTOlist = new ArrayList<List<?>>();
		for (int i = 0; i < columns.length; i++) {
			String column = columns[i];
			List<CodeDTO> codeDTOs = codeService.findCodeByTypeName(column);
			codeDTOlist.add(codeDTOs);
		}
		return codeDTOlist;
	}

	/**
	 * 到数据库查询数据并返回DTO数据集
	 * 
	 * @throws SQLException
	 */
	private List<ReserveVo> findReserveMonthDeatilToDataBase(ReserveVo reserveVo)
			throws SQLException {
		ReserveDTO searchDto = BeanMapper.map(reserveVo, ReserveDTO.class);
		List<ReserveDTO> data = reserveService.getReserveMonthDeatil(searchDto);

		logger.info("=====查询完成======");
		return convertDtoToVo(data);
	}

	/**
	 * 数据统一转换成VO对象
	 */
	private List<ReserveVo> convertDtoToVo(List<ReserveDTO> dtos) {
		return BeanMapper.mapList(dtos, ReserveVo.class);
	}

	/**
	 * 查询出总条数
	 * 
	 * @param reserveVo
	 * @return
	 * @throws SQLException
	 */
	private int getQueryTotalSize(ReserveVo reserveVo) throws SQLException {
		reserveVo.setBeginNo(null);
		reserveVo.setEndNo(null);
		List<ReserveVo> reservedtos = findReserveMonthDeatilToDataBase(reserveVo);
		return reservedtos.size();
	}

	/**
	 * 
	 * 月度明细导出
	 * @date 2016年4月6日
	 * @param request
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping("/monthDeatilResult")
	public ResponseEntity<byte[]> ezzxportMonthDeatilResult(HttpServletRequest request) throws Throwable {
		MonthDeatilVo vo = AutoPojo.bindRequestParam(request,
				MonthDeatilVo.class);
		ReserveDTO reserveDto = BeanMapper.map(vo, ReserveDTO.class);
		if (reserveDto.getDealerName() != null) {
			reserveDto.setDealerName(URLDecoder.decode(
					reserveDto.getDealerName(), "UTF-8"));
		}
		// reserveDto.setChangeTime(vo.getChangeTime());
		String[] header = { "序号", "客户代码", "客户名称", "FOL处理日期", "变动类型",
				"变动前储备金可用余额 ", "变动金额 " };
		// 查询数据
		List<ReserveDTO> data = reserveService
				.getReserveMonthDeatil(reserveDto);

		// 转换为要导出的数据
		List<MonthDeatilDRO> expList = BeanMapper.mapList(data,
				MonthDeatilDRO.class);
		StringUtil.fontFormatFinance(expList);
		// 设置序号
		setLineNumber(expList);
		// 定义文件名称
		String fileName = "Reserve_monthDeatil" + vo.getChangeTime() + ".xls";
		ByteArrayOutputStream bos = reconciliationManagementService
				.<MonthDeatilDRO> exportXls4BillFile(expList, vo.getToken(),
						header, fileName);
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

	private void setLineNumber(List<MonthDeatilDRO> expList) {
		if (null != expList && expList.size() > 0) {
			for (int i = 0; i < expList.size(); i++) {
				MonthDeatilDRO vo = expList.get(i);
				vo.setNum(i + 1);
			}
		}
	}
}
