
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : invoice.service
 *
 * @File name : InvoiceFollowServiceImpl.java
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
	
package com.sgm.dms.fol.invoice.service;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgm.dms.fol.common.api.constants.POConstant;
import com.sgm.dms.fol.common.api.exception.ServiceAppException;
import com.sgm.dms.fol.common.service.utils.CommonUtils;
import com.sgm.dms.fol.invoice.api.InvoiceFollowService;
import com.sgm.dms.fol.invoice.dto.InvoiceFollowDTO;
import com.sgm.dms.fol.invoice.dto.WrCollectDTO;
import com.sgm.dms.fol.invoice.repository.InvoiceDao;
import com.sgm.dms.fol.invoice.repository.WrCollectDao;

/**
 *
 * @author Lujinglei
 * description 索赔发票跟踪查询ServiceImpl
 * @date 2016年5月9日
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class InvoiceFollowServiceImpl implements InvoiceFollowService{


    protected Logger logger = LogManager.getLogger(this.getClass());
    
    @Autowired
    private InvoiceDao invoiceDao;
    
    @Autowired
    private WrCollectDao wrCollectDao;
    
    
    /**
     *  查询索赔发票信息
     * @author Lujinglei
     * @date 2016年5月9日
     * @param invoiceFollowDTO
     * @return
     * @throws ServiceAppException
     * (non-Javadoc)
     * @see com.sgm.dms.fol.invoice.api.InvoiceFollowService#getInvoiceFollow(com.sgm.dms.fol.invoice.dto.InvoiceFollowDTO)
     */
    	
    @Override
    public List<InvoiceFollowDTO> getInvoiceFollow(InvoiceFollowDTO invoiceFollowDTO) throws Exception {
        CommonUtils.filterSpecialWord(invoiceFollowDTO);
        List<InvoiceFollowDTO> list = null;
        
        list = invoiceDao.getInvoiceFollow(invoiceFollowDTO);
        
        return list;
    }

    
    /**
     *  作废
     * @author Lujinglei
     * @date 2016年5月9日
     * @param invoiceFollowDTO
     * @return
     * @throws ServiceAppException
     * (non-Javadoc)
     * @see com.sgm.dms.fol.invoice.api.InvoiceFollowService#cancelInvoice(com.sgm.dms.fol.invoice.dto.InvoiceFollowDTO)
     */
    	
    @Override
    public int cancelInvoice(InvoiceFollowDTO invoiceFollowDTO) throws Exception {
        
        int result = 0 ;
        
        if(invoiceFollowDTO.getStatus() == POConstant.WR_ORDER_INVOICE_SGM_RETURN){
        CommonUtils.filterSpecialWord(invoiceFollowDTO);
        result = invoiceDao.cancelInvoice(invoiceFollowDTO);
            
        }else{
            
                
                throw new Exception("作废失败!");
                
        }
        
        return result;
    }

    
    /**
     *  重做
     * @author Lujinglei
     * @date 2016年5月9日
     * @param invoiceFollowDTO
     * @return
     * @throws ServiceAppException
     * (non-Javadoc)
     * @see com.sgm.dms.fol.invoice.api.InvoiceFollowService#anewInvoice(com.sgm.dms.fol.invoice.dto.InvoiceFollowDTO)
     */
    	
    @Override
    public int anewInvoice(InvoiceFollowDTO invoiceFollowDTO) throws Exception {
        CommonUtils.filterSpecialWord(invoiceFollowDTO);
        int  result = 0;
    
        //检查处理状态
        check(invoiceFollowDTO);
        //重做(将发票状态置为无效)
        result += invoiceDao.anewInvoice(invoiceFollowDTO);  
        
        if(result == 0){
            
            throw new Exception("重做发票失败！");
            
        }  
        //新增发票删除记录
        result += getInvoiceRecord(invoiceFollowDTO);
        
        return result;
    }
    /*
     * 新增旧发票删除记录
     */
    private int getInvoiceRecord(InvoiceFollowDTO invoiceFollowDTO) throws Exception{
        int result = 0;
        CommonUtils.filterSpecialWord(invoiceFollowDTO);
        wrCollectDao.deleteInvoice(invoiceFollowDTO.getInvoiceNo());
        
        //获取旧发票记录
        List<WrCollectDTO> list = wrCollectDao.getOldInvoiceRecord(invoiceFollowDTO.getInvoiceNo(),invoiceFollowDTO.getSapCode());
        WrCollectDTO wrCollectDTO = new WrCollectDTO();
        if(list != null && list.size()>0){
            for (WrCollectDTO wrCollect : list) {
                wrCollectDTO.setValid(POConstant.WR_INVOICE_VALID_STATUS);
                wrCollectDTO.setRemark(wrCollect.getRemark());
                wrCollectDTO.setTsId(wrCollect.getTsId());
                wrCollectDTO.setCreateBy(wrCollect.getCreateBy());
                wrCollectDTO.setUpdateBy(wrCollect.getUpdateBy());
                wrCollectDTO.setWrId(wrCollect.getWrId());
                //新增删除记录
                try {
                    
                    result = wrCollectDao.addDelRecord(wrCollectDTO);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
        return result;
    }
    /*
     * 检查发票状态
     */
    private void check(InvoiceFollowDTO invoiceFollowDTO) throws Exception{
        CommonUtils.filterSpecialWord(invoiceFollowDTO);
        //查询索赔发票信息
        final String invoiceNo = invoiceFollowDTO.getInvoiceNo();
        final String sapCode = invoiceFollowDTO.getSapCode();
        List<InvoiceFollowDTO> list  = invoiceDao.getInvoiceFollow(new InvoiceFollowDTO(){{setInvoiceNo(invoiceNo);setSapCode(sapCode);}});
        
        if(list != null && list.size() > 0){
            
        for (InvoiceFollowDTO invoiceFollow : list) {
            //"SGM已退回"
            if(invoiceFollow.getStatus() != POConstant.WR_ORDER_INVOICE_SGM_RETURN){
                
                throw new Exception("重做失败，请检查处理状态！");
            }
            
          }
        
        }else{
            
            throw new Exception("索赔发票信息为空！");
            
        }
        
    }


    
    /**
     *
     * @author Lujinglei
     * @date 2016年5月19日
     * @param invoiceFollowDTO
     * @return
     * @throws Exception
     * (non-Javadoc)
     * @see com.sgm.dms.fol.invoice.api.InvoiceFollowService#getInvoiceDeatil(com.sgm.dms.fol.invoice.dto.InvoiceFollowDTO)
     */
    	
    @Override
    public List<InvoiceFollowDTO> getInvoiceDeatil(InvoiceFollowDTO invoiceFollowDTO) throws Exception {
       CommonUtils.filterSpecialWord(invoiceFollowDTO);
      List<InvoiceFollowDTO> list = invoiceDao.getInvoiceDeatil(invoiceFollowDTO.getInvoiceNo(),invoiceFollowDTO.getSapCode());  
      return list;
    }
}
