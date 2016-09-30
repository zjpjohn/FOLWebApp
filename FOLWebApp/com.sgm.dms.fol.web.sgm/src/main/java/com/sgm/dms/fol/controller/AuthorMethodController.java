
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : web.sgm
 *
 * @File name : AuthorMethodController.java
 *
 * @Author : wangfl
 *
 * @Date : 2016年3月31日
 *
 ----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2016年3月31日    wangfl    1.0
 *
 *
 *
 *
 ----------------------------------------------------------------------------------
 */
	
package com.sgm.dms.fol.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sgm.dms.fol.common.api.constants.CookieConstant;
import com.sgm.dms.fol.common.api.domain.AuthorMethodDTO;
import com.sgm.dms.fol.common.api.services.AuthorMethodService;
import com.sgm.dms.fol.common.service.utils.BeanMapper;
import com.sgm.dms.fol.common.service.utils.CookieUtil;
import com.sgm.dms.fol.common.service.utils.MapUtil;
import com.sgm.dms.fol.common.service.utils.RSAUtil;
import com.sgm.dms.fol.vo.AuthorMethodVo;
import com.sgm.dms.fol.vo.AuthorityVo;
import com.sgm.solution.framework.dataAuthority.annotations.SGMValidationRequest;
import com.sgm.solution.framework.dataAuthority.annotations.SGMValidationResource;
import com.sgm.solution.framework.dataAuthority.annotations.SGMValidationUserInfo;
import com.sgm.solution.framework.dataAuthority.services.Encryptor;

/**
 * @author wangfl
 * 方法权限信息Controller
 * @date 2016年3月31日
 */
@RestController
@RequestMapping("/authority/authormethod")
public class AuthorMethodController {
    
    @Autowired
    private AuthorMethodService authorMethodService;
    
    /**
     * 
     * @author wangfl
     * 查询方法权限信息列表
     * @date 2016年3月31日
     * @param authorMethodVo
     * @return
     * @throws Throwable
     */
    @RequestMapping("/query")
    public Object getAthoritymounts(@RequestBody AuthorMethodVo authorMethodVo) throws Throwable{
        //初始化数据
        AuthorMethodDTO authorMethodDTO = BeanMapper.map(authorMethodVo, AuthorMethodDTO.class);
        
        //查询列表
        if(StringUtils.isNotBlank(authorMethodDTO.getClazz())){
            authorMethodDTO.setClazzLike("%"+authorMethodDTO.getClazz()+"%");//按类名模糊查询
            authorMethodDTO.setClazz(null);
        }
        List<AuthorMethodDTO> authorMethodList = authorMethodService.getAuthorMethodList(authorMethodDTO);
        //批量转换成VO
        List<AuthorMethodVo> voList = BeanMapper.mapList(authorMethodList, AuthorMethodVo.class);
        
        //设置成前台需要的参数
        Map<String, Object> responsedata=MapUtil.setQueryDataToMap(voList, authorMethodService.getAuthorMethodListCount(authorMethodDTO));
        
        return responsedata;
    }
    
    /**
     * 
     * @author wangfl
     * 新增方法权限信息
     * @date 2016年4月1日
     * @param authorMethodVo
     * @param request
     * @return
     * @throws Throwable
     */
    @RequestMapping("/add")
    //@SGMValidationRequest
    public Object addAuthorMethod(@RequestBody AuthorMethodVo authorMethodVo, HttpServletRequest request, //@SGMValidationResource AuthorityVo vo,
                                  //@SGMValidationUserInfo 
                                  @CookieValue(CookieConstant.COOKIE_USER_ID) String userId) throws Throwable {
        //初始化数据
        AuthorMethodDTO authorMethodDTO = BeanMapper.map(authorMethodVo, AuthorMethodDTO.class);
   //     Long userId = CookieUtil.getUserId(request);
        authorMethodDTO.setCreateBy(Long.parseLong(RSAUtil.decryptByPrivateKey(userId)));

        //新增
        authorMethodService.addAuthorMethod(authorMethodDTO);
        
        return true;
    }
    
    /**
     * 
     * @author wangfl
     * 修改方法权限信息
     * @date 2016年4月1日
     * @param authorMethodVo
     * @param request
     * @return
     * @throws Throwable
     */
    @RequestMapping("/update")
    //@SGMValidationRequest
    public Object updateAuthorMethod(@RequestBody AuthorMethodVo authorMethodVo, HttpServletRequest request
    		//, @SGMValidationResource AuthorityVo vo,@SGMValidationUserInfo 
    		,@CookieValue(CookieConstant.COOKIE_USER_ID) String userId) throws Throwable {
        //初始化数据
        AuthorMethodDTO authorMethodDTO = BeanMapper.map(authorMethodVo, AuthorMethodDTO.class);
     //  Long userId = CookieUtil.getUserId(request);
        authorMethodDTO.setUpdateBy(Long.parseLong(RSAUtil.decryptByPrivateKey(userId)));
        
        //更新
        authorMethodService.updateAuthorMethod(authorMethodDTO);
        return true;
    }
    
    /**
     * 
     * @author wangfl
     * 删除方法权限信息
     * @date 2016年4月1日
     * @param authorMethodVo
     * @param request
     * @return
     * @throws Throwable
     */
    @RequestMapping("/delete")
    public Object delAuthorMethod(@Validated @RequestBody AuthorMethodVo authorMethodVo, HttpServletRequest request) throws Throwable {
        //删除角色
        authorMethodService.delAuthorMethodById(authorMethodVo.getMethodId());
        
        return true;
    }
    /*
     * 获取签名sign返回vo对象
    *
    * @author Lujinglei
    * TODO description
    * @date 2016年4月7日
    * @param authorMethodVo
    * @param request
    * @param userId
    * @return
    * @throws Throwable
     */
    @RequestMapping("/getSign")
    @ResponseBody
    public Object getSign(@RequestBody AuthorMethodVo authorMethodVo,HttpServletRequest request,@CookieValue(CookieConstant.COOKIE_USER_ID) String userId) throws Throwable{
        AuthorMethodVo result=new AuthorMethodVo();
        result.setMethodId(authorMethodVo.getMethodId());
        result.setUrl(authorMethodVo.getUrl());
        String[] refVales=new String[]{String.valueOf(authorMethodVo.getMethodId()),String.valueOf(authorMethodVo.getUrl())};
        String sign=Encryptor.generateSignFromResource(userId,refVales);
        result.setSign(sign);

        return result;
    }
}
