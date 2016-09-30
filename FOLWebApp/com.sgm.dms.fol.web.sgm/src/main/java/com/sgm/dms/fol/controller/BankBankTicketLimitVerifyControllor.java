package com.sgm.dms.fol.controller;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sgm.dms.fol.bankTicket.api.BankBankTicketService;
import com.sgm.dms.fol.bankTicket.dto.BankBankTicketDTO;
import com.sgm.dms.fol.common.api.constants.POConstant;
import com.sgm.dms.fol.common.service.controller.impl.BaseController;
import com.sgm.dms.fol.common.service.utils.BeanMapper;
import com.sgm.dms.fol.common.service.utils.CookieUtil;
import com.sgm.dms.fol.common.service.utils.MapUtil;
import com.sgm.dms.fol.common.service.utils.StringUtil;
import com.sgm.dms.fol.vo.BankBankTicketLimitVerifyVo;
import com.sgm.dms.fol.vo.BankBankTictetVo;
import com.sgm.solution.framework.urlaccess.annotations.ValidationRequestUrl;

/**
 * 银行银票额度审核controllor
 * @author lujinglei
 *
 */
@Controller
@RequestMapping("/bankBankTicketVerify/limits")
public class BankBankTicketLimitVerifyControllor extends BaseController{
	// 日志
	protected Logger logger = LogManager.getLogger(this.getClass());
	
	@Autowired
	private BankBankTicketService bankBankTicketService;
	
	/**
	 * 查询银行银票额度记录
	 * @param bTictetVo
	 * @throws SQLException
	 */
	@RequestMapping(value = "/query", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ValidationRequestUrl
	@ResponseBody
	public Object getBankTicketIssueFile(@Validated @RequestBody BankBankTicketLimitVerifyVo bTictetVo,HttpServletRequest request,HttpServletResponse response)
			throws Throwable {
		// 数据初始化
		BankBankTicketDTO bankBankTicketDTO=initBankBankTicketDTO(bTictetVo);
			
		// 查询银行银票额度记录
		List<BankBankTicketDTO> bankBankTicketDTOList = bankBankTicketService.findBankBankTicketDataByWhs(bankBankTicketDTO);

		// 批量转换成VO
		List<BankBankTictetVo> bankBankTictetVoList=convertVoList(bankBankTicketDTOList);
		
		// 设置成财务专用的格式
		StringUtil.fontFormatFinance(bankBankTictetVoList);
		
		// 设置返回对象
		Map<String, Object> responsedata=MapUtil.setQueryDataToMap(bankBankTictetVoList, Long.valueOf(getTotalBonusIssueFile(bankBankTicketDTO)));
			
		return responsedata;

	}
	
	/** 
	 * 数据初始化
	 * @author DELL
	 * TODO description
	 * @date 2016年3月24日
	 * @param bTictetVo
	 * @return
	 * @throws Exception
	 */
	private BankBankTicketDTO initBankBankTicketDTO(BankBankTicketLimitVerifyVo bTictetVo)throws Exception{
	    BankBankTicketDTO dto=BeanMapper.map(bTictetVo, BankBankTicketDTO.class);
        
        // 设置状态为有效
        dto.setStatus(POConstant.IS_STATUS);    
        // 设置审核状态
        List<Integer> auditStatusList = new ArrayList<Integer>();
        auditStatusList.add(POConstant.BANK_TICKET_ADD_UN_AUDIT);
        auditStatusList.add(POConstant.BANK_TICKET_UPDATE_UN_AUDIT);
        auditStatusList.add(POConstant.BANK_TICKET_DELETE_UN_AUDIT);
        dto.setAuditStatusList(auditStatusList);
        return dto;
	}
	
	/** 
	 * 批量转换成VO
	 * @author DELL
	 * TODO description
	 * @date 2016年3月24日
	 * @param bankBankTicketDTOList
	 * @return
	 * @throws Exception
	 */
	private List<BankBankTictetVo> convertVoList(List<BankBankTicketDTO> bankBankTicketDTOList) throws Exception{
	    List<BankBankTictetVo> bankBankTictetVoList=BeanMapper.mapList(bankBankTicketDTOList, BankBankTictetVo.class);
	    
	    //审核查询不显示上一次的审核结果
	    if(null != bankBankTictetVoList){
            for (BankBankTictetVo bankBankTictetVo : bankBankTictetVoList) {
                bankBankTictetVo.setAuditMsg("");
            }
	    }
	    return bankBankTictetVoList;
	}

	/**
	 * 查询银行银票额度记录数
	 * @param dto
	 * @return
	 */
	private int getTotalBonusIssueFile(BankBankTicketDTO dto) {
		return bankBankTicketService.findBankBankTicketDataCountByWhs(dto);
	}
	/**
	 * 审批通过
	 * @param bankBankTicketLimitVerifyVo
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/auditSuccess", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	@ValidationRequestUrl
	public Object auditSuccess(@RequestBody List<BankBankTicketLimitVerifyVo> bankBankTicketLimitVerifyVoList,HttpServletRequest request) throws Throwable{

	    // 初始化数据
	    List<BankBankTicketDTO> bankBankTicketDTOList=initBankBankTicketDTO(bankBankTicketLimitVerifyVoList);
	    	
	    // 调用审核通过SERVICE
	    bankBankTicketService.bankBankTicketAuditSuccess(bankBankTicketDTOList, CookieUtil.getUserId(request));

	    return true;
	}
	
	/** 
	 * 数据初始化
	 * @author DELL
	 * TODO description
	 * @date 2016年3月24日
	 * @param bankBankTicketLimitVerifyVoList
	 * @return
	 * @throws Exception
	 */
	private List<BankBankTicketDTO> initBankBankTicketDTO(List<BankBankTicketLimitVerifyVo> bankBankTicketLimitVerifyVoList) throws Exception{
	    for (BankBankTicketLimitVerifyVo bankBankTicketLimitVerifyVo : bankBankTicketLimitVerifyVoList) {
            bankBankTicketLimitVerifyVo.setAmount(new BigDecimal(bankBankTicketLimitVerifyVo.getMoneyDisplay().replaceAll(",", "")).divide(BigDecimal.valueOf(POConstant.BANK_TICKET_AMOUNT_BASE_UNIT)));
            bankBankTicketLimitVerifyVo.setMoneyDisplay(null);
        }
	    
	    return BeanMapper.mapList(bankBankTicketLimitVerifyVoList, BankBankTicketDTO.class);
	}
	
	/**
	 * 驳回
	 * @param dealerBankRelevanceVo
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/reject", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	@ValidationRequestUrl
	public Object reject(@RequestBody List<BankBankTicketLimitVerifyVo> bankBankTicketLimitVerifyVoList,HttpServletRequest request) throws Throwable{
	    // 初始化数据
        List<BankBankTicketDTO> bankBankTicketDTOList=initBankBankTicketDTO(bankBankTicketLimitVerifyVoList);
            
        // 调用审核驳回SERVICE
        bankBankTicketService.bankBankTicketAuditReject(bankBankTicketDTOList, CookieUtil.getUserId(request));
	    return true;
	}

}
