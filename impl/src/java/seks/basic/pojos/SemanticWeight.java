/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seks.basic.pojos;

/**
 *
 * @author Paulo Figueiras
 */
public class SemanticWeight {
    private String idDocument ;
    private String parentClass ;
    private String concept;
    private int idSemanticWeight;
    private double weight;
    
    public SemanticWeight() {}
    
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
