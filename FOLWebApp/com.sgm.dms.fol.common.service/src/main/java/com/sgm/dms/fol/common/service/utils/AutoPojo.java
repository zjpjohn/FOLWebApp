package com.sgm.dms.fol.common.service.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * 自动类翻译
 * 
 * @modify
 * 
 * @author lei.fang
 * @create 2014-07-11
 */
public class AutoPojo {

	private static Logger logger = LoggerFactory.getLogger(AutoPojo.class);

	/**
	 * @param clazz
	 *            反射类全名
	 * @param att
	 *            操作的属性(第一个字母需要大写)
	 * @param value
	 *            设置的值
	 * */
	public void setter(String clazz, String att, Object value) {
		try {
			Object obj = Class.forName(clazz).newInstance();
			obj.getClass().getMethod("set" + att, value.getClass())
					.invoke(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param clazz
	 *            反射类全名
	 * @param att
	 *            操作的属性(第一个字母需要大写)
	 * */
	public Object getter(String clazz, String att) {
		Object value = null;
		try {
			Object obj = Class.forName(clazz).newInstance();
			value = obj.getClass().getMethod("get" + att);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	

	/**
	 * 绑定参数到POJO
	 * 
	 * @param <T>
	 * @param request
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T bindRequestParam(HttpServletRequest request,
			Class<T> clazz) {

		try {

			String jsonParams = (String) request.getParameter("jsonparams");
			if (!StringUtils.isEmpty(jsonParams)) {
			
			} else {

				T bean = clazz.newInstance();
				// request attribute
				logger.debug("request paramters :");
				for (Field field : Mirror.getAllFields(clazz)) {

					String fieldName = field.getName();
					logger.debug("-----fieldName:" + fieldName);
					Object value = null;
					if (field.getType().isArray()) {// 如果是数组参数
						value = request.getParameterValues(fieldName);
					} else if ((field.getType()) == List.class) {// 如果是数组参数
						value = request.getParameterValues(fieldName);
					} else {
						value = request.getParameter(fieldName);
					}
					if (value != null && !StringUtils.isEmpty(value)) {
						try {
							value = Mirror.parseObj(field.getType(), value);
							Method method = clazz.getMethod("set"
									+ toUpperCaseFirstOne(fieldName),
									field.getType());
							method.invoke(bean, value);

							logger.debug(fieldName + ":" + value + ",");
						} catch (Exception e) {

							e.printStackTrace();
							logger.error("属性设置失败,Name:" + fieldName + "Type:"
									+ field.getType() + "value:" + value, e);
						}
					}
				}

				return bean;
			}

		} catch (InstantiationException e) {
			e.printStackTrace();
			logger.error("绑定参数到POJO失败", e);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			logger.error("绑定参数到POJO失败", e);
		}
		logger.debug("绑定参数到POJO失败！");
		return null;
	}

	/**
	 * 首字母转大写 actDt -> AckDt
	 * 
	 * @author guopeng.li
	 * @param s
	 * @return
	 */
	private static String toUpperCaseFirstOne(String s) {
		if (Character.isUpperCase(s.charAt(0)))
			return s;
		else
			return (new StringBuilder())
					.append(Character.toUpperCase(s.charAt(0)))
					.append(s.substring(1)).toString();
	}

}