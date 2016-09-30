
package com.sgm.dms.fol.bonus.client;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;

import com.sgm.dms.fol.common.service.utils.PropertiesUtil;


/**
 * OSB Service
 * 
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "BonusService", targetNamespace = "http://ws.agent.service.dms.sgm.com/")
public class BonusService
    extends Service
{

    private final static URL BONUSSERVICE_WSDL_LOCATION;
    private final static WebServiceException BONUSSERVICE_EXCEPTION;
    private final static QName BONUSSERVICE_QNAME = new QName("http://ws.agent.service.dms.sgm.com/", "BonusService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL(PropertiesUtil.getServiceURL("bonus_service_wsdl_location"));
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        } catch (IOException e1) {
			e1.printStackTrace();
		}
        BONUSSERVICE_WSDL_LOCATION = url;
        BONUSSERVICE_EXCEPTION = e;
    }

    public BonusService() {
        super(__getWsdlLocation(), BONUSSERVICE_QNAME);
    }

    public BonusService(WebServiceFeature... features) {
        super(__getWsdlLocation(), BONUSSERVICE_QNAME, features);
    }

    public BonusService(URL wsdlLocation) {
        super(wsdlLocation, BONUSSERVICE_QNAME);
    }

    public BonusService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, BONUSSERVICE_QNAME, features);
    }

    public BonusService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public BonusService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns BonusOperationWebService
     */
    @WebEndpoint(name = "BonusServicePort")
    public BonusOperationWebService getBonusServicePort() {
        return super.getPort(new QName("http://ws.agent.service.dms.sgm.com/", "BonusServicePort"), BonusOperationWebService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns BonusOperationWebService
     */
    @WebEndpoint(name = "BonusServicePort")
    public BonusOperationWebService getBonusServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://ws.agent.service.dms.sgm.com/", "BonusServicePort"), BonusOperationWebService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (BONUSSERVICE_EXCEPTION!= null) {
            throw BONUSSERVICE_EXCEPTION;
        }
        return BONUSSERVICE_WSDL_LOCATION;
    }

}