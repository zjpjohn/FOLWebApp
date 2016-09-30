package com.sgm.dms.fol.bonus.api;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.sgm.dms.fol.bonus.dto.BonusIssueFileDTO;
import com.sgm.dms.fol.bonus.dto.BonusIssueRecordDTO;
import com.sgm.dms.fol.common.api.exception.ServiceAppException;

/****
 * 奖金发放service
*
* @author ZhangBao
* TODO description
* @date 2015年12月15日
 */
public interface BonusIssueService {
	
	/***
	 * 查询奖金发放版本上传记录
	 */
	List<BonusIssueFileDTO> findBonusIssueFileByWhs(BonusIssueFileDTO bonusVo) throws ServiceAppException;
	
	/****
	 * 查询奖金发放版本上传总记录数
	 */
	int findBonusIssueFileCountByWhs(BonusIssueFileDTO bonus)throws ServiceAppException;
	/****
	 * 导入奖金发放文件
	 * @param token 
	 * @param filedId 
	 * @param userId
	 */
	int addImportBonusIssueFile(BonusIssueFileDTO vo, String token, String filedId,Long userId) throws ServiceAppException,IOException;
	
	/****
	 * 检查奖金发放文件是否已存在审核通过(1.非审核通过,2,审核通过)
	 * @param batchNo 
	 */
	public Integer checkBatchNoIsMatched(String batchNo)throws ServiceAppException;

	/****
	 * 更新奖金发放文件状态
	 * @param batchNo 
	 */
	int updateBonusIssueFile(List<BonusIssueFileDTO> vo,long userId)throws ServiceAppException;
	
	/****
	 * 获取批次号
	 * @return
	 */
	String getBatchNo() throws ServiceAppException;

	
	/****
	 * 发放奖金
	 * @param batchNo
	 * @return
	 */
	boolean payBonus(String batchNo,long userid) throws ServiceAppException,SQLException;

	/**
	 * 更新奖金审核状态
	 */
	 Boolean updateBonusAuditState(List<BonusIssueFileDTO> bonusIssueFileDTOs) throws ServiceAppException;
	 
	 /****
	  * 查询奖金重处理记录
	  */
	 List<BonusIssueRecordDTO> findBonusIssueAgainExecuteByWhs(BonusIssueRecordDTO bonusIssueRecordDTO) throws ServiceAppException;
	 
	 /****
	  * 查询奖金重处理总记录数
	  */
	 int findBonusIssueAgainExecuteCountByWhs(BonusIssueRecordDTO bonusIssueRecordDTO) throws ServiceAppException;
	 
	 /******
	  * 奖金颁发记录查询
	  */
	 List<BonusIssueFileDTO> findBonusPublishedFileByWhs(BonusIssueFileDTO bonusVo) throws ServiceAppException;
	 
	 /****
	  * 奖金颁发记录总数
	  */
	 int findBonusPublishedFileCountByWhs(BonusIssueFileDTO bonusVo) throws ServiceAppException;
	 
	 /***
	  * 重处理
	  */
	void payAgainBonus(List<BonusIssueRecordDTO> vo, Long userId) throws ServiceAppException,SQLException;
	
	/***
	 * 查询颁发明细
	 * @param issueSuc 
	 */
	List<BonusIssueRecordDTO> findBonusPublishFileDetailByBatchNo(String batchNo,Integer issueStatus) throws ServiceAppException;
}
