
package com.sgm.dms.fol.reserve.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tranformReserveFundCompensationResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tranformReserveFundCompensationResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://ws.agent.service.dms.sgm.com/}reserveFundCompensationResultVO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tranformReserveFundCompensationResponse", propOrder = {
    "_return"
})
public class TranformReserveFundCompensationResponse {

    @XmlElement(name = "return")
    protected ReserveFundCompensationResultVO _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link ReserveFundCompensationResultVO }
     *     
     */
    public ReserveFundCompensationResultVO getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReserveFundCompensationResultVO }
     *     
     */
    public void setReturn(ReserveFundCompensationResultVO value) {
        this._return = value;
    }

}
