
package com.sgm.dms.fol.invoice.header;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.saic_gm.esb.schemas.common.sgmcommonheader.v1 package. 
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

    private final static QName _SGMCommonHeader_QNAME = new QName("http://www.saic-gm.com/esb/schemas/common/SGMCommonHeader/v1", "SGMCommonHeader");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.saic_gm.esb.schemas.common.sgmcommonheader.v1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SGMCommonHeaderType }
     * 
     */
    public SGMCommonHeaderType createSGMCommonHeaderType() {
        return new SGMCommonHeaderType();
    }

    /**
     * Create an instance of {@link CachePolicyType }
     * 
     */
    public CachePolicyType createCachePolicyType() {
        return new CachePolicyType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SGMCommonHeaderType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.saic-gm.com/esb/schemas/common/SGMCommonHeader/v1", name = "SGMCommonHeader")
    public JAXBElement<SGMCommonHeaderType> createSGMCommonHeader(SGMCommonHeaderType value) {
        return new JAXBElement<SGMCommonHeaderType>(_SGMCommonHeader_QNAME, SGMCommonHeaderType.class, null, value);
    }

}
