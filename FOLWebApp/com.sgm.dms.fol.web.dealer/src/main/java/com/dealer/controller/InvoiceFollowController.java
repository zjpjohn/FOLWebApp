
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : web.dealer
 *
 * @File name : InvoiceFollowController.java
 *
 * @Author : Lujinglei
 *
 * @Date : 2016年5月9日
 *
----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2016年5月9日    Lujinglei    1.0
 *
 *
 *
 *
----------------------------------------------------------------------------------
 */
	
package com.dealer.controller;

import java.io.ByteArrayOutputStream;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dealer.dro.InvoiceFollowDro;
import com.dealer.vo.InvoiceFollowVo;
import com.sgm.dms.fol.common.api.constants.CookieConstant;
import com.sgm.dms.fol.common.api.exception.ServiceBizException;
import com.sgm.dms.fol.common.service.controller.impl.BaseController;
import com.sgm.dms.fol.common.service.utils.AutoPojo;
import com.sgm.dms.fol.common.service.utils.BeanMapper;
import com.sgm.dms.fol.common.service.utils.CookieUtil;
import com.sgm.dms.fol.common.service.utils.DateUtil;
import com.sgm.dms.fol.common.service.utils.MapUtil;
import com.sgm.dms.fol.common.service.utils.RSAUtil;
import com.sgm.dms.fol.common.service.utils.StringUtil;
import com.sgm.dms.fol.invoice.api.InvoiceFollowService;
import com.sgm.dms.fol.invoice.dto.InvoiceFollowDTO;
import com.sgm.dms.fol.reconciliation.api.ReconciliationManagementService;
import com.sgm.solution.framework.dataAuthority.annotations.SGMValidationResource;
import com.sgm.solution.framework.dataAuthority.annotations.SGMValidationUserInfo;
import com.sgm.solution.framework.dataAuthority.services.Encryptor;
import com.sgm.solution.framework.urlaccess.annotations.ValidationRequestUrl;

/**
 *
 * @author Lujinglei
 * description 索赔发票跟踪查询
 * @date 2016年5月9日
 */
@Controller
@RequestMapping("/wrOrder/invoiceFollow")
public class InvoiceFollowController extends BaseController{
    
    protected Logger logger= LogManager.getLogger(this.getClass());

    @Autowired
    private InvoiceFollowService invoiceFollowService;
    
    @Autowired
    private ReconciliationManagementService reconciliationManagementService;
    /**
     * 
     *
     * @author Lujinglei
     * description 索赔发票跟踪查询
     * @date 2016年5月9日
     * @param invoiceFollowVo
     * @return
     * @throws ServiceBizException
     * @throws SQLException
     */
    @RequestMapping(value = "/query", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ValidationRequestUrl
    public Object query(@Validated @RequestBody InvoiceFollowVo invoiceFollowVo,HttpServletRequest request,HttpServletResponse response,@CookieValue(CookieConstant.COOKIE_USER_ID) String userId) throws Throwable{
    	//调用invoiceFollowService查询索赔发票信息
        List<InvoiceFollowDTO> invocielist=findInvoiceFollow(invoiceFollowVo, request);
        
        //批量转换成VO
        List<InvoiceFollowVo> voList = BeanMapper.mapList(invocielist, InvoiceFollowVo.class);
        
        //获取签名
        getSign(voList,request,userId);
        
        //金额转换成财务专用的模式
        StringUtil.fontFormatFinance(voList);
        
        //设置前台需要的对象
        Map<String, Object> responsedata = MapUtil.setQueryDataToMap(voList, Long.valueOf(getQueryTotalSize(invoiceFollowVo,request)));

        return responsedata;
        
    }
    private List<InvoiceFollowDTO> findInvoiceFollow(InvoiceFollowVo invoiceFollowVo,HttpServletRequest request) throws Exception {
        InvoiceFollowDTO dto = BeanMapper.map(invoiceFollowVo, InvoiceFollowDTO.class);
        dto.setSapCode(RSAUtil.decryptByPrivateKey(CookieUtil.getSapCode(request)));
        List<InvoiceFollowDTO> invocielist = invoiceFollowService.getInvoiceFollow(dto);
        
        return invocielist;
    }
    private int getQueryTotalSize(InvoiceFollowVo invoiceFollowVo,HttpServletRequest request) throws Exception {
        invoiceFollowVo.setBeginNo(null);
        invoiceFollowVo.setEndNo(null);
        List<InvoiceFollowDTO> reservedtos = findReserveAmountByDTO(invoiceFollowVo,request);
        return reservedtos.size();
    }
    private List<InvoiceFollowDTO> findReserveAmountByDTO(InvoiceFollowVo invoiceFollowVo,HttpServletRequest request)
            throws Exception {
        InvoiceFollowDTO searchDto = BeanMapper.map(invoiceFollowVo, InvoiceFollowDTO.class);
        searchDto.setSapCode(RSAUtil.decryptByPrivateKey(CookieUtil.getSapCode(request)));
        List<InvoiceFollowDTO> data = invoiceFollowService.getInvoiceFollow(searchDto);

        logger.info("=====查询完成======");
        return data;
    }
    
    @RequestMapping(value = "/deatil", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    @ValidationRequestUrl
    public Object queryDeatil(@Validated @RequestBody InvoiceFollowVo invoiceFollowVo,HttpServletRequest request,HttpServletResponse response) throws Throwable{
        
        InvoiceFollowDTO dto = BeanMapper.map(invoiceFollowVo, InvoiceFollowDTO.class);
        
        dto.setSapCode(RSAUtil.decryptByPrivateKey(CookieUtil.getSapCode(request)));
        //调用invoiceFollowService查询索赔发票信息
        List<InvoiceFollowDTO> invocielist = invoiceFollowService.getInvoiceDeatil(dto);
        //批量转换成VO
        List<InvoiceFollowVo> voList = BeanMapper.mapList(invocielist, InvoiceFollowVo.class);
        //金额转换成财务专用的模式
        StringUtil.fontFormatFinance(voList);
        //设置前台需要的对象
        Map<String, Object> responsedata = MapUtil.setQueryDataToMap(voList, Long.valueOf(invocielist.size()));
        
        return responsedata;
        
    }
    @RequestMapping(value = "/cancel",method = RequestMethod.POST, produces = "application/json" )
    @ResponseBody
    @ValidationRequestUrl
    public Object cancel(@RequestBody  @SGMValidationResource List<InvoiceFollowVo> invoiceFollowVoList,@SGMValidationUserInfo @CookieValue(CookieConstant.COOKIE_USER_ID) String userId,HttpServletRequest request,HttpServletResponse response) throws Throwable{

        groupCancel(invoiceFollowVoList);
        
        return true;
    }
    /**
     *  
     * @param voList
     * @throws Exception
     */
    private void groupCancel(List<InvoiceFollowVo> voList) throws Exception{
        if (null != voList) {
            for (InvoiceFollowVo tVo : voList) {
                InvoiceFollowDTO invoiceFollow = BeanMapper.map(tVo, InvoiceFollowDTO.class);
                invoiceFollowService.cancelInvoice(invoiceFollow);
            }
        }
    }
    
    @RequestMapping(value = "/anew" ,method = RequestMethod.POST, produces = "application/json" )
    @ResponseBody
    @ValidationRequestUrl
    public Object anew(@RequestBody  @SGMValidationResource List<InvoiceFollowVo> invoiceFollowVo,@SGMValidationUserInfo @CookieValue(CookieConstant.COOKIE_USER_ID) String userId,HttpServletRequest request,HttpServletResponse response) throws Throwable{
    	
        groupAnew(invoiceFollowVo);
        
        return true;
    }
    /**
     * 
     * @param voList
     * @throws Exception
     */
    private void groupAnew(List<InvoiceFollowVo> voList) throws Exception{
        if (null != voList) {
            for (InvoiceFollowVo tVo : voList) {
                InvoiceFollowDTO invoiceFollow = BeanMapper.map(tVo, InvoiceFollowDTO.class);
                invoiceFollowService.anewInvoice(invoiceFollow);
            }
        }
    }

    @RequestMapping("/invoiceFollowDeatilExp")
    @ValidationRequestUrl
    public ResponseEntity<byte[]> exportMonthDeatilResult(HttpServletRequest request,HttpServletResponse response) throws Throwable {
        InvoiceFollowVo vo = AutoPojo.bindRequestParam(request, InvoiceFollowVo.class);
        InvoiceFollowDTO invoiceFollowDTO = BeanMapper.map(vo, InvoiceFollowDTO.class);
        invoiceFollowDTO.setSapCode(RSAUtil.decryptByPrivateKey(CookieUtil.getSapCode(request)));
        // 设置标题
        String[] header = {"序号","索赔类型","配件费用","工时费用", "其他费用", "税额","不含税金额","含税金额",};
        // 查询数据
        List<InvoiceFollowDTO> data = invoiceFollowService.getInvoiceDeatil(invoiceFollowDTO);
                

        // 转换为要导出的数据
        List<InvoiceFollowDro> expList = BeanMapper.mapList(data,InvoiceFollowDro.class);
        
        StringUtil.fontFormatFinance(expList);
        // 设置序号
        setLineNumber(expList);
        // 定义文件名称
        String fileName = "Bonus_monthDeatil" + DateUtil.date2str(new Date())
                + ".xls";
        // 导出
        ByteArrayOutputStream bos = reconciliationManagementService
                .<InvoiceFollowDro> exportXls4BillFiles(expList,
                        vo.getToken(), header, fileName);
        return new ResponseEntity<byte[]>(bos.toByteArray(),
                getHeaders(fileName), HttpStatus.CREATED);

    }

    /**
     * 获取headers信息
     */
    private HttpHeaders getHeaders(String fileName) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", fileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return headers;
    }
    private void setLineNumber(List<InvoiceFollowDro> expList) {
        if (null != expList && expList.size() > 0) {
            for (int i = 0; i < expList.size(); i++) {
                InvoiceFollowDro vo = expList.get(i);
                vo.setNum(i + 1);
            }
        }
    }
//    @RequestMapping("/getSign")
//    @ResponseBody
//    public Object getSign(@RequestBody List<InvoiceFollowVo> invoiceFollowVoList,HttpServletRequest request,@CookieValue(CookieConstant.COOKIE_USER_ID) String userId) throws Throwable{
//        for (InvoiceFollowVo invoicefollowvo : invoiceFollowVoList) {
//            String[] refVales=new String[]{String.valueOf(invoicefollowvo.getStatus())};
//            String sign=Encryptor.generateSignFromResource(userId,refVales);
//            invoicefollowvo.setSign(sign);
//		}
//        
//        return invoiceFollowVoList;
//    }
    

    public List<InvoiceFollowVo> getSign(List<InvoiceFollowVo> invoiceFollowVoList,HttpServletRequest request,String userId) throws Throwable{
        for (InvoiceFollowVo invoicefollowvo : invoiceFollowVoList) {
            String[] refVales=new String[]{String.valueOf(invoicefollowvo.getStatus())};
            String sign=Encryptor.generateSignFromResource(userId,refVales);
            invoicefollowvo.setSign(sign);
		}
        
        return invoiceFollowVoList;
    }
}
