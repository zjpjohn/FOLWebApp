package com.sgm.dms.fol.invoice.wsclient.ClaimOperationWebService.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;

import com.sgm.dms.fol.common.service.utils.PropertiesUtil;
import com.sgm.dms.fol.invoice.wsclient.ClaimOperationWebService.ClaimOperationWebService;

import javax.xml.ws.Service;

/**
 * OSB Service
 *
 * This class was generated by Apache CXF 2.7.14
 * 2016-06-12T10:03:16.772+08:00
 * Generated source version: 2.7.14
 * 
 */
@WebServiceClient(name = "ClaimOperationWebServiceImplService", targetNamespace = "http://impl.ws.agent.service.dms.sgm.com/") 
public class ClaimOperationWebServiceImplService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://impl.ws.agent.service.dms.sgm.com/", "ClaimOperationWebServiceImplService");
    public final static QName ClaimOperationWebServiceImplPort = new QName("http://impl.ws.agent.service.dms.sgm.com/", "ClaimOperationWebServiceImplPort");
    static {
        URL url = null;
        try {
            url = new URL(PropertiesUtil.getServiceURL("wol_claim_operation_wsdl_url"));
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(ClaimOperationWebServiceImplService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", url);
        } catch (IOException e) {
        	java.util.logging.Logger.getLogger(ClaimOperationWebServiceImplService.class.getName())
        	.log(java.util.logging.Level.INFO, 
        			"url地址 {0}获取失败", "wol_claim_operation_wsdl_url");
		}
        WSDL_LOCATION = url;
    }

    public ClaimOperationWebServiceImplService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public ClaimOperationWebServiceImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ClaimOperationWebServiceImplService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public ClaimOperationWebServiceImplService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public ClaimOperationWebServiceImplService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public ClaimOperationWebServiceImplService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns ClaimOperationWebService
     */
    @WebEndpoint(name = "ClaimOperationWebServiceImplPort")
    public ClaimOperationWebService getClaimOperationWebServiceImplPort() {
        return super.getPort(ClaimOperationWebServiceImplPort, ClaimOperationWebService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ClaimOperationWebService
     */
    @WebEndpoint(name = "ClaimOperationWebServiceImplPort")
    public ClaimOperationWebService getClaimOperationWebServiceImplPort(WebServiceFeature... features) {
        return super.getPort(ClaimOperationWebServiceImplPort, ClaimOperationWebService.class, features);
    }

}
