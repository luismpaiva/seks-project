package seks.basic.pojos;

import java.io.Serializable;

/**
 * Document Result object class.
 * 
 * @author Paulo Figueiras
 */
public class DocumentResult implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idDocument ;
    private int relevancePercentage ;
    
    /**
     * Class constructor.
     */
    public DocumentResult() {}
    
    /**
     * Class constructor with parameter insertion
     * 
     * @param idDocument            An unique document's ID
     * @param relevancePercentage   The relevance percentage of the document, in 
     *                              comparison with a specific query
     */
    public DocumentResult(int idDocument, int relevancePercentage) {
        this.idDocument = idDocument ;
        this.relevancePercentage = relevancePercentage ;
    }

    /**
     * @return the idDocument
     */
    public int getIdDocument() {
        return idDocument;
    }

    /**
     * @param idDocument the idDocument to set
     */
    public void setIdDocument(int idDocument) {
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
