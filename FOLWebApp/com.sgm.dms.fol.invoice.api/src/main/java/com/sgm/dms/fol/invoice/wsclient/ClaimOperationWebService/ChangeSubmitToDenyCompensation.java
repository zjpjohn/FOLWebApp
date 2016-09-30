
package com.sgm.dms.fol.invoice.wsclient.ClaimOperationWebService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>changeSubmitToDenyCompensation complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="changeSubmitToDenyCompensation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://ws.agent.service.dms.sgm.com/}claimOperationCompensationRequestVO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "changeSubmitToDenyCompensation", propOrder = {
    "arg0"
})
public class ChangeSubmitToDenyCompensation {

    protected ClaimOperationCompensationRequestVO arg0;

    /**
     * ��ȡarg0���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link ClaimOperationCompensationRequestVO }
     *     
     */
    public ClaimOperationCompensationRequestVO getArg0() {
        return arg0;
    }

    /**
     * ����arg0���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link ClaimOperationCompensationRequestVO }
     *     
     */
    public void setArg0(ClaimOperationCompensationRequestVO value) {
        this.arg0 = value;
    }

}
