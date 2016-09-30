package com.sgm.dms.fol.bankTicket.repository;

import java.sql.SQLException;
import java.util.List;

import com.sgm.dms.fol.bankTicket.dto.BankInfoDTO;

/****
 * 银行信息dao
*
* @author Administrator
* TODO description
* @date 2015年12月31日
 */
public interface BankInfoDao {
	
	/*****
	 * 保存银行信息
	 */
	public int saveBankInfo(BankInfoDTO bankInfoDTO) throws SQLException;
	
	/****
	 * 根据缩写或渠道类型查询银行信息
	 */
	public BankInfoDTO findBankinfoWithDealer(BankInfoDTO bankInfoDTO)throws SQLException;
	
	/****
     * 根据缩写查询银行信息
     */
    public BankInfoDTO findBankinfo(BankInfoDTO bankInfoDTO)throws SQLException;
	
	/**
	 * 更新银行信息
	 */
	public int updateBankInfo(BankInfoDTO bankInfoDTO) throws SQLException;
	
	/**
	 * 查询出银行的所有记录
	 */
	public List<BankInfoDTO> getBankInfoAllList() throws SQLException;
}
