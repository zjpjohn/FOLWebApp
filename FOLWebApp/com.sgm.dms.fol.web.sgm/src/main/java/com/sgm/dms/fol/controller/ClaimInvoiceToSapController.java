package com.sgm.dms.fol.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sgm.dms.fol.common.service.controller.impl.BaseController;
import com.sgm.dms.fol.invoice.api.InvoiceNoReciveService;
import com.sgm.dms.fol.invoice.dto.InvoiceNoInfoDTO;
import com.sgm.dms.fol.invoice.functions.ZFIDMSOUT;
import com.sgm.dms.fol.invoice.repository.ClaimInvoiceDao;

@Controller
@RequestMapping("/claim")
public class ClaimInvoiceToSapController extends BaseController{
	protected Logger logger = Logger.getLogger(this.getClass());
    
    @Autowired
    private InvoiceNoReciveService invoiceNoReciveService;
    
    @Autowired
    private ClaimInvoiceDao claimInvoiceDao;

    @RequestMapping(value="/invokeSap",method=RequestMethod.GET)
    @ResponseBody
    public void invokeSap() throws Exception{
        logger.info("调用SAP接口开始------------>");
        
		List<InvoiceNoInfoDTO> invoiceNoInfo = claimInvoiceDao.getInvoiceInfo();
			
		for (InvoiceNoInfoDTO info : invoiceNoInfo) {
			ZFIDMSOUT result = new ZFIDMSOUT();
		    try {
		        //调用SAP
		        if(info != null){                
		        	result=invoiceNoReciveService.invoiceNoRecive(info,result);
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		        logger.error(e.getMessage(),e);
		        invoiceNoReciveService.resultDispose(info, result);
		    }
			
		}

    }
	
}
