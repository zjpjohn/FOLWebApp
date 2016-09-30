package com.sgm.dms.fol.common.service.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.util.FileCopyUtils;

public class FileOperateUtil {
	
	private static final Logger LOGGER  = LogManager.getLogger(FileOperateUtil.class);
	
	private static FileOperateUtil instance;
	
	public static FileOperateUtil getInstance() {
		if(instance == null) instance = new FileOperateUtil();
		return instance;
	}

	/**
	 * 遍历文件夹中文件
	 * 
	 * @param filepath
	 * @return 返回file［］数组
	 */
	public File[] getFileList(String filepath) {
		File d = null;
		File list[] = null;
		// 建立当前目录中文件的File对象
		try {
			d = new File(filepath);
			if (d.exists()) {
				list = d.listFiles();
			}
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		// 取得代表目录中所有文件的File对象数组

		return list;
	}
	
	/**
	 * 
	 * 合并两个list 
	 *    当且仅当fileList1和fileList2都为null时，返回null。
	 *
	 * @author wangfl
	 * @param fileList1
	 * @param fileList2
	 * @return
	 */
	private List<File> addList(List<File> fileList1, List<File> fileList2){
		if(null == fileList1){
			return fileList2;
		}else{
			if(null != fileList2){
				for (File file : fileList2) {
					fileList1.add(file);
				}
			}
			return fileList1;
		}
	}
	
	/**
	 * 
	 * 得到一个文件目录下的所有文件
	 *
	 * @author wangfl
	 * @param directory
	 * @return
	 */
	public List<File> getAllFileList(File directory) {
		List<File> list = new ArrayList<File>();
		if (null == directory) {
			return null;
		} else if (directory.isFile()) {
			list.add(directory);
			return list;
		} else if (directory.isDirectory()) {
			File[] listFiles = directory.listFiles();
			if (null == listFiles || listFiles.length == 0) {
				return null;
			} else {
				for (File fileTemp : listFiles) {
				    list = this.addList(list, getAllFileList(fileTemp));
				}
				return list;
			}
		}

		return list;
	}

	/**
	 * 读取文本文件内容
	 * 
	 * @param filePathAndName
	 *            带有完整绝对路径的文件名
	 * @param encoding
	 *            文本文件打开的编码方式
	 * @return 返回文本文件的内容
	 */
	public String readTxt(String filePathAndName, String encoding) {
		encoding = encoding.trim();
		StringBuffer str = new StringBuffer("");
		String st = "";
		FileInputStream fs = null;
		BufferedReader br=null;
		try {
			fs = new FileInputStream(filePathAndName);
			InputStreamReader isr;
			if (encoding.equals("")) {
				isr = new InputStreamReader(fs);
			} else {
				isr = new InputStreamReader(fs, encoding);
			}
			br = new BufferedReader(isr);
			try {
				String data = "";
				while ((data = br.readLine()) != null) {
					str.append(data);
				}
			} catch (Exception e) {
				str.append(e.toString());
			}
			st = str.toString();
			if (st != null && st.length() > 1)
				st = st.substring(0, st.length() - 1);
		} catch (IOException es) {
			st = "";
		} finally {
			if(fs != null) {
				try {
					fs.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if(br!=null){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return st;
	}

	/**
	 * 新建目录
	 * 
	 * @param folderPath
	 *            目录
	 * @return 返回目录创建后的路径
	 */
	public String createFolder(String folderPath) {
		String txt = folderPath;
		try {
			java.io.File myFilePath = new java.io.File(txt);
			txt = folderPath;
			if (!myFilePath.exists()) {
				boolean resultFlag = myFilePath.mkdirs();
				if(!resultFlag){
					throw new RuntimeException("创建目录【" + txt + "】失败");
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return txt;
	}

	/**
	 * 多级目录创建
	 * 
	 * @param folderPath
	 *            准备要在本级目录下创建新目录的目录路径例如 c:myf
	 * @param paths
	 *            无限级目录参数，各级目录以单数线区分 例如 a|b|c
	 * @return 返回创建文件后的路径
	 */
	public String createFolders(String folderPath, String paths) {
		String txts = folderPath;
		try {
			String txt;
			txts = folderPath;
			StringTokenizer st = new StringTokenizer(paths, "|");
			for (int i = 0; st.hasMoreTokens(); i++) {
				txt = st.nextToken().trim();
				if (txts.lastIndexOf("/") != -1) {
					txts = createFolder(txts + txt);
				} else {
					txts = createFolder(txts + txt + "/");
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return txts;
	}

	/**
	 * 新建文件
	 * 
	 * @param filePathAndName
	 *            文本文件完整绝对路径及文件名
	 * @param fileContent
	 *            文本文件内容
	 * @return
	 */
	public void createFile(String filePathAndName, String fileContent) {
		FileWriter resultFile = null;
		PrintWriter myFile = null;
		try {
			String filePath = filePathAndName;
			String fileFolder = StringUtils.substringBeforeLast(filePath, File.separator);
			File myFileFoler = new File(fileFolder);
			if(!myFileFoler.exists()){
				myFileFoler.mkdirs();
			}
			File myFilePath = new File(filePath);
			if (!myFilePath.exists()) {
				myFilePath.createNewFile();
			}
			resultFile = new FileWriter(myFilePath);
			myFile = new PrintWriter(resultFile);
			String strContent = fileContent;
			myFile.println(strContent);
			myFile.close();
			resultFile.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if(resultFile != null){
				try {
					resultFile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(myFile != null){
				myFile.close();
			}
		}
	}
	
	/**
	 * 
	 * @param filePathAndName
	 * 				文本文件完整绝对路径及文件名
	 * @param input
	 * 				输入流
	 */
	public void createFile(String filePathAndName, InputStream input) {
		FileOutputStream resultfop = null;
		try {
			String filePath = filePathAndName;
			String fileFolder = StringUtils.substringBeforeLast(filePath, File.separator);
			File myFileFoler = new File(fileFolder);
			if(!myFileFoler.exists()){
				myFileFoler.mkdirs();
			}
			File myFilePath = new File(filePath);
			if (!myFilePath.exists()) {
				myFilePath.createNewFile();
			}
			resultfop = new FileOutputStream(myFilePath);
			FileCopyUtils.copy(input, resultfop);
			resultfop.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if(resultfop != null){
				try {
					resultfop.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}


	/**
	 * 有编码方式的文件创建
	 * 
	 * @param filePathAndName
	 *            文本文件完整绝对路径及文件名
	 * @param fileContent
	 *            文本文件内容
	 * @param encoding
	 *            编码方式 例如 GBK 或者 UTF-8
	 * @return
	 */
	public void createFile(String filePathAndName, String fileContent,
			String encoding) {
		PrintWriter myFile = null;
		try {
			String filePath = filePathAndName;
			String fileFolder = StringUtils.substringBeforeLast(filePath, File.separator);
			File myFileFoler = new File(fileFolder);
			if(!myFileFoler.exists()){
				myFileFoler.mkdirs();
			}
			File myFilePath = new File(filePath);
			if (!myFilePath.exists()) {
				myFilePath.createNewFile();
			}
			myFile = new PrintWriter(myFilePath, encoding);
			String strContent = fileContent;
			myFile.println(strContent);
			myFile.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if(myFile != null){
				myFile.close();
			}
		}
	}

	/**
	 * 删除文件
	 * 
	 * @param filePathAndName
	 *            文本文件完整绝对路径及文件名
	 * @return Boolean 成功删除返回true遭遇异常返回false
	 */
	public boolean delFile(String filePathAndName) {
		boolean bea = false;
		try {
			String filePath = filePathAndName;
			File myDelFile = new File(filePath);
			if (myDelFile.exists()) {
				myDelFile.delete();
				bea = true;
			} else {
				bea = false;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return bea;
	}

	/**
	 * 删除文件
	 * 
	 * @param folderPath
	 *            文件夹完整绝对路径
	 * @return
	 */
	public void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			myFilePath.delete(); // 删除空文件夹
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 删除指定文件夹下所有文件
	 * 
	 * @param path
	 *            文件夹完整绝对路径
	 * @return
	 * @return
	 */
	public boolean delAllFile(String path) {
		boolean bea = false;
		File file = new File(path);
		if (!file.exists()) {
			return bea;
		}
		if (!file.isDirectory()) {
			return bea;
		}
		String[] tempList = file.list();
		File temp = null;
		if(tempList!=null&&tempList.length>0){

			for (int i = 0; i < tempList.length; i++) {
				if (path.endsWith(File.separator)) {
					temp = new File(path + tempList[i]);
				} else {
					temp = new File(path + File.separator + tempList[i]);
				}
				if (temp.isFile()) {
					temp.delete();
				}
				if (temp.isDirectory()) {
					delAllFile(path + File.separator + tempList[i]);// 先删除文件夹里面的文件
					delFolder(path + File.separator + tempList[i]);// 再删除空文件
					bea = true;
				}
			}
		
		}
		return bea;
	}

	/**
	 * 复制单个文件
	 * 
	 * @param oldPathFile
	 *            准备复制的文件源
	 * @param newPathFile
	 *            拷贝到新绝对路径带文件名
	 * @return
	 */
	public void copyFile(String oldPathFile, String newPathFile) {
		InputStream inStream = null;
		FileOutputStream fs = null;
		try {
			File file = new File(newPathFile);
			if(!file.exists()){
				String fileFolder = StringUtils.substringBeforeLast(newPathFile, File.separator);
				File myFileFoler = new File(fileFolder);
				if(!myFileFoler.exists()){
					myFileFoler.mkdirs();
				}
				File myFilePath = new File(newPathFile);
				if (!myFilePath.exists()) {
					myFilePath.createNewFile();
				}
			}
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPathFile);
			if (oldfile.exists()) { // 文件存在
				inStream = new FileInputStream(oldPathFile); // 读入源文件
				fs = new FileOutputStream(newPathFile);
				byte[] buffer = new byte[1444];
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // 字节 文件大小
					fs.write(buffer, 0, byteread);
				}
				fs.close();
				inStream.close();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if(inStream != null){
				try {
					inStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fs != null){
				try {
					fs.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void copyFileSameFileName(String oldPathFile, String newPathFileFoler){
		File oldFile = new File(oldPathFile);
		this.copyFile(oldPathFile, newPathFileFoler+File.separator+oldFile.getName());
	}

	/**
	 * 复制整个文件夹的内容
	 * 
	 * @param oldPath
	 *            准备拷贝的目录
	 * @param newPath
	 *            指定绝对路径的新目录
	 * @return
	 */
	public void copyFolder(String oldPath, String newPath) {
		FileInputStream input = null;
		FileOutputStream output = null;
		try {
			new File(newPath).mkdirs(); // 如果文件夹不存在 则建立新文件
			File a = new File(oldPath);
			String[] file = a.list();
			File temp = null;
			if(null != file){
			    for (int i = 0; i < file.length; i++) {
			        if (oldPath.endsWith(File.separator)) {
			            temp = new File(oldPath + file[i]);
			        } else {
			            temp = new File(oldPath + File.separator + file[i]);
			        }
			        if (temp.isFile()) {
			            input = new FileInputStream(temp);
			            output = new FileOutputStream(newPath
			                                          + File.separator + (temp.getName()).toString());
			            byte[] b = new byte[1024 * 5];
			            int len;
			            while ((len = input.read(b)) != -1) {
			                output.write(b, 0, len);
			            }
			            output.flush();
			            output.close();
			            input.close();
			        }
			        if (temp.isDirectory()) {// 如果是子文件夹
			            copyFolder(oldPath + File.separator + file[i], newPath + File.separator + file[i]);
			        }
			    }		    
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if(input != null){
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(output != null){
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 根据源目录下的子目录来删除目标目录的子目录
	 * 
	 * @param sourcePath
	 *            参照的根目录
	 * @param targetPath
	 *            指定绝对路径的新目录
	 * @return
	 */
	public void deleteSubFolder(String sourcePath, String targetPath) {
		try {
			File a = new File(sourcePath);
			String[] file = a.list();
			File temp = null;
			if(null != file){
			    for (int i = 0; i < file.length; i++) {
			        if (sourcePath.endsWith(File.separator)) {
			            temp = new File(sourcePath + file[i]);
			        } else {
			            temp = new File(sourcePath + File.separator + file[i]);
			        }
			        if (temp.isDirectory()) {// 如果是子文件夹
			            File targetFile = new File(targetPath + File.separator + file[i]);
			            if(targetFile.exists()){
			                this.delFolder(targetPath + File.separator + file[i]);
			            }
			        }
			    }			    
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 移动文件
	 * 
	 * @param oldPath
	 * @param newPath
	 * @return
	 */
	public void moveFile(String oldPath, String newPath) {
		File newFile = new File(newPath);
		File fileFolder = newFile.getParentFile();
		if(!fileFolder.exists()) fileFolder.mkdirs();
		copyFile(oldPath, newPath);
		delFile(oldPath);
	}

	/**
	 * 移动目录
	 * 
	 * @param oldPath
	 * @param newPath
	 * @return
	 */
	public void moveFolder(String oldPath, String newPath) {
		copyFolder(oldPath, newPath);
		delFolder(oldPath);
	}

	/**
	 * 建立一个可以追加的bufferedwriter
	 * 
	 * @param fileDir
	 * @param fileName
	 * @return
	 */
	public BufferedWriter getWriter(String fileDir, String fileName) {
		try {
			File f1 = new File(fileDir);
			if (!f1.exists()) {
				f1.mkdirs();
			}
			f1 = new File(fileDir, fileName);
			if (!f1.exists()) {
				f1.createNewFile();
			}
			BufferedWriter bw = new BufferedWriter(new FileWriter(f1.getPath(),
					true));
			return bw;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 得到一个bufferedreader
	 * 
	 * @param fileDir
	 * @param fileName
	 * @param encoding
	 * @return
	 */
	public BufferedReader getReader(String fileDir, String fileName,
			String encoding) {
		try {
			File file = new File(fileDir, fileName);
			InputStreamReader read = new InputStreamReader(new FileInputStream(
					file), encoding);
			BufferedReader br = new BufferedReader(read);
			return br;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
	
	public static void byteToFile(byte[] bytes, String fileDir, String fileName) {
		File dirFile = new File(fileDir + File.separator);
		if(!dirFile.exists()) {
			dirFile.mkdirs();
		}
		FileOutputStream fos = null;
//		BufferedOutputStream bos = null;
		FileChannel writeChannel = null;
		try {
			File file = new File(fileDir + File.separator + fileName);
			fos = new FileOutputStream(file);  
			writeChannel = fos.getChannel();
//			bos = new BufferedOutputStream(fos);
			LOGGER.debug("---byteToFile bytes length:"+bytes.length);
//	        bos.write(bytes, 0, bytes.length);
			writeChannel.write(ByteBuffer.wrap(bytes));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("写临时文件失败");
		} finally {
			if(fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
				}
			}
			if(writeChannel != null) {
				try {
					writeChannel.close();
				} catch (IOException e) {
				}
			}
		}
	}
	
}