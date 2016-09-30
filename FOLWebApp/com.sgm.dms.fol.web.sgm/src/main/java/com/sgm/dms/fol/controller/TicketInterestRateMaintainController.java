package com.sgm.dms.fol.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sgm.dms.fol.bankTicket.api.InterestRateHisService;
import com.sgm.dms.fol.bankTicket.dto.TicketInterestRateDTO;
import com.sgm.dms.fol.common.api.constants.CookieConstant;
import com.sgm.dms.fol.common.service.controller.impl.BaseController;
import com.sgm.dms.fol.common.service.utils.BeanMapper;
import com.sgm.dms.fol.common.service.utils.MapUtil;
import com.sgm.dms.fol.vo.TicketInterestRateVo;
import com.sgm.solution.framework.dataAuthority.annotations.SGMValidationRequest;
import com.sgm.solution.framework.dataAuthority.annotations.SGMValidationResource;
import com.sgm.solution.framework.dataAuthority.annotations.SGMValidationUserInfo;
import com.sgm.solution.framework.dataAuthority.services.Encryptor;

/**
 * 票据贴息年化利率维护Controllor
 * @author lujinglei
 *
 */
@Controller
@RequestMapping("/interestRate/maintain")
public class TicketInterestRateMaintainController extends BaseController{
	//日志
	protected Logger logger = LogManager.getLogger(this.getClass());
	@Autowired
	private InterestRateHisService interestRateHisService;

	/**
	 * 票据贴息当前年化利率
	 * @param ticketInterestRateMaintainVo
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping(value = "/queryCur", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getInterestRateCur(@Validated @RequestBody TicketInterestRateVo ticketInterestRateMaintainVo,HttpServletRequest request) throws Throwable{
			TicketInterestRateDTO dto = BeanMapper.map(ticketInterestRateMaintainVo, TicketInterestRateDTO.class);
			//查询票据贴息当前年利率
			List<TicketInterestRateDTO> interestRateList = interestRateHisService.getInterestRateCurrent(dto);
			//批量转换成VO
			List<TicketInterestRateVo> voList=BeanMapper.mapList(interestRateList, TicketInterestRateVo.class);
			//设置前台需要的对象
			Map<String, Object> responsedata = MapUtil.setQueryDataToMap(voList, Long.valueOf(interestRateList.size()));
		return responsedata;
	}
	/**
	 * 票据贴息历史年化利率
	 * @param ticketInterestRateMaintainVo
	 * @param request
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping(value = "/queryHis", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getInterestRateHis(@Validated @RequestBody TicketInterestRateVo ticketInterestRateMaintainVo,HttpServletRequest request) throws Throwable{
			//查询历史利率
			List<TicketInterestRateVo> interestRateList = geTicketInterestRateHisToDataBase(ticketInterestRateMaintainVo);
			//批量转换成VO
			List<TicketInterestRateVo> voList = BeanMapper.mapList(interestRateList, TicketInterestRateVo.class);
			//设置前台需要的对象
			Map<String, Object> responsedata = MapUtil.setQueryDataToMap(voList, Long.valueOf(getQueryTotalSize(ticketInterestRateMaintainVo)));
		return responsedata;
	}
	/**
	 * 到数据库查询数据并返回DTO数据集
	 * @throws Exception 
	 */
	private List<TicketInterestRateVo> geTicketInterestRateHisToDataBase(TicketInterestRateVo ticketInterestRateVo)
			throws Exception {
		TicketInterestRateDTO searchDto = BeanMapper.map(ticketInterestRateVo, TicketInterestRateDTO.class);
		List<TicketInterestRateDTO> data = interestRateHisService.getInterestRateHis(searchDto);
		logger.info("=====查询完成======");
		return convertDtoToVo(data);
	}
	/**
     * 到数据库查询数据并返回总数
	 * @throws Exception 
     */
    private int getInterestRateHisCountToDataBase(TicketInterestRateVo ticketInterestRateMaintainVo)
            throws Exception {
    	TicketInterestRateDTO searchDto = BeanMapper.map(ticketInterestRateMaintainVo, TicketInterestRateDTO.class);
        List<TicketInterestRateDTO> data = interestRateHisService.getInterestRateHis(searchDto);
        logger.info("=====查询完成======");
        return data.size();
    }

	/**
	 * 数据统一转换成VO对象
	 */
	private List<TicketInterestRateVo> convertDtoToVo(List<TicketInterestRateDTO> dtos) {
		return BeanMapper.mapList(dtos, TicketInterestRateVo.class);
	}
	/**
	 * 查询总条数
	 * @param ticketInterestRateMaintainVo
	 * @return
	 * @throws Exception 
	 */
	private int getQueryTotalSize(TicketInterestRateVo ticketInterestRateMaintainVo) throws Exception {
		ticketInterestRateMaintainVo.setBeginNo(null);
		ticketInterestRateMaintainVo.setEndNo(null);
		return getInterestRateHisCountToDataBase(ticketInterestRateMaintainVo);
	}
    /*
     * 
    *
    * @author Lujinglei
    * TODO 验证签名并进行更新操作
    * @date 2016年4月6日
    * @param vo
    * @param userId
    * @return
    * @throws Exception
     */
    @RequestMapping(value="/update", method = RequestMethod.POST, produces = "application/json")
    @SGMValidationRequest
    public @ResponseBody Object updateDeadline(@RequestBody @SGMValidationResource TicketInterestRateVo vo,@SGMValidationUserInfo @CookieValue(CookieConstant.COOKIE_USER_ID) String userId ) throws Exception {

        TicketInterestRateDTO ticketInterestRateDTO=BeanMapper.map(vo, TicketInterestRateDTO.class);
         //新增年利率修改记录
        interestRateHisService.updateInterestRateHis(ticketInterestRateDTO); 
        
        System.out.println(vo.getCurrentInterestRate());
        System.out.println(vo.getUnAuditRateCur());
        System.out.println(vo.getHistoryInterestRate());
        System.out.println(vo.getAnnualInterestRate());
        
        return true;

    }
    /*
     * 
    *
    * @author Lujinglei
    * TODO 获取签名sign
    * @date 2016年4月6日
    * @param ticketInterestRateMaintainVo
    * @param request
    * @param userId
    * @return
    * @throws Throwable
     */
	    @RequestMapping("/getSign")
	    @ResponseBody
	    public Object getSign(@RequestBody TicketInterestRateVo ticketInterestRateMaintainVo,HttpServletRequest request,@CookieValue(CookieConstant.COOKIE_USER_ID) String userId) throws Throwable{
	        TicketInterestRateVo result=new TicketInterestRateVo();
	        result.setAnnualInterestRate(ticketInterestRateMaintainVo.getAnnualInterestRate());
	        String[] refVales=new String[]{String.valueOf(ticketInterestRateMaintainVo.getAnnualInterestRate())};
	        String sign=Encryptor.generateSignFromResource(userId,refVales);
	        result.setSign(sign);

	        return result;
	    }
}
