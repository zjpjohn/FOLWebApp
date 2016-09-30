package com.sgm.dms.fol.bonus.repository;

import java.sql.SQLException;
import java.util.List;

import com.sgm.dms.fol.bonus.dto.BonusIssueFileDTO;
import com.sgm.dms.fol.bonus.dto.BonusIssueRecordDTO;
import com.sgm.dms.fol.bonus.dto.BonusIssueResultDTO;
/*****
 * 奖金发放DAO
*
* @author ZhangBao
* TODO description
* @date 2015年12月15日
 */
public interface BonusIssueDao {

	/***
	 * 查询奖金发放版本
	 */
	public List<BonusIssueFileDTO> findBonusIssueFileByWhs(BonusIssueFileDTO bonusIssueFileDTO) throws SQLException;
	
	/***
	 * 查询奖金发放版本总条数
	 */
	public int findBonusIssueFileCountByWhs(BonusIssueFileDTO bonus)throws SQLException;
	
	/****
	 * 奖金重新处理查询(查询发放失败的记录)
	 */
	public List<BonusIssueRecordDTO> findBonusIssueAgainExecuteByWhs(BonusIssueRecordDTO bonusIssueRecordDTO)throws SQLException;
	
	/****
	 * 奖金重新处理查询总记录数(查询发放失败的记录)
	 */
	public int findBonusIssueAgainExecuteCountByWhs(BonusIssueRecordDTO bonusIssueRecordDTO)throws SQLException;
	
	/*****
	 * 奖金颁发查询(查询奖金发放成功的文件)
	 */
	public List<BonusIssueFileDTO> findBonusPublishedFileByWhs(BonusIssueFileDTO bonusIssueFileDTO)throws SQLException;
	
	/*****
	 * 奖金颁发查询总记录数(查询奖金发放成功的文件)
	 */
	public int findBonusPublishedFileCountByWhs(BonusIssueFileDTO bonusIssueFileDTO)throws SQLException;
	
	/***
	 * 作废奖金上传的发放版本(大)
	 */
	public int  updateBonusIssueFileStatusById(BonusIssueFileDTO bonusIssueFileDTO)throws SQLException;
	
	/*****
	 * 
	 * 作废奖金上传的发放记录(小)
	 *
	 */
	public int  updateBonusIssueRecordStatusById(BonusIssueRecordDTO bonusIssueRecordDTO)throws SQLException;
	
	/*****
     * 
     * 根据FLOW_NO更新奖金上传的发放记录(小)
     *
     */
    public int updateBonusIssueRecordStatusByFlowNo(BonusIssueRecordDTO bonusIssueRecordDTO)throws SQLException;
	
	/****
	 * 新增奖金发放文件
	 */
	public int saveBonusIssueFile(BonusIssueFileDTO bonusIssueFileDTO)throws SQLException;
	
	/***
	 * 新增奖金发放记录
	 */
	public int saveBonusIssueRecord(BonusIssueRecordDTO bonusIssueRecordDTO)throws SQLException;
	/****
	 * 新增发放奖金结果
	 */
	public int saveBonusIssueResult(BonusIssueResultDTO bonusIssueResultDTO)throws SQLException;
	
	/****
	 * 根据批次号查询奖金上传文件
	 */
	public List<BonusIssueFileDTO> findBonusIssueFileByBatchNo(String batchNo)throws SQLException;
	
	public List<BonusIssueFileDTO> findBonusIssueFileByBatchNoLock(String batchNo)throws SQLException;
	
	public List<BonusIssueRecordDTO>  findBonusRecordFileByBatchNo(String batchNo)throws SQLException;
	public List<BonusIssueRecordDTO> findBonusRecordByBatchNoLock(String batchNo)throws SQLException;
	
	/****
	 * 查询批次号(批次号规则暂时定位yyyymmdd0001)
	 */
	public String getMaxBatchNo()throws SQLException;

	public List<BonusIssueRecordDTO> findBonusIssueRecordByFlowNo(String tsId)throws SQLException;
	
	/***
	 * 查询一个批次未发放成功的记录
	 */
	public List<BonusIssueRecordDTO> findBonusRecordIssueIsNotSucByBatchNo(String batchNo)throws SQLException;
	
	/****
	 * 查询发放文件明细记录
	 */
	public List<BonusIssueRecordDTO> findBonusPublishFileDetailByBatchNo(String batchNo,Integer issueStatus) throws SQLException;
	
	
	
	
	
}
