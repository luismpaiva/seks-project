
package seks.advanced.web.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for documentResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="documentResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idDocument" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="relevancePercentage" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "documentResult", propOrder = {
    "idDocument",
    "relevancePercentage"
})
public class DocumentResult {

    protected String idDocument;
    protected int relevancePercentage;

    /**
     * Gets the value of the idDocument property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdDocument() {
        return idDocument;
    }

    /**
     * Sets the value of the idDocument property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdDocument(String value) {
        this.idDocument = value;
    }

    /**
     * Gets the value of the relevancePercentage property.
     * 
     */
    public int getRelevancePercentage() {
        return relevancePercentage;
    }

    /**
     * Sets the value of the relevancePercentage property.
     * 
     */
    public void setRelevancePercentage(int value) {
        this.relevancePercentage = value;
    }

}
