/**
* @ClassName: SystemConfigurer
* @Description: TODO
* @author: ChenChong
* @date: 2015年7月9日 上午7:22:30
* 
* 
*/
package com.sgm.dms.fol.common.service.utils;

import java.net.URL;

import org.apache.commons.lang3.SystemUtils;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.stereotype.Component;

@Component
public class SystemConfigurer {
	private String linuxUploadPath;
	private String linuxGwmFtpPath;
	private String linuxLog4jPath;
	private String linuxTempPath;
	private String windowsUploadPath;
	private String windowsLog4jPath;
	private String windowsGwmFtpPath;
	private String windowsTempPath;
	private String uploadPath;
	private String gwmFtpPath;
	private String tempPath;
	
	//aaaa

	
	
	//bbb
	public void init() {
		URL url = null;
		if(SystemUtils.IS_OS_LINUX) {
			url = getUrl(linuxLog4jPath);
			uploadPath = linuxUploadPath;
			gwmFtpPath=linuxGwmFtpPath;
			tempPath = linuxTempPath;
		} else if(SystemUtils.IS_OS_WINDOWS) {
			url = getUrl(windowsLog4jPath);
			uploadPath = windowsUploadPath;
			gwmFtpPath = windowsGwmFtpPath;
			tempPath = windowsTempPath;
		}
		PropertyConfigurator.configure(url);
	}
	
	public String getUploadPath() {
		return uploadPath;
	}
	public String getTempPath() {
		return tempPath;
	}
	public String getGwmFtpPath() {
		return gwmFtpPath;
	}

	public void setLinuxUploadPath(String linuxUploadPath) {
		this.linuxUploadPath = linuxUploadPath;
	}
	public void setLinuxLog4jPath(String linuxLog4jPath) {
		this.linuxLog4jPath = linuxLog4jPath;
	}
	public void setWindowsUploadPath(String windowsUploadPath) {
		this.windowsUploadPath = windowsUploadPath;
	}
	public void setWindowsLog4jPath(String windowsLog4jPath) {
		this.windowsLog4jPath = windowsLog4jPath;
	}

	public void setGwmFtpPath(String gwmFtpPath) {
		this.gwmFtpPath = gwmFtpPath;
	}

	public void setWindowsGwmFtpPath(String windowsGwmFtpPath) {
		this.windowsGwmFtpPath = windowsGwmFtpPath;
	}

	public void setLinuxGwmFtpPath(String linuxGwmFtpPath) {
		this.linuxGwmFtpPath = linuxGwmFtpPath;
	}

	public void setLinuxTempPath(String linuxTempPath) {
		this.linuxTempPath = linuxTempPath;
	}

	public void setWindowsTempPath(String windowsTempPath) {
		this.windowsTempPath = windowsTempPath;
	}

	private URL getUrl(String relativePath) {
		return Thread.currentThread().getContextClassLoader().getResource(relativePath);
	}
}