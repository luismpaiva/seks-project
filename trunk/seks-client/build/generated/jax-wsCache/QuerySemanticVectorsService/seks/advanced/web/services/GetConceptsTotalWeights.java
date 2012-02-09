
package seks.advanced.web.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getConceptsTotalWeights complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getConceptsTotalWeights">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="statisticVector" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="conceptsAndKeywords" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getConceptsTotalWeights", propOrder = {
    "statisticVector",
    "conceptsAndKeywords"
})
public class GetConceptsTotalWeights {

    protected String statisticVector;
    protected String conceptsAndKeywords;

    /**
     * Gets the value of the statisticVector property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatisticVector() {
        return statisticVector;
    }

    /**
     * Sets the value of the statisticVector property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatisticVector(String value) {
        this.statisticVector = value;
    }

    /**
     * Gets the value of the conceptsAndKeywords property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConceptsAndKeywords() {
        return conceptsAndKeywords;
    }

    /**
     * Sets the value of the conceptsAndKeywords property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConceptsAndKeywords(String value) {
        this.conceptsAndKeywords = value;
    }

}
