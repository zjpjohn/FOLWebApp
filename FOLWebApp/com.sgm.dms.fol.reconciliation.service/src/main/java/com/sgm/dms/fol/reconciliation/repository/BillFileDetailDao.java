/*
* Copyright 2015 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : common.service
*
* @File name : FileDetailDao.java
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
	
package com.sgm.dms.fol.reconciliation.repository;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.sgm.dms.fol.reconciliation.dto.BillFileDetailDTO;

/*
 *
 * @author ZhangBao
 * 小文件处理DAO
 * @date 2015年11月3日
 */

public interface BillFileDetailDao {

	/*
	*
	* @author ZhangBao
	* TODO description
	* @date 2015年11月3日
	* @param fileD
	* @return
	*/
		
	int addBillFileDetail(BillFileDetailDTO fileD) throws SQLException;

	/*
	*
	* @author ZhangBao
	* TODO description
	* @date 2015年11月3日
	* @param fileDetailDTO
	* @return
	*/
		
	List<BillFileDetailDTO> findDealerBillFileDetailByFdAndPage(
			BillFileDetailDTO fileDetailDTO)throws SQLException;

	/*
	*
	* @author ZhangBao
	* TODO description
	* @date 2015年11月3日
	* @param fileDetailDTO
	* @return
	*/
		
	int updateBillFileStatusById(BillFileDetailDTO fileDetailDTO)throws SQLException;

	/*
	*
	* @author ZhangBao
	* TODO description
	* @date 2015年11月18日
	* @param billDto
	* @return
	*/
		
	List<BillFileDetailDTO> findDealerBillFileDetailForBillFilePublishedByFdAndPage(
			BillFileDetailDTO billDto)throws SQLException;
	
	/*
	*
	* @author ZhangBao
	* TODO description
	* @date 2015年11月20日
	* @param billDto
	* @return
	*/
	List<BillFileDetailDTO> findBillFileDetailByBillDateAndIsDel(
			Date date)throws SQLException;
	
	/*
	*
	* @author ZhangBao
	* TODO description
	* @date 2015年11月20日
	* @param billDto
	* @return
	*/
	List<BillFileDetailDTO> findBillFileByIdAndIsDel(
			long id)throws SQLException;
	
	/*
	*
	* @author ZhangBao
	* TODO description
	* @date 2015年11月3日
	* @param fileDetailDTO
	* @return
	*/
		
	int updateBillFileIsConfirmById(BillFileDetailDTO fileDetailDTO)throws SQLException;
	
	

}
