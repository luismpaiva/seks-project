
package seks.advanced.web.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getSharedConcepts complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getSharedConcepts">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="documentSemanticVector" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="querySemanticVector" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getSharedConcepts", propOrder = {
    "documentSemanticVector",
    "querySemanticVector"
})
public class GetSharedConcepts {

    protected String documentSemanticVector;
    protected String querySemanticVector;

    /**
     * Gets the value of the documentSemanticVector property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocumentSemanticVector() {
        return documentSemanticVector;
    }

    /**
     * Sets the value of the documentSemanticVector property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocumentSemanticVector(String value) {
        this.documentSemanticVector = value;
    }

    /**
     * Gets the value of the querySemanticVector property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuerySemanticVector() {
        return querySemanticVector;
    }

    /**
     * Sets the value of the querySemanticVector property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuerySemanticVector(String value) {
        this.querySemanticVector = value;
    }

}
