
package com.sgm.dms.fol.invoice.wsclient.commonerror;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.saic_gm.esb.schemas.common.sgmerror.v1 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SGMError_QNAME = new QName("http://www.saic-gm.com/esb/schemas/common/SGMError/v1", "SGMError");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.saic_gm.esb.schemas.common.sgmerror.v1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SGMErrorType }
     * 
     */
    public SGMErrorType createSGMErrorType() {
        return new SGMErrorType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SGMErrorType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.saic-gm.com/esb/schemas/common/SGMError/v1", name = "SGMError")
    public JAXBElement<SGMErrorType> createSGMError(SGMErrorType value) {
        return new JAXBElement<SGMErrorType>(_SGMError_QNAME, SGMErrorType.class, null, value);
    }

}
