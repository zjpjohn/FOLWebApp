
package com.sgm.dms.fol.reserve.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for SGMErrorType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SGMErrorType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.saic-gm.com/esb/schemas/common/SGMCommonHeader/v1}SGMCommonHeader"/>
 *         &lt;element name="ErrorCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ErrorMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Timestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="ErrorDetails" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SGMErrorType", namespace = "http://www.saic-gm.com/esb/schemas/common/SGMError/v1", propOrder = {
    "sgmCommonHeader",
    "errorCode",
    "errorMessage",
    "timestamp",
    "errorDetails"
})
public class SGMErrorType {

    @XmlElement(name = "SGMCommonHeader", namespace = "http://www.saic-gm.com/esb/schemas/common/SGMCommonHeader/v1", required = true)
    protected SGMCommonHeaderType sgmCommonHeader;
    @XmlElement(name = "ErrorCode", namespace = "http://www.saic-gm.com/esb/schemas/common/SGMError/v1", required = true)
    protected String errorCode;
    @XmlElement(name = "ErrorMessage", namespace = "http://www.saic-gm.com/esb/schemas/common/SGMError/v1")
    protected String errorMessage;
    @XmlElement(name = "Timestamp", namespace = "http://www.saic-gm.com/esb/schemas/common/SGMError/v1", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar timestamp;
    @XmlElement(name = "ErrorDetails", namespace = "http://www.saic-gm.com/esb/schemas/common/SGMError/v1")
    protected Object errorDetails;

    /**
     * Gets the value of the sgmCommonHeader property.
     * 
     * @return
     *     possible object is
     *     {@link SGMCommonHeaderType }
     *     
     */
    public SGMCommonHeaderType getSGMCommonHeader() {
        return sgmCommonHeader;
    }

    /**
     * Sets the value of the sgmCommonHeader property.
     * 
     * @param value
     *     allowed object is
     *     {@link SGMCommonHeaderType }
     *     
     */
    public void setSGMCommonHeader(SGMCommonHeaderType value) {
        this.sgmCommonHeader = value;
    }

    /**
     * Gets the value of the errorCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Sets the value of the errorCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorCode(String value) {
        this.errorCode = value;
    }

    /**
     * Gets the value of the errorMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets the value of the errorMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorMessage(String value) {
        this.errorMessage = value;
    }

    /**
     * Gets the value of the timestamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the value of the timestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTimestamp(XMLGregorianCalendar value) {
        this.timestamp = value;
    }

    /**
     * Gets the value of the errorDetails property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getErrorDetails() {
        return errorDetails;
    }

    /**
     * Sets the value of the errorDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setErrorDetails(Object value) {
        this.errorDetails = value;
    }

}
