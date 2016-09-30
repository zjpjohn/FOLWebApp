package com.sgm.dms.fol.bonus.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgm.dms.fol.bonus.api.FundBalanceStatementService;
import com.sgm.dms.fol.bonus.dto.FundBalanceStatementDTO;
import com.sgm.dms.fol.bonus.repository.FundBalanceStatementDao;
import com.sgm.dms.fol.common.api.constants.CodeConstant;
import com.sgm.dms.fol.common.api.exception.ServiceAppException;
/**
 * 资金余额报表
 * @author luijnglei
 *
 */
@Service("FundBalanceStatementService")
@Transactional(rollbackFor=Exception.class)
public class FundBalanceStatementServiceImpl implements FundBalanceStatementService{
	// 日志操作
	protected Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private FundBalanceStatementDao fundBalanceStatementDao;

	/**
	 * 查询资金余额
	 */
	@Override
	public List<FundBalanceStatementDTO> findAmountByWhere(FundBalanceStatementDTO fundBalanceStatementDto)
			throws ServiceAppException,SQLException {
		List<FundBalanceStatementDTO> fundDtoList = new ArrayList<FundBalanceStatementDTO>();
		List<FundBalanceStatementDTO> fundList = null;
		try {
			// 查询返回结果
			fundList = fundBalanceStatementDao
					.findAmountByWhere(fundBalanceStatementDto);
			if(fundList != null){
				for (FundBalanceStatementDTO fundDtos : fundList) {
					fundDtos.setBonusAvailRatio(CodeConstant.BONUS_AVAIL_RATIO);
					fundDtoList.add(fundDtos);
				}
			}
		} catch (SQLException e) {
			logger.error(e);
			throw new ServiceAppException(e.getMessage());
		}
		return fundDtoList;
	}

}
