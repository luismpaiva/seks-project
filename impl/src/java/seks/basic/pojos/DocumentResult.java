/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seks.basic.pojos;

/**
 *
 * @author Paulo Figueiras
 */
public class DocumentResult {
    
    private String idDocument ;
    private Double relevancePercentage ;
    
    public DocumentResult() {}
    
    public DocumentResult(String idDocument, Double relevancePercentage) {
        this.idDocument = idDocument ;
        this.relevancePercentage = relevancePercentage ;
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
     * @return the relevancePercentage
     */
    public Double getRelevancePercentage() {
        return relevancePercentage;
    }

    /**
     * @param relevancePercentage the relevancePercentage to set
     */
    public void setRelevancePercentage(Double relevancePercentage) {
        this.relevancePercentage = relevancePercentage;
    }
    
}
