package com.sgm.dms.fol.common.api.exception;
/*
 * 消息内容错误或不合法，只在rest层调用
 * 应用逻辑不做捕捉与处理，只有框架捕捉并处理
 */
public class MessageNotValidException extends RuntimeException {

	private static final long serialVersionUID = -5526463365254777876L;

	public MessageNotValidException(String msg) {
		super(msg);
	}
}
