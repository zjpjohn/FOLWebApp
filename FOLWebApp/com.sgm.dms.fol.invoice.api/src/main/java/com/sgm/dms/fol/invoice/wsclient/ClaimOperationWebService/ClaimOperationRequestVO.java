
package com.sgm.dms.fol.invoice.wsclient.ClaimOperationWebService;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>claimOperationRequestVO complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="claimOperationRequestVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="crosscheck_amount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="simpleClaimOrder" type="{http://ws.agent.service.dms.sgm.com/}simpleClaimOrderVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ts_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ts_time" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "claimOperationRequestVO", propOrder = {
    "crosscheckAmount",
    "simpleClaimOrder",
    "tsId",
    "tsTime"
})
public class ClaimOperationRequestVO {

    @XmlElement(name = "crosscheck_amount")
    protected int crosscheckAmount;
    @XmlElement(nillable = true)
    protected List<SimpleClaimOrderVO> simpleClaimOrder;
    @XmlElement(name = "ts_id")
    protected String tsId;
    @XmlElement(name = "ts_time")
    protected long tsTime;

    /**
     * ��ȡcrosscheckAmount���Ե�ֵ��
     * 
     */
    public int getCrosscheckAmount() {
        return crosscheckAmount;
    }

    /**
     * ����crosscheckAmount���Ե�ֵ��
     * 
     */
    public void setCrosscheckAmount(int value) {
        this.crosscheckAmount = value;
    }

    /**
     * Gets the value of the simpleClaimOrder property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the simpleClaimOrder property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSimpleClaimOrder().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SimpleClaimOrderVO }
     * 
     * 
     */
    public List<SimpleClaimOrderVO> getSimpleClaimOrder() {
        if (simpleClaimOrder == null) {
            simpleClaimOrder = new ArrayList<SimpleClaimOrderVO>();
        }
        return this.simpleClaimOrder;
    }

    /**
     * ��ȡtsId���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTsId() {
        return tsId;
    }

    /**
     * ����tsId���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTsId(String value) {
        this.tsId = value;
    }

    /**
     * ��ȡtsTime���Ե�ֵ��
     * 
     */
    public long getTsTime() {
        return tsTime;
    }

    /**
     * ����tsTime���Ե�ֵ��
     * 
     */
    public void setTsTime(long value) {
        this.tsTime = value;
    }

}
