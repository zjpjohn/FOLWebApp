package com.dealer.controller;

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

import com.dealer.vo.BankTicketInterestVo;
import com.sgm.dms.fol.bankTicket.api.BankTicketInterestDeatilService;
import com.sgm.dms.fol.bankTicket.dto.BankTicketInterestDeatilQueryDTO;
import com.sgm.dms.fol.common.service.utils.BeanMapper;
import com.sgm.dms.fol.common.service.utils.CookieUtil;
import com.sgm.dms.fol.common.service.utils.MapUtil;
import com.sgm.dms.fol.common.service.utils.RSAUtil;
import com.sgm.solution.framework.urlaccess.annotations.ValidationRequestUrl;

/**
 * 银票贴息明细Controllor
 * @author lujinglei
 *
 */
@Controller
@RequestMapping("/bankTicket/interestDeatil")
public class BankTicketInterestDeatilControllor {
	// 日志
	protected Logger logger = LogManager.getLogger(this.getClass());
	@Autowired
	private BankTicketInterestDeatilService bankTicketInterestDeatilService;
	
	@RequestMapping(value = "/query", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	@ValidationRequestUrl
	public Object queryInterestDeatil(@Validated @RequestBody BankTicketInterestVo bankTicketInterestVo,HttpServletRequest request,HttpServletResponse response) throws Exception {
		BankTicketInterestDeatilQueryDTO bankTicketInterestDeatilDTO = BeanMapper.map(bankTicketInterestVo,
				BankTicketInterestDeatilQueryDTO.class);
		bankTicketInterestDeatilDTO.setSapCode(RSAUtil.decryptByPrivateKey(CookieUtil.getSapCode(request)));
		List<BankTicketInterestDeatilQueryDTO> bankTicketInterestList = bankTicketInterestDeatilService
				.findBankTicketInterestDeatilByWhere(bankTicketInterestDeatilDTO);

		// 响应
		return MapUtil.setQueryDataToMap(bankTicketInterestList,
				Long.parseLong(bankTicketInterestDeatilService.findBankTicketInterestDeatilCountByWhere(bankTicketInterestDeatilDTO)+""));
	}
}
