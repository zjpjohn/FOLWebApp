
package com.sgm.dms.fol.common.service.services.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgm.dms.fol.common.api.constants.POConstant;
import com.sgm.dms.fol.common.api.domain.UserDTO;
import com.sgm.dms.fol.common.api.domain.UserPositionDTO;
import com.sgm.dms.fol.common.api.domain.UserSearchDTO;
import com.sgm.dms.fol.common.api.exception.ServiceAppException;
import com.sgm.dms.fol.common.api.exception.ServiceBizException;
import com.sgm.dms.fol.common.api.services.UserService;
import com.sgm.dms.fol.common.service.repository.CommonDao;
import com.sgm.dms.fol.common.service.repository.UserDao;
import com.sgm.dms.fol.common.service.utils.CommonUtils;


@Service("UserService")
public class UserServiceImpl implements UserService {
	protected Logger logger = LogManager.getLogger(this.getClass());
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private CommonDao commondao;

	@Override
	public long searchUsersCount(UserSearchDTO dto) throws ServiceAppException {
		long count = 0;
		try {
			count = userDao.countUsers(dto);
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
			list = userDao.listUsers(dto);
		} catch(SQLException e) {
        	logger.info(e.getMessage());
			throw new ServiceAppException(e.getLocalizedMessage());
		}
		return list;
	}
	
	
	
	
	
	
	
	
	
	
	
	

	public UserDTO getUser(long userId) throws ServiceAppException {
//		UserDTO dto=null;
//		try {
//			dto = userDao.getUserByAccount(userdto);
//		} catch (SQLException e) {
//			throw new ServiceAppException("");
//		}
//		
//		if(dto == null) {
//			throw new ServiceAppException("");
//		}
//		
//		return dto;
		return null;
	}

	/**
	 * 
	 * @Title: updateUser
	 * @Description: 更新人员信息
	 * @param: 
	 * @return: void
	 * @author: wangyx
	 * @date: 2015�?7�?5�? 上午10:43:32
	 * @throws
	 */
	@Override
	public int updateUser(UserDTO dto) throws ServiceAppException {
		int updNum = 0;
		try {
		    CommonUtils.filterSpecialWord(dto);
			updNum = userDao.updateUser(dto);
			userDao.deteteUserPositionByUserAccount(dto);
			if(dto.getPositions()!=null&&dto.getPositions().length()>0){
				String[] positionIds=dto.getPositions().split(",");
				for(int i=0;i<positionIds.length;i++){
					UserPositionDTO userPositionDTO = new UserPositionDTO();
					userPositionDTO.setOrgId(-1);
					userPositionDTO.setLogonStatus(dto.getUserStatus());
					userPositionDTO.setPositionId(positionIds[i]);
					userPositionDTO.setUserAccount(dto.getUserAccount());
					userPositionDTO.setUserName(dto.getUserName());
					userPositionDTO.setOfficalName("SGM");
					userPositionDTO.setOrgName("SGM");
					userPositionDTO.setCreateBy(dto.getCreateBy());
					userPositionDTO.setUpdateBy(dto.getUpdateBy());
					
					CommonUtils.filterSpecialWord(userPositionDTO);
					if(userDao.updateUserPosition(userPositionDTO)==0) {
						userDao.insertUserPosition(userPositionDTO);
					}
				}
			}

		} catch (Exception e) {
			logger.info(e.getMessage());
			throw new ServiceAppException(e.getMessage());
		}
		return updNum;
	}
	/**
	 * 
	 * @Title: addUser
	 * @Description: 添加人员信息
	 * @param: 
	 * @return: long
	 * @author: wangyx
	 * @date: 2015�?7�?5�? 上午10:43:37
	 * @throws
	 */
	@Override
	public int addUser(UserDTO dto) throws ServiceAppException {
		int addNums=0;
		try {
		    CommonUtils.filterSpecialWord(dto);
			addNums = userDao.addUser(dto);
			if(dto.getPositions()!=null && dto.getPositions().length()>0){
				String[] positionIds=dto.getPositions().split(",");
				for(int i=0;i<positionIds.length;i++){
					UserPositionDTO userPositionDTO = new UserPositionDTO();
					userPositionDTO.setOrgId(-1);
					userPositionDTO.setLogonStatus(dto.getUserStatus());
					userPositionDTO.setPositionId(positionIds[i]);
					userPositionDTO.setUserAccount(dto.getUserAccount());
					userPositionDTO.setUserName(dto.getUserName());
					userPositionDTO.setOfficalName("SGM");
					userPositionDTO.setOrgName("SGM");
					userPositionDTO.setCreateBy(dto.getCreateBy());
					CommonUtils.filterSpecialWord(userPositionDTO);
					userDao.insertUserPosition(userPositionDTO);
				}
			}else{
				UserPositionDTO userPositionDTO = new UserPositionDTO();
				userPositionDTO.setOrgId(-1);
				userPositionDTO.setLogonStatus(1);
				userPositionDTO.setUserAccount(dto.getUserAccount());
				userPositionDTO.setUserName(dto.getUserName());
				userPositionDTO.setOfficalName("SGM");
				userPositionDTO.setOrgName("SGM");
				userPositionDTO.setCreateBy(dto.getCreateBy());
				CommonUtils.filterSpecialWord(userPositionDTO);
				userDao.insertUserPosition(userPositionDTO);
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
			throw new ServiceAppException(e.getMessage());
		}
		return addNums;
	}

	@Override
	public int deleteUser(UserDTO dto) throws ServiceBizException {
		int delNum = 0;
		try {
		    CommonUtils.filterSpecialWord(dto);
			delNum = userDao.updateUser(dto);			
			userDao.deteteUserPositionByUserAccount(dto);
		} catch (Exception e) {
			logger.info(e.getMessage());
			throw new ServiceAppException(e.getMessage());
		}
		return delNum;
	}

	@Override
	public int getUserCountByAccount(String account) throws ServiceBizException {
		int num = 0;
		try {
			num = userDao.getUserCountByAccount(account);
		} catch (SQLException e) {
			logger.info("删除异常 :"+e.getMessage());
			throw new ServiceBizException(e.getMessage());
		}
		return num;
	}
	
//	/**
//	 * 
//	 * @Title: getUserById
//	 * @Description: 获取人员信息
//	 * @param: 
//	 * @return: UserAddDTO
//	 * @author: wangyx
//	 * @date: 2015�?7�?5�? 上午10:43:49
//	 * @throws
//	 */
//	@Override
//	public UserAddDTO getUserById(long id) throws ServiceAppException {
//		try {
//			return userDao.getUserDetailById(id);
//		} catch (Exception e) {
//			logger.info(e.getMessage());
//			throw new ServiceAppException(e.getMessage());
//		}
//	}
	/**
	 * 
	 * @Title: removeUser
	 * @Description: 删除人员信息
	 * @param: 
	 * @return: void
	 * @author: wangyx
	 * @date: 2015�?7�?5�? 上午10:43:40
	 * @throws
	 */
	@Override
	public void removeUser(long id) throws ServiceAppException {
//		try {
//			userDao.detetePositionUserByUserId(id);	
//			userDao.removeUser(id);
//		} catch (Exception e) {
//			logger.info(e.getMessage());
//			throw new ServiceAppException(e.getMessage());
//		}
	}

	@Override
	public UserDTO getUserByUserAccount(String userAccount) throws ServiceAppException {
//		try {
//			UserDTO userdto=userDao.getUserByUserAccount(userAccount);
//			return userdto;
//		} catch (Exception e) {
//			logger.info(e.getMessage());
//			throw new ServiceAppException(e.getMessage());
//		}
		return null;
	}

	@Override
	public UserDTO getUser(String userAccount) throws ServiceBizException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserSearchDTO> getSGMUserByAccount(String userAccount)
			throws ServiceAppException {
		List<UserSearchDTO> dto=null;
		try {
			UserDTO userdto=new UserDTO();
			userdto.setUserAccount(userAccount);
			userdto.setUserType(POConstant.USER_TYPE_SGM);
	        
			dto=userDao.getUserByAccount(userdto);
		} catch (SQLException e) {
			throw(new ServiceAppException(e.getMessage()));
		}
		return dto;
	}

	@Override
	public List<UserSearchDTO> getDealerUserByAccount(String userAccount)
			throws ServiceBizException {
	    List<UserSearchDTO> dto=null;
		try {
			UserDTO userdto=new UserDTO();
			userdto.setUserAccount(userAccount);
			userdto.setUserType(POConstant.USER_TYPE_DEALER);
			
			dto=userDao.getUserByAccount(userdto);
		} catch (SQLException e) {
			throw(new ServiceAppException(e.getMessage()));
		}
		return dto;
	}

	@Override
	public UserSearchDTO getUserByUserId(Long userId)
			throws ServiceBizException {
		UserSearchDTO dto=null;
		try {
			UserDTO userdto=new UserDTO();
			userdto.setUserId(userId);
			
			dto=userDao.getUserByUserId(userdto);
		} catch (SQLException e) {
			throw(new ServiceAppException(e.getMessage()));
		}
		return dto;
	}

    /*
    *
    * @author DELL
    * @date 2016年2月16日
    * @param account
    * @return
    * @throws ServiceAppException
    * (non-Javadoc)
    * @see com.sgm.dms.fol.common.api.services.UserService#checkUserAuthorityByAccount(java.lang.String)
    */
    	
    @Override
    public String getAuthorityUrlByAccount(String account) throws ServiceAppException {
        String urllist="";
        try {
            List<String> urls=commondao.getUserAuthorityUrl(account);
            if(urls!=null&&urls.size()>0){
                for (String url : urls) {
                    urllist+=","+url;
                }
                if(urllist!=null&&!"".equals(urllist)){
                    urllist=urllist.substring(1, urllist.length());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw(new ServiceAppException(e));
        }
        return urllist;
    }

}
