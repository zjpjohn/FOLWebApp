/*
* Copyright 2015 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : common.service
*
* @File name : MailUtil.java
*
* @Author : s86yv7
*
* @Date : 2015年10月28日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2015年10月28日    s86yv7    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/

package com.sgm.dms.fol.common.service.utils;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;

import com.sgm.dms.fol.common.api.exception.ServiceAppException;
import com.sgm.dms.fol.common.api.exception.ServiceBizException;

/*
*
* @author s86yv7
* TODO description
* @date 2015年10月28日
*/

public class MailUtil {

    private static Logger logger = Logger.getLogger(MailUtil.class);

    /**
     * mail send without attachment
     */
    public static void sendMail(String mailFrom, List<String> mailTos, String mailSubject,
                                String mailContent) throws ServiceBizException {
        String mailurlAdd = PropertiesUtil.getProperty("com.sgm.dms.common.mail.serviceaddress");
        logger.info("sendEamil entry" + mailurlAdd);
        if (mailurlAdd == null) {
            throw new ServiceBizException("fileload is fail!");
        }
        int status = 0;
        String message = "";
        CloseableHttpClient httpclient = null;
        try {
            httpclient = HttpClients.createDefault();
            HttpPost hpost = new HttpPost(mailurlAdd);
            List<String> list = mailTos;
            for (String mailto : list) {
                logger.info("sendFromPerson:" + mailFrom + " sendToPerson" + mailto);
                StringBody from = new StringBody(mailFrom, org.apache.http.entity.ContentType.create(Consts.TEXT_PLAIN,
                                                                                                     Consts.UTF_8));
                StringBody to = new StringBody(mailto, org.apache.http.entity.ContentType.create(Consts.TEXT_PLAIN,
                                                                                                 Consts.UTF_8));
                StringBody subject = new StringBody(mailSubject,
                                                    org.apache.http.entity.ContentType.create(Consts.TEXT_PLAIN,
                                                                                              Consts.UTF_8));
                StringBody content = new StringBody(mailContent,
                                                    org.apache.http.entity.ContentType.create(Consts.TEXT_PLAIN,
                                                                                              Consts.UTF_8));
                StringBody type = new StringBody(Consts.TEXT_HTML,
                                                 org.apache.http.entity.ContentType.create(Consts.TEXT_PLAIN,
                                                                                           Consts.UTF_8));
                MultipartEntityBuilder builder = MultipartEntityBuilder.create();
                builder.addPart("mailFrom", from).addPart("mailTo", to).addPart("mailSubject",
                                                                                subject).addPart("mailContent",
                                                                                                 content).addPart("emailType",
                                                                                                                  type);
                HttpEntity mentity = builder.build();
                hpost.setEntity(mentity);
                CloseableHttpResponse response = httpclient.execute(hpost);
                try {
                    status = response.getStatusLine().getStatusCode();
                    message = response.getStatusLine().getReasonPhrase();
                    logger.debug("stauts:" + status + " message" + message);
                } finally {
                    try {
                        if (response != null) {
                            response.close();
                        }
                    } catch (Exception e) {
                        logger.warn(e);
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e);
            throw new ServiceAppException(e);
        } finally {
            try {
                if (httpclient != null) httpclient.close();
            } catch (IOException e) {
                logger.warn(e);
            }
        }

    }
}
