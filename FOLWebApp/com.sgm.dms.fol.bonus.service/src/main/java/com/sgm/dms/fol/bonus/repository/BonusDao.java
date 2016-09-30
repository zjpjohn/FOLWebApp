package com.sgm.dms.fol.bonus.repository;

import java.sql.SQLException;
import java.util.List;

import com.sgm.dms.fol.bonus.domain.BonusChangeRecordPo;
import com.sgm.dms.fol.bonus.domain.BonusPo;
import com.sgm.dms.fol.bonus.dto.BonusDTO;
import com.sgm.dms.fol.bonus.dto.BonusQueryDTO;
import com.sgm.dms.fol.bonus.dto.BonusReconcileDTO;
import com.sgm.dms.fol.common.api.domain.DealerInfoDTO;

public interface BonusDao {

	/**
	 * query single bonus record with sapCode
	 * @param bounsDTO
	 * @return
	 */
	public BonusPo getBounsRecordBySapCode(BonusDTO bounsDTO) throws SQLException;
	
	/**
     * query bonus record list
     * @param bounsDTO
     * @return
     */
    public List<BonusPo> getBounsRecord(DealerInfoDTO dealerInfoDTO) throws SQLException;
	
	/**
	 *根据sapcode和日期查询月初和月末的金额
	 * @param bounsDTO
	 * @return
	 */
	public List<BonusChangeRecordPo> getBonusMonthFistAndLastByDateTime(BonusDTO bounsDTO)throws SQLException;
    /**
     * 根据时间查询奖金余额（不包括冻结）
     * @param bonusDTO
     * @return
     * @throws SQLException
     */
    public List<BonusReconcileDTO> getBonusAmountByDateTime(BonusReconcileDTO bonusDTO) throws SQLException;
    
    /**
     * 根据时间查询奖金增加和减少（不包括冻结）
     * @param bonusDTO
     * @return
     * @throws SQLException
     */
    public List<BonusReconcileDTO> getBonusChangeAmountByDateTime(BonusReconcileDTO bonusDTO) throws SQLException;
    
    /**
     *  根据时间查询月度奖金余额（SAP的奖金信息）
     * @param bonusDTO
     * @return
     * @throws SQLException
     */
    public List<BonusReconcileDTO> getMonthBonusAmountByDateTime(BonusReconcileDTO bonusDTO) throws SQLException;

	/**
	 * 奖金余额对账
	 * @param bounsDTO
	 * @return
	 * @throws SQLException
	 */
	public List<BonusReconcileDTO> findBonusAmountByDateTime(BonusReconcileDTO bounsDTO)throws SQLException;

	/**
	 * 奖金月度明细查询
	 * @param bonusDTO
	 * @return
	 */
	public List<BonusReconcileDTO> getBonusMonthDeatil(BonusReconcileDTO bonusDTO) throws SQLException;
	/**
	 * 奖金余额汇总
	 * @return
	 * @throws SQLException
	 */
	public List<BonusReconcileDTO> findAmountReconcileCollect(BonusReconcileDTO bonusDTO) throws SQLException;
	/**
	 * 奖金月度报表(期末)
	 * @param bonusDTO
	 * @return
	 * @throws SQLException
	 */
	public List<BonusReconcileDTO> getBonusMonthForm(BonusReconcileDTO bonusDTO) throws SQLException;
	/**
	 * 奖金月度报表(期初)
	 * @param bonusDTO
	 * @return
	 * @throws SQLException
	 */
	public List<BonusReconcileDTO> getBeforeAmount(BonusReconcileDTO bonusDTO) throws SQLException;
	/**
	 * 根据时间查询奖金解冻信息
	 * @param bonusDTO
	 * @return
	 * @throws SQLException
	 */
	public List<BonusReconcileDTO> getFreezeBonusByTime(BonusReconcileDTO bonusDTO) throws SQLException;
	/**
	 * 根据时间查询奖金冻结信息
	 * @param bonusDTO
	 * @return
	 * @throws SQLException
	 */
	public List<BonusReconcileDTO> getFrozenBonusByTime(BonusReconcileDTO bonusDTO) throws SQLException;
	/**
	 * 根据年月查询订单使用
	 * @param bonusDTO
	 * @return
	 * @throws SQLException
	 */
	public List<BonusReconcileDTO> getOrderByTime(BonusReconcileDTO bonusDTO) throws SQLException;
	/**
	 * 根据年月查询订单扣款
	 * @param bonusDTO
	 * @return
	 * @throws SQLException
	 */
	public List<BonusReconcileDTO> getOrderButton(BonusReconcileDTO bonusDTO) throws SQLException;
	/**
	 * 根据年月查询奖金发放 (颁发)
	 * @param bonusDTO
	 * @return
	 * @throws SQLException
	 */
	public List<BonusReconcileDTO> getBonusByTime(BonusReconcileDTO bonusDTO) throws SQLException;
	/**
	 * 根据年月查询期初余额和期末余额
	 * @param bonusDTO
	 * @return
	 * @throws SQLException
	 */
	public List<BonusReconcileDTO> getAmountByTime(BonusReconcileDTO bonusDTO) throws SQLException;

	/**
	 * 根据 referType查询不同类型的奖金
	 * @param bonusDTO
	 * @return
	 * @throws SQLException
	 */
	public List<BonusReconcileDTO> getAmountByReferType(BonusReconcileDTO bonusDTO) throws SQLException;
	/**
	 * 根据code查询品牌
	 * @param bonusDTO
	 * @return
	 * @throws SQLException
	 */
	public List<BonusReconcileDTO> getTypeNameByCode(BonusReconcileDTO bonusDTO) throws SQLException;
	
	/**
     * 查询奖金变动明细（dealer）
     */
    public List<BonusQueryDTO> getBonusChangeDeatilWithDealer(BonusQueryDTO bonusQueryDTO) throws SQLException;

    /**
     * 查询奖金冻结明细（dealer）
     */
    public List<BonusQueryDTO> getBonusFreezeDeatilWithDealer(BonusQueryDTO bonusQueryDTO) throws SQLException;

}
