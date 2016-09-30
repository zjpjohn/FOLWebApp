package com.sgm.dms.fol.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sgm.dms.fol.bankTicket.api.BankTicketAuditFlowService;
import com.sgm.dms.fol.bankTicket.api.BankTicketLimitAmountService;
import com.sgm.dms.fol.bankTicket.dto.BankTicketLimitAmountDTO;
import com.sgm.dms.fol.common.api.constants.ExceptionConstants;
import com.sgm.dms.fol.common.api.constants.POConstant;
import com.sgm.dms.fol.common.api.constants.StateConstant;
import com.sgm.dms.fol.common.api.constants.TypeConstant;
import com.sgm.dms.fol.common.api.domain.ResponseDTO;
import com.sgm.dms.fol.common.api.exception.ServiceBizException;
import com.sgm.dms.fol.common.service.controller.impl.BaseController;
import com.sgm.dms.fol.common.service.utils.BeanMapper;
import com.sgm.dms.fol.common.service.utils.CookieUtil;
import com.sgm.dms.fol.common.service.utils.MapUtil;
import com.sgm.dms.fol.common.service.utils.RSAUtil;
import com.sgm.dms.fol.common.service.utils.StringUtil;
import com.sgm.dms.fol.vo.BankTicketLimitAmountVo;
import com.sgm.dms.fol.vo.BankTicketLimitVerifyVo;
import com.sgm.solution.framework.urlaccess.annotations.ValidationRequestUrl;

/**
 * 银票限额审核Controllor
 * 
 * @author lujinglei
 *
 */
@Controller
@RequestMapping("/bankTicketLimitAmountVerify/limits")
public class BankTicketLimitAmountVerifyControllor extends BaseController {
	// 日志
	protected Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private BankTicketLimitAmountService bankTicketLimitAmountService;
	@Autowired
	private BankTicketAuditFlowService bankTicketAuditFlowService;

	/** 
	 * 查询品牌的银票限额
	 * @author DELL
	 * TODO description
	 * @date 2016年3月24日
	 * @param bankTicketLimitVerifyVo
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping("/queryBrand")
	@ResponseBody
	public Object queryBankTicketDeadLineWithBrand(@RequestBody BankTicketLimitVerifyVo bankTicketLimitVerifyVo)
			throws Throwable {
		// 初始化数据
		BankTicketLimitAmountDTO bankTicketLimitAmountDTO=initBankTicketLimitAmountDTO(bankTicketLimitVerifyVo,TypeConstant.BANK_TICKET_LIMIT_AMOUNT_TYPE);
		
		// 查询品牌的银票限额
		List<BankTicketLimitAmountDTO> amountLimitList = bankTicketLimitAmountService.findBankTicketLimitAmountByWhere(bankTicketLimitAmountDTO);
		
		// 批量转换成VO
		List<BankTicketLimitAmountVo> bankTicketLimitAmountVoList=BeanMapper.mapList(amountLimitList, BankTicketLimitAmountVo.class);
		
		// 转换成财务专用格式
		StringUtil.fontFormatFinance(bankTicketLimitAmountVoList);
		
		// 设置成前台专用的格式
		Map<String, Object> responsedata=MapUtil.setQueryDataToMap(bankTicketLimitAmountVoList, Long.valueOf(bankTicketLimitAmountService.findBankTicketLimitAmountCountByWhere(bankTicketLimitAmountDTO)));
		
		return responsedata;
	}
	
	private BankTicketLimitAmountDTO initBankTicketLimitAmountDTO(BankTicketLimitVerifyVo bankTicketLimitVerifyVo,int type) throws Exception{
	    BankTicketLimitAmountDTO bankTicketLimitAmountDTO = BeanMapper.map(bankTicketLimitVerifyVo,BankTicketLimitAmountDTO.class);
	    // 设置审核状态
	    List<Integer> auditStatusList = new ArrayList<Integer>();
	    auditStatusList.add(POConstant.BANK_TICKET_ADD_UN_AUDIT);
	    auditStatusList.add(POConstant.BANK_TICKET_UPDATE_UN_AUDIT);
	    auditStatusList.add(POConstant.BANK_TICKET_DELETE_UN_AUDIT);
	    bankTicketLimitAmountDTO.setAuditStatusList(auditStatusList);
	    
	    // 设置类型
	    bankTicketLimitAmountDTO.setType(type);
	    return bankTicketLimitAmountDTO;
	}

	/** 
     * 查询特殊经销商的银票限额
     * @author DELL
     * TODO description
     * @date 2016年3月24日
     * @param bankTicketLimitVerifyVo
     * @return
     * @throws Throwable
     */
	@RequestMapping("/querySpecialDealer")
	@ResponseBody
	public Object queryBankTicketLimitVerifyWithSpecialDealer(@RequestBody BankTicketLimitVerifyVo bankTicketLimitVerifyVo) throws Throwable {
		// 初始化数据
        BankTicketLimitAmountDTO bankTicketLimitAmountDTO=initBankTicketLimitAmountDTO(bankTicketLimitVerifyVo,TypeConstant.BANK_TICKET_SPECIAL_DEALER_TYPE);
		
        // 查询品牌的银票限额
        List<BankTicketLimitAmountDTO> amountLimitList = bankTicketLimitAmountService.findBankTicketLimitAmountByWhere(bankTicketLimitAmountDTO);
        
        // 批量转换成VO
        List<BankTicketLimitAmountVo> bankTicketLimitAmountVoList=BeanMapper.mapList(amountLimitList, BankTicketLimitAmountVo.class);
        
        // 转换成财务专用格式
        StringUtil.fontFormatFinance(bankTicketLimitAmountVoList);
        
        // 设置成前台专用的格式
        Map<String, Object> responsedata=MapUtil.setQueryDataToMap(bankTicketLimitAmountVoList, Long.valueOf(bankTicketLimitAmountService.findBankTicketLimitAmountCountByWhere(bankTicketLimitAmountDTO)));

        return responsedata;
	}

	/**
	 * 审批通过
	 * 
	 * @param bankBankTicketLimitVerifyVo
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/auditSuccess", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	@ValidationRequestUrl
	public Object auditSuccess(@RequestBody List<BankTicketLimitVerifyVo> bankTicketLimitVerifyVoList, HttpServletRequest request)
			throws Throwable {
		//初始化数据
		List<BankTicketLimitAmountDTO> bankTicketLimitAmountDTOList=initBankTicketLimitVerifyVoList(bankTicketLimitVerifyVoList);
		
		//调用审核通过SERVICE
		bankTicketLimitAmountService.bankTicketLimitAmountAuditSuccess(bankTicketLimitAmountDTOList, CookieUtil.getUserId(request));
		
		return true;
	}
	
	/** 
	 * 数据初始化
	 * @author DELL
	 * TODO description
	 * @date 2016年3月24日
	 * @throws Exception
	 */
	private List<BankTicketLimitAmountDTO> initBankTicketLimitVerifyVoList(List<BankTicketLimitVerifyVo> bankTicketLimitVerifyVoList) throws Exception{
	        for (BankTicketLimitVerifyVo bankTicketLimitVerifyVo : bankTicketLimitVerifyVoList) {
	        bankTicketLimitVerifyVo.setAmountLimit(null);
	        }
	    List<BankTicketLimitAmountDTO> bankTicketLimitAmountDTOList=BeanMapper.mapList(bankTicketLimitVerifyVoList, BankTicketLimitAmountDTO.class);
	    
	        return bankTicketLimitAmountDTOList;

	}

	/**
	 * 驳回
	 * 
	 * @param bankTicketLimitVerifyVo
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/reject", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	@ValidationRequestUrl
	public Object reject(@RequestBody List<BankTicketLimitVerifyVo> bankTicketLimitVerifyVoList, HttpServletRequest request)
			throws Throwable {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			if(null != bankTicketLimitVerifyVoList){
				for (BankTicketLimitVerifyVo bankTicketLimitVerifyVo : bankTicketLimitVerifyVoList) {
					int result = 0;
					bankTicketLimitVerifyVo.setAmountLimit(null);
					BankTicketLimitAmountDTO bankTicketLimitAmountDTO = BeanMapper.map(bankTicketLimitVerifyVo,
							BankTicketLimitAmountDTO.class);
					//解密
					bankTicketLimitAmountDTO.setId(Long.valueOf(RSAUtil.decryptByPrivateKey(bankTicketLimitAmountDTO.getEncryptId())));
					/* 设置银票业务类型 */
	    			Integer businessCode = null;//银票业务代码
	    			if(POConstant.BANK_TICKET_ADD_UN_AUDIT == bankTicketLimitAmountDTO.getAuditStatus()){//审批前状态为（新增待审核）
	    				businessCode = POConstant.BUSINESS_TABLE_ADD_BANK_TICKET_LIMIT_AMOUNT;
	    			}
	    			if(POConstant.BANK_TICKET_UPDATE_UN_AUDIT == bankTicketLimitAmountDTO.getAuditStatus()){//审批前状态为（修改待审核）
	    				businessCode = POConstant.BUSINESS_TABLE_UPDATE_BANK_TICKET_LIMIT_AMOUNT;
	    			}
	    			if(POConstant.BANK_TICKET_DELETE_UN_AUDIT == bankTicketLimitAmountDTO.getAuditStatus()){//审批前状态为（删除待审核）
	    				businessCode = POConstant.BUSINESS_TABLE_DELETE_BANK_TICKET_LIMIT_AMOUNT;
	    			}
					
					//设置审核状态
					
					if (bankTicketLimitAmountDTO.getAuditStatus() == POConstant.BANK_TICKET_ADD_UN_AUDIT) {
						bankTicketLimitAmountDTO.setAuditStatus(POConstant.BANK_TICKET_ADD_REJECT);
						result += bankTicketAuditFlowService.addBankTicketAuditFlowRecord(
								bankTicketLimitAmountDTO.getAuditStatus(), bankTicketLimitAmountDTO.getAuditMsg(), businessCode,
								bankTicketLimitAmountDTO.getId(), CookieUtil.getUserId(request));
					} 
					else if (bankTicketLimitAmountDTO.getAuditStatus() == POConstant.BANK_TICKET_UPDATE_UN_AUDIT)
					{
						bankTicketLimitAmountDTO.setAuditStatus(POConstant.BANK_TICKET_UPDATE_REJECT);
						result += bankTicketAuditFlowService.addBankTicketAuditFlowRecord(bankTicketLimitAmountDTO.getAuditStatus(),
								bankTicketLimitAmountDTO.getAuditMsg(), businessCode, bankTicketLimitAmountDTO.getId(),
								CookieUtil.getUserId(request));
					}
					else if (bankTicketLimitAmountDTO.getAuditStatus() == POConstant.BANK_TICKET_DELETE_UN_AUDIT)
					{
						bankTicketLimitAmountDTO.setAuditStatus(POConstant.BANK_TICKET_DELETE_REJECT);
						result += bankTicketAuditFlowService.addBankTicketAuditFlowRecord(bankTicketLimitAmountDTO.getAuditStatus(),
								bankTicketLimitAmountDTO.getAuditMsg(), businessCode, bankTicketLimitAmountDTO.getId(),
								CookieUtil.getUserId(request));
					}
					result= bankTicketLimitAmountService.updateBankTicketLimitAmount(bankTicketLimitAmountDTO, CookieUtil.getUserId(request));
					if (0 == result) {
						throw new ServiceBizException("保存流水失败");
					}
				}
				//初始化数据
				List<BankTicketLimitAmountDTO> bankTicketLimitAmountDTOList=initBankTicketLimitVerifyVoList(bankTicketLimitVerifyVoList);
				
				//调用驳回SERVICE
				bankTicketLimitAmountService.bankTicketLimitAmountAuditReject(bankTicketLimitAmountDTOList, CookieUtil.getUserId(request));
			}
			responseDTO.setState(StateConstant.SUCCESS);
			
		} catch (Exception e) {
		    logger.error(e);
			responseDTO.setState(StateConstant.DATABASE_ERROR);
			responseDTO.setErrorCode(ExceptionConstants.DATABASE_OPERATION_ERROR);
			responseDTO.setMessage(e.getMessage());
		}

		return true;
	}
}
