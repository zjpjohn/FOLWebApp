/**
* @ClassName: ReserveAmountService
* @Description:对账单  INTERFACE
* @author: lu hui
* @date: 2015年10月19日 上午10:10:43
* 
* 
*/
package com.sgm.dms.fol.common.api.services;

import java.util.List;

import com.sgm.dms.fol.common.api.domain.StatementDTO;
import com.sgm.dms.fol.common.api.exception.ServiceBizException;

public interface StatementService {
/**
 * @ClassName: ReserveAmountService
 * @Description: 对账单接口服务
 * @author: lu hui
 * @date: 2015年10月19日 下午 14:33
 * 
 */
public List<StatementDTO> getStatementmount(StatementDTO statementDTO) throws ServiceBizException;
}
