
package seks.advanced.web.services;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for compareSemanticVectors complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="compareSemanticVectors">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="documentSemanticVector" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="querySemanticVector" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sharedConcepts" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "compareSemanticVectors", propOrder = {
    "documentSemanticVector",
    "querySemanticVector",
    "sharedConcepts"
})
public class CompareSemanticVectors {

    protected String documentSemanticVector;
    protected String querySemanticVector;
    protected List<String> sharedConcepts;

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

    /**
     * Gets the value of the sharedConcepts property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sharedConcepts property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSharedConcepts().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getSharedConcepts() {
        if (sharedConcepts == null) {
            sharedConcepts = new ArrayList<String>();
        }
        return this.sharedConcepts;
    }

}
