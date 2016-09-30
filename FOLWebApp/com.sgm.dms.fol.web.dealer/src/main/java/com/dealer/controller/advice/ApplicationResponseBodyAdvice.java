/*
* Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : web.sgm
*
* @File name : ApplicationControllerAdvice.java
*
* @Author : DELL
*
* @Date : 2016年3月21日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2016年3月21日    DELL    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/
	
package com.dealer.controller.advice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.sgm.dms.fol.common.api.constants.StateConstant;
import com.sgm.dms.fol.common.api.domain.BaseDTO;
import com.sgm.dms.fol.common.api.domain.BaseVo;
import com.sgm.dms.fol.common.api.domain.MenuDTO;
import com.sgm.dms.fol.common.api.domain.PageVo;
import com.sgm.dms.fol.common.api.domain.ResponseDTO;

/** reponsebody封装
* @author DELL
* TODO description
* @date 2016年3月21日
*/
@Order(1)
@ControllerAdvice(annotations = Controller.class)
public class ApplicationResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    /*
    *
    * @author DELL
    * @date 2016年3月21日
    * @param returnType
    * @param converterType
    * @return
    * (non-Javadoc)
    * @see org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice#supports(org.springframework.core.MethodParameter, java.lang.Class)
    */
    
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> converterType) { 
        boolean flag=false;
        Class<?> returntype=methodParameter.getMethod().getReturnType();
        if(returntype.isAssignableFrom(List.class)
                ||returntype.isAssignableFrom(Map.class)
                ||returntype.isAssignableFrom(Boolean.class)
                ||returntype.isAssignableFrom(BaseDTO.class)
                ||Number.class.isAssignableFrom(returntype)
                ||returntype.isAssignableFrom(PageVo.class)
                ||BaseVo.class.isAssignableFrom(returntype)){
            flag=true;
        }
        return flag;  
    }  
    
    /*
    *
    * @author DELL
    * @date 2016年3月21日
    * @param body
    * @param returnType
    * @param selectedContentType
    * @param selectedConverterType
    * @param request
    * @param response
    * @return
    * (non-Javadoc)
    * @see org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice#beforeBodyWrite(java.lang.Object, org.springframework.core.MethodParameter, org.springframework.http.MediaType, java.lang.Class, org.springframework.http.server.ServerHttpRequest, org.springframework.http.server.ServerHttpResponse)
    */
    @SuppressWarnings("unchecked")
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        ResponseDTO responsedto=new ResponseDTO();
        responsedto.setState(StateConstant.SUCCESS);
        List<Object> list=new ArrayList<>();//包裹参数
        if(body==null){
            return body;
        }else if(body instanceof List<?>){
            List<Object> responsebody=(List<Object>)body;
            
            //query code
            if(!responsebody.isEmpty()&&responsebody.get(0) instanceof MenuDTO){
                return responsebody;
            }else{
                list.add(responsebody);
                list.add(responsedto);
                return list;
            }
            
        }else if(body instanceof Map<?,?>){            
            Map<?,?> responsebody=(Map<?,?>)body;   
            list.add(responsebody);
            list.add(list.size(), responsedto);
            return list;
        }else if(body instanceof Boolean){
            return responsedto;
        }else if(body instanceof ResponseDTO){
    		return body;
    	}else if(body instanceof BaseDTO || body instanceof Number|| body instanceof PageVo|| body instanceof BaseVo){
        	
            list.add(body);
            list.add(responsedto);
            return list;
        }
        
        return body;
    }
    
    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前把返回值放入Model
     */
//    @ModelAttribute  
//    public Map<String, Object> newUser() {  
//        Map<St`ring, Object> map=new HashMap<>();
//        map.put("rows", null);
//        map.put("total", null);
//        return map;  
//    }  
  
    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     */
//    @InitBinder  
//    public void initBinder(WebDataBinder binder) {
//    }  
    
}
