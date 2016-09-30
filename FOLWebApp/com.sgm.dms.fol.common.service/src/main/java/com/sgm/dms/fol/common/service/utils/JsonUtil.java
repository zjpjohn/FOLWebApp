/*
* Copyright 2015 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : common.service
*
* @File name : JsonUtil.java
*
* @Author : shenweiwei
*
* @Date : 2015年10月15日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2015年10月15日    shenweiwei    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/
	
package com.sgm.dms.fol.common.service.utils;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;


/*
 *
 * @author shenweiwei
 * TODO description
 * @date 2015年10月15日
 */

public class JsonUtil {
    
    private static ObjectMapper objectMapper=new ObjectMapper();;
    /**
     * List转换为Json字符串
     * @return
     * @throws IOException 
     */
    public static String listToJsonString (List<?> list) throws IOException{
        StringWriter sw = new StringWriter();
        JsonGenerator json = new JsonFactory().createJsonGenerator(sw);
        objectMapper.writeValue(json, list);
        String jsonString=sw.toString();
        sw.close();
        json.close();
        return jsonString;
    }
    
    /**
     * bean转换为Json字符串
     * @return
     * @throws IOException 
     */
    public static String objToJson (Object obj) throws IOException{
        StringWriter sw = new StringWriter();
        JsonGenerator json = new JsonFactory().createJsonGenerator(sw);
        objectMapper.writeValue(json, obj);
        String jsonString=sw.toString();
        sw.close();
        json.close();
        return jsonString;
    }
    
    /**
     * 字符串转换成bean
     */
    public static <T> T jsonToBean(String jsonParams,Class<T> beanClass) throws IOException{
        T dataReportVo=objectMapper.readValue(jsonParams, beanClass);
        return dataReportVo;
    }
    
    
}
