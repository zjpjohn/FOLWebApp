
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : invoice.service
 *
 * @File name : ClaimInvoiceDao.java
 *
 * @Author : Lujinglei
 *
 * @Date : 2016年6月3日
 *
----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2016年6月3日    Lujinglei    1.0
 *
 *
 *
 *
----------------------------------------------------------------------------------
 */
	
package com.sgm.dms.fol.invoice.repository;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sgm.dms.fol.invoice.dto.InvoiceFollowDTO;
import com.sgm.dms.fol.invoice.dto.InvoiceInterfaceDTO;
import com.sgm.dms.fol.invoice.dto.InvoiceNoInfoDTO;
import com.sgm.dms.fol.invoice.dto.InvoiceSubjectDTO;
import com.sgm.dms.fol.invoice.dto.SapReturnItemsDTO;

/**
 *
 * @author Lujinglei
 * description 索赔发票SAP接口
 * @date 2016年6月3日
 */

public interface ClaimInvoiceDao {
    
  //根据发票号查询有效状态的发票信息
  public InvoiceNoInfoDTO getInvoiceNoStatus(String invoiceNo) throws SQLException;
  //查询发票信息
  public List<InvoiceNoInfoDTO> getInvoiceInfo() throws SQLException;
  
  //将SAP返回的数据保存到接口表
  public int saveInvoiceCertificate(SapReturnItemsDTO result);
  //将SAP返回的发票凭证保存到发票表
  public int saveCertificate(InvoiceFollowDTO result);
  //将请求SAP数据保存到接口表
  public int saveRequestSAP(InvoiceInterfaceDTO request);
  public int saveRequestSubject(InvoiceSubjectDTO request);
  
  //查询科目数据
  public List<InvoiceSubjectDTO> getSubjectItem(InvoiceNoInfoDTO invoiceNoInfoDTO) throws SQLException;
  
  public List<InvoiceSubjectDTO> getSubByInvoiceNo(@Param("statusList") List<Integer> statusList,@Param("InvoiceNoInfoDTO") InvoiceNoInfoDTO invoiceNoInfoDTO);
  //保存科目数据
  public int saveSubject(InvoiceSubjectDTO subItem) throws SQLException;
  
  public int updateSapDealStatus(@Param("invoiceNo") String invoiceNo,@Param("sapCode") String sapCode,@Param("status") Integer status) throws SQLException;
  public int updateInoviceStatus(@Param("invoiceNo") String invoiceNo,@Param("status") Integer status) throws SQLException;
  public int updateSapRetrunStatus(@Param("invoiceNo") String invoiceNo,@Param("status") Integer status,@Param("remark") String remark) throws SQLException;
  public int saveReturnItem(@Param("result")SapReturnItemsDTO result,@Param("process_status") Integer process_status);

  public InvoiceNoInfoDTO findInvoiceInfoByInvoiceNo(@Param("invoiceNo") String invoiceNo) throws SQLException;
}
