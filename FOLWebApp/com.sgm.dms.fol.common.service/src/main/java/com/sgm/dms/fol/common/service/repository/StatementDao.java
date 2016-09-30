/**
* @ClassName: reserveAmountDao
* @Description: 对账单DAO
* @author: luhui
* @date: 2015年10月19日 上午10:43:42
* 
* 
*/
package com.sgm.dms.fol.common.service.repository;

import java.sql.SQLException;
import java.util.List;

import com.sgm.dms.fol.common.api.domain.StatementDTO;




public interface StatementDao {
	
	//根据条件查询对账单 
	public List<StatementDTO> getStatementAmountByWhere(StatementDTO statementDTO) throws SQLException;
	

}
