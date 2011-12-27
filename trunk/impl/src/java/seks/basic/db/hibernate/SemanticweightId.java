package seks.basic.db.hibernate;
// Generated 27/Dez/2011 14:33:27 by Hibernate Tools 3.2.1.GA


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * SemanticweightId generated by hbm2java
 */
@Embeddable
public class SemanticweightId  implements java.io.Serializable {


     private int idSemanticWeight;
     private String semanticVectorDocumentUri;

    public SemanticweightId() {
    }

    public SemanticweightId(int idSemanticWeight, String semanticVectorDocumentUri) {
       this.idSemanticWeight = idSemanticWeight;
       this.semanticVectorDocumentUri = semanticVectorDocumentUri;
    }
   

    @Column(name="idSemanticWeight", nullable=false)
    public int getIdSemanticWeight() {
        return this.idSemanticWeight;
    }
    
    public void setIdSemanticWeight(int idSemanticWeight) {
        this.idSemanticWeight = idSemanticWeight;
    }

    @Column(name="SemanticVector_documentUri", nullable=false, length=45)
    public String getSemanticVectorDocumentUri() {
        return this.semanticVectorDocumentUri;
    }
    
    public void setSemanticVectorDocumentUri(String semanticVectorDocumentUri) {
        this.semanticVectorDocumentUri = semanticVectorDocumentUri;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof SemanticweightId) ) return false;
		 SemanticweightId castOther = ( SemanticweightId ) other; 
         
		 return (this.getIdSemanticWeight()==castOther.getIdSemanticWeight())
 && ( (this.getSemanticVectorDocumentUri()==castOther.getSemanticVectorDocumentUri()) || ( this.getSemanticVectorDocumentUri()!=null && castOther.getSemanticVectorDocumentUri()!=null && this.getSemanticVectorDocumentUri().equals(castOther.getSemanticVectorDocumentUri()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getIdSemanticWeight();
         result = 37 * result + ( getSemanticVectorDocumentUri() == null ? 0 : this.getSemanticVectorDocumentUri().hashCode() );
         return result;
   }   


}


