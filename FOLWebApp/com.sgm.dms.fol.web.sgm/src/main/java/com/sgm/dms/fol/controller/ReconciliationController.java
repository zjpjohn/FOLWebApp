/*
 * Copyright 2015 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : web.sgm
 *
 * @File name : ReconciliationController.java
 *
 * @Author : ZhangBao
 *
 * @Date : 2015年11月3日
 *
----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2015年11月3日    ZhangBao    1.0
 *
 *
 *
 *
----------------------------------------------------------------------------------
 */

package com.sgm.dms.fol.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sgm.dms.fol.common.api.exception.ServiceBizException;
import com.sgm.dms.fol.common.api.exception.VoNotValidException;
import com.sgm.dms.fol.common.service.controller.impl.BaseController;
import com.sgm.dms.fol.common.service.utils.AutoPojo;
import com.sgm.dms.fol.common.service.utils.BeanMapper;
import com.sgm.dms.fol.common.service.utils.DateUtil;
import com.sgm.dms.fol.common.service.utils.FileUtils;
import com.sgm.dms.fol.common.service.utils.MapUtil;
import com.sgm.dms.fol.common.service.utils.StringUtil;
import com.sgm.dms.fol.common.service.utils.SysException;
import com.sgm.dms.fol.dro.ExportBillDRO;
import com.sgm.dms.fol.dro.ExportBillDetailDRO;
import com.sgm.dms.fol.reconciliation.api.ReconciliationManagementService;
import com.sgm.dms.fol.reconciliation.dto.BillFileDTO;
import com.sgm.dms.fol.reconciliation.dto.BillFileDetailDTO;
import com.sgm.dms.fol.vo.BillFileDetailVo;
import com.sgm.dms.fol.vo.BillFileVo;
import com.sgm.dms.fol.vo.ReciliaTionVo;

/*
 *
 * @author ZhangBao
 * 对账处理controller
 * @date 2015年11月3日
 */
@Controller
@RequestMapping("/reconciliation/billFile")
public class ReconciliationController extends BaseController {
	protected Logger logger = LogManager.getLogger(this.getClass());
	
	@Autowired
	private ReconciliationManagementService reconciliationManagementService;

	/**
	 * 
	 * 导入对账单 大文件保存一条， 文件内容多条
	 *
	 * @author wangfl
	 * @version 
	 * @param reciliaTionVo
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping(value = "/importFile", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object importFile(@RequestBody@Valid ReciliaTionVo reciliaTionVo, BindingResult validResult) throws Throwable {
		logger.info("客户对账单文件导入开始....");
		
		//客户对账单CHECK
		check(validResult);
		
		//初始化文件数据
		BillFileDTO billFileDTO = initFileData(reciliaTionVo);

		//调用客户对账单文件导入SERVICE
		reconciliationManagementService.addImportReconFile(billFileDTO);

		logger.info("客户对账单文件导入结束");
		return true;
	}
	
	/** 
	 * 客户对账单CHECK
	 * @author DELL
	 * TODO description
	 * @date 2016年3月23日
	 * @param validResult
	 * @throws Exception
	 */
	private void check(BindingResult validResult) throws Exception{
	    if(validResult.hasErrors()){
	        throw(new VoNotValidException(super.getErrorMessages(validResult.getAllErrors())));
        }
	}
	

	/*****
	 * 导出对账单(大)
	 * 
	 * @throws ServiceBizException
	 * @throws SysException
	 * @throws IOException
	 * @throws SQLException 
	 */
	@RequestMapping(value = "/exportBillFile")
	public ResponseEntity<byte[]> exportBillFile(HttpServletRequest request)
			throws Throwable {
		BillFileVo vo = AutoPojo.bindRequestParam(request, BillFileVo.class);
		BillFileDTO bill = BeanMapper.map(vo, BillFileDTO.class);
		bill.setDbBillDate(DateUtil.strToDate(vo.getCurrentDate()));
		bill.setStatus(0);

		// 设置导出文件header
		String[] header = { "序号", "标题", "账单年月", "生效日期", "操作" };

		// 查询数据
		List<BillFileDTO> data = reconciliationManagementService
				.findDealerFileByFdAndPage(bill);

		// 转换为要导出的数据
		List<ExportBillDRO> expList = convertList(data);

		// 定义文件名称
		String fileName = "Bill_" + vo.getCurrentDate() + ".xls";
		// 导出文件
		ByteArrayOutputStream bos = reconciliationManagementService
				.<ExportBillDRO> exportXls4BillFile(expList, vo.getToken(),
						header, fileName);

		return new ResponseEntity<byte[]>(bos.toByteArray(),
				getHeaders(fileName), HttpStatus.CREATED);

	}

	/*****
	 * 导出对账单(小)
	 * 
	 * @throws ServiceBizException
	 * @throws SysException
	 * @throws IOException
	 * @throws SQLException 
	 */
	@RequestMapping(value = "/exportBillFileDetail")
	public ResponseEntity<byte[]> exportBillFileDetail(
			HttpServletRequest request) throws Throwable {
		BillFileDetailVo vo = AutoPojo.bindRequestParam(request,
				BillFileDetailVo.class);
		BillFileDetailDTO bill = BeanMapper.map(vo, BillFileDetailDTO.class);
		bill.setDbBillDate(DateUtil.strToDate(vo.getCurrentDate()));
		bill.setStatus(0);

		// 设置导出文件header
		String[] header = { "序号","客户代码","经销商名称", "标题", "账单年月", "生效日期", "签收状态","确认状态" };

		// 查询数据
		List<BillFileDetailDTO> data = reconciliationManagementService
				.findDealerFileDetailByFdAndPage(bill);

		// 转换为要导出的数据
		List<ExportBillDetailDRO> expList = BeanMapper.mapList(data,
				ExportBillDetailDRO.class);

		// 设置序号
		setLineNumber(expList);

		// 定义文件名称
		String fileName = "Bill_DETAIL_" + vo.getCurrentDate() + ".xls";

		// 导出文件
		ByteArrayOutputStream bos = reconciliationManagementService
				.<ExportBillDetailDRO> exportXls4BillFile(expList, vo.getToken(),
						header, fileName);

		return new ResponseEntity<byte[]>(bos.toByteArray(),
				getHeaders(fileName), HttpStatus.CREATED);

	}
	

	/*
	 * 
	 * @author ZhangBao 获取headers信息
	 * 
	 * @date 2015年11月13日
	 * 
	 * @param string
	 * 
	 * @return
	 */

	private HttpHeaders getHeaders(String fileName) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDispositionFormData("attachment", fileName);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return headers;
	}

	private List<ExportBillDRO> convertList(List<BillFileDTO> data) {
		List<ExportBillDRO> list = BeanMapper.mapList(data, ExportBillDRO.class);
		for (int i = 0; i < list.size(); i++) {
			ExportBillDRO v = list.get(i);
			v.setNum(i + 1);
			if ("0".equals(v.getStatus())) {
				v.setStatus("未发布");
			} else {
				v.setStatus("已发布");
			}
		}
		return list;
	}

	/**
	 * 查询对账单(大)
	 */
	@RequestMapping(value = "/queryBillFile", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getBillFile(@Validated @RequestBody BillFileVo vo) throws Throwable {	
		// 查询记录总数
		int total = findBillFIleTotal(vo);
		
		// 数据初始化
		BillFileDTO billFileDTO=initBillFileDTO(vo);

		// 查询数据
		List<BillFileDTO> list = statusConvertToName(reconciliationManagementService.findDealerFileByFdAndPage(billFileDTO));

		// 设置前台需要的数据
		Map<String, Object> responsedata=MapUtil.setQueryDataToMap(list, Long.valueOf(total));
		
		return responsedata;
	}
	
	/**
	 * 数据初始化
	 */
	private BillFileDTO initBillFileDTO(BillFileVo vo) throws Exception{
	    // 参数不为空转换
        BillFileDTO bill;
        if (null != vo) {
            bill = BeanMapper.map(vo, BillFileDTO.class);
            bill.setDbBillDate(DateUtil.strToDate(vo.getCurrentDate()));
        }else {
            bill=new BillFileDTO();
        }
        
        // 设置查询状态为未发布状态
        bill.setStatus(0);
        return bill;
	}

	/*
	 * 
	 * @author ZhangBao
	 * 
	 * @date 2015年11月13日
	 * 
	 * @param findDealerFileByFdAndPage
	 * 
	 * @return
	 */

	private List<BillFileDTO> statusConvertToName(List<BillFileDTO> list) {
		if (null != list && list.size() > 0) {
			for (BillFileDTO bill : list) {
				if (bill.getStatus() == 0) {
					bill.setStatusName("未发布");
				} else if (bill.getStatus() == 1) {
					bill.setStatusName("已发布");
				} else if (bill.getStatus() == 2) {
					bill.setStatusName("发布中");
				} else if (bill.getStatus() == 3) {
					bill.setStatusName("发布失败");
				}

				// 格式化日期
				bill.setBillDate(DateUtil.date2str((bill.getDbBillDate())));
				bill.setEffDate(DateUtil.date2str(bill.getEffectiveDate()));
			}
		}
		return list;
	}

	private int findBillFIleTotal(BillFileVo vo) throws ServiceBizException, SQLException {
		BillFileDTO bill;
		if (null != vo) {
			bill = BeanMapper.map(vo, BillFileDTO.class);
			bill.setDbBillDate(DateUtil.strToDate(vo.getCurrentDate()));
		}else{
			bill=new BillFileDTO();
		}

		// 设置查询状态为未发布状态
		bill.setStatus(0);
		bill.setEndNo(0);
		// 查询数据
		List<BillFileDTO> list = reconciliationManagementService
				.findDealerFileByFdAndPage(bill);
		return list.size();
	}

	private BillFileDTO initFileData(ReciliaTionVo vo) {
		BillFileDTO billFileDTO = new BillFileDTO();
	    billFileDTO.setFileName(vo.getFileName());
		billFileDTO.setFileUrl(StringUtil.getFileUrl(vo.getFilePath(), "?token"));
		Date now = new Date();
		billFileDTO.setTitle("BILL_" + vo.getBillDate());
		billFileDTO.setCreateBy(-1);
		billFileDTO.setCreateDate(now);
		billFileDTO.setUpdateBy(-1);
		billFileDTO.setUpdateDate(now);
		billFileDTO.setStatus(0);
		billFileDTO.setFiledId(vo.getFiledId());
		billFileDTO.setEffectiveDate(DateUtil.strToDateFormat(vo
				.getEffectDate()));
		billFileDTO.setDbBillDate(DateUtil.strToDateFormat(vo.getBillDate()));
		return billFileDTO;

	}
	

	/**
	 * 
	 * @author wangfl
	 * 修改文件发布状态为发布中
	 * @date 2016年4月12日
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping("/publishFileStatus")
    @ResponseBody
	public Object publishFileStatus(@RequestBody List<BillFileVo> billVo) throws SQLException {

		// 验证请求Vo数据
		checkPublishFileData(billVo);

		//修改文件发布状态为发布中
		for (BillFileVo billFileVo : billVo) {
			reconciliationManagementService.publishFIleStatus(billFileVo.getId());
		}

		return true;
	}

	/**
	 * 
	 * 发布文件(大)
	 *
	 * @author wangfl
	 * @version 
	 * @param billVo
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping(value = "/publishFile", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object publishFile(@Validated @RequestBody List<BillFileVo> billVo, HttpServletRequest request) throws Throwable {
	    // 验证数据
	    checkPublishFileData(billVo);
		
		//数据转换
		List<BillFileDTO> billFileDetailDTO = BeanMapper.mapList(billVo,BillFileDTO.class);
		setStatus(billFileDetailDTO, 1);
		
		//发布主逻辑
		reconciliationManagementService.publishFIle(billFileDetailDTO, request);

		return true;
	}
	
	/**
	 * 验证数据(publishFile)
	 */
	private void checkPublishFileData(List<BillFileVo> billVo) throws VoNotValidException{
	    
        if (null == billVo || billVo.size() == 0) {
            throw new ServiceBizException("请选择要发布的文件");
        }
	}

	/*
	 * 
	 * @author ZhangBao TODO description
	 * 
	 * @date 2015年11月13日
	 * 
	 * @param mapList
	 * 
	 * @param i
	 */

	private void setStatus(List<BillFileDTO> mapList, int i) {
		if (null != mapList && mapList.size() > 0) {
			for (BillFileDTO bill : mapList) {
				bill.setStatus(i);
				bill.setUpdateBy(-1);
				bill.setUpdateDate(new Date());
			}

		}
	}

	/*****
	 * 取消发布文件
	 * @throws SQLException 
	 * @throws ServiceBizException 
	 */
	@RequestMapping(value = "/unPublishFile", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object unPublishFile(@Validated @RequestBody List<BillFileVo> billVo)
			throws Throwable {
	    //数据转换
		List<BillFileDTO> billFileDetailDTO = BeanMapper.mapList(billVo,BillFileDTO.class);
		setStatus(billFileDetailDTO, 0);
		
		//取消发布文件SERVICE
		reconciliationManagementService.updateBillFileStatusById(billFileDetailDTO);
		
		return true;
	}

	private void setLineNumber(List<ExportBillDetailDRO> expList) {
		if (null != expList && expList.size() > 0) {
			for (int i = 0; i < expList.size(); i++) {
				ExportBillDetailDRO vo = expList.get(i);
				vo.setNum(i + 1);
				if ("0".equals(vo.getStatus())) {
					vo.setStatus("未签收");
				} else {
					vo.setStatus("已签收");
				}
			}
		}

	}

	/****
	 * 根据大文件billdate查询子文件
	 * @throws SQLException 
	 * @throws ServiceBizException 
	 */
	@RequestMapping(value = "/queryBillFileDetail", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryBillFileDetail(@RequestBody BillFileDetailVo billfill) throws Throwable {

	    //初始化数据
	    BillFileDetailDTO billFileDetailDTO=initBillFileDeatilDTO(billfill);
	    
	    //数据库查询数据
	    List<BillFileDetailDTO> billFileDetailDTOList = convertToStatusName(reconciliationManagementService.findDealerFileDetailByFdAndPage(billFileDetailDTO));
	    
	    //查询出总数
        Long total = Long.valueOf(findBillFileDetailCount(billfill));
        
        //设置成前台需要的数据
        Map<String, Object> responsedata=MapUtil.setQueryDataToMap(billFileDetailDTOList, total);
		
        return responsedata;
	}

	/**
	 * 到数据库查询对账单
	 * 
	 * @param billFileDetailDTO
	 * @return
	 * @throws SQLException 
	 * @throws ServiceBizException 
	 */
	private BillFileDetailDTO initBillFileDeatilDTO(BillFileDetailVo vo) throws Exception {
    	BillFileDetailDTO billDto = BeanMapper.map(vo, BillFileDetailDTO.class);
    	billDto.setDbBillDate(DateUtil.strToDate(vo.getCurrentDate()));
		return billDto;
	}

	/**
	 * 
	 * @author wangfl
	 * 对账单明细导出
	 * @date 2016年4月12日
	 * @param filedId
	 * @param token
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/exportBillFileDetailSingle")
    public ResponseEntity<byte[]> exportBillFileDetail(@RequestParam("filedId") String filedId, @RequestParam("token") String token) throws IOException {
        ByteArrayOutputStream bos= (ByteArrayOutputStream) FileUtils.downLoadStream(filedId, token);
        HttpHeaders headers = new HttpHeaders();    
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);    
        headers.setContentDispositionFormData("attachment", "Bill_DETAIL_"+ DateUtil.formartDate(new Date())+".txt");    
        return new ResponseEntity<byte[]>(bos.toByteArray(), headers, HttpStatus.CREATED);    

    }
	
	private int findBillFileDetailCount(BillFileDetailVo bill) throws ServiceBizException, SQLException {
		BillFileDetailDTO billDto = BeanMapper.map(bill,
				BillFileDetailDTO.class);
		billDto.setDbBillDate(DateUtil.strToDate(bill.getCurrentDate()));
		int total = 0;
		billDto.setEndNo(0);
		List<BillFileDetailDTO> data = reconciliationManagementService
				.findDealerFileDetailByFdAndPage(billDto);
		total = data.size();
		return total;
	}

	private List<BillFileDetailDTO> convertToStatusName(
			List<BillFileDetailDTO> list) {
		if (null != list && list.size() > 0) {
			for (BillFileDetailDTO dto : list) {
				if (0 == dto.getStatus()) {
					dto.setStatusName("未签收");
				} else {
					dto.setStatusName("已签收");
				}
				dto.setBillDate(DateUtil.date2str(dto.getDbBillDate()));
				dto.setEffDate(DateUtil.date2str(dto.getEffectiveDate()));
			}
		}
		return list;
	}

}
