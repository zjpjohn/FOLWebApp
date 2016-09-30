/*
* Copyright 2015 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : common.service
*
* @File name : TractionUtils.java
*
* @Author : ZhangBao
*
* @Date : 2015年11月24日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2015年11月24日    ZhangBao    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/
	
package com.sgm.dms.fol.common.service.utils;

import java.io.UnsupportedEncodingException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/*
 *
 * @author ZhangBao
 * base64 加密  解密
 * @date 2015年11月24日
 */

@SuppressWarnings("restriction")
public class Base64Utils {
	
	//设置编码
	private static final String CHAR_SET="UTF-8";
	// 加密
	public static String encode(String str) {
		byte[] b = null;
		String s = null;
		try {
			b = str.getBytes(CHAR_SET);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (b != null) {
			s = new BASE64Encoder().encode(b);
		}
		return s;
	}

	// 解密
	public static String decode(String s) {
		byte[] b = null;
		String result = null;
		if (s != null) {
			BASE64Decoder decoder = new BASE64Decoder();
			try {
				b = decoder.decodeBuffer(s);
				result = new String(b, CHAR_SET);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	

}
