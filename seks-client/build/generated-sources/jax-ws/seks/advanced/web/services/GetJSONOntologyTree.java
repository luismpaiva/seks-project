
package seks.advanced.web.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getJSONOntologyTree complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getJSONOntologyTree">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="obj" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="clsName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="last" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getJSONOntologyTree", propOrder = {
    "obj",
    "clsName",
    "last"
})
public class GetJSONOntologyTree {

    protected String obj;
    protected String clsName;
    protected boolean last;

    /**
     * Gets the value of the obj property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObj() {
        return obj;
    }

    /**
     * Sets the value of the obj property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObj(String value) {
        this.obj = value;
    }

    /**
     * Gets the value of the clsName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClsName() {
        return clsName;
    }

    /**
     * Sets the value of the clsName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClsName(String value) {
        this.clsName = value;
    }

    /**
     * Gets the value of the last property.
     * 
     */
    public boolean isLast() {
        return last;
    }

    /**
     * Sets the value of the last property.
     * 
     */
    public void setLast(boolean value) {
        this.last = value;
    }

}
