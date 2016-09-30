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
 * @Author : A BAO
 *
 * @Date : 2015年10月20日
 *
----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2015年12月8日    A BAO    1.0
 *
 *
 *
 *
----------------------------------------------------------------------------------
 */

package com.sgm.dms.fol.controller;

import java.io.ByteArrayOutputStream;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sgm.dms.fol.bonus.api.BonusIssueCheckService;
import com.sgm.dms.fol.bonus.api.BonusIssueService;
import com.sgm.dms.fol.bonus.dto.BonusIssueFileDTO;
import com.sgm.dms.fol.bonus.dto.BonusIssueRecordDTO;
import com.sgm.dms.fol.common.api.constants.POConstant;
import com.sgm.dms.fol.common.api.domain.CodeDTO;
import com.sgm.dms.fol.common.api.exception.ServiceBizException;
import com.sgm.dms.fol.common.api.exception.VoNotValidException;
import com.sgm.dms.fol.common.api.services.CodeService;
import com.sgm.dms.fol.common.service.controller.impl.BaseController;
import com.sgm.dms.fol.common.service.utils.AutoPojo;
import com.sgm.dms.fol.common.service.utils.BeanMapper;
import com.sgm.dms.fol.common.service.utils.CookieUtil;
import com.sgm.dms.fol.common.service.utils.DateUtil;
import com.sgm.dms.fol.common.service.utils.MapUtil;
import com.sgm.dms.fol.common.service.utils.StringUtil;
import com.sgm.dms.fol.dro.BonusIssueDetailDRO;
import com.sgm.dms.fol.dro.BonusIssueImportDro;
import com.sgm.dms.fol.reconciliation.api.ReconciliationManagementService;
import com.sgm.dms.fol.vo.BonusIssueAgainExecuteVo;
import com.sgm.dms.fol.vo.BonusIssueDataVo;
import com.sgm.dms.fol.vo.BonusIssueVo;
import com.sgm.solution.framework.urlaccess.annotations.ValidationRequestUrl;

/*
 * 奖金颁发controller
 * @author zhang bao
 * @date 2015年12月10日
*/ 
@Controller
@RequestMapping("/bonus/bonusissue")
public class BonusIssuesController extends BaseController {
	// 日志
	protected Logger logger = LogManager.getLogger(this.getClass());
	@Autowired
	private BonusIssueService bonusService;
	@Autowired
	private BonusIssueCheckService bonusIssueCheckService;
	@Autowired
	private CodeService codeService;
	@Autowired
	private ReconciliationManagementService reconciliationManagementService;

	/**
	 * 查询奖金发放版本上传(审核 发放列表)
	 * 
	 * @throws SQLException
	 */
	@RequestMapping(value = "/query", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ValidationRequestUrl
	@ResponseBody
	public Object getBonusIssueFile(@RequestBody BonusIssueVo bonusIssueVo) throws Throwable {
		
	    //初始化查询数据
	    BonusIssueFileDTO bonusIssueFileDTO=initBonusIssueFileDTO(bonusIssueVo);
			
		//查询数据
		List<BonusIssueFileDTO> bonusList = bonusService.findBonusIssueFileByWhs(bonusIssueFileDTO);
			
		//数据转换
		List<BonusIssueDataVo> list=convertData(bonusList);
		
		//设置前台需要的对象
		Map<String, Object> responsedata=MapUtil.setQueryDataToMap(list, Long.valueOf(getTotalBonusIssueFile(bonusIssueVo)));
			
		return responsedata;
	}
	
	/** 
	 * 数据初始化(getBonusIssueFile)
	 * @param bonusIssueVo
	 * @return
	 * @throws Exception
	 */
	private BonusIssueFileDTO initBonusIssueFileDTO(BonusIssueVo bonusIssueVo) throws Exception{
	    BonusIssueFileDTO bonusIssueFileDTO=BeanMapper.map(bonusIssueVo, BonusIssueFileDTO.class);
        
        //设置状态为有效
	    bonusIssueFileDTO.setStatus(POConstant.IS_STATUS);
        return bonusIssueFileDTO;
    }

	/**
	 * 获取总记录数(getBonusIssueFile)
	 * @param bonusVo
	 * @return
	 * @throws ServiceBizException
	 */
	private int getTotalBonusIssueFile (BonusIssueVo bonusVo)  throws ServiceBizException{
		BonusIssueFileDTO bonus=new BonusIssueFileDTO();
		bonus.setStatus(POConstant.IS_STATUS);
		int total=0;
		if(null!=bonusVo){
			bonus.setBatchNo(bonusVo.getBatchNo());
			bonus.setDealerType(bonusVo.getDealerType());
			bonus.setBonusType(bonusVo.getBonusType());
			bonus.setBeginDate(bonusVo.getBeginDate());
			bonus.setEndDate(bonusVo.getEndDate());
			bonus.setType(bonusVo.getType());
		}
		total=bonusService.findBonusIssueFileCountByWhs(bonus);
		return total;
	}

	/**
	 * 数据转换(getBonusIssueFile)
	 * @param bonusList
	 * @return
	 * @throws Exception
	 */
	private List<BonusIssueDataVo> convertData(List<BonusIssueFileDTO> bonusList) throws Exception {
		//转换为页面需要的显示
		List<BonusIssueDataVo> list=new ArrayList<BonusIssueDataVo>();
		
		CodeDTO code=null;
		if(null!=bonusList&&bonusList.size()>0){
			List<BonusIssueDataVo> data=BeanMapper.mapList(bonusList, BonusIssueDataVo.class);
			for(BonusIssueDataVo vo:data){
				if(null!=vo){
					//上传日期
					vo.setUploadDate(DateUtil.date2str(vo.getCreateDate()));
					CodeDTO dataCode=null;
					code=new CodeDTO();
					if(!StringUtil.isEmpty(vo.getDealerType())){
						code.setCode(vo.getDealerType()==null?null:String.valueOf(vo.getDealerType()));
					
						code.setType(String.valueOf(POConstant.DEALER_TYPE));
						
						//dealerType 转义
						dataCode=codeService.getcodeCnDescByCodeAndTypeName(code);
						
						if(null!=dataCode&&!StringUtil.isEmpty(dataCode.getCodeCnDesc())){
							String dealerTypeName=dataCode.getCodeCnDesc();
							vo.setDealerTypeName(dealerTypeName);
						}
					}
					
					//颁发日期
					vo.setPublishDate(new Timestamp(vo.getCreateDate().getTime()).toString().substring(0, 10));
					
					//bonusType 转义 
					if(!StringUtil.isEmpty(vo.getBonusType())){
						code.setCode(String.valueOf(vo.getBonusType()));
					
						code.setType(String.valueOf(POConstant.TYPE_NAME_CODE_BONUS));
						dataCode=codeService.getcodeCnDescByCodeAndTypeName(code);
						
						if(null!=dataCode&&!StringUtil.isEmpty(dataCode.getCodeCnDesc())){
							vo.setBonusTypeName(dataCode.getCodeCnDesc());
						}
					}
					//matchstatus 转义
					if(!StringUtil.isEmpty(vo.getMatchState())){
						code.setCode(String.valueOf(vo.getMatchState()));
					
						code.setType(String.valueOf(POConstant.MATCH_STATE));
						dataCode=codeService.getcodeCnDescByCodeAndTypeName(code);
//						String matchStateName=dataCode.getCodeCnDesc(); 
						if(null!=dataCode&&!StringUtil.isEmpty(dataCode.getCodeCnDesc())){
							vo.setMatchStateName(dataCode.getCodeCnDesc());
						}
					}
					
					//issue_status 转义
					if(!StringUtil.isEmpty(vo.getIssueStatus())){
						code.setCode(String.valueOf(vo.getIssueStatus()));
					
						code.setType(String.valueOf(POConstant.PAY_BONUS_STATE));
						dataCode=codeService.getcodeCnDescByCodeAndTypeName(code);
//						String issueStatusName=dataCode.getCodeCnDesc();
						if(null!=dataCode&&!StringUtil.isEmpty(dataCode.getCodeCnDesc())){
							vo.setIssueStatusName(dataCode.getCodeCnDesc());
						}
					}
					list.add(vo);
				}
			}
		}
		return list;
		
	}
	
	
	/**
	 * 
	 * 下载模板
	 *
	 * @author wangfl
	 * @version 
	 * @param token
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping(value = "/downloadTemp")
	public ResponseEntity<byte[]> downloadTemp(String token) throws Throwable {
		
		// 定义文件名称
		String fileName = "bonusissue_Template.csv";
				
		// 设置导出文件header
		String[] headers = { "ASC编号", "流水号", "备注", "销售公司","奖金1", "奖金2"};
		
		//模板事例数据
		List<BonusIssueImportDro> exampleDataList = new ArrayList<BonusIssueImportDro>();
		BonusIssueImportDro dro = new BonusIssueImportDro("7700013","6","模板事例数据","SERS","1","2");
		exampleDataList.add(dro);
		
		
		// 导出文件
		//ByteArrayOutputStream bos = reconciliationManagementService.<BonusIssueImportDro> exportXls4BillFile(exampleDataList, token,  header, fileName);
		ByteArrayOutputStream bos = reconciliationManagementService.<BonusIssueImportDro>exportCsvFile(headers, exampleDataList, fileName, token);

	    return new ResponseEntity<byte[]>(bos.toByteArray(),getHeaders(fileName), HttpStatus.CREATED);
	}
	/**
	 * 导入奖金发放版本
	 * @param 
	 * @throws SQLException
	 */
    @RequestMapping(value = "/importFile", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object importFile(@Valid @RequestBody BonusIssueVo vo, BindingResult validResult,HttpServletRequest request)
			throws Throwable {
		logger.info("开始奖金发放文件导入....");
		
		//上传前CHECK数据
		bonusIssueCheckService.beforeUploadBonusCheckData(validResult);
		
		//初始化 BonusIssueFileDTO
		BonusIssueFileDTO bonusDto=setBonusIssueFileDTO(vo);
		
		//调用新增奖金Service
		bonusService.addImportBonusIssueFile(bonusDto,vo.getToken(),vo.getFiledId(),getUserId(request));
			
		logger.info("奖金发放结束");
		return true;
	}
	
    /**
     * 初始化 BonusIssueFileDTO（importFile）
     */
	private BonusIssueFileDTO setBonusIssueFileDTO(BonusIssueVo vo){
	    BonusIssueFileDTO bonusIssueFileDTO;
	    //重新上传
        if(null!=vo.getData()){
            bonusIssueFileDTO=BeanMapper.map(vo.getData(), BonusIssueFileDTO.class);
            bonusIssueFileDTO.setBonusType(vo.getBonusType());
            bonusIssueFileDTO.setDealerType(vo.getDealerType());
        }else{
            //直接上传
            bonusIssueFileDTO=BeanMapper.map(vo, BonusIssueFileDTO.class);
            
        }
        
        return bonusIssueFileDTO;
	}
	
	
	
	/**
	 * 作废奖金发放版本
	 * @param bonusIssueVos
	 * @param request
	 * @throws SQLException
	 */
    @RequestMapping(value = "/cancalBonusFile", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object cancalBonusFile(@RequestBody List<BonusIssueVo> bonusIssueVos,HttpServletRequest request)
			throws Throwable {
		//数据批量转换
        List<BonusIssueFileDTO> dto=BeanMapper.mapList(bonusIssueVos, BonusIssueFileDTO.class);
        
        //数据库更新数据
		bonusService.updateBonusIssueFile(dto,getUserId(request));
		
		return true;
	}
	
    /**
	 * 奖金文件审核
	 * @param bonusIssueVos
	 * @param request
	 * @throws SQLException
	 */	
	@RequestMapping(value = "/bonusAudit", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object bonusAudit(@RequestBody List<BonusIssueVo> bonusIssueVos,HttpServletRequest request) throws Throwable{
	    //数据批量转换
	    List<BonusIssueFileDTO> bonusIssueFileDTOs=BeanMapper.mapList(bonusIssueVos, BonusIssueFileDTO.class);
	    
	    //数据库更新数据
	    bonusService.updateBonusAuditState(setBonusAuditState(bonusIssueFileDTOs,request,POConstant.MATCH_STATE_SUC));

	    return true; 
	}
	
	/** 
	 * 确认需要审核
	 * @param bonusIssueVos
	 * @param request
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping(value = "/confirmAudit", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object confirmAudit(@RequestBody List<BonusIssueVo> bonusIssueVos,HttpServletRequest request) throws Throwable{
	    //数据批量转换
        List<BonusIssueFileDTO> bonusIssueFileDTOs=BeanMapper.mapList(bonusIssueVos, BonusIssueFileDTO.class);

        //数据库更新数据
        bonusService.updateBonusAuditState(setBonusAuditState(bonusIssueFileDTOs,request,POConstant.WAIT_MATCH_STATE));

        return true; 
	}
	
	/**
	 * 设置奖金的审核状态
	 */
	private List<BonusIssueFileDTO> setBonusAuditState(List<BonusIssueFileDTO> bonusIssueFileDTOs,HttpServletRequest request,Integer matchState) throws Exception{
	    for (BonusIssueFileDTO bonusIssueFileDTO : bonusIssueFileDTOs) {
	        bonusIssueFileDTO.setCommonUpdateData(CookieUtil.getUserId(request));
	        bonusIssueFileDTO.setMatchState(matchState);
        }
	    
	    return bonusIssueFileDTOs;
	}
	
    /**
     * 奖金驳回
     */
    @RequestMapping("/bonusReject")
    @ResponseBody
    public Object bonusReject(@RequestBody List<BonusIssueVo> bonusIssueVos,HttpServletRequest request) throws Throwable{
        //数据批量转换
        List<BonusIssueFileDTO> bonusIssueFileDTOs=BeanMapper.mapList(bonusIssueVos, BonusIssueFileDTO.class);
        
        //数据库更新数据
        bonusService.updateBonusAuditState(setBonusAuditState(bonusIssueFileDTOs,request,POConstant.UN_MATCH_STATE));

        return true;
    }
	
	
	
	/**
	 * 发放奖金
	 * 
	 * @throws SQLException
	 */
    @RequestMapping(value = "/payBonus", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object payBonus(@RequestBody BonusIssueVo vo,HttpServletRequest request)
   			throws Throwable {
        //验证参数
        beforePayBonusCheckData(vo);

   		logger.info("发放奖金开始");

   		//发放奖金主逻辑
   		boolean result=bonusService.payBonus(vo.getBatchNo(),getUserId(request));

   		//发放结果判断
   		swtichResult(result);
   		
   		logger.info("奖金发放结束。");
   		return true;
   	}
    
    private void swtichResult(boolean result) throws ServiceBizException{
        if(!result){
            throw new ServiceBizException("奖金发放失败");
        }
    }
    
    /**
     * 发放奖金前CHECK
     */
    private void beforePayBonusCheckData(BonusIssueVo vo) throws VoNotValidException{
        if(null==vo||StringUtil.isEmpty(vo.getBatchNo())){
            throw(new VoNotValidException("没有奖金批次号"));
        }
    }

    /**
	 *重处理
	 * 
	 * @throws SQLException
	 */
    @RequestMapping(value = "/payAgainBonus", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object payAgainBonus(@RequestBody List<BonusIssueVo> vo,HttpServletRequest request) throws Throwable {
    	//验证参数
    	beforePayAgainBonusCheckData(vo);
    	
   		logger.info("发放奖金开始");
   		
   		//转换奖金记录
   		List<BonusIssueRecordDTO> record=BeanMapper.mapList(vo, BonusIssueRecordDTO.class);
   		
   		//奖金重新处理
   		bonusService.payAgainBonus(record,getUserId(request));
   			
   		logger.info("奖金发放结束");
   		return true;
   	}

    /**
     * 重处理发放奖金前CHECK
     */
    private void beforePayAgainBonusCheckData(List<BonusIssueVo> volist) throws VoNotValidException{
        
        if(null==volist||0==volist.size()){
            throw(new VoNotValidException("请选择奖金记录再重处理"));
        }
    }
	
	
	/**
	 * 颁发记录(成功发放的记录)
	 * 
	 * @throws SQLException
	 */
    @RequestMapping(value = "/queryBonusIssueSuc", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ValidationRequestUrl
	@ResponseBody
	public Object getBonusIssueSuc(@RequestBody BonusIssueVo bonusVo) throws Throwable {
        
        //数据初始化
        BonusIssueFileDTO bonusIssueFileDTO=initBonusIssueFileDTOWithGetBonusIssueSuc(bonusVo);
			
        //查询
		List<BonusIssueFileDTO> bonusList = bonusService.findBonusPublishedFileByWhs(bonusIssueFileDTO);
			
		//批量转换VO
		List<BonusIssueDataVo> list=convertData(bonusList);
			
		//设置前台需要返回的值
		Map<String, Object> responsedata=MapUtil.setQueryDataToMap(list, Long.valueOf(getTotalBonusPublishedFile(bonusVo)));
			
		return responsedata;
	}

    /**
     * init BonusIssueFileDTO with getBonusIssueSuc
     */
	private BonusIssueFileDTO initBonusIssueFileDTOWithGetBonusIssueSuc(BonusIssueVo bonusVo){
	    //数据类型转换
        BonusIssueFileDTO dto=BeanMapper.map(bonusVo, BonusIssueFileDTO.class);
            
        //设置状态为有效
        dto.setStatus(POConstant.IS_STATUS);
        return dto;
	}
	
	
	/**
	 *导出奖金颁发明细
	 * @throws Exception 
	 * 
	 * @throws SQLException
	 */
    @RequestMapping("/exportFile")
	public ResponseEntity<byte[]> exportAmountReconcileResult(
			HttpServletRequest request) throws Throwable {
		BonusIssueVo vo = AutoPojo.bindRequestParam(request,BonusIssueVo.class);
		BonusIssueRecordDTO bonusIssueRecordDTO = BeanMapper.map(vo, BonusIssueRecordDTO.class);
		// 设置标题
		String[] header = { "序号", "客户代码", "批次号","销售 公司", "渠道 类型","奖金 类型","颁发金额" ,"颁发日期" };
		// 查询数据
		List<BonusIssueRecordDTO> data = bonusService.findBonusPublishFileDetailByBatchNo(bonusIssueRecordDTO.getBatchNo(),bonusIssueRecordDTO.getIssueStatus());

		// 转换为要导出的数据
		List<BonusIssueDetailDRO> expList = BeanMapper.mapList(data,BonusIssueDetailDRO.class);
		
		//设置序号
		setLineNumber(expList);
		
		StringUtil.fontFormatFinance(expList);
		
		// 定义文件名称
		String fileName = "bonus_issue_" + DateUtil.date2str(new Date())+ ".xls";
		// 导出
		ByteArrayOutputStream bos = reconciliationManagementService.<BonusIssueDetailDRO> exportXls4BillFiles(expList,vo.getToken(), header, fileName);
		return new ResponseEntity<byte[]>(bos.toByteArray(),getHeaders(fileName), HttpStatus.CREATED);

	}
	
	
    private void setLineNumber(List<BonusIssueDetailDRO> expList) {
		if(null!=expList&&expList.size()>0){
			int index=1;
			for(BonusIssueDetailDRO record:expList){
				record.setNum(index);
				index++;
			}
		}
		
	}


	/**
	 *奖金重处理查询(奖金发放失败和sap校验未通过的)
	 * 
	 * @throws SQLException
	 */
    @RequestMapping(value = "/queryBonusIssueFail", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
   	@ValidationRequestUrl
   	@ResponseBody
   	public Object getBonusIssueFail(@RequestBody BonusIssueAgainExecuteVo bonusVo) throws Throwable {
        //数据初始化
        BonusIssueRecordDTO bonusIssueRecordDTO=initBonusIssueRecordDTOWithgetBonusIssueFail(bonusVo);
   			
   		//查询
   		List<BonusIssueRecordDTO> bonusList = bonusService.findBonusIssueAgainExecuteByWhs(bonusIssueRecordDTO);

   		//设置返回对象
   		Map<String, Object> responsedata=MapUtil.setQueryDataToMap(bonusList, Long.valueOf(getTotalBonusIssueAgainExecute(bonusVo)));
   		
   		return responsedata;
   	}

    /**
     * init BonusIssueRecordDTO with getBonusIssueFail
     */
    private BonusIssueRecordDTO initBonusIssueRecordDTOWithgetBonusIssueFail(BonusIssueAgainExecuteVo bonusVo){
        //数据类型转换
        BonusIssueRecordDTO bonusIssueRecordDTO=BeanMapper.map(bonusVo, BonusIssueRecordDTO.class);
        
        //设置状态为有效
        bonusIssueRecordDTO.setStatus(POConstant.IS_STATUS);
        return bonusIssueRecordDTO;
    }
	
	private int getTotalBonusIssueAgainExecute(BonusIssueAgainExecuteVo bonusVo) {
		int total=0;
		BonusIssueRecordDTO record=new BonusIssueRecordDTO();
		if(null!=bonusVo){
			record.setStatus(POConstant.IS_STATUS);
			record.setBatchNo(bonusVo.getBatchNo());
			record.setBonusType(bonusVo.getBonusType());
			record.setServ(bonusVo.getServ());
			record.setSapCode(bonusVo.getSapCode());
			total=bonusService.findBonusIssueAgainExecuteCountByWhs(record);
		}
		return total;
	}


	private int getTotalBonusPublishedFile(BonusIssueVo bonusVo) {
		BonusIssueFileDTO bonus=new BonusIssueFileDTO();
		bonus.setStatus(POConstant.IS_STATUS);
		int total=0;
		if(null!=bonusVo){
			bonus.setBatchNo(bonusVo.getBatchNo());
			bonus.setBeginDate(bonusVo.getBeginDate());
			bonus.setEndDate(bonusVo.getEndDate());
		}
		total=bonusService.findBonusPublishedFileCountByWhs(bonus);
		return total;
	}


	/*****
	 * 获取当前用户id
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static Long  getUserId(HttpServletRequest request) throws Exception{
		Long userId = CookieUtil.getUserId(request);
		if(StringUtil.isEmpty(userId)){
			throw new ServiceBizException("获取用户信息失败");
		}
		return userId;
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
