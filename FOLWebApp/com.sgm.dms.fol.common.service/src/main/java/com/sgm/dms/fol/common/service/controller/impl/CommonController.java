
package com.sgm.dms.fol.common.service.controller.impl;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.sgm.dms.fol.common.api.constants.CookieConstant;
import com.sgm.dms.fol.common.api.domain.MenuDTO;
import com.sgm.dms.fol.common.api.domain.RoleDTO;
import com.sgm.dms.fol.common.api.domain.UserDTO;
import com.sgm.dms.fol.common.api.exception.ServiceBizException;
import com.sgm.dms.fol.common.api.services.MenuService;
import com.sgm.dms.fol.common.api.services.RoleService;
import com.sgm.dms.fol.common.api.services.UserService;
import com.sgm.dms.fol.common.service.utils.CookieUtil;



@Controller
@RequestMapping("/common")
public class CommonController extends BaseController {
	protected Logger logger = LogManager.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private MenuService menuService;
	
	@RequestMapping(value="/oldlogin", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	public void oldlogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userAccount = request.getParameter("userAccount");
		String aType = request.getParameter("aType");
		logger.info("login userAccount :"+userAccount);
		
		UserDTO user = null;
		try {
			user = userService.getUser(userAccount);
		} catch(ServiceBizException be) {
			throw new ServiceBizException("");
		}
		if(user == null) {
			throw new ServiceBizException("");
		}
		if(user.getUserStatus() != 10011001) {
			throw new ServiceBizException("");
		}
		if(aType!=null&&aType.equals("dealer")&&(user.getUserType()==0||user.getUserType() != 10021001)) {
			throw new ServiceBizException("user type is not valid");
		}
	    if(aType!=null&&aType.equals("sgm")&&(user.getUserType()==0||user.getUserType() != 10021000)) {
			throw new ServiceBizException("user type is not valid");
		}
		
		Cookie userId = new Cookie(CookieConstant.COOKIE_USER_ID, String.valueOf(user.getUserId()));
		userId.setPath("/");
		response.addCookie(userId);
		Cookie dealerId = new Cookie(CookieConstant.COOKIE_DEALER_ID, String.valueOf(user.getOrgCode()));//dealerId存放ascCode
		dealerId.setPath("/");
		response.addCookie(dealerId);
		Cookie orgCode = new Cookie(CookieConstant.COOKIE_ORG_CODE, user.getOrgCode());
		orgCode.setPath("/");
		response.addCookie(orgCode);
		Cookie account = new Cookie(CookieConstant.COOKIE_ACCOUNT, user.getUserAccount());
		account.setPath("/");
		response.addCookie(account);
		Cookie dlrId = new Cookie(CookieConstant.COOKIE_DLR_ID, user.getDealerId());
		dlrId.setPath("/");
		response.addCookie(dlrId);

		try {
			response.sendRedirect(request.getContextPath() + "/index.html");
		} catch(IOException e) {
			throw new ServiceBizException("");
		}
	}

	@RequestMapping(value="/user", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	public @ResponseBody UserDTO getUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long userId = CookieUtil.getUserId(request);
		logger.info("userId:"+userId);
		return getUserWithRole(userId);
	}
	
	@RequestMapping(value="/menu", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	public @ResponseBody List<MenuDTO> getMenu(HttpServletRequest request,HttpServletResponse response) throws Exception {
		logger.info("menu size:"+menuService.getActiveMenus(CookieUtil.getUserId(request),CookieUtil.getPositionId(request)));
		return menuService.getActiveMenus(CookieUtil.getUserId(request),CookieUtil.getPositionId(request));
	}
	
	@RequestMapping(value="/token")
	@ResponseBody
	public Map<String,String> getToken(HttpServletRequest request) throws Exception {
	    String userAccount = CookieUtil.getAccount(request);
	    System.err.println("===="+userAccount+"=======");
	    Map<String,String> map=new HashMap<String, String>();
	    //map.put("token", CookieUtil.getToken(userAccount));
        return map;
	}

	
	private UserDTO getUserWithRole(long userId) throws Exception {
		UserDTO user = userService.getUser(userId);
		List<RoleDTO> roles = roleService.getRolesByUser(userId);
		
		List<String> permissions = new ArrayList<String>();
		for(RoleDTO role : roles) {
			permissions.add(role.getRoleName());
		}
		user.setPermission(permissions);
		
		return user;
	}
}