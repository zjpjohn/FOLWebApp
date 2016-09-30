package com.sgm.dms.fol.bonus.repository;

import java.sql.SQLException;
import java.util.List;

import com.sgm.dms.fol.bonus.dto.FundBalanceStatementDTO;

/**
 * 资金余额报表
 * @author lujinglei
 *
 */
public interface FundBalanceStatementDao {
	/**
	 * 根据条件查询资金余额
	 * @param fundBalanceStatementDto
	 * @return
	 * @throws SQLException
	 */
	public List<FundBalanceStatementDTO> findAmountByWhere(FundBalanceStatementDTO fundBalanceStatementDto) throws SQLException;

}
