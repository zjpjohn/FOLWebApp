package com.sgm.dms.fol.bonus.api;

import java.sql.SQLException;
import java.util.List;

import com.sgm.dms.fol.bonus.dto.FundBalanceStatementDTO;
import com.sgm.dms.fol.common.api.exception.ServiceAppException;

/**
 * 资金余额报表service
 * @author lujinglei
 *
 */
public interface FundBalanceStatementService {
	/**
	 * 查询资金余额
	 * @param fundBalanceStatementDto
	 * @return
	 * @throws SQLException
	 */
	public List<FundBalanceStatementDTO> findAmountByWhere(FundBalanceStatementDTO fundBalanceStatementDto) throws ServiceAppException,SQLException;

}
