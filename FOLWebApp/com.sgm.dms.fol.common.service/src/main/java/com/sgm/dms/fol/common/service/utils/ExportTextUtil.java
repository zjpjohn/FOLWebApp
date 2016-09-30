
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : common.service
 *
 * @File name : ExportTextUtil.java
 *
 * @Author : wangfl
 *
 * @Date : 2016年8月12日
 *
 ----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2016年8月12日    wangfl    1.0
 *
 *
 *
 *
 ----------------------------------------------------------------------------------
 */
	
package com.sgm.dms.fol.common.service.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sgm.dms.fol.common.api.exception.ServiceBizException;

/**
 * 导出text文件
 * @author wangfl
 * @date 2016年8月12日
 */
@Component
public class ExportTextUtil {
	
	@Autowired
	private TokenUtils tokenUtils;
	
	public byte[] exportText(String content) throws Exception {
		String token = tokenUtils.getTokenStr();
		
		// 上传文件
		String fileName = UUID.randomUUID() + ".txt";
		ByteArrayInputStream inputStream = null;
		String filedId = null;
		try {
			inputStream = new ByteArrayInputStream(content.toString().getBytes("gb2312"));
			filedId = FileUtils.upload(fileName, token, inputStream);
		} catch (UnsupportedEncodingException e) {
			throw new ServiceBizException("文件上传失败", e);
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}

		// 下载文件流
		ByteArrayOutputStream outputStream = null;
		try {
			outputStream = (ByteArrayOutputStream) FileUtils.downLoadStream(filedId, token);
		} catch (Exception e) {
			throw new ServiceBizException("文件下载失败", e);
		} finally {
			if (outputStream != null) {
				outputStream.flush();
				outputStream.close();
			}
		}

		return outputStream.toByteArray();
	}

}
