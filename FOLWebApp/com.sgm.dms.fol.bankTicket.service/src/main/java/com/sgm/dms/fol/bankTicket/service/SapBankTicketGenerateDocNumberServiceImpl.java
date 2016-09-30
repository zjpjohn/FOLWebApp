package com.sgm.dms.fol.bankTicket.service;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.sgm.dms.fol.bankTicket.api.SapBankTicketGenerateDocNumberService;
import com.sgm.dms.fol.bankTicket.dto.BankTicketBusinessInquiriesRequestDTO;
import com.sgm.dms.fol.bankTicket.dto.BankTicketBusinessInquiriesResultDTO;
import com.sgm.dms.fol.common.api.exception.ServiceAppException;
import com.sgm.dms.fol.common.api.exception.ServiceBizException;
import com.sgm.dms.fol.common.service.utils.PropertiesUtil;

/***
 * 调用sap生成贴息凭证号实现类
*
* @author zhang bao
* TODO description
* @date 2016年1月14日
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class SapBankTicketGenerateDocNumberServiceImpl  implements SapBankTicketGenerateDocNumberService{
	
	//esb提供的sap生成贴息凭证号地址
	private String SAP_GENERATE_BANK_TICKET_DOC_NUMBER_URL = "";
		
	// 日志
	protected Logger logger = LogManager.getLogger(this.getClass());
	@Override
	public BankTicketBusinessInquiriesResultDTO gernerateBankTicketInvoiceNumber(
			List<BankTicketBusinessInquiriesRequestDTO> request) throws ServiceAppException {
		BankTicketBusinessInquiriesResultDTO resultDto=null;
		try {
			SAP_GENERATE_BANK_TICKET_DOC_NUMBER_URL=PropertiesUtil.getServiceURL("sap_generate_bank_ticket_doc_number_url");
		} catch (IOException e1) {
		    logger.error(e1);
			throw new ServiceBizException("读取serviceurl 异常:"+e1);
		}
		CloseableHttpClient httpClient =null;
		CloseableHttpResponse resp=null;
		try {
			httpClient=HttpClients.createDefault();
			HttpPost method = new HttpPost(SAP_GENERATE_BANK_TICKET_DOC_NUMBER_URL);
			if (null==request) {
				logger.info("请求数据为空数据为空=====");
				throw new ServiceBizException("参数为空，请求终止");
			}
			JSONObject jsonStr=new JSONObject();
			 jsonStr.put("rfcArgs", request);
			StringEntity entity = new StringEntity(jsonStr.toString().toUpperCase().replace("RFCARGS", "rfcArgs"),"utf-8");
			entity.setContentEncoding("UTF-8");  
			entity.setContentType("application/json");  
			method.setEntity(entity);
			resp = httpClient.execute(method);
			//请求结束，返回结果
			int statusCode = resp.getStatusLine().getStatusCode();
			if(statusCode != HttpStatus.SC_OK){
				throw new ServiceBizException("请求sap接口失败");
			}
			String resData = EntityUtils.toString(resp.getEntity());
			
			resultDto=json2Result(resData);
		} catch (Exception e) {
			logger.error("调用sap接口异常信息:"+e);
			throw new ServiceBizException("调用sap接口异常信息:"+e);
		}finally{
			logger.info("调用sap接口生成贴息凭证号完成");
			if (resp != null) {
				try {
					resp.close();
				} catch (IOException e) {
				    logger.error(e);
				}
			}
			if (httpClient != null) {
				try {
					httpClient.close();
				} catch (IOException e) {
				    logger.error(e);
				}
			}
		}
		return resultDto;
	}
	private BankTicketBusinessInquiriesResultDTO json2Result(String resData) {
		BankTicketBusinessInquiriesResultDTO result=new BankTicketBusinessInquiriesResultDTO();
			JSONObject obj=new JSONObject(resData);
			JSONArray jsonArry=(JSONArray) obj.get("OUT");
			/*result.setBelnr(json.get(0).toString());
			result.setSequence(json.get("SEQUENCE").toString());
			result.setMsg(json.get("SEQUENCE").toString());
			result.setXblnr(json.get("XBLNR").toString());*/
			for(int i=0;i<jsonArry.length();i++){
				Iterator<?> items=jsonArry.getJSONObject(i).keys();
				while(items.hasNext()){
					String keyName=items.next().toString();
					String value="";
					 Object object =jsonArry.getJSONObject(i).opt(keyName);
					 if(null==object){
						value="";
					 }
				     if (object instanceof String) {
				    	 value=(String) object;
				      }else{
				    	  value=object+"";
				      }
					if(!StringUtils.isEmpty(value)){
						if("SEQUENCE".equals(keyName)){
							result.setSequence(value);
						}else if("BELNR".equals(keyName)){
							result.setBelnr(value);
						}else if ("MSG".equals(keyName)){
							result.setMsg(value);
						}else if("XBLNR".equals(keyName)){
							result.setXblnr(value);
						}else if("TYPE".equals(keyName)){
							result.setType(value);
						}
					}
				}
			}
			return result;
		}
	}


