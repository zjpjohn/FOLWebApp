/**
* @ClassName: AmountServiceImpl
* @Description: 对账单 实现类
* @author:  luhui
* @date: 2015年10月19日 上午10:12:43
* 
* 
*/
package com.sgm.dms.fol.common.service.services.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgm.dms.fol.common.api.domain.StatementDTO;
import com.sgm.dms.fol.common.api.exception.ServiceBizException;
import com.sgm.dms.fol.common.api.services.StatementService;
import com.sgm.dms.fol.common.service.repository.StatementDao;
@Service("StatementService")
@Transactional(rollbackFor=ServiceBizException.class)
public class StatementServiceImpl implements StatementService {
	
	//日志操作
	protected Logger logger = LogManager.getLogger(this.getClass());
	
	//储备金DAO
	@Autowired
	private StatementDao statementDao;
	
	@Override
	public List<StatementDTO>getStatementmount(StatementDTO statementDTO) throws ServiceBizException {
		
		List<StatementDTO> reserveDtOlist=null;
		try {
			//查询返回结果 
		    reserveDtOlist=statementDao.getStatementAmountByWhere(statementDTO);
		} catch (SQLException e) {
			
			logger.info("查询信息异常 :"+e.getMessage());
			throw new ServiceBizException(e.getMessage());
		}
		
		/*//包装返回对象
		List<ReserveAmountDTO> ReserveAmountDto=new ArrayList<ReserveAmountDTO>();
		
		if(null!=reserveAmountPo&&reserveAmountPo.size()>0){
			for (ReserveAmountDTO ReservePo:reserveAmountPo) {
				if(null!=ReservePo){
					ReserveAmountDTO ReserveAmount=new ReserveAmountDTO();
					BeanUtils.copyProperties(ReserveAmount, ReservePo);
					ReserveAmountDto.add(ReserveAmount);
				}
			}
		}*/
		
		return reserveDtOlist;
	}
	

}
