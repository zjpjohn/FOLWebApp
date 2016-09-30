/*
 * Copyright 2015 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : common.service
 *
 * @File name : FileUtils.java
 *
 * @Author : ZhangBao
 *
 * @Date : 2015年11月4日
 *
----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2015年11月4日    ZhangBao    1.0
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
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.sgm.solution.framework.core.common.ServiceClient;
import com.sgm.solution.framework.core.vo.DownloadConfig;
import com.sgm.solution.framework.core.vo.HttpCallResponse;
import com.sgm.solution.framework.core.vo.UploadConfig;
import com.sgm.solution.framework.core.vo.webservice.UserTokenResponse;
import com.sgm.solution.framework.file.common.DownLoadServiceBuilder;
import com.sgm.solution.framework.file.common.DownloadClient;
import com.sgm.solution.framework.file.common.UploadClient;
import com.sgm.solution.framework.file.common.UploadServiceBuilder;
import com.sgm.solution.framework.file.resource.vo.UserFileResponse;

/*
 *
 * @author ZhangBao
 * TODO description
 * @date 2015年11月4日
 */

public class FileUtils {

	/****
	 * 根据文件路径读取txt文件内容拆分成小文件
	 */
	public static final String writeFilePath = "http://dapi.saic-gm.com/fileservice/v1/files/";
	public static Logger logger = LoggerFactory.getLogger(FileUtils.class);

	// 文件上传
	public static String upload(String fileName, String tokenStr,
			ByteArrayInputStream input) {
		UserTokenResponse token = new UserTokenResponse();
		token.setToken(tokenStr);
		// InputStream s=new PipedInputStream(out);
		/*
		 * UploadClient client=new UploadClient(CommonController.class);
		 * UserFileResponse response=client.uploadFileWithStream(token,
		 * fileName, "sgm", s);
		 */
		UploadConfig config = UploadServiceBuilder.UPLOAD_CONFIG
				.get(UploadServiceBuilder.class.getName());
		UploadClient c = new ServiceClient.Builder<UploadClient, UploadServiceBuilder>()
				.loadBuilerConfig(UploadServiceBuilder.CONFIG_KEY, config)
				.loadBuilerConfig(UploadServiceBuilder.RUN_FILENAME, fileName)
				.loadBuilerConfig(UploadServiceBuilder.RUN_ISFILE,
						Boolean.FALSE)
				.loadBuilerConfig(UploadServiceBuilder.RUN_FILESTREAM, input)
				.loadBuilerConfig(UploadServiceBuilder.RUN_SOURCE, "sgm")
				.loadBuilerConfig(UploadServiceBuilder.RUN_TOKEN,
						token.getToken())
				.build(UploadClient.class, UploadServiceBuilder.class);
		UserFileResponse response = (UserFileResponse) c.getServiceResult();
		
		if (response != null) {
			return response.getFile_id();
		}
		return null;

	}

	/******
	 * 获取文件名
	 */
	public static String getFileNameByFilePath(String filePath) {
		if (StringUtils.isEmpty(filePath)) {
			return "";
		}
		File file = new File(filePath);
		return file.getName();
	}

	/****
	 * download
	 * @throws IOException 
	 */
	public static OutputStream downLoadStream(String fileId, String token) throws IOException{
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			HttpCallResponse response=null;
	        DownloadConfig config = DownLoadServiceBuilder.DOWNLOAD_CONFIG.get(DownloadClient.class.getName());
	        DownloadClient c = new ServiceClient.Builder<DownloadClient, DownLoadServiceBuilder>()
	                .loadBuilerConfig(DownLoadServiceBuilder.CONFIG_KEY, config)
	                .loadBuilerConfig(DownLoadServiceBuilder.RUN_FILEID, fileId)
	                .loadBuilerConfig(DownLoadServiceBuilder.RUN_TOKEN, token)
	                .loadBuilerConfig(DownLoadServiceBuilder.RUN_ISFILE,Boolean.FALSE)
	                .loadBuilerConfig(DownLoadServiceBuilder.RESULT_STREAM,outputStream)
	                .build(DownloadClient.class, DownLoadServiceBuilder.class);
		 response=(HttpCallResponse)c.getServiceResult();
		 return response.getOs();
		 
		} finally {
			if (outputStream != null) {
				outputStream.close();
			}
		}
		
		
	}
	
	 /*****
	   * down load file
	  */
	 public static void downLoadFIle(String fileId,String token){
		 DownloadConfig config = DownLoadServiceBuilder.DOWNLOAD_CONFIG.get(DownloadClient.class.getName());
		    DownloadClient c = new ServiceClient.Builder<DownloadClient, DownLoadServiceBuilder>()
		            .loadBuilerConfig(DownLoadServiceBuilder.CONFIG_KEY,config)
		            .loadBuilerConfig(DownLoadServiceBuilder.RUN_FILEID, fileId)
		            .loadBuilerConfig(DownLoadServiceBuilder.RUN_TOKEN,token)
		            .loadBuilerConfig(DownLoadServiceBuilder.RUN_ISFILE, Boolean.TRUE)
		            .loadBuilerConfig(DownLoadServiceBuilder.PROP_CONFIG_SAVE_BASE_URL,"e:\\")
		            .build(DownloadClient.class, DownLoadServiceBuilder.class);
		    HttpCallResponse response=(HttpCallResponse)c.getServiceResult();
		    if(null!=response){
		    	logger.info(response.getResponseStatus()+"");
		    }
		    //assertTrue(response.getResponseStatus() == 200);

	 }
	 
	 /**
	  * 
	  * 下载文件
	  *
	  * @author wangfl
	  * @version 
	  * @param fileId
	  * @param token
	  * @param savePath
	 * @throws IOException 
	  */
	public static void downLoadFIle(String fileId, String token, String savePath) throws IOException {
		if(!new File(savePath).exists()){
			new File(savePath).mkdirs();
		}
		
		DownloadConfig config = DownLoadServiceBuilder.DOWNLOAD_CONFIG.get(DownloadClient.class.getName());
		DownloadClient c = new ServiceClient.Builder<DownloadClient, DownLoadServiceBuilder>()
				.loadBuilerConfig(DownLoadServiceBuilder.CONFIG_KEY, config)
				.loadBuilerConfig(DownLoadServiceBuilder.RUN_FILEID, fileId)
				.loadBuilerConfig(DownLoadServiceBuilder.RUN_TOKEN, token)
				.loadBuilerConfig(DownLoadServiceBuilder.RUN_ISFILE, Boolean.TRUE)
				.loadBuilerConfig(DownLoadServiceBuilder.PROP_CONFIG_SAVE_BASE_URL, savePath)
				.build(DownloadClient.class, DownLoadServiceBuilder.class);
		HttpCallResponse response = (HttpCallResponse) c.getServiceResult();
		if (null != response) {
			logger.info(response.getResponseStatus() + "");
		}

	}

	// 下载文件内容
	public static List<String> parseFile(InputStream is) throws Exception {
		if (is != null) {
			BufferedReader br = null;
			try {
				br = new BufferedReader(new InputStreamReader(is, "GBK"));
				String row = null;
				List<String> record = new ArrayList<String>();
				while ((row = br.readLine()) != null) {
					record.add(row);
				}
				return record;
			} catch (IOException t) {
				logger.error("io is error");
				throw new Exception(t.getMessage());
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						logger.warn("Parse input stream close failed!", e);
					}
				}
				
					try {
						is.close();
					} catch (IOException e) {
						logger.warn("Parse input stream close failed!", e);
					
				}
			}
		}
		return null;
	}

}
