
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : invoice.service
 *
 * @File name : WrCollectServiceImpl.java
 *
 * @Author : wangfl
 *
 * @Date : 2016年5月12日
 *
 ----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2016年5月12日    wangfl    1.0
 *
 *
 *
 *
 ----------------------------------------------------------------------------------
 */
	
package com.sgm.dms.fol.invoice.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgm.dms.fol.common.api.constants.CodeConstant;
import com.sgm.dms.fol.common.api.exception.ServiceBizException;
import com.sgm.dms.fol.common.api.exception.VoNotValidException;
import com.sgm.dms.fol.common.service.utils.BeanMapper;
import com.sgm.dms.fol.common.service.utils.CommonUtils;
import com.sgm.dms.fol.invoice.api.WrCollectService;
import com.sgm.dms.fol.invoice.domain.WrCollect;
import com.sgm.dms.fol.invoice.domain.WrOrder;
import com.sgm.dms.fol.invoice.dto.InvoiceDto;
import com.sgm.dms.fol.invoice.dto.StayInvoiceDTO;
import com.sgm.dms.fol.invoice.dto.WrCollectDTO;
import com.sgm.dms.fol.invoice.dto.WrOrderDTO;
import com.sgm.dms.fol.invoice.repository.InvoiceDao;
import com.sgm.dms.fol.invoice.repository.WrCollectDao;
import com.sgm.dms.fol.invoice.repository.WrOrderDao;

/**
 * @author wangfl
 * TODO description
 * @date 2016年5月12日
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class WrCollectServiceImpl implements WrCollectService {
	
	@Autowired
	private WrOrderDao wrOrderDao;
	
	@Autowired
	private WrCollectDao wrCollectDao;
	
	@Autowired
	private InvoiceDao invoiceDao;

	/**
	 * @author wangfl
	 * @date 2016年5月12日
	 * @param wrOrderDtoList
	 * @throws ServiceBizException
	 * (non-Javadoc)
	 * @see com.sgm.dms.fol.invoice.api.WrCollectService#collectWrOrder(java.util.List)
	 */

	@Override
	public void collectWrOrder(List<WrOrderDTO> wrOrderDtoList) throws ServiceBizException {
		//Dto转po
		List<WrOrder> wrOrderList = BeanMapper.mapList(wrOrderDtoList, WrOrder.class);
		CommonUtils.filterSpecialWord(wrOrderList);
		
		//1.校验索赔金额是否大于等于0
		BigDecimal totalGross=new BigDecimal("0");
		for (WrOrder wrOrder : wrOrderList) {
			if(null != wrOrder.getGross()){
				totalGross = totalGross.add(wrOrder.getGross());
			}
		}
		if(totalGross.compareTo(new BigDecimal("0")) == -1){
			throw new ServiceBizException("索赔总金额为负数，合并失败。");
		}
		
		//2.校验索赔单是否已汇总过 ps.同一张索赔单不能重复汇总
		List<WrOrderDTO> wrOrderValidList = wrOrderDao.findWrOrderListByPrimary(wrOrderDtoList);
		if(null != wrOrderValidList && wrOrderValidList.size() > 0){
			StringBuffer errorMsg = new StringBuffer();
			for (WrOrderDTO wrOrderDTO : wrOrderValidList) {
				errorMsg.append("索赔单号为").append(wrOrderDTO.getWrNo()).append("，工单号为").append(wrOrderDTO.getRoNo()).append("，行号为").append(wrOrderDTO.getLineNo()).append("的索赔单已合并过，不能重复合并；");
			}
			throw new VoNotValidException(errorMsg.toString());
		}
		
		//3.把要汇总的索赔单同步到本系统数据库
		for (WrOrder wrOrder : wrOrderList) {
			wrOrderDao.insert(wrOrder);
		}
		
		
		//4.添加汇总结果
		List<WrCollect> wrCollectList = new ArrayList<WrCollect>();
		WrCollect wrCollect = null;
		for (WrOrder wrOrder : wrOrderList) {
			wrCollect = new WrCollect();
			wrCollect.setWrId(wrOrder.getWrId());
			wrCollect.setTsId(CodeConstant.PROJECT_NAME+(new Date().getTime()));//tsId="FOL"+时间戳
			wrCollect.setCreateBy(wrOrder.getCreateBy());
			wrCollect.setUpdateBy(wrOrder.getUpdateBy());
			wrCollectList.add(wrCollect);
		}
		
		wrCollectDao.insertList(wrCollectList);
		
		//4.把汇总状态发给wol
	}

	@Override
	public List<StayInvoiceDTO> getStayInvoicePageList(StayInvoiceDTO dto) {
	    CommonUtils.filterSpecialWord(dto);
		return wrCollectDao.findStayInvoicePageList(dto);
	}

	@Override
	public int getStayInvoiceTotalNum(StayInvoiceDTO dto) {
	    CommonUtils.filterSpecialWord(dto);
		return wrCollectDao.findStayInvoiceTotalNum(dto);
	}

	@Override
	public void bathchUpdateExpressNo(WrCollectDTO dto) {
	    CommonUtils.filterSpecialWord(dto);
		String[] tsIdAarry = dto.getTsId().split(",");
		WrCollectDTO wrCollectDTO = null;
		for (String tsId : tsIdAarry) {
			wrCollectDTO = new WrCollectDTO();
			wrCollectDTO.setTsId(tsId);
			wrCollectDTO.setExpressNo(dto.getExpressNo());
			wrCollectDao.updateWrCollectByTsId(wrCollectDTO);
		}
		
	}

	@Override
	public List<WrOrderDTO> getWrOrderListByTsId(String tsId) {
		return wrOrderDao.findWrOrderListByTsId(tsId);
	}

	@Override
	public void delStayInvoiceByTsId(String tsId) {
		String[] tsIdAarry = tsId.split(",");
		WrCollectDTO wrCollectDTO = null;
		for (String tsIdTemp : tsIdAarry) {
			//删除索赔单
			wrOrderDao.delWrOrderByTsId(tsIdTemp);
			//删除待开发票
			wrCollectDTO = new WrCollectDTO();
			wrCollectDTO.setTsId(tsIdTemp);
			wrCollectDTO.setValidStr(0+"");
			CommonUtils.filterSpecialWord(wrCollectDTO);
			wrCollectDao.updateWrCollectByTsId(wrCollectDTO);
		}
		
	}

	@Override
	public void saveStayInvoice(List<WrCollectDTO> dtoList) {
		if(null != dtoList){
			for (WrCollectDTO wrCollectDTO : dtoList) {
			    CommonUtils.filterSpecialWord(wrCollectDTO);
			    
			    if(StringUtils.isNotBlank(wrCollectDTO.getInvoiceNo())){
			    	List<InvoiceDto> invoiceByTsId = invoiceDao.getInvoiceByInvoiceNo(wrCollectDTO.getInvoiceNo(), wrCollectDTO.getSapCode());
			    	if(invoiceByTsId != null && invoiceByTsId.size() > 0){
			    		throw new ServiceBizException("该经销商已经上报过发票号，" + wrCollectDTO.getInvoiceNo() + "不能重复上报。");
			    	}
			    }
			    
			    
				wrCollectDao.updateWrCollectByTsId(wrCollectDTO);
			}
		}
		
	}

	@Override
	public void reportStayInvoice(String tsId, Long reporterId, String sapCode) {
		//判断是否已上报过
		List<InvoiceDto> invoiceByTsId = invoiceDao.getInvoiceByTsId(tsId, sapCode);
		if(invoiceByTsId != null && invoiceByTsId.size() > 0){
			throw new ServiceBizException("该经销商已经上报过该发票号，不能重复上报。");
		}
		
		List<WrCollectDTO> wrCollectByTsIds = wrCollectDao.getWrCollectByTsId(tsId);
		if(wrCollectByTsIds != null && !wrCollectByTsIds.isEmpty()){
			WrCollectDTO wrCollectDTO = wrCollectByTsIds.get(0);
			if(StringUtils.isBlank(wrCollectDTO.getExpressNo()) || StringUtils.isBlank(wrCollectDTO.getInvoiceNo())){
				throw new ServiceBizException("快递单号和发票号不能为空，请先保存。");
			}
		}
		
		//新增发票信息
		String[] tsIdArray = tsId.split(",");
		InvoiceDto dto = new InvoiceDto();
		dto.setFlowNo(CodeConstant.PROJECT_NAME+(new Date().getTime()));//tsId="FOL"+时间戳
		dto.setInvoiceTitle("上汽通用汽车销售有限公司");
		dto.setCreateBy(reporterId);
		dto.setUpdateBy(reporterId);
		CommonUtils.filterSpecialWord(dto);
		invoiceDao.insertInvoiceListInReport(tsIdArray, dto);
		
		
	}
}
