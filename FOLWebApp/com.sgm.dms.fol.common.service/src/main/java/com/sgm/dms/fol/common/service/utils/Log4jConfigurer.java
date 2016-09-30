/**
* @ClassName: Log4jConfigurer
* @Description: TODO
* @author: ChenChong
* @date: 2015年7月9日 上午5:28:46
* 
* 
*/
package com.sgm.dms.fol.common.service.utils;

import java.net.URL;

import org.apache.commons.lang3.SystemUtils;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.stereotype.Component;

@Component
public class Log4jConfigurer {

	private String linux;
	private String windows;
	
	public void init() {
		URL url = null;
		if(SystemUtils.IS_OS_LINUX) {
			url = getUrl(linux);
		} else if(SystemUtils.IS_OS_WINDOWS) {
			url = getUrl(windows);
		}
		PropertyConfigurator.configure(url);
	}

	public String getLinux() {
		return linux;
	}

	public void setLinux(String linux) {
		this.linux = linux;
	}

	public String getWindows() {
		return windows;
	}

	public void setWindows(String windows) {
		this.windows = windows;
	}

	private URL getUrl(String relativePath) {
		return Thread.currentThread().getContextClassLoader().getResource(relativePath);
	}
}