/*
* Copyright 2015 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : common.api
*
* @File name : ReconciliationManagementService.java
*
* @Author : ZhangBao
*
* @Date : 2015年11月3日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2015年11月3日    ZhangBao    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/
	
package com.sgm.dms.fol.reconciliation.api;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.FileSystemException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sgm.dms.fol.common.api.exception.ServiceAppException;
import com.sgm.dms.fol.common.api.exception.ServiceBizException;
import com.sgm.dms.fol.common.service.utils.SysException;
import com.sgm.dms.fol.reconciliation.dto.BillFileDTO;
import com.sgm.dms.fol.reconciliation.dto.BillFileDetailDTO;

/*
 *
 * @author ZhangBao
 * 对账管理service
 * @date 2015年11月3日
 */

public interface ReconciliationManagementService {
	
	/****
	 * 添加大文件信息
	 * @throws SQLException 
	 */
	public int addFile(BillFileDTO billFileDTO)throws ServiceBizException, SQLException;
	
	/****
	 * 添加小文件信息
	 * @throws SQLException 
	 */
	public int addFileDetail(List<BillFileDetailDTO> billFileDetailDTOs)throws ServiceBizException, SQLException;
	/****
	 * 查询客户对账单信息
	 * @throws SQLException 
	 */
	public List<BillFileDetailDTO>  findDealerFileDetailByFdAndPage(BillFileDetailDTO billFileDetailDTO)throws ServiceBizException, SQLException;
	
	/****
	 * 更新签收状态
	 * @throws SQLException 
	 */
	public int updateBillFileDetailStatusById(List<BillFileDetailDTO> billFileDetailDTO)throws ServiceBizException, SQLException;
	
	public void addImportReconFile(BillFileDTO billFile)throws ServiceBizException, SQLException;

	/*****
	 * 取消发布
	 * @throws SQLException 
	 */
	public void updateBillFileStatusById(List<BillFileDTO> fileDTO)throws ServiceAppException, SQLException;

	/*****
	 * 发布文件
	 */
	public void publishFIle(List<BillFileDTO> fileDTO, HttpServletRequest request);
	
	/**
	 * 
	 * @author wangfl
	 * 修改发布状态为发布中
	 * @date 2016年4月12日
	 * @param id
	 */
	public void publishFIleStatus(Integer id) throws SQLException;
		
	
	
		
	/****
	 * 导出客户对账单
	 * @throws SysException 
	 * @throws UnsupportedEncodingException 
	 * @throws IOException 
	 */
	public <T> ByteArrayOutputStream  exportXls4BillFile(List<T> data,String token,String [] headers,String fileName)throws ServiceBizException, FileNotFoundException,FileSystemException, UnsupportedEncodingException, SysException, IOException;
	
	/**
	 * 
	 * @author wangfl
	 * 导出csv报表
	 * @date 2016年3月29日
	 * @param headers
	 * @param dataList
	 * @param fileName
	 * @param token
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IOException
	 */
	public <T> ByteArrayOutputStream  exportCsvFile(String [] headers, List<T> dataList, String fileName, String token) throws NoSuchMethodException, SecurityException, IOException;

	/****
	 * 导出客户对账单(合并单元格)
	 * @throws SysException 
	 * @throws UnsupportedEncodingException 
	 * @throws IOException 
	 */
	public <T> ByteArrayOutputStream  exportXls4BillFiles(List<T> data,String token,String [] headers,String fileName)throws ServiceBizException, FileNotFoundException,FileSystemException, UnsupportedEncodingException, SysException, IOException;

	/*
	*
	* @author ZhangBao
	* TODO description
	* @date 2015年11月12日
	* @param bill
	* @return
	*/
		
	public List<BillFileDTO> findDealerFileByFdAndPage(BillFileDTO bill)throws ServiceBizException, SQLException;
	
	/*****
	 * 检测文件是否已存在
	 * @throws SQLException 
	 */
	public BillFileDTO existBillFileByBillDate(Date date)throws ServiceBizException, SQLException;

	/*
	*
	* @author ZhangBao
	* TODO description
	* @date 2015年11月18日
	* @param billDto
	* @return
	*/
		
	public List<BillFileDetailDTO> findDealerBillFileDetailForBillFilePublishedByFdAndPage(BillFileDetailDTO billDto)throws ServiceBizException;
	
	/*
	*
	* @author ZhangBao
	* 确认已签收文件无误 
	* @date 2015年11月18日
	* @param billDto
	* @return
	*/
		
	public int confirmBillFileDetails(List<BillFileDetailDTO> billFileDetailDTO)throws ServiceBizException, SQLException;
	
	
	
	

}
