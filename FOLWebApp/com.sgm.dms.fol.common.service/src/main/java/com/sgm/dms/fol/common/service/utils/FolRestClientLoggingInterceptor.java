
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : common.service
 *
 * @File name : FolRestClientHttpRequestInterceptor.java
 *
 * @Author : wangfl
 *
 * @Date : 2016年8月18日
 *
 ----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2016年8月18日    wangfl    1.0
 *
 *
 *
 *
 ----------------------------------------------------------------------------------
 */
	
package com.sgm.dms.fol.common.service.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map.Entry;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

/**
 * RESTFUL日志拦截器
 * @author wangfl
 * @date 2016年8月18日
 */
public class FolRestClientLoggingInterceptor implements ClientHttpRequestInterceptor {
	private Logger logger = LogManager.getLogger(this.getClass());

	/**
	 * @author wangfl
	 * @date 2016年8月18日
	 * @param request
	 * @param body
	 * @param execution
	 * @return
	 * @throws IOException
	 * (non-Javadoc)
	 * @see org.springframework.http.client.ClientHttpRequestInterceptor#intercept(org.springframework.http.HttpRequest, byte[], org.springframework.http.client.ClientHttpRequestExecution)
	 */

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
		
		//打印请求报文
		StringBuffer outboundMsg = new StringBuffer("\n-----------------------------Outbound Message start-------------------------------\n");
		outboundMsg.append("Method:" + request.getMethod()).append("\n");//request method
		outboundMsg.append("URI:" + request.getURI()).append("\n");//request url
		
		for (Entry<String, List<String>> header : request.getHeaders().entrySet()) {
			outboundMsg.append(header.getKey() + ":" + header.getValue()).append("\n");//request header
		}
		
		outboundMsg.append("body：" + new String(body)).append("\n");//request body
		outboundMsg.append("-------------------------------Outbound Message end-------------------------------");
		logger.info(outboundMsg);
		
		//执行请求
		BufferingClientHttpResponseWrapper clientHttpResponse = new BufferingClientHttpResponseWrapper(execution.execute(request, body));
		
		//打印响应报文
		StringBuffer inboundMsg = new StringBuffer("\n------------------------------Inbound Message start-------------------------------\n");
		inboundMsg.append("HttpStatus：" + clientHttpResponse.getRawStatusCode()).append("\t");//response statusCode
		inboundMsg.append(clientHttpResponse.getStatusText()).append("\n");//状态码对应的原因状语
		for (Entry<String, List<String>> header : clientHttpResponse.getHeaders().entrySet()) {
			inboundMsg.append(header.getKey() + ":" + header.getValue()).append("\n");//response header
		}
		inboundMsg.append("body：" + new String(clientHttpResponse.body)).append("\n");//response body
		inboundMsg.append("-------------------------------Inbound Message end--------------------------------");//response body
		logger.info(inboundMsg);
		
		//返回响应结果
		return clientHttpResponse;
	}
	
	/**
	 * Simple implementation of {@link ClientHttpResponse} that reads the response's body into memory,
     * thus allowing for multiple invocations of {@link #getBody()}.
	 * @author wangfl
	 * @date 2016年8月19日
	 */
	final class BufferingClientHttpResponseWrapper implements ClientHttpResponse {

		private final ClientHttpResponse response;

		private byte[] body;


		BufferingClientHttpResponseWrapper(ClientHttpResponse response) throws IOException {
			this.response = response;
			this.body = StreamUtils.copyToByteArray(this.response.getBody());
		}


		@Override
		public HttpStatus getStatusCode() throws IOException {
			return this.response.getStatusCode();
		}

		@Override
		public int getRawStatusCode() throws IOException {
			return this.response.getRawStatusCode();
		}

		@Override
		public String getStatusText() throws IOException {
			return this.response.getStatusText();
		}

		@Override
		public HttpHeaders getHeaders() {
			return this.response.getHeaders();
		}

		@Override
		public InputStream getBody() throws IOException {
			if(null == body) this.body = StreamUtils.copyToByteArray(this.response.getBody());
			return new ByteArrayInputStream(this.body);
		}

		@Override
		public void close() {
			this.response.close();
		}

	}

}
