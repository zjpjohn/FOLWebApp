/*
 * Copyright 2015 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : common.service
 *
 * @File name : LogonFilter.java
 *
 * @Author : ShenWeiwei
 *
 * @Date : 2015年10月29日
 *
----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2015年10月29日    ShenWeiwei    1.0
 *
 *
 *
 *
----------------------------------------------------------------------------------
 */

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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.sgm.dms.fol.common.api.constants.CodeConstant;
import com.sgm.dms.fol.common.api.constants.CookieConstant;
import com.sgm.dms.fol.common.api.domain.DealerInfoDTO;
import com.sgm.dms.fol.common.api.domain.PositionDTO;
import com.sgm.dms.fol.common.api.domain.UserDTO;
import com.sgm.dms.fol.common.api.domain.UserSearchDTO;
import com.sgm.dms.fol.common.api.services.DealerService;
import com.sgm.dms.fol.common.api.services.PositionService;
import com.sgm.dms.fol.common.api.services.UserService;
import com.sgm.dms.fol.common.service.utils.ApplicationContextUtils;
import com.sgm.dms.fol.common.service.utils.CookieUtil;
import com.sgm.dms.fol.common.service.utils.LogUtil;
import com.sgm.dms.fol.common.service.utils.PropertiesUtil;
import com.sgm.dms.fol.common.service.utils.RSAUtil;
import com.sgm.dms.fol.common.service.utils.StringUtil;

/*
 *
 * @author ShenWeiwei
 * TODO description
 * @date 2015年10月29日
 */

public class FolSgmLogonFilter implements Filter {

    // 日志
    protected Logger logger = LogManager.getLogger(this.getClass());

    // 登陆成功URL
//    private String innerUrl = null;

    // 登陆URL
    private String loginUrl = null;

    // 登陆退出URL
    private String logoutUrl = null;

    // 路径白名单
    private List<String> excludePathList = new ArrayList<String>();

    // 文件白名单
    private List<String> excludeFileList = new ArrayList<String>();

    private HttpServletRequest req = null;

    private HttpServletResponse res = null;

    /*
     * @author ShenWeiwei
     * @date 2015年10月29日
     * @param filterConfig
     * @throws ServletException (non-Javadoc)
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        innerUrl = filterConfig.getInitParameter("innerUrl"); 
        loginUrl =filterConfig.getInitParameter("loginUrl");
        logoutUrl = filterConfig.getInitParameter("logoutUrl");

        // 初始化白名单路径
        String excludePath = filterConfig.getInitParameter("excludePath");
        if (excludePath != null && !"".equals(excludePath)) {
            String[] paths = excludePath.split(",");
            for (String path : paths) {
                excludePathList.add(path);
            }
        }

        // 初始化白名单文件类型
        String excludeFile = filterConfig.getInitParameter("excludeFile");
        if (excludeFile != null && !"".equals(excludeFile)) {
            String[] files = excludeFile.split(",");
            for (String file : files) {
                excludeFileList.add(file);
            }
        }

        // init RSA publickey and privatekey
        try {
            RSAUtil.initRSAKeys();            
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }

    }

    /*
     * @author ShenWeiwei
     * @date 2015年10月29日
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException (non-Javadoc)
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse,
     * javax.servlet.FilterChain)
     */

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
                                                                                              ServletException {
        req = (HttpServletRequest) request;
        res = (HttpServletResponse) response;
        
		// 打印请求日志
		//LogUtil.getRequestLog(req);
        
        /*session过期手动创建一个SESSION*/
        HttpSession httpSession=req.getSession(true);
        
        /*设置响应头信息，告诉浏览器不要缓存此内容*/
        res.setDateHeader("Expires", 0);
        res.setHeader("Cache-Control", "no-cache,no-store,max-age=0");
        res.setHeader("Pragma", "no-cache");
        
        //数据初始化
        String requestURI = req.getRequestURI();
        String path=req.getServletPath();
        Boolean isDoFilter=false;
        Boolean isNotExistsVisitAuthority=false;
        String redirectUrl="";
        
        //获取账号
        String acnt = req.getHeader(CodeConstant.LDAP_USER_REQUEST_STRING);
        logger.info("acnt:" + acnt);
        
        //获取SERVICE
        UserService userService = (UserService) ApplicationContextUtils.getBeanById("UserService");
        
        try {
            // 如果是测试模式就走入到IF里
            String model = PropertiesUtil.getProperty("logon.account.model.test");

            if (model != null && Boolean.valueOf(model)) {
                acnt = PropertiesUtil.getProperty("logon.account.name");
            }
            
            // 排除不需要检查的文件路径
            for (String excludePath : excludePathList) {
                if (requestURI.contains(excludePath) ) {
                    isDoFilter=true;
                    return;
                }
            }
            // 排除不需要检查的文件类型
            for (String excludeFile : excludeFileList) {
                if (requestURI.contains(excludeFile) ) {
                    isDoFilter=true;
                    return;
                }
            }

            Long userId = null;
       
        
            logger.info("begin step first");
            userId = CookieUtil.getUserId(req);
            logger.info("userId:" + userId);
            
            System.out.println("requestURI:"+requestURI);
            System.out.println("ServletPath:"+req.getServletPath());
            
            // 如果是common/login请求则走到IF里(岗位切换，或者是首次登陆选择岗位)
            if(requestURI.contains("common/login")){
                
                List<UserSearchDTO> users = userService.getSGMUserByAccount(acnt);

                // 如果读取出来的库里有
                if (users != null && users.size() > 0 && req.getParameter("loginId") != null) {
                    UserSearchDTO user = null;
                    for (UserSearchDTO userSearchDTO : users) {
                        if (userSearchDTO.getUserId().equals(req.getParameter("loginId"))) {
                            user = userSearchDTO;
                            break;
                        }
                    }
                    if (user != null) {
                        Cookie uId = new Cookie(CookieConstant.COOKIE_USER_ID,
                                                RSAUtil.encryptByPublicKey(user.getUserId()));
                        uId.setPath("/");
                        res.addCookie(uId);

                        // Cookie dealerId = new Cookie(
                        // CookieConstant.COOKIE_DEALER_ID,
                        // String.valueOf(user.getOrgCode()));
                        // dealerId.setPath("/");
                        // res.addCookie(dealerId);
                        //
                        // Cookie orgCode = new Cookie(
                        // CookieConstant.COOKIE_ORG_CODE,
                        // user.getOrgCode());
                        // orgCode.setPath("/");
                        // res.addCookie(orgCode);

                        // 根据USER查询出岗位
                        UserDTO userDTO = new UserDTO();
                        userDTO.setUserAccount(user.getUserAccount());
                        PositionService positionService = (PositionService) ApplicationContextUtils.getBeanById("PositionService");
                        List<PositionDTO> positions = positionService.getPositionsByUserAccount(userDTO);
                        for (PositionDTO positionDTO : positions) {
                            logger.info("before changed positionId:" + req.getParameter("positionId"));
                            if (req.getParameter("positionId") != null
                                && positionDTO.getPositionId().equals(req.getParameter("positionId"))) {
                                logger.info("changed positionId:" + req.getParameter("positionId"));
                                Cookie psId = new Cookie(CookieConstant.COOKIE_POSITION_ID,
                                                         RSAUtil.encryptByPublicKey(String.valueOf(positionDTO.getPositionId())));
                                psId.setPath("/");
                                res.addCookie(psId);
                            }
                        }

                        Cookie account = new Cookie(CookieConstant.COOKIE_ACCOUNT,
                                                    RSAUtil.encryptByPublicKey(user.getUserAccount()));
                        account.setPath("/");
                        res.addCookie(account);

                        Cookie dlrId = new Cookie(CookieConstant.COOKIE_DLR_ID,
                                                  RSAUtil.encryptByPublicKey(user.getDealerId()));
                        dlrId.setPath("/");
                        res.addCookie(dlrId);
                        
//                        RoleService roleService = (RoleService) ApplicationContextUtils.getBeanById("roleService");
//                        List<RoleDTO> roles=roleService.getRolesByUser(Long.parseLong(user.getUserId()));
//                        if(roles!=null&&roles.size()>0){
//                            String rolestr="";
//                            for (RoleDTO roledto : roles) {
//                                rolestr+=","+roledto.getRoleId();
//                            }
//                            
//                            Cookie roleId = new Cookie(CookieConstant.COOKIE_ROLE_ID,rolestr.substring(1,rolestr.length()));
//                            roleId.setPath("/");
//                            res.addCookie(roleId);
//                            
//                            CookieRegister.register(req, res); 
//                        }else{
//                            logger.info("not exists role");
//                            isNotExistsVisitAuthority=true;
////                            res.sendError(403);
//                            return;
//                        }
                        
                        
                        // 根据DEALER_ID查找出DEALER_INFO
                        DealerInfoDTO dealerInfoDTO = new DealerInfoDTO();
                        dealerInfoDTO.setDealerId(user.getDealerId());
                        // 如果SGM不需要执行下面的SERVICE
                        if ("-1".equals(user.getDealerId())) {
                            isDoFilter=true;
                            return;
                        }

                        logger.info("dealerService:" + ApplicationContextUtils.getBeanById("DealerService"));

                        DealerService dealerService = (DealerService) ApplicationContextUtils.getBeanById("DealerService");

                        DealerInfoDTO resultdto = dealerService.getDealerInfoByDealerId(dealerInfoDTO);
                        Cookie sapCode = new Cookie(CookieConstant.COOKIE_SAP_CODE,
                                                    RSAUtil.encryptByPublicKey(resultdto.getSapCode()));
                        sapCode.setPath("/");
                        res.addCookie(sapCode);

                        isDoFilter=true;
                        return;

                    } else {
                        logger.info("state5:403");
                        redirectUrl=loginUrl;
//                        res.sendRedirect(loginUrl);
                        return;
                    }
                } else {
                    logger.info("state6:403");
                    redirectUrl=loginUrl;
//                    res.sendRedirect(loginUrl);
                    return;
                }
            }
            
            // 判断cookie中有没有用户
            if (userId == null) {
                if (requestURI.indexOf("/logout.html") != -1) {
                    logger.info("logout");
                    redirectUrl=logoutUrl;
//                    res.sendRedirect(logoutUrl);
                    return;
                }

                // LDAP和本地的用户都为空，跳转回LDAP界面
                if (acnt == null) {
                    logger.info("login");
                    redirectUrl=loginUrl;
//                    res.sendRedirect(loginUrl);
                    return;
                    // 本地用户登录成功直接跳转到主页面
                } else {
                    logger.info("login success");
                   
                    List<UserSearchDTO> users = userService.getSGMUserByAccount(acnt);

                    // 如果读取出来的库里有
                    if (users != null && users.size() == 1) {
                        UserSearchDTO user = users.get(0);
                        logger.info("user:" + user.getUserId());
                        Cookie uId = new Cookie(CookieConstant.COOKIE_USER_ID,
                                                RSAUtil.encryptByPublicKey(String.valueOf(user.getUserId())));
                        uId.setPath("/");
                        res.addCookie(uId);

                        // Cookie dealerId = new Cookie(
                        // CookieConstant.COOKIE_DEALER_ID,
                        // String.valueOf(user.getOrgCode()));
                        // dealerId.setPath("/");
                        // res.addCookie(dealerId);
                        //
                        // Cookie orgCode = new Cookie(
                        // CookieConstant.COOKIE_ORG_CODE,
                        // user.getOrgCode());
                        // orgCode.setPath("/");
                        // res.addCookie(orgCode);

                        Cookie account = new Cookie(CookieConstant.COOKIE_ACCOUNT,
                                                    RSAUtil.encryptByPublicKey(user.getUserAccount()));
                        account.setPath("/");
                        res.addCookie(account);

                        Cookie dlrId = new Cookie(CookieConstant.COOKIE_DLR_ID,
                                                  RSAUtil.encryptByPublicKey(user.getDealerId()));
                        dlrId.setPath("/");
                        res.addCookie(dlrId);

                        // 根据USER查询出岗位
                        Cookie psId = new Cookie(CookieConstant.COOKIE_POSITION_ID,
                                                 RSAUtil.encryptByPublicKey(String.valueOf(user.getPositionId())));
                        psId.setPath("/");
                        res.addCookie(psId);

//                        RoleService roleService = (RoleService) ApplicationContextUtils.getBeanById("roleService");
//                        List<RoleDTO> roles=roleService.getRolesByUser(Long.parseLong(user.getUserId()));
//                        if(roles!=null&&roles.size()>0){
//                            String rolestr="";
//                            for (RoleDTO roledto : roles) {
//                                rolestr+=","+roledto.getRoleId();
//                            }
//                            
//                            Cookie roleId = new Cookie(CookieConstant.COOKIE_ROLE_ID,rolestr.substring(1,rolestr.length()));
//                            roleId.setPath("/");
//                            res.addCookie(roleId);
//                            
//                            CookieRegister.register(req, res); 
//                        }else{
//                            logger.info("not exists role");
//                            isNotExistsVisitAuthority=true;
////                            res.sendError(403);
//                            return;
//                        }
                        
                        // 如果SGM不需要执行下面的SERVICE
                        if ("-1".equals(user.getDealerId())) {
//                            isDoFilter=true;
                            return;
                        }

                        // 根据DEALER_ID查找出DEALER_INFO
                        DealerInfoDTO dealerInfoDTO = new DealerInfoDTO();
                        dealerInfoDTO.setDealerId(user.getDealerId());
                        DealerService dealerService = (DealerService) ApplicationContextUtils.getBeanById("DealerService");

                        DealerInfoDTO resultdto = dealerService.getDealerInfoByDealerId(dealerInfoDTO);
                        Cookie sapCode = new Cookie(CookieConstant.COOKIE_SAP_CODE,
                                                    RSAUtil.encryptByPublicKey(resultdto.getSapCode()));
                        sapCode.setPath("/");
                        res.addCookie(sapCode);

                        isDoFilter=true;
                        return;
                    } else if (users!=null&&users.size() > 1) {
                        redirectUrl=req.getContextPath()+"/msg_identity.html";
//                        res.sendRedirect(req.getContextPath()+"/msg_identity.html");
                        return;
                    } else {
                        logger.info("state1:403");
                        isNotExistsVisitAuthority=true;
//                        res.sendError(403);
                        return;
                    }
                }
            } 
            else {
                logger.info("begin step second");
                if (acnt == null) {
                    logger.info("login2");
                    redirectUrl=loginUrl;
                    res.sendRedirect(loginUrl);
                    return;
                    // 本地用户登录成功直接跳转到主页面
                } else {
                    logger.info("login2 success");

                   
                    List<UserSearchDTO> users = userService.getSGMUserByAccount(acnt);

                    // 如果读取出来的库里有
                    if (users != null && users.size() > 0) {
                        UserSearchDTO user = null;
                        for (UserSearchDTO userSearchDTO : users) {

                            if (userSearchDTO.getUserId().equals(userId+"")) {
                                user = userSearchDTO;
                                break;
                            }
                        }
//                        
//                        if (user != null) {
//                            Cookie uId = new Cookie(CookieConstant.COOKIE_USER_ID,RSAUtil.encryptByPublicKey(user.getUserId()));
//                            uId.setPath("/");
//                            res.addCookie(uId);
//
//                            // Cookie dealerId = new Cookie(
//                            // CookieConstant.COOKIE_DEALER_ID,
//                            // String.valueOf(user.getOrgCode()));
//                            // dealerId.setPath("/");
//                            // res.addCookie(dealerId);
//                            //
//                            // Cookie orgCode = new Cookie(
//                            // CookieConstant.COOKIE_ORG_CODE,
//                            // user.getOrgCode());
//                            // orgCode.setPath("/");
//                            // res.addCookie(orgCode);
//
//                            // 根据USER查询出岗位
//                            UserDTO userDTO = new UserDTO();
//                            userDTO.setUserId(Long.parseLong(user.getUserId()));
//                            PositionService positionService = (PositionService) ApplicationContextUtils.getBeanById("PositionService");
//                            List<PositionDTO> positions = positionService.getPositionsByUserId(userDTO);
//                            for (PositionDTO positionDTO : positions) {
//                                logger.info("before changed positionId:" + req.getParameter("positionId"));
//                                if (req.getParameter("positionId") != null
//                                    && positionDTO.getPositionId().equals(req.getParameter("positionId"))) {
//                                    logger.info("changed positionId:" + req.getParameter("positionId"));
//                                    Cookie psId = new Cookie(CookieConstant.COOKIE_POSITION_ID,RSAUtil.encryptByPublicKey(user.getPositionId()));
//                                    psId.setPath("/");
//                                    res.addCookie(psId);
//                                }
//                            }
//
//                            Cookie account = new Cookie(CookieConstant.COOKIE_ACCOUNT,RSAUtil.encryptByPublicKey(user.getUserAccount()));
//                            account.setPath("/");
//                            res.addCookie(account);
//
//                            Cookie dlrId = new Cookie(CookieConstant.COOKIE_DLR_ID,RSAUtil.encryptByPublicKey(user.getDealerId()));
//                            dlrId.setPath("/");
//                            res.addCookie(dlrId);
//
////                            RoleService roleService = (RoleService) ApplicationContextUtils.getBeanById("roleService");
////                            List<RoleDTO> roles=1.getRolesByUser(Long.parseLong(user.getUserId()));
////                            if(roles!=null&&roles.size()>0){
////                                String rolestr="";
////                                for (RoleDTO roledto : roles) {
////                                    rolestr+=","+roledto.getRoleId();
////                                }
////                                
////                                Cookie roleId = new Cookie(CookieConstant.COOKIE_ROLE_ID,rolestr.substring(1,rolestr.length()));
////                                roleId.setPath("/");
////                                res.addCookie(roleId);
////                                
////                                CookieRegister.register(req, res); 
////                            }else{
////                                logger.info("not exists role");
////                                isNotExistsVisitAuthority=true;
//////                                res.sendError(403);
////                                return;
////                            }
//                            
//                            // 根据DEALER_ID查找出DEALER_INFO
//                            DealerInfoDTO dealerInfoDTO = new DealerInfoDTO();
//                            dealerInfoDTO.setDealerId(user.getDealerId());
//                            // 如果SGM不需要执行下面的SERVICE
//                            if ("-1".equals(user.getDealerId())) {
////                                isDoFilter=true;
//                                return;
//                            }
//
//                            logger.info("dealerService:" + ApplicationContextUtils.getBeanById("DealerService"));
//
//                            DealerService dealerService = (DealerService) ApplicationContextUtils.getBeanById("DealerService");
//
//                            DealerInfoDTO resultdto = dealerService.getDealerInfoByDealerId(dealerInfoDTO);
//                            Cookie sapCode = new Cookie(CookieConstant.COOKIE_SAP_CODE,RSAUtil.encryptByPublicKey(resultdto.getSapCode()));
//                            sapCode.setPath("/");
//                            res.addCookie(sapCode);
//
//                            isDoFilter=true;
//                            return;
                        	
//                        } else {
//                            logger.info("state3:403");
//                            redirectUrl=loginUrl;
////                            res.sendRedirect(loginUrl);
//                            return;
//                        }
//                    } else {
//                        logger.info("state4:403");
//                        redirectUrl=loginUrl;
//                        res.sendRedirect(loginUrl);
//                        return;
                        
                        if(user==null){
                        	logger.info("state3:403");
                        	redirectUrl=loginUrl;
                        	return;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();            
        } finally{
            if(acnt!=null&&!"".equals(acnt)){    
                if(isNotExistsVisitAuthority){
                    res.sendError(403);
                    return;
                }
                if(!"".equals(redirectUrl)){
                    res.sendRedirect(redirectUrl);
                    return;
                }
                if(isDoFilter||path.contains("index.html")||path.contains("index_dev.html")||path.contains("common/users")||path.contains("common/logout")||path.contains("common/login")){
                    chain.doFilter(request, response);
                    return;
                }
                
                if(path.contains("common/logout")){
                	if(httpSession.getAttribute("urllist")!=null){
                		httpSession.removeAttribute("urllist");
                	}
                    chain.doFilter(request, response);
                    return;
                }
                
                try {
                    if(StringUtil.isEmpty(httpSession.getAttribute("urllist"))){
                        String urllist=userService.getAuthorityUrlByAccount(CookieUtil.getAccount(req));
                        httpSession.setAttribute("urllist", RSAUtil.encryptByPublicKey(urllist)); 
                    }
                    
                    if(!StringUtil.isEmpty(httpSession.getAttribute("urllist"))){
                        String urllist=RSAUtil.decryptByPrivateKey(httpSession.getAttribute("urllist").toString());
                        
                        if(StringUtil.matchesNumber(path)){
                        	String[] list=urllist.split(",");
                        	for (String url : list) {
								if(path.contains(url)){
									chain.doFilter(request, response);
		                            return;
								}
							}
                        	
                        	logger.info("user not exists http action authority");
                            res.sendError(403);
                            return;
                        }
                        
                        if(!urllist.contains(path.substring(1, path.length()))){
                            logger.info("user not exists http action authority");
                            res.sendError(403);
                            return; 
                        }else{
                        	chain.doFilter(request, response);
                            return;
                        }
                    }else{
                        chain.doFilter(request, response);
                        return;
                    }
                } catch (Exception e) {                    
                    e.printStackTrace();
                }
            }
        }

    }

    /*
     * @author ShenWeiwei
     * @date 2015年10月29日 (non-Javadoc)
     * @see javax.servlet.Filter#destroy()
     */

    @Override
    public void destroy() {
        logger.info("this filter destory");
        if (req != null && res != null) {
            try {
                CookieUtil.getCleanCookie(req, res);
                req.getSession().removeAttribute("urllist");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}
