
package com.sgm.dms.fol.reserve.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tranformFrznReserveFundCompensation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tranformFrznReserveFundCompensation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://ws.agent.service.dms.sgm.com/}reserveFundCompensationRequestVO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tranformFrznReserveFundCompensation", propOrder = {
    "arg0"
})
public class TranformFrznReserveFundCompensation {

    protected ReserveFundCompensationRequestVO arg0;

    /**
     * Gets the value of the arg0 property.
     * 
     * @return
     *     possible object is
     *     {@link ReserveFundCompensationRequestVO }
     *     
     */
    public ReserveFundCompensationRequestVO getArg0() {
        return arg0;
    }

    /**
     * Sets the value of the arg0 property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReserveFundCompensationRequestVO }
     *     
     */
    public void setArg0(ReserveFundCompensationRequestVO value) {
        this.arg0 = value;
    }

}
