
package seks.advanced.web.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for createQuerySemanticVector complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="createQuerySemanticVector">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="conceptsAndWeights" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createQuerySemanticVector", propOrder = {
    "conceptsAndWeights"
})
public class CreateQuerySemanticVector {

    protected String conceptsAndWeights;

    /**
     * Gets the value of the conceptsAndWeights property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConceptsAndWeights() {
        return conceptsAndWeights;
    }

    /**
     * Sets the value of the conceptsAndWeights property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConceptsAndWeights(String value) {
        this.conceptsAndWeights = value;
    }

}
