/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seks.advanced.semantic.vectors;

import java.util.ArrayList;
import java.util.HashMap;
import seks.basic.pojos.DocumentResult;
import seks.basic.pojos.SemanticWeight;

/**
 *
 * @author Paulo Figueiras
 */
public interface SemanticVectorComparison {
    public ArrayList<String> getSharedConcepts(HashMap<String, SemanticWeight> semanticVector1, HashMap<String, SemanticWeight> semanticVector2) ;
    
    public DocumentResult compareSemanticVectors(HashMap<String, SemanticWeight> semanticVector1, HashMap<String, SemanticWeight> semanticVector2, ArrayList<String> sharedConcepts) ;
    
    public HashMap<String, SemanticWeight> getSemanticVectorByDocumentID(String documentID) ;
    
    public ArrayList<String> getDocumentURIs() ;
    
    public ArrayList<DocumentResult> sortDocumentResultsByRelevance(ArrayList<DocumentResult> documentResults) ;
}
