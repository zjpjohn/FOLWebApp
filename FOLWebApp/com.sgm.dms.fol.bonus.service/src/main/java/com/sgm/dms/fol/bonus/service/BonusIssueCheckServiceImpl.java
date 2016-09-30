package com.sgm.dms.fol.bonus.service;

import java.io.IOException;
import java.util.ArrayList;
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
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.sgm.dms.fol.bonus.api.BonusIssueCheckService;
import com.sgm.dms.fol.bonus.dto.RequestPayBonus;
import com.sgm.dms.fol.bonus.dto.ResponsePayBonus;
import com.sgm.dms.fol.common.api.exception.ServiceAppException;
import com.sgm.dms.fol.common.api.exception.ServiceBizException;
import com.sgm.dms.fol.common.service.utils.PropertiesUtil;
import com.sgm.dms.fol.common.service.utils.StringUtil;
/**
 * 奖金发放CheckService实现类
 *
 * @author zhang bao
 * TODO description
 * @date 2016年1月4日
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class BonusIssueCheckServiceImpl implements BonusIssueCheckService {
	//QA环境
	private String BONUS_ISSUE_SAP_CHECK_DATA_URL ="";
	
	//PRD环境
	// 日志
	protected Logger logger = LogManager.getLogger(this.getClass());

	@Override
	public List<ResponsePayBonus> bonusIssueCheckData(List<RequestPayBonus> req) throws ServiceAppException {
		try {
			BONUS_ISSUE_SAP_CHECK_DATA_URL=PropertiesUtil.getServiceURL("bonus_issue_sap_check_data_url");
		} catch (IOException e1) {
		    logger.error(e1);
			throw new ServiceBizException("读取服务地址失败");
		}
		List<ResponsePayBonus> respList=null;
//		JSONObject json = new JSONObject();
		CloseableHttpClient httpClient =null;
		CloseableHttpResponse resp=null;
		HttpPost method = new HttpPost(BONUS_ISSUE_SAP_CHECK_DATA_URL);
		if (req == null || req.size() == 0) {
			logger.info("请求数据为空数据为空=====");
			throw new ServiceBizException("参数为空，请求终止");
		}
		
		try {
			httpClient=HttpClients.createDefault();
			String jsonStr=new JSONObject().put("BounsInfo", req).toString();
			StringEntity entity = new StringEntity(jsonStr.toString().toUpperCase().replace("BOUNSINFO", "BounsInfo"),"utf-8");
			entity.setContentEncoding("UTF-8");  
			entity.setContentType("application/json");  
			method.setEntity(entity);
			String authorization = PropertiesUtil.getProperty("esb.basic.auth.password");
			if(null != authorization && !"".equals(authorization = authorization.trim())) method.addHeader("Authorization", authorization);
			resp = httpClient.execute(method);
			//请求结束，返回结果
			int statusCode = resp.getStatusLine().getStatusCode();
			if(statusCode != HttpStatus.SC_OK){
				throw new ServiceBizException("请求sap接口失败");
			}
			String resData = EntityUtils.toString(resp.getEntity(),"UTF-8");
			
			respList=jsontoList(resData);
		} catch (Exception e) {
			logger.error("调用sap接口异常信息:"+e);
			throw new ServiceBizException("调用sap接口异常:"+e);
		}finally{
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
		return respList;
		}
	
	public static List<ResponsePayBonus> jsontoList(String json) {
		List<ResponsePayBonus> list=new ArrayList<ResponsePayBonus>();
		ResponsePayBonus resp=null;
		JSONObject obj=new JSONObject(json);
		JSONArray jsonArry=(JSONArray) obj.get("ZSBONUSE");
		for(int i=0;i<jsonArry.length();i++){
			resp=new ResponsePayBonus();
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
			      }else if(object instanceof Integer){
			    	  value=object+"";
			      }
				if(!StringUtil.isEmpty(value)){
					if("ZZ_VKORG".equals(keyName)){
						resp.setZz_vkorg(value);
					}else if("ZZ_ID".equals(keyName)){
						resp.setZz_id(Integer.parseInt(value));
					}else if ("ZZ_KUNNR".equals(keyName)){
						resp.setZz_kunnr(value);
					}else if("Z_NUMBER".equals(keyName)){
						resp.setZ_number(value);
					}else if("Z_MESSAGE_CODE".equals(keyName)){
						resp.setZ_message_code(value);
					}
				}
			}
			list.add(resp);
		}
		return list;

		
		
	}

    /*
    * 上传奖金前check
    * @author DELL
    * @date 2016年3月23日
    * @throws ServiceAppException
    * (non-Javadoc)
    * @see com.sgm.dms.fol.bonus.api.BonusIssueCheckService#beforeUploadBonusCheckData()
    */
    	
    @Override
    public void beforeUploadBonusCheckData(BindingResult validResult) throws ServiceAppException {
        /**
         * 对象验证不通过
         */
        if(validResult.hasErrors()){
            throw(new ServiceBizException(getErrorMessages(validResult.getAllErrors())));
        }
    }
    
    private String getErrorMessages(List<ObjectError> errors) {
        StringBuffer buffer = new StringBuffer();
        for(ObjectError error:errors) {
            if(buffer.length() > 0)
                buffer.append("；");
            buffer.append(error.getDefaultMessage());
        }
        return buffer.toString();
    }

}
