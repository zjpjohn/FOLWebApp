
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : common.service
 *
 * @File name : DataAuthoritySignController.java
 *
 * @Author : wangfl
 *
 * @Date : 2016年6月28日
 *
 ----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2016年6月28日    wangfl    1.0
 *
 *
 *
 *
 ----------------------------------------------------------------------------------
 */

package com.dealer.controller;

import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sgm.dms.fol.common.api.constants.CookieConstant;
import com.sgm.dms.fol.common.api.constants.StateConstant;
import com.sgm.dms.fol.common.api.domain.ResponseDTO;
import com.sgm.dms.fol.common.service.controller.impl.BaseController;
import com.sgm.solution.framework.dataAuthority.annotations.SGMValidationFieldRefIds;
import com.sgm.solution.framework.dataAuthority.annotations.SGMValidationResource;
import com.sgm.solution.framework.dataAuthority.services.Encryptor;
import com.sgm.solution.framework.urlaccess.annotations.ValidationRequestUrl;


/**
 * 敏感数据防篡改getSign公共Controller
 * @author wangfl
 * @date 2016年7月7日
 */
@Controller
@RequestMapping
public class DataAuthoritySignController extends BaseController {
	
	@Autowired
	private RequestMappingHandlerMapping requestMappingHandlerMapping;
	
	private static Map<MethodIdentify,Method> uriMethodMap;//uri和method的映射Map，静态，单例

	/**
	 * 防篡改签名获取（请求Vo放到方法的第一个参数）
	 * @author wangfl
	 * @date 2016年6月28日
	 * @param objectVo
	 * @param request
	 * @param userId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/**/getSign", method = RequestMethod.POST, consumes = "application/json", produces = "application/json;charset=UTF-8")
	@ValidationRequestUrl
	public void getSign(@RequestBody String objectVoStr, HttpServletRequest request, HttpServletResponse response,@CookieValue(CookieConstant.COOKIE_USER_ID) String userId) throws Exception {
		//结果list
		List<Object> resultList = new ArrayList<Object>();
		
		//请求参数校验
		if(objectVoStr == null || "".equals(objectVoStr)) throw new Exception("请求参数不能为空");
		
		//截取请求URI
		String requestURI = request.getRequestURI().substring(request.getContextPath().length(),request.getRequestURI().indexOf("/getSign"));
		
		//获取请求http方法类型
		String methodType = request.getMethod();
		
		//根据请求URI获取请求的Controller方法
		Method method = uriToMethod(new MethodIdentify(requestURI, methodType.toUpperCase()));
		
		if(null == method) throw new Exception("URI对应的方法找不到");
		
		//方法参数类型列表
		Class<?>[] parameterTypes = method.getParameterTypes();
		
		if(null == parameterTypes || parameterTypes.length == 0) throw new Exception("防篡改的方法无参数");
		
		//注意：暂时默认把请求值赋值到方法的第一个添加@SGMValidationResource的参数上
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		
		//获取需要防篡改的参数类型列表
		List<Class<?>> vaLidParameterTypes = new ArrayList<Class<?>>();
		Class<?> vaLidParameterType = null;//要验证的Vo参数类型
		for (int i = 0; i < parameterTypes.length; i++) {
			Annotation[] annotations = method.getParameterAnnotations()[i];
			if(annotations != null){
				for (Annotation annotation : annotations) {
					if(annotation instanceof SGMValidationResource){
						vaLidParameterTypes.add(parameterTypes[i]);
						vaLidParameterType = vaLidParameterTypes.get(0);//防篡改Vo
						if(vaLidParameterType.isAssignableFrom(List.class)){
							Type type = method.getGenericParameterTypes()[i];
							ParameterizedType pt = (ParameterizedType)type;
							Class<?> actualTypeClazz = (Class<?>)pt.getActualTypeArguments()[0];
							vaLidParameterType = actualTypeClazz;
						}
						break;
					}
				}
			}
		}
		
		if(0 == vaLidParameterTypes.size()) throw new Exception("防篡改的方法没有添加注解@SGMValidationResource");
		
		
		
		//请求json字符串转json对象
		JSONArray resultArray = new JSONArray();
		if (objectVoStr.startsWith("{")) {
			JSONObject objectVoJson = new JSONObject(objectVoStr);
			objectVoJsonAddSign(userId, vaLidParameterType, objectVoJson);
			resultList.add(0, objectVoJson.toString());
			resultArray.put(objectVoJson);
		} else if (objectVoStr.startsWith("[")) {
			JSONArray objectVoArray = new JSONArray(objectVoStr);
			for (int i = 0; i < objectVoArray.length(); i++) {
				JSONObject jsonObject = (JSONObject) objectVoArray.get(i);
				objectVoJsonAddSign(userId, vaLidParameterType, jsonObject);
			}
			resultArray.put(objectVoArray);
			resultList.add(0, objectVoArray.toString());
		} else {
			throw new Exception("请求参数不是json格式");
		}
		
		
		
		
		//组装响应对象
		ResponseDTO responsedto=new ResponseDTO();
        responsedto.setState(StateConstant.SUCCESS);
        resultList.add(responsedto);
        resultArray.put( new JSONObject(responsedto));
        PrintWriter writer = response.getWriter();
        writer.write(resultArray.toString());
        writer.flush();
        writer.close();
	}

	
	/**
	 * 根据请求uri获取请求的Controller方法
	 * @author wangfl
	 * @date 2016年6月28日
	 * @param uri
	 * @return
	 */
	private Method uriToMethod(MethodIdentify methodIdentify) {
		if(null == uriMethodMap){
			uriMethodMap = new HashMap<MethodIdentify,Method>();
			Map<RequestMappingInfo, HandlerMethod> handlerMethodMap = requestMappingHandlerMapping.getHandlerMethods();
			for (Entry<RequestMappingInfo, HandlerMethod> handlerMethodMapTemp : handlerMethodMap.entrySet()) {
				Method method = handlerMethodMapTemp.getValue().getMethod();
				RequestMethodsRequestCondition methodsCondition = handlerMethodMapTemp.getKey().getMethodsCondition();
				Set<RequestMethod> methods = methodsCondition.getMethods();
				
				PatternsRequestCondition patternsCondition = handlerMethodMapTemp.getKey().getPatternsCondition();
				Set<String> patterns = patternsCondition.getPatterns();
				
				for (String pattern : patterns) {
					if (methods.size() == 0) {
						uriMethodMap.put(new MethodIdentify(pattern, null), method);
						continue;
					}
					
					for (RequestMethod requestMethod : methods) {
						uriMethodMap.put(new MethodIdentify(pattern, requestMethod.toString().toUpperCase()), method);
					}
				}
			}
		}
		
		return uriMethodMap.get(methodIdentify) == null ? uriMethodMap.get(new MethodIdentify(methodIdentify.getUri(), null)) : uriMethodMap.get(methodIdentify);
	}
	
	/**
	 * Json对象中添加签名sign
	 * @author wangfl
	 * @date 2016年7月7日
	 * @param userId
	 * @param vaLidParameterType
	 * @param objectVoJson
	 * @throws Exception
	 */
	private void objectVoJsonAddSign(String userId, Class<?> vaLidParameterType, JSONObject objectVoJson)
			throws Exception {
		Set<?> keySet = objectVoJson.keySet();
		
		//获取要验证的Vo属性列表
		Field[] declaredFields = vaLidParameterType.getDeclaredFields();
		
		//TreeMap，对篡改的字段按SGMValidationFieldRefIds注解的seq属性排序。
		Map<Long, String> validateFieldMap = new TreeMap<Long, String>(new Comparator<Long>() {
			@Override
			public int compare(Long key1, Long key2) {
				return (int)(key1 - key2);
			}
		});
		
		if(null != declaredFields){
			for (Field field : declaredFields) {
				if(field.isAnnotationPresent(SGMValidationFieldRefIds.class)){
					String tempValue = keySet.contains(field.getName()) && objectVoJson.get(field.getName())!= null ? objectVoJson.get(field.getName()).toString() : "";
					validateFieldMap.put(((SGMValidationFieldRefIds)field.getAnnotation(SGMValidationFieldRefIds.class)).seq(), "null".equals(tempValue) ? "" : tempValue);
				}
			}
			
			Collection<String> values = validateFieldMap.values();
			
			String[] validateFieldArray = new String[values.size()];
			int i = 0;
			
			for (String value : values) {
				validateFieldArray[i++] = value;
			}
			
			//把带有SGMValidationFieldRefIds字段的属性防篡改
			String sign=Encryptor.generateSignFromResource(userId,validateFieldArray);
			
			//在sign属性中放入加密签名
			objectVoJson.put("sign", sign);
		}
	}
	
	/**
	 * Controller方法的唯一标识类 
	 *     请求uri和请求RequestMethod可以唯一标识一个Controller方法
	 * @author wangfl
	 * @date 2016年7月7日
	 */
	public static final class MethodIdentify{
		private String uri;//uri
		private String methodType;//HTTP请求方法类型
		
		public MethodIdentify(String uri, String methodType) {
			this.uri = uri;
			this.methodType = methodType;
		}
		
		public String getUri() {
			return uri;
		}
		public void setUri(String uri) {
			this.uri = uri;
		}
		public String getMethodType() {
			return methodType;
		}
		public void setMethodType(String methodType) {
			this.methodType = methodType;
		}
		
		@Override
		public int hashCode() {
			return Objects.hash(uri, methodType);
		}
		
		@Override
		public boolean equals(Object otherObject) {
			if(otherObject == null) return false;
			
			if(MethodIdentify.class != otherObject.getClass()) return false;
			
			MethodIdentify tempOtherObject = (MethodIdentify)otherObject;
			
			return Objects.equals(uri, tempOtherObject.getUri()) && Objects.equals(methodType, tempOtherObject.getMethodType());
		}
		
	}
	
}
