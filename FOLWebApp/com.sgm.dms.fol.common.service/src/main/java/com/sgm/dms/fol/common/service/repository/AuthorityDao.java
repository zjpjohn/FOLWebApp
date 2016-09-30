/**
* @ClassName: reserveAmountDao
* @Description: 角色权限 DAO
* @author:LuHui
* @date: 2015年10月19日 上午10:43:42
* 
* 
*/
package com.sgm.dms.fol.common.service.repository;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sgm.dms.fol.common.api.domain.AuthorityDTO;
import com.sgm.dms.fol.common.api.domain.RoleMenuDTO;




public interface AuthorityDao {
	
	//根据条件查询角色权限列表-不分页
	public List<AuthorityDTO> getAuthorityByWhere(AuthorityDTO authorityDTO) throws SQLException;
	
	//根据条件查询角色权限列表-分页
	public List<AuthorityDTO> getAuthorityList(AuthorityDTO authorityDTO) throws SQLException;
	
	//根据条件查询角色权限数量-分页
	public long getAuthorityCount(AuthorityDTO authorityDTO) throws SQLException;
	
	//根据Type和Position查询权限列表及被选择的方式
	public List<AuthorityDTO> getAuthorityListByTypePosition(AuthorityDTO authorityDTO) throws SQLException;
	
	//修改角色权限
	public int updatarole(AuthorityDTO role) throws SQLException;
		
	//根据角色查询权限
	public List<RoleMenuDTO> findRoleMenu(RoleMenuDTO roleM);
	
	//根据查询所有权限
	public List<RoleMenuDTO> findMenuByMenuId(RoleMenuDTO roleM);
	
	//根据菜单类型查询菜单
	public List<RoleMenuDTO> findMenuByMenuType(long menuType) throws SQLException;
	
	//根据菜单类型查询菜单
	public List<RoleMenuDTO> findMenuByMenuTypeRoleId(@Param("menuType") long menuType, @Param("roleId") long roleId) throws SQLException;
	
	//修改角色权限(ADD)
	public int addRoleMenu(RoleMenuDTO roleM) throws SQLException;
	public int addAuthorMethod(RoleMenuDTO roleM) throws SQLException;

	//修改角色权限(ADD)
	public int updateAuthorityADD(RoleMenuDTO roleM) throws SQLException;
	
	//修改角色权限(DEL)
	public int updateAuthorityDel(RoleMenuDTO roleM) throws SQLException;

	//添加角色权限
	public int addAuthority(AuthorityDTO authorityDTO) throws SQLException;
	//修改角色权限
	public int updateAuthority(AuthorityDTO authorityDTO) throws SQLException;
	//删除角色权限
	public int delAuthority(AuthorityDTO authorityDTO) throws SQLException;
	//根据roleId删除角色菜单对应关系
	public int deteteMenuRoleByRoleId(long roleId) throws SQLException;
	public int deteteAuthorMethodByRoleId(long roleId) throws SQLException;

	public RoleMenuDTO checkRoleMenu(long roleId, Integer menuId);
	
	public int getAuthorityCountByName(String name) throws SQLException;

}
