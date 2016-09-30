/**
* @ClassName: reserveAmountDao
* @Description: 岗位DAO
* @author: LuHui
* @date: 2015年10月19日 上午10:43:42
* 
* 
*/
package com.sgm.dms.fol.common.service.repository;

import java.sql.SQLException;
import java.util.List;

import com.sgm.dms.fol.common.api.domain.PersonRoleDTO;
import com.sgm.dms.fol.common.api.domain.UserDTO;
import com.sgm.dms.fol.common.api.domain.UserSearchDTO;




public interface PersonRoleDao {
	
	//查询角色
	public List<PersonRoleDTO> findA(PersonRoleDTO pRole) throws SQLException;
	//修改角色名称
	public int updataPersonRole(PersonRoleDTO per)throws SQLException;
	
	public long countUsers(UserSearchDTO userDto) throws SQLException;
	public List<UserDTO> listUsers(UserSearchDTO userDto) throws SQLException;
}

