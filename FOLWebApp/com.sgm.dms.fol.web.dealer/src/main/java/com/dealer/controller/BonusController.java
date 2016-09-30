/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : web.dealer
*
* @File name : BonusController.java
*
* @Author : DELL
*
* @Date : 2016年1月21日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2016年1月21日    DELL    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/
	
package com.dealer.controller;

import java.io.ByteArrayOutputStream;
import java.sql.SQLException;
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
import com.dealer.vo.BonusTableDataVo;
import com.dealer.vo.BonusVo;
import com.dealer.vo.ReserveVo;
import com.sgm.dms.fol.bonus.api.BonusService;
import com.sgm.dms.fol.bonus.dto.BonusQueryDTO;
import com.sgm.dms.fol.common.api.exception.ServiceBizException;
import com.sgm.dms.fol.common.service.controller.impl.BaseController;
import com.sgm.dms.fol.common.service.utils.AutoPojo;
import com.sgm.dms.fol.common.service.utils.BeanMapper;
import com.sgm.dms.fol.common.service.utils.DateUtil;
import com.sgm.dms.fol.common.service.utils.MapUtil;
import com.sgm.dms.fol.common.service.utils.StringUtil;
import com.sgm.dms.fol.reconciliation.api.ReconciliationManagementService;
import com.sgm.solution.framework.urlaccess.annotations.ValidationRequestUrl;

/*
*
* @author DELL
* TODO description
* @date 2016年1月21日
*/

@Controller
@RequestMapping("/bonus")
public class BonusController extends BaseController{

    // 日志
    protected Logger logger = LogManager.getLogger(this.getClass());
    
    @Autowired
    private BonusService bonusService;
    @Autowired
	private ReconciliationManagementService reconciliationManagementService;
    /**
     * 动态查询奖金变动明细
     */
    @RequestMapping("/changedeatilbonus/query")
    @ResponseBody
    @ValidationRequestUrl
    public Object findBonusDeatilAmount(@RequestBody BonusVo bonusVo,HttpServletRequest request,HttpServletResponse response) throws Exception{
		// 查询区间数据集
		List<BonusQueryDTO> data = findBonusDeatilChangeByDTO(bonusVo);

		List<BonusTableDataVo> bonusVos = BeanMapper.mapList(data, BonusTableDataVo.class);
		StringUtil.fontFormatFinance(bonusVos);

		// 响应
		return MapUtil.setQueryDataToMap(bonusVos, Long.parseLong(getQueryTotalSizeForChange(bonusVo) + ""));
    }
    
    /**
     * 动态查询奖金冻结明细
     * @throws Exception 
     * @throws ServiceBizException 
     */
    
    @RequestMapping(value = "/freezedeatilbonus/query")
    @ResponseBody
    @ValidationRequestUrl
    public Object getFreezeDeatilBonus(@RequestBody BonusVo bonusVo,HttpServletRequest request,HttpServletResponse response) throws ServiceBizException, Exception{
		// 查询区间数据集
		List<BonusQueryDTO> data = findFreezeBonusDeatilChangeByDTO(bonusVo);

		List<BonusTableDataVo> bonusVos = BeanMapper.mapList(data, BonusTableDataVo.class);
		StringUtil.fontFormatFinance(bonusVos);

		// 响应
		return MapUtil.setQueryDataToMap(bonusVos, Long.parseLong(getQueryTotalSizeForFrozen(bonusVo) + ""));
    }
    
    /**
     * 根据DTO查询数据库数据(储备金动态明细查询)
     * 
     * @return
     * @throws SQLException
     */
    private List<BonusQueryDTO> findBonusDeatilChangeByDTO(BonusVo bonusVo)
            throws ServiceBizException, Exception {
        BonusQueryDTO searchDto = BeanMapper.map(bonusVo, BonusQueryDTO.class);
        List<BonusQueryDTO> data = bonusService.getBonusChangeDeatilWithDealer(searchDto);
        logger.info("=====查询完成======");
        return data;
    }

    /**
     * 根据DTO查询数据库数据(储备金冻结动态明细查询)
     * 
     * @return
     * @throws SQLException
     */
    private List<BonusQueryDTO> findFreezeBonusDeatilChangeByDTO(BonusVo bonusVo) throws ServiceBizException, Exception {
        BonusQueryDTO searchDto = BeanMapper.map(bonusVo, BonusQueryDTO.class);
        List<BonusQueryDTO> data = bonusService.getBonusFreezeDeatilWithDealer(searchDto);
        logger.info("=====查询完成======");
        return data;
    }

    /**
     * 根据DTO查询数据库数据(储备金动态明细记录总数)
     * 
     * @return
     * @throws SQLException
     */
    private int findBonusDeatilChangeCountByDTO(BonusVo bonusVo) throws ServiceBizException, SQLException {
        BonusQueryDTO searchDto = BeanMapper.map(bonusVo, BonusQueryDTO.class);
        int count = bonusService.getBonusChangeDeatilCountByWhere(searchDto);
        logger.info("=====查询完成======");
        return count;
    }

    /**
     * 根据DTO查询数据库数据(储备金冻结动态明细记录总数)
     * 
     * @return
     * @throws SQLException
     */
    private int findFreezeBonusDeatilChangeCountByDTO(BonusVo bonusVo) throws ServiceBizException, SQLException {
        BonusQueryDTO searchDto = BeanMapper.map(bonusVo, BonusQueryDTO.class);
        int count = bonusService.getBonusFreezeDeatilCountByWhere(searchDto);
        logger.info("=====查询完成======");
        return count;
    }
    
    /**
     * 查询变动明细的总行数
     * 
     * @param reserveVo
     * @return
     * @throws SQLException
     */
    private int getQueryTotalSizeForChange(BonusVo bonusVo)
            throws SQLException {
        bonusVo.setBeginNo(null);
        bonusVo.setEndNo(null);
        return findBonusDeatilChangeCountByDTO(bonusVo);
    }

    /**
     * 查询冻结明细的总行数
     * 
     * @param reserveVo
     * @return
     * @throws SQLException
     */
    private int getQueryTotalSizeForFrozen(BonusVo bonusVo)
            throws SQLException {
        bonusVo.setBeginNo(null);
        bonusVo.setEndNo(null);
        return findFreezeBonusDeatilChangeCountByDTO(bonusVo);
    }
	/**
	 * 查询储备金变动明细信息导出
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/BonusChangeDeatil")
	@ValidationRequestUrl
	public ResponseEntity<byte[]> exportReserveChangeDeatilResult(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ReserveVo vo = AutoPojo.bindRequestParam(request, ReserveVo.class);
		BonusQueryDTO bonusQueryDTO = BeanMapper.map(vo, BonusQueryDTO.class);
		String[] header = { "序号", "客户代码", "客户名称", "变更日期", "变动前奖金金额  ",
				"变动金额 ", "变动后奖金金额 ", "变动类型", "摘要" };
		//解密
//		reserveDto.setSapCode(reserveDto.getSapCode());
		// 查询数据
		List<BonusQueryDTO> data = bonusService
				.getBonusChangeDeatilWithDealer(bonusQueryDTO);
		// 转换为要导出的数据
		List<ReserveChangeDeatilDro> expList = BeanMapper.mapList(data,
				ReserveChangeDeatilDro.class);
		StringUtil.fontFormatFinance(expList);
		// 设置序号
		setLineNumber(expList);

		// 定义文件名称
		String fileName = "dealer_bonusChange"
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
	@RequestMapping("/BonusFreezeDeatil")
	@ValidationRequestUrl
	public ResponseEntity<byte[]> exportDeatilFrozenReserve(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ReserveVo vo = AutoPojo.bindRequestParam(request, ReserveVo.class);
		BonusQueryDTO bonusDto = BeanMapper.map(vo, BonusQueryDTO.class);
		String[] header = { "序号", "客户代码", "客户名称", "变更日期", "冻结前奖金金额 ",
				"冻结金额  ", "冻结后奖金金额  ", "变动类型", "摘要" };
		// 查询数据
		List<BonusQueryDTO> data = bonusService
				.getBonusFreezeDeatilWithDealer(bonusDto);
		// 转换为要导出的数据
		List<ReserveFreezeDeatilDro> expList = BeanMapper.mapList(data,
				ReserveFreezeDeatilDro.class);
		StringUtil.fontFormatFinance(expList);
		// 设置序号
		setLineNumber1(expList);

		// 定义文件名称
		String fileName = "dealer_bonusFreeze"
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
