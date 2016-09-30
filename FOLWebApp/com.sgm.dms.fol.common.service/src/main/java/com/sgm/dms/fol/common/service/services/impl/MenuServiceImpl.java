
package com.sgm.dms.fol.common.service.services.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.directory.SearchControls;

import com.sgm.dms.fol.common.api.domain.MenuDTO;
import com.sgm.dms.fol.common.api.domain.MenuTreeDTo;
import com.sgm.dms.fol.common.api.domain.UserDTO;
import com.sgm.dms.fol.common.api.domain.UserSearchDTO;
import com.sgm.dms.fol.common.api.exception.ServiceAppException;
import com.sgm.dms.fol.common.api.services.MenuService;
import com.sgm.dms.fol.common.api.services.UserService;
import com.sgm.dms.fol.common.service.domains.MenuRolePo;
import com.sgm.dms.fol.common.service.repository.CommonDao;
import com.sgm.dms.fol.common.service.repository.UserDao;
import com.sgm.dms.fol.common.service.utils.CookieUtil;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("menuService")
public class MenuServiceImpl implements MenuService {
	protected Logger logger=LogManager.getLogger(this.getClass());

	@Autowired
	CommonDao commonDao;
	
	@Autowired
	private UserService userService;
	
	public List<MenuDTO> getActiveMenus(long userId,String positionId) throws ServiceAppException {
		
		UserSearchDTO userSearchDTO =userService.getUserByUserId(userId);
		userSearchDTO.getUserType();
		
		Map<String, List<MenuDTO>> subMap = new HashMap<String, List<MenuDTO>>();
		//
		List<MenuRolePo> menuRoles = getMenuRoles(userId,positionId,Integer.valueOf(userSearchDTO.getUserType()));
		if(menuRoles != null && menuRoles.size() > 0) {
			long lastMid = -1;
			long lastPid = -1;
			List<MenuDTO> subMenu = null;
			for(MenuRolePo menuRole : menuRoles) {
				long menuId = menuRole.getMenuId();
				long parentId = menuRole.getParentId();
				if(parentId == lastPid) {
					//同父菜单
					if(menuId == lastMid) {
						//同菜单
						subMenu.get(subMenu.size() - 1).getPermissions().add(menuRole.getRoleName());
					} else {
						//不同菜单
						MenuDTO dtoMenu = convert(menuRole);
						String subKey = String.valueOf(menuId);
						if(subMap.containsKey(subKey)) {
							dtoMenu.setSubmenus(subMap.get(subKey));
							subMap.remove(subKey);
						} else {
							dtoMenu.setSubmenus(new ArrayList<MenuDTO>());
						}
						subMenu.add(dtoMenu);
						lastMid = menuId;
					}
				} else {
					//非同父菜单
					if(subMenu != null) {
						subMap.put(String.valueOf(lastPid), subMenu);
					}
					MenuDTO dtoMenu = convert(menuRole);
					String subKey = String.valueOf(menuId);
					if(subMap.containsKey(subKey)) {
						dtoMenu.setSubmenus(subMap.get(subKey));
						subMap.remove(subKey);
					} else {
						dtoMenu.setSubmenus(new ArrayList<MenuDTO>());
					}
					subMenu = new ArrayList<MenuDTO>();
					subMenu.add(dtoMenu);
					lastPid = parentId;
					lastMid = menuId;
				}
			}
			return subMenu;
		} else {
			return null;
		}
	}

	@Transactional
	private List<MenuRolePo> getMenuRoles(long userId,String positionId,int type) throws ServiceAppException{
		List<MenuRolePo> list = null;
		try{
			list = commonDao.getMenuRoles(userId+"",positionId,type);
		}catch(Exception e){
			throw new ServiceAppException("error");
		}
		return list;
	}
	
	private MenuDTO convert(MenuRolePo menuRole) throws ServiceAppException{
		MenuDTO dtoMenu = new MenuDTO();
		
		dtoMenu.setId(menuRole.getMenuId());
		dtoMenu.setName(menuRole.getMenuName());
		dtoMenu.setUrl(menuRole.getMenuUrl() == null ? "" : menuRole.getMenuUrl().trim());
		List<String> permissions = new ArrayList<String>();
		permissions.add(menuRole.getRoleName());
		dtoMenu.setPermissions(permissions);
		
		return dtoMenu;
	}

	public List<MenuTreeDTo> getMenusTree(long type) throws ServiceAppException{
		List<MenuRolePo> menuRoles = null;
		try{
			menuRoles = commonDao.getMenuRolesByType(type);
		}catch(Exception e){
			throw new ServiceAppException("error");
		}

		if(menuRoles != null && menuRoles.size() > 0) {
			
			List<MenuTreeDTo> subTreeMenu = new ArrayList<MenuTreeDTo>();
			for(MenuRolePo menuRole : menuRoles) {
				if(menuRole.getParentId()==0){
					MenuDTO dtoMenu = convert(menuRole);
					MenuTreeDTo menuTreeDTo =new MenuTreeDTo();
					menuTreeDTo.setId(dtoMenu.getId());
					menuTreeDTo.setState("closed");
					menuTreeDTo.setText(dtoMenu.getName());
					menuTreeDTo.setChildren(new ArrayList<MenuTreeDTo>());
					subTreeMenu.add(menuTreeDTo);
				}
			}
			for(MenuTreeDTo dTo : subTreeMenu) {
				List<MenuTreeDTo> subTreeMenu1 = new ArrayList<MenuTreeDTo>();
				for(MenuRolePo menuRole : menuRoles) {
					if(menuRole.getParentId()==dTo.getId()){
						MenuDTO dtoMenu = convert(menuRole);
						MenuTreeDTo menuTreeDTo =new MenuTreeDTo();
						menuTreeDTo.setId(dtoMenu.getId());
						menuTreeDTo.setState("open");
						menuTreeDTo.setText(dtoMenu.getName());
						subTreeMenu1.add(menuTreeDTo);
					}
				}
				if(subTreeMenu1.size()<=0){
					dTo.setState("open");
				}
				dTo.setChildren(subTreeMenu1);
			}
			return subTreeMenu;
		} else {
			return null;
		}
	}
}