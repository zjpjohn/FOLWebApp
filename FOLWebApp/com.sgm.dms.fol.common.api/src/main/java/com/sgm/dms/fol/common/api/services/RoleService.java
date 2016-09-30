
package com.sgm.dms.fol.common.api.services;

import com.sgm.dms.fol.common.api.domain.RoleDTO;
import com.sgm.dms.fol.common.api.exception.ServiceAppException;

import java.util.List;

public interface RoleService {
	public List<RoleDTO> getRolesByUser(long userId) throws ServiceAppException;
	public Long[] getRoleIdsByUser(long userId) throws ServiceAppException;
	public List<RoleDTO> getRolesByMenu(long menuId) throws ServiceAppException;
	public RoleDTO getRoleById(long id) throws ServiceAppException;
}
