package com.sgm.dms.fol.bonus.repository;

import java.sql.SQLException;

import com.sgm.dms.fol.bonus.dto.BonusIssueFileRcTsDTO;

/******
 * 请求奖金发放服务记录DAO
 * @author Administrator
 *
 */
public interface BonusIssueFileRcTsDao {
	
	/*****
	 * 保存请求奖金发放服务记录
	 * @param bonusIssueFileRcTsDTO
	 * @return
	 * @throws SQLException
	 */
	public int saveBonusIssueFileRcTs(BonusIssueFileRcTsDTO bonusIssueFileRcTsDTO) throws SQLException;

}
