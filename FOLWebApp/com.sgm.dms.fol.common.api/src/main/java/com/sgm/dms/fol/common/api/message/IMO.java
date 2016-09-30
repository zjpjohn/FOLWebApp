package com.sgm.dms.fol.common.api.message;

import com.sgm.dms.fol.common.api.exception.MessageNotValidException;

import java.util.Map;

/**
*
*/
public interface IMO {


	boolean getNeedTrans();

	ISignMO trans2VO(Map<String,String> map) throws MessageNotValidException;

	void validateVO(ISignMO vo) throws MessageNotValidException ;

	ISignMO receiveMO(Map<String,String> map,ISignMO vo) throws MessageNotValidException;
	
}
