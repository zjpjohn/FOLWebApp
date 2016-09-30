
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : common.service
 *
 * @File name : XmlConverUtil.java
 *
 * @Author : wangfl
 *
 * @Date : 2016年8月8日
 *
 ----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2016年8月8日    wangfl    1.0
 *
 *
 *
 *
 ----------------------------------------------------------------------------------
 */

package com.sgm.dms.fol.common.service.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import net.sf.json.xml.XMLSerializer;

/**
 * XML转化其它格式工具类
 * 
 * @author wangfl
 * @date 2016年8月8日
 */

public class XmlConverUtil {

	/**
	 * XML字符串转json字符串
	 * @author wangfl
	 * @date 2016年8月8日
	 * @param xml
	 * @return
	 */
	public static String xmltoJson(String xmlStr) {
		if(null == xmlStr) return null;
			
		XMLSerializer xmlSerializer = new XMLSerializer();
		
		return xmlSerializer.read(xmlStr).toString().replaceAll("\\[\\]", "null");
	}
	
	/**
	 * XML输出流转json字符串
	 * @author wangfl
	 * @date 2016年8月8日
	 * @param xmlOutputStream
	 * @return
	 * @throws IOException 
	 */
	public static String xmltoJson(ByteArrayOutputStream xmlByteArrayOutputStream) throws IOException {
		StringBuffer xmlStr = new StringBuffer();
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(xmlByteArrayOutputStream.toByteArray());
		InputStreamReader inputStreamReader = new InputStreamReader(byteArrayInputStream, "gb2312");
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		String lineTxt = "";
		while ((lineTxt = bufferedReader.readLine()) != null) {
			xmlStr.append(lineTxt);
		}

		return xmltoJson(xmlStr.toString());
	}
	
	/**
	 * 从文件系统上下载XML，并转化为json字符串
	 * @author wangfl
	 * @date 2016年8月8日
	 * @param fileId
	 * @param token
	 * @return
	 * @throws IOException
	 */
	public static String xmltoJson(String fileId, String token) throws IOException {
		
		OutputStream xmlOutputStream = FileUtils.downLoadStream(fileId, token);
		
		if(xmlOutputStream instanceof ByteArrayOutputStream){
			return xmltoJson((ByteArrayOutputStream)xmlOutputStream);
		}
		
		return null;
	}

}
