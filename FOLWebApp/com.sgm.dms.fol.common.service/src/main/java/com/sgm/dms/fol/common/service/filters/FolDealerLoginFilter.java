package com.sgm.dms.fol.common.service.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;

import com.sgm.dms.am.client.SSOCertificate;
import com.sgm.dms.am.client.util.SSOClientUtil;
import com.sgm.dms.fol.common.api.domain.UserSearchDTO;
import com.sgm.dms.fol.common.api.services.UserService;
import com.sgm.dms.fol.common.service.utils.ApplicationContextUtils;
import com.sgm.dms.fol.common.service.utils.CookieUtil;
import com.sgm.dms.fol.common.service.utils.PropertiesUtil;
import com.sgm.dms.fol.common.service.utils.RSAUtil;

public class FolDealerLoginFilter implements Filter {
	private Logger logger = LogManager.getLogger(this.getClass());

	// 路径白名单
	private List<String> excludePathList = new ArrayList<String>();

	// 文件白名单
	private List<String> excludeFileList = new ArrayList<String>();

	private HttpServletRequest httpServletRequest = null;

	private HttpServletResponse httpServletResponse = null;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// 初始化路径白名单
		String excludePath = filterConfig.getInitParameter("excludePath");
		if (excludePath != null) {
			String[] paths = excludePath.split(",");
			for (String path : paths) {
				if (!"".equals(path = path.trim())) excludePathList.add(path);
			}
		}

		// 初始化文件白名单
		String excludeFile = filterConfig.getInitParameter("excludeFile");
		if (excludeFile != null) {
			String[] files = excludeFile.split(",");
			for (String file : files) {
				if (!"".equals(file = file.trim())) excludeFileList.add(file);
			}
		}

		// init RSA publickey and privatekey
		try {
			RSAUtil.initRSAKeys();
		} catch (Exception e) {
			logger.error("init RSA publickey and privatekey error", e);
		}
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
		try {
			httpServletRequest = (HttpServletRequest) servletRequest;
			httpServletResponse = (HttpServletResponse) servletResponse;
			
			//打印请求日志
	        //LogUtil.getRequestLog(httpServletRequest);

			// 禁用浏览器缓存
			httpServletResponse.setDateHeader("Expires", 0);
			httpServletResponse.setHeader("Cache-Control", "no-cache,no-store,max-age=0");
			httpServletResponse.setHeader("Pragma", "no-cache");

			String requestURI = httpServletRequest.getRequestURI();

			// 排除不需要检查的文件路径
			for (String excludePath : excludePathList) {
				if (requestURI.contains(excludePath)) {
					chain.doFilter(servletRequest, servletResponse);
					return;
				}
			}
			// 排除不需要检查的文件类型
			for (String excludeFile : excludeFileList) {
				if (requestURI.toUpperCase().endsWith(excludeFile.toUpperCase())) {
					chain.doFilter(servletRequest, servletResponse);
					return;
				}
			}

			/*
			 * 验证用户是否已经登录或者系统现在是否是测试模式
			 */
			String userAccount = null;//登录账户
			String userAccountInCookie = CookieUtil.getAccount(httpServletRequest);//cookie账户

			
			String model = PropertiesUtil.getProperty("logon.account.model.test");
			if (!Boolean.valueOf(model)) {//如果不是测试模式，从SSOCertificate中读取用户账号。
				SSOCertificate certificate = SSOClientUtil.getCertificate(httpServletRequest);
				userAccount = null == certificate ? null : certificate.getUserId();
			}else{//如果是测试模式，从配置文件中读取默认用户账号。
				userAccount = PropertiesUtil.getProperty("logon.account.name");
			}

			logger.info("===========================login userAccount is============================:" + userAccount);
			if (null == userAccount) httpServletResponse.sendError(HttpStatus.FORBIDDEN.value());//不是测试模式，也没登录，403没有访问权限响应。

			/**
			 * 设置用户信息cookie
			 */
			if (userAccount.equals(userAccountInCookie) && !requestURI.contains("/user/login/dealer/selectposition")) {// cookie值与证书userAccount相等，已经存在用户信息cookie，直接通过。
				chain.doFilter(servletRequest, servletResponse);return;
			} else {// cookie值为空，或者与证书userAccount不相等，重新从数据库查询用户信息并设置到cookie
				UserService userService = ApplicationContextUtils.getBeanByType(UserService.class);
				List<UserSearchDTO> users = userService.getDealerUserByAccount(userAccount);
				
				if(null == users || users.isEmpty()){
					httpServletResponse.sendError(HttpStatus.FORBIDDEN.value());
				}else if(1 == users.size()){
					CookieUtil.setCookies(users.get(0), httpServletRequest, httpServletResponse);
					httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/index.html");
				}else{
					if (requestURI.contains("/user/login/dealer/selectposition")) {
						UserSearchDTO user = userService.getUserByUserId(Long.parseLong(httpServletRequest.getParameter("loginId")));
						CookieUtil.setCookies(user, httpServletRequest, httpServletResponse);
						httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/index.html");
					}
					httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/msg_identity.html");
				}
			}
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Override
	public void destroy() {}
}
