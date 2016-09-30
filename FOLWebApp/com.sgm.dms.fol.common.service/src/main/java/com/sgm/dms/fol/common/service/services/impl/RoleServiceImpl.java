
package com.sgm.dms.fol.common.service.services.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sgm.dms.fol.common.api.domain.RoleDTO;
import com.sgm.dms.fol.common.api.exception.ServiceAppException;
import com.sgm.dms.fol.common.api.services.RoleService;
import com.sgm.dms.fol.common.service.domains.RolePo;
import com.sgm.dms.fol.common.service.repository.CommonDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {

	private Logger logger=Logger.getLogger(RoleServiceImpl.class);
	@Autowired
	CommonDao roleDao;
	
	public List<RoleDTO> getRolesByUser(long userId) throws ServiceAppException {
		List<RolePo> domainRoles = null;
		try{
			domainRoles = roleDao.getRolesByUserId(userId);
		}catch(SQLException e){
			throw new ServiceAppException("error");
		}
		if(domainRoles == null || domainRoles.size() < 1) {
			//用户为设置职位�?�角色，视为业务异常
			throw new ServiceAppException("User without position or role!");
		}
		
		List<RoleDTO> dtoRoles = new ArrayList<RoleDTO>();
		for(RolePo role : domainRoles) {
			RoleDTO dtoRole = new RoleDTO();
			dtoRole.setRoleId(role.getRoleId());
			dtoRole.setRoleName(role.getRoleName());
			dtoRoles.add(dtoRole);
		}
		
		return dtoRoles;
	}

	public List<RoleDTO> getRolesByMenu(long menuId) throws ServiceAppException {
		List<RolePo> domainRoles=null;
		try {
			domainRoles = roleDao.getRolesByMenu(menuId);
		} catch (SQLException e) {
			throw new ServiceAppException("error");
		}
		
		if(domainRoles != null && domainRoles.size() > 0) {
			List<RoleDTO> dtoRoles = new ArrayList<RoleDTO>();
			for(RolePo role : domainRoles) {
				RoleDTO dtoRole = new RoleDTO();
				dtoRole.setRoleId(role.getRoleId());
				dtoRole.setRoleName(role.getRoleName());
				dtoRoles.add(dtoRole);
			}
			return dtoRoles;
		}
		
		return null;
	}

	public Long[] getRoleIdsByUser(long userId) throws ServiceAppException {
		List<RolePo> domainRoles = null;
		try{
			domainRoles = roleDao.getRolesByUserId(userId);
		}catch(SQLException e){
			throw new ServiceAppException("");
		}
		if(domainRoles == null || domainRoles.size() < 1) {
			return null;
		}
		
		Long[] result = new Long[domainRoles.size()];
		for(int i = 0; i < result.length; i++) {
			result[i] = domainRoles.get(i).getRoleId();
		}
		
		return result;
	}
	
	/**
	 * 
	 * @Title: listRoles
	 * @Description: 查询角色列表
	 * @param: 
	 * @return: List<RoleSearchPo>
	 * @author: wangyx
	 * @date: 2015�?7�?5�? 上午10:41:41
	 * @throws
	 
	public PagePo<RoleSearchPo> listRoles(RoleSearchDTO roleSearchDTO) throws BizException {
		long count;
		try {
			count = roleDao.countRoles(roleSearchDTO);
			List<RoleSearchPo>roleSearchPos = roleDao.listRoles(roleSearchDTO);
			PagePo<RoleSearchPo> pagePo=new PagePo<RoleSearchPo>();
			pagePo.setCount(count);
			pagePo.setList(roleSearchPos);
			return pagePo;
		} catch (Exception e) {
			logger.info(e.getMessage());
			throw new BizException(CodeConstant.DB_ERROR, e.getMessage(), e.getCause());
		}
	}*/
	/**
	 * 
	 * @Title: addRole
	 * @Description: 添加角色
	 * @param: 
	 * @return: long
	 * @author: wangyx
	 * @date: 2015�?7�?5�? 上午10:41:46
	 * @throws
	 
	public void addRole(RoleDetailDTO roleDTO) throws BizException {
		try {
			roleDao.addRole(roleDTO);
			if(roleDTO.getMenus()!=null&&roleDTO.getMenus().length()>0){
				String[] roleIds=roleDTO.getMenus().split(",");
				for(int i=0;i<roleIds.length;i++){
					RoleMenuDTO roleMenuDTO = new RoleMenuDTO();
					roleMenuDTO.setCreateDate(new Date());
					roleMenuDTO.setMenuId(Long.parseLong(roleIds[i]));
					roleMenuDTO.setStatus(roleDTO.getRoleStatus());
					roleMenuDTO.setRoleId(roleDTO.getRoleId());
					roleMenuDTO.setCreateBy(roleDTO.getCreateBy());
					roleDao.insertMenuRole(roleMenuDTO);
				}
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
			throw new BizException(CodeConstant.DB_ERROR, e.getMessage(), e.getCause());
		}
	}*/
	/**
	 * 
	 * @Title: getRoleById
	 * @Description: 根据id角色
	 * @param: 
	 * @return: RoleDTO
	 * @author: wangyx
	 * @date: 2015�?7�?5�? 上午10:41:50
	 * @throws
	 */
	public RoleDTO getRoleById(long id) throws ServiceAppException {
		try {
			return roleDao.getRoleById(id);
		} catch (Exception e) {
			logger.info(e.getMessage());
			throw new ServiceAppException("");
		}
	}
	/**
	 * 
	 * @Title: removeRole
	 * @Description: 删除角色
	 * @param: 
	 * @return: void
	 * @author: wangyx
	 * @date: 2015�?7�?5�? 上午10:41:57
	 * @throws
	 
	public void removeRole(long id) throws BizException {
		try {
			roleDao.removeRole(id);
			roleDao.deteteMenuRoleByRoleId(id);
		} catch (Exception e) {
			logger.info(e.getMessage());
			throw new BizException(CodeConstant.DB_ERROR, e.getMessage(), e.getCause());
		}
	}*/
	/**
	 * 
	 * @Title: updateRole
	 * @Description: 更新角色
	 * @param: 
	 * @return: void
	 * @author: wangyx
	 * @date: 2015�?7�?5�? 上午10:42:02
	 * @throws
	 
	public void updateRole(RoleDetailDTO roleDTO) throws BizException {
		try {
			roleDao.updateRole(roleDTO);
			roleDao.deteteMenuRoleByRoleId(roleDTO.getRoleId());
			if(roleDTO.getMenus()!=null&&roleDTO.getMenus().length()>0){
				String[] roleIds=roleDTO.getMenus().split(",");
				for(int i=0;i<roleIds.length;i++){
					RoleMenuDTO roleMenuDTO = new RoleMenuDTO();
					roleMenuDTO.setCreateDate(new Date());
					roleMenuDTO.setMenuId(Long.parseLong(roleIds[i]));
					roleMenuDTO.setStatus(roleDTO.getRoleStatus());
					roleMenuDTO.setRoleId(roleDTO.getRoleId());
					roleMenuDTO.setCreateBy(roleDTO.getCreateBy());
					roleDao.insertMenuRole(roleMenuDTO);
				}
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
			throw new BizException(CodeConstant.DB_ERROR, e.getMessage(), e.getCause());
		}

	}*/
	/**
	 * 
	 * @Title: getRoleId
	 * @Description: 获取角色
	 * @param: 
	 * @return: long
	 * @author: wangyx
	 * @date: 2015�?7�?5�? 上午10:43:03
	 * @throws
	 
	public long getRoleId() throws BizException {
		try {
			return roleDao.getRoleId();
		} catch (Exception e) {
			logger.info(e.getMessage());
			throw new BizException(CodeConstant.DB_ERROR, e.getMessage(), e.getCause());
		}
	}*/
	/**
	 * 
	 * @Title: listRolesByType
	 * @Description: 获取角色列表根据类新
	 * @param: 
	 * @return: List<RoleSearchPo>
	 * @author: wangyx
	 * @date: 2015�?7�?5�? 上午10:43:07
	 * @throws
	 
	public List<RoleSearchPo> listRolesByType(String type) throws BizException {
		try {
			return roleDao.listRolesByType(type);
		} catch (Exception e) {
			logger.info(e.getMessage());
			throw new BizException(CodeConstant.DB_ERROR, e.getMessage(), e.getCause());
		}
	}*/
	/**
	 * 
	 * @Title: getPositionRoleByRoleId
	 * @Description: 获取各位角色关系
	 * @param: 
	 * @return: List<PositionRoleDTO>
	 * @author: wangyx
	 * @date: 2015�?7�?5�? 上午10:43:11
	 * @throws
	 
	public List<PositionRoleDTO> getPositionRoleByRoleId(long id) throws BizException {
		try {
			return roleDao.getPositionRoleByRoleId(id);
		} catch (Exception e) {
			logger.info(e.getMessage());
			throw new BizException(CodeConstant.DB_ERROR, e.getMessage(), e.getCause());
		}
	}*/
}
