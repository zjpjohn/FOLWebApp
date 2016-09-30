/**
* @ClassName: ServiceImpl
* @Description:  实现类
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

import com.sgm.dms.fol.common.api.domain.PersonRoleDTO;
import com.sgm.dms.fol.common.api.domain.UserDTO;
import com.sgm.dms.fol.common.api.domain.UserSearchDTO;
import com.sgm.dms.fol.common.api.exception.ServiceAppException;
import com.sgm.dms.fol.common.api.exception.ServiceBizException;
import com.sgm.dms.fol.common.api.services.PersonPositionService;
import com.sgm.dms.fol.common.service.repository.PersonRoleDao;
import com.sgm.dms.fol.common.service.utils.CommonUtils;
@Service("PersonRoleService")
public class PersonRoleServiceImpl implements PersonPositionService {
	
	//日志操作
	protected Logger logger = LogManager.getLogger(this.getClass());
	
	//DAO
	@Autowired
	private PersonRoleDao personRoleDao;
	
	@Override
	public long searchUsersCount(UserSearchDTO dto) throws ServiceAppException {
		long count = 0;
		try {
			count = personRoleDao.countUsers(dto);
		} catch(SQLException e) {
        	logger.info(e.getMessage());
			throw new ServiceAppException(e.getLocalizedMessage());
		}
		return count;
	}

	@Override
	public List<UserDTO> searchUsersList(UserSearchDTO dto) throws ServiceAppException {
		List<UserDTO> list = null;
		try {
			list = personRoleDao.listUsers(dto);
		} catch(SQLException e) {
        	logger.info(e.getMessage());
			throw new ServiceAppException(e.getLocalizedMessage());
		}
		return list;
	}

	@Override
	public List<PersonRoleDTO> findA(PersonRoleDTO pRole)
			throws ServiceBizException {
		List<PersonRoleDTO> reserveDtOlist=null;
			try {
				reserveDtOlist= personRoleDao.findA(pRole);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return reserveDtOlist;
	}

	@Override
	public int updataPersonRole(PersonRoleDTO per) {
		int resut=0;
		try {
		     CommonUtils.filterSpecialWord(per);
			 resut=personRoleDao.updataPersonRole(per);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resut;
	}

	
}
