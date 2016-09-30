/*
* Copyright 2015 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : common.service
*
* @File name : ResponseUtil.java
*
* @Author : shenweiwei
*
* @Date : 2015年10月23日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2015年10月23日    shenweiwei    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/
	
package com.sgm.dms.fol.common.service.utils;

import javax.servlet.http.HttpServletResponse;


/*
 *
 * @author shenweiwei
 * TODO description
 * @date 2015年10月23日
 */

public class ResponseUtil {
    public static void setResponseForCors(HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");//允许那些URL可以跨域请求到本域
        response.setHeader("Access-Control-Allow-Credentials", "true");//允许跨域请求带COOKIE
        response.setHeader("Access-Control-Allow-Methods", "POST,GET");
        response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type");//允许那些请求头可以跨域
    }
}
