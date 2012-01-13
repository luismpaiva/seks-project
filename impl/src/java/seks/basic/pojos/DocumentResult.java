package seks.basic.pojos;

import java.io.Serializable;

/**
 * Document Result object class.
 * 
 * @author Paulo Figueiras
 */
public class DocumentResult implements Serializable {
    
    private String idDocument ;
    private int relevancePercentage ;
    
    /**
     * Class constructor.
     */
    public DocumentResult() {}
    
    /**
     * Class constructor with parameter insertion
     * 
     * @param idDocument            An unique document's URI
     * @param relevancePercentage   The relevance percentage of the document, in 
     *                              comparison with a specific query
     */
    public DocumentResult(String idDocument, int relevancePercentage) {
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
    public int getRelevancePercentage() {
        return relevancePercentage;
    }

    /**
     * @param relevancePercentage the relevancePercentage to set
     */
    public void setRelevancePercentage(int relevancePercentage) {
        this.relevancePercentage = relevancePercentage;
    }
    
}
