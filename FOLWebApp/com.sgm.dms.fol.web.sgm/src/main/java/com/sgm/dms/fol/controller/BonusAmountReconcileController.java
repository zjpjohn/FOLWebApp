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
 * @Date : 2015年01月08日
 *
----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2015年01月08日   shenweiwei    1.0
 * 2. 2016年03月23日   shenweiwei    2.0
 *
 *
 *
----------------------------------------------------------------------------------
 */

package com.sgm.dms.fol.controller;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
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

import com.sgm.dms.fol.bonus.api.BonusService;
import com.sgm.dms.fol.bonus.dto.BonusReconcileDTO;
import com.sgm.dms.fol.common.api.domain.CodeDTO;
import com.sgm.dms.fol.common.api.services.CodeService;
import com.sgm.dms.fol.common.service.controller.impl.BaseController;
import com.sgm.dms.fol.common.service.utils.AutoPojo;
import com.sgm.dms.fol.common.service.utils.BeanMapper;
import com.sgm.dms.fol.common.service.utils.DateUtil;
import com.sgm.dms.fol.common.service.utils.MapUtil;
import com.sgm.dms.fol.common.service.utils.StringUtil;
import com.sgm.dms.fol.dro.BonusReconcileDRO;
import com.sgm.dms.fol.reconciliation.api.ReconciliationManagementService;
import com.sgm.dms.fol.vo.BonusVo;
import com.sgm.solution.framework.urlaccess.annotations.ValidationRequestUrl;

/**
 * 奖金余额对账
 * @author shenweiwei
 * @date 2015年01月08日
 */
@Controller
@RequestMapping("/bonus/bonusAmountReconcile")
public class BonusAmountReconcileController extends BaseController {
	// 日志
	protected Logger logger = LogManager.getLogger(this.getClass());
	@Autowired
	private CodeService codeService;
	@Autowired
	private BonusService bonusService;
	@Autowired
	private ReconciliationManagementService reconciliationManagementService;

	/**
	 * 余额对账
	 * @param bonusVo
	 * @throws SQLException
	 */
	@RequestMapping(value = "/query", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ValidationRequestUrl
	@ResponseBody
	public Object getBonusAmountReconcile(@Validated @RequestBody BonusVo bonusVo) throws Throwable {

	    // 数据库查询数据
	    List<BonusVo> list = findBonusAmountReconcileToDataBase(bonusVo);

		// 批量转换成vo
		List<BonusVo> bonusVos = BeanMapper.mapList(list,BonusVo.class);
			
		// 转换成财务专用格式		
		setMoneyFormat(bonusVos);
		
		// 设置前台需要的参数
		Map<String, Object> responsedata=MapUtil.setQueryDataToMap(bonusVos, Long.valueOf(getQueryTotalSize(bonusVo)));
			
		return responsedata;
	}

	/**
	 * 查询code数据
	 */
	@RequestMapping(value = "/findCodeTypeNames", method = RequestMethod.POST)
	@ValidationRequestUrl
	@ResponseBody
	public Object findCodeTypeNames() throws Throwable{
		//查询code数据
		List<List<?>> responsedata = findCodeTypeNamesToDataBase();
			
		return responsedata;
	}
	/**
	 * 到数据库查询Code
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
	 * 到数据库查询数据并返回DTO数据
	 * @param bonusVo
	 * @throws SQLException
	 */
	private List<BonusVo> findBonusAmountReconcileToDataBase(BonusVo bonusVo) throws SQLException {
		BonusReconcileDTO searchDto = BeanMapper.map(bonusVo, BonusReconcileDTO.class);
		List<BonusReconcileDTO> data = bonusService.getBonusAmountReconcile(searchDto);
		logger.info("=====查询完成======");
		return convertDtoToVo(data);
	}

	/**
	 * 数据统一转换成VO对象
	 */
	private List<BonusVo> convertDtoToVo(List<BonusReconcileDTO> dtos) {
		return BeanMapper.mapList(dtos, BonusVo.class);
	}
	/**
	 * 查询总条数
	 * @param bonusVo
	 * @return
	 * @throws SQLException
	 */
	private int getQueryTotalSize(BonusVo bonusVo) throws SQLException {
		bonusVo.setBeginNo(null);
		bonusVo.setEndNo(null);
		List<BonusVo> bonusVos = findBonusAmountReconcileToDataBase(bonusVo);
		return bonusVos.size();
	}
	/**
	 * 导出余额对账明细
	 */
	@RequestMapping("/amountReconcile")
	public ResponseEntity<byte[]> exportAmountReconcileResult(
			HttpServletRequest request) throws Throwable {
		BonusVo vo = AutoPojo.bindRequestParam(request,BonusVo.class);
		BonusReconcileDTO bonusDto=BeanMapper.map(vo, BonusReconcileDTO.class);
		// 设置标题
		String[] header = { "序号", "客户代码", "客户名称","FOL本月期初余额",
				"本月增加:财务本月颁发 , 退货奖金返回 ", "本月减少: billing使用 ",
				"FOL本月期末余额","SAP奖金信息:本月期初余额,本月期末余额","余额差异" };
		// 查询数据
		List<BonusReconcileDTO> data = bonusService.getBonusAmountReconcile(bonusDto);

		// 转换为要导出的数据
		List<BonusReconcileDRO> expList = BeanMapper.mapList(data,
				BonusReconcileDRO.class);
		
		//设置财务专用数据
		setMoneyFormat(expList);
		
		// 设置序号
		setLineNumber(expList);
		// 定义文件名称
		String fileName = "Bonus_amountReconcile" + DateUtil.date2str(new Date())+ ".xls";
		// 导出
		ByteArrayOutputStream bos = reconciliationManagementService
				.<BonusReconcileDRO> exportXls4BillFiles(expList,
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

	private void setLineNumber(List<BonusReconcileDRO> expList) {
		if (null != expList && expList.size() > 0) {
			for (int i = 0; i < expList.size(); i++) {
				BonusReconcileDRO vo = expList.get(i);
				vo.setNum(i + 1);
			}
		}
	}

	/**
	 * 设置财务专用数据
	 */
	private void setMoneyFormat(List<?> list) throws Exception{
	    StringUtil.fontFormatFinance(list);
	    
		for (Object object : list) {
		    if(object instanceof BonusVo){
		        BonusVo bonusVo=(BonusVo)object;
		        bonusVo.setBonusAdd(String.format("%,.2f",new BigDecimal(bonusVo.getBonusAdd())));
	            bonusVo.setBillBonus(String.format("%,.2f",new BigDecimal(bonusVo.getBillBonus())));
		    }else if(object instanceof BonusReconcileDRO){
		        BonusReconcileDRO bonusDro=(BonusReconcileDRO)object;
		        bonusDro.setBonusAdd(String.format("%,.2f",new BigDecimal(bonusDro.getBonusAdd())));
	            bonusDro.setBillBonus(String.format("%,.2f",new BigDecimal(bonusDro.getBillBonus())));
		    }
			
		}
	}
	
}
