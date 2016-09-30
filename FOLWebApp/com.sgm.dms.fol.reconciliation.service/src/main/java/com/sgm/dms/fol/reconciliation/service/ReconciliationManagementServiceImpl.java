/*
 * Copyright 2015 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : common.api
 *
 * @File name : ReconciliationManagementService.java
 *
 * @Author : ZhangBao
 *
 * @Date : 2015年11月3日
 *
----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2015年11月3日    ZhangBao    1.0
 *
 *
 *
 *
----------------------------------------------------------------------------------
 */

package com.sgm.dms.fol.reconciliation.service;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.FileSystemException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgm.dms.fol.common.api.constants.CodeConstant;
import com.sgm.dms.fol.common.api.constants.POConstant;
import com.sgm.dms.fol.common.api.exception.ServiceAppException;
import com.sgm.dms.fol.common.api.exception.ServiceBizException;
import com.sgm.dms.fol.common.service.utils.CommonUtils;
import com.sgm.dms.fol.common.service.utils.CookieUtil;
import com.sgm.dms.fol.common.service.utils.DateUtil;
import com.sgm.dms.fol.common.service.utils.ExportExcel;
import com.sgm.dms.fol.common.service.utils.ExportExcelPoi;
import com.sgm.dms.fol.common.service.utils.FileOperateUtil;
import com.sgm.dms.fol.common.service.utils.FileUtils;
import com.sgm.dms.fol.common.service.utils.PropertiesUtil;
import com.sgm.dms.fol.common.service.utils.RSAUtil;
import com.sgm.dms.fol.common.service.utils.SysException;
import com.sgm.dms.fol.common.service.utils.TokenUtils;
import com.sgm.dms.fol.common.service.utils.ZipUtil;
import com.sgm.dms.fol.reconciliation.api.ReconciliationManagementService;
import com.sgm.dms.fol.reconciliation.dto.BillFileDTO;
import com.sgm.dms.fol.reconciliation.dto.BillFileDetailDTO;
import com.sgm.dms.fol.reconciliation.repository.BillFileDao;
import com.sgm.dms.fol.reconciliation.repository.BillFileDetailDao;


/*
 *
 * @author ZhangBao
 * 对账管理service
 * @date 2015年11月3日
 */
@Service("reconciliationManagementService")
@Transactional(rollbackFor = ServiceBizException.class)
public class ReconciliationManagementServiceImpl implements
ReconciliationManagementService {
	
	public static String CHARSET="gbk";
	
	@Autowired
	private BillFileDao billFileDao;
	@Autowired
	private BillFileDetailDao billFileDetailDao;
	
	@Autowired
	private TokenUtils tokenUtils;
	
	/*
	 * 
	 * @author ZhangBao
	 * 
	 * @date 2015年11月3日
	 * 
	 * @param fileDTO
	 * 
	 * @return (non-Javadoc)
	 * 
	 * @see
	 * com.sgm.dms.fol.common.api.services.ReconciliationManagementService#addFile
	 * (com.sgm.dms.fol.common.api.domain.FileDTO)
	 */
	
	@Override
	public int addFile(BillFileDTO fileDTO) throws ServiceBizException, SQLException {
		int resut = 0;
		CommonUtils.filterSpecialWord(fileDTO);
		resut = billFileDao.addBillFile(fileDTO);
		return resut;
	}
	
	/*
	 * 
	 * @author ZhangBao
	 * 
	 * @date 2015年11月3日
	 * 
	 * @param fileDetailDTO
	 * 
	 * @return (non-Javadoc)
	 * 
	 * @see com.sgm.dms.fol.common.api.services.ReconciliationManagementService#
	 * addFileDetail(com.sgm.dms.fol.common.api.domain.FileDetailDTO)
	 */
	
	@Override
	public int addFileDetail(List<BillFileDetailDTO> fileDetailDTO)
			throws ServiceBizException, SQLException {
		int reseult = 0;
		if (null != fileDetailDTO && fileDetailDTO.size() > 1) {
			for (BillFileDetailDTO fileD : fileDetailDTO) {
		        CommonUtils.filterSpecialWord(fileDetailDTO);
				reseult += billFileDetailDao.addBillFileDetail(fileD);
			}
		}
		return reseult;
	}
	
	/*
	 * 
	 * @author ZhangBao
	 * 
	 * @date 2015年11月3日
	 * 
	 * @param fileDetailDTO
	 * 
	 * @return (non-Javadoc)
	 * 
	 * @see com.sgm.dms.fol.common.api.services.ReconciliationManagementService#
	 * findDealerFileDetailByFdAndPage
	 * (com.sgm.dms.fol.common.api.domain.FileDetailDTO)
	 */ 
	
	@Override
	public List<BillFileDetailDTO> findDealerFileDetailByFdAndPage(
			BillFileDetailDTO fileDetailDTO) throws ServiceBizException, SQLException {
		List<BillFileDetailDTO> list = null;
		list =convertToStatusName( billFileDetailDao.findDealerBillFileDetailByFdAndPage(fileDetailDTO));
		return list;
	}
	
	/*
	 * 
	 * @author ZhangBao
	 * 
	 * @date 2015年11月3日
	 * 
	 * @param fileDetailDTO
	 * 
	 * @return (non-Javadoc)
	 * 
	 * @see com.sgm.dms.fol.common.api.services.ReconciliationManagementService#
	 * updateDealerFileDetaById(com.sgm.dms.fol.common.api.domain.FileDetailDTO)
	 */
	
	@Override
	public int updateBillFileDetailStatusById(List<BillFileDetailDTO> fileDetailDTO)
			throws ServiceBizException, SQLException {
		int result = 0;
		if(null!=fileDetailDTO&&fileDetailDTO.size()>0){
			for(BillFileDetailDTO billDto:fileDetailDTO){
				billDto.setStatus(1);
				billDto.setUpdateBy(-1);
				billDto.setUpdateDate(new Date());
				
				CommonUtils.filterSpecialWord(billDto);
				result = billFileDetailDao.updateBillFileStatusById(billDto);	
			}
			
		}
		
		return result;
	}
	
	/*
	 * 
	 * @author ZhangBao
	 * 
	 * @date 2015年11月4日
	 * 
	 * @param billFile
	 * 
	 * @param billFileDetailDTOs
	 * 
	 * @return (non-Javadoc)
	 * 
	 * @see com.sgm.dms.fol.reconciliation.api.ReconciliationManagementService#
	 * addImportReconFile(com.sgm.dms.fol.reconciliation.dto.BillFileDTO,
	 * java.util.List)
	 */
	
	@Override
	public void addImportReconFile(BillFileDTO billFile) throws ServiceAppException,SQLException  {
	    int result=CodeConstant.IMPORT_FILE_RESULT_NOT_EXIST;
		//账单年月父文件和子文件保持一致
	    CommonUtils.filterSpecialWord(billFile);
		if(null != billFile.getDbBillDate()){
			BillFileDTO bill=existBillFileByBillDate(billFile.getDbBillDate());
			if(bill!=null){
				if(0==bill.getStatus()){
					//已存在未发布的文件，覆盖原来的文件(包含 小文件)
					billFile.setUpdateDate(new Date());
					billFile.setUpdateBy(-1);
					billFileDao.updateFiledIdByBillDate(billFile);
					
					//将原有小文件状态置为失效
					List<BillFileDetailDTO> list=billFileDetailDao.findBillFileDetailByBillDateAndIsDel(billFile.getDbBillDate());
					if(null!=list&&list.size()>0){
						for(BillFileDetailDTO billDto:list){
							billDto.setIsDel(1);
							billDto.setUpdateDate(new Date());
							billDto.setUpdateBy(-1);
							CommonUtils.filterSpecialWord(billDto);
							billFileDetailDao.updateBillFileStatusById(billDto);
						}
					}
					result= CodeConstant.IMPORT_FILE_RESULT_EXIST_UPDATE;
				}else{
					//已存在已发布的文件，不做任何处理
				    result= CodeConstant.IMPORT_FILE_RESULT_EXIST_PUBLISH;
				}
				
			}
		}
		if (CodeConstant.IMPORT_FILE_RESULT_EXIST_PUBLISH == result) {
            throw(new ServiceBizException("检测到该月已有发布的文件，不能重新上传。"));
        }else if(CodeConstant.IMPORT_FILE_RESULT_EXIST_UPDATE==result){
           return;
        }
		
		// 保存文件名信息
		billFileDao.addBillFile(billFile);		
	}
	
	/*
	 * 
	 * @author ZhangBao
	 * 
	 * @date 2015年11月10日
	 * 
	 * @param filePath
	 * 
	 * @return (non-Javadoc)
	 * 
	 * @see
	 * com.sgm.dms.fol.reconciliation.api.ReconciliationManagementService#getTxtFile
	 * (java.lang.String)
	 */
	
	/*
	*
	* @author ZhangBao
	* TODO description
	* @date 2015年11月20日
	* @param billFileDetailDTOs
	*/
		
	/*
	*
	* @author ZhangBao
	* TODO description
	* @date 2015年11月20日
	* @param billFileDetailDTOs
	* @param dbBillDate
	* @param filePath
	* @param effectiveDate
	*/
		
	private void saveBillFileDetails(
			List<BillFileDetailDTO> billFileDetailDTOs, Date billDate,
			 Date effectiveDate) throws SQLException {
		if (null != billFileDetailDTOs && billFileDetailDTOs.size() > 0) {
			for (BillFileDetailDTO fileD : billFileDetailDTOs) {
				Date now = new Date();
				fileD.setTitle(fileD.getDealerCode()+"_"+DateUtil.date2str(billDate));
				fileD.setCreateBy(-1);
				fileD.setCreateDate(now);
				fileD.setUpdateBy(-1);
				fileD.setStatus(0);// 未签收
				fileD.setUpdateDate(now);
				fileD.setEffectiveDate(effectiveDate);
				fileD.setFiledId(fileD.getFiledId());
				fileD.setDbBillDate(billDate);
				CommonUtils.filterSpecialWord(fileD);
				billFileDetailDao.addBillFileDetail(fileD);
				
			}
		}
		
	}
	
	
	private List<BillFileDetailDTO> getBillFileDetailFromBufferedReader(String token, BufferedReader br, HttpServletRequest request) throws UnsupportedEncodingException, IOException {
		String uploadToken = token;
		List<BillFileDetailDTO> list = new ArrayList<BillFileDetailDTO>();
		
		ByteArrayInputStream input = null;
		
		try {
			// 文件行内容
			String lineTxt = null;
			// 经销商code
			String dealerCode = "";
			// 经销商姓名
			String dealerName = "";
			// 账单日期
			String billDate = null;
			// 根据BEGIN END标签解析文件,每一个<BEGIN创建文件(每个文件内容是<BEGIN>,<END>标签内的内容)
			String beginTag = "<BEGIN";
			String endTag = "<END>";
			
			// 拼接文件内容
			StringBuffer sb = new StringBuffer();
			// 子文件名称
			String subFileName = "";
			BillFileDetailDTO billFileDetail = null;
			int index = 0;
			while ((lineTxt = br.readLine()) != null) {
				String[] str = lineTxt.split(" ");
				
				// 获取经销商代码
				if (beginTag.equals(str[0])) {
					dealerCode = str[1].replace(">", "");// 获取dealerCode
					subFileName = dealerCode + "-" + DateUtil.formartDate(new Date());
					index = 1;
				} else {
					
					// 获取账单日期
					if (3 == index) {
						billDate = lineTxt.toString().trim().replace("帐目", "");
					}
					
					// 获取经销商名称
					if (4 == index) {
						dealerName = lineTxt.replace(dealerCode, "").trim();
						
					}
					
					// 判断单个文件是否结束
					String end = lineTxt.trim();
					
					if (!end.equals(endTag)) {
						sb.append(lineTxt + System.lineSeparator());
						index++;
					} else {
						// 单个文件结束,保存需要的信息,上传到服务器
						input = new ByteArrayInputStream(sb.toString().getBytes(CHARSET));
						String filedId = null;
						try {
						    filedId = FileUtils.upload(subFileName, uploadToken, input);
                        } catch (Exception e) {
                            try {
                                uploadToken = TokenUtils.getToken(CookieUtil.getAccount(request)).getToken();
                            } catch (Exception e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                            filedId = FileUtils.upload(subFileName, uploadToken, input);
                        }
						sb = new StringBuffer();
						input.close();
						
						// 保存需要的信息
						billFileDetail = new BillFileDetailDTO();
						billFileDetail.setDealerCode(dealerCode);
						billFileDetail.setBillDate(billDate.toString());
						billFileDetail.setDealerName(dealerName);
						billFileDetail.setFiledId(filedId);
						list.add(billFileDetail);
						
					}
				}
			}
			
		} finally {
			if (br != null) {
				br.close();
			}
			if (input != null) {
				input.close();
			}
		}
		
		return list;
	}
	
	
	/*
	 *
	 * @author ZhangBao
	 * @date 2015年11月11日
	 * @param fileDTO
	 * @return
	 * @throws ServiceBizException
	 * (non-Javadoc)
	 * @see com.sgm.dms.fol.reconciliation.api.ReconciliationManagementService#updateFileStatusByFIledId(com.sgm.dms.fol.reconciliation.dto.BillFileDTO)
	 */
	
	@Override
	public void updateBillFileStatusById(List<BillFileDTO> fileDTO)
			throws ServiceAppException, SQLException {
		int result=0;
		if(null!=fileDTO&&fileDTO.size()>0){
			for(BillFileDTO billDto:fileDTO){
			    CommonUtils.filterSpecialWord(billDto);
				result=billFileDao.updateBillFileStatusById(billDto);
			}
		}
		if (result == 0) {
		    throw(new ServiceBizException("参数不正确"));
        }
	}
	
	/*
	 *
	 * @author ZhangBao
	 * @date 2015年11月11日
	 * @param billFileDetailDTO
	 * @return
	 * @throws ServiceBizException
	 * (non-Javadoc)
	 * @see com.sgm.dms.fol.reconciliation.api.ReconciliationManagementService#exportExcel(com.sgm.dms.fol.reconciliation.dto.BillFileDTO)
	 */
	
	@Override
	public <T> ByteArrayOutputStream exportXls4BillFile(List<T> data,String token,String [] headers,String fileName)
			throws ServiceBizException, SysException, IOException {
		
		if(null == token || "".equals(token))
			try {
				token = tokenUtils.getTokenStr();
			} catch (Exception e1) {
				throw new ServiceBizException("获取token失败");
			}
		
		ExportExcel<T> exp=new ExportExcel<T>();				
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		exp.exportExcel(headers, data, os);
		ByteArrayInputStream in = new ByteArrayInputStream(os.toByteArray());
		//上传文件
		String filedId=FileUtils.upload(fileName, token, in);	
		//下载文件流
		ByteArrayOutputStream out = null;
		try {
			out = (ByteArrayOutputStream) FileUtils
					.downLoadStream(filedId, token);
		} catch (Exception e) {
			e.printStackTrace();
			throw new  ServiceBizException("异常:"+e);
		}finally {
			if(out!=null){
				out.flush();
				out.close();
			}
		}
		return out;
	}
	
	@Override
    public <T> ByteArrayOutputStream exportCsvFile(String[] headers, List<T> data, String fileName, String token) throws NoSuchMethodException, SecurityException, IOException {
	    
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < headers.length; i++) {
            sb.append(headers[i]);
            if(i != headers.length-1){
                sb.append(",");
            }else{
                sb.append("\n");
            }
        }
        //遍历数据列表
        for (T t : data) {
            Field[] fields = t.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                String fieldName = fields[i].getName();
                String getFileMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                Method getFileMethod = t.getClass().getMethod(getFileMethodName, new Class[]{});
                try {
                    Object value = getFileMethod.invoke(t, new Object[]{});
                    sb.append(value);
                    if(i != fields.length-1){
                        sb.append(",");
                    }else{
                        sb.append("\n");
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        ByteArrayInputStream in = new ByteArrayInputStream(sb.toString().getBytes(CHARSET));
        //上传文件
        String filedId=FileUtils.upload(fileName, token, in);
        //下载文件流
        ByteArrayOutputStream out = null;
        try {
            out = (ByteArrayOutputStream) FileUtils
                    .downLoadStream(filedId, token);
        } catch (Exception e) {
            e.printStackTrace();
            throw new  ServiceBizException("异常:"+e);
        }finally {
            if(out!=null){
                out.flush();
                out.close();
            }
        }
        return out;
    }
	
	/*
	 *
	 * @author ZhangBao
	 * @date 2015年11月12日
	 * @param bill
	 * @return
	 * (non-Javadoc)
	 * @see com.sgm.dms.fol.reconciliation.api.ReconciliationManagementService#findDealerFileByFdAndPage(com.sgm.dms.fol.reconciliation.dto.BillFileDTO)
	 */
	
	@Override
	public List<BillFileDTO> findDealerFileByFdAndPage(BillFileDTO bill) throws SQLException {
		List<BillFileDTO> data=null;
		data=billFileDao.findDealerFileByFdAndPage(bill);
		return data;
	}
	
	/*
	 *
	 * @author ZhangBao
	 * @date 2015年11月16日
	 * @param date
	 * @return
	 * @throws ServiceBizException
	 * (non-Javadoc)
	 * @see com.sgm.dms.fol.reconciliation.api.ReconciliationManagementService#existBillFileByBillDate(java.util.Date)
	 */
	
	@Override
	public BillFileDTO existBillFileByBillDate(Date date)
			throws ServiceBizException, SQLException {
		BillFileDTO bill=null;
		List<BillFileDTO> billFileDTO=billFileDao.findBillFileByBillDate(date);
		if(null!=billFileDTO&&billFileDTO.size()>1){
			throw new ServiceBizException("该日期的文件已导入多个");
		}
		if(null!=billFileDTO&&billFileDTO.size()==1){
			bill=billFileDTO.get(0);
		}
		return bill;
	}
	/*
	*
	* @author ZhangBao
	* @date 2015年11月18日
	* @param billDto
	* @return
	* (non-Javadoc)
	* @see com.sgm.dms.fol.reconciliation.api.ReconciliationManagementService#findDealerBillFileDetailForBillFilePublishedByFdAndPage(com.sgm.dms.fol.reconciliation.dto.BillFileDetailDTO)
	*/
		
	@Override
	public List<BillFileDetailDTO> findDealerBillFileDetailForBillFilePublishedByFdAndPage(
			BillFileDetailDTO billDto)throws ServiceBizException {
		List<BillFileDetailDTO> data=null;
		try {
		    billDto.setDealerCode(RSAUtil.decryptByPrivateKey(billDto.getDealerCode()));
			 data=convertToStatusName(billFileDetailDao.findDealerBillFileDetailForBillFilePublishedByFdAndPage(billDto));
		} catch (SQLException e) {
			throw (new ServiceBizException("SQL ERROR"));
		} catch (Exception e) {
		    throw (new ServiceBizException("RSA decrypt ERROR"));
        }
		return data;
	}

	/*
	*
	* @author ZhangBao
	* @date 2015年11月20日
	* @param fileDTO
	* @param billFiles
	* @return
	* @throws ServiceBizException
	* @throws SQLException
	* (non-Javadoc)
	* @see com.sgm.dms.fol.reconciliation.api.ReconciliationManagementService#publishFIleStatus(java.util.List, java.util.List)
	*/
		
	@Override
	public void publishFIle(List<BillFileDTO> fileDTO, HttpServletRequest request) {
		
		if(null != fileDTO && fileDTO.size() > 0){
			for(BillFileDTO billDto:fileDTO){//遍历发布每个对账单
				try{
					//过滤特殊字符
					CommonUtils.filterSpecialWord(billDto);
					
					//读取文件内容
					List<BillFileDetailDTO> billFileDetailList = null;
					String fileName = billDto.getFileName();//大文件文件名
					
					if(".zip".equalsIgnoreCase(fileName.substring(fileName.lastIndexOf(".")))){//zip文件
						//下载并解压zip大文件
						String savePath = PropertiesUtil.getProperty("unzip.temp.location")+"/"+UUID.randomUUID().toString();
						FileUtils.downLoadFIle(billDto.getFiledId(), billDto.getToken(), savePath+"/");
						ZipUtil.unZip(savePath + "/" + fileName, savePath + "/temp" );
						
						//遍历处理解压后的每一个txt文件
						List<File> fileList = FileOperateUtil.getInstance().getAllFileList(new File(savePath+"/temp/"));
						if (null != fileList) {
							for (File file : fileList) {
								if(!".txt".equalsIgnoreCase(file.getName().substring(file.getName().lastIndexOf(".")))){//压缩包中，非txt文件不做处理。
									continue;
								}
								// 创建reader，准备解析txt文件
								BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), CHARSET));
								billFileDetailList = this.getBillFileDetailFromBufferedReader(billDto.getToken(), br, request);
								if(null == billFileDetailList || billFileDetailList.size()==0){
									throw new ServiceBizException("读取文件失败");
								}
								
								// 保存文件内容信息
								saveBillFileDetails(billFileDetailList,billDto.getDbBillDate(),billDto.getEffectiveDate());
							}
							
						}
						
						//解析完成后，删除解压文件临时目录。
						FileOperateUtil.getInstance().delFolder(savePath);
					}else{//txt文件
						// 下载文件
						OutputStream downLoadStream = FileUtils.downLoadStream(billDto.getFiledId(), billDto.getToken());
						if (null == downLoadStream) {
							throw new IOException("文件下载失败");
						}
						ByteArrayInputStream is = new ByteArrayInputStream(((ByteArrayOutputStream) downLoadStream).toByteArray());
						BufferedReader br = new BufferedReader(new InputStreamReader(is, CHARSET));
						billFileDetailList = this.getBillFileDetailFromBufferedReader(billDto.getToken(), br, request);
						if(null == billFileDetailList || billFileDetailList.size()==0){
							throw new ServiceBizException("读取文件失败");
						}
						
						// 保存文件内容信息
						saveBillFileDetails(billFileDetailList,billDto.getDbBillDate(),billDto.getEffectiveDate());
					}
					
					
					//更新大文件状态为发布成功
					billFileDao.updateBillFileStatusById(billDto);
					
				}catch(Exception e){
					//更新大文件状态为发布失败
					try {
						billDto.setStatus(3);
						billFileDao.updateBillFileStatusById(billDto);
					} catch (SQLException e1) {
						e1.printStackTrace();
						throw new ServiceAppException(e1.getMessage());
					}
					//throw new ServiceAppException(e.getMessage());
				}
				
			}
		}
		

	}

	@Override
	public <T> ByteArrayOutputStream exportXls4BillFiles(List<T> data,
			String token, String[] headers, String fileName)
			throws ServiceBizException, FileNotFoundException,
			FileSystemException, UnsupportedEncodingException, SysException,
			IOException {
		ExportExcelPoi<T> exp=new ExportExcelPoi<T>();				
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		exp.exportExcels(headers, data, os);
		ByteArrayInputStream in = new ByteArrayInputStream(os.toByteArray());
		//上传文件
		String filedId=FileUtils.upload(fileName, token, in);	
		//下载文件流
		ByteArrayOutputStream out = null;
		try {
			out = (ByteArrayOutputStream) FileUtils
					.downLoadStream(filedId, token);
		} catch (Exception e) {
			e.printStackTrace();
			throw new  ServiceBizException("异常:"+e);
		}finally {
			if(out!=null){
				out.flush();
				out.close();
			}
		}
		return out;
	}
	
	/*
	*
	* @author ZhangBao
	* @date 2015年11月25日
	* @param billDto
	* @return
	* @throws ServiceBizException
	* (non-Javadoc)
	* @see com.sgm.dms.fol.reconciliation.api.ReconciliationManagementService#confirmBillFileDetails(com.sgm.dms.fol.reconciliation.dto.BillFileDetailDTO)
	*/
		
	@Override
	public int confirmBillFileDetails(
			List<BillFileDetailDTO> billDto) throws ServiceBizException, SQLException {
		int result=0;
		if(null==billDto||billDto.size()==0){
			throw new ServiceBizException("请选择要确认的数据");
		}
		for(BillFileDetailDTO bill:billDto){
			if(POConstant.FILE_NO_CONFIRM==bill.getIsConfirm()){
				bill.setUpdateBy(-1);
				bill.setUpdateDate(new Date());
				bill.setIsConfirm(POConstant.FILE_CONFIRMED);
				CommonUtils.filterSpecialWord(bill);
				result+=billFileDetailDao.updateBillFileIsConfirmById(bill);
			}
		}
		
		return result;
	}

	/*
	*
	* @author ZhangBao
	* @date 2015年11月14日
	* @param findDealerFileDetailByFdAndPage
	* @return
	*/
		
	private List<BillFileDetailDTO> convertToStatusName(List<BillFileDetailDTO> list) {
			if(null!=list&&list.size()>0){
				for(BillFileDetailDTO dto:list){
					if(POConstant.FILE_NO_CHECK==dto.getStatus()){
						dto.setStatusName("未签收");
					}else{
						dto.setStatusName("已签收");
					}
					
					if(POConstant.FILE_NO_CONFIRM==dto.getIsConfirm()){
						dto.setConfirmName("未确认");
					}else{
						dto.setConfirmName("已确认");
					}
					dto.setBillDate(DateUtil.date2str(dto.getDbBillDate()));
					dto.setEffDate(DateUtil.date2str(dto.getEffectiveDate()));
				}
			}
		return list;
	}

    @Override
    public void publishFIleStatus(Integer id) throws SQLException {
        
        BillFileDTO fileDTO = new BillFileDTO();
        fileDTO.setId(id);
        fileDTO.setStatus(2);
        fileDTO.setUpdateBy(-1);
        billFileDao.updateBillFileStatusById(fileDTO);
    }

}
