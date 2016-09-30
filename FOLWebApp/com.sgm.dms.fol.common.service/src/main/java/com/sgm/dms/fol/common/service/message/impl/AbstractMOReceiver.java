package com.sgm.dms.fol.common.service.message.impl;

import com.sgm.dms.fol.common.api.exception.MessageNotValidException;
import com.sgm.dms.fol.common.api.message.IMO;
import com.sgm.dms.fol.common.api.message.IMessageValidationResolver;
import com.sgm.dms.fol.common.api.message.ISignMO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public abstract class AbstractMOReceiver implements IMO {

	@Autowired
	private IMessageValidationResolver IMessageValidationResolver;

	protected StringBuffer errorBuffer=new StringBuffer();

	private void basicValidation(ISignMO tempVo,IMessageValidationResolver messageValidationResolver){
		String result=messageValidationResolver.getValidationResult(tempVo);
		errorBuffer.append(result);
	}

	@Override
	public ISignMO receiveMO(Map<String,String> map,ISignMO vo) throws MessageNotValidException{
		ISignMO tempVo =null;
		try{
			if(getNeedTrans()){
				tempVo =  trans2VO(map);
			}else{
				tempVo=vo;
			}
			basicValidation(tempVo,IMessageValidationResolver);
			validateVO(tempVo);
		}catch(MessageNotValidException e){
			throw e;
		}
		if(!errorBuffer.toString().equals("")){
			throw new MessageNotValidException(errorBuffer.toString());
		}
		else{
			return tempVo;
		}
	};

}
