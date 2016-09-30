/**
 * @ClassName: ReserveAmountService
 * @Description: 储备金 INTERFACE
 * @author: A BAO
 * @date: 2015年10月9日 上午10:10:43
 * 
 * 
 */
package com.sgm.dms.fol.reserve.api;

import java.sql.SQLException;
import java.util.List;

import com.sgm.dms.fol.common.api.domain.DealerInfoDTO;
import com.sgm.dms.fol.common.api.exception.ServiceAppException;
import com.sgm.dms.fol.reserve.dto.DataReportDTO;
import com.sgm.dms.fol.reserve.dto.ReserveDTO;

public interface ReserveService {

    /**
     * @ClassName: ReserveAmountService
     * @Description: 储备金 接口服务
     * @author: A BAO
     * @date: 2015年10月9日 下午 14:33
     */
    public List<ReserveDTO> getReserveAmount(ReserveDTO reserveDTO) throws SQLException;

    // 根据条件查询储备金变动明细信息
    public List<ReserveDTO> getReserveChangeDeatilByWhere(ReserveDTO reserveDTO) throws SQLException;

    // 查询储备金冻结变动明细
    public List<ReserveDTO> getReserveFreezeDeatilByWhere(ReserveDTO reserveDTO) throws SQLException;
    
    // 查询储备金月度明细
    public List<ReserveDTO> getReserveMonthDeatil(ReserveDTO reserveDTO) throws SQLException;
    
    // 储备金余额对账
    public List<ReserveDTO> getReserveAmountReconcile(ReserveDTO reserveDTO) throws SQLException;
    
    // 储备金余额对账总数
    public int getReserveAmountReconcileCount(ReserveDTO reserveDTO) throws SQLException;
   
    // 我的账户
    public DataReportDTO findReserveRecordWithDealer(ReserveDTO reserveDTO) throws Exception;
    
    // 根据条件查询储备金变动明细信息(DEALER)
    public List<ReserveDTO> getReserveChangeDeatilWithDealer(ReserveDTO reserveDTO) throws Exception;

    // 查询储备金冻结变动明细(DEALER)
    public List<ReserveDTO> getReserveFreezeDeatilWithDealer(ReserveDTO reserveDTO) throws Exception;
    
    // 查询出所有经销商的储备金和奖金信息
    public List<DataReportDTO> getReserveAndBonusInfoList(DealerInfoDTO dealerInfoDTO) throws ServiceAppException;
    
    public int getReserveAndBonusInfoCount(DealerInfoDTO dealerInfoDTO) throws ServiceAppException;
}
