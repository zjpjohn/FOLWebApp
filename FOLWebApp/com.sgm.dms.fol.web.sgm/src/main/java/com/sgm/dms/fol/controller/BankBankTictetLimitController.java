package com.sgm.dms.fol.controller;

import java.sql.SQLException;
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

import com.sgm.dms.fol.bankTicket.api.BankBankTicketService;
import com.sgm.dms.fol.bankTicket.dto.BankBankTicketDTO;
import com.sgm.dms.fol.bankTicket.dto.BankInfoDTO;
import com.sgm.dms.fol.common.api.constants.POConstant;
import com.sgm.dms.fol.common.service.controller.impl.BaseController;
import com.sgm.dms.fol.common.service.utils.BeanMapper;
import com.sgm.dms.fol.common.service.utils.CookieUtil;
import com.sgm.dms.fol.common.service.utils.MapUtil;
import com.sgm.dms.fol.common.service.utils.StringUtil;
import com.sgm.dms.fol.vo.BankBankTictetSelVo;
import com.sgm.dms.fol.vo.BankBankTictetVo;
import com.sgm.solution.framework.urlaccess.annotations.ValidationRequestUrl;

/**
 * 银行银票额度维护controller
 * @author zhang  bao
 * @date 2015年12月31日
 */
@Controller
@RequestMapping("/bankTictet/limits")
public class BankBankTictetLimitController  extends BaseController{
	// 日志
	protected Logger logger = LogManager.getLogger(this.getClass());
	
	@Autowired
	private BankBankTicketService bankBankTicketService;
	
	/**
	 * 查询银行银票额度记录
	 * 
	 * @throws SQLException
	 */
	@RequestMapping(value = "/query", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ValidationRequestUrl
	@ResponseBody
	public Object getBankTicketIssueFile(@Validated @RequestBody BankBankTictetSelVo bTictetVo) throws Throwable {
	    //数据初始化
	    BankBankTicketDTO bankBankTicketDTO=initBankBankTicketDTO(bTictetVo);
			
	    //查询
		List<BankBankTicketDTO> bonusList = bankBankTicketService.findBankBankTicketDataByWhs(bankBankTicketDTO);
			
		//批量转换成VO
		List<BankBankTictetVo> bankBankTictetVoList=BeanMapper.mapList(bonusList, BankBankTictetVo.class);
			
		//设置财务专用的格式
		StringUtil.fontFormatFinance(bankBankTictetVoList);
		
		//设置前台需要的数据
		Map<String, Object> responsedata=MapUtil.setQueryDataToMap(bankBankTictetVoList, Long.valueOf(getTotalBonusIssueFile(bankBankTicketDTO)));
			
		return responsedata;
	}
	
	/**
	 * 初始化数据
	 * @param bTictetVo
	 * @throws Exception
	 */
	private BankBankTicketDTO initBankBankTicketDTO(BankBankTictetSelVo bTictetVo) throws Exception{
	    BankBankTicketDTO bankBankTicketDTO=BeanMapper.map(bTictetVo, BankBankTicketDTO.class);
        
        //设置状态为有效
	    bankBankTicketDTO.setStatus(POConstant.IS_STATUS);
        return bankBankTicketDTO;
	}
	
	/***
	 * 查询银行银票额度记录数
	 */
	private int getTotalBonusIssueFile(BankBankTicketDTO dto) {
		return bankBankTicketService.findBankBankTicketDataCountByWhs(dto);
	}

	/** 
	 * 新增银行银票额度
	 * @author DELL
	 * TODO description
	 * @date 2016年3月23日
	 * @param bankBankTictetVo
	 * @param request
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	@ValidationRequestUrl
	public Object addBankBankTicketLimit(@RequestBody BankBankTictetVo bankBankTictetVo,HttpServletRequest request) throws Throwable{
	    //转换数据库需要查询的数据
	    BankBankTicketDTO bankBankTicketDTO=BeanMapper.map(bankBankTictetVo, BankBankTicketDTO.class);
	    BankInfoDTO bankInfoDTO=BeanMapper.map(bankBankTictetVo, BankInfoDTO.class);
	        
	    //调用新增银行银票额度SERVICE
	    bankBankTicketService.saveBankBankTicket(bankBankTicketDTO, bankInfoDTO, CookieUtil.getUserId(request));
	        
	    return true;
	}
	
	/** 
	 * 修改银行银票额度
	 * @author DELL
	 * TODO description
	 * @date 2016年3月23日
	 * @param bankBankTictetVo
	 * @param request
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ValidationRequestUrl
    public Object updateBankBankTicketLimit(@RequestBody BankBankTictetVo bankBankTictetVo,HttpServletRequest request) throws Throwable{
	    //转换数据库需要查询的数据
        BankBankTicketDTO bankBankTicketDTO=BeanMapper.map(bankBankTictetVo, BankBankTicketDTO.class);
        BankInfoDTO bankInfoDTO=BeanMapper.map(bankBankTictetVo, BankInfoDTO.class);
        
        //调用修改银行银票额度SERVICE
        bankBankTicketService.updateBankBankTicket(bankBankTicketDTO, bankInfoDTO, CookieUtil.getUserId(request));
        
        return true;
    }

}
