package com.dealer.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dealer.vo.PositionSwitchVo;
import com.dealer.vo.UserSearchVo;
import com.sgm.dms.am.client.SSOCertificate;
import com.sgm.dms.am.client.constant.OAuthClientConstant;
import com.sgm.dms.am.client.util.SSOClientUtil;
import com.sgm.dms.fol.common.api.domain.PositionSwitchDTO;
import com.sgm.dms.fol.common.api.services.PositionService;
import com.sgm.dms.fol.common.service.controller.impl.BaseController;
import com.sgm.dms.fol.common.service.utils.BeanMapper;
import com.sgm.dms.fol.common.service.utils.MapUtil;
import com.sgm.dms.fol.common.service.utils.PropertiesUtil;
import com.sgm.solution.framework.urlaccess.annotations.ValidationRequestUrl;

@Controller
@RequestMapping("/system/systemamount")
public class PositionController extends BaseController {
	// 日志
	protected Logger logger = LogManager.getLogger(this.getClass());

	// 岗位 SERVICE
	@Autowired
	private PositionService systemService;

	/**
	 * 岗位切换列表查询(不分页)
	 * 
	 * @param positionVo
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/find", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object findSystemmounts(
			@Validated @RequestBody UserSearchVo userSearchVo,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			PositionSwitchDTO positionSwitchDto = BeanMapper.map(userSearchVo,
					PositionSwitchDTO.class);

			SSOCertificate certificate = SSOClientUtil.getCertificate(request);
			logger.info("certificate_controller:"+certificate);
			logger.info("SGM_SSO_AUTHENTICATION_controller:"+request.getSession(true).getAttribute(OAuthClientConstant.SGM_SSO_AUTHENTICATION));
            String userAccount=certificate==null?null:certificate.getUserId();
			logger.info("acnt:" + userAccount);

			// 如果是测试模式就走入到IF里
			String model = PropertiesUtil
					.getProperty("logon.account.model.test");

			if (model != null && Boolean.valueOf(model)) {
				userAccount = PropertiesUtil.getProperty("logon.account.name");
			}

			// 解密
			positionSwitchDto.setUserAccount(userAccount);
			List<PositionSwitchDTO> dtoList = systemService
					.getPositionSwitch(positionSwitchDto);
			List<PositionSwitchVo> voList = BeanMapper.mapList(dtoList, PositionSwitchVo.class);
			
	        //设置前台需要的数据
	        //Map<String, Object> responsedata=MapUtil.setQueryDataToMap(voList, null);
		
		//响应
		return MapUtil.setQueryDataToMap(voList, voList == null ? 0L : voList.size());
	}

}
