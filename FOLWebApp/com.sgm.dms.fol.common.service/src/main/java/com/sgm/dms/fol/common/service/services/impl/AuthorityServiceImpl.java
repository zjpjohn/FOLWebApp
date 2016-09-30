/**
* @ClassName: AmountServiceImpl
* @Description: 角色权限 实现类
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
import org.springframework.transaction.annotation.Transactional;

import com.sgm.dms.fol.common.api.domain.AuthorityDTO;
import com.sgm.dms.fol.common.api.domain.RoleMenuDTO;
import com.sgm.dms.fol.common.api.exception.ServiceBizException;
import com.sgm.dms.fol.common.api.services.AuthorityService;
import com.sgm.dms.fol.common.service.repository.AuthorityDao;
import com.sgm.dms.fol.common.service.utils.CommonUtils;
@Service("AuthorityService")
@Transactional(rollbackFor=ServiceBizException.class)
public class AuthorityServiceImpl implements AuthorityService {
	
	//日志操作
	protected Logger logger = LogManager.getLogger(this.getClass());
	
	//角色DAO 
	@Autowired
	private AuthorityDao authorityDao;

	@Override
	public List<AuthorityDTO> getAuthoritymounts(AuthorityDTO authorityDTO) throws ServiceBizException {
		List<AuthorityDTO> reserveDtOlist=null;
		try {
			//查询返回结果 
		    reserveDtOlist=authorityDao.getAuthorityByWhere(authorityDTO);
		} catch (SQLException e) {
			logger.info("查询信息异常 :"+e.getMessage());
			throw new ServiceBizException(e.getMessage());
		}
		
		return reserveDtOlist;
	}

	@Override
	public List<RoleMenuDTO> findMenuByMenuType(long menuType) throws ServiceBizException {
		List<RoleMenuDTO> dtoList =null;
		try {
			dtoList = authorityDao.findMenuByMenuType(menuType);
		} catch (SQLException se) {
			logger.info("查询信息异常 :"+se.getMessage());
			throw new ServiceBizException(se.getMessage());
		}
		return dtoList;
	}

	@Override
	public List<RoleMenuDTO> findMenuByMenuTypeRoleId(long menuType, long roleId) throws ServiceBizException {
		List<RoleMenuDTO> dtoList = null;
		try {
			dtoList = authorityDao.findMenuByMenuTypeRoleId(menuType, roleId);
		} catch (SQLException se) {
			logger.info("查询信息异常 :"+se.getMessage());
			throw new ServiceBizException(se.getMessage());
		}
		return dtoList;
	}

	@Override
	public List<AuthorityDTO> getAuthorityList(AuthorityDTO authorityDTO) throws ServiceBizException {
		List<AuthorityDTO> reserveDtOlist=null;
		try {
			//查询返回结果 
		    reserveDtOlist=authorityDao.getAuthorityList(authorityDTO);
		} catch (SQLException e) {
			logger.info("查询信息异常 :"+e.getMessage());
			throw new ServiceBizException(e.getMessage());
		}
		
		return reserveDtOlist;
	}

	@Override
	public long getAuthorityCount(AuthorityDTO authorityDTO) throws ServiceBizException {
		long count = 0;
		try {
			//查询返回结果 
			count=authorityDao.getAuthorityCount(authorityDTO);
		} catch (SQLException e) {
			logger.info("查询信息异常 :"+e.getMessage());
			throw new ServiceBizException(e.getMessage());
		}
		
		return count;
	}


	@Override
	public List<AuthorityDTO> getAuthorityByTypePosition(AuthorityDTO authorityDTO) throws ServiceBizException {
		List<AuthorityDTO> list=null;
		try {
			//查询返回结果 
			list=authorityDao.getAuthorityListByTypePosition(authorityDTO);
		} catch (SQLException e) {
			logger.info("查询信息异常 :"+e.getMessage());
			throw new ServiceBizException(e.getMessage());
		}
		
		return list;
	}

	@Override
	public int addAuthority(AuthorityDTO authorityDTO) throws ServiceBizException {
		int ad=0;
		try {
			//查询返回结果 
		    CommonUtils.filterSpecialWord(authorityDTO);
			ad=authorityDao.addAuthority(authorityDTO);
			String[] menuIds=null;
			if(authorityDTO.getMenuId()!=null && authorityDTO.getMenuId().length()>0) {
				menuIds = authorityDTO.getMenuId().split(",");
			}
			if (null != menuIds && 0 < menuIds.length) {
				for (String menuId : menuIds) {
					RoleMenuDTO dto = new RoleMenuDTO();
					dto.setRoleId(authorityDTO.getRoleId());
					dto.setMenuId(Integer.parseInt(menuId));
					dto.setCreateBy(authorityDTO.getCreateBy());
					

		            CommonUtils.filterSpecialWord(dto);
					authorityDao.addRoleMenu(dto);
				}
			}
		} catch (SQLException e) {
			logger.info("添加信息异常 :"+e.getMessage());
			throw new ServiceBizException(e.getMessage());
		}
		return ad;
	}

	@Override
	public int updateAuthority(AuthorityDTO authorityDTO) throws ServiceBizException {
		int upd=0;
		try {
			//修改角色资料
		    CommonUtils.filterSpecialWord(authorityDTO);
			upd=authorityDao.updateAuthority(authorityDTO);
			authorityDao.deteteMenuRoleByRoleId(authorityDTO.getRoleId());
			String[] menuIds=null;
			if(authorityDTO.getMenuId()!=null && authorityDTO.getMenuId().length()>0) {
				menuIds = authorityDTO.getMenuId().split(",");
			}
			if (null != menuIds && 0 < menuIds.length) {
				for (String menuId : menuIds) {
					RoleMenuDTO dto = new RoleMenuDTO();
					dto.setRoleId(authorityDTO.getRoleId());
					dto.setMenuId(Integer.parseInt(menuId));
					dto.setCreateBy(authorityDTO.getCreateBy());
					dto.setUpdateBy(authorityDTO.getUpdateBy());
					
					CommonUtils.filterSpecialWord(dto);
					authorityDao.addRoleMenu(dto);
				}
			}
		} catch (SQLException e) {
			logger.info("添加信息异常 :"+e.getMessage());
			throw new ServiceBizException(e.getMessage());
		}
		return upd;
	}
	@Override
	public int updateAuthorityMethod(AuthorityDTO authorityDTO) throws ServiceBizException {
	    int upd=0;
	    try {
	        //修改角色资料
	        CommonUtils.filterSpecialWord(authorityDTO);
	        upd=authorityDao.updateAuthority(authorityDTO);
	        authorityDao.deteteAuthorMethodByRoleId(authorityDTO.getRoleId());
	        String[] methodIds=null;
	        if(authorityDTO.getMethodId() !=null && authorityDTO.getMethodId().length()>0) {
	            methodIds = authorityDTO.getMethodId().split(",");
	        }
	        if (null != methodIds && 0 < methodIds.length) {
	            for (String methodId : methodIds) {
	                RoleMenuDTO dto = new RoleMenuDTO();
	                dto.setRoleId(authorityDTO.getRoleId());
	                dto.setMethodId(Integer.parseInt(methodId));
	                dto.setCreateBy(authorityDTO.getCreateBy());
	                dto.setUpdateBy(authorityDTO.getUpdateBy());
	                
	                CommonUtils.filterSpecialWord(dto);
	                authorityDao.addAuthorMethod(dto);
	            }
	        }
	    } catch (SQLException e) {
	        logger.info("添加信息异常 :"+e.getMessage());
	        throw new ServiceBizException(e.getMessage());
	    }
	    return upd;
	}

	@Override
	public int delAuthority(AuthorityDTO authorityDTO) throws ServiceBizException {
		int de=0;
		try {
			//删除返回结果 
		    CommonUtils.filterSpecialWord(authorityDTO);
			de=authorityDao.updateAuthority(authorityDTO);
			authorityDao.deteteMenuRoleByRoleId(authorityDTO.getRoleId());
		} catch (SQLException e) {
			logger.info("删除异常 :"+e.getMessage());
			throw new ServiceBizException(e.getMessage());
		}
		return de;
	}

	@Override
	public int getAuthorityCountByName(String name) throws ServiceBizException {
		int num = 0;
		try {
			num = authorityDao.getAuthorityCountByName(name);
		} catch (SQLException e) {
			logger.info("删除异常 :"+e.getMessage());
			throw new ServiceBizException(e.getMessage());
		}
		return num;
	}


	@Override
	public int updateAuthorityADD(RoleMenuDTO roleM)
			throws ServiceBizException {
		try {
		    CommonUtils.filterSpecialWord(roleM);
			return authorityDao.updateAuthorityADD(roleM);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}


	@Override
	public int updateAuthorityDel(RoleMenuDTO roleM)
			throws ServiceBizException {
		try {
		    CommonUtils.filterSpecialWord(roleM);
			int resultcount=authorityDao.updateAuthorityDel(roleM);
			return resultcount;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}


	@Override
	public List<RoleMenuDTO> findRoleMenu(RoleMenuDTO roleM)
			throws ServiceBizException {
		
		return  authorityDao.findRoleMenu(roleM);
	}


	@Override
	public List<RoleMenuDTO> findMenuByMenuId(RoleMenuDTO roleM)
			throws ServiceBizException {
		return  authorityDao.findMenuByMenuId(roleM);
	}


	@Override
	public int updatarole(AuthorityDTO role)
			throws ServiceBizException {
		try {
		     CommonUtils.filterSpecialWord(role);
			 return authorityDao.updatarole(role);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}


	@Override
	public boolean checkRoleMenu(long roleId, Integer menuId) {
		// TODO Auto-generated method stub
		RoleMenuDTO result= authorityDao.checkRoleMenu(roleId,menuId);
		if(null!=result){
			return true;
		}
		return false;
	}


}
