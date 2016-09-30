/**
 * @ClassName: AmountServiceImpl
 * @Description: 储备金 实现类
 * @author:  A BAO
 * @date: 2015年10月9日 上午10:12:43
 * 
 * 
 */
package com.sgm.dms.fol.reserve.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgm.dms.fol.bonus.domain.BonusPo;
import com.sgm.dms.fol.bonus.dto.BonusDTO;
import com.sgm.dms.fol.bonus.repository.BonusDao;
import com.sgm.dms.fol.common.api.constants.POConstant;
import com.sgm.dms.fol.common.api.constants.StateConstant;
import com.sgm.dms.fol.common.api.domain.DealerInfoDTO;
import com.sgm.dms.fol.common.api.exception.ServiceAppException;
import com.sgm.dms.fol.common.api.exception.ServiceBizException;
import com.sgm.dms.fol.common.service.domains.DealerInfoPo;
import com.sgm.dms.fol.common.service.repository.DealerDao;
import com.sgm.dms.fol.common.service.utils.BeanMapper;
import com.sgm.dms.fol.common.service.utils.DateUtil;
import com.sgm.dms.fol.common.service.utils.RSAUtil;
import com.sgm.dms.fol.reserve.api.ReserveService;
import com.sgm.dms.fol.reserve.dto.DataReportDTO;
import com.sgm.dms.fol.reserve.dto.ReserveDTO;
import com.sgm.dms.fol.reserve.repository.ReserveDao;

@Service("ReserveService")
@Transactional(rollbackFor = ServiceBizException.class)
public class ReserveServiceImpl implements ReserveService {

	// 日志操作
	protected Logger logger = LogManager.getLogger(this.getClass());

	// 储备金DAO
	@Autowired
	private ReserveDao reserveDao;

	@Autowired
	private DealerDao dealerDao;

	@Autowired
	private BonusDao bonusDao;

	@Override
	public List<ReserveDTO> getReserveAmount(ReserveDTO reserveDto)
			throws SQLException {

		List<ReserveDTO> reserveDtOlist = null;
		// 查询返回结果
		reserveDtOlist = reserveDao.getReserveAmountByWhere(reserveDto);

		// 包装返回对象
		List<ReserveDTO> finalReserveDtoList = new ArrayList<ReserveDTO>();

		if (null != reserveDtOlist && reserveDtOlist.size() > 0) {
			for (ReserveDTO reservedto : reserveDtOlist) {
				if (null != reservedto
						&& POConstant.RESERVE_TYPE_PARTS == reserveDto
								.getReserveType()) {
					reservedto.setReserveTypeName("配件");
				} else if (null != reservedto
						&& POConstant.RESERVE_TYPE_CAR == reserveDto
								.getReserveType()) {
					reservedto.setReserveTypeName("整车");
				}
				finalReserveDtoList.add(reservedto);
			}
		}
	        
		return finalReserveDtoList;
	}

	/*
	 * 
	 * @author shenweiwei
	 * 
	 * @date 2015年10月19日
	 * 
	 * @param reserveDTO
	 * 
	 * @return
	 * 
	 * @throws SQLException (non-Javadoc)
	 * 
	 * @see
	 * com.sgm.dms.fol.reserve.api.ReserveService#getReserveChangeDeatilByWhere
	 * (com.sgm.dms.fol.reserve.dto.ReserveDTO)
	 */

	@Override
	public List<ReserveDTO> getReserveChangeDeatilByWhere(ReserveDTO reserveDTO)
			throws SQLException {
		List<ReserveDTO> reserveDtOlist = null;
		try {
			if(null != reserveDTO.getSapCode()){
				if(reserveDTO.getSapCode().length() <= 10){
					reserveDTO.setSapCode(reserveDTO.getSapCode());
				}else{
					reserveDTO.setSapCode(RSAUtil.decryptByPrivateKey(reserveDTO.getSapCode()));
				}
			}
			// 查询返回结果
			reserveDtOlist = reserveDao
					.getReserveChangeDeatilByWhere(reserveDTO);
		} catch (SQLException e) {
			logger.info("储备金查询信息异常 :" + e.getMessage());
			throw new ServiceBizException(e.getMessage());
		}catch (Exception e) {
			logger.error("储备金查询信息异常 :"+e.getMessage());
            throw new ServiceBizException(e.getMessage());
        }
		return reserveDtOlist;
	}

	/*
	 * 
	 * @author shenweiwei
	 * 
	 * @date 2015年10月19日
	 * 
	 * @param reserveDTO
	 * 
	 * @return
	 * 
	 * @throws SQLException (non-Javadoc)
	 * 
	 * @see
	 * com.sgm.dms.fol.reserve.api.ReserveService#getReserveChangeDeatilByWhere
	 * (com.sgm.dms.fol.reserve.dto.ReserveDTO)
	 */

	@Override
	public List<ReserveDTO> getReserveFreezeDeatilByWhere(ReserveDTO reserveDTO)
			throws SQLException {
		List<ReserveDTO> reserveDtOlist = null;
		try {
//			if(null != reserveDTO.getSapCode()){
//				reserveDTO.setSapCode(RSAUtil.decryptByPrivateKey(reserveDTO.getSapCode()));
//			}
			if(null != reserveDTO.getSapCode()){
				if(reserveDTO.getSapCode().length() <= 10){
					reserveDTO.setSapCode(reserveDTO.getSapCode());
				}else{
					reserveDTO.setSapCode(RSAUtil.decryptByPrivateKey(reserveDTO.getSapCode()));
				}
			}
			// 查询返回结果
			reserveDtOlist = reserveDao
					.getReserveFreezeDeatilByWhere(reserveDTO);
		} catch (SQLException e) {
			logger.info("储备金查询信息异常 :" + e.getMessage());
			throw new ServiceBizException(e.getMessage());
		}catch (Exception e) {
			logger.error("储备金查询信息异常 :" + e.getMessage());
            throw new ServiceBizException(e.getMessage());
        }
		return reserveDtOlist;
	}

	/*
	 * 
	 * @author shenweiwei
	 * 
	 * @date 2015年10月20日
	 * 
	 * @param reserveDTO
	 * 
	 * @return
	 * 
	 * @throws SQLException (non-Javadoc)
	 * 
	 * @see
	 * com.sgm.dms.fol.reserve.api.ReserveService#getReserveMonthDeatil(com.
	 * sgm.dms.fol.reserve.dto.ReserveDTO)
	 */

	@Override
	public List<ReserveDTO> getReserveMonthDeatil(ReserveDTO reserveDTO)
			throws SQLException {
		List<ReserveDTO> reserveDtOlist = null;
		try {
			// 查询返回结果
			reserveDtOlist = reserveDao.getReserveMonthDeatil(reserveDTO);
		} catch (SQLException e) {
			logger.info("储备金查询信息异常 :" + e.getMessage());
			throw new ServiceBizException(e.getMessage());
		}
		return reserveDtOlist;
	}

	/*
	 * 
	 * @author shenweiwei
	 * 
	 * @date 2015年10月21日
	 * 
	 * @param reserveDTO
	 * 
	 * @return
	 * 
	 * @throws SQLException (non-Javadoc)
	 * 
	 * @see
	 * com.sgm.dms.fol.reserve.api.ReserveService#getReserveAmountReconcile(
	 * com.sgm.dms.fol.reserve.dto.ReserveDTO)
	 */

	@Override
	public List<ReserveDTO> getReserveAmountReconcile(ReserveDTO reserveDTO)
			throws SQLException {
		List<ReserveDTO> list = new ArrayList<ReserveDTO>();
		List<ReserveDTO> reservedtolist = new ArrayList<ReserveDTO>();
		List<ReserveDTO> tempReserveDTO = null;// 临时的DTOList
		// ReserveDTO reservedto=new ReserveDTO();

		try {
			// 先查询出所有的
			// 查询出本月储备金余额
			tempReserveDTO = reserveDao.getReserveAmountByDateTime(reserveDTO);
			if (tempReserveDTO != null) {
				for (ReserveDTO tempdto : tempReserveDTO) {
					reservedtolist.add(tempdto);
				}
			}

			// 查询出本月增加或减少
			tempReserveDTO = reserveDao
					.getReserveChangeAmountByDateTime(reserveDTO);
			if (tempReserveDTO != null) {
				for (ReserveDTO tempdto : tempReserveDTO) {
					for (ReserveDTO realdto : reservedtolist) {
						if (tempdto.getSapCode().equals(realdto.getSapCode())) {
							realdto.setReserveChangeAddAmount(tempdto
									.getReserveChangeAddAmount());
							realdto.setReserveChangeSubtractAmount(tempdto
									.getReserveChangeSubtractAmount());
						}
					}
				}
			}

			// SAP本月月额
			tempReserveDTO = reserveDao
					.getMonthReserveAmountByDateTime(reserveDTO);
			if (tempReserveDTO != null) {
				for (ReserveDTO tempdto : tempReserveDTO) {
					for (ReserveDTO realdto : reservedtolist) {
						if (tempdto.getSapCode().equals(realdto.getSapCode())) {
							realdto.setSapReserveAmount(tempdto
									.getSapReserveAmount());
						}
					}
				}
			}
			
			//查询出上月储备金余额 
            Integer changeMonth=Integer.parseInt(reserveDTO.getChangeTime().substring(5, 7))-1;//把时间转换到上月
            
            if(changeMonth<1){
                Integer changeYear=Integer.parseInt(reserveDTO.getChangeTime().substring(0,4))-1;
                reserveDTO.setChangeTime(changeYear+"-12");
            }else{
                reserveDTO.setChangeTime(reserveDTO.getChangeTime().substring(0,5)+DateUtil.monthReplenishZero(changeMonth.toString()));
            }
            
            tempReserveDTO=reserveDao.getReserveAmountAccumulateByDateTime(reserveDTO);
            if(tempReserveDTO!=null){
                for (ReserveDTO tempdto : tempReserveDTO) {
                    for (ReserveDTO realdto : reservedtolist) {
                        if(tempdto.getSapCode().equals(realdto.getSapCode())){
                            realdto.setReserveLastAmount(tempdto.getReserveAmount());
                        }
                    }
                }
            }
            
            if(reserveDTO.getMonth()<=1){
                reserveDTO.setYear(reserveDTO.getYear()-1);
                reserveDTO.setMonth(12);
            }else{
                reserveDTO.setMonth(reserveDTO.getMonth()-1);
            }
            
            //SAP上月余额
            tempReserveDTO=reserveDao.getMonthReserveAmountByDateTime(reserveDTO);
            if(null!=tempReserveDTO&&tempReserveDTO.size()>0){
	            for (ReserveDTO tempdto : tempReserveDTO) {
	                for (ReserveDTO realdto : reservedtolist) {
	                    if(tempdto.getSapCode().equals(realdto.getSapCode())){
	//                        if(tempReserveDTO!=null){
	                            realdto.setSapReserveLastAmount(tempdto.getSapReserveAmount());
	//                        }
	                    }
	                }
	            }
            }
			for (ReserveDTO realdto : reservedtolist) {
				if (realdto.getSapCode() != null) {
				    
				    //渠道类型筛选
	                if(realdto.getDealerType()!=null&&!reserveDTO.getDealerType().trim().equals("-1")&&!realdto.getDealerType().equals(reserveDTO.getDealerType().trim())){
	                    continue;
	                }else if(realdto.getDealerType()==null){
	                    continue;
	                }

					// 查询出上月储备金余额
                    if (realdto.getReserveLastAmount() == null) {
                        realdto.setReserveLastAmount(new BigDecimal(0));
                    }
                    
                    // 本月储备金余额为空但是上月不为空，把上月余额的值放入到本月余额
                    if(realdto.getReserveAmount() == null&&realdto.getReserveLastAmount() != null){
                        realdto.setReserveAmount(realdto.getReserveLastAmount());
                    }else if (realdto.getReserveAmount() == null&&realdto.getReserveLastAmount()==null) {
                    // 本月以及上月储备金余额为空转换成0
                        realdto.setReserveAmount(new BigDecimal(0));
                    }
					
					// 本月增加为空转换成0
					if (realdto.getReserveChangeAddAmount() == null) {
						realdto.setReserveChangeAddAmount(new BigDecimal(0));
					}
					// 本月减少为空转换成0
					if (realdto.getReserveChangeSubtractAmount() == null) {
						realdto.setReserveChangeSubtractAmount(new BigDecimal(0));
					}
					// SAP本月月额为空转换成0
					if (realdto.getSapReserveAmount() == null) {
						realdto.setSapReserveAmount(new BigDecimal(0));
					}
					
					// SAP上月余额
					if (realdto.getSapReserveLastAmount() == null) {
						realdto.setSapReserveLastAmount(new BigDecimal(0));
					}
					
					BigDecimal reserveAmount=realdto.getReserveLastAmount().add(realdto.getReserveChangeAddAmount()).add(realdto.getReserveChangeSubtractAmount());
	                realdto.setReserveAmount(reserveAmount);
	                
					// 算出余额差异
                    if (realdto.getReserveAmount() != null
                            && realdto.getSapReserveAmount() != null) {
                        realdto.setDifferenceAmount(realdto.getReserveAmount()
                                .subtract(realdto.getSapReserveAmount()));
                    } else if (realdto.getReserveAmount() != null
                            && realdto.getSapReserveAmount() == null) {
                        realdto.setDifferenceAmount(realdto.getReserveAmount());
                    } else if (realdto.getReserveAmount() == null
                            && realdto.getSapReserveAmount() != null) {
                        realdto.setDifferenceAmount(new BigDecimal("0")
                                .subtract(realdto.getSapReserveAmount()));
                    }
				} 
				
				// 余额差异筛选条件
				if (StateConstant.RESERVE_AMOUNT_DIFFENCE_YES.equals(reserveDTO.getDifference())) {
					if (realdto.getReserveAmount().compareTo(realdto.getSapReserveAmount())!=0
							&& (!"".equals(realdto.getSapCode()) || realdto
									.getSapCode() != null)) {
						list.add(realdto);
					}
				} else if (StateConstant.RESERVE_AMOUNT_DIFFENCE_NO.equals(reserveDTO.getDifference())) {
					if (realdto.getReserveAmount().compareTo(realdto.getSapReserveAmount())==0
							&& (!"".equals(realdto.getSapCode()) || realdto
									.getSapCode() != null)) {
						list.add(realdto);
					}
				} else {
					if (realdto.getSapCode() != null) {
						list.add(realdto);
					}
				}

			}

		} catch (SQLException e) {
			logger.info("储备金查询信息异常 :" + e.getMessage());
			throw new ServiceBizException(e.getMessage());
		}

		if(null==reserveDTO.getBeginNo()&&null==reserveDTO.getEndNo()){
		    return list;
		}
		
		if(null!=reserveDTO.getBeginNo()&&reserveDTO.getBeginNo()==0){
		    if(null!=reserveDTO.getEndNo()&&reserveDTO.getEndNo()>list.size()){
		        list=list.subList(reserveDTO.getBeginNo(), list.size());
		    }else{
		        list=list.subList(reserveDTO.getBeginNo(), reserveDTO.getEndNo());
		    }
		}else{
		    if(null!=reserveDTO.getEndNo()&&reserveDTO.getEndNo()>list.size()){
		        list=list.subList(reserveDTO.getBeginNo()-1, list.size());
		    }else{
		        list=list.subList(reserveDTO.getBeginNo()-1, reserveDTO.getEndNo());
		    }
		}

		return list;
	}

	/*
	 * 
	 * @author ShenWeiwei
	 * 
	 * @date 2015年10月27日
	 * 
	 * @param sapCode
	 * 
	 * @return
	 * 
	 * @throws SQLException (non-Javadoc)
	 * 
	 * @see
	 * com.sgm.dms.fol.reserve.api.ReserveService#findReserveRecordWithDealer
	 * (java.lang.String)
	 */

	@Override
	public DataReportDTO findReserveRecordWithDealer(ReserveDTO reserveDTO)
			throws Exception {
		// 根据sapCode查询出储备金
		reserveDTO.setSapCode(RSAUtil.decryptByPrivateKey(reserveDTO
				.getSapCode()));
		ReserveDTO reservedto = reserveDao
				.findReserveRecordBySapCode(reserveDTO);

		// 用于动态查询的临时对象
		BonusDTO tempDto = new BonusDTO();
		tempDto.setSapCode(reserveDTO.getSapCode());

		BonusPo bonusPo = bonusDao.getBounsRecordBySapCode(tempDto);
		BonusDTO bonusDTO = BeanMapper.map(bonusPo, BonusDTO.class);

		DataReportDTO dataReportDTO = new DataReportDTO();
		// 设置我的账户需要的值
		if (reservedto != null && bonusDTO == null) {
			dataReportDTO.setReserveTotalAmount(reservedto.getTotalAmount());
			dataReportDTO.setReserveFrozenAmount(reservedto.getFrozenAmount());
			dataReportDTO.setReserveAvailAmount(reservedto.getAvailAmount());
			dataReportDTO.setBonusTotalAmount(new BigDecimal(0));
			dataReportDTO.setBonusFrozenAmount(new BigDecimal(0));
			dataReportDTO.setBonusAvailAmount(new BigDecimal(0));
			dataReportDTO.setSapCode(reservedto.getSapCode());
			dataReportDTO.setDealerName(reservedto.getDealerName());
		} else if (reservedto != null && bonusDTO != null) {
			dataReportDTO.setReserveTotalAmount(reservedto.getTotalAmount());
			dataReportDTO.setReserveFrozenAmount(reservedto.getFrozenAmount());
			dataReportDTO.setReserveAvailAmount(reservedto.getAvailAmount());
			dataReportDTO.setBonusTotalAmount(bonusDTO.getTotalAmount());
			dataReportDTO.setBonusFrozenAmount(bonusDTO.getFrozenAmount());
			dataReportDTO.setBonusAvailAmount(bonusDTO.getTotalAmount()
					.subtract(bonusDTO.getFrozenAmount()));
			dataReportDTO.setSapCode(reservedto.getSapCode());
			dataReportDTO.setDealerName(reservedto.getDealerName());
		} else if (reservedto == null && bonusDTO != null) {
			dataReportDTO.setReserveTotalAmount(new BigDecimal(0));
			dataReportDTO.setReserveFrozenAmount(new BigDecimal(0));
			dataReportDTO.setReserveAvailAmount(new BigDecimal(0));
			dataReportDTO.setBonusTotalAmount(bonusDTO.getTotalAmount());
			dataReportDTO.setBonusFrozenAmount(bonusDTO.getFrozenAmount());
			dataReportDTO.setBonusAvailAmount(bonusDTO.getTotalAmount()
					.subtract(bonusDTO.getFrozenAmount()));
			dataReportDTO.setSapCode(bonusDTO.getSapCode());
			dataReportDTO.setDealerName(bonusDTO.getDealerName());
		} else {
			dataReportDTO.setReserveTotalAmount(new BigDecimal(0));
			dataReportDTO.setReserveFrozenAmount(new BigDecimal(0));
			dataReportDTO.setReserveAvailAmount(new BigDecimal(0));
			dataReportDTO.setBonusTotalAmount(new BigDecimal(0));
			dataReportDTO.setBonusFrozenAmount(new BigDecimal(0));
			dataReportDTO.setBonusAvailAmount(new BigDecimal(0));
			dataReportDTO.setSapCode(reserveDTO.getSapCode());

			// 根据SAPCODE去库里差dealerInfo
			DealerInfoDTO dealerinfodto = new DealerInfoDTO();
			dealerinfodto.setSapCode(dataReportDTO.getSapCode());
			DealerInfoPo dealerInfoPo = dealerDao
					.getDealerInfoBySapCode(dealerinfodto);
			if (dealerInfoPo == null) {
				return null;
			} else {
				dataReportDTO.setDealerName(dealerInfoPo.getDealerName());
			}

		}
		return dataReportDTO;
	}

	@Override
	public List<ReserveDTO> getReserveChangeDeatilWithDealer(
			ReserveDTO reserveDTO) throws Exception {
		List<ReserveDTO> reserveDtOlist = null;
		try {
			reserveDTO.setSapCode(RSAUtil.decryptByPrivateKey(reserveDTO
					.getSapCode()));
			// 查询返回结果
			reserveDtOlist = reserveDao
					.getReserveChangeDeatilByWhere(reserveDTO);
		} catch (SQLException e) {
			logger.info("储备金查询信息异常 :" + e.getMessage());
			throw new ServiceBizException(e.getMessage());
		}
		return reserveDtOlist;
	}

	@Override
	public List<ReserveDTO> getReserveFreezeDeatilWithDealer(
			ReserveDTO reserveDTO) throws Exception {
		List<ReserveDTO> reserveDtOlist = null;
		try {
			reserveDTO.setSapCode(RSAUtil.decryptByPrivateKey(reserveDTO
					.getSapCode()));
			// 查询返回结果
			reserveDtOlist = reserveDao
					.getReserveFreezeDeatilByWhere(reserveDTO);
		} catch (SQLException e) {
			logger.info("储备金查询信息异常 :" + e.getMessage());
			throw new ServiceBizException(e.getMessage());
		}
		return reserveDtOlist;
	}

    /*
    *
    * @author DELL
    * @date 2016年1月7日
    * @param reserveDTO
    * @return
    * @throws SQLException
    * (non-Javadoc)
    * @see com.sgm.dms.fol.reserve.api.ReserveService#getReserveAmountReconcileCount(com.sgm.dms.fol.reserve.dto.ReserveDTO)
    */
    	
    @Override
    public int getReserveAmountReconcileCount(ReserveDTO reserveDTO) throws SQLException {
        List<ReserveDTO> list = new ArrayList<ReserveDTO>();
        List<ReserveDTO> reservedtolist = new ArrayList<ReserveDTO>();
        List<ReserveDTO> tempReserveDTO = null;// 临时的DTOList
        // ReserveDTO reservedto=new ReserveDTO();

        try {
            // 先查询出所有的
            // 查询出本月储备金余额
            tempReserveDTO = reserveDao.getReserveAmountByDateTime(reserveDTO);
            if (tempReserveDTO != null) {
                for (ReserveDTO tempdto : tempReserveDTO) {
                    reservedtolist.add(tempdto);
                }
            }

            // 查询出本月增加或减少
            tempReserveDTO = reserveDao
                    .getReserveChangeAmountByDateTime(reserveDTO);
            if (tempReserveDTO != null) {
                for (ReserveDTO tempdto : tempReserveDTO) {
                    for (ReserveDTO realdto : reservedtolist) {
                        if (tempdto.getSapCode().equals(realdto.getSapCode())) {
                            realdto.setReserveChangeAddAmount(tempdto
                                    .getReserveChangeAddAmount());
                            realdto.setReserveChangeSubtractAmount(tempdto
                                    .getReserveChangeSubtractAmount());
                        }
                    }
                }
            }

            // SAP本月月额
            tempReserveDTO = reserveDao
                    .getMonthReserveAmountByDateTime(reserveDTO);
            if (tempReserveDTO != null) {
                for (ReserveDTO tempdto : tempReserveDTO) {
                    for (ReserveDTO realdto : reservedtolist) {
                        if (tempdto.getSapCode().equals(realdto.getSapCode())) {
                            realdto.setSapReserveAmount(tempdto
                                    .getSapReserveAmount());
                        }
                    }
                }
            }
            
            //查询出上月储备金余额 
            Integer changeMonth=Integer.parseInt(reserveDTO.getChangeTime().substring(5, 7))-1;//把时间转换到上月
            
            if(changeMonth<1){
                Integer changeYear=Integer.parseInt(reserveDTO.getChangeTime().substring(0,4))-1;
                reserveDTO.setChangeTime(changeYear+"-12");
            }else{
                reserveDTO.setChangeTime(reserveDTO.getChangeTime().substring(0,5)+DateUtil.monthReplenishZero(changeMonth.toString()));
            }
            
            tempReserveDTO=reserveDao.getReserveAmountAccumulateByDateTime(reserveDTO);
            if(tempReserveDTO!=null){
                for (ReserveDTO tempdto : tempReserveDTO) {
                    for (ReserveDTO realdto : reservedtolist) {
                        if(tempdto.getSapCode().equals(realdto.getSapCode())){
                            realdto.setReserveLastAmount(tempdto.getReserveAmount());
                        }
                    }
                }
            }
            
            if(reserveDTO.getMonth()<=1){
                reserveDTO.setYear(reserveDTO.getYear()-1);
                reserveDTO.setMonth(12);
            }else{
                reserveDTO.setMonth(reserveDTO.getMonth()-1);
            }
            
            //SAP上月余额
            tempReserveDTO=reserveDao.getMonthReserveAmountByDateTime(reserveDTO);
            if(null!=tempReserveDTO&&tempReserveDTO.size()>0){
                for (ReserveDTO tempdto : tempReserveDTO) {
                    for (ReserveDTO realdto : reservedtolist) {
                        if(tempdto.getSapCode().equals(realdto.getSapCode())){
    //                        if(tempReserveDTO!=null){
                                realdto.setSapReserveLastAmount(tempdto.getSapReserveAmount());
    //                        }
                        }
                    }
                }
            }
            for (ReserveDTO realdto : reservedtolist) {
                if (realdto.getSapCode() != null) {
                    
                    //渠道类型筛选
                    if(realdto.getDealerType()!=null&&!reserveDTO.getDealerType().trim().equals("-1")&&!realdto.getDealerType().equals(reserveDTO.getDealerType().trim())){
                        continue;
                    }else if(realdto.getDealerType()==null){
                        continue;
                    }
                    
                    

                    // 查询出上月储备金余额
                    if (realdto.getReserveLastAmount() == null) {
                        realdto.setReserveLastAmount(new BigDecimal(0));
                    }
                    
                    // 本月储备金余额为空但是上月不为空，把上月余额的值放入到本月余额
                    if(realdto.getReserveAmount() == null&&realdto.getReserveLastAmount() != null){
                        realdto.setReserveAmount(realdto.getReserveLastAmount());
                    }else if (realdto.getReserveAmount() == null&&realdto.getReserveLastAmount()==null) {
                    // 本月以及上月储备金余额为空转换成0
                        realdto.setReserveAmount(new BigDecimal(0));
                    }
                    
                    // 本月增加为空转换成0
                    if (realdto.getReserveChangeAddAmount() == null) {
                        realdto.setReserveChangeAddAmount(new BigDecimal(0));
                    }
                    // 本月减少为空转换成0
                    if (realdto.getReserveChangeSubtractAmount() == null) {
                        realdto.setReserveChangeSubtractAmount(new BigDecimal(0));
                    }
                    // SAP本月月额为空转换成0
                    if (realdto.getSapReserveAmount() == null) {
                        realdto.setSapReserveAmount(new BigDecimal(0));
                    }
                    
                    // SAP上月余额
                    if (realdto.getSapReserveLastAmount() == null) {
                        realdto.setSapReserveLastAmount(new BigDecimal(0));
                    }
                    
                    // 算出余额差异
                    if (realdto.getReserveAmount() != null
                            && realdto.getSapReserveAmount() != null) {
                        realdto.setDifferenceAmount(realdto.getReserveAmount()
                                .subtract(realdto.getSapReserveAmount()));
                    } else if (realdto.getReserveAmount() != null
                            && realdto.getSapReserveAmount() == null) {
                        realdto.setDifferenceAmount(realdto.getReserveAmount());
                    } else if (realdto.getReserveAmount() == null
                            && realdto.getSapReserveAmount() != null) {
                        realdto.setDifferenceAmount(new BigDecimal("0")
                                .subtract(realdto.getSapReserveAmount()));
                    }
                } 

                // 余额差异筛选条件
                if (StateConstant.RESERVE_AMOUNT_DIFFENCE_YES.equals(reserveDTO
                        .getDifference())) {
                    if (!realdto.getReserveAmount().equals(
                            realdto.getSapReserveAmount())
                            && (!"".equals(realdto.getSapCode()) || realdto
                                    .getSapCode() != null)) {
                        list.add(realdto);
                    }
                } else if (StateConstant.RESERVE_AMOUNT_DIFFENCE_NO
                        .equals(reserveDTO.getDifference())) {
                    if (realdto.getReserveAmount().equals(
                            realdto.getSapReserveAmount())
                            && (!"".equals(realdto.getSapCode()) || realdto
                                    .getSapCode() != null)) {
                        list.add(realdto);
                    }
                } else {
                    if (realdto.getSapCode() != null) {
                        list.add(realdto);
                    }
                }

            }

        } catch (SQLException e) {
            logger.info("储备金查询信息异常 :" + e.getMessage());
            throw new ServiceBizException(e.getMessage());
        }
        
        return list.size();
    }

    /*
    *
    * @author DELL
    * @date 2016年1月25日
    * @param dealerInfoDTO
    * @return
    * @throws SQLException
    * (non-Javadoc)
    * @see com.sgm.dms.fol.reserve.api.ReserveService#getReserveAndBonusInfoList(com.sgm.dms.fol.common.api.domain.DealerInfoDTO)
    */
    	
    @Override
    public List<DataReportDTO> getReserveAndBonusInfoList(DealerInfoDTO dealerInfoDTO) throws ServiceAppException {
        List<DataReportDTO> list = new ArrayList<DataReportDTO>();
        try {

            // 根据sapCode查询出储备金
            if(dealerInfoDTO!=null&&!"".equals(dealerInfoDTO.getSapCode())){
                ReserveDTO reserveDTO=new ReserveDTO();  
                reserveDTO.setSapCode(dealerInfoDTO.getSapCode());
                ReserveDTO reservedto = reserveDao.findReserveRecordBySapCode(reserveDTO);
    
                // 用于动态查询的临时对象
                BonusDTO tempDto = new BonusDTO();
                tempDto.setSapCode(reserveDTO.getSapCode());
    
                BonusPo bonusPo = bonusDao.getBounsRecordBySapCode(tempDto);
                BonusDTO bonusDTO = BeanMapper.map(bonusPo, BonusDTO.class);
    
                DataReportDTO dataReportDTO=new DataReportDTO();
                // 设置我的账户需要的值
                if (reservedto != null && bonusDTO == null) {
                    dataReportDTO.setReserveTotalAmount(reservedto.getTotalAmount());
                    dataReportDTO.setReserveFrozenAmount(reservedto.getFrozenAmount());
                    dataReportDTO.setReserveAvailAmount(reservedto.getAvailAmount());
                    dataReportDTO.setBonusTotalAmount(new BigDecimal(0));
                    dataReportDTO.setBonusFrozenAmount(new BigDecimal(0));
                    dataReportDTO.setBonusAvailAmount(new BigDecimal(0));
                    dataReportDTO.setSapCode(reservedto.getSapCode());
                    dataReportDTO.setDealerName(reservedto.getDealerName());
                } else if (reservedto != null && bonusDTO != null) {
                    dataReportDTO.setReserveTotalAmount(reservedto.getTotalAmount());
                    dataReportDTO.setReserveFrozenAmount(reservedto.getFrozenAmount());
                    dataReportDTO.setReserveAvailAmount(reservedto.getAvailAmount());
                    dataReportDTO.setBonusTotalAmount(bonusDTO.getTotalAmount());
                    dataReportDTO.setBonusFrozenAmount(bonusDTO.getFrozenAmount());
                    dataReportDTO.setBonusAvailAmount(bonusDTO.getTotalAmount()
                            .subtract(bonusDTO.getFrozenAmount()));
                    dataReportDTO.setSapCode(reservedto.getSapCode());
                    dataReportDTO.setDealerName(reservedto.getDealerName());
                } else if (reservedto == null && bonusDTO != null) {
                    dataReportDTO.setReserveTotalAmount(new BigDecimal(0));
                    dataReportDTO.setReserveFrozenAmount(new BigDecimal(0));
                    dataReportDTO.setReserveAvailAmount(new BigDecimal(0));
                    dataReportDTO.setBonusTotalAmount(bonusDTO.getTotalAmount());
                    dataReportDTO.setBonusFrozenAmount(bonusDTO.getFrozenAmount());
                    dataReportDTO.setBonusAvailAmount(bonusDTO.getTotalAmount()
                            .subtract(bonusDTO.getFrozenAmount()));
                    dataReportDTO.setSapCode(bonusDTO.getSapCode());
                    dataReportDTO.setDealerName(bonusDTO.getDealerName());
                } else {
                    dataReportDTO.setReserveTotalAmount(new BigDecimal(0));
                    dataReportDTO.setReserveFrozenAmount(new BigDecimal(0));
                    dataReportDTO.setReserveAvailAmount(new BigDecimal(0));
                    dataReportDTO.setBonusTotalAmount(new BigDecimal(0));
                    dataReportDTO.setBonusFrozenAmount(new BigDecimal(0));
                    dataReportDTO.setBonusAvailAmount(new BigDecimal(0));
                    dataReportDTO.setSapCode(reserveDTO.getSapCode());
    
                    // 根据SAPCODE去库里差dealerInfo
                    DealerInfoDTO dealerinfodto = new DealerInfoDTO();
                    dealerinfodto.setSapCode(dataReportDTO.getSapCode());
                    DealerInfoPo dealerInfoPo = dealerDao
                            .getDealerInfoBySapCode(dealerinfodto);
                    if (dealerInfoPo == null) {
                        return null;
                    } else {
                        dataReportDTO.setDealerName(dealerInfoPo.getDealerName());
                    }
    
                }
                list.add(dataReportDTO);
    
            }else{               
                List<ReserveDTO> reservedtos = reserveDao.findReserveRecord(dealerInfoDTO);
    
                if(reservedtos!=null &&reservedtos.size()>0){
                    for (ReserveDTO dto : reservedtos) {
                        DataReportDTO dataReportDTO=new DataReportDTO();
                        
                        // 设置我的账户需要的值
                        if (dto != null) {
                            dataReportDTO.setReserveTotalAmount(dto.getTotalAmount());
                            dataReportDTO.setReserveFrozenAmount(dto.getFrozenAmount());
                            dataReportDTO.setReserveAvailAmount(dto.getAvailAmount());
                            dataReportDTO.setBonusAvailAmount(new BigDecimal("0"));
                            dataReportDTO.setBonusTotalAmount(new BigDecimal("0"));
                            dataReportDTO.setBonusFrozenAmount(new BigDecimal("0"));
                            dataReportDTO.setSapCode(dto.getSapCode());
                            dataReportDTO.setDealerName(dto.getDealerName());
                            list.add(dataReportDTO);
                        } 
                    }
                }
               
    
                List<BonusPo> bonusPos = bonusDao.getBounsRecord(dealerInfoDTO);
                
                if(bonusPos!=null&&bonusPos.size()>0){
                    for (BonusPo bonusPo : bonusPos) {
                        if(bonusPo!=null){
                            boolean flag=false;
                            for (DataReportDTO dataReportDTO : list) {
                                if(dataReportDTO.getSapCode().equals(bonusPo.getSapCode())){
                                    flag=true;
                                    dataReportDTO.setBonusTotalAmount(bonusPo.getTotalAmount());
                                    dataReportDTO.setBonusFrozenAmount(bonusPo.getFrozenAmount());
                                    dataReportDTO.setBonusAvailAmount(bonusPo.getTotalAmount().subtract(bonusPo.getFrozenAmount()));
                                    break;
                                }
                            }
                            
                            if(!flag){
                                DataReportDTO dataReportDTO=new DataReportDTO();
                                dataReportDTO.setReserveAvailAmount(new BigDecimal("0"));
                                dataReportDTO.setReserveTotalAmount(new BigDecimal(0));
                                dataReportDTO.setReserveFrozenAmount(new BigDecimal("0"));
                                dataReportDTO.setBonusTotalAmount(bonusPo.getTotalAmount());
                                dataReportDTO.setBonusFrozenAmount(bonusPo.getFrozenAmount());
                                dataReportDTO.setBonusAvailAmount(bonusPo.getTotalAmount().subtract(bonusPo.getFrozenAmount()));
                                dataReportDTO.setSapCode(bonusPo.getSapCode());
                                dataReportDTO.setDealerName(bonusPo.getDealerName());
                                list.add(dataReportDTO);
                            }
                        }
                    }
                } 
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw(new ServiceAppException(e));
        } catch (Exception e) {
            e.printStackTrace();
            throw(new ServiceAppException(e));
        }
       
        return list;
    }

    /*
    *
    * @author DELL
    * @date 2016年1月25日
    * @param dealerInfoDTO
    * @return
    * @throws ServiceAppException
    * (non-Javadoc)
    * @see com.sgm.dms.fol.reserve.api.ReserveService#getReserveAndBonusInfoCount(com.sgm.dms.fol.common.api.domain.DealerInfoDTO)
    */
    	
    @Override
    public int getReserveAndBonusInfoCount(DealerInfoDTO dealerInfoDTO) throws ServiceAppException {
        dealerInfoDTO.setBeginNo(null);
        dealerInfoDTO.setEndNo(null);
        List<?> list=getReserveAndBonusInfoList(dealerInfoDTO);
        if(list!=null&&list.size()>0){
            return list.size();
        }else{
            return 0;
        }
       
    }
}
