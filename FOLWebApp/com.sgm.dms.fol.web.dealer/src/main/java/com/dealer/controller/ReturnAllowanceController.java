/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : web.dealer
*
* @File name : ReturnAllowanceController.java
*
* @Author : st78sr
*
* @Date : 2016年8月1日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2016年8月1日    st78sr    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/
	
package com.dealer.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dealer.vo.AllowanceAddReqVo;
import com.dealer.vo.AllowanceReportReqVo;
import com.dealer.vo.PageListRespVo;
import com.dealer.vo.ReturnAllowanceInfoVo;
import com.dealer.vo.ReturnAllowanceVO;
import com.dealer.vo.ReturnOrderInfoVo;
import com.dealer.vo.ToUpdReqCommonVo;
import com.sgm.dms.fol.common.api.constants.POConstant;
import com.sgm.dms.fol.common.api.domain.PageVo;
import com.sgm.dms.fol.common.api.domain.ResponseDTO;
import com.sgm.dms.fol.common.api.exception.ServiceBizException;
import com.sgm.dms.fol.common.api.exception.ServiceInvocationException;
import com.sgm.dms.fol.common.api.exception.VoNotValidException;
import com.sgm.dms.fol.common.service.controller.impl.BaseController;
import com.sgm.dms.fol.common.service.utils.BeanMapper;
import com.sgm.dms.fol.common.service.utils.CookieUtil;
import com.sgm.dms.fol.common.service.utils.DateUtil;
import com.sgm.dms.fol.common.service.utils.RSAUtil;
import com.sgm.dms.fol.common.service.utils.TokenUtils;
import com.sgm.dms.fol.common.service.utils.XmlConverUtil;
import com.sgm.dms.fol.returnallowance.api.ReturnAllowanceManagementService;
import com.sgm.dms.fol.returnallowance.dto.ReturnAllowanceDTO;
import com.sgm.dms.fol.returnallowance.dto.ReturnOrderDto;
import com.sgm.dms.fol.returnallowance.restclient.client.ReturnOrdersClient;
import com.sgm.dms.fol.returnallowance.restclient.dto.ReturnOrdersRespDto;
import com.sgm.dms.fol.returnallowance.restclient.dto.ReturnOrdersRespDto.PartClaimReturnOrderDto;
import com.sgm.solution.framework.urlaccess.annotations.ValidationRequestUrl;

/*
*
* @author st78sr
* 折让单管理Controller
* @date 2016年8月1日
*/
@RestController
@RequestMapping("/returnallowance")
public class ReturnAllowanceController extends BaseController {
	
	@Autowired
	private TokenUtils tokenUtils;
	
	@Autowired
	private ReturnOrdersClient returnOrdersClient;
    
    @Autowired
    private ReturnAllowanceManagementService returnAllowanceManagementService;
    
    /**
     * 查询折让单list
     */
    @RequestMapping("/query")
    @ValidationRequestUrl
    public PageListRespVo<ReturnAllowanceDTO> queryReturnAllowanceList(@RequestBody ReturnAllowanceVO returnallowanceVo,HttpServletRequest request,HttpServletResponse response) throws Exception{
    	returnallowanceVo.setApplyDateEnd(DateUtil.nextDate(returnallowanceVo.getApplyDateEnd()));
        ReturnAllowanceDTO returnAllowanceDTO = BeanMapper.map(returnallowanceVo, ReturnAllowanceDTO.class);
        // 查询折让单List
        List<ReturnAllowanceDTO> raResultList = returnAllowanceManagementService.queryReturnAllowanceList4Dealer(returnAllowanceDTO);
        
        // 查询折让单List count
        int total = returnAllowanceManagementService.countReturnAllowance4Dealer(returnAllowanceDTO);
        
        // 响应
        return new PageListRespVo<ReturnAllowanceDTO>(raResultList, total);
    }
    
    
    /**
     * 折让单-dealer作废
     */
    @RequestMapping("/delete")
    @ValidationRequestUrl
    public Object deleteReturnAllowanceById(@RequestBody ReturnAllowanceDTO returnAllowanceDTO,HttpServletRequest request,HttpServletResponse response) throws Exception{
        //校验折让单状态，只有“dealer待上报（已保存）”状态的，才能作废
        returnAllowanceManagementService.checkReturnAllowanceStatus4Dealer(returnAllowanceDTO,POConstant.RETURN_ALLOWANCE_STATUS_DLR_NOT_REPORTED);
        
        //折让单作废，单条作废
        int result = returnAllowanceManagementService.deleteReturnAllowanceById(returnAllowanceDTO);
        
        //响应
        return result;
    }
    
    /**
     * 获取退货证明列表
     * @author wangfl
     * @date 2016年8月18日
     * @param reqVo
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/getReturnOrders", method = RequestMethod.POST,consumes = "application/json", produces = "application/json;charset=UTF-8")
    @ValidationRequestUrl
    public PageListRespVo<PartClaimReturnOrderDto> getReturnOrders(@RequestBody PageVo reqVo,HttpServletRequest request,HttpServletResponse response) throws IOException{
    	//参数校验
    	if(null == reqVo.getBeginNo() || null == reqVo.getEndNo() || reqVo.getEndNo() <= reqVo.getBeginNo()) throw new VoNotValidException("请求参数错误");
    	
    	//调用退货证明列表获取接口调用client
    	ReturnOrdersRespDto returnOrdersRespDto = returnOrdersClient.getReturnOrders(reqVo.getBeginNo(), reqVo.getEndNo());
    	
    	//把退货单信息保存到session，方便保存折让单时取得退货证明的数据。
    	request.getSession().setAttribute(POConstant.CLAIM_RETURN_ORDERS_SESSION_NAME, returnOrdersRespDto.getReturnOrders());
    	
    	//组装响应对象
    	return new PageListRespVo<PartClaimReturnOrderDto>(returnOrdersRespDto.getReturnOrders(), returnOrdersRespDto.getTotal());
    }
    
    /**
     * 获取附件XML信息
     * @author wangfl
     * @date 2016年8月18日
     * @param fileId
     * @return
     * @throws Exception
     */
	@RequestMapping(value = "/getXmlInfo", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ValidationRequestUrl
	public ResponseEntity<String> getXmlInfo(@RequestParam("fileId") String fileId,HttpServletRequest request,HttpServletResponse response) throws Exception{
		//参数校验
		if(null == fileId || "".equals(fileId = fileId.trim())) throw new VoNotValidException("fileId不能为空");
		
    	//响应body体
    	String xmlInfoStr = XmlConverUtil.xmltoJson(fileId, tokenUtils.getTokenStr());//xml信息
    	String body = xmlInfoStr.substring(0, xmlInfoStr.length()-1) + "," + new JSONObject(new ResponseDTO()) + "]";//拼接上ResponseDTO
    	
    	//响应状态码
    	HttpStatus httpStatus = HttpStatus.OK;
    	
    	//组装响应对象
    	return new ResponseEntity<String>(body, httpStatus);
    }
	
	/**
	 * 进入新增或修改页面
	 *    折让单id为空，走新增
	 *    折让单id不为空，走更新
	 * @author wangfl
	 * @date 2016年8月18日
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/toAddOrUpdate", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ValidationRequestUrl
	public ReturnAllowanceInfoVo toAddOrUpdate(@RequestBody(required=false) ToUpdReqCommonVo reqVo, HttpServletRequest request, HttpServletResponse response) throws Exception{
		ReturnAllowanceInfoVo resultVo = new ReturnAllowanceInfoVo();
		
		if(reqVo==null || null == reqVo.getId()){//折让单id为空，走新增
			resultVo.setAllowanceNo(returnAllowanceManagementService.getAllowanceNoFlowNo());
			resultVo.setCustomerRemark(RSAUtil.decryptByPrivateKey(CookieUtil.getSapCode(request)));
		}else{//折让单id不为空，走更新
			ReturnAllowanceDTO returnAllowanceDto = returnAllowanceManagementService.getReturnAllowanceById(reqVo.getId());
			BeanMapper.copy(returnAllowanceDto, resultVo);//折让单信息
			ReturnOrderDto returnOrderDto = returnAllowanceManagementService.getReturnOrderById(returnAllowanceDto.getClaimReturnOrderId());
			resultVo.setReturnOrderInfo(BeanMapper.map(returnOrderDto, ReturnOrderInfoVo.class));//退货证明信息
		}
		
		//组装响应对象
		return resultVo;
	}
	
	/**
	 * 新增或修改
	 *    折让单id为空，走新增service
	 *    折让单id不为空，走更新service
	 * @author wangfl
	 * @date 2016年8月17日
	 * @param reqVo
	 * @param result
	 * @return 新增折让单的id
	 * @throws Exception 
	 * @throws IOException 
	 */
	@RequestMapping(value = "/addOrUpdate", method = RequestMethod.POST, consumes = "application/json", produces = "application/json;charset=UTF-8")
	@ValidationRequestUrl
	public Long addReturnAllowance(@Valid @RequestBody AllowanceAddReqVo reqVo, BindingResult result, HttpServletRequest request,HttpServletResponse response) throws IOException, Exception{
		//Vo请求参数校验
		validateVo(result);
		
		//处理service
		if (null == reqVo.getId()) {// 折让单id为空，走新增逻辑
			return returnAllowanceManagementService.saveReturnAllowance(BeanMapper.map(reqVo, ReturnAllowanceDTO.class), reqVo.getReturnOrderInfo());
		} else {// 折让单id不为空，走修改逻辑
			returnAllowanceManagementService.updateReturnAllowance(BeanMapper.map(reqVo, ReturnAllowanceDTO.class), reqVo.getReturnOrderInfo());
			return reqVo.getId();
		}
	}
	
	
	/**
	 * 上报
	 * @author wangfl
	 * @date 2016年8月9日
	 * @param reqVoList
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/report", method = RequestMethod.POST, consumes = "application/json", produces = "application/json;charset=UTF-8")
	@ValidationRequestUrl
	public ResponseDTO report(@RequestBody List<AllowanceReportReqVo> reqVoList, BindingResult result,HttpServletRequest request, HttpServletResponse response){
		StringBuffer errorMessage = new StringBuffer();//错误信息
		
		//循环上报每一个折让单
		AllowanceReportReqVo reqVo = null;
		for(int i = 0; i < reqVoList.size(); i++){
			reqVo = reqVoList.get(i);
			if(null == reqVo || null == reqVo.getId()) {
				errorMessage.append("第" + (i + 1) + "个折让单的id为空;");continue;
			}
			//上报service
			try {
				returnAllowanceManagementService.reportReturnAllowance(reqVo.getId());
			} catch (Exception e) {
				//ServiceBizException或者ServiceInvocationException，返回异常的e.getMessage()信息；其它异常，直接提示系统错误。
				errorMessage.append("第" + (i + 1) + "个折让单上报失败,").append((e instanceof ServiceBizException || e instanceof ServiceInvocationException
						? e.getMessage() : "系统错误;"));
			}
		}
		
		//有错误信息是，抛异常。
    	if(errorMessage.length() > 0) throw new ServiceBizException(errorMessage.toString());
		
    	//组装响应对象
		return new ResponseDTO();
	}
    
}
