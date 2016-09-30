/**
* @ClassName: ReserveAmountService
* @Description:角色 INTERFACE
* @author: lu hui
* @date: 2015年10月9日 上午10:10:43
* 
* 
*/
package com.sgm.dms.fol.common.api.services;

import java.util.List;

import com.sgm.dms.fol.common.api.domain.AuthorityDTO;
import com.sgm.dms.fol.common.api.domain.RoleMenuDTO;
import com.sgm.dms.fol.common.api.exception.ServiceBizException;

public interface AuthorityService {
/**
 * @ClassName: ReserveAmountService
 * @Description: 角色接口服务
 * @author: lu hui
 * @date: 2015年10月9日 下午 14:33
 * 
 */
	//查询角色列表-不分页
	public List<AuthorityDTO> getAuthoritymounts(AuthorityDTO authorityDTO) throws ServiceBizException;
	//查询角色列表-分页
	public List<AuthorityDTO> getAuthorityList(AuthorityDTO authorityDTO) throws ServiceBizException;
	//查询角色列表数量-分页
	public long getAuthorityCount(AuthorityDTO authorityDTO) throws ServiceBizException;
	//查询角色列表-不分页-根据Type和PositionId
	public List<AuthorityDTO> getAuthorityByTypePosition(AuthorityDTO authorityDTO) throws ServiceBizException;
	
	//修改角色
	public int updatarole(AuthorityDTO authorityDTO) throws ServiceBizException;
	//根据角色查询权限
	public List<RoleMenuDTO> findRoleMenu(RoleMenuDTO roleM) throws ServiceBizException;
	//查询所有权限
	public List<RoleMenuDTO> findMenuByMenuId(RoleMenuDTO roleM) throws ServiceBizException;
	//查询所有权限-By menuType
	public List<RoleMenuDTO> findMenuByMenuType(long menuType) throws ServiceBizException;
	//查询所有权限-By menuType RoleId
	public List<RoleMenuDTO> findMenuByMenuTypeRoleId(long menuType, long roleId) throws ServiceBizException;
	//修改角色(权限ADD)
	public int updateAuthorityADD(RoleMenuDTO roleM) throws ServiceBizException;
	//修改角色(权限DEL)
	public int updateAuthorityDel(RoleMenuDTO roleM) throws ServiceBizException;
	//添加角色 
	public int addAuthority(AuthorityDTO authorityDTO) throws ServiceBizException;
	//修改角色 
	public int updateAuthority(AuthorityDTO authorityDTO) throws ServiceBizException;
	public int updateAuthorityMethod(AuthorityDTO authorityDTO) throws ServiceBizException;
	//删除角色 
	public int delAuthority(AuthorityDTO authorityDTO) throws ServiceBizException;
	public boolean checkRoleMenu(long roleId, Integer menuId);
	
	public int getAuthorityCountByName(String name) throws ServiceBizException;
	
	
			
}
