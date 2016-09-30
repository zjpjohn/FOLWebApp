
package com.sgm.dms.fol.common.api.services;

import java.util.List;

import com.sgm.dms.fol.common.api.domain.MenuDTO;
import com.sgm.dms.fol.common.api.domain.MenuTreeDTo;
import com.sgm.dms.fol.common.api.exception.ServiceAppException;


public interface MenuService {
	public List<MenuDTO> getActiveMenus(long userId, String positionId) throws ServiceAppException;

	public List<MenuTreeDTo> getMenusTree(long type)throws ServiceAppException;
}