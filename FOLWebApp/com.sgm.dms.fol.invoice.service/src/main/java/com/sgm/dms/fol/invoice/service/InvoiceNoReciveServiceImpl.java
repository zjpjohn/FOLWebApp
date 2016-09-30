
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : invoice.service
 *
 * @File name : InvoiceNoReciveServiceImpl.java
 *
 * @Author : Lujinglei
 *
 * @Date : 2016年5月27日
 *
----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2016年5月27日    Lujinglei    1.0
 *
 *
 *
 *
----------------------------------------------------------------------------------
 */
	
package com.sgm.dms.fol.invoice.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.Holder;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsClientProxy;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.sgm.dms.fol.common.api.constants.ClaimInvoiceConstants;
import com.sgm.dms.fol.common.api.constants.CodeConstant;
import com.sgm.dms.fol.common.api.exception.ServiceAppException;
import com.sgm.dms.fol.common.api.exception.ServiceBizException;
import com.sgm.dms.fol.common.service.utils.BeanMapper;
import com.sgm.dms.fol.common.service.utils.PropertiesUtil;
import com.sgm.dms.fol.invoice.api.InvoiceNoReciveService;
import com.sgm.dms.fol.invoice.client.FOLSAPZFICREATEDOCDMSPT;
import com.sgm.dms.fol.invoice.client.FOLSAPZFICREATEDOCDMSPTBindingQSService;
import com.sgm.dms.fol.invoice.dto.InvoiceFollowDTO;
import com.sgm.dms.fol.invoice.dto.InvoiceInterfaceDTO;
import com.sgm.dms.fol.invoice.dto.InvoiceNoInfoDTO;
import com.sgm.dms.fol.invoice.dto.InvoiceSubjectDTO;
import com.sgm.dms.fol.invoice.dto.SapReturnItemsDTO;
import com.sgm.dms.fol.invoice.functions.ZFICREATEDOCDMS;
import com.sgm.dms.fol.invoice.functions.ZFICREATEDOCDMSRESPONSE;
import com.sgm.dms.fol.invoice.functions.ZFIDMSITEM;
import com.sgm.dms.fol.invoice.functions.ZFIDMSOUT;
import com.sgm.dms.fol.invoice.functions.ZFIDMSTABLE;
import com.sgm.dms.fol.invoice.repository.ClaimInvoiceDao;
import com.sgm.dms.fol.reserve.client.ReserveFundRequestVO;
import com.sgm.dms.fol.reserve.client.ReserveFundResultVO;
import com.sgm.dms.fol.reserve.client.ReserveFundService;
import com.sgm.dms.fol.reserve.client.ReserveOperationWebService;
import com.sgm.dms.fol.reserve.client.TranformReserveFund;
import com.sgm.dms.fol.reserve.client.TranformReserveFundResponse;
import com.sgm.dms.fol.reserve.common.CommonHelper;

/**
 *
 * @author Lujinglei
 * description 
 * @date 2016年5月27日
 */
@Service
public class InvoiceNoReciveServiceImpl implements InvoiceNoReciveService{

        
    // 日志
    protected Logger logger = LogManager.getLogger(this.getClass());
    
    @Autowired
    private ClaimInvoiceDao claimInvoiceDao ;


    
    /**
     * 调用sap生成索赔发票凭证号
     * @author Lujinglei
     * @date 2016年6月1日
     * @param invoiceNo
     * @return
     * @throws Exception
     * (non-Javadoc)
     * @see com.sgm.dms.fol.invoice.api.InvoiceNoReciveService#invoiceNo(java.lang.String)
     */
    	
    @Override 
    @Transactional(rollbackFor=Exception.class)
    public ZFIDMSOUT invoiceNoRecive(InvoiceNoInfoDTO invoiceNoInfo,ZFIDMSOUT result) throws ServiceAppException{ 
            if(invoiceNoInfo == null){
                throw new ServiceBizException("发票信息为空,请检查请求参数");
            }
            
            try {   
            	//保存接口表数据
            	saveDisposeItem(invoiceNoInfo);
                
                //从配置文件中处理成功和失败状态
                String model = PropertiesUtil.getProperty("invoke.method.model.invokeSap");
               
                if(model!=null && !Boolean.valueOf(model)){
                    throw new ServiceBizException("SAP处理失败！");
                } 
                //调用SAP
                result = invoiceCertificate(invoiceNoInfo,result);

                if(result.getBELNR()==null||"".equals(result.getBELNR().toString().trim())){
                    throw new ServiceBizException("SAP处理失败！");
                }
                
                claimInvoiceDao.updateSapDealStatus(result.getXBLNR(),result.getKUNNR(),ClaimInvoiceConstants.SAP_DEAL_SUCCESS);
                
                SapReturnItemsDTO items = new SapReturnItemsDTO();
                items.setAscCode(result.getKUNNR());
                items.setCo_code(result.getBUKRS());
                items.setFinanceWarranty(result.getBELNR());
                items.setRemark(result.getMSG());
                items.setInvoiceNo(result.getXBLNR());
                items.setProcess_status(ClaimInvoiceConstants.SAP_DEAL_SUCCESS);
                //保存SAP返回数据
                claimInvoiceDao.saveInvoiceCertificate(items);
                InvoiceFollowDTO res = new InvoiceFollowDTO();
                res.setInvoiceCertificate(result.getBELNR());
                res.setInvoiceNo(result.getXBLNR());
                claimInvoiceDao.saveCertificate(res);
                updateReserveAmount(invoiceNoInfo);
            } catch (Exception e) {
            	e.printStackTrace();
                logger.error("error:",e);
                throw(new ServiceAppException(e)); 
            } 
            return  result;
    }
    
    @Override
    @Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRES_NEW)
    public void resultDispose(InvoiceNoInfoDTO invoiceNoInfoDTO,ZFIDMSOUT result) throws ServiceAppException{
    	//不管如何都会更新状态
    	if(null == invoiceNoInfoDTO) return;
    	try {
	    	claimInvoiceDao.updateSapDealStatus(invoiceNoInfoDTO.getInvoiceNo(),invoiceNoInfoDTO.getSapCode(),ClaimInvoiceConstants.SAP_DEAL_FAIL);
	    	
	    	SapReturnItemsDTO items = new SapReturnItemsDTO();
	        if(result.getKUNNR() != null&&!"".equals(result.getKUNNR().trim())){
	            //保存返回结果
	            items.setAscCode(result.getKUNNR());
	            items.setCo_code(result.getBUKRS());
	            items.setFinanceWarranty(result.getBELNR());
	            items.setRemark(result.getMSG());
	            items.setInvoiceNo(result.getXBLNR());
	            items.setProcess_status(ClaimInvoiceConstants.SAP_DEAL_FAIL);
	            //保存SAP返回数据
	            claimInvoiceDao.saveInvoiceCertificate(items);
	        }else{
	            items.setAscCode(invoiceNoInfoDTO.getSapCode());
	            items.setCo_code(ClaimInvoiceConstants.CO_CODE);
	            items.setFinanceWarranty("");
	            items.setRemark("FOL DISPOSE ERROR");
	            items.setInvoiceNo(invoiceNoInfoDTO.getInvoiceNo());
	            items.setProcess_status(ClaimInvoiceConstants.FOL_DEAL_FAIL);
	            //保存SAP返回数据
	            claimInvoiceDao.saveInvoiceCertificate(items);
	        }

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
			throw(new ServiceAppException(e));
		}
    }
    
    @Override
    @Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRES_NEW)
    public void resultDispose(String invoiceNo,ZFIDMSOUT result) throws ServiceAppException{
    	//不管如何都会更新状态
    	try {
	    	claimInvoiceDao.updateInoviceStatus(invoiceNo,ClaimInvoiceConstants.SAP_DEAL_FAIL);
	    	
	    	SapReturnItemsDTO items = new SapReturnItemsDTO();
	        if(result.getKUNNR() != null&&!"".equals(result.getKUNNR().trim())){
	            //保存返回结果
	            items.setAscCode(result.getKUNNR());
	            items.setCo_code(result.getBUKRS());
	            items.setFinanceWarranty(result.getBELNR());
	            items.setRemark(result.getMSG());
	            items.setInvoiceNo(result.getXBLNR());
	            items.setProcess_status(ClaimInvoiceConstants.SAP_DEAL_FAIL);
	            //保存SAP返回数据
	            claimInvoiceDao.saveInvoiceCertificate(items);
	        }else if(!StringUtils.isEmpty(invoiceNo)){
	        	InvoiceNoInfoDTO invoiceNoInfoDTO=claimInvoiceDao.findInvoiceInfoByInvoiceNo(invoiceNo);
	            items.setAscCode(invoiceNoInfoDTO.getSapCode());
	            items.setCo_code(ClaimInvoiceConstants.CO_CODE);
	            items.setFinanceWarranty("");
	            items.setRemark("FOL DISPOSE ERROR");
	            items.setInvoiceNo(invoiceNoInfoDTO.getInvoiceNo());
	            items.setProcess_status(ClaimInvoiceConstants.FOL_DEAL_FAIL);
	            //保存SAP返回数据
	            claimInvoiceDao.saveInvoiceCertificate(items);
	        }
	        
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
			throw(new ServiceAppException(e));
		}
    }
    
    private void saveAgainDisposeItem(InvoiceNoInfoDTO invoiceNoInfo) throws ServiceAppException{
        
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        try {
            InvoiceInterfaceDTO request = new InvoiceInterfaceDTO();
            
            request.setAscCode(invoiceNoInfo.getSapCode());
            request.setCurrency(ClaimInvoiceConstants.WAERS);
            request.setDocumentDate(String.valueOf(sf.format(date)));
            request.setDocumentHeadtext(ClaimInvoiceConstants.BKTXT);
            request.setDocunmentType(invoiceNoInfo.getCertificateType());
            request.setExpressNo(invoiceNoInfo.getExpressNo());
            request.setGross(invoiceNoInfo.getGross());
            request.setInvoiceNo(invoiceNoInfo.getInvoiceNo());
            request.setInvoiceTitle(ClaimInvoiceConstants.INVOICE_TITLE);
            request.setLabourCost(invoiceNoInfo.getLabourCost());
            request.setLinetotal(invoiceNoInfo.getLinetotal());
            request.setOtherCost(invoiceNoInfo.getOtherCost());
            request.setPartCost(invoiceNoInfo.getPartCost());
            request.setPostDate(String.valueOf(sf.format(date)));
            request.setPostPeriod(String.valueOf(sf.format(date).substring(5,6)));
            request.setProcessStatus(invoiceNoInfo.getProcessStatus());
            request.setRemark(invoiceNoInfo.getRemark());
            request.setTax(invoiceNoInfo.getTax());
            request.setValid(invoiceNoInfo.getValid());
            claimInvoiceDao.saveRequestSAP(request);
            
            InvoiceSubjectDTO brItem = new InvoiceSubjectDTO();
            //贷方数据
            brItem.setInvoiceId(request.getId());
            brItem.setAmount(invoiceNoInfo.getGross());
            brItem.setAssignment(invoiceNoInfo.getInvoiceNo());
            brItem.setCoCode(ClaimInvoiceConstants.BUKRS);
            brItem.setCostCenter(ClaimInvoiceConstants.KOSTL);
            brItem.setGross(invoiceNoInfo.getGross());
            brItem.setLinetotal(invoiceNoInfo.getLinetotal());
            brItem.setPositionKey(ClaimInvoiceConstants.POSTING_KEY_BR);
            brItem.setRemark(invoiceNoInfo.getRemark());
            brItem.setSapText(String.valueOf(sf.format(date)));
            brItem.setSpecialG(ClaimInvoiceConstants.UMSKZ);
            brItem.setSubjectCode(invoiceNoInfo.getSapCode());
            brItem.setSubjectName(invoiceNoInfo.getSubjectName());
            brItem.setValid(invoiceNoInfo.getValid());
            brItem.setTaxCode(ClaimInvoiceConstants.MWSKZ);
           //保存请求SAP数据
           claimInvoiceDao.saveRequestSubject(brItem);
           //借方数据
           InvoiceSubjectDTO loanItem = new InvoiceSubjectDTO();
           loanItem.setInvoiceId(request.getId());
           loanItem.setAmount(invoiceNoInfo.getTax());
           loanItem.setAssignment(invoiceNoInfo.getInvoiceNo());
           loanItem.setCoCode(ClaimInvoiceConstants.BUKRS);
           loanItem.setCostCenter(ClaimInvoiceConstants.KOSTL);
           loanItem.setGross(invoiceNoInfo.getGross());
           loanItem.setLinetotal(invoiceNoInfo.getLinetotal());
           loanItem.setPositionKey(ClaimInvoiceConstants.POSTING_KEY_LOAN);
           loanItem.setRemark(invoiceNoInfo.getRemark());
           loanItem.setSapText(String.valueOf(sf.format(date)));
           loanItem.setSubjectCode(ClaimInvoiceConstants.SGM_ACCOUNT);
           loanItem.setSubjectName(invoiceNoInfo.getSubjectName());
           loanItem.setValid(invoiceNoInfo.getValid());
           loanItem.setTaxCode(ClaimInvoiceConstants.MWSKZ);
           claimInvoiceDao.saveRequestSubject(loanItem);
           
           List<Integer> statusList=new ArrayList<>();
           statusList.add(ClaimInvoiceConstants.SAP_DEAL_FAIL);
           statusList.add(ClaimInvoiceConstants.FOL_DEAL_FAIL);
           
           List<InvoiceSubjectDTO> subItemList = claimInvoiceDao.getSubByInvoiceNo(statusList,invoiceNoInfo);
           for (InvoiceSubjectDTO item : subItemList) {
              InvoiceSubjectDTO commonSubItem = new InvoiceSubjectDTO();
              commonSubItem.setInvoiceId(request.getId());
              commonSubItem.setAmount(item.getLinetotal());
              commonSubItem.setAssignment(item.getAssignment());
              commonSubItem.setCoCode(ClaimInvoiceConstants.BUKRS);
              commonSubItem.setCostCenter(ClaimInvoiceConstants.KOSTL);
              commonSubItem.setGross(item.getGross());
              commonSubItem.setLinetotal(item.getLinetotal());
              commonSubItem.setPositionKey(ClaimInvoiceConstants.POSTING_KEY_LOAN);
              commonSubItem.setRemark(invoiceNoInfo.getRemark());
              commonSubItem.setSapText(String.valueOf(sf.format(date)));
              commonSubItem.setSubjectCode(item.getSubjectCode());
              commonSubItem.setSubjectName(item.getSubjectName());
              commonSubItem.setValid(invoiceNoInfo.getValid());
              claimInvoiceDao.saveSubject(commonSubItem);
           }
       } catch (Exception e) {
           e.printStackTrace();
           throw new ServiceAppException("保存请求SAP数据异常！");
       }
        
    }
    
    private void saveDisposeItem(InvoiceNoInfoDTO invoiceNoInfo) throws ServiceAppException{
        
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        try {
            InvoiceInterfaceDTO request = new InvoiceInterfaceDTO();
            
            request.setAscCode(invoiceNoInfo.getSapCode());
            request.setCurrency(ClaimInvoiceConstants.WAERS);
            request.setDocumentDate(String.valueOf(sf.format(date)));
            request.setDocumentHeadtext(ClaimInvoiceConstants.BKTXT);
            request.setDocunmentType(invoiceNoInfo.getCertificateType());
            request.setExpressNo(invoiceNoInfo.getExpressNo());
            request.setGross(invoiceNoInfo.getGross());
            request.setInvoiceNo(invoiceNoInfo.getInvoiceNo());
            request.setInvoiceTitle(ClaimInvoiceConstants.INVOICE_TITLE);
            request.setLabourCost(invoiceNoInfo.getLabourCost());
            request.setLinetotal(invoiceNoInfo.getLinetotal());
            request.setOtherCost(invoiceNoInfo.getOtherCost());
            request.setPartCost(invoiceNoInfo.getPartCost());
            request.setPostDate(String.valueOf(sf.format(date)));
            request.setPostPeriod(String.valueOf(sf.format(date).substring(5,6)));
            request.setProcessStatus(invoiceNoInfo.getProcessStatus());
            request.setRemark(invoiceNoInfo.getRemark());
            request.setTax(invoiceNoInfo.getTax());
            request.setValid(invoiceNoInfo.getValid());
            claimInvoiceDao.saveRequestSAP(request);
            
            InvoiceSubjectDTO brItem = new InvoiceSubjectDTO();
            //贷方数据
            brItem.setInvoiceId(request.getId());
            brItem.setAmount(invoiceNoInfo.getGross());
            brItem.setAssignment(invoiceNoInfo.getInvoiceNo());
            brItem.setCoCode(ClaimInvoiceConstants.BUKRS);
            brItem.setCostCenter(ClaimInvoiceConstants.KOSTL);
            brItem.setGross(invoiceNoInfo.getGross());
            brItem.setLinetotal(invoiceNoInfo.getLinetotal());
            brItem.setPositionKey(ClaimInvoiceConstants.POSTING_KEY_BR);
            brItem.setRemark(invoiceNoInfo.getRemark());
            brItem.setSapText(String.valueOf(sf.format(date)));
            brItem.setSpecialG(ClaimInvoiceConstants.UMSKZ);
            brItem.setSubjectCode(invoiceNoInfo.getSapCode());
            brItem.setSubjectName(invoiceNoInfo.getSubjectName());
            brItem.setValid(invoiceNoInfo.getValid());
            brItem.setTaxCode(ClaimInvoiceConstants.MWSKZ);
           //保存请求SAP数据
           claimInvoiceDao.saveRequestSubject(brItem);
           //借方数据
           InvoiceSubjectDTO loanItem = new InvoiceSubjectDTO();
           loanItem.setInvoiceId(request.getId());
           loanItem.setAmount(invoiceNoInfo.getTax());
           loanItem.setAssignment(invoiceNoInfo.getInvoiceNo());
           loanItem.setCoCode(ClaimInvoiceConstants.BUKRS);
           loanItem.setCostCenter(ClaimInvoiceConstants.KOSTL);
           loanItem.setGross(invoiceNoInfo.getGross());
           loanItem.setLinetotal(invoiceNoInfo.getLinetotal());
           loanItem.setPositionKey(ClaimInvoiceConstants.POSTING_KEY_LOAN);
           loanItem.setRemark(invoiceNoInfo.getRemark());
           loanItem.setSapText(String.valueOf(sf.format(date)));
           loanItem.setSubjectCode(ClaimInvoiceConstants.SGM_ACCOUNT);
           loanItem.setSubjectName(invoiceNoInfo.getSubjectName());
           loanItem.setValid(invoiceNoInfo.getValid());
           loanItem.setTaxCode(ClaimInvoiceConstants.MWSKZ);
           claimInvoiceDao.saveRequestSubject(loanItem);
           
           List<Integer> statusList=new ArrayList<>();
           statusList.add(ClaimInvoiceConstants.SGM_DEAL_SUCCESS);
           
           List<InvoiceSubjectDTO> subItemList = claimInvoiceDao.getSubByInvoiceNo(statusList,invoiceNoInfo);
           for (InvoiceSubjectDTO item : subItemList) {
              InvoiceSubjectDTO commonSubItem = new InvoiceSubjectDTO();
              commonSubItem.setInvoiceId(request.getId());
              commonSubItem.setAmount(item.getLinetotal());
              commonSubItem.setAssignment(item.getAssignment());
              commonSubItem.setCoCode(ClaimInvoiceConstants.BUKRS);
              commonSubItem.setCostCenter(ClaimInvoiceConstants.KOSTL);
              commonSubItem.setGross(item.getGross());
              commonSubItem.setLinetotal(item.getLinetotal());
              commonSubItem.setPositionKey(ClaimInvoiceConstants.POSTING_KEY_LOAN);
              commonSubItem.setRemark(invoiceNoInfo.getRemark());
              commonSubItem.setSapText(String.valueOf(sf.format(date)));
              commonSubItem.setSubjectCode(item.getSubjectCode());
              commonSubItem.setSubjectName(item.getSubjectName());
              commonSubItem.setValid(invoiceNoInfo.getValid());
              claimInvoiceDao.saveSubject(commonSubItem);
           }
       } catch (Exception e) {
           e.printStackTrace();
           throw new ServiceAppException("保存请求SAP数据异常！");
       }
        
    }
    
    //调用SAP索赔接口 返回结果信息
    private ZFIDMSOUT invoiceCertificate(InvoiceNoInfoDTO invoiceNoInfo,ZFIDMSOUT zfidmsout) throws ServiceAppException{
        com.sgm.dms.fol.invoice.header.SGMCommonHeaderType headerType = new com.sgm.dms.fol.invoice.header.SGMCommonHeaderType();
        headerType.setMessageId(UUID.randomUUID().toString());
        headerType.setFrom("FOL");
        headerType.setTo("SAP");
        
        GregorianCalendar gcal = new GregorianCalendar();

        Date date = new Date();
        gcal.setTime(date);
        XMLGregorianCalendar xgcal = null;
        try {
            xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
        } catch (DatatypeConfigurationException e) {
        	e.printStackTrace();
        	logger.error(e.getMessage());
        	throw new ServiceAppException(e.getMessage(),e);
            
        }

        try {
	        headerType.setTimestamp(xgcal);
	        FOLSAPZFICREATEDOCDMSPTBindingQSService test = new FOLSAPZFICREATEDOCDMSPTBindingQSService();
	        FOLSAPZFICREATEDOCDMSPT invoice = test.getFOLSAPZFICREATEDOCDMSPTBindingQSPort();
	        
	        ZFICREATEDOCDMS zficreatedocdms = new ZFICREATEDOCDMS();
	        ZFICREATEDOCDMS.ITDOC z = new ZFICREATEDOCDMS.ITDOC();
	        
	        List<ZFIDMSTABLE> item = z.getItem();
	        ZFIDMSTABLE zfidmstable = new ZFIDMSTABLE();
	        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
	
	        zfidmstable.setBKTXT(ClaimInvoiceConstants.BKTXT);
	        zfidmstable.setBLART(invoiceNoInfo.getCertificateType());
	        zfidmstable.setBLDAT(String.valueOf(sf.format(date)));
	        zfidmstable.setBUDAT(String.valueOf(sf.format(date)));
	        zfidmstable.setBUKRS(ClaimInvoiceConstants.BUKRS);
	        zfidmstable.setKUNNR(invoiceNoInfo.getSapCode());
	        zfidmstable.setMONAT(String.valueOf(sf.format(date).substring(5,6)));
	        zfidmstable.setWAERS(ClaimInvoiceConstants.WAERS);
	        zfidmstable.setXBLNR(invoiceNoInfo.getInvoiceNo());
	        item.add(zfidmstable);
	        
	        ZFIDMSTABLE.ITEM zi = new ZFIDMSTABLE.ITEM();
	        List<ZFIDMSITEM> zfidmsitemlist=zi.getItem();
	        List<InvoiceSubjectDTO> ItemList = claimInvoiceDao.getSubjectItem(invoiceNoInfo);
	        for (InvoiceSubjectDTO subItem : ItemList) {
	            ZFIDMSITEM zfidmsitem=new ZFIDMSITEM(); 
	            zfidmsitem.setBSCHL(subItem.getPositionKey());
	            zfidmsitem.setCOCODE(subItem.getCoCode());
	            zfidmsitem.setDMBTR(subItem.getAmount());
	            zfidmsitem.setHKONT(subItem.getSubjectCode());
	            zfidmsitem.setKOSTL(subItem.getCostCenter());
	            zfidmsitem.setMWSKZ(subItem.getTaxCode()==null?"":subItem.getTaxCode().trim());
	            zfidmsitem.setSGTXT(String.valueOf(sf.format(date)));
	            zfidmsitem.setUMSKZ(subItem.getSpecialG()==null?"":subItem.getSpecialG().trim());
	            zfidmsitem.setZUONR(subItem.getAssignment());
	            zfidmsitemlist.add(zfidmsitem);            
	        }
	
	        zfidmstable.setITEM(zi);
	        zficreatedocdms.setITDOC(z);
	
	        ZFICREATEDOCDMSRESPONSE result = null ;
	        ZFIDMSOUT resVo = null;
        
            Client client = JaxWsClientProxy.getClient(invoice);
            client.getInInterceptors().add(new LoggingInInterceptor());
            client.getOutInterceptors().add(new LoggingOutInterceptor());
            result = invoice.zFICREATEDOCDMS(headerType, zficreatedocdms);
            resVo = result.getITOUT().getItem().get(0);
            BeanMapper.copy(resVo, zfidmsout);
            logger.info(result.getITOUT().getItem().get(0).getMSG());
            return resVo;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceAppException("调用SAP接口异常:" + e);
        }
        
    }

    private ReserveFundResultVO updateReserveAmount(InvoiceNoInfoDTO invoiceNoInfoDTO) throws ServiceAppException {
        try {
            ReserveFundRequestVO reserveFundRequestVO=initReserveFundResultVo(invoiceNoInfoDTO); 
            ReserveFundService service=new ReserveFundService();
            ReserveFundResultVO result=null;
            Holder<com.sgm.dms.fol.reserve.client.SGMCommonHeaderType> header=new Holder<com.sgm.dms.fol.reserve.client.SGMCommonHeaderType>();
            header.value=CommonHelper.tansfer(new GregorianCalendar());
            TranformReserveFund reserveFund=new TranformReserveFund();
            TranformReserveFundResponse reserveFundResponse=null;
            ReserveOperationWebService ws=service.getReserveFundPort();
            
            //添加日志拦截
            Client client = JaxWsClientProxy.getClient(ws);
            client.getInInterceptors().add(new LoggingInInterceptor());//输入流日志拦截器
            client.getOutInterceptors().add(new LoggingOutInterceptor());//输出流日志拦截器
            
            reserveFund.setArg0(reserveFundRequestVO);
            reserveFundResponse=ws.tranformReserveFund(reserveFund, header);
            result=reserveFundResponse.getReturn();
            logger.info(result);
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ServiceAppException(e.getMessage(), e);
        }
    }
    
    private ReserveFundRequestVO initReserveFundResultVo(InvoiceNoInfoDTO invoiceNoInfoDTO) throws ServiceAppException{
        ReserveFundRequestVO requestVO=new ReserveFundRequestVO();
        String referCode = invoiceNoInfoDTO.getInvoiceNo()+"|"+invoiceNoInfoDTO.getExpressNo();
        requestVO.setSapCode(invoiceNoInfoDTO.getSapCode());
        requestVO.setReferType(Long.valueOf(CodeConstant.WR_RESERVE_AMOUNT_ADD));
        requestVO.setReferCode(referCode);
        requestVO.setAmount(invoiceNoInfoDTO.getGross().doubleValue());
        
        requestVO.setTsId(invoiceNoInfoDTO.getFlowNo());
        requestVO.setFundType(null);
        return requestVO;
    }
    
    /**
     *
     * @author Lujinglei
     * @date 2016年6月7日
     * @return
     * @throws Exception
     * (non-Javadoc)
     * @see com.sgm.dms.fol.invoice.api.InvoiceNoReciveService#getinvoiceInfo()
     */
    	
    @Override
    @Transactional(rollbackFor=Exception.class)
    public List<InvoiceNoInfoDTO> getInvoiceInfo() throws ServiceAppException {
    	try {
			return claimInvoiceDao.getInvoiceInfo();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw(new ServiceAppException(e.getMessage()));
		}
        
    }

    
    /**
     *重处理专门调用
     * @author Lujinglei
     * @date 2016年6月8日
     * @param invoiceNoInfo
     * @return
     * @throws Exception
     * (non-Javadoc)
     * @see com.sgm.dms.fol.invoice.api.InvoiceNoReciveService#invokeByAnew(com.sgm.dms.fol.invoice.dto.InvoiceNoInfoDTO)
     */
    	
    @Override
    @Transactional(rollbackFor=Exception.class)
    public ZFIDMSOUT invokeByAnew(String invoiceNo,ZFIDMSOUT result) throws ServiceAppException {
        if(invoiceNo == null || invoiceNo.equals("")){
            throw new ServiceBizException("发票为空,请检查请求参数");
        }
        
        InvoiceNoInfoDTO invoiceNoInfoDTO=null;
        
        try {  
	        invoiceNoInfoDTO = claimInvoiceDao.getInvoiceNoStatus(invoiceNo);

	        saveAgainDisposeItem(invoiceNoInfoDTO);
            result = invoiceCertificate(invoiceNoInfoDTO,result);
             //从配置文件中处理成功和失败状态
//            String model = PropertiesUtil.getProperty("invoke.method.model.invokeSap");
//            
//            if(model!=null && !Boolean.valueOf(model)){
//               
//                throw new Exception("SAP处理失败！");
//            } 
            
            if(result.getBELNR()==null ||"".equals(result.getBELNR())){
                throw new ServiceBizException("凭证号为空,SAP处理失败!");
                
            }
             claimInvoiceDao.updateSapDealStatus(result.getXBLNR(),result.getKUNNR(),ClaimInvoiceConstants.SAP_DEAL_SUCCESS);

             SapReturnItemsDTO items = new SapReturnItemsDTO();
             items.setAscCode(result.getKUNNR());
             items.setCo_code(result.getBUKRS());
             items.setFinanceWarranty(result.getBELNR());
             items.setRemark(result.getMSG());
             items.setInvoiceNo(result.getXBLNR());
             items.setProcess_status(ClaimInvoiceConstants.SAP_DEAL_SUCCESS);
             //保存SAP返回数据
             claimInvoiceDao.saveInvoiceCertificate(items);
             InvoiceFollowDTO res = new InvoiceFollowDTO();
             res.setInvoiceCertificate(result.getBELNR());
             res.setInvoiceNo(result.getXBLNR());
             claimInvoiceDao.saveCertificate(res);
             
         } catch (Exception e) {            
             e.printStackTrace();
             logger.error(e.getMessage(),e);
             throw(new ServiceAppException(e));
         }
         return  result;
    }

}
