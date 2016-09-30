/*
* Copyright 2015 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : web.sgm
*
* @File name : SGMTokenController.java
*
* @Author : s86yv7
*
* @Date : 2015年10月26日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2015年10月26日    s86yv7    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/

package com.sgm.dms.fol.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sgm.dms.fol.common.service.utils.CookieUtil;
import com.sgm.dms.fol.common.service.utils.PropertiesUtil;
import com.sgm.dms.fol.common.service.utils.StringUtil;
import com.sgm.solution.framework.core.common.ServiceClient;
import com.sgm.solution.framework.core.common.UserTokenClient;
import com.sgm.solution.framework.core.common.UserTokenServiceBuilder;
import com.sgm.solution.framework.core.exception.FrameworkException;
import com.sgm.solution.framework.core.vo.TokenConfig;
import com.sgm.solution.framework.core.vo.webservice.UserTokenResponse;

/*
*
* @author s86yv7
* SGMTokenController to get token from token server
* @date 2015年10月26日
*/
@Controller
@RequestMapping("/services/tokens")
public class SGMTokenController {

//    private final String APP_ID = "app_id";
    
//    @Value("${token.properties.location}")
//    private String tokenPropertiesLocation;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody UserTokenResponse getToken(HttpServletRequest request) throws Throwable {
        // TODO get username from session etc
//        String userName = "edwin";
        //Properties properties = PropertiesUtils.loadPropertiesFromFile(SGMTokenController.class, "config/token.properties");
        String userName = CookieUtil.getAccount(request);
        String app_id= PropertiesUtil.getProperty("app_id");
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
}
