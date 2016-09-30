/**
* @ClassName: UserDao
* @Description: TODO
* @author: ChenChong
* @date: 2015年6月5日 上午5:27:42
* 
* 
*/
package com.sgm.dms.fol.common.service.repository;

import java.sql.SQLException;
import java.util.List;

import com.sgm.dms.fol.common.api.domain.UserDTO;
import com.sgm.dms.fol.common.api.domain.UserPositionDTO;
import com.sgm.dms.fol.common.api.domain.UserSearchDTO;

public interface UserDao {

	public List<UserDTO> listUsers(UserSearchDTO userDto) throws SQLException;
	public long countUsers(UserSearchDTO userDto) throws SQLException;
	
	public int addUser(UserDTO dto) throws SQLException;
	public void insertUserPosition(UserPositionDTO userPositionDTO) throws SQLException;
	public int updateUserPosition(UserPositionDTO userPositionDTO) throws SQLException;
	public int updateUser(UserDTO userAddDTO) throws SQLException;
	public void deteteUserPositionByUserAccount(UserDTO userAddDTO) throws SQLException;
	public int getUserCountByAccount(String account) throws SQLException;
	
	/**
	 * 根据Account获取USER
	 * @param userAccount
	 * @return
	 * @throws SQLException
	 */
	public List<UserSearchDTO> getUserByAccount(UserDTO userdto) throws SQLException;
	
	/**
	 * 根据USER_ID获取USER
	 * @param userdto
	 * @return
	 * @throws SQLException
	 */
	public UserSearchDTO getUserByUserId(UserDTO userdto) throws SQLException;

}
