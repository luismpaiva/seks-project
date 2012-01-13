package seks.basic.pojos;

import java.io.Serializable;

/**
 * Semantic Weight object class.
 * 
 * @author Paulo Figueiras
 */
public class SemanticWeight implements Serializable{
    private String idDocument ;
    private String parentClass ;
    private String concept;
    private int idSemanticWeight;
    private double weight;
    
    /**
     * Class constructor.
     */
    public SemanticWeight() {}
    
    /**
     * Class constructor with parameter insertion.
     * 
     * @param idDocument    An unique document's URI
     * @param parentClass   The concept's parent class local name
     * @param concept       The concept's local name
     * @param weight        The concept's weight
     */
    public SemanticWeight(String idDocument, String parentClass, String concept, double weight) {
        this.idDocument = idDocument ;
        this.parentClass = parentClass ;
        this.concept = concept ;
        this.weight = weight ;
    }

    /**
     * @return the idDocument
     */
    public String getIdDocument() {
        return idDocument;
    }

    /**
     * @param idDocument the idDocument to set
     */
    public void setIdDocument(String idDocument) {
        this.idDocument = idDocument;
    }

    /**
     * @return the parentClass
     */
    public String getParentClass() {
        return parentClass;
    }

    /**
     * @param parentClass the parentClass to set
     */
    public void setParentClass(String parentClass) {
        this.parentClass = parentClass;
    }

    /**
     * @return the concept
     */
    public String getConcept() {
        return concept;
    }

    /**
     * @param concept the concept to set
     */
    public void setConcept(String concept) {
        this.concept = concept;
    }

    /**
     * @return the weight
     */
    public double getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }
}
