package com.dealer.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sgm.dms.fol.common.api.domain.MenuDTO;
import com.sgm.dms.fol.common.api.domain.RoleDTO;
import com.sgm.dms.fol.common.api.domain.UserSearchDTO;
import com.sgm.dms.fol.common.api.services.MenuService;
import com.sgm.dms.fol.common.api.services.RoleService;
import com.sgm.dms.fol.common.api.services.UserService;
import com.sgm.dms.fol.common.service.utils.CookieUtil;
import com.sgm.solution.framework.urlaccess.annotations.ValidationRequestUrl;

@Controller
@RequestMapping("/user")
public class UserController {
	protected Logger logger=LogManager.getLogger(this.getClass());

	@Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;
	
	@RequestMapping("/logout/dealer")
	@ValidationRequestUrl
	public void logoutDealer(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Cookie[] cookies=request.getCookies();
		for (Cookie cookie : cookies) {
			Cookie newCookie=new Cookie(cookie.getName(),null);
			newCookie.setPath("/");
			newCookie.setMaxAge(0);
			response.addCookie(newCookie);
		}
		request.getSession().invalidate();
		
		
		logger.info("local logout success");
		String url="http://dp.saic-gm.com/am/oauth/logout";
			
		response.sendRedirect(url);
	}
	
	@Deprecated
	@RequestMapping("/login/dealer/selectposition")
	@ValidationRequestUrl
	public void login(HttpServletRequest request,HttpServletResponse response) throws Exception{
		logger.info("local login success");
		response.sendRedirect(request.getContextPath()+"/index.html");
	}
	
	@RequestMapping(value="/users", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ValidationRequestUrl
	@ResponseBody
    public UserSearchDTO getUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.info("begin users action");
        Long userId = CookieUtil.getUserId(request);
        logger.info("users action:"+userId);
        return getUserWithRole(userId);
    }
    
    @RequestMapping(value="/menus", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ValidationRequestUrl
    @ResponseBody
    public List<MenuDTO> getMenu(HttpServletRequest request,HttpServletResponse response) throws Exception {
        logger.info("begin menu");
        
        List<MenuDTO> menus=menuService.getActiveMenus(CookieUtil.getUserId(request),CookieUtil.getPositionId(request));
        logger.info("menus:"+menus);
        return menus;
    }
    
    private UserSearchDTO getUserWithRole(Long userId) throws Exception {
        UserSearchDTO user = userService.getUserByUserId(userId);
        List<RoleDTO> roles = roleService.getRolesByUser(userId);
        
        List<String> permissions = new ArrayList<String>();
        for(RoleDTO role : roles) {
            permissions.add(role.getRoleName());
        }
        user.setPermission(permissions);
        
        return user;
    }
}
