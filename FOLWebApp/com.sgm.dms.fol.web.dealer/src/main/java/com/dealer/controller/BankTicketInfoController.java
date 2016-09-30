package com.dealer.controller;

import java.util.ArrayList;
import java.util.List;

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

import com.dealer.vo.BankTicketDetailFormVo;
import com.dealer.vo.BankTicketVo;
import com.dealer.vo.BankticketDetailVo;
import com.sgm.dms.fol.bankTicket.api.BankTicketService;
import com.sgm.dms.fol.bankTicket.dto.QueryBankTicketInfoDTO;
import com.sgm.dms.fol.common.service.controller.impl.BaseController;
import com.sgm.dms.fol.common.service.utils.BeanMapper;
import com.sgm.dms.fol.common.service.utils.CookieUtil;
import com.sgm.dms.fol.common.service.utils.MapUtil;
import com.sgm.dms.fol.common.service.utils.RSAUtil;
import com.sgm.dms.fol.common.service.utils.StringUtil;
import com.sgm.solution.framework.urlaccess.annotations.ValidationRequestUrl;
/****
 * 
*
* @author zhang bao
* 银票controller
* @date 2016年1月20日
 */
@Controller
@RequestMapping("/bankTicket")
public class BankTicketInfoController extends BaseController{
	// 日志
	protected Logger logger = LogManager.getLogger(this.getClass());
	
	@Autowired
	private BankTicketService bankTicketService;
	
	@RequestMapping(value = "/deatil/query", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ValidationRequestUrl
	@ResponseBody
	public Object getbankticketdetail(@RequestBody BankticketDetailVo bankticketVo,HttpServletRequest request,HttpServletResponse response) throws Exception {

		List<BankTicketDetailFormVo> bonusList = findbankticketdetailsFormToDataBase(bankticketVo, request,response);
		// 将金额转换成财务专用
		StringUtil.fontFormatFinance(bonusList);

		// 响应
		return MapUtil.setQueryDataToMap(bonusList, Long.parseLong(getbankticketdetailTotal(bankticketVo, request,response)+""));
	}

	private int getbankticketdetailTotal(BankticketDetailVo bankticketVo,HttpServletRequest request,HttpServletResponse response)  throws Exception{
		QueryBankTicketInfoDTO queryBankTicketInfoDTO=null;
		if(null!=bankticketVo){
			queryBankTicketInfoDTO=BeanMapper.map(bankticketVo, QueryBankTicketInfoDTO.class);	
		}else{
			queryBankTicketInfoDTO=new QueryBankTicketInfoDTO();
		}
		queryBankTicketInfoDTO.setSapCode(RSAUtil.decryptByPrivateKey(CookieUtil.getSapCode(request)));
		int total=bankTicketService.findBankTicketInfoCountByWhere(queryBankTicketInfoDTO);
		return total;
	}

	private List<BankTicketDetailFormVo> findbankticketdetailsFormToDataBase(BankticketDetailVo bankticketVo,HttpServletRequest request,HttpServletResponse response) throws Exception{
		QueryBankTicketInfoDTO queryBankTicketInfoDTO=null;
		List<BankTicketDetailFormVo> listForm=null;
		if(null!=bankticketVo){
			queryBankTicketInfoDTO=BeanMapper.map(bankticketVo, QueryBankTicketInfoDTO.class);	
		}else{
			queryBankTicketInfoDTO=new QueryBankTicketInfoDTO();
		}
		queryBankTicketInfoDTO.setSapCode(RSAUtil.decryptByPrivateKey(CookieUtil.getSapCode(request)));
		List<QueryBankTicketInfoDTO>  list=bankTicketService.findBankTicketInfoByWhere(queryBankTicketInfoDTO);
		if(null!=list&&list.size()>0){
			listForm=BeanMapper.mapList(list, BankTicketDetailFormVo.class);
		}else{
			listForm=new ArrayList<BankTicketDetailFormVo>();
		}
		return listForm;
	}

	@RequestMapping(value = "/status/query", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ValidationRequestUrl
    @ResponseBody
	public Object getBankTicketStatus(@RequestBody BankTicketVo bankticketVo,HttpServletRequest request,HttpServletResponse response)
			throws Exception {
		QueryBankTicketInfoDTO queryBankTicketInfoDTO = BeanMapper.map(bankticketVo, QueryBankTicketInfoDTO.class);
		queryBankTicketInfoDTO.setSapCode(RSAUtil.decryptByPrivateKey(CookieUtil.getSapCode(request)));
		List<QueryBankTicketInfoDTO> bankticketlist = bankTicketService
				.findBankTicketInfoByWhere(queryBankTicketInfoDTO);
		// 将金额转换成0，0.01
		List<BankTicketVo> list = BeanMapper.mapList(bankticketlist, BankTicketVo.class);
		StringUtil.fontFormatFinance(list);

		// 响应
		return MapUtil.setQueryDataToMap(list,
				Long.parseLong(bankTicketService.findBankTicketInfoCountByWhere(queryBankTicketInfoDTO) + ""));
	}
}
