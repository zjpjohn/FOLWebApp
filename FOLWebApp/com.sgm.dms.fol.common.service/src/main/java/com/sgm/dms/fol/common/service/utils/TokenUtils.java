
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : common.service
 *
 * @File name : TokenUtils.java
 *
 * @Author : wangfl
 *
 * @Date : 2016年4月11日
 *
 ----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2016年4月11日    wangfl    1.0
 *
 *
 *
 *
 ----------------------------------------------------------------------------------
 */
	
package com.sgm.dms.fol.common.service.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sgm.solution.framework.core.common.ServiceClient;
import com.sgm.solution.framework.core.common.UserTokenClient;
import com.sgm.solution.framework.core.common.UserTokenServiceBuilder;
import com.sgm.solution.framework.core.exception.FrameworkException;
import com.sgm.solution.framework.core.vo.TokenConfig;
import com.sgm.solution.framework.core.vo.webservice.UserTokenResponse;

/**
 * @author wangfl
 * 获取token工具类
 * @date 2016年4月11日
 */
@Component
public class TokenUtils {
	
	@Autowired
	private HttpServletRequest request;
    
    /**
     * 
     * @author wangfl
     * 获取token
     *         注：后期遗弃，建议不再使用，换成使用本类getToken()方法
     * @date 2016年4月11日
     * @param userName
     * @return
     */
	@Deprecated
    public static UserTokenResponse getToken(String userName) {
        String app_id = PropertiesUtil.getProperty("app_id");
        if (StringUtil.isEmpty(app_id)) {
            throw new FrameworkException("app id is null,client init failure");
        }
        TokenConfig tokenConfig = UserTokenServiceBuilder.TOKEN_CONFIG.get(UserTokenClient.class.getName());
        UserTokenClient c = new ServiceClient.Builder<UserTokenClient, UserTokenServiceBuilder>().loadBuilerConfig(UserTokenServiceBuilder.PROP_APP_ID,
                                                                                                                   app_id).loadBuilerConfig(UserTokenServiceBuilder.USER_KEY,
                                                                                                                                            userName).loadBuilerConfig(UserTokenServiceBuilder.CONFIG_KEY,
                                                                                                                                                                       tokenConfig).build(UserTokenClient.class,
                                                                                                                                                                                          UserTokenServiceBuilder.class);
        UserTokenResponse token = (UserTokenResponse) c.getServiceResult();
        return token;
    }
    
    /**
     * 获取token
     * @author wangfl
     * @date 2016年8月10日
     * @return
     * @throws Exception
     */
    public UserTokenResponse getToken() throws Exception {
    	String userName = CookieUtil.getAccount(request);
        String app_id= PropertiesUtil.getProperty("app_id");
        
        if (StringUtil.isEmpty(app_id)) throw new FrameworkException("app_id is null,client init failure");
  
        TokenConfig tokenConfig = UserTokenServiceBuilder.TOKEN_CONFIG.get(UserTokenClient.class.getName());
        UserTokenClient c = new ServiceClient.Builder<UserTokenClient, UserTokenServiceBuilder>().loadBuilerConfig(UserTokenServiceBuilder.PROP_APP_ID,
                app_id).loadBuilerConfig(UserTokenServiceBuilder.USER_KEY,
                                         userName).loadBuilerConfig(UserTokenServiceBuilder.CONFIG_KEY,
                                                                    tokenConfig).build(UserTokenClient.class,
                                                                                       UserTokenServiceBuilder.class);
         
        UserTokenResponse token = (UserTokenResponse) c.getServiceResult();
        return token;
    }
    
    /**
     * 获取token字符串
     * @author wangfl
     * @date 2016年8月10日
     * @return
     * @throws Exception
     */
    public String getTokenStr() throws Exception{
    	return getToken().getToken();
    }
}
