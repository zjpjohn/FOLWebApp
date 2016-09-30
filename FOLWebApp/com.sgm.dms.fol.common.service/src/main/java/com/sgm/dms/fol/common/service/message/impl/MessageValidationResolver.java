package com.sgm.dms.fol.common.service.message.impl;

import com.sgm.dms.fol.common.api.message.IMessageValidationResolver;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Iterator;
import java.util.Set;

@Service
public class MessageValidationResolver implements IMessageValidationResolver {

    public String getValidationResult(Object obj) {

    	StringBuffer buffer = new StringBuffer(1024);  

		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    	Set<ConstraintViolation<Object>> constraintViolations =validator.validate(obj);
    	Iterator<ConstraintViolation<Object>> iter = constraintViolations.iterator();  
    	while (iter.hasNext()) {  
    		String message = iter.next().getMessage();  
    		buffer.append(message);
    		buffer.append("\n");
    	}  
    	return buffer.toString();  
    }
    
}
