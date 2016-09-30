package com.sgm.dms.fol.bonus.repository;

import java.sql.SQLException;
import java.util.List;

import com.sgm.dms.fol.bonus.dto.BonusIssueFileFlowDTO;

/*** 
 * 奖金发放版本状态记录
 * @author  zhang bao
 *
 */
public interface BonusIssueFileFlowDao {
	
	/*****
	 * 添加记录
	 * @param bonusIssueFileFlowDTO
	 * @return
	 * @throws SQLException
	 */
	public int saveBonusIssueFileFlow(BonusIssueFileFlowDTO bonusIssueFileFlowDTO) throws SQLException;
	
	/******
	 * 根据批次号查询当前状态
	 * @param batchNo
	 * @return
	 * @throws SQLException
	 */
	public List<BonusIssueFileFlowDTO> findBonusCurrentStatus(String batchNo)throws SQLException;
	

}
