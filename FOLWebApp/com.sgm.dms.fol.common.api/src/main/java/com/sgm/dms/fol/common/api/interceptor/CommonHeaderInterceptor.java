
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : common.api
 *
 * @File name : CommonHeaderInterceptor.java
 *
 * @Author : wangfl
 *
 * @Date : 2016年6月7日
 *
 ----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2016年6月7日    wangfl    1.0
 *
 *
 *
 *
 ----------------------------------------------------------------------------------
 */
	
package com.sgm.dms.fol.common.api.interceptor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.helpers.DOMUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.sgm.dms.fol.common.api.constants.CodeConstant;

/**
 * @author wangfl
 * WEBSERVICE添加header头公共拦截器
 * @date 2016年6月7日
 */

public class CommonHeaderInterceptor extends AbstractPhaseInterceptor<SoapMessage> {
	
	private static final String sgm_qname = "SGMCommonHeader";
    private static final String xml_sgmheader_el = "SGMCommonHeader";
    private static final String xml_messageid_el = "MessageId";
    private static final String xml_from_el = "From";
    private static final String xml_to_el = "To";
    private static final String xml_Timestamp_el = "Timestamp";

    private static final String SGM_FROM = CodeConstant.PROJECT_NAME;
    private String SGM_TO ;//发送到的系统名

	public CommonHeaderInterceptor(String SGM_TO) {
		super(Phase.PREPARE_SEND);
		this.SGM_TO = SGM_TO;
	}

	/**
	 * <SGMCommonHeader>
     *    <MessageId>Fol1465268220111</MessageId>
     *    <From>FOL</v1:From>
     *    <To>WOL</v1:To>
     *    <Timestamp>2016-06-02T00:00:00.000+08:00</v1:Timestamp>
     * </SGMCommonHeader>
	 * @author wangfl
	 * @date 2016年6月7日
	 * @param msg
	 * @throws Fault
	 * (non-Javadoc)
	 * @see org.apache.cxf.interceptor.Interceptor#handleMessage(org.apache.cxf.message.Message)
	 */
	@Override
	public void handleMessage(SoapMessage msg) {
		List<Header> headers = msg.getHeaders();
		
		Document doc = DOMUtils.createDocument();
		
		Element root = doc.createElement(xml_sgmheader_el);
		
		Element messageId = doc.createElement(xml_messageid_el);
		messageId.setTextContent(java.util.UUID.randomUUID().toString());
		doc.adoptNode(messageId);
		
		Element from = doc.createElement(xml_from_el);
		from.setTextContent(SGM_FROM);
		
		Element to = doc.createElement(xml_to_el);
		to.setTextContent(SGM_TO);
		
		Element time = doc.createElement(xml_Timestamp_el);
		time.setTextContent(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(new Date()));
		
		root.appendChild(messageId);
		root.appendChild(from);
		root.appendChild(to);
		root.appendChild(time);
		
		SoapHeader header = new SoapHeader(new QName(sgm_qname), root);

		headers.add(header);
	}

}
