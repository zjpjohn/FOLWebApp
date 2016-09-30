package com.sgm.dms.fol.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sgm.dms.fol.bankTicket.api.BankTicketInterestDeatilService;
import com.sgm.dms.fol.bankTicket.dto.BankTicketInterestDeatilQueryDTO;
import com.sgm.dms.fol.common.service.utils.BeanMapper;
import com.sgm.dms.fol.common.service.utils.MapUtil;
import com.sgm.dms.fol.common.service.utils.StringUtil;
import com.sgm.dms.fol.vo.BankTicketInterestDeatilQueryVo;
import com.sgm.dms.fol.vo.BankTicketInterestVo;

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
	
	/** 
	 * 票据贴息明细查询
	 * @author DELL
	 * TODO description
	 * @date 2016年3月24日
	 * @param bankTicketInterestVo
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping(value = "/query", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryInterestDeatil(@RequestBody BankTicketInterestVo bankTicketInterestVo) throws Throwable {
	    //数据初始化
	    BankTicketInterestDeatilQueryDTO bankTicketInterestDeatilDTO=BeanMapper.map(bankTicketInterestVo, BankTicketInterestDeatilQueryDTO.class);
	    
	    //查询票据贴息明细
	    List<BankTicketInterestDeatilQueryDTO> bankTicketInterestList=bankTicketInterestDeatilService.findBankTicketInterestDeatilByWhere(bankTicketInterestDeatilDTO);
	    
	    //批量转换成VO
	    List<BankTicketInterestDeatilQueryVo> volist=BeanMapper.mapList(bankTicketInterestList, BankTicketInterestDeatilQueryVo.class);
	    
	    //转换成财务专用格式
	    StringUtil.fontFormatFinance(volist);
	    
	    //设置前台需要的数据
	    Map<String, Object> responsedata=MapUtil.setQueryDataToMap(volist,Long.valueOf(bankTicketInterestDeatilService.findBankTicketInterestDeatilCountByWhere(bankTicketInterestDeatilDTO)));
	    
	    return responsedata;
	}
}
