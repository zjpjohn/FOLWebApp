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

package com.sgm.dms.fol.controller;

import java.io.ByteArrayOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
import com.sgm.dms.fol.common.api.exception.ServiceBizException;
import com.sgm.dms.fol.common.api.services.CodeService;
import com.sgm.dms.fol.common.service.controller.impl.BaseController;
import com.sgm.dms.fol.common.service.utils.AutoPojo;
import com.sgm.dms.fol.common.service.utils.BeanMapper;
import com.sgm.dms.fol.common.service.utils.DateUtil;
import com.sgm.dms.fol.common.service.utils.MapUtil;
import com.sgm.dms.fol.common.service.utils.RSAUtil;
import com.sgm.dms.fol.common.service.utils.StringUtil;
import com.sgm.dms.fol.dro.DeatilFrozenReserveDRO;
import com.sgm.dms.fol.dro.DeatilReserveChangeDRO;
import com.sgm.dms.fol.reconciliation.api.ReconciliationManagementService;
import com.sgm.dms.fol.reserve.api.ReserveService;
import com.sgm.dms.fol.reserve.dto.ReserveDTO;
import com.sgm.dms.fol.vo.DeatilChangeVo;
import com.sgm.dms.fol.vo.ReserveTableDataVo;
import com.sgm.dms.fol.vo.ReserveVo;
import com.sgm.solution.framework.urlaccess.annotations.ValidationRequestUrl;

/*
 * 储备金变动明细
 * @author shenweiwei
 * TODO description
 * @date 2015年10月16日
 */
@Controller
@RequestMapping("/reserve/reservedeatilchange")
public class ReserveDeatilChangeController extends BaseController {

	// 日志
	protected Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private ReserveService reserveService;

	@Autowired
	private CodeService codeService;

	@Autowired
	private ReconciliationManagementService reconciliationManagementService;

	/**
	 * 查询储备金变动记录明细
	 */
	@RequestMapping(value = "/changedeatilreserve/query", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ValidationRequestUrl
	@ResponseBody
	public Object getChangeDeatilReserve(@Validated @RequestBody ReserveVo reserveVo,HttpServletRequest request) throws Throwable {
	    //查询储备金变动明细
		List<ReserveDTO> data = findReserveDeatilChangeByDTO(reserveVo);

		//批量转成VO
		List<ReserveTableDataVo> reservechangevos = BeanMapper.mapList(data,ReserveTableDataVo.class);
		
		//金额转换成财务专用的模式
		StringUtil.fontFormatFinance(reservechangevos);
		
		//设置前台需要的对象
		Map<String, Object> responsedata=MapUtil.setQueryDataToMap(reservechangevos, Long.valueOf(getQueryTotalSizeForChange(reserveVo)));

		return responsedata;
	}

	/**
	 * 查询储备金冻结记录明细
	 */
	@RequestMapping(value = "/freezedeatilreserve/query", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ValidationRequestUrl
	@ResponseBody
	public Object getFreezeDeatilReserve(@Validated @RequestBody ReserveVo reserveVo,HttpServletRequest request) throws Throwable {
		//查询储备金冻结记录明细
		List<ReserveDTO> data = findFreezeReserveDeatilChangeByDTO(reserveVo);

		//批量转成VO
		List<ReserveTableDataVo> reserveFreezevos = BeanMapper.mapList(data,ReserveTableDataVo.class);
			
		//金额转换成财务专用的模式
		StringUtil.fontFormatFinance(reserveFreezevos);

		//设置前台需要的对象
		Map<String, Object> responsedata=MapUtil.setQueryDataToMap(reserveFreezevos, Long.valueOf(getQueryTotalSizeForFrozen(reserveVo)));
		
		return responsedata;
	}

	/**
	 * 查询code数据
	 */
	@RequestMapping(value = "/findCodeTypeNames", method = RequestMethod.POST)
	@ValidationRequestUrl
	@ResponseBody
	public Object findCodeTypeNames() throws Throwable {
		List<List<?>> responsedata = findCodeTypeNamesToDataBase();	
		return responsedata;
	}

	/**
	 * 根据DTO查询数据库数据(储备金动态明细查询)
	 * 
	 * @return
	 * @throws SQLException
	 */
	private List<ReserveDTO> findReserveDeatilChangeByDTO(ReserveVo reserveVo) throws Exception {
	    reserveVo.setSapCode(RSAUtil.encryptByPublicKey(reserveVo.getSapCode()));
		ReserveDTO searchDto = BeanMapper.map(reserveVo, ReserveDTO.class);
		List<ReserveDTO> data = reserveService.getReserveChangeDeatilByWhere(searchDto);
		logger.info("=====查询完成======");
		return data;
	}

	/**
	 * 根据DTO查询数据库数据(储备金冻结动态明细查询)
	 * 
	 * @return
	 * @throws SQLException
	 */
	private List<ReserveDTO> findFreezeReserveDeatilChangeByDTO(ReserveVo reserveVo) throws Exception {
	    reserveVo.setSapCode(RSAUtil.encryptByPublicKey(reserveVo.getSapCode()));
		ReserveDTO searchDto = BeanMapper.map(reserveVo, ReserveDTO.class);
		List<ReserveDTO> data = reserveService.getReserveFreezeDeatilByWhere(searchDto);
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
		String[] columns = { "渠道类型", "变动类型" };
		List<List<?>> dtolist = new ArrayList<List<?>>();
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
	@RequestMapping(value = "/ChangeReserveAmount")
	public ResponseEntity<byte[]> exportReserveChangeDeatilResult(
			HttpServletRequest request) throws Throwable {
		DeatilChangeVo vo = AutoPojo.bindRequestParam(request,
				DeatilChangeVo.class);
		ReserveDTO reserveDto = BeanMapper.map(vo, ReserveDTO.class);
		String[] header = { "序号", "客户代码", "客户名称", "变更日期", "变动前储备金金额  ",
				"变动金额 ", "变动后储备金金额 ", "变动类型", "摘要" };

		// 查询数据
		List<ReserveDTO> data = reserveService
				.getReserveChangeDeatilByWhere(reserveDto);
		// 转换为要导出的数据
		List<DeatilReserveChangeDRO> expList = BeanMapper.mapList(data,
				DeatilReserveChangeDRO.class);
		StringUtil.fontFormatFinance(expList);
		// 设置序号
		setLineNumber(expList);

		// 定义文件名称
		String fileName = "Reserve_deatilChange"
				+ DateUtil.date2str(new Date()) + ".xls";
		// 导出
		ByteArrayOutputStream bos = reconciliationManagementService
				.<DeatilReserveChangeDRO> exportXls4BillFile(expList,
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
	@RequestMapping("/FreezeReserveAmount")
	public ResponseEntity<byte[]> exportDeatilFrozenReserve(
			HttpServletRequest request) throws Throwable {
		DeatilChangeVo vo = AutoPojo.bindRequestParam(request,
				DeatilChangeVo.class);
		ReserveDTO reserveDto = BeanMapper.map(vo, ReserveDTO.class);
		String[] header = { "序号", "客户代码", "客户名称", "变更日期", "冻结前储备金金额 ",
				"冻结金额  ", "冻结后储备金金额  ", "变动类型", "摘要" };
		// 查询数据
		List<ReserveDTO> data = reserveService
				.getReserveFreezeDeatilByWhere(reserveDto);
		// 转换为要导出的数据
		List<DeatilFrozenReserveDRO> expList = BeanMapper.mapList(data,
				DeatilFrozenReserveDRO.class);
		StringUtil.fontFormatFinance(expList);
		// 设置序号
		setLineNumber1(expList);

		// 定义文件名称
		String fileName = "Reserve_deatilFreeze"
				+ DateUtil.date2str(new Date()) + ".xls";
		// 导出
		ByteArrayOutputStream bos = reconciliationManagementService
				.<DeatilFrozenReserveDRO> exportXls4BillFile(expList,
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

	private void setLineNumber(List<DeatilReserveChangeDRO> expList) {
		if (null != expList && expList.size() > 0) {
			for (int i = 0; i < expList.size(); i++) {
				DeatilReserveChangeDRO vo = expList.get(i);
				vo.setNum(i + 1);
			}
		}
	}

	private void setLineNumber1(List<DeatilFrozenReserveDRO> expList) {
		if (null != expList && expList.size() > 0) {
			for (int i = 0; i < expList.size(); i++) {
				DeatilFrozenReserveDRO vo = expList.get(i);
				vo.setNum(i + 1);
			}
		}
	}
}
