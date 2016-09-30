
package com.sgm.dms.fol.common.service.utils;

import com.sgm.dms.fol.common.api.constants.CookieConstant;
import com.sgm.dms.fol.common.api.domain.UserSearchDTO;
import com.sgm.dms.fol.common.api.exception.ServiceAppException;
import com.sgm.solution.framework.core.encrypt.Encryptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class CookieUtil {
	public static Long getUserId(HttpServletRequest request) throws Exception {
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals(CookieConstant.COOKIE_USER_ID)) {
				    System.out.println(cookie.getValue());
					return Long.valueOf(RSAUtil.decryptByPrivateKey(cookie.getValue()));
				}
			}
		}
		return null;
	}
	
	public static String getDealerId(HttpServletRequest request)throws Exception {
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals(CookieConstant.COOKIE_DEALER_ID)) {
					return RSAUtil.decryptByPrivateKey(cookie.getValue());
				}
			}
		}
		return null;
	}
	
	public static String getOrgCode(HttpServletRequest request)throws Exception {
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals(CookieConstant.COOKIE_ORG_CODE)) {
					return RSAUtil.decryptByPrivateKey(cookie.getValue());
				}
			}
		}
		return null;
	}
	
	public static String getPositionId(HttpServletRequest request)throws Exception {
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals(CookieConstant.COOKIE_POSITION_ID)) {
					return RSAUtil.decryptByPrivateKey(cookie.getValue());
				}
			}
		}
		return null;
	}
	
	public static String getAccount(HttpServletRequest request)throws Exception {
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals(CookieConstant.COOKIE_ACCOUNT)) {
					return RSAUtil.decryptByPrivateKey(cookie.getValue());
				}
			}
		}
		return null;
	}
	
	public static String getDlrId(HttpServletRequest request)throws Exception {
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals(CookieConstant.COOKIE_DLR_ID)) {
					return RSAUtil.decryptByPrivateKey(cookie.getValue());
				}
			}
		}
		return null;
	}
	
	/*public static String getLogonId(HttpServletRequest request)throws Exception {
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals(CookieConstant.COOKIE_LOCAL_SYSTEM_LOGON_ID)) {
					return RSAUtil.decryptByPrivateKey(cookie.getValue());
				}
			}
		}
		return null;
	}*/
	public static String getLogonId(HttpServletRequest request)throws Exception {
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals(CookieConstant.COOKIE_USER_ID)) {
					return RSAUtil.decryptByPrivateKey(cookie.getValue());
				}
			}
		}
		return null;
	}
	
	public static String getSapCode(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals(CookieConstant.COOKIE_SAP_CODE)) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}
	
	/**
	 * 根据用户信息设置cookie
	 * @author wangfl
	 * @date 2016年9月26日
	 * @param user
	 * @param request
	 * @param response
	 */
	public static void setCookies(UserSearchDTO user, HttpServletRequest request, HttpServletResponse response){
		String cookiePath = request.getContextPath();//cookie path
		try {
			// userId
			String userId = user.getUserId();
			if (null != userId) {
				Cookie userIdCookie = new Cookie(CookieConstant.COOKIE_USER_ID, RSAUtil.encryptByPublicKey(userId));
				userIdCookie.setPath(cookiePath);
				response.addCookie(userIdCookie);
			}

			// positionId
			String positionId = user.getPositionId();
			if (null != positionId) {
				Cookie positionIdCookie = new Cookie(CookieConstant.COOKIE_POSITION_ID, RSAUtil.encryptByPublicKey(String.valueOf(positionId)));
				positionIdCookie.setPath(cookiePath);
				response.addCookie(positionIdCookie);
			}

			// userAccount
			String userAccount = user.getUserAccount();
			if (null != userAccount) {
				Cookie userAccountCookie = new Cookie(CookieConstant.COOKIE_ACCOUNT, RSAUtil.encryptByPublicKey(userAccount));
				userAccountCookie.setPath(cookiePath);
				response.addCookie(userAccountCookie);
			}

			// dlrId
			String dealerId = user.getDealerId();
			if (null != dealerId) {
				Cookie dlrIdCookie = new Cookie(CookieConstant.COOKIE_DLR_ID, RSAUtil.encryptByPublicKey(dealerId));
				dlrIdCookie.setPath(cookiePath);
				response.addCookie(dlrIdCookie);
			}

			// sapCode
			String sapCode = user.getSapCode();
			if (null != sapCode) {
				Cookie sapCodeCookie = new Cookie(CookieConstant.COOKIE_SAP_CODE, RSAUtil.encryptByPublicKey(sapCode));
				sapCodeCookie.setPath(cookiePath);
				response.addCookie(sapCodeCookie);
			}

			// dPPositionCode
			String dPPositionCode = user.getdPPositionCode();
			if (null != dPPositionCode) {
				Cookie dPPositionCodeCookie = new Cookie(CookieConstant.COOKIE_DP_POSITION_CODE, RSAUtil.encryptByPublicKey(dPPositionCode));
				dPPositionCodeCookie.setPath(cookiePath);
				response.addCookie(dPPositionCodeCookie);
			}

			// roleId
			String roleIds = user.getRoleIds();
			if (null != roleIds) {
				Cookie roleIdCookie = new Cookie(CookieConstant.COOKIE_ROLE_ID, roleIds);
				roleIdCookie.setPath(cookiePath);
				response.addCookie(roleIdCookie);

				// REST-API 权限控制模块集成
				Cookie roleIdEncCookie = new Cookie(CookieConstant.ROLE_ID_ENC, Encryptor.generateSign(roleIds));
				roleIdEncCookie.setPath(cookiePath);
				response.addCookie(roleIdEncCookie);
			}
		} catch (Exception e) {
			throw new ServiceAppException("设置系统cookie发生错误", e);
		}
    }
	
	public static void getCleanCookie(HttpServletRequest request,HttpServletResponse response) throws Exception{
		//清空Cookie
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				Cookie newCookie = new Cookie(cookie.getName(), null);
				newCookie.setPath("/");
				newCookie.setMaxAge(0);
				response.addCookie(newCookie);
			}
		}
	}
}
