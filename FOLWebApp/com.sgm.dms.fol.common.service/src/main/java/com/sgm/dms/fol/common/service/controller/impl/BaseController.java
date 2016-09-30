package com.sgm.dms.fol.common.service.controller.impl;

import com.sgm.dms.fol.common.api.constants.StateConstant;
import com.sgm.dms.fol.common.api.domain.ResponseDTO;
import com.sgm.dms.fol.common.api.exception.MessageNotValidException;
import com.sgm.dms.fol.common.api.exception.ServiceAppException;
import com.sgm.dms.fol.common.api.exception.ServiceBizException;
import com.sgm.dms.fol.common.api.exception.VoNotValidException;
import com.sgm.solution.framework.dataAuthority.commons.DataAuthorityException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

/**
 * Controller基类
 * @author wangfl
 * @date 2016年8月26日
 */
public abstract class BaseController {
	private Logger logger = LogManager.getLogger(this.getClass());
	
	/**
     * 
     * @author wangfl
     * springmvc Valid注解方式 参数验证错误信息处理
     *                注意：本方法以后遗弃，建议采用本类中的方法validateVo(Errors result)进行SpringMVC参数校验错误处理
     * @date 2016年3月21日
     * @param errors
     * @return
     */
	@Deprecated
    protected String getErrorMessages(List<ObjectError> errors) {
        StringBuffer buffer = new StringBuffer();
        for(ObjectError error:errors) {
            if(buffer.length() > 0)
                buffer.append("；");
            buffer.append(error.getDefaultMessage());
        }
        logger.error("请求参数校验错误："+buffer.toString());
        return buffer.toString();
    }
    
    /**
     * springmvc Valid注解方式 参数验证错误信息处理
     * @author wangfl
     * @date 2016年8月9日
     * @param result
     */
	protected void validateVo(Errors result) {

		if (!result.hasErrors()) return;//没错，直接返回。

		StringBuffer buffer = new StringBuffer();
		
		List<ObjectError> errors = result.getAllErrors();
		
		for (ObjectError error : errors) {
			if (buffer.length() > 0) buffer.append("；");
			buffer.append(error.getDefaultMessage());
		}
		
		throw new VoNotValidException(buffer.toString());//有错，抛出VoNotValidException异常。
	}

	
    /////////////////////////////////////统一异常处理start////////////////////////////////////
    /**
     * 消息内容错误或不合法异常
     */
    @ExceptionHandler(MessageNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    protected ResponseDTO processMessageValidationException(MessageNotValidException ex) {
    	logger.error("消息内容错误或不合法异常", ex);
        return new ResponseDTO(StateConstant.DATA_ERROR, ex.getMessage());
    }

    /**
     * 内部业务异常
     */
    @ExceptionHandler(ServiceBizException.class)
    @ResponseStatus(HttpStatus.NOT_EXTENDED)
    @ResponseBody
    protected ResponseDTO processServiceBizExp(ServiceBizException ex) {
    	logger.error("内部业务异常", ex);
        return new ResponseDTO(StateConstant.DATA_ERROR, ex.getMessage());
    }


    /**
     * 内部应用异常
     */
    @ExceptionHandler(ServiceAppException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    protected ResponseDTO processServiceAppExp(ServiceAppException ex) {
    	logger.error("内部应用异常", ex);
        return new ResponseDTO(StateConstant.DATA_ERROR, "内部应用异常");
    }
    
    /**
     * HTTP异常
     */
    @ExceptionHandler(HttpClientErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    protected ResponseDTO processHttpExp(HttpClientErrorException ex) {
    	logger.error("HTTP异常", ex);
        return new ResponseDTO(StateConstant.HTTP_ERROR, "访问出错，请刷新页面");
    }
    
    /**
     * SQL异常
     */
    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    protected ResponseDTO processSQLExp(SQLException ex) {
    	logger.error("SQL异常", ex);
        return new ResponseDTO(StateConstant.DATA_ERROR, "数据库操作出错");
    }
    
    /**
     * 权限验证异常
     */    
    @ExceptionHandler(DataAuthorityException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    protected ResponseDTO processDataAuthorityException(DataAuthorityException ex) {
    	logger.error("权限验证异常", ex);
        return new ResponseDTO(StateConstant.DATA_ERROR, ex.getMessage());
    }
    
    /**
     * 文件处理异常
     */    
    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    protected ResponseDTO processIOException(IOException ex) {
    	logger.error("文件处理异常", ex);
        return new ResponseDTO(StateConstant.DATA_ERROR, "文件处理异常");
    }
    
    /** 
     * VO请求参数不合法异常
     */
    @ExceptionHandler(VoNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    protected ResponseDTO processVoNotValidException(VoNotValidException ex) {
    	logger.error("VO请求参数不合法异常", ex);
        return new ResponseDTO(StateConstant.DATA_ERROR, ex.getMessage());
    }
    
    /** 
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    protected ResponseDTO processException(Exception ex){
    	logger.error("系统异常", ex);
    	return new ResponseDTO(StateConstant.APP_ERROR, "系统异常");
    }
	//////////////////////////////////////// 统一异常处理end////////////////////////////////////
}
