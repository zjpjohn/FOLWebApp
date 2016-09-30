/**
* @ClassName: reserveAmountDao
* @Description: 储备金DAO
* @author: A BAO
* @date: 2015年10月9日 上午10:43:42
* 
* 
*/
package com.sgm.dms.fol.reserve.repository;

import java.sql.SQLException;
import java.util.List;

import com.sgm.dms.fol.common.api.domain.DealerInfoDTO;
import com.sgm.dms.fol.reserve.dto.ReserveDTO;



public interface ReserveDao {
	
	// 根据条件查询储备金信息
	public List<ReserveDTO> getReserveAmountByWhere(ReserveDTO reserveDTO) throws SQLException;
	
	// 根据条件查询储备金变动明细信息
    public List<ReserveDTO> getReserveChangeDeatilByWhere(ReserveDTO reserveDTO) throws SQLException;

    // 查询储备金冻结变动明细根据
    public List<ReserveDTO> getReserveFreezeDeatilByWhere(ReserveDTO reserveDTO) throws SQLException;

    // 查询储备金月度明细
    public List<ReserveDTO> getReserveMonthDeatil(ReserveDTO reserveDTO) throws SQLException;
    
    // 根据时间查询储备金余额（不包括冻结）
    public List<ReserveDTO> getReserveAmountByDateTime(ReserveDTO reserveDTO) throws SQLException;
    
    // 根据时间查询储备金增加和减少（不包括冻结）
    public List<ReserveDTO> getReserveChangeAmountByDateTime(ReserveDTO reserveDTO) throws SQLException;
    
    // 根据时间查询当前月之前最新储备金余额（不包括冻结）
    public List<ReserveDTO> getReserveAmountAccumulateByDateTime(ReserveDTO reserveDTO) throws SQLException;
    
    // 根据时间查询月度储备金余额（SAP的储备金信息）
    public List<ReserveDTO> getMonthReserveAmountByDateTime(ReserveDTO reserveDTO) throws SQLException;

    // 根据SAPCODE储备金RECORD
    public ReserveDTO findReserveRecordBySapCode(ReserveDTO reserveDTO) throws SQLException;
    
    // 查出所有有效的储备金RECORD
    public List<ReserveDTO> findReserveRecord(DealerInfoDTO dealerInfoDTO) throws SQLException;
}
