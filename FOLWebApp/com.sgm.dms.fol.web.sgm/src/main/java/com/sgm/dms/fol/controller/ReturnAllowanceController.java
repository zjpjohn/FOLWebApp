/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : web.sgm
*
* @File name : ReturnAllowanceController.java
*
* @Author : st78sr
*
* @Date : 2016年8月8日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2016年8月8日    st78sr    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/
	
package com.sgm.dms.fol.controller;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.FileSystemException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sgm.dms.fol.common.api.constants.CookieConstant;
import com.sgm.dms.fol.common.api.constants.POConstant;
import com.sgm.dms.fol.common.api.domain.ResponseDTO;
import com.sgm.dms.fol.common.api.exception.ServiceBizException;
import com.sgm.dms.fol.common.api.exception.ServiceInvocationException;
import com.sgm.dms.fol.common.api.exception.VoNotValidException;
import com.sgm.dms.fol.common.service.controller.impl.BaseController;
import com.sgm.dms.fol.common.service.utils.AutoPojo;
import com.sgm.dms.fol.common.service.utils.BeanMapper;
import com.sgm.dms.fol.common.service.utils.CommonUtils;
import com.sgm.dms.fol.common.service.utils.DateUtil;
import com.sgm.dms.fol.common.service.utils.ExportTextUtil;
import com.sgm.dms.fol.common.service.utils.MapUtil;
import com.sgm.dms.fol.common.service.utils.SysException;
import com.sgm.dms.fol.common.service.utils.TokenUtils;
import com.sgm.dms.fol.common.service.utils.XmlConverUtil;
import com.sgm.dms.fol.dro.ReturnAllowanceDro;
import com.sgm.dms.fol.reconciliation.api.ReconciliationManagementService;
import com.sgm.dms.fol.returnallowance.api.ReturnAllowanceManagementService;
import com.sgm.dms.fol.returnallowance.dto.AllowanceInvoiceInfoDto;
import com.sgm.dms.fol.returnallowance.dto.ReturnAllowanceDTO;
import com.sgm.dms.fol.returnallowance.dto.ReturnOrderDto;
import com.sgm.dms.fol.vo.AllowanceBatchReqVo;
import com.sgm.dms.fol.vo.ReturnAllowanceInfoVo;
import com.sgm.dms.fol.vo.ReturnAllowanceResultVO;
import com.sgm.dms.fol.vo.ReturnAllowanceVO;
import com.sgm.dms.fol.vo.ToUpdReqCommonVo;
import com.sgm.solution.framework.dataAuthority.annotations.SGMValidationRequest;
import com.sgm.solution.framework.dataAuthority.annotations.SGMValidationResource;
import com.sgm.solution.framework.dataAuthority.annotations.SGMValidationUserInfo;
import com.sgm.solution.framework.dataAuthority.services.Encryptor;

/*
*
* @author st78sr
* TODO description
* @date 2016年8月8日
*/
@RestController
@RequestMapping("/returnallowance")
public class ReturnAllowanceController extends BaseController {
    
    @Autowired
    private TokenUtils tokenUtils;
    
    @Autowired
    private ExportTextUtil exportTextUtil;
    
    @Autowired
    private ReturnAllowanceManagementService returnAllowanceManagementService;
    
    @Autowired
    private ReconciliationManagementService reconciliationManagementService;
    
    /**
     * 查询折让单list(4SGM)
     */
    @RequestMapping("/query")
    public Object queryReturnAllowanceList(@RequestBody ReturnAllowanceVO returnallowanceVo,@CookieValue(CookieConstant.COOKIE_USER_ID) String userId) throws Exception{
        List<ReturnAllowanceDTO> raResultDtoList = null;
        List<ReturnAllowanceResultVO> raResultVOList = null;
//        System.err.println("zbw VO.companyCode.[].length: "+returnallowanceVo.getCompanyCode().size());
//        System.err.println("zbw endno: "+returnallowanceVo.getEndNo());
        // 输入参数
        ReturnAllowanceDTO returnAllowanceDTO = BeanMapper.map(returnallowanceVo, ReturnAllowanceDTO.class);
        
//        System.err.println("zbw InputDTO.companyCode.[].length: "+returnAllowanceDTO.getCompanyCode().size());
//        System.err.println("zbw2 endno: "+returnAllowanceDTO.getEndNo());
        // 查询折让单结果List
        returnAllowanceDTO.setApproveDateEnd(DateUtil.nextDate(returnAllowanceDTO.getApproveDateEnd()));
        raResultDtoList = returnAllowanceManagementService.queryReturnAllowanceList4SGM(returnAllowanceDTO);
        raResultVOList = BeanMapper.mapList(raResultDtoList, ReturnAllowanceResultVO.class);
        
        // 查询折让单结果List count
        int total = returnAllowanceManagementService.countReturnAllowance4SGM(returnAllowanceDTO);
//        System.err.println("zbw:   "+total);
        
        //获取签名
        for (ReturnAllowanceResultVO returnAllowanceResultVO : raResultVOList) {
        	String[] refVales=new String[]{returnAllowanceResultVO.getId().toString(),returnAllowanceResultVO.getReturnOrderNo(),returnAllowanceResultVO.getSapCode()};
        	String sign=Encryptor.generateSignFromResource(userId,refVales);	
        	returnAllowanceResultVO.setSign(sign);
		}
        
        // 响应
        return MapUtil.setQueryDataToMap(raResultVOList, Long.parseLong(total+""));
    }
    
    /**
     * 导出
     * @param request
     * @return
     * @throws ServiceBizException
     * @throws FileNotFoundException
     * @throws FileSystemException
     * @throws UnsupportedEncodingException
     * @throws SysException
     * @throws IOException
     */
    @RequestMapping("/exportListExcel")
    public ResponseEntity<byte[]> exportListExcel(HttpServletRequest request) throws ServiceBizException, FileNotFoundException, FileSystemException, UnsupportedEncodingException, SysException, IOException{
    	ReturnAllowanceVO reqDto = AutoPojo.bindRequestParam(request, ReturnAllowanceVO.class);
    	if(reqDto.getDealerName()!=null){
    		reqDto.setDealerName(URLEncoder.encode(reqDto.getDealerName(), "UTF-8"));
    	}
    	
    	if(reqDto.getApproveDateEnd()!=null){
    		reqDto.setApproveDateEnd(DateUtil.nextDate(reqDto.getApproveDateEnd()));
    	}
    	
    	List<ReturnAllowanceDTO> resultList = returnAllowanceManagementService.queryReturnAllowanceList4SGM(BeanMapper.map(reqDto, ReturnAllowanceDTO.class));
    	
    	// 定义文件名称
    	String fileName = "RETURN_ALLOWANCE_" + DateUtil.formatDate(new Date()) + ".xls";
    			
    	// 设置导出文件header
    	String[] header = {"ASC代码", "ASC名称 ", "快递单号", "折让单号", "金额（不含税） ", "税额 ", "退货证明单号 ", "索赔发票号 ", "销售发票号", "受理日期", "折让单状态", "所属公司"};
    	
    	//响应header头
    	HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", fileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
    	
        List<ReturnAllowanceDro> droList = BeanMapper.mapList(resultList, ReturnAllowanceDro.class);
        
    	// 导出文件
        ByteArrayOutputStream bos = reconciliationManagementService.<ReturnAllowanceDro> exportXls4BillFile(droList, null, header, fileName);

        return new ResponseEntity<byte[]>(bos.toByteArray(), headers, HttpStatus.CREATED);
    }
    
    /**
     * 根据id查询折让单信息
     * @author wangfl
     * @date 2016年8月23日
     * @param reqVo
     * @return
     */
    @RequestMapping(value = "/queryReturnAllowanceById", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public ReturnAllowanceInfoVo queryReturnAllowanceById(@RequestBody ToUpdReqCommonVo reqVo) {
    	//参数校验
    	if(null == reqVo.getId()) throw new VoNotValidException("id不能为空");
    	
    	//组装响应对象
    	return BeanMapper.map(returnAllowanceManagementService.getReturnAllowanceById(reqVo.getId()), ReturnAllowanceInfoVo.class);
	}
    
    /**
     * 根据退货证明ID，查询退货证明ReturnOrder的明细(4SGM)
     */
    @RequestMapping("/queryReturnOrderDetailByRoId")
    public Object queryReturnOrderDetailByRoId(@RequestBody Map<?,?> returnOrderIdMap) throws Exception{
        ReturnOrderDto returnOrderDto = returnAllowanceManagementService.queryReturnOrderDetailByRoId(((Integer)returnOrderIdMap.get("returnOrderId")).longValue());
        
        List<ReturnOrderDto> returnOrderDtoList = new ArrayList<ReturnOrderDto>();
        returnOrderDtoList.add(returnOrderDto);
        
        // 响应
        return MapUtil.setQueryDataToMap(returnOrderDtoList, Long.parseLong("1"));
    }
    
    
    /**
     * 根据退货证明ID，查询退货证明ReturnOrder对应的发票明细(4SGM)
     */
    @RequestMapping("/queryInvoiceDetailByRoId")
    public Object queryInvoiceDetailByRoId(@RequestBody Map<?,?> returnOrderIdMap) throws Exception{
        List<AllowanceInvoiceInfoDto> allowanceInvoiceInfoDtoList = returnAllowanceManagementService.queryInvoiceDetailByRoId(((Integer)returnOrderIdMap.get("returnOrderId")).longValue());
        
        // 查询退货证明对应的invoice list count
        int total = allowanceInvoiceInfoDtoList.size();
        
        // 响应
        return MapUtil.setQueryDataToMap(allowanceInvoiceInfoDtoList, Long.parseLong(total+""));
    }
    
    
    /**
     * 开票
     * @author wangfl
     * @date 2016年8月11日
     * @param reqVoList
     * @return
     */
    @RequestMapping(value = "/billing", method = RequestMethod.POST, consumes = "application/json", produces = "application/json;charset=UTF-8")
    @SGMValidationRequest
    public ResponseDTO billing(@RequestBody @SGMValidationResource List<AllowanceBatchReqVo> reqVoList,@SGMValidationUserInfo @CookieValue(CookieConstant.COOKIE_USER_ID) String userId,HttpServletRequest request){
    	StringBuffer errorMessage = new StringBuffer();//错误信息
    	
    	//循环开票每一个折让单
    	for (int i = 0; i < reqVoList.size(); i++) {
    		AllowanceBatchReqVo tempReqVo = reqVoList.get(i);
    		
			if (null == tempReqVo || null == tempReqVo.getId()) {
				errorMessage.append("第" + (i + 1) + "个折让单的id为空；");
				continue;
			}
			
			try {
				//开票service
				returnAllowanceManagementService.billing(tempReqVo.getId(), tempReqVo.getApproveMsg());
			} catch (Exception e) {
				//ServiceBizException或者ServiceInvocationException，返回异常的e.getMessage()信息；其它异常，直接提示系统错误。
				errorMessage.append((e instanceof ServiceBizException || e instanceof ServiceInvocationException
						? e.getMessage() : "系统错误") + "，第" + (i + 1) + "个折让单开票失败;");
			}
    		
		}
    	
    	//有错误信息是，抛异常。
    	if(errorMessage.length() > 0) throw new ServiceBizException(errorMessage.toString());
    	
    	//组装响应对象
    	return new ResponseDTO();
    }
    
    
    /**
     * 批量导出开票文件
     * @author wangfl
     * @date 2016年8月11日
     * @param ids 折让单id，多个id用逗号分开
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "/exportBillingFile", method = RequestMethod.GET)
    public ResponseEntity<byte[]> exportBillingFile(@RequestParam String ids, HttpServletResponse resoponse) throws Exception{
    	try{
		    	//参数校验
		    	if(null == ids || "".equals(ids = ids.trim()) || ",".equals(ids)) throw new VoNotValidException("请求参数不能为空");
		    	
		    	//响应body体
		    	String bodyContent = returnAllowanceManagementService.getBillingFileContent(ids);
		    	//byte[] body = bodyContent.getBytes();//不建议直接下载文件，应先上传到文件服务器再从文件服务器下载。
		    	byte[] body = exportTextUtil.exportText(bodyContent);
		    	
		    	//响应header头
		    	HttpHeaders headers = new HttpHeaders();
		    	headers.setContentDispositionFormData("attachment", "ALLOWANCE_BILLING_"+ DateUtil.formatDate(new Date()) + ".txt");
		    	headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		    	
		    	//响应状态码
		    	HttpStatus httpStatus = HttpStatus.OK;
		    	
		    	//sap处理成功
		    	returnAllowanceManagementService.updateReturnAllowanceStatus(ids, POConstant.RETURN_ALLOWANCE_STATUS_SAP_SUCCESS);
		    	
		    	//组装响应对象
		    	return new ResponseEntity<byte[]>(body, headers, httpStatus);
    	}catch(Exception e){
    		resoponse.sendError(500, e.getMessage());
    	}
    	return null;
    }
    
    /**
     * 折让单拒绝(4SGM)
     * 只有状态为“dealer已提交sgm未审批”和“sgm已审批通过”的折让单，可以拒绝
     */
    @RequestMapping(value = "/reject", method = RequestMethod.POST, consumes = "application/json", produces = "application/json;charset=UTF-8")
    @SGMValidationRequest
    public Object rejectReturnAllowance(@SGMValidationResource @RequestBody List<AllowanceBatchReqVo> allowanceBatchReqVoList,@SGMValidationUserInfo @CookieValue(CookieConstant.COOKIE_USER_ID) String userId) throws Exception{
        
        StringBuffer errorMessage = new StringBuffer();//错误信息
        
        List<ReturnAllowanceDTO> returnallowanceDTOList=BeanMapper.mapList(allowanceBatchReqVoList, ReturnAllowanceDTO.class);
        
        //先校验list中所有折让单状态是符合要求的
        returnAllowanceManagementService.checkReturnAllowanceStatus4SGM(returnallowanceDTOList);
        
        //循环，拒绝每一个折让单
        for (int i = 0; i < returnallowanceDTOList.size(); i++) {
            //请求service，更新FOL折让单状态POConstant.RETURN_ALLOWANCE_STATUS_SGM_FAIL，调用POL接口更新折让单信息
            ReturnAllowanceDTO returnAllowanceDTO = returnallowanceDTOList.get(i);
            
            try{
                //拒绝折让单Service
                //输入参数：折让单ID,退货证明单号,经销商代码
            	CommonUtils.filterSpecialWord(returnAllowanceDTO);
                returnAllowanceManagementService.rejectReturnAllowance4SGM(returnAllowanceDTO.getId(),returnAllowanceDTO.getReturnOrderNo(),returnAllowanceDTO.getSapCode(),returnAllowanceDTO.getApproveMsg());
            } catch(Exception e) {
                e.printStackTrace();
                errorMessage.append(e instanceof ServiceBizException || e instanceof ServiceInvocationException
                        ? e.getMessage() : "系统错误;");
            }
        }
        
        //有错误信息是，抛异常。
        if(errorMessage.length() > 0) throw new ServiceBizException(errorMessage.toString());
        
        // 响应
        return 1;
    }
    
    /**
     * 获取附件XML信息
     * @author wangfl
     * @date 2016年8月23日
     * @param fileId
     * @return
     * @throws Exception
     */
	@RequestMapping(value = "/getXmlInfo", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity<String> getXmlInfo(@RequestParam("fileId") String fileId) throws Exception{
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
}
