
package com.sgm.dms.fol.common.api.services;


import java.sql.SQLException;
import java.util.List;

import com.sgm.dms.fol.common.api.domain.UserDTO;
import com.sgm.dms.fol.common.api.domain.UserSearchDTO;
import com.sgm.dms.fol.common.api.exception.ServiceAppException;
import com.sgm.dms.fol.common.api.exception.ServiceBizException;

public interface UserService {
	public long searchUsersCount(UserSearchDTO dto) throws ServiceBizException;
	public List<UserDTO> searchUsersList(UserSearchDTO dto) throws ServiceBizException;
	//public List<UserDTO> listUsers(UserSearchDTO userDto) throws ServiceBizException;
	public int updateUser(UserDTO dto) throws ServiceBizException;
	public int deleteUser(UserDTO dto) throws ServiceBizException;
	public int getUserCountByAccount(String account) throws ServiceBizException;
	
	public UserDTO getUser(long userId) throws ServiceBizException;
	
	public UserDTO getUser(String userAccount) throws ServiceBizException;
	
	
	public void removeUser(long id) throws ServiceBizException;
	
	public UserDTO getUserByUserAccount(String userAccount) throws ServiceBizException;
	public int addUser(UserDTO userAddDTO) throws ServiceBizException;
	
	/**
	 * SGM USER拥有全部权限
	 * @param userAccount
	 * @return
	 * @throws SQLException
	 */
	public List<UserSearchDTO> getSGMUserByAccount(String userAccount) throws ServiceBizException;

	/**
	 * DEALER USER拥有部分权限（批发商，经销商）
	 * @param userAccount
	 * @return
	 * @throws SQLException
	 */
	public List<UserSearchDTO> getDealerUserByAccount(String userAccount) throws ServiceBizException;

	/**
	 * 根据USER_ID获取USER
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	public UserSearchDTO getUserByUserId(Long userId) throws ServiceBizException;
	
	/**
	 * 根据SSO_ACCOUNT检查用户是否有权限
	 */
	public String getAuthorityUrlByAccount(String account) throws ServiceAppException;
}
