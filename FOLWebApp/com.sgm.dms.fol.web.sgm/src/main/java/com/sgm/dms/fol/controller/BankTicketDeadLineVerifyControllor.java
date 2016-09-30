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

import com.sgm.dms.fol.bankTicket.api.BankTicketDeadLineService;
import com.sgm.dms.fol.bankTicket.dto.BankTicketDeadLineDTO;
import com.sgm.dms.fol.common.api.constants.POConstant;
import com.sgm.dms.fol.common.service.controller.impl.BaseController;
import com.sgm.dms.fol.common.service.utils.BeanMapper;
import com.sgm.dms.fol.common.service.utils.CookieUtil;
import com.sgm.dms.fol.common.service.utils.MapUtil;
import com.sgm.dms.fol.vo.BankTicketDeadLineVo;
import com.sgm.solution.framework.urlaccess.annotations.ValidationRequestUrl;

/**
 * 银票期限审核Controller
 * @author lujinglei
 *
 */
@Controller
@RequestMapping("/bankTicketVerify/deadLine")
public class BankTicketDeadLineVerifyControllor extends BaseController{
    //日志
	protected Logger logger = LogManager.getLogger(this.getClass());
	 
	@Autowired
	private BankTicketDeadLineService bankTicketDeadLineService;

	/** 
	 * 查询品牌待审核的数据
	 * @author DELL
	 * TODO description
	 * @date 2016年3月24日
	 * @param bankTicketDeadLineVo
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping("/queryBrand")
	@ResponseBody
	public Object queryBrand(@RequestBody BankTicketDeadLineVo bankTicketDeadLineVo) throws Throwable{
		//数据初始化
		BankTicketDeadLineDTO bankTicketDeadLineDTO=initBankTicketDeadLineDTO(bankTicketDeadLineVo);
		
		//查询品牌待审核的数据
		List<BankTicketDeadLineDTO> deadLineList=bankTicketDeadLineService.findBankTicketDeadLineWithBrand(bankTicketDeadLineDTO);
		
		//批量转换成VO
		List<BankTicketDeadLineVo> bankTicketDeadLineVoList=BeanMapper.mapList(deadLineList, BankTicketDeadLineVo.class);
		
		//设置前台需要的值
		Map<String, Object> responsedata=MapUtil.setQueryDataToMap(bankTicketDeadLineVoList, Long.valueOf(bankTicketDeadLineService.findBankTicketDeadLineCountWithBrand(bankTicketDeadLineDTO)));
		
		return responsedata;
	}
	
	/** 
	 * 数据初始化
	 * @author DELL
	 * TODO description
	 * @date 2016年3月24日
	 * @return
	 * @throws Exception
	 */
	private BankTicketDeadLineDTO initBankTicketDeadLineDTO(BankTicketDeadLineVo bankTicketDeadLineVo) throws Exception{
	    BankTicketDeadLineDTO bankTicketDeadLineDTO=BeanMapper.map(bankTicketDeadLineVo, BankTicketDeadLineDTO.class);
        //设置审批状态
        List<Integer> auditStatusStrs = new ArrayList<Integer>();
        auditStatusStrs.add(POConstant.BANK_TICKET_ADD_UN_AUDIT);
        auditStatusStrs.add(POConstant.BANK_TICKET_UPDATE_UN_AUDIT);
        auditStatusStrs.add(POConstant.BANK_TICKET_DELETE_UN_AUDIT);
        bankTicketDeadLineDTO.setAuditStatusStrs(auditStatusStrs);
        return bankTicketDeadLineDTO;
	}
	
	/** 
	 * 查询特殊经销商待审核的数据
	 * @author DELL
	 * TODO description
	 * @date 2016年3月24日
	 * @param bankTicketDeadLineVo
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping("/querySpecialDealer")
	@ResponseBody
	public Object queryBankTicketDeadLineWithSpecialDealer(@RequestBody BankTicketDeadLineVo bankTicketDeadLineVo) throws Throwable{
        //数据初始化
        BankTicketDeadLineDTO bankTicketDeadLineDTO=initBankTicketDeadLineDTO(bankTicketDeadLineVo);
        
        //查询品牌待审核的数据
        List<BankTicketDeadLineDTO> deadLineList=bankTicketDeadLineService.findBankTicketDeadLineWithSpecialDealer(bankTicketDeadLineDTO);
        
        //批量转换成VO
        List<BankTicketDeadLineVo> bankTicketDeadLineVoList=BeanMapper.mapList(deadLineList, BankTicketDeadLineVo.class);
        
        //设置前台需要的值
        Map<String, Object> responsedata=MapUtil.setQueryDataToMap(bankTicketDeadLineVoList, Long.valueOf(bankTicketDeadLineService.findBankTicketDeadLineCountWithSpecialDealer(bankTicketDeadLineDTO)));
        
        return responsedata;
	}
	
	/**
	 * 审批通过
	 * @param bankTicketDeadLineVo
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/auditSuccess", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	@ValidationRequestUrl
	public Object auditSuccess(@RequestBody List<BankTicketDeadLineVo> bankTicketDeadLineVoList,HttpServletRequest request) throws Throwable{
	    //vo与Dto实体转换
	    List<BankTicketDeadLineDTO> bankTicketDeadLineDTOList=BeanMapper.mapList(bankTicketDeadLineVoList, BankTicketDeadLineDTO.class);

	    //调用审核通过SERVICE
	    bankTicketDeadLineService.bankTicketDeadLineAuditSuccess(bankTicketDeadLineDTOList, CookieUtil.getUserId(request));
	    
	    return true;
	}
	/**
	 * 驳回
	 * @param bankTicketDeadLineVo
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/reject", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	@ValidationRequestUrl
	public Object reject(@RequestBody List<BankTicketDeadLineVo> bankTicketDeadLineVoList,HttpServletRequest request) throws Throwable{
	    //vo与Dto实体转换
        List<BankTicketDeadLineDTO> bankTicketDeadLineDTOList=BeanMapper.mapList(bankTicketDeadLineVoList, BankTicketDeadLineDTO.class);

        //调用驳回SERVICE
        bankTicketDeadLineService.bankTicketDeadLineAuditReject(bankTicketDeadLineDTOList, CookieUtil.getUserId(request));
	    
        return true;
	}
}
