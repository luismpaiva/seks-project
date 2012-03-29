package seks.basic.pojos;

import java.io.Serializable;

/**
 * Semantic Weight object class.
 * 
 * @author Paulo Figueiras
 */
public class SemanticWeight implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idDocument ;
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
     * @param concept       The concept's local name
     * @param weight        The concept's weight
     */
    public SemanticWeight(int idDocument, String concept, double weight) {
        this.idDocument = idDocument ;
        this.concept = concept ;
        this.weight = weight ;
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

	public int getIdSemanticWeight() {
		return idSemanticWeight;
	}

	public void setIdSemanticWeight(int idSemanticWeight) {
		this.idSemanticWeight = idSemanticWeight;
	}
}
