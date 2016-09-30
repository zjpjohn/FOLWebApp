
package com.sgm.dms.fol.common.service.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 
 * PropertiesUtil.java
 *
 * @desc properties 资源文件解析工具
 * @author wangyx
 * @datatime Apr 7, 2015 3:58:45 PM
 *
 */
public class PropertiesUtil {

	private static Properties props = null;

	// public PropertiesUtil() {
	// if(ApplicationContextUtils.getBeanById("propertyConfigurer") instanceof
	// Properties){
	// props=(Properties)ApplicationContextUtils.getBeanById("propertyConfigurer");
	// }
	// }

	public static Map<String, String> getAllParamsProperty() {
		Map<String, String> propertiesMap = new HashMap<String, String>();
		if (props == null) {
			props = (Properties) ApplicationContextUtils.getBeanById("propertyConfigurer");
		}

		Enumeration enu = props.propertyNames();
		while (enu.hasMoreElements()) {
			String key = (String) enu.nextElement();
			String value = props.getProperty(key);
			propertiesMap.put(key, value);
		}
		return propertiesMap;
	}

	/**
	 * 获取某个属性
	 */
	public static String getProperty(String key) {
		Map<String, String> propertiesMap = getAllProperty();
		// if(propertiesMap!=null){
		return propertiesMap.get(key);
		// }
		// return null;
	}

	/**
	 * 获取所有属性 可以试试props.putAll(t)
	 */
	private static Map<String, String> getAllProperty() {
		// PropertiesUtil propertiesUtil=new PropertiesUtil();
		if (props == null) {
			props = (Properties) ApplicationContextUtils.getBeanById("propertyConfigurer");
		}

		Map<String, String> propertiesMap = new HashMap<String, String>();
		Enumeration enu = props.propertyNames();
		while (enu.hasMoreElements()) {
			String key = (String) enu.nextElement();
			String value = props.getProperty(key);
			propertiesMap.put(key, value);
		}
		return propertiesMap;
	}
	
	/****
	 * 读取文件url
	 * @throws IOException 
	 */
	public static String getServiceURL(String servicePropertityName) throws IOException{
		String filePath = getProperty("service.propertity.name");
		File file=new File(filePath);
		
		String lineTxt ="";
		BufferedReader read=null;
		try {
			read=new BufferedReader(new FileReader(file));
			while ((lineTxt = read.readLine())!=null) {
				String [] str=lineTxt.split("=");
				if(servicePropertityName.equals(str[0])){
					return str[1];
				}
			}
		} catch (IOException e) {
			throw new IOException("读取文件异常:"+e);
		}finally{
			try {
				if(null != read){
					read.close();
				}
			} catch (IOException e) {
				throw new IOException("读取文件错误");
			}
		}
		return "";
	}
	
	
}
