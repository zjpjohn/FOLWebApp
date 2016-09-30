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

import com.sgm.dms.fol.bankTicket.api.DealerBankRelevanceService;
import com.sgm.dms.fol.bankTicket.dto.DealerBankRelevanceDTO;
import com.sgm.dms.fol.common.api.constants.POConstant;
import com.sgm.dms.fol.common.service.controller.impl.BaseController;
import com.sgm.dms.fol.common.service.utils.BeanMapper;
import com.sgm.dms.fol.common.service.utils.CookieUtil;
import com.sgm.dms.fol.common.service.utils.MapUtil;
import com.sgm.dms.fol.vo.DealerBankRelevanceVo;
import com.sgm.solution.framework.urlaccess.annotations.ValidationRequestUrl;

/**
 * 银行特殊经销商审核controllor
 * @author lujinglei
 *
 */
@Controller
@RequestMapping("/bankTicketVerify/specialDealer")
public class BankSpecialDealerVerifyControllor extends BaseController{
    //日志
	protected Logger logger = LogManager.getLogger(this.getClass());
	    
	@Autowired
	private DealerBankRelevanceService dealerBankRelevanceService;
	    
	/** 
	 * 查询银行特殊经销商数据
	 * @author DELL
	 * TODO description
	 * @date 2016年3月24日
	 * @param dealerBankRelevanceVo
	 * @param request
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping("/querySpecialDealer")    
	@ResponseBody
	public Object queryBankTicketDeadLineWithSpecialDealer(@RequestBody DealerBankRelevanceVo dealerBankRelevanceVo,HttpServletRequest request) throws Throwable{
	    //数据初始化
	    DealerBankRelevanceDTO dealerBankRelevanceDTO=initDealerBankRelevanceDTO(dealerBankRelevanceVo);
	    
	    //查询银行特殊经销商数据
	    List<DealerBankRelevanceDTO> dealerBankRelevanceList=dealerBankRelevanceService.findDealerBankRelevanceByWhere(dealerBankRelevanceDTO);
	    
	    //批量转换VO
	    List<DealerBankRelevanceVo> dealerBankRelevanceVoList=BeanMapper.mapList(dealerBankRelevanceList, DealerBankRelevanceVo.class);
	    
	    //设置成前台需要的数据
	    Map<String, Object> responsedata=MapUtil.setQueryDataToMap(dealerBankRelevanceVoList, Long.valueOf(dealerBankRelevanceService.findDealerBankRelevanceCountByWhere(dealerBankRelevanceDTO)));
	    
	    return responsedata;
	}
	
	private DealerBankRelevanceDTO initDealerBankRelevanceDTO(DealerBankRelevanceVo dealerBankRelevanceVo) throws Exception{
	    DealerBankRelevanceDTO dealerBankRelevanceDTO=BeanMapper.map(dealerBankRelevanceVo, DealerBankRelevanceDTO.class);
        //设置审核状态
        List<Integer> auditStatusList = new ArrayList<Integer>();
        auditStatusList.add(POConstant.BANK_TICKET_ADD_UN_AUDIT);
        auditStatusList.add(POConstant.BANK_TICKET_UPDATE_UN_AUDIT);
        auditStatusList.add(POConstant.BANK_TICKET_DELETE_UN_AUDIT);
        dealerBankRelevanceDTO.setAuditStatusList(auditStatusList);
        return dealerBankRelevanceDTO;
	}
	
    /**
     * 审批通过
     * @param dealerBankRelevanceVo
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/auditSuccess", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ValidationRequestUrl
    public Object auditSuccess(@RequestBody List<DealerBankRelevanceVo> dealerBankRelevanceVoList,HttpServletRequest request) throws Throwable{
        //数据初始化
        List<DealerBankRelevanceDTO> dealerBankRelevanceDTOList=BeanMapper.mapList(dealerBankRelevanceVoList, DealerBankRelevanceDTO.class);
        
        //调用审核通过SERVICE
        dealerBankRelevanceService.dealerBankRelevanceAuditSuccess(dealerBankRelevanceDTOList,CookieUtil.getUserId(request));
        
        return true;
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
    public Object reject(@RequestBody List<DealerBankRelevanceVo> dealerBankRelevanceVoList,HttpServletRequest request) throws Throwable{
        //数据初始化
        List<DealerBankRelevanceDTO> dealerBankRelevanceDTOList=BeanMapper.mapList(dealerBankRelevanceVoList, DealerBankRelevanceDTO.class);
        
        //调用审核通过SERVICE
        dealerBankRelevanceService.dealerBankRelevanceAuditReject(dealerBankRelevanceDTOList,CookieUtil.getUserId(request));
        
        return true;
    }
}
