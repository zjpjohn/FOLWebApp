
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : web.sgm
 *
 * @File name : FinancialCertificateController.java
 *
 * @Author : Lujinglei
 *
 * @Date : 2016年5月10日
 *
----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2016年5月10日    Lujinglei    1.0
 *
 *
 *
 *
----------------------------------------------------------------------------------
 */
	
package com.sgm.dms.fol.controller;

import java.io.ByteArrayOutputStream;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sgm.dms.fol.common.service.controller.impl.BaseController;
import com.sgm.dms.fol.common.service.utils.AutoPojo;
import com.sgm.dms.fol.common.service.utils.BeanMapper;
import com.sgm.dms.fol.common.service.utils.DateUtil;
import com.sgm.dms.fol.common.service.utils.MapUtil;
import com.sgm.dms.fol.common.service.utils.StringUtil;
import com.sgm.dms.fol.dro.FinancialCertificateDro;
import com.sgm.dms.fol.invoice.api.FinancialCertificateService;
import com.sgm.dms.fol.invoice.dto.InvoiceFollowDTO;
import com.sgm.dms.fol.reconciliation.api.ReconciliationManagementService;
import com.sgm.dms.fol.vo.FinancialCertificateVo;

/**
 *
 * @author Lujinglei
 * description SAP每日财务凭证查询
 * @date 2016年5月10日
 */
@Controller
@RequestMapping("/financial/certificate")
public class FinancialCertificateController extends BaseController{

    protected Logger logger= LogManager.getLogger(this.getClass());
    @Autowired
    private FinancialCertificateService financialCertificateService;
    @Autowired
    private ReconciliationManagementService reconciliationManagementService;
    
    @RequestMapping(value = "/query",method=RequestMethod.POST)
    @ResponseBody
    public Object query(@RequestBody FinancialCertificateVo financialCertificateVo) throws Throwable{
        
        InvoiceFollowDTO dto = BeanMapper.map(financialCertificateVo, InvoiceFollowDTO.class);
        //调用FinancialCertificateService
        List<InvoiceFollowDTO> invocielist = financialCertificateService.getSAPFinancialCertificate(dto);
        //批量转换成VO
        List<FinancialCertificateVo> voList = BeanMapper.mapList(invocielist, FinancialCertificateVo.class);
        //设置前台需要的对象
        Map<String, Object> responsedata = MapUtil.setQueryDataToMap(voList, Long.valueOf(getQueryTotalSize(financialCertificateVo)));
        
        return responsedata;
        
    }
    private int getQueryTotalSize(FinancialCertificateVo financialCertificateVo) throws Exception {
        financialCertificateVo.setBeginNo(null);
        financialCertificateVo.setEndNo(null);
        List<InvoiceFollowDTO> reservedtos = findReserveAmountByDTO(financialCertificateVo);
        return reservedtos.size();
    }
    private List<InvoiceFollowDTO> findReserveAmountByDTO(FinancialCertificateVo financialCertificateVo)
            throws Exception {
        InvoiceFollowDTO searchDto = BeanMapper.map(financialCertificateVo, InvoiceFollowDTO.class);
        List<InvoiceFollowDTO> data = financialCertificateService.getSAPFinancialCertificate(searchDto);

        logger.info("=====查询完成======");
        return data;
    }
    @RequestMapping("/invoiceDeatilExp")
    public ResponseEntity<byte[]> exportDeatilResult(HttpServletRequest request) throws Throwable {
        
        FinancialCertificateVo vo = AutoPojo.bindRequestParam(request, FinancialCertificateVo.class);
        if (vo.getApproveMan() != null) {
            vo.setApproveMan(URLDecoder.decode(
                    vo.getApproveMan(), "UTF-8"));
        }
        InvoiceFollowDTO invoiceFollowDTO = BeanMapper.map(vo, InvoiceFollowDTO.class);
        // 设置标题
        String[] header = {"序号","提交SGM日期 ","经销商代码","发票号","发票凭证  ","金额","税额 ","总金额 ","审核人 " };
        // 查询数据
        List<InvoiceFollowDTO> data = financialCertificateService.getSAPFinancialCertificate(invoiceFollowDTO);
        // 转换为要导出的数据
        List<FinancialCertificateDro> expList = BeanMapper.mapList(data,FinancialCertificateDro.class);
        
        StringUtil.fontFormatFinance(expList);
        // 设置序号
        setLineNumber(expList);
        // 定义文件名称
        String fileName = "Bonus_monthDeatil" + DateUtil.date2str(new Date())+ ".xls";
        // 导出
        ByteArrayOutputStream bos = reconciliationManagementService
                .<FinancialCertificateDro> exportXls4BillFiles(expList,
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
    private void setLineNumber(List<FinancialCertificateDro> expList) {
        if (null != expList && expList.size() > 0) {
            for (int i = 0; i < expList.size(); i++) {
                FinancialCertificateDro vo = expList.get(i);
                vo.setNum(i + 1);
            }
        }
    }
    
}
