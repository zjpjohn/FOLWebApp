/*
 * Copyright 2015 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : web.sgm
 *
 * @File name : ReserveDeatilChangeController.java
 *
 * @Author : shenweiwei
 *
 * @Date : 2015年10月16日
 *
----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2015年10月16日    shenweiwei    1.0
 *
 *
 *
 *
----------------------------------------------------------------------------------
 */

package com.dealer.controller;

import java.io.ByteArrayOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
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

import com.dealer.dro.ReserveChangeDeatilDro;
import com.dealer.dro.ReserveFreezeDeatilDro;
import com.dealer.vo.ReserveTableDataVo;
import com.dealer.vo.ReserveVo;
import com.sgm.dms.fol.common.api.domain.CodeDTO;
import com.sgm.dms.fol.common.api.exception.ServiceBizException;
import com.sgm.dms.fol.common.api.services.CodeService;
import com.sgm.dms.fol.common.service.controller.impl.BaseController;
import com.sgm.dms.fol.common.service.utils.AutoPojo;
import com.sgm.dms.fol.common.service.utils.BeanMapper;
import com.sgm.dms.fol.common.service.utils.DateUtil;
import com.sgm.dms.fol.common.service.utils.MapUtil;
import com.sgm.dms.fol.common.service.utils.StringUtil;
import com.sgm.dms.fol.reconciliation.api.ReconciliationManagementService;
import com.sgm.dms.fol.reserve.api.ReserveService;
import com.sgm.dms.fol.reserve.dto.ReserveDTO;
import com.sgm.solution.framework.urlaccess.annotations.ValidationRequestUrl;

/*
 *
 * @author shenweiwei
 * TODO description
 * @date 2015年10月16日
 */
@Controller
@RequestMapping("/datareport/account/reservedeatilchange")
public class ReserveDeatilChangeController extends BaseController {

	// 日志
	protected Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private ReserveService reserveService;

	@Autowired
	private CodeService codeService;

	@Autowired
	private ReconciliationManagementService reconciliationManagementService;

	// /**
	// * 用来接收跨域请求
	// */
	// @RequestMapping(value = "/changedeatilreserve/query", method =
	// RequestMethod.GET)
	// public @ResponseBody
	// Object getChangeDeatilReserveForCors(@RequestParam String jsonParams,
	// HttpServletRequest request,
	// HttpServletResponse response) throws Exception {
	// List<Object> responsedata=new ArrayList<Object>();
	// ResponseDTO responseDTO=new ResponseDTO();
	// try {
	// //设置接收跨域请求头信息
	// ResponseUtil.setResponseForCors(response);
	//
	// //查询区间数据集
	// ReserveVo reserveVo=JsonUtil.jsonToBean(jsonParams, ReserveVo.class);
	// List<ReserveDTO> data=findReserveDeatilChangeByDTO(reserveVo);
	// responsedata.add(data);
	//
	// //查询总行数
	// // List<ReserveDTO>
	// reservedatas=responseDTO.setTotal(getQueryTotalSizeForChange(reserveVo));(reserveVo);
	// // responseDTO.setTotal(reservedatas.size());
	// responseDTO.setTotal(getQueryTotalSizeForChange(reserveVo));
	//
	// responseDTO.setState(StateConstant.SUCCESS);
	// } catch (SQLException e) {
	// e.printStackTrace();
	// responseDTO.setState(StateConstant.DATABASE_ERROR);
	// responseDTO.setErrorCode(ExceptionConstants.DATABASE_OPERATION_ERROR);
	// }
	//
	// responsedata.add(responseDTO);
	// return responsedata;
	// }
	//
	// /**
	// * 用来接收跨域请求
	// */
	// @RequestMapping(value = "/freezedeatilreserve/query", method =
	// RequestMethod.GET)
	// public @ResponseBody
	// Object getFreezeDeatilReserveForCors(@RequestParam String jsonParams,
	// HttpServletRequest request,
	// HttpServletResponse response) throws Exception {
	// List<Object> responsedata=new ArrayList<Object>();
	// ResponseDTO responseDTO=new ResponseDTO();
	// try {
	// //设置接收跨域请求头信息
	// ResponseUtil.setResponseForCors(response);
	//
	// //查询区间数据集
	// ReserveVo reserveVo=JsonUtil.jsonToBean(jsonParams, ReserveVo.class);
	// List<ReserveDTO> data=findFreezeReserveDeatilChangeByDTO(reserveVo);
	// responsedata.add(data);
	//
	// //查询总行数
	// // List<ReserveDTO>
	// reservedatas=findFreezeReserveDeatilChangeCountByDTO(reserveVo);
	// // responseDTO.setTotal(reservedatas.size());
	// responseDTO.setTotal(getQueryTotalSizeForFrozen(reserveVo));
	//
	// responseDTO.setState(StateConstant.SUCCESS);
	// } catch (SQLException e) {
	// e.printStackTrace();
	// responseDTO.setState(StateConstant.DATABASE_ERROR);
	// responseDTO.setErrorCode(ExceptionConstants.DATABASE_OPERATION_ERROR);
	// }
	//
	// responsedata.add(responseDTO);
	// return responsedata;
	// }

	// /**
	// * 查询code数据
	// */
	// @RequestMapping(value = "/findCodeTypeNames", method =
	// RequestMethod.POST)
	// public @ResponseBody
	// Object findCodeTypeNames() {
	// List<Object> responsedata=new ArrayList<Object>();
	// ResponseDTO responseDTO=new ResponseDTO();
	// try {
	// List<List<?>> list= findCodeTypeNamesToDataBase();
	// responsedata.add(list);
	//
	// responseDTO.setState(StateConstant.SUCCESS);
	// } catch (SQLException sqlException) {
	// sqlException.printStackTrace();
	// responseDTO.setState(StateConstant.DATABASE_ERROR);
	// responseDTO.setErrorCode(ExceptionConstants.DATABASE_OPERATION_ERROR);
	// }
	// responsedata.add(responseDTO);
	// return responsedata;
	// }
	/**
	 * 动态查询储备金变动明细
	 */
	@RequestMapping(value = "/changedeatilreserve/query")
	@ValidationRequestUrl
	@ResponseBody
	public Object getChangeDeatilReserveForCors(@RequestBody ReserveVo reserveVo,HttpServletRequest request,HttpServletResponse response)
			throws Exception {
		// 查询区间数据集
		List<ReserveDTO> data = findReserveDeatilChangeByDTO(reserveVo);

		List<ReserveTableDataVo> reserveVos = BeanMapper.mapList(data, ReserveTableDataVo.class);
		StringUtil.fontFormatFinance(reserveVos);

		return MapUtil.setQueryDataToMap(reserveVos, Long.parseLong(getQueryTotalSizeForChange(reserveVo) + ""));
	}

	/**
	 * 动态查询储备金冻结明细
	 */
	@RequestMapping(value = "/freezedeatilreserve/query")
	@ValidationRequestUrl
	@ResponseBody
	public Object getFreezeDeatilReserveForCors(@RequestBody ReserveVo reserveVo,HttpServletRequest request,HttpServletResponse response)
			throws Exception {
		// 查询区间数据集
		List<ReserveDTO> data = findFreezeReserveDeatilChangeByDTO(reserveVo);

		List<ReserveTableDataVo> reserveVos = BeanMapper.mapList(data, ReserveTableDataVo.class);
		StringUtil.fontFormatFinance(reserveVos);

		return MapUtil.setQueryDataToMap(reserveVos, Long.parseLong(getQueryTotalSizeForFrozen(reserveVo) + ""));

	}

	@RequestMapping(value = "/findCodeTypeNames")
	@ValidationRequestUrl
	@ResponseBody
	public Object findCodeTypeNamesforCors(HttpServletRequest request,HttpServletResponse response)
			throws Exception {
		List<List<?>> list = findCodeTypeNamesToDataBase();

		return MapUtil.setQueryDataToMap(list, Long.parseLong(list.size()+""));

	}

	/**
	 * 根据DTO查询数据库数据(储备金动态明细查询)
	 * 
	 * @return
	 * @throws SQLException
	 */
	private List<ReserveDTO> findReserveDeatilChangeByDTO(ReserveVo reserveVo)
			throws ServiceBizException, Exception {
		ReserveDTO searchDto = BeanMapper.map(reserveVo, ReserveDTO.class);
		List<ReserveDTO> data = reserveService.getReserveChangeDeatilWithDealer(searchDto);
		logger.info("=====查询完成======");
		return data;
	}

	/**
	 * 根据DTO查询数据库数据(储备金冻结动态明细查询)
	 * 
	 * @return
	 * @throws SQLException
	 */
	private List<ReserveDTO> findFreezeReserveDeatilChangeByDTO(
			ReserveVo reserveVo) throws ServiceBizException, Exception {
		ReserveDTO searchDto = BeanMapper.map(reserveVo, ReserveDTO.class);
		List<ReserveDTO> data = reserveService.getReserveFreezeDeatilWithDealer(searchDto);
		logger.info("=====查询完成======");
		return data;
	}

	/**
	 * 根据DTO查询数据库数据(储备金动态明细记录总数)
	 * 
	 * @return
	 * @throws SQLException
	 */
	private List<ReserveDTO> findReserveDeatilChangeCountByDTO(
			ReserveVo reserveVo) throws ServiceBizException, SQLException {
		reserveVo.setBeginNo(0);
		reserveVo.setEndNo(0);
		ReserveDTO searchDto = BeanMapper.map(reserveVo, ReserveDTO.class);
		List<ReserveDTO> data = reserveService.getReserveChangeDeatilByWhere(searchDto);
		logger.info("=====查询完成======");
		return data;
	}

	/**
	 * 根据DTO查询数据库数据(储备金冻结动态明细记录总数)
	 * 
	 * @return
	 * @throws SQLException
	 */
	private List<ReserveDTO> findFreezeReserveDeatilChangeCountByDTO(
			ReserveVo reserveVo) throws ServiceBizException, SQLException {
		reserveVo.setBeginNo(0);
		reserveVo.setEndNo(0);
		ReserveDTO searchDto = BeanMapper.map(reserveVo, ReserveDTO.class);
		List<ReserveDTO> data = reserveService.getReserveFreezeDeatilByWhere(searchDto);
		logger.info("=====查询完成======");
		return data;
	}

	/**
	 * 到 数据库查询初始数据
	 * 
	 * @throws ServiceBizException
	 */
	private List<List<?>> findCodeTypeNamesToDataBase() throws SQLException {
		List<List<?>> dtolist = new ArrayList<List<?>>();
		String[] columns = { "变动类型" };

		for (int i = 0; i < columns.length; i++) {
			String column = columns[i];
			List<CodeDTO> codeDTOs = codeService.findCodeByTypeName(column);
			dtolist.add(codeDTOs);
		}

		return dtolist;
	}

	/**
	 * 查询变动明细的总行数
	 * 
	 * @param reserveVo
	 * @return
	 * @throws SQLException
	 */
	private int getQueryTotalSizeForChange(ReserveVo reserveVo)
			throws SQLException {
		reserveVo.setBeginNo(null);
		reserveVo.setEndNo(null);
		List<ReserveDTO> reservedtos = findReserveDeatilChangeCountByDTO(reserveVo);
		return reservedtos.size();
	}

	/**
	 * 查询冻结明细的总行数
	 * 
	 * @param reserveVo
	 * @return
	 * @throws SQLException
	 */
	private int getQueryTotalSizeForFrozen(ReserveVo reserveVo)
			throws SQLException {
		reserveVo.setBeginNo(null);
		reserveVo.setEndNo(null);
		List<ReserveDTO> reservedtos = findFreezeReserveDeatilChangeCountByDTO(reserveVo);
		return reservedtos.size();
	}

	/**
	 * 查询储备金变动明细信息导出
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/ReserveChangeDeatil")
	@ValidationRequestUrl
	public ResponseEntity<byte[]> exportReserveChangeDeatilResult(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ReserveVo vo = AutoPojo.bindRequestParam(request, ReserveVo.class);
		ReserveDTO reserveDto = BeanMapper.map(vo, ReserveDTO.class);
		String[] header = { "序号", "客户代码", "客户名称", "变更日期", "变动前储备金金额  ",
				"变动金额 ", "变动后储备金金额 ", "变动类型", "摘要" };
		//解密
//		reserveDto.setSapCode(reserveDto.getSapCode());
		// 查询数据
		List<ReserveDTO> data = reserveService
				.getReserveChangeDeatilByWhere(reserveDto);
		// 转换为要导出的数据
		List<ReserveChangeDeatilDro> expList = BeanMapper.mapList(data,
				ReserveChangeDeatilDro.class);
		StringUtil.fontFormatFinance(expList);
		// 设置序号
		setLineNumber(expList);

		// 定义文件名称
		String fileName = "dealer_reserveChange"
				+ DateUtil.date2str(new Date()) + ".xls";
		// 导出
		ByteArrayOutputStream bos = reconciliationManagementService
				.<ReserveChangeDeatilDro> exportXls4BillFile(expList,
						vo.getToken(), header, fileName);
		return new ResponseEntity<byte[]>(bos.toByteArray(),
				getHeaders(fileName), HttpStatus.CREATED);
	}

	/**
	 * 储备金冻结变动明细导出
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/ReserveFreezeDeatil")
	@ValidationRequestUrl
	public ResponseEntity<byte[]> exportDeatilFrozenReserve(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ReserveVo vo = AutoPojo.bindRequestParam(request, ReserveVo.class);
		ReserveDTO reserveDto = BeanMapper.map(vo, ReserveDTO.class);
		String[] header = { "序号", "客户代码", "客户名称", "变更日期", "冻结前储备金金额 ",
				"冻结金额  ", "冻结后储备金金额  ", "变动类型", "摘要" };
		// 查询数据
		List<ReserveDTO> data = reserveService
				.getReserveFreezeDeatilByWhere(reserveDto);
		// 转换为要导出的数据
		List<ReserveFreezeDeatilDro> expList = BeanMapper.mapList(data,
				ReserveFreezeDeatilDro.class);
		StringUtil.fontFormatFinance(expList);
		// 设置序号
		setLineNumber1(expList);

		// 定义文件名称
		String fileName = "dealer_reserveFreeze"
				+ DateUtil.date2str(new Date()) + ".xls";
		// 导出
		ByteArrayOutputStream bos = reconciliationManagementService
				.<ReserveFreezeDeatilDro> exportXls4BillFile(expList,
						vo.getToken(), header, fileName);
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

	private void setLineNumber(List<ReserveChangeDeatilDro> expList) {
		if (null != expList && expList.size() > 0) {
			for (int i = 0; i < expList.size(); i++) {
				ReserveChangeDeatilDro vo = expList.get(i);
				vo.setNum(i + 1);
			}
		}
	}

	private void setLineNumber1(List<ReserveFreezeDeatilDro> expList) {
		if (null != expList && expList.size() > 0) {
			for (int i = 0; i < expList.size(); i++) {
				ReserveFreezeDeatilDro vo = expList.get(i);
				vo.setNum(i + 1);
			}
		}
	}

}
