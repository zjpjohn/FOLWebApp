
package com.sgm.dms.fol.invoice.wsclient.commonerror;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import com.sgm.dms.fol.invoice.wsclient.commonheader.SGMCommonHeaderType;


/**
 * <p>SGMErrorType complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
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
@XmlType(name = "SGMErrorType", propOrder = {
    "sgmCommonHeader",
    "errorCode",
    "errorMessage",
    "timestamp",
    "errorDetails"
})
public class SGMErrorType {

    @XmlElement(name = "SGMCommonHeader", namespace = "http://www.saic-gm.com/esb/schemas/common/SGMCommonHeader/v1", required = true)
    protected SGMCommonHeaderType sgmCommonHeader;
    @XmlElement(name = "ErrorCode", required = true)
    protected String errorCode;
    @XmlElement(name = "ErrorMessage")
    protected String errorMessage;
    @XmlElement(name = "Timestamp", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar timestamp;
    @XmlElement(name = "ErrorDetails")
    protected Object errorDetails;

    /**
     * ��ȡsgmCommonHeader���Ե�ֵ��
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
     * ����sgmCommonHeader���Ե�ֵ��
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
     * ��ȡerrorCode���Ե�ֵ��
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
     * ����errorCode���Ե�ֵ��
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
     * ��ȡerrorMessage���Ե�ֵ��
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
     * ����errorMessage���Ե�ֵ��
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
     * ��ȡtimestamp���Ե�ֵ��
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
     * ����timestamp���Ե�ֵ��
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
     * ��ȡerrorDetails���Ե�ֵ��
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
     * ����errorDetails���Ե�ֵ��
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
