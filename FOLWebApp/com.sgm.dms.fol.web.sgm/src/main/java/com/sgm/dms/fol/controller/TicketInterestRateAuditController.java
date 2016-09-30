package com.sgm.dms.fol.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sgm.dms.fol.bankTicket.api.InterestRateHisService;
import com.sgm.dms.fol.bankTicket.dto.TicketInterestRateDTO;
import com.sgm.dms.fol.common.service.controller.impl.BaseController;
import com.sgm.dms.fol.common.service.utils.BeanMapper;
import com.sgm.dms.fol.common.service.utils.MapUtil;
import com.sgm.dms.fol.vo.TicketInterestRateVo;
import com.sgm.solution.framework.dataAuthority.annotations.SGMValidationRequest;

/**
 * 票据贴息年化利率审核Controllor
 * 
 * @author lujinglei
 *
 */
@Controller
@RequestMapping("/interestRate/audit")
public class TicketInterestRateAuditController extends BaseController {
	// 日志
	protected Logger logger = LogManager.getLogger(this.getClass());
	@Autowired
	private InterestRateHisService interestRateHisService;

	/**
	 * 票据贴息年化利率维审核
	 */
	@RequestMapping(value = "/query", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getInterestRateHis(@Validated @RequestBody TicketInterestRateVo ticketInterestRateAuditVo,HttpServletRequest request)
			throws Throwable {	
		TicketInterestRateDTO dto = BeanMapper.map(ticketInterestRateAuditVo, TicketInterestRateDTO.class);
		//查询待审核年利率
		List<TicketInterestRateDTO> interestRateList = interestRateHisService.getInterestRateAudit(dto);	
		//批量转换成VO
		List<TicketInterestRateVo> voList=BeanMapper.mapList(interestRateList, TicketInterestRateVo.class);
		//设置前台需要的对象
		Map<String, Object> responsedata=MapUtil.setQueryDataToMap(voList,Long.valueOf(interestRateList.size()) );
	
		return responsedata;
	}
	/*
	 * 
	*
	* @author Lujinglei
	* TODO 审核通过
	* @date 2016年4月6日
	* @param tInterestRateList
	* @param vo
	* @param userId
	* @return
	* @throws Exception
	 */
    @RequestMapping(value = "/auditSuccess" ,method = RequestMethod.POST, produces = "application/json")
    @SGMValidationRequest
    public @ResponseBody Object auditSuccess
            (@RequestBody List<TicketInterestRateVo> tInterestRateList) throws Exception {
          //审核通过
        updateAudit(tInterestRateList);
        return true;
    }
	/**
	 * 批量审核通过
	 * @param voList
	 * @throws Exception
	 */
	private void updateAudit(List<TicketInterestRateVo> voList) throws Exception{
		if (null != voList) {
			for (TicketInterestRateVo tVo : voList) {
				TicketInterestRateDTO ticketInterestRateDTO = BeanMapper.map(tVo, TicketInterestRateDTO.class);
				interestRateHisService.updateInterestRateCurAudit(ticketInterestRateDTO);
			}
		}
	}

	/**
	 * 批量驳回
	 * @param voList
	 * @throws Exception
	 */
	private void updateReject(List<TicketInterestRateVo> voList) throws Exception{
		if (null != voList) {
			for (TicketInterestRateVo vo : voList) {
				TicketInterestRateDTO ticketInterestRateDTO = BeanMapper.map(vo,TicketInterestRateDTO.class);
				interestRateHisService.updateInterestRateCurReject(ticketInterestRateDTO);
			}
		}
	}
    	/*
    	 * 
    	*
    	* @author Lujinglei
    	* TODO 驳回
    	* @date 2016年4月6日
    	* @param tInterestRateList
    	* @param vo
    	* @param userId
    	* @return
    	* @throws Exception
    	 */
    	 @RequestMapping(value = "/reject" ,method = RequestMethod.POST, produces = "application/json")
    	 @SGMValidationRequest
    	 public @ResponseBody Object reject
    	         (@RequestBody List<TicketInterestRateVo> tInterestRateList) throws Exception {
    	        //驳回
    	     updateReject(tInterestRateList);
    	     return true;
    	 }

}
