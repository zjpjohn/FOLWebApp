package com.sgm.dms.fol.common.service.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

/**
 * 
 * Title: Class ZipUtil.java
 * Description:zip工具类
 *
 *
 * @author wangfl
 * @version 0.0.1
 */
public class ZipUtil {
	private static final Logger LOGGER = LogManager.getLogger(ZipUtil.class);
	
	/**
	 * 使用GBK编码可以避免压缩中文文件名乱码
	 */
	private static final String CHINESE_CHARSET = "gbk";

	/**
	 * 文件读取缓冲区大小
	 */
	private static final int CACHE_SIZE = 1024;

	/**
	 * <p>
	 * 压缩文件
	 * </p>
	 * 
	 * @param sourceFolder
	 *            压缩文件夹
	 * @param zipFilePath
	 *            压缩文件输出路径
	 * @throws Exception
	 */
	public static void zip(String sourceFolder, String zipFilePath) {
		OutputStream out = null;
		BufferedOutputStream bos = null;
		ZipOutputStream zos = null;
		try {
			out = new FileOutputStream(new File(zipFilePath));
			bos = new BufferedOutputStream(out);
			zos = new ZipOutputStream(bos);
			// 解决中文文件名乱码
			zos.setEncoding(CHINESE_CHARSET);
			File file = new File(sourceFolder);
			String basePath = null;
			if (file.isDirectory()) {
				basePath = file.getPath();
			} else {
				basePath = file.getParent();
			}
			zipFile(file, basePath, zos);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally{
			if(zos != null){
				try {
					zos.closeEntry();
					zos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(bos != null){
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(out != null){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * <p>
	 * 递归压缩文件
	 * </p>
	 * 
	 * @param parentFile
	 * @param basePath
	 * @param zos
	 * @throws Exception
	 */
	private static void zipFile(File parentFile, String basePath, ZipOutputStream zos) {
		File[] files;
		if (parentFile.isDirectory()) {
			files = parentFile.listFiles();
		} else {
			files = new File[1];
			files[0] = parentFile;
		}
		String pathName;
		InputStream is = null;
		BufferedInputStream bis = null;
		try {
			byte[] cache = new byte[CACHE_SIZE];
			if(null != files){
			    for (File file : files) {
			        if (file.isDirectory()) {
			            pathName = file.getPath().substring(basePath.length() + 1) + "/";
			            zos.putNextEntry(new ZipEntry(pathName));
			            zipFile(file, basePath, zos);
			        } else {
			            pathName = file.getPath().substring(basePath.length() + 1);
			            is = new FileInputStream(file);
			            bis = new BufferedInputStream(is);
			            zos.putNextEntry(new ZipEntry(pathName));
			            int nRead = 0;
			            while ((nRead = bis.read(cache, 0, CACHE_SIZE)) != -1) {
			                zos.write(cache, 0, nRead);
			            }
			        }
			    }		    
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if(bis != null){
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * <p>
	 * 解压压缩包
	 * </p>
	 * 
	 * @param zipFilePath
	 *            压缩文件路径
	 * @param destDir
	 *            压缩包释放目录
	 * @throws Exception
	 */
	public static void unZip(String zipFilePath, String destDir) {
		ZipFile zipFile = null;
		BufferedInputStream bis = null;
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		File file, parentFile;
		ZipEntry entry;
		try {
			File dest=new File(destDir);
			if(!dest.exists()){
				dest.mkdirs();
			}
			LOGGER.info("zipFilePath=["+zipFilePath+"]");
			zipFile = new ZipFile(zipFilePath, CHINESE_CHARSET);
			Enumeration<?> emu = zipFile.getEntries();
			byte[] cache = new byte[CACHE_SIZE];
			while (emu.hasMoreElements()) {
				entry = (ZipEntry) emu.nextElement();
				if (entry.isDirectory()) {
					new File(destDir +File.separator+ entry.getName()).mkdirs();
					continue;
				}
				bis = new BufferedInputStream(zipFile.getInputStream(entry));
				file = new File(destDir +File.separator+ entry.getName());
				parentFile = file.getParentFile();
				if (parentFile != null && (!parentFile.exists())) {
					parentFile.mkdirs();
				}
				fos = new FileOutputStream(file);
				bos = new BufferedOutputStream(fos, CACHE_SIZE);
				int nRead = 0;
				while ((nRead = bis.read(cache, 0, CACHE_SIZE)) != -1) {
					fos.write(cache, 0, nRead);
				}
				bos.flush();
				fos.close();
				bos.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			if(bos!= null){
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fos!= null){
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(bis != null){
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(zipFile != null){
				try {
					zipFile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
