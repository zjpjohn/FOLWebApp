/**
* @ClassName: AmountServiceImpl
* @Description:岗位  实现类
* @author:  LuHui
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

import com.sgm.dms.fol.common.api.domain.PositionDTO;
import com.sgm.dms.fol.common.api.domain.PositionRoleDTO;
import com.sgm.dms.fol.common.api.domain.PositionSwitchDTO;
import com.sgm.dms.fol.common.api.domain.UserDTO;
import com.sgm.dms.fol.common.api.exception.ServiceBizException;
import com.sgm.dms.fol.common.api.services.PositionService;
import com.sgm.dms.fol.common.service.repository.PositionDao;
import com.sgm.dms.fol.common.service.utils.CommonUtils;
@Service("PositionService")
public class PositionServiceImpl implements PositionService {
	
	//日志操作
	protected Logger logger = LogManager.getLogger(this.getClass());
	
	//岗位DAO
	@Autowired
	private PositionDao positionDao;

	@Override
	public List<PositionDTO> getPositions(PositionDTO positionDTO) throws ServiceBizException {
		
		List<PositionDTO> reserveDtOlist=null;
		try {
			//查询返回结果 
		    reserveDtOlist=positionDao.getPositionByWhere(positionDTO);
		} catch (SQLException e) {
			
			logger.info("查询信息异常 :"+e.getMessage());
			throw new ServiceBizException(e.getMessage());
		}
		return reserveDtOlist; 
	}

	@Override
	public List<PositionDTO> getPositionList(PositionDTO positionDTO) throws ServiceBizException {
		List<PositionDTO> dtolist=null;
		try {
			//查询返回结果 
			dtolist=positionDao.getPositionList(positionDTO);
		} catch (SQLException e) {
			logger.info("查询信息异常 :"+e.getMessage());
			throw new ServiceBizException(e.getMessage());
		}
		
		return dtolist;
	}

	@Override
	public long getPositionCount(PositionDTO positionDTO) throws ServiceBizException {
		long count = 0;
		try {
			//查询返回结果 
			count=positionDao.getPositionCount(positionDTO);
		} catch (SQLException e) {
			logger.info("查询信息异常 :"+e.getMessage());
			throw new ServiceBizException(e.getMessage());
		}
		
		return count;
	}

	@Override
	public int addPosition(PositionDTO positionDTO) throws ServiceBizException {
		int addNums=0;
		try {
			//新增
		    CommonUtils.filterSpecialWord(positionDTO);
			addNums=positionDao.addPosition(positionDTO);
			String[] roles=null;
			if(positionDTO.getRoles()!=null && positionDTO.getRoles().length()>0) {
				roles = positionDTO.getRoles().split(",");
			}
			if (null != roles && 0 < roles.length) {
				for (String roleId : roles) {
					PositionRoleDTO dto = new PositionRoleDTO();
					dto.setPositionId(positionDTO.getPositionId());
					dto.setRoleId(Long.valueOf(roleId));
					dto.setCreateBy(positionDTO.getCreateBy());
					CommonUtils.filterSpecialWord(dto);
					positionDao.addPositionRole(dto);
				}
			}
		} catch (SQLException e) {
			logger.info("添加信息异常 :"+e.getMessage());
			throw new ServiceBizException(e.getMessage());
		}
		return addNums;
	}

	@Override
	public int updatePosition(PositionDTO positionDTO) throws ServiceBizException {
		int updNums=0;
		try {
			//修改
		    CommonUtils.filterSpecialWord(positionDTO);
			updNums=positionDao.updatePosition(positionDTO);
			positionDao.deteteRoleByPositionId(positionDTO.getPositionId());
			String[] roles=null;
			if(positionDTO.getRoles()!=null && positionDTO.getRoles().length()>0) {
				roles = positionDTO.getRoles().split(",");
			}
			if (null != roles && 0 < roles.length) {
				for (String roleId : roles) {
					PositionRoleDTO dto = new PositionRoleDTO();
					dto.setPositionId(positionDTO.getPositionId());
					dto.setRoleId(Long.valueOf(roleId));
					dto.setCreateBy(positionDTO.getCreateBy());
					CommonUtils.filterSpecialWord(dto);
					positionDao.addPositionRole(dto);
				}
			}
		} catch (SQLException e) {
			logger.info("添加信息异常 :"+e.getMessage());
			throw new ServiceBizException(e.getMessage());
		}
		return updNums;
	}

	@Override
	public int deletePosition(PositionDTO positionDTO) throws ServiceBizException {
		int updNums=0;
		try {
		    positionDTO.setStatus(2L);
		    CommonUtils.filterSpecialWord(positionDTO);
			updNums=positionDao.updatePosition(positionDTO);
			positionDao.deteteRoleByPositionId(positionDTO.getPositionId());
		} catch (SQLException e) {
			logger.info("添加信息异常 :"+e.getMessage());
			throw new ServiceBizException(e.getMessage());
		}
		return updNums;
	}

	@Override
	public List<PositionDTO> getPositionsChecked(int type, String userAccount) throws ServiceBizException {
		List<PositionDTO> dtolist=null;
		try {
			//查询返回结果 
			dtolist=positionDao.getPositionsChecked(type, userAccount);
		} catch (SQLException e) {
			logger.info("查询信息异常 :"+e.getMessage());
			throw new ServiceBizException(e.getMessage());
		}
		
		return dtolist;
	}

	@Override
	public int getPositionCountByName(String name) throws SQLException {
		int num = 0;
		try {
			num = positionDao.getPositionCountByName(name);
		} catch (SQLException e) {
			logger.info("删除异常 :"+e.getMessage());
			throw new ServiceBizException(e.getMessage());
		}
		return num;
	}


	


	


	@Override
	public List<PositionDTO> getPositionsByUserId(UserDTO userDTO)
			throws  SQLException {
		return positionDao.getPositionsByUserId(userDTO);
	}
	
	@Override
	public boolean checkRoleMenu(long roleId, String menuId) {
		PositionRoleDTO result= positionDao.checkRoleMenu(roleId,menuId);
		if(null!=result){
			return true;
		}
		return false;
	}


	@Override
	public List<PositionRoleDTO> findA(PositionRoleDTO pRole)
			throws ServiceBizException {
		List<PositionRoleDTO> reserveDtOlist=null;
		try {
			//查询返回结果 
		    reserveDtOlist=positionDao.findAu(pRole);
		} catch (SQLException e) {
			
			logger.info("查询信息异常 :"+e.getMessage());
			throw new ServiceBizException(e.getMessage());
		}
		return reserveDtOlist;
	}


	@Override
	public int updateAuthorityAdd(PositionRoleDTO pRole)
			throws ServiceBizException {
		
		return positionDao.updateAuthorityAdd(pRole);
	}


	@Override
	public int updateAuthorityDel(PositionRoleDTO pRole)
			throws ServiceBizException {
		return positionDao.updateAuthorityDel(pRole);
	}

	@Override
	public List<PositionSwitchDTO> getSystemmounts(PositionSwitchDTO positionSwitchDto)
			throws ServiceBizException {
		
		List<PositionSwitchDTO> reserveDtOlist=null;
		try {
			//查询返回结果 
		    reserveDtOlist=positionDao.getSystemmounts(positionSwitchDto);
		} catch (SQLException e) {
			logger.info("查询信息异常 :"+e.getMessage());
			throw new ServiceBizException(e.getMessage());
		}
		return reserveDtOlist; 
	}

    @Override
    public List<PositionDTO> getPositionsByUserAccount(UserDTO userDTO) throws ServiceBizException {
        List<PositionDTO> positions=null;
        try {
            positions=positionDao.getPositionsByUserAccount(userDTO);
        } catch (SQLException e) {
            logger.info("查询信息异常 :"+e.getMessage());
            throw new ServiceBizException(e.getMessage());
        }
        return positions;
    }

	@Override
	public List<PositionSwitchDTO> getPositionSwitch(
			PositionSwitchDTO positionSwitchDto) throws ServiceBizException {
		
		List<PositionSwitchDTO> reserveDtOlist=null;
		try {
			//查询返回结果 
		    reserveDtOlist=positionDao.getPositionSwitch(positionSwitchDto);
		} catch (SQLException e) {
			logger.info("查询信息异常 :"+e.getMessage());
			throw new ServiceBizException(e.getMessage());
		}
		return reserveDtOlist; 
	}

}
