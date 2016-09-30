package com.sgm.dms.fol.bonus.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgm.dms.fol.bonus.api.BonusService;
import com.sgm.dms.fol.bonus.domain.BonusChangeRecordPo;
import com.sgm.dms.fol.bonus.domain.BonusPo;
import com.sgm.dms.fol.bonus.dto.BonusChangeRecordDTO;
import com.sgm.dms.fol.bonus.dto.BonusDTO;
import com.sgm.dms.fol.bonus.dto.BonusQueryDTO;
import com.sgm.dms.fol.bonus.dto.BonusReconcileDTO;
import com.sgm.dms.fol.bonus.repository.BonusDao;
import com.sgm.dms.fol.common.api.constants.CodeConstant;
import com.sgm.dms.fol.common.api.constants.StateConstant;
import com.sgm.dms.fol.common.api.exception.ServiceAppException;
import com.sgm.dms.fol.common.service.utils.BeanMapper;
import com.sgm.dms.fol.common.service.utils.DateUtil;
import com.sgm.dms.fol.common.service.utils.RSAUtil;
import com.sgm.dms.fol.common.service.utils.StringUtil;

@Service("BonusService")
@Transactional(rollbackFor=Exception.class)
public class BonusServiceImpl implements BonusService {
	// 日志操作
	protected Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private BonusDao bonusDao;

	@Override
	public BonusDTO getBounsRecordBySapCode(BonusDTO bounsDTO)
			throws ServiceAppException,SQLException {
		BonusPo bonusPo = bonusDao.getBounsRecordBySapCode(bounsDTO);
		BonusDTO bonusDTO = BeanMapper.map(bonusPo, BonusDTO.class);
		return bonusDTO;
	}

	@Override
	public BonusDTO getBounsChangeReconBySapCode(BonusDTO bounsDTO)
			throws ServiceAppException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<BonusChangeRecordDTO> getBonusMonthFistAndLastByDateTime(
			BonusDTO bounsDTO) throws ServiceAppException {
		List<BonusChangeRecordDTO> data = new ArrayList<BonusChangeRecordDTO>();
		List<BonusChangeRecordPo> bonuspo;
		try {
			bonuspo = bonusDao
					.getBonusMonthFistAndLastByDateTime(bounsDTO);
			if (null != bonuspo && bonuspo.size() > 0) {
				data = BeanMapper.mapList(bonuspo, BonusChangeRecordDTO.class);
			}
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceAppException(e.getMessage());		}
		return data;
	}

	/**
	 * 奖金月度明细查询
	 */
	@Override
	public List<BonusReconcileDTO> getBonusMonthDeatil(
			BonusReconcileDTO bounsDTO) throws ServiceAppException {
		List<BonusReconcileDTO> bonusDtOlist = null;
		try {
			// 查询返回结果
			bonusDtOlist = bonusDao.getBonusMonthDeatil(bounsDTO);
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceAppException(e.getMessage());
		}
		return bonusDtOlist;
	}

	/**
	 * 奖金余额汇总
	 */
	@Override
	public List<BonusReconcileDTO> getAmountReconcileCollect(
			BonusReconcileDTO bounsDTO) throws ServiceAppException {
		List<BonusReconcileDTO> bonusdtolist = new ArrayList<BonusReconcileDTO>();
		List<BonusReconcileDTO> tempBonusDTO = null;// 临时的DTOList
		try {
//			tempBonusDTO = bonusDao.getAmountReconcileCollect(bounsDTO);
//			if(tempBonusDTO != null){
//				for (BonusReconcileDTO tempdto : tempBonusDTO) {
//					tempdto.setBonusSub(BigDecimal.valueOf(0));
//					bonusdtolist.add(tempdto);
//				}
//			}
			tempBonusDTO = bonusDao.findAmountReconcileCollect(bounsDTO);
			if(tempBonusDTO != null){
				for (BonusReconcileDTO tempdto : tempBonusDTO) {
//					for (BonusReconcileDTO realdto : bonusdtolist) {
//						if(realdto.getSapCode().equals(tempdto.getSapCode())){
//							realdto.setBeforeChangeAmount(tempdto.getBeforeChangeAmount());
//							realdto.setAfterChangeAmount(tempdto.getAfterChangeAmount());
//						}
//					}
					tempdto.setBonusSub(BigDecimal.valueOf(0));
					bonusdtolist.add(tempdto);
				}
			}
			//查询订单使用
			bounsDTO.setReferType(CodeConstant.BONUS_BUTTON_REFER_TYPE);
			tempBonusDTO = bonusDao.getOrderByTime(bounsDTO);
			if(tempBonusDTO != null){
				for (BonusReconcileDTO tempdto : tempBonusDTO) {
					for (BonusReconcileDTO realdto : bonusdtolist) {
						if(realdto.getSapCode().equals(tempdto.getSapCode())){
							realdto.setOrderSub(tempdto.getOrderSub());
						}
					}
				}
			}
			// 查询本月颁发
			bounsDTO.setReferType(CodeConstant.BONUS_ISSUE_REFER_TYPE);
			tempBonusDTO = bonusDao.getBonusByTime(bounsDTO);
			if (tempBonusDTO != null) {
				for (BonusReconcileDTO tempdto : tempBonusDTO) {
					for (BonusReconcileDTO realdto : bonusdtolist) {
						if (realdto.getSapCode().equals(tempdto.getSapCode())) {
							realdto.setBonusAdd(tempdto.getBonusAdd());

						}

				}
			}
		}
		
			String queryTime=bounsDTO.getYear()+"-"+bounsDTO.getMonth();
			for (BonusReconcileDTO realdto : bonusdtolist) {
				String systime=new Timestamp(System.currentTimeMillis()).toString().substring(0,7);
				
				if(realdto.getBonusAdd()==null){
					realdto.setBonusAdd(new BigDecimal(0));
				}
				if(realdto.getOrderSub()==null){
					realdto.setOrderSub(new BigDecimal(0));
				}
				if(realdto.getBeforeChangeAmount()!=null && (realdto.getAfterChangeAmount()==null||realdto.getAfterChangeAmount().compareTo(BigDecimal.valueOf(0))==0)){
					realdto.setAfterChangeAmount(realdto.getBeforeChangeAmount());
				}
				if(realdto.getAfterChangeAmount()==null&& systime.compareTo(queryTime)>0){
					realdto.setAfterChangeAmount(realdto.getFolBonusAmount());					
				}else if(realdto.getAfterChangeAmount()==null){
					realdto.setAfterChangeAmount(new BigDecimal(0));
				}
				if(realdto.getBeforeChangeAmount()==null&& systime.compareTo(queryTime)>0){
					realdto.setBeforeChangeAmount(realdto.getFolBonusAmount());
					
				}else if(realdto.getBeforeChangeAmount()==null){
					realdto.setBeforeChangeAmount(new BigDecimal(0));
				}
				
				//月末余额重新算
				//月初余额+本月颁发-订单使用
				BigDecimal lastFund=realdto.getBeforeChangeAmount().add(realdto.getBonusAdd()).add(realdto.getOrderSub());
				realdto.setAfterChangeAmount(lastFund);
			}
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceAppException(e.getMessage());
		}
		return bonusdtolist;
	
	}

	/**
	 * 奖金月度报表
	 */
	@Override
	public List<BonusReconcileDTO> getBonusMonthForm(BonusReconcileDTO bounsDTO)
			throws ServiceAppException {
		List<BonusReconcileDTO> bonusdtolist = new ArrayList<BonusReconcileDTO>();
		List<BonusReconcileDTO> returnbonuslist = new ArrayList<BonusReconcileDTO>();
		List<BonusReconcileDTO> tempReserveDTO = null;// 临时的DTOList
		
		try {
			// 查询期初.期末和年�?			
			tempReserveDTO = bonusDao.getBonusMonthForm(bounsDTO);
				for (BonusReconcileDTO tempdto : tempReserveDTO) {
					tempdto.setBonusSub(BigDecimal.valueOf(0));
					bonusdtolist.add(tempdto);
			}

			tempReserveDTO = bonusDao.getBeforeAmount(bounsDTO);
			if (tempReserveDTO != null) {
				for (BonusReconcileDTO tempdto : tempReserveDTO) {
					for (BonusReconcileDTO realdto : bonusdtolist) {
						if (tempdto.getSapCode().equals(realdto.getSapCode())
								&&tempdto.getYear().equals(realdto.getYear())
								&&tempdto.getMonth().equals(realdto.getMonth())
								) 
						{
							realdto.setBeforeChangeAmount(tempdto.getBeforeChangeAmount());
						}

					}
				}
			}
			for (BonusReconcileDTO realdto : bonusdtolist) {
				if(realdto.getBeforeChangeAmount() !=null && realdto.getAfterChangeAmount()==null){
					realdto.setAfterChangeAmount(realdto.getBeforeChangeAmount());
				}else if(realdto.getAfterChangeAmount()==null){
					realdto.setAfterChangeAmount(new BigDecimal(0));
				}
				
			}	
			// 查询新增颁发
			bounsDTO.setReferType(CodeConstant.BONUS_ISSUE_REFER_TYPE);
			tempReserveDTO = bonusDao.getAmountByReferType(bounsDTO);
			if (tempReserveDTO != null) {
				for (BonusReconcileDTO tempdto : tempReserveDTO) {
					for (BonusReconcileDTO realdto : bonusdtolist) {
						if (tempdto.getSapCode().equals(realdto.getSapCode())
								&& tempdto.getYear().equals(realdto.getYear())
								&& tempdto.getMonth().equals(realdto.getMonth())
								) 
						{
							realdto.setBonusAddAmount(tempdto.getBonusAddAmount());
						}

					}
				}
			}
			for (BonusReconcileDTO realdto : bonusdtolist) {
				if(realdto.getBonusAddAmount()==null){
					realdto.setBonusAddAmount(new BigDecimal(0));
				}
			}
			// 查询出订单扣款
			bounsDTO.setReferType(CodeConstant.BONUS_BUTTON_REFER_TYPE);
			tempReserveDTO = bonusDao.getOrderButton(bounsDTO);
			if (tempReserveDTO != null) {
				for (BonusReconcileDTO tempdto : tempReserveDTO) {
					for (BonusReconcileDTO realdto : bonusdtolist) {
						if (tempdto.getSapCode().equals(realdto.getSapCode())
								&& tempdto.getYear().equals(realdto.getYear())
								&& tempdto.getMonth().equals(realdto.getMonth())
								) {
							realdto.setOrderSub(tempdto.getOrderSub());
						}
					}
				}
			} 
			for (BonusReconcileDTO realdto : bonusdtolist) {
				if(realdto.getOrderSub()==null){
					realdto.setOrderSub(new BigDecimal(0));
				}
			}
			bounsDTO.setReferType(CodeConstant.BONUS_UNFREEZE_REFER_TYPE);
			// 查询出订单解�?
			tempReserveDTO = bonusDao.getFreezeBonusByTime(bounsDTO);
			if (tempReserveDTO != null) {
				for (BonusReconcileDTO tempdto : tempReserveDTO) {
					for (BonusReconcileDTO realdto : bonusdtolist) {
						if (tempdto.getSapCode().equals(realdto.getSapCode())
								&& tempdto.getYear().equals(realdto.getYear())
								&& tempdto.getMonth().equals(realdto.getMonth())	
								) {
							realdto.setFreezeAmount(tempdto.getFreezeAmount());
						}
					}
				}
			}
			for (BonusReconcileDTO realdto : bonusdtolist) {
				if(realdto.getFreezeAmount()==null){
					realdto.setFreezeAmount(new BigDecimal(0));
				}
			}
			// 查询出订单冻�?
			bounsDTO.setReferType(CodeConstant.BONUS_FREEZE_REFER_TYPE);
			tempReserveDTO = bonusDao.getFrozenBonusByTime(bounsDTO);
			if (tempReserveDTO != null) {
				for (BonusReconcileDTO tempdto : tempReserveDTO) {
					for (BonusReconcileDTO realdto : bonusdtolist) {
						if (tempdto.getSapCode().equals(realdto.getSapCode())
								&& tempdto.getYear().equals(realdto.getYear())
								&& tempdto.getMonth().equals(realdto.getMonth())		
								) {
							realdto.setFrozenAmount(tempdto.getFrozenAmount());
						}
					}
				}
			}
			for (BonusReconcileDTO realdto : bonusdtolist) {
				if(realdto.getFrozenAmount()==null){
					realdto.setFrozenAmount(new BigDecimal(0));
				}
				if(bounsDTO.getSapCode().equals(realdto.getSapCode())){
					BigDecimal lastfund=realdto.getBeforeChangeAmount().add(realdto.getBonusAddAmount()).add(realdto.getOrderSub());
					realdto.setAfterChangeAmount(lastfund);
					returnbonuslist.add(realdto);
				}
				
			}
			
		} catch (SQLException e) {
			logger.error(e);
			throw new ServiceAppException(e.getMessage());
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceAppException(e.getMessage());
		}
		return returnbonuslist;
	}

	/**
	 * 奖金余额对账
	 * @param bonusDTO
	 * @return
	 * @throws SQLException
	 */

	@Override
	public List<BonusReconcileDTO> getBonusAmountReconcile(
			BonusReconcileDTO bounsDTO) throws ServiceAppException {

		List<BonusReconcileDTO> list = new ArrayList<BonusReconcileDTO>();
		List<BonusReconcileDTO> realList = new ArrayList<BonusReconcileDTO>();
		List<BonusReconcileDTO> bonusdtolist = new ArrayList<BonusReconcileDTO>();
		List<BonusReconcileDTO> tempBonusDTO = null;// 临时的DTOList

		try {	
	
			
			// 查询出本月期末余额
			tempBonusDTO = bonusDao.getBonusAmountByDateTime(bounsDTO);
			if (tempBonusDTO != null) {
				for (BonusReconcileDTO tempdto : tempBonusDTO) {
					tempdto.setBonusSub(BigDecimal.valueOf(0));
					bonusdtolist.add(tempdto);
				}
			}

			
			Integer changeMonth = Integer.parseInt(bounsDTO.getChangeTime()
					.substring(5, 7)) - 1;// 把时间转换到上月

			BonusReconcileDTO bounsLastDTO=BeanMapper.map(bounsDTO, BonusReconcileDTO.class);
			
			if (changeMonth < 1) {
				Integer changeYear = Integer.parseInt(bounsDTO.getChangeTime()
						.substring(0, 4)) - 1;
				bounsLastDTO.setChangeTime(changeYear + "-12");
			} else {
				bounsLastDTO.setChangeTime(bounsDTO.getChangeTime().substring(0, 5)
						+ DateUtil.monthReplenishZero(changeMonth.toString()));
			}
			
			// 查询期初余额
			tempBonusDTO = bonusDao.findBonusAmountByDateTime(bounsLastDTO);
			if (tempBonusDTO != null) {
				for (BonusReconcileDTO tempdto : tempBonusDTO) {
					for (BonusReconcileDTO realdto : bonusdtolist) {
						if (tempdto.getSapCode().equals(realdto.getSapCode())) {
							realdto.setBonusLastAmount(tempdto.getBonusLastAmount());
						}
					}
				}
			}
			// 查询出本月增加或减少
			tempBonusDTO = bonusDao.getBonusChangeAmountByDateTime(bounsDTO);
			if (tempBonusDTO != null) {
				for (BonusReconcileDTO tempdto : tempBonusDTO) {
					for (BonusReconcileDTO realdto : bonusdtolist) {
						if (tempdto.getSapCode().equals(realdto.getSapCode())) {
							realdto.setBonusAdd(tempdto
									.getBonusAdd());
							realdto.setBillBonus(tempdto
									.getBillBonus());
						}
					}
				}
			}

			// SAP本月月额
			tempBonusDTO = bonusDao.getMonthBonusAmountByDateTime(bounsDTO);
			if (tempBonusDTO != null) {
				for (BonusReconcileDTO tempdto : tempBonusDTO) {
					for (BonusReconcileDTO realdto : bonusdtolist) {
						if (tempdto.getSapCode().equals(realdto.getSapCode())) {
							realdto.setSapBonusAmount(tempdto
									.getSapBonusAmount());
						}
					}
				}
				for (BonusReconcileDTO realdto : bonusdtolist) {
					if(realdto.getSapBonusAmount()==null){
						realdto.setSapBonusAmount(new BigDecimal(0));
					}
				}
			}
			
			if (changeMonth < 1) {
				Integer changeYear = Integer.parseInt(bounsDTO.getChangeTime()
						.substring(0, 4)) - 1;
				bounsDTO.setChangeTime(changeYear + "-12");
			} else {
				bounsDTO.setChangeTime(bounsDTO.getChangeTime().substring(0, 5)
						+ DateUtil.monthReplenishZero(changeMonth.toString()));
			}
			
			if (Integer.valueOf(bounsDTO.getMonth()).intValue() <= 1) {
				bounsDTO.setYear((Integer.valueOf(bounsDTO.getYear()) - 1)+"");
				bounsDTO.setMonth("12");
			} else {
				bounsDTO.setMonth((Integer.valueOf(bounsDTO.getMonth()) - 1)+"");
			}

			// SAP上月余额
			tempBonusDTO=bonusDao.getMonthBonusAmountByDateTime(bounsDTO);
	            if(null!=tempBonusDTO&&tempBonusDTO.size()>0){
		            for (BonusReconcileDTO tempdto : tempBonusDTO) {
		                for (BonusReconcileDTO realdto : bonusdtolist) {
		                    if(tempdto.getSapCode().equals(realdto.getSapCode())){
		                            realdto.setSapBonusLastAmount(tempdto.getSapBonusAmount());
		                    }
		                }
		            }
	            }
	
			for (BonusReconcileDTO realdto : bonusdtolist) {
				if (realdto.getSapCode() != null) {
					//渠道类型筛选
	                if(realdto.getDealerType()!=null&&!bounsDTO.getDealerType().trim().equals("-1")&&!realdto.getDealerType().equals(bounsDTO.getDealerType().trim())){
	                    continue;
	                }else if(realdto.getDealerType()==null){
	                    continue;
	                }
	                
					//如果当月未发生变动则
					if(realdto.getBonusLastAmount() != null && realdto.getBonusAmount() == null){
						realdto.setBonusAmount(realdto.getBonusLastAmount());
					}
					// 本月奖金余额为空转换成0
					if (realdto.getBonusAmount() == null && realdto.getBonusLastAmount()==null){
						realdto.setBonusAmount(new BigDecimal(0));
					}
					// 本月增加为空转换�?
					if (realdto.getBonusAdd() == null) {
						realdto.setBonusAdd(new BigDecimal(0));
					}
					// 本月减少为空转换�?
					if (realdto.getBillBonus() == null) {
						realdto.setBillBonus(new BigDecimal(0));
					}
					// SAP本月月额为空转换�?
					if (realdto.getSapBonusAmount() == null) {
						realdto.setSapBonusAmount(new BigDecimal(0));
					}
					// 查询出上月奖金余额
					if (realdto.getBonusLastAmount() == null) {
						realdto.setBonusLastAmount(new BigDecimal(0));
					}
					// SAP上月余额
					if (realdto.getSapBonusLastAmount() == null) {
						realdto.setSapBonusLastAmount(new BigDecimal(0));
					}
				}
				
				//月末余额重新算
				//月初余额+本月颁发-订单使用
				BigDecimal lastFund=realdto.getBonusLastAmount().add(realdto.getBonusAdd()).add(realdto.getBillBonus());
				realdto.setBonusAmount(lastFund);
				
				// 算出余额差异
				if (realdto.getBonusAmount() != null
						&& realdto.getSapBonusAmount() != null) {
					realdto.setDifferenceAmount(realdto.getBonusAmount()
							.subtract(realdto.getSapBonusAmount()));
				} else if (realdto.getBonusAmount() != null
						&& realdto.getSapBonusAmount() == null) {
					realdto.setDifferenceAmount(realdto.getBonusAmount());
				} else if (realdto.getBonusAmount() == null
						&& realdto.getSapBonusAmount() != null) {
					realdto.setDifferenceAmount(new BigDecimal("0")
							.subtract(realdto.getSapBonusAmount()));
				}
				

				
				// 余额差异筛�?条件
				if (StateConstant.RESERVE_AMOUNT_DIFFENCE_YES.equals(bounsDTO
						.getIsDiff()+"")) {
					if (realdto.getBonusAmount().compareTo(realdto.getSapBonusAmount())!=0
							&& (!"".equals(realdto.getSapCode()) || realdto
									.getSapCode() != null)) {
						list.add(realdto);
					}
				} else if (StateConstant.RESERVE_AMOUNT_DIFFENCE_NO
						.equals(bounsDTO.getIsDiff()+"")) {
					if (realdto.getBonusAmount().compareTo(realdto.getSapBonusAmount())==0
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

		} catch (Exception e) {
			logger.error(e);
			throw new ServiceAppException(e.getMessage());
		}

	    //取出startSapCode和endSapCode的区间数据，没有则全部
        for (BonusReconcileDTO dto : list) {
            if(!StringUtil.isEmpty(bounsDTO.getStartSapCode())&&
                    StringUtil.isEmpty(bounsDTO.getEndSapCode())&&
                    Integer.parseInt(dto.getSapCode())>=Integer.parseInt(bounsDTO.getStartSapCode())){
                realList.add(dto);
            }else if(StringUtil.isEmpty(bounsDTO.getStartSapCode())&&
                    !StringUtil.isEmpty(bounsDTO.getEndSapCode())&&
                    Integer.parseInt(dto.getSapCode())<=Integer.parseInt(bounsDTO.getEndSapCode())){
                realList.add(dto);
            }else if(!StringUtil.isEmpty(bounsDTO.getStartSapCode())&&
                    !StringUtil.isEmpty(bounsDTO.getEndSapCode())&&
                    Integer.parseInt(dto.getSapCode())>=Integer.parseInt(bounsDTO.getStartSapCode())&&
                    Integer.parseInt(dto.getSapCode())<=Integer.parseInt(bounsDTO.getEndSapCode())){
                realList.add(dto);
            }else if(StringUtil.isEmpty(bounsDTO.getStartSapCode())&&StringUtil.isEmpty(bounsDTO.getEndSapCode())){
                realList.add(dto);
            }
            
        }
        
        if (null == bounsDTO.getBeginNo() && null == bounsDTO.getEndNo()) {
            return realList;
        }

		if (null != bounsDTO.getBeginNo() && bounsDTO.getBeginNo() == 0) {
			if (null != bounsDTO.getEndNo()
					&& bounsDTO.getEndNo() > realList.size()) {
			    realList = realList.subList(bounsDTO.getBeginNo(), realList.size());
			} else {
			    realList = realList.subList(bounsDTO.getBeginNo(), bounsDTO.getEndNo());
			}
		} else {
			if (null != bounsDTO.getEndNo()
					&& bounsDTO.getEndNo() > realList.size()) {
			    realList = realList.subList(bounsDTO.getBeginNo() - 1, realList.size());
			} else {
			    realList = realList.subList(bounsDTO.getBeginNo() - 1,
						bounsDTO.getEndNo());
			}
		}
		

		return realList;
	}

	@Override
	public List<BonusReconcileDTO> getFreezeBonusByTime(
			BonusReconcileDTO bonusDTO) throws ServiceAppException {
		List<BonusReconcileDTO> bonusDtOlist = null;
		try {
			// 查询返回结果
			bonusDtOlist = bonusDao.getFreezeBonusByTime(bonusDTO);
		} catch (Exception e) {
			logger.info(e);
			throw new ServiceAppException(e.getMessage());
		}
		return bonusDtOlist;
	}

	@Override
	public List<BonusReconcileDTO> getOrderByTime(BonusReconcileDTO bonusDTO)
			throws ServiceAppException {
		List<BonusReconcileDTO> bonusDtOlist = null;
		try {
			// 查询返回结果
			bonusDtOlist = bonusDao.getOrderByTime(bonusDTO);
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceAppException(e.getMessage());
		}
		return bonusDtOlist;
	}

	@Override
	public List<BonusReconcileDTO> getBonusByTime(BonusReconcileDTO bonusDTO)
			throws ServiceAppException {
		List<BonusReconcileDTO> bonusDtOlist = null;
		try {
			// 查询返回结果
			bonusDtOlist = bonusDao.getBonusByTime(bonusDTO);
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceAppException(e.getMessage());
		}
		return bonusDtOlist;
	}

	@Override
	public List<BonusReconcileDTO> getAmountByTime(BonusReconcileDTO bonusDTO)
			throws ServiceAppException {
		List<BonusReconcileDTO> bonusDtOlist = null;
		try {
			// 查询返回结果
			bonusDtOlist = bonusDao.getAmountByTime(bonusDTO);
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceAppException(e.getMessage());
		}
		return bonusDtOlist;
	}

	@Override
	public int getBonusAmountReconcileCount(BonusReconcileDTO bounsDTO)
			throws ServiceAppException {

		List<BonusReconcileDTO> list = new ArrayList<BonusReconcileDTO>();
		List<BonusReconcileDTO> bonusdtolist = new ArrayList<BonusReconcileDTO>();
		List<BonusReconcileDTO> tempBonusDTO = null;// 临时的DTOList

		try {	
	
			
			// 查询出本月奖金余额
			tempBonusDTO = bonusDao.getBonusAmountByDateTime(bounsDTO);
			if (tempBonusDTO != null) {
				for (BonusReconcileDTO tempdto : tempBonusDTO) {
					tempdto.setBonusSub(BigDecimal.valueOf(0));
					bonusdtolist.add(tempdto);
				}
			}

			// 查询出本月增加或减少
			tempBonusDTO = bonusDao.getBonusChangeAmountByDateTime(bounsDTO);
			if (tempBonusDTO != null) {
				for (BonusReconcileDTO tempdto : tempBonusDTO) {
					for (BonusReconcileDTO realdto : bonusdtolist) {
						if (tempdto.getSapCode().equals(realdto.getSapCode())) {
							realdto.setBonusAdd(tempdto
									.getBonusAdd());
							realdto.setBillBonus(tempdto
									.getBillBonus());
						}
					}
				}
			}

			// SAP本月月额
			tempBonusDTO = bonusDao.getMonthBonusAmountByDateTime(bounsDTO);
			if (tempBonusDTO != null) {
				for (BonusReconcileDTO tempdto : tempBonusDTO) {
					for (BonusReconcileDTO realdto : bonusdtolist) {
						if (tempdto.getSapCode().equals(realdto.getSapCode())) {
							realdto.setSapBonusAmount(tempdto
									.getSapBonusAmount());
						}
					}
				}
				for (BonusReconcileDTO realdto : bonusdtolist) {
					if(realdto.getSapBonusAmount()==null){
						realdto.setSapBonusAmount(new BigDecimal(0));
					}
				}
			}
			// 查询出上月奖金余�?
			Integer changeMonth = Integer.parseInt(bounsDTO.getChangeTime()
					.substring(5, 7)) - 1;// 把时间转换到上月

			if (changeMonth < 1) {
				Integer changeYear = Integer.parseInt(bounsDTO.getChangeTime()
						.substring(0, 4)) - 1;
				bounsDTO.setChangeTime(changeYear + "-12");
			} else {
				bounsDTO.setChangeTime(bounsDTO.getChangeTime().substring(0, 5)
						+ DateUtil.monthReplenishZero(changeMonth.toString()));
			}

			if (Integer.valueOf(bounsDTO.getMonth()).intValue() <= 1) {
				bounsDTO.setYear((Integer.valueOf(bounsDTO.getYear()) - 1)+"");
				bounsDTO.setMonth("12");
			} else {
				bounsDTO.setMonth((Integer.valueOf(bounsDTO.getMonth()) - 1)+"");
			}

			// SAP上月余额
			tempBonusDTO=bonusDao.getMonthBonusAmountByDateTime(bounsDTO);
	            if(null!=tempBonusDTO&&tempBonusDTO.size()>0){
		            for (BonusReconcileDTO tempdto : tempBonusDTO) {
		                for (BonusReconcileDTO realdto : bonusdtolist) {
		                    if(tempdto.getSapCode().equals(realdto.getSapCode())){
		                            realdto.setSapBonusLastAmount(tempdto.getSapBonusAmount());
		                    }
		                }
		            }
	            }
	
			for (BonusReconcileDTO realdto : bonusdtolist) {
				if (realdto.getSapCode() != null) {
					//渠道类型筛选
	                if(realdto.getDealerType()!=null&&!bounsDTO.getDealerType().trim().equals("-1")&&!realdto.getDealerType().equals(bounsDTO.getDealerType().trim())){
	                    continue;
	                }else if(realdto.getDealerType()==null){
	                    continue;
	                }
	                
					// 算出余额差异
					if (realdto.getBonusAmount() != null
							&& realdto.getSapBonusAmount() != null) {
						realdto.setDifferenceAmount(realdto.getBonusAmount()
								.subtract(realdto.getSapBonusAmount()));
					} else if (realdto.getBonusAmount() != null
							&& realdto.getSapBonusAmount() == null) {
						realdto.setDifferenceAmount(realdto.getBonusAmount());
					} else if (realdto.getBonusAmount() == null
							&& realdto.getSapBonusAmount() != null) {
						realdto.setDifferenceAmount(new BigDecimal("0")
								.subtract(realdto.getSapBonusAmount()));
					}

					// 本月奖金余额为空转换成0
					if (realdto.getBonusAmount() == null) {
						realdto.setBonusAmount(new BigDecimal(0));
					}
					// 本月增加为空转换�?
					if (realdto.getBonusAdd() == null) {
						realdto.setBonusAdd(new BigDecimal(0));
					}
					// 本月减少为空转换�?
					if (realdto.getBillBonus() == null) {
						realdto.setBillBonus(new BigDecimal(0));
					}
					// SAP本月月额为空转换�?
					if (realdto.getSapBonusAmount() == null) {
						realdto.setSapBonusAmount(new BigDecimal(0));
					}
					// 查询出上月奖金余额
					if (realdto.getBonusLastAmount() == null) {
						realdto.setBonusLastAmount(new BigDecimal(0));
					}
					// SAP上月余额
					if (realdto.getSapBonusLastAmount() == null) {
						realdto.setSapBonusLastAmount(new BigDecimal(0));
					}
				}
				// 余额差异筛�?条件
				if (StateConstant.RESERVE_AMOUNT_DIFFENCE_YES.equals(bounsDTO
						.getIsDiff()+"")) {
					if (!realdto.getBonusAmount().equals(
							realdto.getSapBonusAmount())
							&& (!"".equals(realdto.getSapCode()) || realdto
									.getSapCode() != null)) {
						list.add(realdto);
					}
				} else if (StateConstant.RESERVE_AMOUNT_DIFFENCE_NO
						.equals(bounsDTO.getIsDiff()+"")) {
					if (realdto.getBonusAmount().equals(
							realdto.getSapBonusAmount())
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
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceAppException(e.getMessage());
		}

		return list.size();
	}

    /*
    *
    * @author DELL
    * @date 2016年1月21日
    * @param bonusQueryDTO
    * @return
    * @throws SQLException
    * (non-Javadoc)
    * @see com.sgm.dms.fol.bonus.api.BonusService#getBonusChangeDeatilWithDealer(com.sgm.dms.fol.bonus.dto.BonusQueryDTO)
    */
    	
    @Override
    public List<BonusQueryDTO> getBonusChangeDeatilWithDealer(BonusQueryDTO bonusQueryDTO) throws ServiceAppException {
        List<BonusQueryDTO> bonusDTOlist;
        try {
            bonusQueryDTO.setSapCode(RSAUtil.decryptByPrivateKey(bonusQueryDTO
                    .getSapCode()));
            // 查询返回结果
            bonusDTOlist = bonusDao.getBonusChangeDeatilWithDealer(bonusQueryDTO);
        } catch (Exception e) {
            logger.error(e);
            throw new ServiceAppException(e);
        }
        return bonusDTOlist;
    }

    /*
    *
    * @author DELL
    * @date 2016年1月21日
    * @param bonusQueryDTO
    * @return
    * @throws SQLException
    * (non-Javadoc)
    * @see com.sgm.dms.fol.bonus.api.BonusService#getBonusFreezeDeatilWithDealer(com.sgm.dms.fol.bonus.dto.BonusQueryDTO)
    */
    	
    @Override
    public List<BonusQueryDTO> getBonusFreezeDeatilWithDealer(BonusQueryDTO bonusQueryDTO) throws ServiceAppException {
        List<BonusQueryDTO> bonusDTOlist;
        try {
            bonusQueryDTO.setSapCode(RSAUtil.decryptByPrivateKey(bonusQueryDTO
                    .getSapCode()));
            // 查询返回结果
            bonusDTOlist = bonusDao.getBonusFreezeDeatilWithDealer(bonusQueryDTO);
        } catch (Exception e) {
            logger.error(e);
            throw new ServiceAppException(e);
        }
        return bonusDTOlist;
    }

    /*
    *
    * @author DELL
    * @date 2016年1月21日
    * @param bonusQueryDTO
    * @return
    * @throws SQLException
    * (non-Javadoc)
    * @see com.sgm.dms.fol.bonus.api.BonusService#getBonusChangeDeatilCountByWhere(com.sgm.dms.fol.bonus.dto.BonusQueryDTO)
    */
    	
    @Override
    public int getBonusChangeDeatilCountByWhere(BonusQueryDTO bonusQueryDTO) throws ServiceAppException {
        try {
            bonusQueryDTO.setSapCode(RSAUtil.decryptByPrivateKey(bonusQueryDTO
                    .getSapCode()));
            // 查询返回结果
            List<BonusQueryDTO> bonusDTOlist = bonusDao.getBonusChangeDeatilWithDealer(bonusQueryDTO);
            return bonusDTOlist.size();
        }  catch (Exception e) {
            logger.error(e);
            throw new ServiceAppException(e);
        }
       
    }

    /*
    *
    * @author DELL
    * @date 2016年1月21日
    * @param bonusQueryDTO
    * @return
    * @throws SQLException
    * (non-Javadoc)
    * @see com.sgm.dms.fol.bonus.api.BonusService#getBonusFreezeDeatilCountByWhere(com.sgm.dms.fol.bonus.dto.BonusQueryDTO)
    */
    	
    @Override
    public int getBonusFreezeDeatilCountByWhere(BonusQueryDTO bonusQueryDTO) throws ServiceAppException {
        try {
            bonusQueryDTO.setSapCode(RSAUtil.decryptByPrivateKey(bonusQueryDTO
                    .getSapCode()));
            // 查询返回结果
            List<BonusQueryDTO> bonusDTOlist = bonusDao.getBonusFreezeDeatilWithDealer(bonusQueryDTO);
            
            return bonusDTOlist.size();
        }catch (Exception e) {
            logger.error(e);
            throw new ServiceAppException(e);
        }
    }

}
