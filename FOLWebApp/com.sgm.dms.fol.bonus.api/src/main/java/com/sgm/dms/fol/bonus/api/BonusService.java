package com.sgm.dms.fol.bonus.api;

import java.sql.SQLException;
import java.util.List;

import com.sgm.dms.fol.bonus.dto.BonusChangeRecordDTO;
import com.sgm.dms.fol.bonus.dto.BonusDTO;
import com.sgm.dms.fol.bonus.dto.BonusQueryDTO;
import com.sgm.dms.fol.bonus.dto.BonusReconcileDTO;
import com.sgm.dms.fol.common.api.exception.ServiceAppException;

/**
 * 奖金TOTALservice
 * @author ShenWeiwei
 *
 */
public interface BonusService {
	/**
	 * select single bonus record with sapCode
	 * @param bounsDTO
	 * @return
	 */
	public BonusDTO getBounsRecordBySapCode(BonusDTO bounsDTO) throws ServiceAppException,SQLException;
	
	/**
	 * select single bonus record with sapCode
	 * @param bounsDTO
	 * @return
	 */
	public BonusDTO getBounsChangeReconBySapCode(BonusDTO bounsDTO) throws ServiceAppException;
	
	/**
	 *根据sapcode和日期查询月初和月末的金额
	 * @param bounsDTO
	 * @return
	 */
	public List<BonusChangeRecordDTO> getBonusMonthFistAndLastByDateTime(BonusDTO bounsDTO)throws ServiceAppException;
	
	/**
	 * 奖金余额对账
	 * @param bounsDTO
	 * @return
	 * @throws SQLException
	 */
	public List<BonusReconcileDTO> getBonusAmountReconcile(BonusReconcileDTO bounsDTO)throws ServiceAppException;
	/**
	 * 奖金余额对账总数
	 * @param bounsDTO
	 * @return
	 * @throws SQLException
	 */
	public int getBonusAmountReconcileCount(BonusReconcileDTO bounsDTO)throws ServiceAppException;

	/**
	 * 奖金月度明细查询
	 * @param bonusDTO
	 * @return
	 */
	public List<BonusReconcileDTO> getBonusMonthDeatil(BonusReconcileDTO bonusDTO) throws ServiceAppException;
	/**
	 * 奖金余额汇总
	 * @return
	 * @throws SQLException
	 */
	public List<BonusReconcileDTO> getAmountReconcileCollect(BonusReconcileDTO bonusDTO) throws ServiceAppException;
	/**
	 * 奖金月度报表
	 * @param bonusDTO
	 * @return
	 * @throws SQLException
	 */
	public List<BonusReconcileDTO> getBonusMonthForm(BonusReconcileDTO bonusDTO) throws ServiceAppException;
	/**
	 * 根据时间查询奖金冻结.解冻信息
	 * @param bonusDTO
	 * @return
	 * @throws SQLException
	 */
	public List<BonusReconcileDTO> getFreezeBonusByTime(BonusReconcileDTO bonusDTO) throws ServiceAppException;
	/**
	 * 根据年月查询订单取消补款或订单扣款
	 * @param bonusDTO
	 * @return
	 * @throws SQLException
	 */
	public List<BonusReconcileDTO> getOrderByTime(BonusReconcileDTO bonusDTO) throws ServiceAppException;
	/**
	 * 根据年月查询奖金发放 
	 * @param bonusDTO
	 * @return
	 * @throws SQLException
	 */
	public List<BonusReconcileDTO> getBonusByTime(BonusReconcileDTO bonusDTO) throws ServiceAppException;
	/**
	 * 根据年月查询期初余额和期末余额
	 * @param bonusDTO
	 * @return
	 * @throws SQLException
	 */
	public List<BonusReconcileDTO> getAmountByTime(BonusReconcileDTO bonusDTO) throws ServiceAppException;
	

	/**
	 * 查询奖金变动明细（dealer）
	 */
	public List<BonusQueryDTO> getBonusChangeDeatilWithDealer(BonusQueryDTO bonusQueryDTO) throws ServiceAppException;

	/**
	 * 查询奖金冻结明细（dealer）
	 */
	public List<BonusQueryDTO> getBonusFreezeDeatilWithDealer(BonusQueryDTO bonusQueryDTO) throws ServiceAppException;

	/**
	 * 查询奖金变动明细总数（dealer）
	 */
	public int getBonusChangeDeatilCountByWhere(BonusQueryDTO bonusQueryDTO) throws ServiceAppException;

	/**
     * 查询奖金冻结明细总数（dealer）
     */
	public int getBonusFreezeDeatilCountByWhere(BonusQueryDTO bonusQueryDTO) throws ServiceAppException;
}
