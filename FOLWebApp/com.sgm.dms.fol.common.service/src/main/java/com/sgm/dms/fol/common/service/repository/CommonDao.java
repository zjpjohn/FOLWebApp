
package com.sgm.dms.fol.common.service.repository;

import com.sgm.dms.fol.common.api.domain.RoleDTO;
import com.sgm.dms.fol.common.service.domains.MenuRolePo;
import com.sgm.dms.fol.common.service.domains.RolePo;
import com.sgm.dms.fol.common.service.domains.UserPo;

import java.sql.SQLException;
import java.util.List;

public interface CommonDao {
	public UserPo getUserById(long userId)throws SQLException;
	public UserPo getUserByAccount(String userAccount)throws SQLException;
	public List<RolePo> getRolesByUserId(long userId)throws SQLException;
	public List<RolePo> getRolesByMenu(long menuId)throws SQLException;
	public List<MenuRolePo> getMenuRoles(String userId, String positionId,Integer type)throws SQLException;
	public RoleDTO getRoleById(long id) throws SQLException;
	public List<MenuRolePo> getMenuRolesByType(long type)throws SQLException;

	public List<String> getUserAuthorityUrl(String userAccount) throws SQLException;
}
