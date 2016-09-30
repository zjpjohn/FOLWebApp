/*
* Copyright 2015 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : common.service
*
* @File name : FileDao.java
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

import com.sgm.dms.fol.reconciliation.dto.BillFileDTO;

/*
 *
 * @author ZhangBao
 * 大文件处理DAO
 * @date 2015年11月3日
 */

public interface BillFileDao {

	/*
	*
	* @author ZhangBao
	* TODO description
	* @date 2015年11月3日
	* @param fileDTO
	*/
		
	int addBillFile(BillFileDTO fileDTO) throws SQLException;
	
	int updateBillFileStatusById(BillFileDTO fileDTO)throws SQLException;

	/*
	*
	* @author ZhangBao
	* TODO description
	* @date 2015年11月12日
	* @param bill
	* @return
	*/
		
	List<BillFileDTO> findDealerFileByFdAndPage(BillFileDTO bill)throws SQLException;
	
	List<BillFileDTO> findBillFileByBillDate(Date date)throws SQLException;
	
	int updateFiledIdByBillDate(BillFileDTO billFileDTO);

}
